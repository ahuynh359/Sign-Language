package com.demo.learnsignlanguage.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.learnsignlanguage.data.model.User
import com.demo.learnsignlanguage.network.NetworkResult
import com.demo.learnsignlanguage.ui.activity.AuthActivity
import com.demo.learnsignlanguage.ui.viewmodel.SettingViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.demo.learnsignlanguage.databinding.FragmentSettingBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLogout.setOnClickListener {
            signOut()
        }
        getAuthState()
        getUser()


        // Tạo một danh sách các điểm dữ liệu (ví dụ: thời gian và số liệu)
        val entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 20f))
        entries.add(Entry(2f, 40f))
        entries.add(Entry(3f, 30f))
        entries.add(Entry(4f, 50f))
        entries.add(Entry(5f, 60f))

        val dataSet = LineDataSet(entries, "Thời gian sử dụng ứng dụng")

        dataSet.color = Color.BLUE

        val lineData = LineData(dataSet)

        binding.lineChart.data = lineData

        binding.lineChart.description.isEnabled = false
        binding.lineChart.xAxis.isEnabled = false
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.legend.isEnabled = true
        binding.lineChart.animateY(1000)

    }

    @ExperimentalCoroutinesApi
    private fun getAuthState() {
        viewModel.getAuthState().observe(viewLifecycleOwner) { isUserSignedOut ->
            if (isUserSignedOut) {
                startActivity(Intent(requireActivity(), AuthActivity::class.java))

            }
        }
    }

    private fun signOut() {
        viewModel.signOut().observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> binding.progressBar.hide()
                is NetworkResult.Failure -> {
                    print(response.error)
                    binding.progressBar.hide()
                }
            }
        }
    }

    private fun setUserDataToView(data: User) {

        Glide.with(requireContext()).load(data.photoUrl).circleCrop().transition(DrawableTransitionOptions.withCrossFade()).into(binding.imvUser)
        binding.tvName.text = data.name
    }

    private fun getUser() {
        viewModel.getUser().observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    setUserDataToView(response.data)
                    binding.progressBar.hide()
                }

                is NetworkResult.Failure -> {
                    binding.progressBar.hide()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}