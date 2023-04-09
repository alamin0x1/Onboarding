package com.developeralamin.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(private val list: List<OnboardingModel>):RecyclerView.Adapter<OnboardingAdapter.OnboardingItemViewHolder>() {

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val imageOnboarding = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onboardingModel: OnboardingModel){
            imageOnboarding.setImageResource(onboardingModel.image)
            textTitle.text = onboardingModel.title
            textDescription.text = onboardingModel.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container, parent, false))
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}