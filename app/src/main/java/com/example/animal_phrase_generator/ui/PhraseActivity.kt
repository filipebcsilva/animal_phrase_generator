package com.example.animal_phrase_generator.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.animal_phrase_generator.R
import com.example.animal_phrase_generator.databinding.ActivityPhraseBinding
import com.example.animal_phrase_generator.viewModel.PhraseViewModel


class PhraseActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityPhraseBinding

    private lateinit var phraseVM: PhraseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhraseBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.phrase_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonGenerate.setOnClickListener(this)
        binding.buttonEditName.setOnClickListener(this)
        binding.imageCat.setOnClickListener (this)
        binding.imageDog.setOnClickListener (this)
        binding.imageBird.setOnClickListener (this)

        phraseVM = ViewModelProvider(this).get(PhraseViewModel::class.java)
        setObservers()

        phraseVM.setCurrentName()
        phraseVM.setCurrentAnimal()
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.buttonGenerate.id){
            phraseVM.changePhrase()
        }
        if (v?.id == binding.imageCat.id){
            phraseVM.selectAnimal("cat")
        }
        if (v?.id == binding.imageDog.id){
            phraseVM.selectAnimal("dog")
        }
        if (v?.id == binding.imageBird.id){
            phraseVM.selectAnimal("bird")
        }
        if (v?.id == binding.buttonEditName.id){
            phraseVM.changeToMainActivity()
        }
    }


    fun setObservers(){
        phraseVM.currentName.observe(this){
            binding.textSecondHello.text = " $it"
        }
        phraseVM.currentAnimal.observe(this){
            if (!it.isEmpty()){
                when(it){
                    "cat" -> colorCat(binding,applicationContext)
                    "dog" -> colorDog(binding,applicationContext)
                    "bird" -> colorBird(binding,applicationContext)
                }

            }
        }
        phraseVM.goMainActivity.observe(this){
            if (it == true){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        phraseVM.animalError.observe(this){
            if (it == true){
                Toast.makeText(applicationContext, R.string.error_without_selection_msg, Toast.LENGTH_SHORT).show()
                phraseVM.setErrorAnimalFalse()
            }
        }
        phraseVM.currentPhrase.observe(this){
            binding.textPhrase.text = it
        }
        phraseVM.error.observe(this){
            binding.textPhrase.text = it
        }
    }

    private fun colorCat(binding: ActivityPhraseBinding,context: Context){
        binding.imageCat.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
        binding.imageDog.setColorFilter(ContextCompat.getColor(context, R.color.black))
        binding.imageBird.setColorFilter(ContextCompat.getColor(context, R.color.black))
    }

    private fun colorDog(binding: ActivityPhraseBinding,context: Context){
        binding.imageCat.setColorFilter(ContextCompat.getColor(context, R.color.black))
        binding.imageDog.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
        binding.imageBird.setColorFilter(ContextCompat.getColor(context, R.color.black))
    }

    private fun colorBird(binding: ActivityPhraseBinding,context: Context){
        binding.imageCat.setColorFilter(ContextCompat.getColor(context, R.color.black))
        binding.imageDog.setColorFilter(ContextCompat.getColor(context, R.color.black))
        binding.imageBird.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
    }
}