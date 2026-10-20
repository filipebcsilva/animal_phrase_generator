package com.example.animal_phrase_generator.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.animal_phrase_generator.viewModel.MainViewModel
import com.example.animal_phrase_generator.R
import com.example.animal_phrase_generator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left + binding.main.paddingLeft, systemBars.top , systemBars.right + binding.main.paddingLeft, systemBars.bottom)
            insets
        }

        binding.buttonSave.setOnClickListener(this)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        setObservers()

        mainVM.existName()
    }

    private fun setObservers() {
        mainVM.nameError.observe(this){
            if (it == true){
                Toast.makeText(applicationContext, R.string.error_msg, Toast.LENGTH_SHORT).show()
                mainVM.setNameErrorFalse()
            }
        }
        mainVM.goPhraseActivity.observe(this){
            if (it == true){
                startActivity(Intent(this, PhraseActivity::class.java))
                finish()
            }
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.buttonSave.id)
        {
            val name = binding.editName.text.toString()
            mainVM.validateName(name)
        }
    }
}