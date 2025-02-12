package com.dev.baseproject.ui.component.home.fragment

import android.speech.tts.TextToSpeech
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.dev.baseproject.R
import com.dev.baseproject.databinding.FragmentTextToVoiceBinding
import com.dev.baseproject.ui.base.BaseFragmentBinding
import com.dev.baseproject.utils.AppConfig
import com.dev.baseproject.utils.Constants.TEXT_TO_VOICE_LISTEN_AGAIN
import com.dev.baseproject.utils.Constants.TEXT_TO_VOICE_LISTEN_FIRST
import com.dev.baseproject.utils.Constants.TEXT_TO_VOICE_SAVE_PASSCODE_AGAIN
import com.dev.baseproject.utils.Constants.TEXT_TO_VOICE_SAVE_PASSCODE_FIRST
import com.dev.baseproject.utils.Constants.TEXT_TO_VOICE_SELECT_LANGUAGE_AGAIN
import com.dev.baseproject.utils.Constants.TEXT_TO_VOICE_SELECT_LANGUAGE_FIRST
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class TextToVoiceFragment : BaseFragmentBinding<FragmentTextToVoiceBinding>() {
    private var myLocale: Locale? = null
    private var textToSpeech: TextToSpeech? = null
    override fun getContentViewId() = R.layout.fragment_text_to_voice

    override fun initializeViews() {
        when (localStorage.voiceLocal) {
            "en" -> {
                dataBinding.tvselectedlang.text = getString(R.string.english)
                myLocale = Locale.US
            }
            "es" -> {
                dataBinding.tvselectedlang.text = getString(R.string.spanish)
                myLocale = Locale("es", "ES")
            }
            "hi" -> {
                dataBinding.tvselectedlang.text = getString(R.string.hindi)
                myLocale = Locale("hi", "IN")
            }
            "ja" -> {
                dataBinding.tvselectedlang.text = getString(R.string.japanese)
                myLocale = Locale.JAPANESE
            }
            "ko" -> {
                dataBinding.tvselectedlang.text = getString(R.string.korean)
                myLocale = Locale.KOREAN
            }
            "vi" -> {
                dataBinding.tvselectedlang.text = getString(R.string.vietnamese)
                myLocale = Locale("vi", "VN")
            }
            "zh" -> {
                dataBinding.tvselectedlang.text = getString(R.string.chinese)
                myLocale = Locale.CHINESE
            }
            "ar" -> {
                dataBinding.tvselectedlang.text = getString(R.string.arabic)
                myLocale = Locale("ar", "SA")
            }
            "tr" -> {
                dataBinding.tvselectedlang.text = getString(R.string.turkish)
                myLocale = Locale("tr", "TR")
            }
            "pt" -> {
                dataBinding.tvselectedlang.text = getString(R.string.portuguese)
                myLocale = Locale("pt", "PT")
            }
            "uk" -> {
                dataBinding.tvselectedlang.text = getString(R.string.ukrainian)
                myLocale = Locale("uk", "UA")
            }
            "de" -> {
                dataBinding.tvselectedlang.text = getString(R.string.german)
                myLocale = Locale.GERMAN
            }
            "ru" -> {
                dataBinding.tvselectedlang.text = getString(R.string.russian)
                myLocale = Locale("ru", "RU")
            }
            else -> {

            }
        }
        textToSpeech = TextToSpeech(requireContext()) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech?.language = myLocale
            }
        }
        dataBinding.etpasscode.setText(localStorage.voicePasscode)
        dataBinding.btnsavepasscode.setOnClickListener {
            if(localStorage.isFirstSavePasscodeTexttoVoiceClick){
                AppConfig.logEventTracking(TEXT_TO_VOICE_SAVE_PASSCODE_FIRST)
                localStorage.isFirstSavePasscodeTexttoVoiceClick = false
            }else{
                AppConfig.logEventTracking(TEXT_TO_VOICE_SAVE_PASSCODE_AGAIN)
            }
            if (dataBinding.etpasscode.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.please_record_something), Toast.LENGTH_SHORT).show()
            }else{
                localStorage.voicePasscode =  dataBinding.etpasscode.text.toString()
            }
            findNavControllerSafety()?.popBackStack()
        }

        dataBinding.btnlistenpasscode.setOnClickListener {
            if(localStorage.isFirstListenTexttoVoiceClick){
                AppConfig.logEventTracking(TEXT_TO_VOICE_LISTEN_FIRST)
                localStorage.isFirstListenTexttoVoiceClick = false
            }else{
                AppConfig.logEventTracking(TEXT_TO_VOICE_LISTEN_AGAIN)
            }
            if (dataBinding.etpasscode.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.please_record_something), Toast.LENGTH_SHORT).show()
            }else{
                textToSpeech?.speak(dataBinding.etpasscode.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
            }

        }

        dataBinding.selectlanguage.setOnClickListener {
            if(localStorage.isFirstSelectLanguageTexttoVoiceClick){
                AppConfig.logEventTracking(TEXT_TO_VOICE_SELECT_LANGUAGE_FIRST)
                localStorage.isFirstSelectLanguageTexttoVoiceClick = false
            }else{
                AppConfig.logEventTracking(TEXT_TO_VOICE_SELECT_LANGUAGE_AGAIN)
            }
            showLanguageSelectionDialog()
        }

        dataBinding.btnBack.setOnClickListener {
            findNavControllerSafety()?.navigateUp()
        }

        dataBinding.btnCancel.setOnClickListener {
            findNavControllerSafety()?.navigateUp()
        }
    }
    private fun showLanguageSelectionDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomDialogTheme)
        val dialogView = layoutInflater.inflate(R.layout.dialog_language_selection, null)
        builder.setView(dialogView)
        val radioButtonEnglish = dialogView.findViewById<RadioButton>(R.id.radioButtonEnglishv)
        val radioButtonSpanish = dialogView.findViewById<RadioButton>(R.id.radioButtonSpanishv)
        val radioButtonHindi = dialogView.findViewById<RadioButton>(R.id.radioButtonHindiv)
        val radioButtonJapanese = dialogView.findViewById<RadioButton>(R.id.radioButtonJapanesev)
        val radioButtonKorean = dialogView.findViewById<RadioButton>(R.id.radioButtonKoreanv)
        val radioButtonVietnamese = dialogView.findViewById<RadioButton>(R.id.radioButtonVietnamesev)
        val radioButtonChinese = dialogView.findViewById<RadioButton>(R.id.radioButtonChinesev)
        val radioButtonArabic = dialogView.findViewById<RadioButton>(R.id.radioButtonArabicv)
        val radioButtonTurkish = dialogView.findViewById<RadioButton>(R.id.radioButtonTurkishv)
        val radioButtonPortuguese = dialogView.findViewById<RadioButton>(R.id.radioButtonPortuguesev)
        val radioButtonUkrainian = dialogView.findViewById<RadioButton>(R.id.radioButtonUkrainianv)
        val radioButtonGerman = dialogView.findViewById<RadioButton>(R.id.radioButtonGermanv)
        val radioButtonRussian = dialogView.findViewById<RadioButton>(R.id.radioButtonRussianv)

        when (localStorage.voiceLocal) {
            "en" -> radioButtonEnglish.isChecked = true
            "es" -> radioButtonSpanish.isChecked = true
            "hi" -> radioButtonHindi.isChecked = true
            "ja" -> radioButtonJapanese.isChecked = true
            "ko" -> radioButtonKorean.isChecked = true
            "vi" -> radioButtonVietnamese.isChecked = true
            "zh" -> radioButtonChinese.isChecked = true
            "ar" -> radioButtonArabic.isChecked = true
            "tr" -> radioButtonTurkish.isChecked = true
            "pt" -> radioButtonPortuguese.isChecked = true
            "uk" -> radioButtonUkrainian.isChecked = true
            "de" -> radioButtonGerman.isChecked = true
            "ru" -> radioButtonRussian.isChecked = true
        }

        val alertDialog = builder.create()

        dialogView.findViewById<TextView>(R.id.buttonOk).setOnClickListener {
            when {
                radioButtonEnglish.isChecked -> updateLanguage("en", R.string.english)
                radioButtonSpanish.isChecked -> updateLanguage("es", R.string.spanish)
                radioButtonHindi.isChecked -> updateLanguage("hi", R.string.hindi)
                radioButtonJapanese.isChecked -> updateLanguage("ja", R.string.japanese)
                radioButtonKorean.isChecked -> updateLanguage("ko", R.string.korean)
                radioButtonVietnamese.isChecked -> updateLanguage("vi", R.string.vietnamese)
                radioButtonChinese.isChecked -> updateLanguage("zh", R.string.chinese)
                radioButtonArabic.isChecked -> updateLanguage("ar", R.string.arabic)
                radioButtonTurkish.isChecked -> updateLanguage("tr", R.string.turkish)
                radioButtonPortuguese.isChecked -> updateLanguage("pt", R.string.portuguese)
                radioButtonUkrainian.isChecked -> updateLanguage("uk", R.string.ukrainian)
                radioButtonGerman.isChecked -> updateLanguage("de", R.string.german)
                radioButtonRussian.isChecked -> updateLanguage("ru", R.string.russian)
            }
            alertDialog.dismiss()
        }

        dialogView.findViewById<TextView>(R.id.buttonCancel).setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
    private fun updateLanguage(locale: String, languageResId: Int) {
        localStorage.voiceLocal = locale
        dataBinding.tvselectedlang.text = getString(languageResId)
    }
    override fun registerListeners() {

    }

    override fun initializeData() {

    }

}
