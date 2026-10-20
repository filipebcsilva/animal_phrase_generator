package com.example.animal_phrase_generator.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animal_phrase_generator.model.MyPreferences
import com.example.animal_phrase_generator.model.api.birds.BirdClient
import com.example.animal_phrase_generator.model.api.birds.BirdPhraseEntity
import com.example.animal_phrase_generator.model.api.cats.CatClient
import com.example.animal_phrase_generator.model.api.cats.CatPhraseEntity
import com.example.animal_phrase_generator.model.api.dogs.DogClient
import com.example.animal_phrase_generator.model.api.dogs.DogPhraseEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class PhraseViewModel(application: Application) : AndroidViewModel(application) {

    private var preferences = MyPreferences(application.applicationContext)

    private val _goMainActivity = MutableLiveData<Boolean>()
    val goMainActivity: LiveData<Boolean> = _goMainActivity

    private val _currentName = MutableLiveData<String>()
    val currentName: LiveData<String> = _currentName

    private val _currentAnimal = MutableLiveData<String>()
    val currentAnimal: LiveData<String> = _currentAnimal

    private val _currentPhrase = MutableLiveData<String>()
    val currentPhrase: LiveData<String> = _currentPhrase

    private val _animalError = MutableLiveData<Boolean>()
    val animalError: LiveData<Boolean> = _animalError

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun setCurrentName(){
        val name = preferences.getString("NAME")
        _currentName.value = name
    }

    fun setCurrentAnimal(){
        val animal = preferences.getString("ANIMAL")
        _currentAnimal.value = animal
    }

    fun changeToMainActivity(){
        preferences.setString("NAME","")
        _goMainActivity.value = true
    }

    fun selectAnimal(animal: String){
        preferences.setString("ANIMAL",animal)
        _currentAnimal.value = animal
    }

    fun setErrorAnimalFalse(){
        _animalError.value = false
    }

    fun changePhrase(){
        val animal = _currentAnimal.value
        if (animal.isNullOrEmpty()) {
            _animalError.value = true
            return
        }
        when(animal){
            "cat" -> loadCatPhrase()
            "dog" -> loadDogPhrase()
            "bird" -> loadBirdPhrase()
        }
    }

    private fun loadCatPhrase(){
        val service = CatClient.createCatPhraseService()

        service.getCatFact().enqueue(object : Callback<CatPhraseEntity> {
            override fun onResponse(call: Call<CatPhraseEntity>, response: Response<CatPhraseEntity>) {
                if (response.isSuccessful) {
                    _currentPhrase.value = response.body()?.fact
                } else {
                    _error.value = "Erro ${response.code()}"
                }
            }

            override fun onFailure(call: Call<CatPhraseEntity>, t: Throwable) {
                _error.value = "Falha de conexão"
            }
        })
    }
    private fun loadBirdPhrase(){
        val service = BirdClient.createBirdPhraseService()

        service.getBirdPhrase().enqueue(object : Callback<BirdPhraseEntity> {
            override fun onResponse(call: Call<BirdPhraseEntity>, response: Response<BirdPhraseEntity>) {
                if (response.isSuccessful) {
                    _currentPhrase.value = response.body()?.facts?.firstOrNull()
                } else {
                    _error.value = "Erro ${response.code()}"
                }
            }

            override fun onFailure(call: Call<BirdPhraseEntity>, t: Throwable) {
                _error.value = "Falha de conexão"
            }
        })
    }

    private fun loadDogPhrase(){
        val service = DogClient.createDogPhraseService()

        service.getDogFact().enqueue(object : Callback<DogPhraseEntity> {
            override fun onResponse(call: Call<DogPhraseEntity>, response: Response<DogPhraseEntity>) {
                if (response.isSuccessful) {
                    _currentPhrase.value = response.body()?.fact?.firstOrNull()
                } else {
                    _error.value = "Erro ${response.code()}"
                }
            }

            override fun onFailure(call: Call<DogPhraseEntity>, t: Throwable) {
                _error.value = "Falha de conexão"
            }
        })
    }
}