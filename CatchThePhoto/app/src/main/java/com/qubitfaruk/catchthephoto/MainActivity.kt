package com.qubitfaruk.catchthephoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.os.Looper.getMainLooper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var score=0
    var imageArrays=ArrayList<ImageView>()
    var handler=Handler(Looper.getMainLooper())
    var runnable=Runnable{}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageArrays.add(imageView)
        imageArrays.add(imageView2)
        imageArrays.add(imageView3)
        imageArrays.add(imageView4)
        imageArrays.add(imageView5)
        imageArrays.add(imageView6)
        imageArrays.add(imageView7)
        imageArrays.add(imageView8)
        imageArrays.add(imageView9)
        imageArrays.add(imageView10)
        imageArrays.add(imageView11)
        imageArrays.add(imageView12)
        hideImage()




        object: CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text="Time : "+millisUntilFinished/1000
            }

            override fun onFinish() {
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Oyun bitti")
                alert.setMessage("Tekrar oynamak ister misiniz")
                alert.setPositiveButton("Evet"){dialog,wich->
                    val intent=intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("Hayır "){dialog,wich->
                    Toast.makeText(this@MainActivity,"Oyun bitti ",Toast.LENGTH_LONG).show()
                    handler.removeCallbacks(runnable)
                    for (image in imageArrays){
                        image.visibility=View.INVISIBLE
                    }
                }
                alert.show()
            }
        }.start()
    }
    fun increaseScore(view:View){
        score=score+1
        scoreText.text="Skor : $score"
    }
    fun hideImage(){
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArrays){
                    image.visibility=View.INVISIBLE
                }
                val random = java.util.Random()
                var randomInteger=random.nextInt(12)
                imageArrays[randomInteger].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
}