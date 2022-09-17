package com.wapp.factsapp.flow.ui.profile

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.wapp.factsapp.R
import com.wapp.factsapp.activities.deleteaccount.DeleteAccount
import com.wapp.factsapp.databinding.FragmentProfileBinding
import com.wapp.factsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class ProfileFragment : Fragment(),EasyPermissions.PermissionCallbacks {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding : FragmentProfileBinding
    private lateinit var rewardedAd: RewardedAd
    private var shortAnimationDuration: Int = 0
    private lateinit var rewardVideoAdText: TextView
    private lateinit var freeCoinsLayout: FrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        rewardVideoAdText = binding.rewardsVideo
        freeCoinsLayout = binding.freeCoinsLayout

        createAndLoadRewardedAd()

        binding.uploadImage.setOnClickListener {
            viewModel.onUploadImageClick()
        }

        binding.notificationSwitch.setOnCheckedChangeListener{_,isChecked ->
            viewModel.saveNotificationStatus(isChecked)
        }

        binding.notificationSwitch.isChecked = viewModel.notificationStatus

        viewModel.uploadImage.observe(viewLifecycleOwner,{
            if(it){
                onUploadPhotoClick()
                viewModel.onUploadImageClicked()
            }
        })
        viewModel.imageSaved.observe(viewLifecycleOwner,{
            if(it){
                binding.uploadProgress.visibility = View.INVISIBLE
            }
            else{
                binding.uploadProgress.visibility = View.INVISIBLE
                displayMessage(binding.profileLayout,getString(R.string.something_wrong_message))
            }
        })

        viewModel.pickCategories.observe(viewLifecycleOwner,{
            if(it){
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToPickUpCategories())
                viewModel.onPickCategoriesClicked()
            }
        })

        viewModel.gdprClick.observe(viewLifecycleOwner,{
            if(it){
                showGdprDialog()
                viewModel.onGdprClicked()
            }
        })

        viewModel.rewardAdsClick.observe(viewLifecycleOwner,{
            if(it){
                viewModel.onRewardAdsClicked()
                if(rewardedAd.isLoaded){
                    val adCallback = object: RewardedAdCallback(){
                        override fun onUserEarnedReward(p0: RewardItem) {
                            viewModel.attributeTheGiftCoins()
                        }
                        override fun onRewardedAdClosed() {
                            rewardedAd = createAndLoadRewardedAd()
                        }
                    }
                    rewardedAd.show(requireActivity(), adCallback)
                }
                else{
                    displayMessage(binding.profileLayout,getString(R.string.ads_not_ready))
                }
            }
        })

        viewModel.deleteAccount.observe(viewLifecycleOwner,{
            if(it){
                if(viewModel.currentUser.value!!.name != ANONYMOUS)
                    showDeleteDialog()
                else{
                    if(isNetworkAvailable(requireContext()))
                        startActivity(Intent(requireActivity(),DeleteAccount::class.java))
                    else
                        displayMessage(binding.profileLayout,getString(R.string.cant_connect))
                }
            }
        })

        viewModel.reAuthenticate.observe(viewLifecycleOwner,{
            if(it){
               startActivity(Intent(requireActivity(),DeleteAccount::class.java))
            }
            else
                displayMessage(binding.profileLayout,getString(R.string.something_wrong_message))
        })

        viewModel.deleteWithEmail.observe(viewLifecycleOwner,{
            if(it)
                showDeletePasswordDialog()
        })

        viewModel.currentUser.observe(viewLifecycleOwner,{
            it?.let {
                binding.coins.text = it.coins.toString()
            }
        })

        return binding.root
    }

    private fun crossFadeRewardView(){
        if(!rewardVideoAdText.isVisible){
            freeCoinsLayout.apply {
                alpha = 0f
                visibility = View.VISIBLE
                animate()
                        .alpha(1f)
                        .setDuration(shortAnimationDuration.toLong())
                        .setListener(null)
            }
        }
    }

    private fun createAndLoadRewardedAd():RewardedAd{
        rewardedAd = RewardedAd(requireActivity(),
                getString(R.string.rewarded_ads))
        val adLoadCallback = object: RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {
                crossFadeRewardView()
            }
        }
        if(viewModel.consentStatus == 1)
            rewardedAd.loadAd(AdRequest.Builder().build(),adLoadCallback)
        else{
            val extras = Bundle()
            extras.putString("npa", "1")
            val request = AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                    .build()
            rewardedAd.loadAd(request,adLoadCallback)
        }
        return rewardedAd
    }

    private fun showGdprDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.gdpr_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val yesBtn = dialog.findViewById<Button>(R.id.yes_btn)
        val noBtn = dialog.findViewById<Button>(R.id.no_btn)
        yesBtn.setOnClickListener {
            viewModel.setConsentStatus(1)
            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            viewModel.setConsentStatus(2)
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun showDeleteDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.delete_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val yesBtn = dialog.findViewById<Button>(R.id.yes_delete_dialog)
        val noBtn = dialog.findViewById<Button>(R.id.no_delete_dialog)
        yesBtn.setOnClickListener {
            viewModel.reAuthenticateUser()
            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun showDeletePasswordDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.enter_password_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val confirm = dialog.findViewById<Button>(R.id.btn_confirm)
        val cancel = dialog.findViewById<Button>(R.id.btn_cancel)
        val passwordEdit = dialog.findViewById<EditText>(R.id.edit_password)
        confirm.setOnClickListener {
            if(isNetworkAvailable(requireActivity())){
                if(passwordEdit.text.isNotEmpty()){
                    viewModel.reAuthenticationEmailUser(passwordEdit.text.toString())
                    dialog.dismiss()
                }
            }
            else{
                dialog.dismiss()
                displayMessage(binding.profileLayout,getString(R.string.cant_connect))
            }


        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun onUploadPhotoClick(){
        if(Build.VERSION.SDK_INT >= 23)
            requestStoragePermission()
        else
            startUploadActivity()

    }

    private fun requestStoragePermission(){
        if(EasyPermissions.hasPermissions(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)){
            startUploadActivity()
        }else{
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.gallery_permission_text),
                MY_PERMISSION_REQUEST_GALLERY,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    private fun startUploadActivity(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RC_CHOOSE_PHOTO)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startUploadActivity()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        requestStoragePermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == RC_CHOOSE_PHOTO && resultCode == Activity.RESULT_OK){
            if(data == null || data.data == null){
                return
            }
            binding.uploadProgress.visibility = View.VISIBLE
            viewModel.uploadImageToFirebase(data.data)
        }
    }
}