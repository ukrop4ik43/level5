package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.model.User


interface UserActionListener{
    fun onUserDelete(user: User)
}


class UsersAdapter(private val actionListener: UserActionListener)
    : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(), View.OnClickListener{



    var users: List<User> = emptyList()
        set(newValue) {
            field = newValue
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        binding.trashBin.setOnClickListener(this)
        return UsersViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding){
            holder.itemView.tag=user
            trashBin.tag=user
            nameSurname.text =user.name
            occupy.text=user.occupy
        if(user.photo.isNotBlank()){
            user.loadPhoto(user,icon)
        }
            else{
            icon.setImageResource(R.drawable.me)
        }
        }
    }

   private fun User.loadPhoto(user: User,imageView: ImageView)= Glide.with(imageView.context)
        .load(user.photo)
        .circleCrop()
        .placeholder(R.drawable.me)
        .error(R.drawable.me)
        .into(imageView)

    override fun getItemCount(): Int = users.size


    class UsersViewHolder(
        val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val user=v.tag as User
        val imageButton=ImageButton(v.context)
        imageButton.setOnClickListener{
        }
        val context=v.context
        when(v.id){
            R.id.trashBin ->{
                val indexToDelete = users.indexOfFirst { it.id == user.id }
                actionListener.onUserDelete(user)
                notifyItemRemoved(indexToDelete)
                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
            }
        }
    }
}