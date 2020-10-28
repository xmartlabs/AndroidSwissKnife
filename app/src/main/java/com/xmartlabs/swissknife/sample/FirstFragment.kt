package com.xmartlabs.swissknife.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xmartlabs.swissknife.navigation.extensions.navigateSafe
import com.xmartlabs.swissknife.sample.data.SessionRepository
import com.xmartlabs.swissknife.sample.data.model.Session
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(R.layout.fragment_first) {
  private val sessionRepository by lazy { SessionRepository(requireContext()) }
  private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupLastVisitedTextView(view)
    view.findViewById<Button>(R.id.button_first).setOnClickListener {
      findNavController().navigateSafe(R.id.action_FirstFragment_to_SecondFragment)
    }
  }

  private fun setupLastVisitedTextView(view: View) {
    lifecycleScope.launchWhenStarted {
      val session = sessionRepository.session.first()
      view.findViewById<TextView>(R.id.welcome_text_first).text = if (session == null) {
        "This is the first time you visit this fragment"
      } else {
        "The last time you visited this fragment was ${dateFormat.format(session.lastTime)}"
      }
      val newSession: Session? = session?.copy(lastTime = Date()) ?: Session(Date())
      sessionRepository.saveSession(newSession)
    }
  }
}
