package com.app.hyperpayandroiddemo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.app.hyperpay.builder.OnPaymentResponseCallback
import com.app.hyperpay.helper_model.PaymentResponse
import com.app.hyperpay.temp.PaymentHelper

class MainActivity : AppCompatActivity(), OnPaymentResponseCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PaymentHelper.Builder(this, this)
            .paymentType(hashSetOf("VISA", "MASTER", "DIRECTDEBIT_SEPA"))
            .checkoutId("278AC8A9A8AAEACFAD253EAD86E2640D.uat01-vm-tx01")
            .shopperResultUrl("com.app.hyperpayandroiddemo")
            .build()
    }

    override fun onPaymentResponseCallback(paymentResponse: PaymentResponse) {
        when(paymentResponse) {
            is PaymentResponse.PaymentSuccess -> {
                Log.e("PaymentResult", "onActivityResult: SUCCESS >>>>> ${paymentResponse.resourcePath}")
                Log.e("PaymentResult", "onActivityResult: SUCCESS >>>>> ${paymentResponse.transaction}")
            }
            is PaymentResponse.PaymentCanceled -> {
                Log.e("PaymentResult", "onActivityResult: CANCELED >>>>> CANCEL")
            }
            is PaymentResponse.PaymentFailed -> {
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${paymentResponse.paymentError?.errorCode}")
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${paymentResponse.paymentError?.errorInfo}")
                Log.e("PaymentResult", "onActivityResult: ERROR >>>>> ${paymentResponse.paymentError?.errorMessage}")
            }
        }
    }

}