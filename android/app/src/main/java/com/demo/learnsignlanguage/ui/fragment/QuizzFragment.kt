package com.demo.learnsignlanguage.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.demo.learnsignlanguage.databinding.FragmentQuizzBinding
import com.demo.learnsignlanguage.ui.adapter.AnswerSelectionListener
import com.demo.learnsignlanguage.ui.adapter.QuizAdapter
import com.demo.learnsignlanguage.utils.Constants.questions
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class QuizzFragment : Fragment(), AnswerSelectionListener {
    private var _binding: FragmentQuizzBinding? = null
    private val binding get() = _binding!!
    private var progressValue = 0
    private var score = 0
    private val max = 5


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizzBinding.inflate(layoutInflater)
        val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAppClosingDialog()
            }
        }
        return binding.root
    }

    private fun showAppClosingDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Warning")
            .setMessage("Do you really want to submit?")
            .setPositiveButton("Yes") { _, _ -> navigateToDone() }
            .setNegativeButton("No", null)
            .show()
    }

    private fun navigateToDone(){
        val action = QuizzFragmentDirections.actionQuizzFragmentToDoneFragment(score)
        findNavController().navigate(action)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmit.setOnClickListener {
            showAppClosingDialog()
        }
        binding.progressBar.max = max - 1
        val randomItems = questions.shuffled().take(max).toMutableList()
        val adapter = QuizAdapter(randomItems, this)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("ABCD", position.toString())
                val progress = position
                binding.progressBar.progress = progress

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })


    }

    override fun onAnswerSelected(isCorrect: Boolean) {

        if (isCorrect) {
            score++
            val text = "Score : $score"
            binding.tvScore.text = text


        }

    }



}