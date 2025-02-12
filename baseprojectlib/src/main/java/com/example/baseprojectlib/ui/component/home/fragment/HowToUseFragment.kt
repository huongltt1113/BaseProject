package com.example.baseprojectlib.ui.component.home.fragment

import androidx.core.content.ContextCompat
import io.github.huongltt1113.R
import io.github.huongltt1113.databinding.FragmentHowToUseBinding
import com.example.baseprojectlib.ui.base.BaseFragmentBinding
import com.example.baseprojectlib.ui.component.home.adapter.SectionsPagerAdapter
import com.example.baseprojectlib.utils.Constants
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HowToUseFragment : BaseFragmentBinding<FragmentHowToUseBinding>() {
    private var mode: Int = Constants.MODE_CLAP
    override fun getContentViewId() = R.layout.fragment_how_to_use
    override fun initializeViews() {
        arguments?.let {
            mode = it.getInt("mode", Constants.MODE_CLAP)
        }
        val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), parentFragmentManager)
        dataBinding.viewPager.adapter = sectionsPagerAdapter

        dataBinding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_tab_item)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.background = ContextCompat.getDrawable(requireContext(), R.color.transparent)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        dataBinding.tabs.setupWithViewPager(dataBinding.viewPager)

        if(mode == Constants.MODE_CLAP) {
            dataBinding.tabs.getTabAt(0)?.select()
        } else if(mode == Constants.MODE_VOICE) {
            dataBinding.tabs.getTabAt(1)?.select()
        }
//        else if(mode == Constants.MODE_POCKET) {
//            dataBinding.tabs.getTabAt(3)?.select()
//        } else {
//            dataBinding.tabs.getTabAt(2)?.select()
//        }

        dataBinding.back.setOnClickListener {
           findNavControllerSafety()?.popBackStack()
        }
    }

    override fun registerListeners() {

    }

    override fun initializeData() {

    }

}
