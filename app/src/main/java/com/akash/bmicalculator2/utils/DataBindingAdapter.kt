package com.akash.bmicalculator2.utils

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akash.bmicalculator2.R
import com.akash.bmicalculator2.bmis.BmisListAdapter
import com.akash.bmicalculator2.models.BMI

@BindingAdapter("app:getShortEvaluation")
fun getShortEvaluation(textView: TextView, bmi: Float) {
    when {
        bmi <= BmiRanges.UNDERWEIGHT.value ->
            textView.text = BMIState.Underweight.name
        bmi <= BmiRanges.HEALTHY.value ->
            textView.text = BMIState.Healthy.name
        bmi <= BmiRanges.OVERWEIGHT.value ->
            textView.text = BMIState.Overweight.name
        else ->
            textView.text = BMIState.Obese.name
    }
}

@BindingAdapter("app:setBmiStateColor", "app:context")
fun setBmiStateColor(textView: TextView, bmi: Float, context: Context) {
    when {
        bmi <= BmiRanges.UNDERWEIGHT.value ->
            textView.setTextColor(ContextCompat.getColor(context, R.color.orange))
        bmi <= BmiRanges.HEALTHY.value ->
            textView.setTextColor(ContextCompat.getColor(context, R.color.green))
        bmi <= BmiRanges.OVERWEIGHT.value ->
            textView.setTextColor(ContextCompat.getColor(context, R.color.orange))
        else ->
            textView.setTextColor(ContextCompat.getColor(context, R.color.red))
    }

    @BindingAdapter("app:items")
    fun setRecyclerViewItems(recylerView: RecyclerView, items: List<BMI>?) {
        items?.let {
            (recylerView.adapter as BmisListAdapter).submitList(items)
        }
    }

}