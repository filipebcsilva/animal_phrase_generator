package com.example.animal_phrase_generator

import MyPreferences
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.animal_phrase_generator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        val p = MyPreferences(applicationContext)
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

        val isEditing = intent.getBooleanExtra("EDIT", false)

        if (!isEditing && p.getString("NAME").isNotEmpty()){
            startActivity(Intent(this, PhraseActivity::class.java))
            finish()
            return
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == binding.buttonSave.id)
        {
            val name = binding.editName.text.toString()
            if (name.isEmpty() || name.length <= 3){
                Toast.makeText(applicationContext, R.string.error_msg, Toast.LENGTH_SHORT).show()
                return
            }
            val p = MyPreferences(applicationContext)
            p.setString("NAME",name)
            startActivity(Intent(this, PhraseActivity::class.java))
            finish()
        }
    }
}