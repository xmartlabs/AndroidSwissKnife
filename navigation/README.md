# Navigation Module

It contains the core extensions needed when you are dealing with the [Android Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started).

## Dependencies:
- [Navigation Fragment Dependency (`androidx.navigation:navigation-fragment-ktx`)](https://developer.android.com/jetpack/androidx/releases/navigation).
- [Core Module](../core) 

## Module's content:

### [NavigationExtensions](src/main/java/com/xmartlabs/swissknife/ui/NavigationExtensions.kt)
A series of extensions to navigate safely.
Suppose that you have a button that navigates from the screen `A` to the screen `B`.
If the user clicks this button twice quickly, the first time will navigate from `A` to `B`.
However, the second one the navigation component will try to navigate from `A` to `B` making crash the app because the current node is `B`.
These extensions help to avoid these cases.

### [Keep Nav State](src/main/java/com/xmartlabs/swissknife/ui/keep_nav_state)
This package, contains the [`KeepStateNavigator`] class, which saves the Fragment state, when the fragment is removed from the Fragment Manager.
The navigator defines the `keep_state_fragment` tag, that should be used in the navigation graph to indicates to use this Navigator.
For example, your graph will look something like:

To use the [`KeepStateNavigator`], you can add the controller manually in your NavHostFragment or use the [`KeepStateNavHostFragment`], a `NavHostFragment` witch provides [`KeepStateNavigator`] support.
A common use of this tool is when you have a fragment that contains multiple fragments that can change and you don't want to lose the child Fragment's state.
The parent fragment can define the child navigation fragment as: 
```xml
<fragment
    android:id="@id/keepStateNavHostContentFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:name="com.xmartlabs.swissknife.ui.keep_nav_state.KeepStateNavHostFragment"
    app:navGraph="@navigation/nav_graph"
    />
```
The Navigation graph will look like:
```xml
<navigation
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/fragmentA"
    >

    <keep_state_fragment
        android:id="@+id/fragmentA"
        android:name="...FragmentA"
        android:label="FragmentA"
        />

    <keep_state_fragment
        android:id="@+id/fragmentB"
        android:name="...FragmentB"
        android:label="FragmentB"
        />


    <action
        android:id="@+id/action_display_fragment_a"
        app:destination="@id/fragmentA"
        />

    <action
        android:id="@+id/action_display_fragment_b"
        app:destination="@id/fragmentB"
        />

</navigation>
```

[`KeepStateNavigator`]: src/main/java/com/xmartlabs/swissknife/ui/keep_nav_state/KeepStateNavigator.kt
[`KeepStateNavHostFragment`]: src/main/java/com/xmartlabs/swissknife/ui/keep_nav_state/KeepStateNavHostFragment.kt
