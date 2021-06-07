package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.viewmodels.DealsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deal_list.*

@AndroidEntryPoint
class DealListFragment : Fragment() {

    private val viewModel: DealsListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setupObservers()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.syncDeals()
        viewModel.getDeals()
        retry_button.setOnClickListener {
            /* making the value of error message as null so as to handle
               null condition in viewModel*/
            viewModel.errorMessage.value = null
            viewModel.getDeals()
        }
    }


    /* listening the countries data.
    Also in case of error show an error and retry button*/
    private fun setupObservers() {
        viewModel.deals.observe(viewLifecycleOwner, Observer {
            val adapter = DealItemAdapter(it).apply {
                onItemClick = { deal ->
                    viewModel.setSelectedDealItem(deal)
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.replace(R.id.container, DealItemFragment())
                    transaction?.addToBackStack(null)
                    transaction?.commit()
                }
            }
            recyclerView.adapter = adapter
            error_handler.visibility = View.GONE
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
            error_handler.visibility = View.GONE
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it != null)
                error_handler.visibility = View.VISIBLE
        })
    }
}
