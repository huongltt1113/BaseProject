package com.dev.baseproject.ui.component.home.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.OnBackPressedCallback
import com.dev.baseproject.R
import com.dev.baseproject.databinding.FragmentCountDownBinding
import com.dev.baseproject.ui.base.BaseFragmentBinding
import com.dev.baseproject.utils.Constants
import com.dev.baseproject.utils.Constants.COUNT_DOWN_TIME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountDownFragment : BaseFragmentBinding<FragmentCountDownBinding>() {
    private var countDown: CountDownTimer? = null
    private var mode: Int? = null
    private var serviceClassName: String? = null

    override fun getContentViewId() = R.layout.fragment_count_down

    override fun initializeViews() {
        // Nhận dữ liệu từ Bundle
        arguments?.let {
            mode = it.getInt("mode_key")
            serviceClassName = it.getString("service_class")
        }
        // Thiết lập text dựa trên chế độ
        dataBinding.noti.text =
            when (mode) {
                Constants.MODE_POCKET -> getString(R.string.pocket_mode_active)
                Constants.MODE_DONT_TOUCH -> getString(R.string.don_t_touch_mode_active)
                else -> getString(R.string.don_t_touch_mode_active)
            }
        dataBinding.description.text =
            when (mode) {
                Constants.MODE_POCKET -> getString(R.string.pocket_mode)
                Constants.MODE_DONT_TOUCH -> getString(R.string.don_t_touch_mode)
                else -> getString(R.string.don_t_touch_mode)
            }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    stopCountDownWithoutService()
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            },
        )

        startCountDown()
    }

    private fun stopCountDownWithoutService() {
        countDown?.cancel()
        countDown = null
    }

    override fun registerListeners() {
        dataBinding.btnStartNow.setOnClickListener {
            stopCountDownAndStartService()
        }
    }

    private fun startCountDown() {
        countDown =
            object : CountDownTimer(COUNT_DOWN_TIME, 1000) {
                override fun onTick(p0: Long) {
                    val secondsRemaining = p0 / 1000
                    dataBinding.number.text = secondsRemaining.toString()
                }

                override fun onFinish() {
                    navigateBackAndStartService()
                }
            }.start()
    }

    private fun navigateBackAndStartService() {
        // Tạo Bundle chứa thông tin dịch vụ cần chạy
        val resultBundle =
            Bundle().apply {
                putString("service_class", serviceClassName)
            }

        // Gửi kết quả trở lại HomeFragment
        parentFragmentManager.setFragmentResult("countdown_finished", resultBundle)

        // Đóng CountDownFragment
        parentFragmentManager.popBackStack()
    }

    private fun stopCountDownAndStartService() {
        countDown?.cancel()
        countDown = null
        navigateBackAndStartService()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCountDownWithoutService()
    }

    override fun initializeData() {}
}
