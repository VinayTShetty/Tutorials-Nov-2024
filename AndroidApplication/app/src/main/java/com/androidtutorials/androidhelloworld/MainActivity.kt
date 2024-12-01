package com.androidtutorials.androidhelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.androidtutorials.androidhelloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflate the layout using view Binding
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textMessage.text="No Name"
        binding.btnClickme.setOnClickListener {
            binding.textMessage.text="Hello Vinay"
            Toast.makeText(this,binding.textMessage.text,Toast.LENGTH_LONG).show()
        }
    }
}