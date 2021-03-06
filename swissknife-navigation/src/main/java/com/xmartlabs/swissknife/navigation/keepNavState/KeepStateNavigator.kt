package com.xmartlabs.swissknife.navigation.keepNavState

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

/**
 * [FragmentNavigator] which keeps the fragment state.
 * Used to change the child Fragment (using the childFragmentManager) and store the view state.
 */
@Navigator.Name("keep_state_fragment") // `keep_state_fragment` is used in navigation xml
class KeepStateNavigator(
    private val context: Context,
    private val childFragmentManager: FragmentManager, // Should pass childFragmentManager.
    private val containerId: Int
) : FragmentNavigator(context, childFragmentManager, containerId) {

  override fun navigate(
      destination: Destination,
      args: Bundle?,
      navOptions: NavOptions?,
      navigatorExtras: Navigator.Extras?
  ): NavDestination? {
    val tag = destination.id.toString()
    val transaction = childFragmentManager.beginTransaction()

    var initialNavigate = false
    val currentFragment = childFragmentManager.primaryNavigationFragment
    if (currentFragment != null) {
      transaction.detach(currentFragment)
    } else {
      initialNavigate = true
    }

    var fragment = childFragmentManager.findFragmentByTag(tag)
    if (fragment == null) {
      val className = destination.className
      fragment = childFragmentManager.fragmentFactory.instantiate(context.classLoader, className)
      transaction.add(containerId, fragment, tag)
    } else {
      transaction.attach(fragment)
    }

    transaction.setPrimaryNavigationFragment(fragment)
    transaction.setReorderingAllowed(true)
    transaction.commitNow()

    return if (initialNavigate) {
      destination
    } else {
      null
    }
  }
}
