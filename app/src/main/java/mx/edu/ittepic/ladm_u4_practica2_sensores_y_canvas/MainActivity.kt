package mx.edu.ittepic.ladm_u4_practica2_sensores_y_canvas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var mProximity: Sensor
    private lateinit var giroscope: Sensor
    private lateinit var canva: Canva

    var noche = true
    var iniciado = false
    var gx = 0f
    var gy = 0f
    var gz = 0f

    private val NS2S = 1.0f / 1000000000.0f
    private val deltaRotationVector = FloatArray(4) { 0f }
    private var timestamp: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canva = Canva(this)
        setContentView(canva)
        iniciado = true
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        giroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.stringType.equals(giroscope.stringType)) logicaGiroscopio(
            event.values,
            event.timestamp
        )
        if (event.sensor.stringType.equals(mProximity.stringType)) logicaProximidad(event.values)
    }

    private fun logicaProximidad(valores: FloatArray) {
        val distance = valores[0]

        noche = distance < 5
        if (iniciado) canva.invalidate()
    }

    private fun logicaGiroscopio(valores: FloatArray, evtTimestamp: Long) {
        gx = valores[0]
        gy = valores[1]
        gz = valores[2]
        if (iniciado) canva.invalidate()
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()

        mProximity.also { proximity ->
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL)
        }
        giroscope.also { giro ->
            sensorManager.registerListener(this, giro, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}