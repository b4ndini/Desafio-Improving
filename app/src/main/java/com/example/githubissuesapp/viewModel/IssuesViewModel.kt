package com.example.githubissuesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubissuesapp.model.Issues
import com.example.githubissuesapp.repository.IssuesRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class IssuesViewModel(private val repository: IssuesRepository): ViewModel() {


    private lateinit var disposable: Disposable
    var issuesLiveData: MutableLiveData<Issues> = MutableLiveData()
    var errorMsgLiveData: MutableLiveData<String> = MutableLiveData()

    fun getIssues(){
        repository.getIssues()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Issues> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(data: Issues) {
                  issuesLiveData.postValue(data)
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