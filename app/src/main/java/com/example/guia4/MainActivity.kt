package com.example.guia4
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
class MainActivity : AppCompatActivity() {
    private val TAG = "Guia4-Permisos"
    private val CODIGO_SOLICITUD_GRABAR = 101

    lateinit var btnCamera: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Comprobación de permisos
        configurarPermiso()
        btnCamera = findViewById(R.id.btnCamara)
        btnCamera.setOnClickListener {
            val intent = Intent(this, CamaraActivity::class.java)
            startActivity(intent)
        }
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
    private fun configurarPermiso() {
        val estadoPermiso = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.RECORD_AUDIO
        )
        if (estadoPermiso != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, getString(R.string.permiso_audio_denegado))
            val mostrarRequest = ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.RECORD_AUDIO
            );
            if (mostrarRequest) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(getString(R.string.permiso_audio_requerido))
                    .setTitle("Permiso requirido")
                builder.setPositiveButton("OK") { _, _ ->
                    Log.i(TAG, "Seleccionado")
                    solicitudPermiso()
                }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                solicitudPermiso()
            }
        }
    }

    // 3. Solicitud de permiso
    private fun solicitudPermiso() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            CODIGO_SOLICITUD_GRABAR
        )
    }
    // 4. Manejo de respuesta de solicitud de permiso
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CODIGO_SOLICITUD_GRABAR -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, getString(R.string.permiso_audio_denegado_usuario))
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.permiso_audio_concedido_usuario),
                        Toast.LENGTH_SHORT
                    ).show()
                    /* Código cuando el ususario deniegue el permiso */
                } else {
                    Log.i(TAG, getString(R.string.permiso_audio_concedido_usuario))
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.permiso_audio_concedido_usuario),
                        Toast.LENGTH_SHORT
                    ).show()
                    /* Código cuando el ususario condeda el permiso */
                }
            }
        }
    }

}