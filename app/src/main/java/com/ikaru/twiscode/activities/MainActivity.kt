package com.ikaru.twiscode.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ikaru.twiscode.DataRepository
import com.ikaru.twiscode.R
import com.ikaru.twiscode.adapters.MealAdapter
import com.ikaru.twiscode.models.Meal
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.custom.sliderimage.logic.SliderImage
import com.ikaru.twiscode.models.Meals

class MainActivity : AppCompatActivity() {

    var meals : ArrayList<Meal> = ArrayList()
    val postServices = DataRepository.create()
    val mealAdapter =  MealAdapter(R.layout.item_rv,meals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        rv_meals.adapter = mealAdapter
        mealAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Meal",meals.get(position).idMeal)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
            true
        }


    }


    fun getData(){
        postServices.getCar().enqueue(object : Callback<Meals> {

            override fun onFailure(call: Call<Meals>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Tidak Ada Koneksi Internet",Toast.LENGTH_SHORT).show()
                t.message?.let { Log.e("ASW", it) }
            }

            override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                meals = response.body()!!.meals

                rv_meals.layoutManager =  GridLayoutManager(this@MainActivity,2)
                Log.e("ASW", meals.get(0)!!.idMeal)
                var images : MutableList<String> = ArrayList()

                mealAdapter.refill(meals)

                for (meal in meals){
                    images.add(meal.strMealThumb)
                }


                // add images URLs


                // Add image URLs to sliderImage
                slider.setItems(images)
                slider.addTimerToSlide(2000)
            }

        })
    }
}
