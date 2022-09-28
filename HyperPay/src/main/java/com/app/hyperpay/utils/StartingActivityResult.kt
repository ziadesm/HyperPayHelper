package com.app.hyperpay.utils
import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.app.hyperpay.builder.OnGettingResultBack

class StartingActivityResult(
    private val request: Int,
    private val mListener: OnGettingResultBack
) {

    private lateinit var fragmentLaunchActivityResult: ActivityResultLauncher<Intent>
    private lateinit var activityLaunchActivityResult: ActivityResultLauncher<Intent>
    fun initFragmentResult(fragment: FragmentActivity): ActivityResultLauncher<Intent> {
        fragmentLaunchActivityResult = fragment.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) result.data?.let { mListener.onGettingResult(request, it) }
        }
        return fragmentLaunchActivityResult
    }

    fun initActivityResult(act: AppCompatActivity): ActivityResultLauncher<Intent> {
        activityLaunchActivityResult = act.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) result.data?.let { mListener.onGettingResult(request, it) }
        }
        return activityLaunchActivityResult
    }

    fun launchResultFragment(intent: Intent) {
        fragmentLaunchActivityResult.launch(intent)
    }

    fun launchResultActivity(intent: Intent) {
        activityLaunchActivityResult.launch(intent)
    }
}