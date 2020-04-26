package com.github.xadkile.reusables.location

/**
 * A pair of labels signifying a departure and a destination
 */
interface FromTo<T>{
    fun getFromId():T
    fun getToId():T
}