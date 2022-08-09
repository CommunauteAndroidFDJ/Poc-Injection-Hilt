package com.fdj.injection.marvel.ui.character.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fdj.injection.databinding.ItemCharacterBinding
import com.fdj.injection.marvel.ui.character.model.CharacterModel

class CharacterListAdapter(
    private val context: Context,
    private val onClickCallback: (CharacterModel) -> Unit
) : ListAdapter<CharacterModel, RecyclerView.ViewHolder>(
    TaskDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createCharacterHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as CharacterViewHolder).apply {
            bindItem(
                item,
                onClickCallback
            )
        }
    }


    private fun createCharacterHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemCharacterBinding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(
            itemCharacterBinding
        )
    }

    class CharacterViewHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {
        fun bindItem(character: CharacterModel, onClickCallback: (CharacterModel) -> Unit) {
            itemCharacterBinding.item = character
            itemCharacterBinding.card.setOnClickListener {
                onClickCallback.invoke(character)
            }
            itemCharacterBinding.executePendingBindings()
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }
}