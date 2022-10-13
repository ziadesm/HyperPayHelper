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
            .paymentType(hashSetOf("VISA"))
            .checkoutId("80FB22874855575586E1BD0CC2489E1E.uat01-vm-tx01")
            .shopperResultUrl("com.app.hyperpayandroiddemo")
            .testMode(false)
            .build()

        PaymentHelper.Builder(this, this)

    }


    override fun onPaymentResponseCallback(paymentResponse: PaymentResponse) {

    }

}