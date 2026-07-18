package com.example.guia4
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    private val TAG = "Guia4-Permisos"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Comprobación de permisos
        comprobarEstadoPermiso()
    }
    // 1. Comprobar estado permiso
    private fun comprobarEstadoPermiso() {
        val estadoPermiso = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        )
        if (estadoPermiso != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permiso para grabar denegado")
        }
    }
    // 2. Configurar permisos
    // 3. Solicitud de permiso
    // 4. Manejo de respuesta de solicitud de permiso
}