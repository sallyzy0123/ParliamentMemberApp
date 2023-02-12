package com.example.myfirstapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.data.ParliamentMember
import com.example.myfirstapplication.fragment.*

// date: 11 Oct 2022

// Adapter for creating the RecyclerView in Member Fragment
class RecyclerAdapter(): ListAdapter<ParliamentMember, RecyclerAdapter.MemberViewHolder>(MemberDiffCallback()) {

    lateinit var items : LiveData<List<ParliamentMember>>

    // for setting the data in the recyclerview
    fun setListData(data:LiveData<List<ParliamentMember>>) {
        this.items = data
    }

    // this function is to inflate list_item to the whole layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MemberViewHolder(itemView)
    }

    // populate the view with the data corresponding to the position in the recycler view
    override fun onBindViewHolder(holder: RecyclerAdapter.MemberViewHolder, position: Int) {

        // to get the value from the Livedata
        val currentItem = items.value?.get(position)

        // use glide to display the image
        val _image = currentItem?.pictureUrl
        val image = "https://avoindata.eduskunta.fi/${_image}"
        GlideApp.with(holder.itemMembersImg)
            .load(image)
            .into(holder.itemMembersImg)

        // populate the view with the data
        holder.itemFirstName.text = currentItem?.firstname
        holder.itemLastName.text = currentItem?.lastname
        holder.itemParty.text = currentItem?.party
        holder.itemHetekaId.text = currentItem?.hetekaId.toString()
        holder.itemSeatNumber.text = currentItem?.seatNumber.toString()
        holder.itemMinister.text = currentItem?.minister.toString()

        // implement the onClickListener for each item to Second Fragment
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v?.context as AppCompatActivity
                val secondFragment = SecondFragment()
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.flfragment, secondFragment)
                    .commit()
            }
        })
    }

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemMembersImg : ImageView = itemView.findViewById(R.id.membersImg)
        val itemFirstName : TextView = itemView.findViewById(R.id.firstName)
        val itemLastName : TextView = itemView.findViewById(R.id.lastName)
        val itemParty : TextView = itemView.findViewById(R.id.partyName)
        val itemHetekaId : TextView = itemView.findViewById(R.id.hetekaId)
        val itemSeatNumber : TextView = itemView.findViewById(R.id.seatNumber)
        val itemMinister : TextView = itemView.findViewById(R.id.minister)

    }
}

// inform Adapter how to compute list updates when using DiffUtil
class MemberDiffCallback: DiffUtil.ItemCallback<ParliamentMember>() {

    // Called to decide whether two objects represent the same item.
    override fun areItemsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.hetekaId == newItem.hetekaId
    }

    // Called to decide whether two items have the same data.
    // This information is used to detect if the contents of an item have changed.
    override fun areContentsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.pictureUrl == newItem.pictureUrl && oldItem.firstname == newItem.firstname
                && oldItem.lastname == newItem.lastname && oldItem.party == newItem.party
                && oldItem.seatNumber == newItem.seatNumber && oldItem.minister == newItem.minister
    }
}

