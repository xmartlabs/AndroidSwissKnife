# Navigation Debug Module
This module contains a series of extensions that helps you to debug and understand the current navigation controller state when you are working with [Android Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started).
If you want to include this dependency only in debug mode, you can use `debugImplementation` method or use a custom flavour.

```groovy
dependencies {
    // Only include the dependency in debug builds
    debugImplementation 'com.github.xmartlabs.AndroidSwissKnife:navigation-debug:$master-latest-hash-commit'

    // Only include the dependency in dev's flavour builds
    devImplementation 'com.github.xmartlabs.AndroidSwissKnife:navigation-debug:$master-latest-hash-commit'
}
```


## Dependencies:
- [Navigation Fragment Dependency (`androidx.navigation:navigation-fragment-ktx`)](https://developer.android.com/jetpack/androidx/releases/navigation).
- [Core Module](../core) 
- [Navigation Module](../navigation) 
- [Reflection Module](../reflection) 

## Module's content:

### [NavigationDebugExtensions](./src/main/java/com/xmartlabs/swissknife/navigationdebug/NavigationDebugExtensions.kt)
It contains a series of extensions to log the current navigation graph state.
The extensions uses reflection, so it's recommended used only in debug mode.
