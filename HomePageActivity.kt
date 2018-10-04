package com.example.lkcuo.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

         Button menuText = (Button) findVievById(R.id.menuText);
         Button reserText = (Button) findVievById(R.id.reserText);
         Button infoText = (Button) findVievById(R.id.infoText);
         EditText logoutText = (EditText) findVievById(R.id.logoutText);

    }
}
