package com.kodingwithkyle.workouttracker.ui.safetynet

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.safetynet.SafetyNet
import com.kodingwithkyle.workouttracker.MainActivity
import com.kodingwithkyle.workouttracker.R
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.security.SecureRandom


class SafetyNetActivity : Activity() {

    companion object {
        const val TAG = "SAFETY_NET_ACTIVITY"
    }

    private val mGoogleApiClient by lazy {
        GoogleApiAvailability.getInstance()
    }

    private var mGoogleAPIAvailabilityTryCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safety_net)
        checkGooglePlay()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111) {
            checkGooglePlay()
        }
    }

    private fun checkGooglePlay() {
        val code = mGoogleApiClient.isGooglePlayServicesAvailable(this, 13000000)
        when {
            ++mGoogleAPIAvailabilityTryCount == 3 -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            code == ConnectionResult.SUCCESS -> {
                Log.d(TAG, "successfully connected to google play")
                //attest()
                startActivity(Intent(this, MainActivity::class.java))
            }
            else -> {
                if (mGoogleApiClient.isUserResolvableError(code)) {
                    GoogleApiAvailability.getInstance().getErrorDialog(this, 1, 111)
                }
            }
        }
    }

    private fun attest() {
        val nonceData = "Safety Net Sample: " + System.currentTimeMillis()
        val nonce: ByteArray = getRequestNonce(nonceData)

        SafetyNet.getClient(this).attest(nonce, "AIzaSyBmnlfqlsm2Lponv9geDYBYUOhaiYgVwsU")
            .addOnSuccessListener(this) {
                // Indicates communication with the service was successful.
                // Use response.getJwsResult() to get the result data.
                Log.d(TAG, it.jwsResult)
            }
            .addOnFailureListener(this) { e ->
                // An error occurred while communicating with the service.
                if (e is ApiException) {
                    // An error with the Google Play services API contains some
                    // additional details.
                    val apiException = e as ApiException

                    // You can retrieve the status code using the
                    // apiException.statusCode property.
                } else {
                    // A different, unknown type of error occurred.
                    Log.d(TAG, "Error: " + e.message)
                }
            }
    }

    private fun getRequestNonce(data: String): ByteArray {
        val byteStream = ByteArrayOutputStream()
        val bytes = ByteArray(24)
        SecureRandom().nextBytes(bytes)
        try {
            byteStream.write(bytes)
            byteStream.write(data.toByteArray())
        } catch (e: IOException) {
            return bytes
        }
        return byteStream.toByteArray()
    }
}