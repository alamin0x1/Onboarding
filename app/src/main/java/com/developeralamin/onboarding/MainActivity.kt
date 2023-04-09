package com.developeralamin.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingAdapter: OnboardingAdapter
    private lateinit var indicatorContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems(){
        onboardingAdapter = OnboardingAdapter(
            listOf(
                OnboardingModel(
                    image = R.drawable.task,
                    title = "Manage your Task",
                    description = "Organize all your to do's and projests, Color "
                ),
                OnboardingModel(
                    image = R.drawable.time,
                    title = "Manage your Time",
                    description = "Organize all your to do's and projests, Color "
                ),
                OnboardingModel(
                    image = R.drawable.remeber,
                    title = "Manage your Rember",
                    description = "Organize all your to do's and projests, Color "
                )
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imageNext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingAdapter.itemCount){
                onboardingViewPager.currentItem +=1
            }else{
                navigateToHomeAction()
            }
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToHomeAction()
        }

        findViewById<Button>(R.id.buttonGetStarted).setOnClickListener {
            navigateToHomeAction()
        }
    }

    private fun navigateToHomeAction(){
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    private fun setupIndicators(){
        indicatorContainer = findViewById(R.id.indicatorContainer)
        val indicators = arrayOfNulls<ImageView>(onboardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_backgound
                    )
                )

                it.layoutParams = layoutParams
                indicatorContainer.addView(it)
            }
        }
    }
    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_backgound
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_backgound
                    )
                )
            }
        }
    }
}