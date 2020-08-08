package com.github.xadkile.reusables.iterators

interface CarriedOverSelector<T> {
    /**
     * produce a new instance of [CarrierOverSelector] with [newValue] which can be used in evaluating elements of a list
     */
    fun carryOver(newValue:T): CarriedOverSelector<T>

    /**
     * check if element [e] meet certain condition
     */
    fun elementCondition(e:T):Boolean
}