package com.github.xadkile.reusables.comparator


/**
 * A comparator that accept a list of [T] which acts as an order guideline to compare
 * items that do not belong to [order] are consider "smaller" than items that do
 */
abstract class CustomOrderComparator<T:Comparable<T>> (
    private val order:List<T>
): Comparator<T> {
    override fun compare(item1: T, item2: T): Int {
        val index1: Int = this.order.indexOf(item1)
        val index2: Int = this.order.indexOf(item2)
        return index1.compareTo(index2)
    }

    companion object {
        @JvmStatic
        fun <T:Comparable<T>> create(order:List<T>):CustomOrderComparator<T>{
            return object : CustomOrderComparator<T>(order){}
        }
    }
}