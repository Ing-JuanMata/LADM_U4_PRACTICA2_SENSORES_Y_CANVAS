package mx.edu.ittepic.ladm_u4_practica2_sensores_y_canvas

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class Canva(p: MainActivity) : View(p) {
    val main = p
    val bruja = BitmapFactory.decodeResource(resources, R.drawable.bruja)
    val luna = BitmapFactory.decodeResource(resources, R.drawable.luna)
    val sol = BitmapFactory.decodeResource(resources, R.drawable.sol)
    val nubes = BitmapFactory.decodeResource(resources, R.drawable.clouds)
    val p = Paint()

    val azul = "#ADD8E6"
    val azulOscuro = "#252850"

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (main.noche) {
            noche(canvas)
        } else {
            dia(canvas)
        }
    }

    private fun dia(canvas: Canvas) {
        canvas.drawColor(Color.parseColor(azul))
        val gx = main.gx * 100
        val gy = main.gy * 100
        canvas.drawBitmap(
            sol,
            width / 2f - sol.width / 2f + gy,
            height / 2f - sol.height / 2f + gx,
            p
        )
        canvas.drawBitmap(nubes, width / 2f - nubes.width / 2f + gy, gx, p)
        canvas.drawBitmap(
            bruja,
            width / 2f - bruja.width / 2f + gy,
            height / 2f - bruja.height / 2f + gx,
            p
        )
    }

    private fun noche(canvas: Canvas) {
        canvas.drawColor(Color.parseColor(azulOscuro))
        val gx = main.gx * 100
        val gy = main.gy * 100
        canvas.drawBitmap(
            luna,
            width / 2f - luna.width / 2f + gy,
            height / 2f - luna.height / 2f + gx,
            p
        )
        canvas.drawBitmap(nubes, width / 2f - nubes.width / 2f + gy, gx, p)
        canvas.drawBitmap(
            bruja,
            width / 2f - bruja.width / 2f + gy,
            height / 2f - bruja.height / 2f + gx,
            p
        )
    }
}