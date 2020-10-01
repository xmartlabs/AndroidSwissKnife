package com.xmartlabs.swissknife.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xmartlabs.swissknife.navigation.extensions.navigateSafe

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(R.layout.fragment_first) {
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<Button>(R.id.button_first).setOnClickListener {
      findNavController().navigateSafe(R.id.action_FirstFragment_to_SecondFragment)
    }
  }
}
