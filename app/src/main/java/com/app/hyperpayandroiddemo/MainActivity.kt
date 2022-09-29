package com.app.hyperpayandroiddemo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.hyperpay.builder.OnPaymentResponseCallback
import com.app.hyperpay.helper_model.PaymentResponse
import com.app.hyperpay.temp.PaymentHelper

class MainActivity : AppCompatActivity(), OnPaymentResponseCallback {/*OnPaymentResponseCallback*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PaymentHelper.Builder(this, this)
            .paymentType(hashSetOf("VISA"))
            .checkoutId("80FB22874855575586E1BD0CC2489E1E.uat01-vm-tx01")
            .shopperResultUrl("com.app.hyperpayandroiddemo")
            .build()
    }

    override fun onPaymentResponseCallback(paymentResponse: PaymentResponse) {
        when(paymentResponse) {
            is PaymentResponse.PaymentSuccess -> {
                Log.e("PaymentResult", "onActivityResult: SUCCESS >>>>> ${paymentResponse.resourcePath}")
                Log.e("PaymentResult", "onActivityResult: SUCCESS >>>>> ${paymentResponse.transaction?.transaction_type}")
            }
            is PaymentResponse.PaymentCanceled -> {
                Log.e("PaymentResult", "onActivityResult: CANCELED >>>>> CANCEL")
            }
            is PaymentResponse.PaymentFailed -> {
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${paymentResponse.paymentError?.error_code}")
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${paymentResponse.paymentError?.error_info}")
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${paymentResponse.paymentError?.error_message}")
            }
        }
    }

}