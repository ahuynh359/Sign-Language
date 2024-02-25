package com.demo.learnsignlanguage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.learnsignlanguage.databinding.FragmentChatBinding
import com.demo.learnsignlanguage.ui.adapter.MessageAdapter
import com.demo.learnsignlanguage.ui.adapter.OnMessageClicked
import com.demo.learnsignlanguage.utils.Constants.messagesList


class ChatFragment : Fragment(), OnMessageClicked {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentChatBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MessageAdapter(messagesList, this)
        binding.messagesRecyclerView.adapter = adapter
        binding.messagesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun handle(position: Int) {
        TODO("Not yet implemented")
    }
}