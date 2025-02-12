package com.dev.baseproject.ui.component.setting

import android.content.res.Configuration
import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.dev.baseproject.App
import com.dev.baseproject.R
import com.dev.baseproject.databinding.FragmentSettingLanguageBinding
import com.dev.baseproject.ui.base.BaseViewModelFragmentBinding
import com.dev.baseproject.ui.component.splash.dialog.ConfirmApplyLanguageBottomSheet
import com.dev.baseproject.ui.component.splash.view.AskLanguageFragment2
import com.dev.baseproject.ui.component.splash.viewmodel.SettingViewModel
import com.dev.baseproject.utils.AppConfig.logEventTracking
import com.dev.baseproject.utils.Constants
import com.dev.baseproject.utils.Logger
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SettingLanguageFragment : BaseViewModelFragmentBinding<FragmentSettingLanguageBinding, SettingViewModel>() {

    private var isChangeLanguage = true
    private var confirmApplyLanguageBottomSheet : ConfirmApplyLanguageBottomSheet? = null
    private var langCode = App.instance.localStorage.langCode

    override fun getContentViewId() = R.layout.fragment_setting_language

    override fun onResume() {
        super.onResume()

        if (localStorage.isFirstOpenSetting) {
            logEventTracking(Constants.BUNDLE_ANALYTICS_GO_TO_SETTING_LANGUAGE_FIRST)
            localStorage.isFirstOpenSetting = false
        } else {
            logEventTracking(Constants.BUNDLE_ANALYTICS_GO_TO_SETTING_LANGUAGE_AGAIN)
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun initializeViews() {
        updateLanguageLocale()
        resetViewItem()
        bindViewItemSelected()
    }

    override fun registerListeners() {
        dataBinding.itemEnglish.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.ENGLISH.value)
        }
        dataBinding.itemJapan.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.JAPAN.value)
        }
        dataBinding.itemKorean.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.KOREAN.value)
        }
        dataBinding.itemHindi.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.HINDI.value)
        }
        dataBinding.itemChina.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.CHINA.value)
        }
        dataBinding.itemVietNam.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.VIETNAM.value)
        }
        dataBinding.itemSpanish.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.SPANISH.value)
        }
        dataBinding.itemPortuguase.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.PORTUGUESE.value)
        }
        dataBinding.itemRussian.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.RUSSIAN.value)
        }
        dataBinding.itemGerman.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.GERMAN.value)
        }
        dataBinding.itemUkraian.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.UKRAIAN.value)
        }
        dataBinding.itemAbric.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.ABRIC.value)
        }
        dataBinding.itemTurkey.setOnClickListener {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet!!.isVisible) confirmApplyLanguageBottomSheet?.dismiss()
            updateLanguage(AskLanguageFragment2.LangCode.TURKEY.value)
        }
        dataBinding.ivBack.setOnClickListener {
            findNavControllerSafety()?.popBackStack()
        }
    }

    override fun onBackPressed(): Boolean {
        return super.onBackPressed()
    }

    override fun initializeData() {
    }

    private fun updateLanguage(languageCode: String) {
        confirmApplyLanguageBottomSheet = ConfirmApplyLanguageBottomSheet()
        confirmApplyLanguageBottomSheet?.clickConfirmYes = {
            if (languageCode.isNotBlank()) {
                localStorage.langCode = languageCode
                updateLanguageLocale()

                try {
                    val id =  findNavControllerSafety()?.currentDestination?.id
                    findNavControllerSafety()?.popBackStack(id!!,true)
                    findNavControllerSafety()?.navigate(id!!)
                } catch (e : Exception) {
                    Logger.e(e.message)
                    Firebase.crashlytics.recordException(e)
                }
            }
        }
        confirmApplyLanguageBottomSheet?.clickConfirmNo = {
            isChangeLanguage = false
        }
        if (activity?.isFinishing == false) {
            activity?.supportFragmentManager?.let {
                confirmApplyLanguageBottomSheet?.show(it,
                    ConfirmApplyLanguageBottomSheet.TAG)
            }
        }
    }

    protected fun updateLanguageLocale() {
        if (localStorage.langCode.isNotBlank()) {
            val resources: Resources? = context?.resources
            val locale = Locale(localStorage.langCode)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources?.updateConfiguration(config, resources?.displayMetrics)
        }
    }

    private fun resetViewItem() {
        dataBinding.itemEnglish.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemJapan.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemKorean.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemHindi.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemChina.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemVietNam.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemPortuguase.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemSpanish.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemGerman.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemRussian.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemUkraian.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemAbric.setBackgroundResource(R.drawable.bg_item_language)
        dataBinding.itemTurkey.setBackgroundResource(R.drawable.bg_item_language)

        context?.let {
            dataBinding.itemEnglish.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemJapan.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemKorean.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemHindi.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemChina.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemVietNam.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemPortuguase.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemSpanish.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemRussian.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemGerman.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemUkraian.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemAbric.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
            dataBinding.itemTurkey.setTextColor(
                ContextCompat.getColor(
                    it,
                    R.color.black
                )
            )
        }
    }

    private fun bindViewItemSelected() {
        if (langCode.isEmpty()) return
        when (langCode) {
            AskLanguageFragment2.LangCode.NONE.value -> Unit

            AskLanguageFragment2.LangCode.ENGLISH.value -> {
                dataBinding.itemEnglish.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemEnglish.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.JAPAN.value -> {
                dataBinding.itemJapan.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemJapan.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.KOREAN.value -> {
                dataBinding.itemKorean.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemKorean.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.HINDI.value -> {
                dataBinding.itemHindi.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemHindi.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.CHINA.value -> {
                dataBinding.itemChina.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemChina.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.VIETNAM.value -> {
                dataBinding.itemVietNam.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemVietNam.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.PORTUGUESE.value -> {
                dataBinding.itemPortuguase.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemPortuguase.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.SPANISH.value -> {
                dataBinding.itemSpanish.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemSpanish.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.GERMAN.value -> {
                dataBinding.itemGerman.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemGerman.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.RUSSIAN.value -> {
                dataBinding.itemRussian.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemRussian.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.UKRAIAN.value -> {
                dataBinding.itemUkraian.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemUkraian.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.ABRIC.value -> {
                dataBinding.itemAbric.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemAbric.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }

            AskLanguageFragment2.LangCode.TURKEY.value -> {
                dataBinding.itemTurkey.setBackgroundResource(R.drawable.bg_selected_request_age)
                context?.let {
                    dataBinding.itemTurkey.setTextColor(
                        ContextCompat.getColor(
                            it,
                            R.color.black
                        )
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (confirmApplyLanguageBottomSheet != null && confirmApplyLanguageBottomSheet?.isVisible == true) {
                confirmApplyLanguageBottomSheet?.dismiss()
            }
        } catch (e: Exception) {
            Logger.e(e.message)
            Firebase.crashlytics.recordException(e)
        }
    }
    companion object {

    }
}