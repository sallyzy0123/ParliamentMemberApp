package com.example.myfirstapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var secViewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // initialize the binding and secViewModel
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        secViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // click the Next Button to next random parliament member
        binding.btnNext.setOnClickListener {
            updateUI()
        }
    }

    private fun updateUI() {
        secViewModel = SecondViewModel()
        val currentItem = secViewModel.allSecMembers.value?.first()

        // update the view
        binding.secFirstName.text = currentItem?.firstname
        binding.secLastName.text = currentItem?.lastname
        binding.secPartyName.text = currentItem?.party
        binding.secHetekaId.text = currentItem?.hetekaId.toString()
    }
}