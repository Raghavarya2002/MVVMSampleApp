package com.example.mvvvmsampleapp.ui.home.quotes

import com.example.mvvvmsampleapp.R
import com.example.mvvvmsampleapp.data.db.entities.Quote
import com.example.mvvvmsampleapp.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {

        viewBinding.setQuote(quote)

    }

}