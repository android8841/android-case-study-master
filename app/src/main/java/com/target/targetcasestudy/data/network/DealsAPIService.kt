package com.target.targetcasestudy.data.network

import com.target.targetcasestudy.data.models.Deals
import io.reactivex.Observable
import retrofit2.http.GET

interface DealsAPIService {
    @GET("mobile_case_study_deals/v1/deals")
    fun getDeals(): Observable<Deals>
}