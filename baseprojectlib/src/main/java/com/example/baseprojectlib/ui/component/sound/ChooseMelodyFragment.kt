package com.example.baseprojectlib.ui.component.sound

import io.github.huongltt1113.R
import com.example.baseprojectlib.data.entity.SoundItem
import io.github.huongltt1113.databinding.FragmentChooseMelodyBinding
import com.example.baseprojectlib.ui.base.BaseFragmentBinding
import com.example.baseprojectlib.utils.AppConfig
import com.example.baseprojectlib.utils.Constants.GO_TO_SOUND_DETAIL_AGAIN
import com.example.baseprojectlib.utils.Constants.GO_TO_SOUND_DETAIL_FIRST
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseMelodyFragment(
    val onSoundClick: (SoundItem) -> Unit = {}
): BaseFragmentBinding<FragmentChooseMelodyBinding>() {
    private lateinit var soundAdapter: ChooseSoundAdapter
    private val listMelody = mutableListOf<SoundItem>()

    override fun getContentViewId() = R.layout.fragment_choose_melody

    override fun initializeViews() {
        if(listMelody.isEmpty()){
            listMelody.apply {
                add(SoundItem(R.drawable.solitude_ringtone, getString(R.string.solitude_ringtone_label)))
                add(SoundItem(R.drawable.ballerina_music, getString(R.string.ballerina_music_label)))
                add(SoundItem(R.drawable.ding_dong_merrily, getString(R.string.ding_dong_merrily_label)))
                add(SoundItem(R.drawable.peaceful_piano_melody, getString(R.string.peaceful_piano_melody_label)))
            }
        }
    }

    override fun registerListeners() {
        setupRecyclerView()
    }

    override fun initializeData() {

    }

    private fun setupRecyclerView(){
        soundAdapter = ChooseSoundAdapter(
            soundList = listMelody,
            onClick = { soundItem ->
                if (localStorage.goToSoundDetail) {
                    AppConfig.logEventTracking(GO_TO_SOUND_DETAIL_FIRST)
                    localStorage.goToSoundDetail = false
                } else {
                    AppConfig.logEventTracking(GO_TO_SOUND_DETAIL_AGAIN)
                }
                onSoundClick(soundItem)
            }
        )

        dataBinding.rvSoundMelody.adapter = soundAdapter
        soundAdapter.notifyDataSetChanged()
    }
}