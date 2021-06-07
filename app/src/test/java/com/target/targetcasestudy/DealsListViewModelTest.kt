package com.target.targetcasestudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.target.targetcasestudy.data.models.DealItem
import com.target.targetcasestudy.repositories.DealsRepository
import com.target.targetcasestudy.ui.viewmodels.DealsListViewModel
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class DealsListViewModelTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun initialize() {
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Mock
    private lateinit var observer: Observer<List<DealItem>>

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var dealsListViewModel: DealsListViewModel

    @Mock
    private lateinit var dealsRepository: DealsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        dealsListViewModel = DealsListViewModel(dealsRepository)
    }

    private val dealItem = DealItem(1, "title", "desc", null, "b1", "url")

    @Test
    fun `if loader is stopped when data is received`() {
        val list = listOf(dealItem)
        doReturn(Observable.just(list)).`when`(dealsRepository).getDeals()
        dealsListViewModel.deals.observeForever(observer)
        dealsListViewModel.getDeals()
        Assert.assertEquals(dealsListViewModel.isLoading.value, false)
    }

    @Test
    fun `if correct data is sent by live data`() {
        val list = listOf(dealItem)
        doReturn(Observable.just(list)).`when`(dealsRepository).getDeals()
        dealsListViewModel.deals.observeForever(observer)
        dealsListViewModel.getDeals()
        Assert.assertEquals(dealsListViewModel.deals.value, list)
    }

    @Test
    fun `if error data is sent by live data`() {
        doReturn(Observable.just(Exception())).`when`(dealsRepository).getDeals()
        dealsListViewModel.deals.observeForever(observer)
        dealsListViewModel.getDeals()
        Assert.assertNotNull(dealsListViewModel.errorMessage.value)
        Assert.assertNull(dealsListViewModel.deals.value)
    }
}
