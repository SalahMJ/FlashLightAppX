package com.salahmj.flashlightappx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import android.os.Build
import com.salahmj.flashlightappx.R
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraAccessException
import com.salahmj.flashlightappx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar!!.hide()
        binding!!.button.setOnClickListener {
            if (binding!!.button.text.toString() == "Turn on") {
                binding!!.button.setText(R.string.turnoff)
                binding!!.flashimage.setImageResource(R.drawable.flashon)
                changeLightState(true)
            } else {
                binding!!.button.setText(R.string.turnon)
                binding!!.flashimage.setImageResource(R.drawable.flashoff)
                changeLightState(false)
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun changeLightState(state: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
        run {
            val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
            var camId: String? = null
            try {
                camId = cameraManager.cameraIdList[0]
                cameraManager.setTorchMode(camId, state)
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding!!.button.setText(R.string.turnon)
    }
}