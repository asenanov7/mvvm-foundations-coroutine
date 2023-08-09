package com.example.simplemvvm.views.changecolor

import androidx.recyclerview.widget.DiffUtil
import ua.cn.stu.simplemvvm.views.changecolor.NamedColorListItem

class AdapterDiffCallback : DiffUtil.ItemCallback<NamedColorListItem>() {
    override fun areItemsTheSame(oldItem: NamedColorListItem, newItem: NamedColorListItem): Boolean {
        return oldItem.namedColor == newItem.namedColor
    }

    override fun areContentsTheSame(oldItem: NamedColorListItem, newItem: NamedColorListItem): Boolean {
        return oldItem == newItem
    }
}