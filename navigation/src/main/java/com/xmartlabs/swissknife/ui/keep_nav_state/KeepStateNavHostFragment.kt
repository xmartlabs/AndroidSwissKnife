package com.xmartlabs.swissknife.ui.keep_nav_state

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import com.xmartlabs.swissknife.ui.R

/**
 * [NavHostFragment] with [KeepStateNavigator] support.
 * Used to keep the child [Fragment] state.
 */
class KeepStateNavHostFragment : NavHostFragment() {
  private val containerId: Int
    get() = if (id != 0 && id != View.NO_ID) id else R.id.nav_host_fragment_container

  override fun onCreateNavController(navController: NavController) {
    navController.navigatorProvider += KeepStateNavigator(
      requireContext(),
      childFragmentManager,
      containerId
    )
    super.onCreateNavController(navController)
  }
}
