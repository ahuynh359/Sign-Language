package com.demo.learnsignlanguage.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.demo.learnsignlanguage.R
import com.demo.learnsignlanguage.databinding.FragmentCardBinding
import com.demo.learnsignlanguage.databinding.FragmentDoneBinding
import com.demo.learnsignlanguage.databinding.FragmentGameBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoneFragment : Fragment() {


    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!
    private val args: DoneFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoneBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = args.score
        binding.tvScore.text = "Score : $score"

        binding.btnReplay.setOnClickListener {
            view.findNavController().popBackStack()
        }

        binding.btnGame.setOnClickListener {
            view.findNavController().popBackStack()
            view.findNavController().popBackStack()
        }

    }


}