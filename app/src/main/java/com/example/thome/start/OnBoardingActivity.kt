package com.example.thome.start

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.thome.R
import com.example.thome.start.adapter.OnboardingItemsAdapter
import com.example.thome.start.home.HomeActivity
import com.example.thome.start.login.LoginActivity
import com.google.android.material.button.MaterialButton
import java.text.FieldPosition

//hàm viewbinding
@SuppressLint("StaticFieldLeak")
private lateinit var binding:OnBoardingActivity
class OnBoardingActivity : AppCompatActivity() {
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Thread.sleep(10000)
        installSplashScreen()*/
        setContentView(R.layout.on_boarding_activity)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }
    private fun setOnboardingItems(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.raw.animation_clearn,
                    title ="THome1",
                    description = "Thao tác nhanh"
                ),
                OnboardingItem(
                    onboardingImage = R.raw.animation_service,
                    title ="THome2",
                    description = "Tiện lợi"
                ),
                OnboardingItem(
                    onboardingImage = R.raw.raw_bin,
                    title ="THome3",
                    description = "Thao tác nhanh"
                )
            )
        )
//set viewPager2
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0)as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imv_next).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount){
                onboardingViewPager.currentItem += 1
            }else{
                navigateToLoginActivity()
            }
            findViewById<TextView>(R.id.textSkip).setOnClickListener {
                navigateToLoginActivity()
            }
        }
        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            navigateToLoginActivity()
        }

    }
//Chuyển sang màn hình Login
    private fun navigateToLoginActivity(){
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }

    private  fun setupIndicators(){
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let{
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i== position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}