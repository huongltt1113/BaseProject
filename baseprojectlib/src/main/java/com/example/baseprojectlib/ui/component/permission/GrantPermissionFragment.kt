package com.example.baseprojectlib.ui.component.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.github.huongltt1113.R
import io.github.huongltt1113.databinding.FragmentGrantPermissionBinding
import com.example.baseprojectlib.ui.base.BaseFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GrantPermissionFragment : BaseFragmentBinding<FragmentGrantPermissionBinding>() {
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result: Boolean ->
            if (result) {
                dataBinding.swNotification.isChecked = true
                dataBinding.swNotification.visibility = View.GONE
                dataBinding.tvNotificationAllowed.visibility = View.VISIBLE
                isRequestAudioPermission = false
            } else {
                // Nếu quyền bị từ chối, hiển thị Toast và yêu cầu lại quyền
                isRequestAudioPermission = false
                Toast.makeText(
                    requireContext(),
                    getString(R.string.need_allow_notification_to_show_notification),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val requestAudioPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result == true) {
                dataBinding.swRecord.isChecked = true
                dataBinding.swRecord.visibility = View.GONE
                dataBinding.tvRecordAllowed.visibility = View.VISIBLE
                isRequestNotificationPermission = false
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.need_to_allow_audio_recording_to_use_this_feature),
                    Toast.LENGTH_SHORT
                ).show()
                isRequestNotificationPermission = false
            }
        }

    private var isRequestAudioPermission: Boolean = false
    private var isRequestNotificationPermission: Boolean = false

    override fun getContentViewId() = R.layout.fragment_grant_permission

    override fun initializeViews() {
        checkPermissionAndBindView()
    }

    private fun checkPermissionAndBindView(){
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                "android.permission.RECORD_AUDIO"
            ) != 0
        ) {
            dataBinding.swRecord.isChecked = false
            dataBinding.swRecord.visibility = View.VISIBLE
            dataBinding.tvRecordAllowed.visibility = View.GONE
            isRequestAudioPermission = true
        } else {
            dataBinding.swRecord.isChecked = true
            dataBinding.swRecord.visibility = View.GONE
            dataBinding.tvRecordAllowed.visibility = View.VISIBLE
            isRequestAudioPermission = true
        }

        if (Build.VERSION.SDK_INT < 33) {
            // Nếu Android SDK dưới 33, không cần quyền POST_NOTIFICATIONS
            dataBinding.swNotification.isChecked = true
            dataBinding.swNotification.visibility = View.GONE
            dataBinding.tvNotificationAllowed.visibility = View.VISIBLE
        } else if (ContextCompat.checkSelfPermission(
                requireContext(),
                "android.permission.POST_NOTIFICATIONS"
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Nếu quyền đã được cấp, chạy dịch vụ
            dataBinding.swNotification.isChecked = true
            dataBinding.swNotification.visibility = View.GONE
            dataBinding.tvNotificationAllowed.visibility = View.VISIBLE
            isRequestNotificationPermission = true
        } else {
            dataBinding.swNotification.isChecked = false
            dataBinding.swNotification.visibility = View.VISIBLE
            dataBinding.tvNotificationAllowed.visibility = View.GONE
            isRequestNotificationPermission = true
        }
    }

    override fun registerListeners() {
        dataBinding.btnBack.setOnClickListener {
            findNavControllerSafety()?.navigateUp()
        }
        dataBinding.swRecord.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && isRequestAudioPermission) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        "android.permission.RECORD_AUDIO"
                    ) != 0
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            "android.permission.RECORD_AUDIO"
                        )
                    ) {
                        requestAudioPermissionLauncher.launch("android.permission.RECORD_AUDIO")
                        localStorage.isFirstAudioPermissionRequire = false
                    } else {
                        if(localStorage.isFirstAudioPermissionRequire){
                            requestAudioPermissionLauncher.launch("android.permission.RECORD_AUDIO")
                        } else {
                            isRequestAudioPermission = false
                            startActivity(
                                Intent(
                                    ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", requireActivity().packageName, null),
                                )
                            )
                        }
                    }
                }
            }
        }

        dataBinding.swNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (ContextCompat.checkSelfPermission(
                        requireContext(),
                        "android.permission.POST_NOTIFICATIONS"
                    ) != 0
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            "android.permission.POST_NOTIFICATIONS"
                        )
                    ) {
                        requestPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")
                        localStorage.isFirstNotificationPermissionRequire = false
                    } else {
                        if(localStorage.isFirstNotificationPermissionRequire){
                            requestPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS")
                        } else {
                            isRequestNotificationPermission = false
                            val intent = if(Build.VERSION.SDK_INT >= 33){
                                Intent(Settings.ACTION_ALL_APPS_NOTIFICATION_SETTINGS)
                            } else {
                                Intent(
                                    ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", requireActivity().packageName, null),
                                )
                            }
                            startActivity(intent)
                        }
                    }
                }
            } else {
                if(Build.VERSION.SDK_INT >= 33) {

                } else {
                    dataBinding.swNotification.isChecked = true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val hasNotificationPermission =
            ContextCompat.checkSelfPermission(
                requireContext(),
                "android.permission.POST_NOTIFICATIONS"
            ) == PackageManager.PERMISSION_GRANTED
                    || Build.VERSION.SDK_INT < 33
        val hasRecordPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            "android.permission.RECORD_AUDIO"
        ) == PackageManager.PERMISSION_GRANTED
        dataBinding.swRecord.isChecked = hasRecordPermission
        dataBinding.swNotification.isChecked = hasNotificationPermission
        isRequestAudioPermission = true
        isRequestNotificationPermission = true
        if(hasNotificationPermission){
            dataBinding.swNotification.visibility = View.GONE
            dataBinding.tvNotificationAllowed.visibility = View.VISIBLE
        } else {
            dataBinding.swNotification.visibility = View.VISIBLE
            dataBinding.tvNotificationAllowed.visibility = View.GONE
        }
        if(hasRecordPermission){
            dataBinding.swRecord.visibility = View.GONE
            dataBinding.tvRecordAllowed.visibility = View.VISIBLE
        } else {
            dataBinding.swRecord.visibility = View.VISIBLE
            dataBinding.tvRecordAllowed.visibility = View.GONE
        }
    }
    
    override fun initializeData() {

    }
}