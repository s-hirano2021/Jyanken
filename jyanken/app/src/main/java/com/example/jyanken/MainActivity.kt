package com.example.jyanken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
//import androidx.databinding.DataBindingUtil.setContentView
import com.example.jyanken.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding:ActivityMainBinding

    lateinit var timer: Timer
    lateinit var handler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val ButtonPaa: Button=findViewById<Button>(R.id.ButtonPaa)

        binding.ButtonPaa.setOnClickListener(this)
        binding.ButtonGuu.setOnClickListener(this)
        binding.ButtonTyoki.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        var f=0
        val imageView:ImageView=findViewById(R.id.imageView)

        handler = Handler()
        timer = Timer()
        timer.schedule(2000,300,{runOnUiThread {
            f++

            if (f==1) {
                imageView.setImageResource(R.drawable.janken_gu)
            }else if(f==2){
                imageView.setImageResource(R.drawable.janken_choki)
            }else{
                imageView.setImageResource(R.drawable.janken_pa)
            }

            if (f==3){
                f=0
            }
        }})
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    override fun onClick(v: View?) {
        //findviewを追記
        val  imageViewResult:ImageView=findViewById(R.id.imageViewRessult)
        val  imageView:ImageView=findViewById(R.id.imageView)
        val  textViewresult:TextView=findViewById(R.id.textViewresult)
        val  ButtonGuu:ImageView=findViewById(R.id.ButtonGuu)
        val  ButtonPaa:ImageView=findViewById(R.id.ButtonPaa)
        val  ButtonTyoki:ImageView=findViewById(R.id.ButtonTyoki)

        ButtonGuu.isEnabled=false
        ButtonTyoki.isEnabled=false
        ButtonPaa.isEnabled=false

        val n=Random().nextInt(3)
        if (n==0) {
            imageViewResult.setImageResource(R.drawable.janken_gu)
        }else if(n==1){
            imageViewResult.setImageResource(R.drawable.janken_choki)
        }else {
            imageViewResult.setImageResource(R.drawable.janken_pa)
        }
        textViewresult.text=getString(R.string.ponn)

        when(v?.id){

            R.id.ButtonGuu
                    ->ButtonGuu.setImageResource(R.drawable.janken_gu2)


            R.id.ButtonTyoki
            ->ButtonTyoki.setImageResource(R.drawable.janken_choki2)

            R.id.ButtonPaa
            ->ButtonPaa.setImageResource(R.drawable.janken_pa2)
        }

        imageViewResult.visibility=View.VISIBLE
        imageView.visibility=View.INVISIBLE
        handler.postDelayed(Runnable {
            when(v?.id){
                R.id.ButtonGuu
                        ->{
                            ButtonGuu.setImageResource(R.drawable.janken_gu)
                    if(n==0){
                        textViewresult.text=getString(R.string.result_Drow)
                    }else if (n==1){
                        textViewresult.text=getString(R.string.result_Win)
                    }else{
                        textViewresult.text=getString(R.string.result_Loos)
                    }
                        }

                R.id.ButtonTyoki
                        ->{
                            ButtonTyoki.setImageResource(R.drawable.janken_choki)
                    if (n==0) {
                        textViewresult.text = getString(R.string.result_Loos)
                    }else if (n==1){
                        textViewresult.text=getString(R.string.result_Drow)
                    }else{
                        textViewresult.text=getString(R.string.result_Win)
                    }
                }

                R.id.ButtonPaa
                ->{
                    ButtonPaa.setImageResource(R.drawable.janken_pa)
                    if (n==0) {
                        textViewresult.text = getString(R.string.result_Win)
                    }else if (n==1){
                        textViewresult.text=getString(R.string.result_Loos)
                    }else{
                        textViewresult.text=getString(R.string.result_Drow)
                    }
                }

            }
        },1000)

        handler.postDelayed(Runnable {
            ButtonGuu.isEnabled=true
            ButtonTyoki.isEnabled=true
            ButtonPaa.isEnabled=true

            textViewresult.text=getString(R.string.jannken)
            imageViewResult.visibility=View.VISIBLE
            imageView.visibility=View.VISIBLE
        },2000)
    }
}