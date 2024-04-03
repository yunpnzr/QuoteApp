package com.yunpnzr.quoteapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yunpnzr.quoteapp.R
import com.yunpnzr.quoteapp.data.model.Quote
import com.yunpnzr.quoteapp.databinding.ItemQuoteBinding

class QuotesAdapter: RecyclerView.Adapter<QuotesAdapter.QuoteListViewHolder>() {

    private val data = mutableListOf<Quote>()

    fun submitData(items: List<Quote>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    class QuoteListViewHolder(
        private val binding: ItemQuoteBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Quote){
            val formattingQuote = binding.tvQuote.resources.getString(R.string.quote_format)
            binding.tvQuote.text = String.format(formattingQuote, item.quote)
            binding.tvCharacter.text = item.character
            binding.tvAnime.text = item.anime
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): QuoteListViewHolder {
        val binding = ItemQuoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return QuoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesAdapter.QuoteListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /*private val dataDiffer =
    AsyncListDiffer(
        this,
        object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(
                oldItem: Quote,
                newItem: Quote
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Quote,
                newItem: Quote
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    )*/
}