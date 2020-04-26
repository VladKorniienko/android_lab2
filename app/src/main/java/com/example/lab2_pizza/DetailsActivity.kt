package com.example.lab2_pizza

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2_pizza.Models.Pizza
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val i = intent
        val pizza: Pizza = i.getSerializableExtra("pizza") as Pizza

        sizeText1.text = "Size price: " + pizza.sizePrice.toString()
        ingredientsText1.text = "Ingredients price: " + (pizza.totalPrice - pizza.sizePrice).toString()
        priceText.text = pizza.totalPrice.toString()

        val btnStartMainActivity: Button = findViewById(R.id.newOrder)
        btnStartMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
