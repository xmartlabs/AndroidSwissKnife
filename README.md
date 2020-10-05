# AndroidSwissKnife
![Check and compile](https://github.com/xmartlabs/AndroidSwissKnife/workflows/Check%20and%20compile/badge.svg?branch=master)
[![Release](https://jitpack.io/v/xmartlabs/AndroidSwissKnife.svg)](https://jitpack.io/#xmartlabs/AndroidSwissKnife)

A library that contains a series of extensions, helpers, and useful classes used in most of [Xmartlabs' Android Projects](https://www.xmartlabs.com/).

It's divided into a series of modules with different proposes:
- [Core](./swissknife-core): Core extensions of the application, which extends Android and Kotlin features.
- [DataStore](./swissknife-datastore): A Custom [DataStoreSource], based on [Jetpack DataStore], that provides the ability to store custom entities types without the requirement of using Protocol Buffers.
- [Navigation](./swissknife-navigation): Navigation extensions and useful helper classes related to [Navigation Architecture Component].
- [Navigation-debug](./swissknife-navigation-debug): A series of extensions and useful tools to debug and log important information of the [Navigation Architecture Component].
- [Reflection](./swissknife-reflection): A series of extensions useful when you are dealing with Reflection.

## Setup
Add library to project dependencies with JitPack.

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.xmartlabs.AndroidSwissKnife:swissknife-core:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:swissknife-datastore:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:swissknife-navigation:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:swissknife-navigation-debug:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:swissknife-reflection:${master-latest-hash-commit}"
}
```

## About
Made with ❤️ by [XMARTLABS](http://xmartlabs.com)

[Navigation Architecture Component]: https://developer.android.com/guide/navigation/navigation-getting-started
[Jetpack DataStore]: https://developer.android.com/topic/libraries/architecture/datastore
