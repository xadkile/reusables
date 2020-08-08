package com.github.xadkile.reusables.iterators

object CarriedOverSelectors {
    /**
     * Simply return the next element
     */
    class Sequential<T> : CarriedOverSelector<T> {
        override fun carryOver(newValue: T): CarriedOverSelector<T> {
            return this
        }

        override fun elementCondition(e: T): Boolean {
            return true
        }
    }
}