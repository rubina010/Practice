package com.example.letspractice.ui.stackoverflow

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letspractice.R
import com.example.letspractice.databinding.RecyclerItemViewBinding
import com.example.letspractice.entity.StackFlowResponse

val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<StackFlowResponse>() {
    override fun areItemsTheSame(oldItem: StackFlowResponse, newItem: StackFlowResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StackFlowResponse, newItem: StackFlowResponse): Boolean {
        return oldItem.equals(newItem)

    }
}

class ItemAdapter(val context: Context) : PagedListAdapter<StackFlowResponse, ItemAdapter.ItemViewHolder>(DIFFUTIL_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val dataBinding: RecyclerItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recycler_item_view,
                parent, false)
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = getItem(position)
        holder.viewHolderBinding.stackoverflow = model
        Glide.with(context)
                .load(model!!.items[position].owner.profile_image)
                .into(holder.viewHolderBinding.itemImageView)
        holder.viewHolderBinding.itemNameTextView.text = model.items[position].owner.display_name
    }

    class ItemViewHolder(binding: RecyclerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        /* var imageView: ImageView = view.findViewById(R.id.item_image_view)
         var textView = view.findViewById<TextView>(R.id.item_name_text_view)*/
        val viewHolderBinding: RecyclerItemViewBinding = binding

    }
}