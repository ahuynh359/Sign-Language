package com.demo.learnsignlanguage.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import android.util.Log
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.Rot90Op
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class GestureClassifierHelper(
    var threshold: Float = 0.0f,
    var numThreads: Int = 2,
    var maxResults: Int = 3,
    var currentDelegate: Int = 0,
    val context: Context,
    val gestureClassifierListener: ClassifierListener?
) {
    private var gestureClassifier: ImageClassifier? = null

    init {
        setupGestureClassifier()
    }

    fun clearGestureClassifier() {
        gestureClassifier = null
    }

    private fun setupGestureClassifier() {
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)


        val baseOptionsBuilder = BaseOptions.builder().setNumThreads(numThreads)
        when (currentDelegate) {
            DELEGATE_CPU -> {
                // Default
            }
            DELEGATE_GPU -> {
                if (CompatibilityList().isDelegateSupportedOnThisDevice) {
                    baseOptionsBuilder.useGpu()
                } else {
                    gestureClassifierListener?.onError(
                        "GPU is not supported on " +
                                "this device"
                    )
                }
            }
            DELEGATE_NNAPI -> {
                baseOptionsBuilder.useNnapi()
            }
        }

        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        try {
            gestureClassifier =
                ImageClassifier.createFromFileAndOptions(
                    context, MODEL_PATH,
                    optionsBuilder.build()
                )
        } catch (e: IllegalStateException) {
            gestureClassifierListener?.onError(
                "Gesture classifier failed to initialize. See error logs for " +
                        "details"
            )
            Log.e(TAG, "TFLite failed to load model with error: " + e.message)
        }
    }

    fun classify(image: Bitmap, imageRotation: Int) {
        if (gestureClassifier == null) {
            setupGestureClassifier()
        }

        var inferenceTime = SystemClock.uptimeMillis()

        val imageProcessor =
            ImageProcessor.Builder()
                .add(Rot90Op(imageRotation / 90))
                .build()

        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(image))

        val results = gestureClassifier?.classify(tensorImage)
        inferenceTime = SystemClock.uptimeMillis() - inferenceTime
        gestureClassifierListener?.onResults(results, inferenceTime)
    }

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(
            results: List<Classifications>?,
            inferenceTime: Long
        )
    }

    companion object {
        const val DELEGATE_CPU = 0
        const val DELEGATE_GPU = 1
        const val DELEGATE_NNAPI = 2

        private const val MODEL_PATH = "model_metadata.tflite"
        private const val TAG = "GestureClassifierHelper"
    }
}
