# AndroidSwissKnife
![Check and compile](https://github.com/xmartlabs/AndroidSwissKnife/workflows/Check%20and%20compile/badge.svg)
![Check and compile](https://github.com/xmartlabs/AndroidSwissKnife/workflows/Check%20and%20compile/badge.svg?branch=master)
[![Release](https://jitpack.io/v/xmartlabs/AndroidSwissKnife.svg)](https://jitpack.io/#xmartlabs/AndroidSwissKnife)

A library that contains a series of extensions, helpers, and useful classes used in most of [Xmartlabs' Android Projects](https://www.xmartlabs.com/).

It's divided into a series of modules with different proposes:
- [Core](/core): Core extensions of the application, for example view's and resource's extensions.
- [Navigation](/navigation): Navigation extensions and useful helper classes related to [Navigation Architecture Component].
- [Navigation-debug](/navigation-debug): A series of extensions and useful tools to debug and log important information of the [Navigation Architecture Component].
- [Reflection](/reflection): A series of extensions useful when you are dealing with Reflection.

## Setup
Add library to project dependencies with JitPack.

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.xmartlabs.AndroidSwissKnife:core:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:navigation:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:navigation-debug:${master-latest-hash-commit}"
    implementation "com.github.xmartlabs.AndroidSwissKnife:reflection:${master-latest-hash-commit}"
}
```

## About
Made with ❤️ by [XMARTLABS](http://xmartlabs.com)

[Navigation Architecture Component]: https://developer.android.com/guide/navigation/navigation-getting-started
