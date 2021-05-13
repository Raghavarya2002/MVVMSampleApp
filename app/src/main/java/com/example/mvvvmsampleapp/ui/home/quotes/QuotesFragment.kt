package com.example.mvvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvvmsampleapp.R
import com.example.mvvvmsampleapp.data.db.entities.Quote
import com.example.mvvvmsampleapp.util.Coroutines
import com.example.mvvvmsampleapp.util.hide
import com.example.mvvvmsampleapp.util.show
import com.example.mvvvmsampleapp.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: QuoteViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
        bindUI()


    }

    private fun bindUI() = Coroutines.main {
        progressbar.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progressbar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {

        val madapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = madapter
        }

    }

    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map {
            QuoteItem(it)
        }
    }

}