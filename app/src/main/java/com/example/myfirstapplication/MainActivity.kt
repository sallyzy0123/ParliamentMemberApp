package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myfirstapplication.data.AppDatabase
import com.example.myfirstapplication.databinding.ActivityMainBinding
import com.example.myfirstapplication.fragment.MemberFragment
import com.example.myfirstapplication.fragment.SecondFragment
import com.example.myfirstapplication.network.MemberApi
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// date: 11 Oct 2022


// MainActivity has two fragment connected by button
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding the Main Activity
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memberFragment = MemberFragment()
        val secondFragment = SecondFragment()

        // get the data from network and insert them in the Room Database
        GlobalScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            val allMembers = MemberApi.retrofitService.getMembers()
            allMembers.forEach{
                AppDatabase.getInstance().parliamentMemberDao.insert(it)
            }
        }

        // initialize the Member Fragment as the home page
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, memberFragment)
            commit()
        }

        // click the Fragment 1 to Member Fragment
        val btnFragment1: Button = findViewById(R.id.btnFragment1)
        btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, memberFragment)
                commit()
            }
        }

        // click the Fragment 2 to Second Fragment
        val btnFragment2: Button = findViewById(R.id.btnFragment2)
        btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flfragment, secondFragment)
                commit()
            }
        }

    }
}