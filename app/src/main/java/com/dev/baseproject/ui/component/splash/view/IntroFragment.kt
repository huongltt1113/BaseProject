package com.dev.baseproject.ui.component.splash.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dev.baseproject.ads.AdStatus
import com.dev.baseproject.ads.GoogleMobileAdsConsentManager
import com.dev.baseproject.ads.OpenAdManager
import com.dev.baseproject.R
import com.dev.baseproject.ads.NativeAdsUtils
import com.dev.baseproject.databinding.FragmentIntroBinding
import com.dev.baseproject.ui.MainActivity
import com.dev.baseproject.ui.base.BaseViewModelFragmentBinding
import com.dev.baseproject.ui.component.splash.viewmodel.TopicViewModel
import com.dev.baseproject.utils.AppConfig
import com.dev.baseproject.utils.Constants
import com.dev.baseproject.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class IntroFragment : BaseViewModelFragmentBinding<FragmentIntroBinding, TopicViewModel>() {

    @Inject
    lateinit var googleMobileAdsConsentManager: GoogleMobileAdsConsentManager

    private var isIntro2 = false
    private var isIntro3 = false
    override fun getContentViewId() = R.layout.fragment_intro

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).checkShowRequestInternet()
    }

    override fun initializeViews() {
        isIntro2 = arguments?.containsKey(KEY_INTRO2) ?: false
        isIntro3 = arguments?.containsKey(KEY_INTRO3) ?: false

        NativeAdsUtils.viewGroupIntro = dataBinding.nativeIntro.nativeAdSmall
        initAds()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                OpenAdManager.adFLow.collectLatest {
                    if (it.status == AdStatus.OPENED) {
                        NativeAdsUtils.viewGroupIntro.visibility = View.GONE
                    } else {
                        initAds()
                    }
                }
            }
        }

        if (isIntro2) {
            dataBinding.ivIndicator.setImageResource(R.drawable.progress_indicators_2)
            dataBinding.imgIntro.setImageResource(R.drawable.intro_2)
            dataBinding.txtTitle.text = getString(R.string.text_intro2_title)
            dataBinding.txtMsg.text = getString(R.string.text_intro2_msg)

            if (localStorage.isFirstOpenIntro2) {
                AppConfig.logEventTracking(Constants.BUNDLE_ANALYTICS_GO_TO_INTRO_2_FIRST)
                localStorage.isFirstOpenIntro2 = false
            } else {
                AppConfig.logEventTracking(Constants.BUNDLE_ANALYTICS_GO_TO_INTRO_2_AGAIN)
            }
            return
        } else if (isIntro3) {
            dataBinding.ivIndicator.setImageResource(R.drawable.progress_indicators_3)
            dataBinding.imgIntro.setImageResource(R.drawable.intro_3)
            dataBinding.txtTitle.text = getString(R.string.text_intro3_title)
            dataBinding.txtMsg.text = getString(R.string.text_intro3_msg)

            if (localStorage.isFirstOpenIntro3) {
                AppConfig.logEventTracking(Constants.BUNDLE_ANALYTICS_GO_TO_INTRO_3_FIRST)
                localStorage.isFirstOpenIntro3 = false
            } else {
                AppConfig.logEventTracking(Constants.BUNDLE_ANALYTICS_GO_TO_INTRO_3_AGAIN)
            }
            return
        } else {
            dataBinding.ivIndicator.setImageResource(R.drawable.progress_indicators_1)
            dataBinding.imgIntro.setImageResource(R.drawable.intro_1)
            dataBinding.txtTitle.text = getString(R.string.text_intro1_title)
            dataBinding.txtMsg.text = getString(R.string.text_intro1_msg)
        }
    }

    override fun registerListeners() {
        dataBinding.txtNext.setOnClickListener {
            if (isIntro2) {
                val bundle = Bundle()
                bundle.putBoolean(KEY_INTRO3, true)
                try {
                    findNavControllerSafety()?.navigate(R.id.atcOpenNext, bundle)
                } catch (ex : Exception) {

                }
            } else if (isIntro3){
                try {
                    findNavControllerSafety()?.navigate(R.id.action_introFragment3_to_permissionFragment)
                } catch (ex : Exception) {

                }
            } else {
                val bundle = Bundle()
                bundle.putBoolean(KEY_INTRO2, true)
                try {
                    findNavControllerSafety()?.navigate(R.id.actOpenIntro2, bundle)
                } catch (ex : Exception) {

                }
            }
        }
    }

    override fun initializeData() {
    }
    private fun initAds() {
        if (!NetworkUtils.isNetworkConnected() || !googleMobileAdsConsentManager.canRequestAds) {
            NativeAdsUtils.viewGroupIntro.visibility = View.GONE
        } else {
            if (NativeAdsUtils.nativeIntro != null && isIntro3) {
                NativeAdsUtils.showNative(
                    true,
                    activity,
                    NativeAdsUtils.viewGroupIntro,
                    R.layout.native_ad_unified_small,
                    NativeAdsUtils.nativeIntro,
                    null
                )
                NativeAdsUtils.viewGroupIntro.visibility = View.VISIBLE
                AppConfig.logEventTracking(Constants.INTRO_NATIVE_SHOW)
            } else {
                NativeAdsUtils.viewGroupIntro.visibility = View.GONE
                if (isIntro3) {
                    NativeAdsUtils.nativeIntro = NativeAdsUtils.addSmallNativeAd(true, activity, null, false)
                }
                AppConfig.logEventTracking(Constants.INTRO_NATIVE_DIS)
            }
        }
    }

    companion object {
        const val KEY_INTRO1 = "isIntro1"
        const val KEY_INTRO2 = "isIntro2"
        const val KEY_INTRO3 = "isIntro3"
    }
}