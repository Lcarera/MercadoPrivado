package com.lcarera.mercadoprivado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        println(intent.getStringExtra("id"))
    }
}