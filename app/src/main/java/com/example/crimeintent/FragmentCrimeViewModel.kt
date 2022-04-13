package com.example.crimeintent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class FragmentCrimeViewModel : ObservableViewModel() {
    // TODO: Implement the ViewModel
    var time : MutableLiveData<String> = MutableLiveData()
    fun date(){
        time.postValue(Date().toString())
        notifyChange()
    }
}