package com.example.githubissuesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubissuesapp.model.IssuesItem
import com.example.githubissuesapp.repository.IssuesRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class IssueDetailViewModel(private val repository: IssuesRepository) : ViewModel(){

    /*private val repository by lazy{
        IssuesRepository()
    }*/

    private lateinit var disposable: Disposable
    var issueDetailLiveData: MutableLiveData<IssuesItem> = MutableLiveData()
    var errorMsgLiveData: MutableLiveData<String> = MutableLiveData()

    fun getIssueDetail(number: Int){
        repository.getIssueDetail(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<IssuesItem> {
                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(data: IssuesItem) {
                        issueDetailLiveData.postValue(data)
                    }

                    override fun onError(e: Throwable) {
                        errorMsgLiveData.postValue("Something went wrong")
                    }

                    override fun onComplete() {
                        //
                    }

                })
    }

    override fun onCleared() {
        super.onCleared()
        if(!disposable.isDisposed){
            disposable.dispose()
        }
    }

}