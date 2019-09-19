package com.offer.one

class Singleton8 private constructor() {
    companion object {
        val instance: Singleton8 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            Singleton8()
        }
    }

    fun main(){
        lazy1("aaa"){
            "aaa"
        }
    }

    fun a():String = "aa"

    fun <T> lazy1(mode: String, initializer: () -> T):T{
        return initializer()
    }
}