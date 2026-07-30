package com.gmail.rwgreen1173.appletest

class GreetingGenerator {
    private val platform: Platform = getPlatform()

    fun getName(): String {
        return platform.name
    }
}
