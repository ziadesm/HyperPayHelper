package com.app.hyperpay.temp
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.app.hyperpay.builder.OnGettingResultBack
import com.app.hyperpay.builder.OnPaymentResponseCallback
import com.app.hyperpay.builder.PaymentBuilder
import com.app.hyperpay.helper_model.PaymentResponse
import com.app.hyperpay.utils.StartingActivityResult
import com.oppwa.mobile.connect.checkout.dialog.CheckoutActivity
import com.oppwa.mobile.connect.checkout.meta.CheckoutSettings
import com.oppwa.mobile.connect.exception.PaymentError
import com.oppwa.mobile.connect.provider.Connect
import com.oppwa.mobile.connect.provider.Transaction
import kotlinx.coroutines.launch

class PaymentHelper: PaymentBuilder, OnGettingResultBack  {
    private constructor(activity: AppCompatActivity, paymentCallback: OnPaymentResponseCallback) {
        mActivity = activity
        mPaymentModel = PaymentHelperModel()
        mPaymentResponse = paymentCallback
        createStartingActivity(activity.lifecycleScope, activity)
    }
    private constructor(fragment: FragmentActivity, paymentCallback: OnPaymentResponseCallback) {
        mActivity = fragment as AppCompatActivity
        mPaymentModel = PaymentHelperModel()
        mPaymentResponse = paymentCallback
        createStartingFragment(fragment.lifecycleScope, fragment)
    }

    private var mActivity: AppCompatActivity? = null
    private var mPaymentModel: PaymentHelperModel? = null
    private var mStartPayment: StartingActivityResult? = null
    private var mPaymentResponse: OnPaymentResponseCallback? = null

    override fun paymentType(paymentType: HashSet<String>): PaymentBuilder {
        mPaymentModel?.payment_type = paymentType
        return this
    }

    override fun checkoutId(checkoutId: String): PaymentBuilder {
        mPaymentModel?.checkout_id = checkoutId
        return this
    }

    override fun shopperResultUrl(shopperUrl: String): PaymentBuilder {
        mPaymentModel?.shopper_url = shopperUrl
        return this
    }

    override fun testMode(testMode: Boolean): PaymentBuilder {
        mPaymentModel?.provider_mode = if (testMode) Connect.ProviderMode.TEST else Connect.ProviderMode.LIVE
        return this
    }

    override fun build() {
        val checkoutSettings = CheckoutSettings(mPaymentModel?.checkout_id ?: "", mPaymentModel?.payment_type, mPaymentModel?.provider_mode!!)
        checkoutSettings.shopperResultUrl = "${mPaymentModel?.shopper_url}://result"

        val intent = checkoutSettings.createCheckoutActivityIntent(mActivity)
        mStartPayment?.launchResultActivity(intent)
    }

    private fun createStartingActivity(lifecycle: LifecycleCoroutineScope, activity: AppCompatActivity) {
        lifecycle.launch {
            mStartPayment = StartingActivityResult(
                CheckoutActivity.REQUEST_CODE_CHECKOUT,
                this@PaymentHelper
            )
            mStartPayment?.initActivityResult(activity)
        }
    }
    private fun createStartingFragment(lifecycle: LifecycleCoroutineScope, fragment: FragmentActivity) {
        lifecycle.launch {
            mStartPayment = StartingActivityResult(
                CheckoutActivity.REQUEST_CODE_CHECKOUT,
                this@PaymentHelper
            )
            mStartPayment?.initFragmentResult(fragment)
        }
    }

    companion object {
        fun Builder(activity: AppCompatActivity, paymentCallback: OnPaymentResponseCallback): PaymentHelper {
            return PaymentHelper(activity, paymentCallback)
        }
        fun Builder(fragment: FragmentActivity, paymentCallback: OnPaymentResponseCallback): PaymentHelper {
            return PaymentHelper(fragment, paymentCallback)
        }
    }

    override fun onGettingResult(request: Int, data: Intent?) {
        when(request) {
            CheckoutActivity.RESULT_OK -> {
                val transaction: Transaction? = data?.getParcelableExtra(CheckoutActivity.CHECKOUT_RESULT_TRANSACTION)
                val resourcePath: String? = data?.getStringExtra(CheckoutActivity.CHECKOUT_RESULT_RESOURCE_PATH)
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentSuccess(transaction, resourcePath))
            }

            CheckoutActivity.RESULT_CANCELED -> {
                Log.e("PaymentResult", "onActivityResult: CANCEL >>>>> ")
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentCanceled)
            }

            CheckoutActivity.RESULT_ERROR -> {
                val error: PaymentError? = data?.getParcelableExtra(CheckoutActivity.CHECKOUT_RESULT_ERROR)
                mPaymentResponse?.onPaymentResponseCallback(PaymentResponse.PaymentFailed(error))
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${error?.errorCode}")
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${error?.errorInfo}")
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${error?.errorMessage}")
            }
        }
    }
}