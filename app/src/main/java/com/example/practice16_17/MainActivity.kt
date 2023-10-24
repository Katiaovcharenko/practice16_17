package com.example.practice16_17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        menuItem = toolbar.menu.findItem(R.id.theme_switch)
        updateMenuText()

        // Логика смены темы
        menuItem.setOnMenuItemClickListener {
            switchTheme()
            updateMenuText()
            true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                // Открываем новое окно с содержимым "О программе"
                openAboutProgramActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateMenuText() {
        val themeText = if (isDarkThemeEnabled()) {
            getString(R.string.light)
        } else {
            getString(R.string.night)
        }
        menuItem.title = themeText
    }

    private fun switchTheme() {
        if (isDarkThemeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun isDarkThemeEnabled(): Boolean {
        val nightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
        return nightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }

    private fun openAboutProgramActivity() {
        // Открываем новое окно с содержимым "О программе"
        val intent = Intent(this, AboutProgramActivity::class.java)
        startActivity(intent)
    }

}