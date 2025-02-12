package com.example.baseprojectlib.ui.component.sound

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import io.github.huongltt1113.R
import io.github.huongltt1113.databinding.FragmentChooseSoundBinding
import com.example.baseprojectlib.ui.base.BaseFragmentBinding
import com.example.baseprojectlib.ui.component.home.fragment.HomeFragmentDirections
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseSoundFragment : BaseFragmentBinding<FragmentChooseSoundBinding>() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun getContentViewId() = R.layout.fragment_choose_sound

    override fun initializeViews() {
        initTab()
    }

    override fun registerListeners() {

    }

    override fun initializeData() {

    }

    private fun initTab() {
        mViewPager = dataBinding.viewPager
        mTabLayout = dataBinding.tabLayout

        mViewPagerAdapter = ViewPagerAdapter(this)
        mViewPager.adapter = mViewPagerAdapter
        mViewPagerAdapter.setData(
            listOf(
                ChooseSoundEffectFragment(
                    onSoundClick = { soundItem ->
                        val action = ChooseSoundFragmentDirections.actionChooseSoundFragmentToSoundDetailFragment(soundItem.iconResId)
                        findNavController().navigate(action)
                    }
                ),
                ChooseMelodyFragment(
                    onSoundClick = { soundItem ->
                        val action = ChooseSoundFragmentDirections.actionChooseSoundFragmentToSoundDetailFragment(soundItem.iconResId)
                        findNavController().navigate(action)
                    }
                ),
                ChooseFunnyFragment(
                    onSoundClick = { soundItem ->
                        val action = ChooseSoundFragmentDirections.actionChooseSoundFragmentToSoundDetailFragment(soundItem.iconResId)
                        findNavController().navigate(action)
                    }
                )
            )
        )
        mViewPager.offscreenPageLimit = 1
        mViewPager.isUserInputEnabled = false

        TabLayoutMediator(mTabLayout, mViewPager) { tab, position ->
            tab.text =
                if (position == 0) getString(R.string.sound_effects) else if (position == 1) getString(
                    R.string.melody
                ) else getString(R.string.funny)
        }.attach()
        mTabLayout.getTabAt(0)?.view?.apply {
            background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_tab_item)
        }

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_tab_item)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.background =
                    ContextCompat.getDrawable(requireContext(), R.color.transparent)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        dataBinding.back.setOnClickListener {
            findNavControllerSafety()?.popBackStack()
        }
    }
}