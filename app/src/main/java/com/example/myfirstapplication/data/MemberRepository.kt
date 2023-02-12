package com.example.myfirstapplication.data

import androidx.lifecycle.LiveData

// date: 11 Oct 2022

// The purpose of repository is to connect the Database to viewModel
class MemberRepository(private val parliamentMemberDao: ParliamentMemberDao) {

    // read all data for viewModel
    val readAllData: LiveData<List<ParliamentMember>> = parliamentMemberDao.getAll()
}

