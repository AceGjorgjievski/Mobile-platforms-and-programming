package com.example.lab1mpip.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShowTextViewModel : ViewModel() {

    private val _userChoice:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getUserChoice() : String {
        return this._userChoice.value.toString()
    }

    fun getUserChoiceValue() : MutableLiveData<String> {
        return this._userChoice;
    }

    fun setUserChoice(userChoice: String) {
        this._userChoice.value = userChoice;
    }
}