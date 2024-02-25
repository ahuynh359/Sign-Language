package com.demo.learnsignlanguage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.learnsignlanguage.databinding.FragmentLearnBinding
import com.demo.learnsignlanguage.ui.adapter.LearningAdapter
import com.demo.learnsignlanguage.utils.LearningData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearnFragment : Fragment() , LearningAdapter.OnItemClickListener {
    private var _binding: FragmentLearnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        val adapter = LearningAdapter(this)
        val gridLayout = GridLayoutManager(context, 2)
        binding.apply {
            rcyLearn.adapter = adapter
            rcyLearn.layoutManager = gridLayout

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(position: Int, item: LearningData) {
        when(position){
            0 -> displayPDF("a.pdf")
            1 -> displayPDF("asl_number.pdf")
            2 -> displayPDF("asl_alphabet.pdf")
            3 -> moveToCardFragment()

        }
    }

    private fun moveToCardFragment(){
        val action = LearnFragmentDirections.actionLearnFragmentToCardFragment()
        findNavController().navigate(action)
    }


    private fun displayPDF(name : String){
        val action = LearnFragmentDirections.actionLearnFragmentToPdfPreviewFragment(pdfPath = name)
        findNavController().navigate(action)
    }

}