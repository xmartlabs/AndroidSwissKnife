package com.xmartlabs.swissknife.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.xmartlabs.swissknife.core.Logger
import com.xmartlabs.swissknife.navigationdebug.setupOnDestinationChangedLogger
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    setupTimber()

    findNavController(R.id.nav_host_fragment)
        .setupOnDestinationChangedLogger(object : Logger {
          override fun log(message: String) = Timber
              .tag("Nav-Path")
              .d(message)
        })

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }
  }

  private fun setupTimber() = Timber.plant(Timber.DebugTree())
}
