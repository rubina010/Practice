package com.example.letspractice.ui.stackoverflow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letspractice.entity.Items
import com.example.letspractice.R
import com.example.letspractice.databinding.RecyclerItemViewBinding

val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.answer_id == newItem.answer_id
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.equals(newItem)

    }
}

class ItemAdapter(val context: Context) : PagedListAdapter<Items, ItemAdapter.ItemViewHolder>(DIFFUTIL_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val dataBinding: RecyclerItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recycler_item_view,
                parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = getItem(position)
        holder.viewHolderBinding.stackoverflow = model
        Glide.with(context)
                .load(model!!.owner.profile_image)
                .into(holder.viewHolderBinding.itemImageView)
        holder.viewHolderBinding.itemNameTextView.text = model.owner.display_name
    }

    class ItemViewHolder(binding: RecyclerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        /* var imageView: ImageView = view.findViewById(R.id.item_image_view)
         var textView = view.findViewById<TextView>(R.id.item_name_text_view)*/
        val viewHolderBinding: RecyclerItemViewBinding = binding

    }
}