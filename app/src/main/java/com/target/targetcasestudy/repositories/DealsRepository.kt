package com.target.targetcasestudy.repositories

import com.target.targetcasestudy.data.db.DatabaseHelper
import com.target.targetcasestudy.data.models.DealItem
import com.target.targetcasestudy.data.models.Deals
import com.target.targetcasestudy.data.network.DealsAPIService
import io.reactivex.Observable
import javax.inject.Inject


open class DealsRepository @Inject constructor(
    private val dealsAPIService: DealsAPIService,
    private val databaseHelper: DatabaseHelper
) {
    fun syncDeals(): Observable<Boolean> {
        return dealsAPIService.getDeals().switchMap { deals: Deals ->
            databaseHelper.deleteDeals()
            Observable.just(deals)
        }.switchMap {
            databaseHelper.insertDeals(it.products)
        }
    }

    open fun getDeals(): Observable<List<DealItem>> {
        return databaseHelper.getDeals()
    }
}
