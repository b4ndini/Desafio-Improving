package com.example.githubissuesapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubissuesapp.model.IssuesItem
import com.example.githubissuesapp.model.User
import com.example.githubissuesapp.repository.IssuesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class IssueDetailViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun beforeTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(Function { Schedulers.trampoline() })
    }

    private val repository = mockk<IssuesRepository>()
    private val issueDetailLiveDataObserver = mockk<Observer<IssuesItem>>(relaxed = true)
    private val errorMsgLiveDataObserver = mockk<Observer<String>>(relaxed = true)
    private val userA = User("www.teste.com/teste.jpg")
    private val issueItem = IssuesItem("Test", "2021/05/02", "www.teste.com", 1234, "open", "Fix some kotlin bugs", userA)


    private fun createViewModel(): IssueDetailViewModel{
        val viewModel =  IssueDetailViewModel(repository)
        viewModel.issueDetailLiveData.observeForever(issueDetailLiveDataObserver)
        viewModel.errorMsgLiveData.observeForever(errorMsgLiveDataObserver)
        return viewModel
    }


    @Test
    fun `fazendo nova chamada de API após clicar num item da lista retornando mais informaçoes do item clicado`(){
        val vm = createViewModel()
        val clicked = 2


        every { repository.getIssueDetail(clicked) } returns Observable.just(issueItem)

        vm.getIssueDetail(clicked)

        verify { repository.getIssueDetail(clicked)}
        verify { issueDetailLiveDataObserver.onChanged(issueItem) }

    }

    @Test
    fun `fazendo chamada de API falhar retornando mensagem de erro`(){
        val vm = createViewModel()
        val clicked = 2


        every { repository.getIssueDetail(clicked) } returns Observable.error(Throwable("Error"))

        vm.getIssueDetail(clicked)

        verify { repository.getIssueDetail(clicked)}
        verify { errorMsgLiveDataObserver.onChanged("Something went wrong") }

    }


}