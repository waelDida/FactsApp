package com.wapp.factsapp.flow

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.wapp.factsapp.R
import com.wapp.factsapp.signin.SignIn
import com.wapp.factsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appLink: String

    //Ads
    private lateinit var interstitialAd: InterstitialAd


    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Get the app link
        appLink = "https://play.google.com/store/apps/details?id=+$packageName"

        //Check if there are new facts
        val x = intent.extras?.getInt(UPDATE_FLAG) ?: 0
        if(x == UPDATE_FLAG_VALUE)
            showNewFactsDialog()

        // Init Interstitial Ads
        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = getString(R.string.interstitial_ads)
        interstitialAd.adListener = object : AdListener(){
            override fun onAdClosed() {
                loadInterstitialAds()
            }
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home, R.id.favoriteFragment, R.id.categoriesFragment, R.id.bookmarksFragment, R.id.profileFragment,
                    R.id.startFragment
                ), drawerLayout
        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.factDetail  -> {
                    toolbar.visibility = View.GONE
                    updateStatusBarColor("#60aaf5")
                }
                R.id.quizFragment -> {
                    toolbar.visibility = View.GONE
                    updateStatusBarColor("#60aaf5")
                }
                R.id.quizCompleted -> {
                    updateStatusBarColor("#42b5a4")
                }
                R.id.gameOver -> {
                    updateStatusBarColor("#e94f4f")
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                    updateStatusBarColor("#60aaf5")
                }
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)

        viewModel.currentUser.observe(this, {
            it?.let {
                initProfileUi(it.name, it.imgUrl)
            }
        })
    }

    private fun updateStatusBarColor(color: String){
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(color)
    }

    private fun showNewFactsDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.new_facts_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val gotItButton = dialog.findViewById<Button>(R.id.new_fact_got_it)
        gotItButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        dialog.show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareTheApp()
            R.id.rate -> rateTheApp()
            R.id.privacy -> showPrivacy()
            R.id.logout -> {
                if(viewModel.currentUser.value!!.name != ANONYMOUS)
                    logout()
                else
                    Toast.makeText(this,"You Login as Anonymous",Toast.LENGTH_SHORT).show()
            }
            else -> navigateToDestination(item.itemId)
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initProfileUi(userName: String, imgUrl: String){
        val headerLayout = navView.getHeaderView(0)
        // Set the user's photo
        val profileImg = headerLayout.findViewById<CircleImageView>(R.id.user_image)
        Glide.with(profileImg.context)
            .load(imgUrl.toUri().buildUpon().scheme("https").build())
            .apply(RequestOptions.placeholderOf(ColorDrawable(ContextCompat.getColor(profileImg.context, R.color.loading_color))))
            .error(R.drawable.ic_user_photo)
            .into(profileImg)

        headerLayout.findViewById<TextView>(R.id.user_name).apply {
            text = userName
        }
    }

    private fun shareTheApp(){
        val intent = Intent()
        with(intent){
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, appLink)
            startActivity(Intent.createChooser(this, "Share this app"))
        }
    }

    private fun rateTheApp(){
        val intent = Intent()
        with(intent){
            action = Intent.ACTION_VIEW
            data = Uri.parse(appLink)
            startActivity(this)
        }
    }

    private fun showPrivacy() {
        val intent = Intent()
        with(intent) {
            action = Intent.ACTION_VIEW
            data = Uri.parse(PRIVACY_LINK)
            startActivity(this)
        }
    }

    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this@MainActivity, SignIn::class.java))
        finish()
    }

    private fun isValidDestination(destinationId: Int) = destinationId != navController.currentDestination!!.id

    private fun navigateToDestination(id: Int){
        if(isValidDestination(id)){
            val navOption = NavOptions.Builder().setPopUpTo(R.id.mobile_navigation, true).build()
            navController.navigate(id, null, navOption)
        }
    }

    //Ads functions

    fun loadInterstitialAds(){
        if(viewModel.consentStatus == 1){
            interstitialAd.loadAd(AdRequest.Builder().build())
        }

        else{
            val extras = Bundle()
            extras.putString("npa", "1")
            val request = AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                    .build()
            interstitialAd.loadAd(request)
        }
    }

    fun isInterstitialAdsLoaded() = interstitialAd.isLoaded

    fun showInterstitialAds(){
        interstitialAd.show()
    }
}