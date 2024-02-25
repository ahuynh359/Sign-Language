package com.demo.learnsignlanguage.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.demo.learnsignlanguage.R
import com.demo.learnsignlanguage.data.api.CardApi
import com.demo.learnsignlanguage.databinding.FragmentCardBinding
import com.demo.learnsignlanguage.ui.adapter.CardAdapter
import com.demo.learnsignlanguage.ui.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var mediaPlayer: MediaPlayer
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CardViewModel>()
    private var cardAdapter: CardAdapter? = null

    //    private var list: MutableList<CardApi> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.button_pressed)


        //getData()
        setUpViewPager()
        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager2.currentItem
            var nextItem: Int = 0
            if (currentItem < 36) {
                nextItem = currentItem + 1
            }
            binding.viewPager2.currentItem = nextItem
            playSound()

        }

        binding.btnPre.setOnClickListener {
            val currentItem = binding.viewPager2.currentItem
            var nextItem: Int = 0
            if (currentItem > 0) {
                nextItem = currentItem - 1
            }
            binding.viewPager2.currentItem = nextItem
            playSound()

        }

        binding.btnShuffle.setOnClickListener {
            cardAdapter?.shuffleItems()
        }


    }

    private fun playSound() {
        mediaPlayer.seekTo(0)

        // Start playing the sound
        mediaPlayer.start()
    }

    private fun setUpViewPager() {

        val listCard = mutableListOf<CardApi>(
            CardApi(0, R.drawable.n0, "0", ""),
            CardApi(1, R.drawable.n1, "1", ""),
            CardApi(2, R.drawable.n2, "2", ""),
            CardApi(3, R.drawable.n3, "3", ""),
            CardApi(4, R.drawable.n4, "4", ""),
            CardApi(5, R.drawable.n5, "5", ""),
            CardApi(6, R.drawable.n6, "6", ""),
            CardApi(7, R.drawable.n7, "7", ""),
            CardApi(8, R.drawable.n8, "8", ""),
            CardApi(9, R.drawable.n9, "9", ""),
            CardApi(10, R.drawable.a, "a", ""),
            CardApi(11, R.drawable.b, "b", ""),
            CardApi(12, R.drawable.c, "c", ""),
            CardApi(13, R.drawable.d, "d", ""),
            CardApi(14, R.drawable.e, "e", ""),
            CardApi(15, R.drawable.f, "f", ""),
            CardApi(16, R.drawable.g, "g", ""),
            CardApi(17, R.drawable.h, "h", ""),
            CardApi(18, R.drawable.i, "i", ""),
            CardApi(19, R.drawable.j, "j", ""),
            CardApi(20, R.drawable.k, "k", ""),
            CardApi(21, R.drawable.l, "l", ""),
            CardApi(22, R.drawable.m, "m", ""),
            CardApi(23, R.drawable.n, "n", ""),
            CardApi(24, R.drawable.o, "o", ""),
            CardApi(25, R.drawable.p, "p", ""),
            CardApi(26, R.drawable.q, "q", ""),
            CardApi(27, R.drawable.r, "r", ""),
            CardApi(28, R.drawable.s, "s", ""),
            CardApi(29, R.drawable.t, "t", ""),
            CardApi(30, R.drawable.u, "u", ""),
            CardApi(31, R.drawable.v, "v", ""),
            CardApi(32, R.drawable.w, "w", ""),
            CardApi(33, R.drawable.x, "x", ""),
            CardApi(34, R.drawable.y, "y", ""),
            CardApi(35, R.drawable.z, "z", "")
        )
        listCard.shuffled()
        cardAdapter = CardAdapter(listCard)
        binding.viewPager2.adapter = cardAdapter
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val text = (position + 1).toString() + "/ " + 36.toString()
                binding.tvTotal.text = text


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

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

//    private fun getData() {
//        viewModel.getCards().observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is NetworkResult.Loading -> binding.progressBar.show()
//                is NetworkResult.Success -> {
//                    val data = response.data
//                    list = data?.data?.toMutableList()!!
//
//                    binding.progressBar.hide()
//
//                }
//
//                is NetworkResult.Failure -> {
//                    val data = response
//                    binding.progressBar.hide()
//
//                }
//            }
//
//
//        }
//
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}