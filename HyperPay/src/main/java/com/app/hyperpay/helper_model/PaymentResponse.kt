package com.app.hyperpay.helper_model
import com.oppwa.mobile.connect.exception.PaymentError
import com.oppwa.mobile.connect.provider.Transaction

sealed class PaymentResponse {
    data class PaymentSuccess(val transaction: Transaction?, val resourcePath: String?): PaymentResponse()
    data class PaymentFailed(val paymentError: PaymentError?): PaymentResponse()
    object PaymentCanceled: PaymentResponse()
}