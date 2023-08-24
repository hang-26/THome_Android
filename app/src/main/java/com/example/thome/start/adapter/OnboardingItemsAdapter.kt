package com.example.thome.start.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.thome.R
import com.example.thome.start.OnboardingItem

class OnboardingItemsAdapter(private val onboardingItems:List<OnboardingItem>):
    RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {
    inner class OnboardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private  val imageOnboarding = view.findViewById<LottieAnimationView>(R.id.imageOnboarding)
        private  val textTile = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        //Hàm gán biến vào trong giao diện
        fun bind(onboardingItem: OnboardingItem){
            imageOnboarding.setAnimation(onboardingItem.onboardingImage)
            textTile.text = onboardingItem.title
            textDescription.text= onboardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onboardingItems[position])
    }
    override fun getItemCount(): Int {
        return onboardingItems.size
    }
}