package com.github.xadkile.reusables.geometry

interface TwoD <T:Comparable<T>> {
    fun getWidth():T
    fun getLength():T
    companion object CO{
        @JvmStatic
        fun <T:Comparable<T>> create(width:T,length:T):TwoD<T>{
            return object:TwoD<T>{
                override fun getWidth(): T {
                    return width
                }

                override fun getLength(): T {
                    return length
                }

            }
        }
    }
}