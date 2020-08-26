package com.ikaru.twiscode.adapters

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ikaru.twiscode.R
import com.ikaru.twiscode.models.Meal
import com.squareup.picasso.Picasso
import android.util.Log
import com.chad.library.adapter.base.BaseViewHolder

class MealAdapter (layoutResId: Int, data: List<Meal>? = null ) :  BaseQuickAdapter<Meal, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: Meal) {
        helper.setText(R.id.tv_item, item.strMeal)
        val myImageView: ImageView = helper.getView(R.id.iv_item)
        Picasso
            .get()
            .load(item.strMealThumb) // load the image
            .into(myImageView) // select the ImageView to load it into
    }

    fun refill(items : List<Meal>? = null){
        this.mData = items
        notifyDataSetChanged()
        Log.e("ASW","TERPANGGIL")
    }

}