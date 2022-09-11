package com.example.myapplication.contactsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.extensions.addImage
import com.example.myapplication.model.User


interface UserActionListener {
    fun onUserDelete(user: User)
}

class UsersAdapter(private val actionListener: UserActionListener) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener {


    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        binding.trashBinIB.setOnClickListener(this)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            holder.itemView.tag = user
            trashBinIB.tag = user
            nameSurnameTV.text = user.name
            careerTV.text = user.occupy
            if (user.photo.isNotBlank()) {
                avatarIV.addImage(user)
            } else {
                avatarIV.setImageResource(R.drawable.me)
            }
        }

    }

    override fun getItemCount(): Int = users.size

    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onClick(v: View) {
        val user = v.tag as User
        val imageButton = ImageButton(v.context)
        imageButton.setOnClickListener {
        }
        val context = v.context
        when (v.id) {
            R.id.trashBinIB -> {
                val indexToDelete = users.indexOfFirst { it.id == user.id }
                actionListener.onUserDelete(user)
                notifyItemRemoved(indexToDelete)
                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show()
            }
        }
    }
}



