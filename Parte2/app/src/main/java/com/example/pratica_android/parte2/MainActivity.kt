package com.example.pratica_android.parte2

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), SensorEventListener, LocationListener {
    lateinit var mSensorManager : SensorManager
    lateinit var mAccelerometer : Sensor
     lateinit var mGyroscope: Sensor
    lateinit var mLight: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        var l = getLocation()

        if (l != null) {
            findViewById<EditText>(R.id.editTextLat).setText(l.latitude.toString())
            findViewById<EditText>(R.id.editTextLon).setText(l.longitude.toString())
        }
    }

    override fun onResume() {
        super.onResume()

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()

        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.equals(mAccelerometer)) {
            findViewById<EditText>(R.id.editTextAccelX).setText(event.values[0].toString())
            findViewById<EditText>(R.id.editTextAccelY).setText(event.values[1].toString())
            findViewById<EditText>(R.id.editTextAccelZ).setText(event.values[2].toString())
        } else if (event.sensor.equals(mGyroscope)) {
            findViewById<EditText>(R.id.editTextGyroX).setText(event.values[0].toString())
            findViewById<EditText>(R.id.editTextGyroY).setText(event.values[1].toString())
            findViewById<EditText>(R.id.editTextGyroZ).setText(event.values[2].toString())
        } else if (event.sensor.equals(mLight)) {
            findViewById<EditText>(R.id.editTextLight).setText(event.values[0].toString())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
    }

    override fun onLocationChanged(location: Location) {
    }

    fun getLocation(): Location? {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show()

            return null;
        }

        var lm = getSystemService(LOCATION_SERVICE) as LocationManager

        var isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (isGPSEnabled) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10F, this)

            return lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } else {
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_LONG).show()
        }

        return null
    }
}
