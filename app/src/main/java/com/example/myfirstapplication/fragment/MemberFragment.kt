package com.example.myfirstapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapplication.RecyclerAdapter
import com.example.myfirstapplication.databinding.FragmentMemberBinding

// date: 11 Oct 2022


// This fragment shows the recyclerview of Members with their basic properties
class MemberFragment : Fragment() {

    val viewModel: MemberViewModel by viewModels()
    private lateinit var binding: FragmentMemberBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize the binding
        binding = FragmentMemberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // implement the RecyclerView by RecyclerAdapter
        adapter = RecyclerAdapter()
        binding.MemberRecyclerView.adapter = adapter
        binding.MemberRecyclerView.layoutManager = LinearLayoutManager(context)

        // implement the data that shown in the RecyclerView
        val list = viewModel.allMembers
        adapter.setListData(list)
        viewModel.allMembers.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}



