package com.example.myfirstapplication.fragment

import androidx.lifecycle.*
import com.example.myfirstapplication.data.*
import com.example.myfirstapplication.network.MemberApi
import kotlinx.coroutines.launch

// date: 11 Oct 2022


// The MemberViewModel that is attached to the Member Fragment.
class MemberViewModel(): ViewModel() {

    // fetch the data from Database to Member Fragment
    private val parliamentMemberDao = AppDatabase.getInstance().parliamentMemberDao
    private val repository = MemberRepository(parliamentMemberDao)
    var allMembers = repository.readAllData
}

