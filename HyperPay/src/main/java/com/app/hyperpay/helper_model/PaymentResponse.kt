package com.app.hyperpay.helper_model
import com.oppwa.mobile.connect.exception.PaymentError
import com.oppwa.mobile.connect.provider.Transaction

sealed class PaymentResponse {
    data class PaymentSuccess(val transaction: PaymentSuccessModel?, val resourcePath: String?): PaymentResponse()
    data class PaymentFailed(val paymentError: PaymentErrorModel?): PaymentResponse()
    object PaymentCanceled: PaymentResponse()
}