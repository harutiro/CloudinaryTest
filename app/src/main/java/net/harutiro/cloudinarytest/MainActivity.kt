package net.harutiro.cloudinarytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback

class MainActivity : AppCompatActivity() {

    private val TAG = "Cloudinary"

    private val launcher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
        findViewById<ImageView>(R.id.imageView2).setImageURI(it)

        MediaManager.get().upload(it).callback(object : UploadCallback {
            override fun onStart(requestId: String) {
                Log.d(TAG, "onStart: " + "started")
            }

            override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                Log.d(TAG, "onStart: " + "uploading")
            }

            override fun onSuccess(requestId: String, resultData: Map<*, *>?) {
                Log.d(TAG, "onStart: " + "usuccess" + resultData?.get("secure_url"))

                findViewById<ImageView>(R.id.imageView3).load(resultData?.get("secure_url"))
            }

            override fun onError(requestId: String?, error: ErrorInfo) {
                Log.d(TAG, "onStart: $error")
            }

            override fun onReschedule(requestId: String?, error: ErrorInfo) {
                Log.d(TAG, "onStart: $error")
            }
        }).dispatch()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            launcher.launch(arrayOf("image/*"))
        }

        val config = mapOf(
            "cloud_name" to "dlg3xe2l2",
            "api_key" to "693697224285166",
            "api_secret" to "OTkrTsmgoXdEyBEtw2gTjiOs9oo"
        )
        MediaManager.init(this, config);




    }
}