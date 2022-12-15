package com.example.pratica_android.parte1_ex3

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlin.math.abs

class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var mSensorManager : SensorManager
    lateinit var mAccelerometer : Sensor
    var x : Float = 0F
    var y : Float = 0F
    var z : Float = 0F
    var initialized : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()

        initialized = false

        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (initialized) {
            var movement = abs(event.values[0] - x) + abs(event.values[1] - y) + abs(event.values[2] - z)

            if (movement > 20) {
                startActivity(Intent(this, SecondActivity::class.java))
            }
        } else {
            initialized = true
        }

        x = event.values[0]
        y = event.values[1]
        z = event.values[2]

        findViewById<EditText>(R.id.editTextX).setText(event.values[0].toString())
        findViewById<EditText>(R.id.editTextY).setText(event.values[1].toString())
        findViewById<EditText>(R.id.editTextZ).setText(event.values[2].toString())
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }
}
