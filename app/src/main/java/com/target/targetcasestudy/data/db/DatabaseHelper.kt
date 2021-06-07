package com.target.targetcasestudy.data.db

import com.target.targetcasestudy.data.models.DealItem
import io.reactivex.Observable
import javax.inject.Inject

open class DatabaseHelper @Inject constructor(private val appDatabase: AppDatabase) {

    open fun insertDeals(deals: List<DealItem>): Observable<Boolean> {
        appDatabase.dealsDao().insertAll(deals)
        return Observable.just(true)
    }

    fun getDeals(): Observable<List<DealItem>> {
        return appDatabase.dealsDao().getAllDeals()
    }

    fun deleteDeals() {
        appDatabase.dealsDao().deleteAll()
    }
}