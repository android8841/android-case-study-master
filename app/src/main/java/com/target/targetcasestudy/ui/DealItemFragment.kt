package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.target.targetcasestudy.R
import com.target.targetcasestudy.ui.viewmodels.DealsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deal_item.*

@AndroidEntryPoint
class DealItemFragment : Fragment() {

    private val viewModel: DealsListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val selectedDeal = viewModel.getSelectedDealData()
        Glide.with(this)
            .load(selectedDeal.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(selected_item_image)
        selected_item_price.text = selectedDeal.price?.displayValue
        selected_item_title.text = selectedDeal.title
        val text = getString(R.string.deals_description, selectedDeal.description)
       selected_item_desc.loadDataWithBaseURL("", text, "text/html", "UTF-8", "");
    }
}
