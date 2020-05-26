package com.xmartlabs.swissknife.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

/**
 * Navigate to a destination from the current navigation graph **safely**.
 * It's safe because it avoids crashes when the new direction doesn't exist.
 * For example, it's useful when the user clicks multiple times in the redirection button quickly.
 *
 * @param resId an {@link NavDestination#getAction(int) action} id or a destination id to
 *              navigate to
 * @param args arguments to pass to the destination
 * @param navOptions special options for this navigation operation
 * @param navigatorExtras extras to pass to the Navigator
 */
fun NavController.navigateSafe(
  @IdRes resId: Int,
  args: Bundle? = null,
  navOptions: NavOptions? = null,
  navExtras: Navigator.Extras? = null
) {
  val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
  if (action != null && currentDestination?.id != action.destinationId) {
    navigate(resId, args, navOptions, navExtras)
  }
}

/**
 * Navigate **safely** via the given {@link NavDirections}
 * It's safe because it avoids crashes when the new direction doesn't exist.
 * For example, it's useful when the user clicks multiple times in the redirection button quickly.
 *
 * @param directions directions that describe this navigation operation
 * @param navOptions special options for this navigation operation
 */
fun NavController.navigateSafe(
  direction: NavDirections,
  navOptions: NavOptions? = null
) {
  val action = currentDestination?.getAction(direction.actionId) ?: graph.getAction(direction.actionId)
  if (action != null && currentDestination?.id != action.destinationId) {
    navigate(direction, navOptions)
  }
}
