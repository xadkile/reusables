package com.github.xadkile.reusables.geo

interface ThreeD<T:Comparable<T>> : TwoD<T>{
    fun getHeight():T

    companion object CO{
        @JvmStatic
        fun <T:Comparable<T>> create(width:T,length:T,height:T):ThreeD<T>{
            return object:ThreeD<T>{
                override fun getWidth(): T {
                    return width
                }

                override fun getLength(): T {
                    return length
                }

                override fun getHeight(): T {
                    return height
                }
            }
        }
    }
}