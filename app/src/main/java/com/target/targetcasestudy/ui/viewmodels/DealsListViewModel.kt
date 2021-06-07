package com.target.targetcasestudy.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.models.DealItem
import com.target.targetcasestudy.repositories.DealsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class DealsListViewModel @ViewModelInject constructor(
    private val dealsRepository: DealsRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private lateinit var dealsData: DealItem

    private var isSynced: Boolean = false

    val deals: MutableLiveData<List<DealItem>> by lazy {
        MutableLiveData<List<DealItem>>()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getDeals() {
        if (deals.value == null && errorMessage.value == null) {
            isLoading.postValue(true)
            disposables.add(
                dealsRepository.getDeals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ dealList: List<DealItem> ->
                         deals.postValue(dealList)
                        if (dealList != null || (dealList == null && isSynced)) {
                            isLoading.postValue(false)
                        }
                    },
                        { throwable: Throwable -> errorMessage.setValue(throwable.message) })
            )
        }
    }

    fun setSelectedDealItem(dealItem: DealItem) {
        this.dealsData = dealItem
    }

    fun getSelectedDealData(): DealItem {
        return dealsData
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun syncDeals() {
        if (!isSynced) {
            disposables.add(
                dealsRepository.syncDeals()
                    .onErrorResumeNext(Observable.just(true))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        isSynced = true
                    }
            )
        }
    }
}