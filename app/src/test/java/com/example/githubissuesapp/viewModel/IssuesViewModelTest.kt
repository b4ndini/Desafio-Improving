package com.example.githubissuesapp.viewModel

import com.example.githubissuesapp.model.Issues
import com.example.githubissuesapp.model.IssuesItem
import com.example.githubissuesapp.model.User
import com.example.githubissuesapp.repository.IssuesRepository
import io.mockk.mockk
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule

class IssuesViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun beforeTest() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{Schedulers.trampoline()}
    }




    private val repository = mockk<IssuesRepository>()
    private val issueLiveDataObserver = mockk<Observer<Issues>>(relaxed = true)
    private val errorMsgLiveDataObserver = mockk<Observer<String>>(relaxed = true)
    private val userA = User("www.teste.com/teste.jpg")

    private val mockedIssue = Issues()
    private val itemOne = IssuesItem("Test", "2021/05/02", "www.teste.com", 1234, "open", "Fix some kotlin bugs", userA)
    private val itemTwo = IssuesItem("Test 2", "2021/05/01", "www.teste.com", 1234, "open", "Fix some kotlin bugs", userA)
    private val itemThree = IssuesItem("Test 3", "2021/05/03", "www.teste.com", 1234, "open", "Fix some kotlin bugs", userA)
    private val issuesListOf = listOf(itemOne,itemTwo,itemThree)

    private fun createViewModel(): IssuesViewModel{
       val viewModel =  IssuesViewModel(repository)
        viewModel.issuesLiveData.observeForever(issueLiveDataObserver)
        viewModel.errorMsgLiveData.observeForever(errorMsgLiveDataObserver)
        return viewModel

    }

    @Test
    fun `fazendo uma chamada de API com sucesso retornando lista com issues`(){
        val vm = createViewModel()
        mockedIssue.addAll(issuesListOf)

        every { repository.getIssues() } returns Observable.just(mockedIssue)

        vm.getIssues()

        verify { repository.getIssues()}
        verify { issueLiveDataObserver.onChanged(mockedIssue) }

    }

    @Test
    fun `fazendo chamada de API falhar retornando mensagem de erro`(){

        val vm = createViewModel()
        every { repository.getIssues() } returns Observable.error(Throwable("Error"))

        vm.getIssues()

        verify { repository.getIssues() }
        verify { errorMsgLiveDataObserver.onChanged("Something went wrong")}
    }

}