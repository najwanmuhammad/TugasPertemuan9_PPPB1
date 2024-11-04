package com.example.tugaspertemuan9

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : AppCompatActivity() {

    private var nameData = ""
    private var emailData = ""
    private var nimData = ""
    private var passwordData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameData = intent.getStringExtra("EXTRA_NAME") ?: ""
        emailData = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        nimData = intent.getStringExtra("EXTRA_NIM") ?: ""
        passwordData = intent.getStringExtra("EXTRA_PASSWORD") ?: ""

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_profil -> {
                val intent = Intent (this@DashboardActivity, ProfileActivity::class.java)
                intent.putExtra("EXTRA_EMAIL", emailData)
                intent.putExtra("EXTRA_PASSWORD", passwordData)
                intent.putExtra("EXTRA_NAME", nameData)
                intent.putExtra("EXTRA_NIM", nimData)
                startActivity(intent)
                return true
            }

            R.id.menu_logout -> {
                val intent = Intent (this@DashboardActivity, MainActivity::class.java)
                intent.putExtra("EXTRA_EMAIL", emailData)
                intent.putExtra("EXTRA_PASSWORD", passwordData)
                intent.putExtra("EXTRA_NAME", nameData)
                intent.putExtra("EXTRA_NIM", nimData)
                startActivity(intent)
                Toast.makeText(this, "Berhasil Logout.", Toast.LENGTH_SHORT).show()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}