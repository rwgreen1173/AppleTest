package com.gmail.rwgreen1173.appletest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
