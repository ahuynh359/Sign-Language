package com.demo.learnsignlanguage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.learnsignlanguage.databinding.FragmentPdfPreviewBinding


class PdfPreviewFragment : Fragment() {
    private var _binding: FragmentPdfPreviewBinding? = null
    private val binding get() = _binding!!
    private var pdfLoaded = false
    val args: PdfPreviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPdfPreviewBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pdfFilePath = args.pdfPath
        binding.pdfViewer.fromAsset(pdfFilePath)
            .load()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}