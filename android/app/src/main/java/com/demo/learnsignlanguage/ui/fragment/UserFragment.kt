package com.demo.learnsignlanguage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.learnsignlanguage.databinding.FragmentUserBinding
import com.demo.learnsignlanguage.ui.adapter.OnItemClicked
import com.demo.learnsignlanguage.ui.adapter.UserAdapter
import com.demo.learnsignlanguage.utils.Constants.users


class UserFragment : Fragment(), OnItemClicked {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter(users,this)
        binding.rcyUser.adapter = adapter
        binding.rcyUser.layoutManager =
            LinearLayoutManager(requireContext())


    }

    override fun handle(position: Int) {
       val action = UserFragmentDirections.actionUserFragmentToChatFragment()
        findNavController().navigate(action)
    }


}