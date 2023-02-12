package com.example.myfirstapplication.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapplication.data.*
import com.example.myfirstapplication.network.MemberApi
import kotlinx.coroutines.launch

// date: 11 Oct 2022


// The SecondViewModel that is attached to the SecondFragment.
class SecondViewModel: ViewModel() {

    // fetch the data from Database to Second Fragment
    private val parliamentMemberDao = AppDatabase.getInstance().parliamentMemberDao
    private val repository = MemberRepository(parliamentMemberDao)
    var allSecMembers = repository.readAllData

}
