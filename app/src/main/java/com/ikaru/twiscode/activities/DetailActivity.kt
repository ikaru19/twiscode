package com.ikaru.twiscode.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ikaru.twiscode.DataRepository
import com.ikaru.twiscode.R
import com.ikaru.twiscode.models.Meal
import com.ikaru.twiscode.models.Meals
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {

    lateinit var idMeal : String
    val postServices = DataRepository.create()
    var meals : ArrayList<Meal> = ArrayList()
    lateinit var meal : Meal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        idMeal = intent.extras?.get("Meal") as String

        getData()

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Detail Activity"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)



    }


    fun getData(){
        postServices.getReviewByID(idMeal).enqueue(object : Callback<Meals> {
            override fun onFailure(call: Call<Meals>, t: Throwable) {
                Toast.makeText(this@DetailActivity,"Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show()
                t.message?.let { Log.e("ASW", it) }
            }

            override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
               meals = response.body()!!.meals
                initData()

            }

        })
    }

    fun initData(){
        meal = meals.get(0)
        tv_judul.text = meal.strMeal
        tv_category.text = meal.strCategory
        tv_instruction.text = meal.strInstructions
        Picasso.get()
            .load(meal.strMealThumb) // load the image
            .into(iv_detail) // select the ImageView to load it into

        btn_sumber.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(meal.strSource))
            startActivity(browserIntent)
        }

        btn_youtube.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube))
            startActivity(browserIntent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
