package com.xmartlabs.swissknife.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xmartlabs.swissknife.ui.extensions.navigateSafe

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(R.layout.fragment_second) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<Button>(R.id.button_second).setOnClickListener {
      findNavController().navigateSafe(R.id.action_SecondFragment_to_FirstFragment)
    }
  }
}
