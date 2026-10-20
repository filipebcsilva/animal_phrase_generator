package com.example.animal_phrase_generator.viewModel

import com.example.animal_phrase_generator.model.MyPreferences
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var preferences = MyPreferences(application.applicationContext)

    private val _goPhraseActivity = MutableLiveData<Boolean>()
    val goPhraseActivity: LiveData<Boolean> = _goPhraseActivity

    private  val _nameError = MutableLiveData<Boolean>()
    val nameError: LiveData<Boolean> = _nameError

    fun validateName(name: String) {
        if (name.isEmpty() || name.length <= 3){
            _nameError.value = true
            return
        }

        preferences.setString("NAME",name)
        _goPhraseActivity.value = true
    }

    fun existName(){
        val nowName = preferences.getString("NAME")

        if (!nowName.isEmpty()){
            _goPhraseActivity.value = true
        }
    }

    fun setNameErrorFalse(){
        _nameError.value = false
    }


}