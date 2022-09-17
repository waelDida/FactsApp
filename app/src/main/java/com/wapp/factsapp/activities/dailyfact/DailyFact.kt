package com.wapp.factsapp.activities.dailyfact


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.wapp.factsapp.R
import com.wapp.factsapp.SplashActivity
import com.wapp.factsapp.databinding.ActivityDailyFactBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyFact : AppCompatActivity() {
    val viewModel: DailyFactViewModel by viewModels()
    private lateinit var binding: ActivityDailyFactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_fact)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_daily_fact)

        supportActionBar?.hide()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val likeImage = binding.like
        val bookMarkImage = binding.bookmark

        MobileAds.initialize(this) {}

        displayBannerAds()

        viewModel.dailyFact.observe(this,{
            it?.let {
                binding.dailyShortDescription.text = it.shortDescription
                Glide.with(this)
                        .load(it.imageUrl.toUri().buildUpon().scheme("https").build())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(binding.factImage)
            }
        })

        viewModel.gotIt.observe(this,{
           finish()
        })

        viewModel.details.observe(this,{
            startActivity(Intent(this@DailyFact,SplashActivity::class.java))
            finish()
        })

        viewModel.share.observe(this,{
            shareTheFact(it.shortDescription)
        })

    }

    private fun displayBannerAds(){
        val mAdView = binding.adView
        if(viewModel.consentStatus == 1){
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
        }
        else{
            val extras = Bundle()
            extras.putString("npa", "1")
            val adRequest = AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                    .build()
            mAdView.loadAd(adRequest)
        }
    }

    private fun shareTheFact(fact:String){
        val intent = Intent()
        with(intent){
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, fact)
            startActivity(Intent.createChooser(this, "Share this fact"))
        }
    }
}