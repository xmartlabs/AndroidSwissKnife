package com.xmartlabs.swissknife.navigationdebug.extensions

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import com.xmartlabs.swissknife.core.Logger
import com.xmartlabs.swissknife.navigationdebug.DebugAndroidLogger
import com.xmartlabs.swissknife.reflection.extensions.getPrivatePropertyByReflection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Deque

private const val LOGGER_TAG = "NAV-DIRECTION"
private val DEFAULT_LOGGER: Logger =
    DebugAndroidLogger(LOGGER_TAG)

/**
 * Log the current nav path.
 * It uses reflection, so it shouldn't be used in production.
 *
 * @param logger The logger used to log the message.
 */
suspend fun NavController.logGraphPath(logger: Logger = DEFAULT_LOGGER) =
    withContext(Dispatchers.Default) {
      val currentBackStack =
          getPrivatePropertyByReflection<NavController, Deque<NavBackStackEntry>>("mBackStack")
              ?.map(NavBackStackEntry::getDestination)
              ?.filterNot { navBackStackEntry -> navBackStackEntry is NavGraph }
              ?.map(NavDestination::getLabel)
              ?.joinToString(" -> ")
      logger.log("Current nav graph path: $currentBackStack")
    }

/**
 * Log the current nav path in background.
 * It uses reflection, so it shouldn't be used in production.
 *
 * @param logger The logger used to log the message.
 */
fun NavController.logGraphPathInBackground(logger: Logger = DEFAULT_LOGGER) = GlobalScope.launch {
  logGraphPath(logger)
}

/**
 * Log the graph path changes.
 * It uses reflection, so it shouldn't be used in production.
 *
 * @param logger The logger used to log the message.
 */
fun NavController.setupOnDestinationChangedLogger(logger: Logger = DEFAULT_LOGGER) {
  addOnDestinationChangedListener { controller, _, _ ->
    controller.logGraphPathInBackground(logger)
  }
}
