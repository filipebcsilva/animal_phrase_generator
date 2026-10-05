package com.example.animal_phrase_generator

import MyPreferences
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.animal_phrase_generator.databinding.ActivityMainBinding
import com.example.animal_phrase_generator.databinding.ActivityPhraseBinding

fun colorCat(binding: ActivityPhraseBinding,context: Context){
    binding.imageCat.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
    binding.imageDog.setColorFilter(ContextCompat.getColor(context, R.color.black))
    binding.imageBird.setColorFilter(ContextCompat.getColor(context, R.color.black))
}

fun colorDog(binding: ActivityPhraseBinding,context: Context){
    binding.imageCat.setColorFilter(ContextCompat.getColor(context, R.color.black))
    binding.imageDog.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
    binding.imageBird.setColorFilter(ContextCompat.getColor(context, R.color.black))
}

fun colorBird(binding: ActivityPhraseBinding,context: Context){
    binding.imageCat.setColorFilter(ContextCompat.getColor(context, R.color.black))
    binding.imageDog.setColorFilter(ContextCompat.getColor(context, R.color.black))
    binding.imageBird.setColorFilter(ContextCompat.getColor(context, R.color.yellow))
}


class PhraseActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {

    lateinit var binding: ActivityPhraseBinding

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


        val name = MyPreferences(applicationContext).getString("NAME")
        binding.textSecondHello.text = " $name"

        val animal = MyPreferences(applicationContext).getString("ANIMAL")
        if (!animal.isEmpty()){
            when(animal){
                "cat" -> colorCat(binding,applicationContext)
                "dog" -> colorDog(binding,applicationContext)
                "bird" -> colorBird(binding,applicationContext)
            }

        }

        binding.buttonGenerate.setOnClickListener(this)
        binding.buttonEditName.setOnClickListener(this)
        binding.imageCat.setOnClickListener (this)
        binding.imageDog.setOnClickListener (this)
        binding.imageBird.setOnClickListener (this)
        binding.textPhrase.setOnLongClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.buttonGenerate.id){
            val p = MyPreferences(applicationContext)
            val animal = p.getString("ANIMAL")
            if (animal.isEmpty())
            {
                Toast.makeText(applicationContext, R.string.error_without_selection_msg, Toast.LENGTH_SHORT).show()
                return
            }
            val phrase = when(animal){
                "cat" -> CatPhrase.getRandomPhrase()
                "dog" -> DogPhrase.getRandomPhrase()
                "bird" -> BirdPhrase.getRandomPhrase()
                else -> "Sem frase"
            }
            binding.textPhrase.text = phrase
        }
        if (v?.id == binding.imageCat.id){
            val p = MyPreferences(applicationContext)
            colorCat(binding,applicationContext)
            p.setString("ANIMAL","cat")
        }
        if (v?.id == binding.imageDog.id){
            val p = MyPreferences(applicationContext)
            colorDog(binding,applicationContext)
            p.setString("ANIMAL","dog")
        }
        if (v?.id == binding.imageBird.id){
            val p = MyPreferences(applicationContext)
            colorBird(binding,applicationContext)
            p.setString("ANIMAL","bird")
        }
        if (v?.id == binding.buttonEditName.id){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EDIT", true)
            startActivity(intent)
            finish()
        }
    }

    override fun onLongClick(v: View?): Boolean{
        if (v?.id == binding.textPhrase.id){
            val string = binding.textPhrase.text.toString()
            binding.textPhrase.text = ""
            val animal = MyPreferences(applicationContext).getString("ANIMAL")
            if (!animal.isEmpty()){
                when(animal){
                    "cat" -> CatPhrase.deletePhrase(string)
                    "dog" -> DogPhrase.deletePhrase(string)
                    "bird" -> BirdPhrase.deletePhrase(string)
                }

            }
            Toast.makeText(applicationContext, R.string.success_delete_phrase, Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }
}