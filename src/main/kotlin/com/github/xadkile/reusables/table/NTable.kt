package com.github.xadkile.reusables.table

/**
 * immutable NTable
 */
interface NTable<K:Comparable<K>,V> {
    fun query(keys:List<K>):V?
    fun query(vararg keys:K):V?
}

/**
 * Mutable NTable
 */
interface MutableNTable<K:Comparable<K>,V> : NTable<K,V>{
    fun update(keys:List<K>,newVal:V):MutableNTable<K,V>
}