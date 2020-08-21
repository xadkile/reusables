package com.github.xadkile.reusables.table
interface TwoDTable<K:Comparable<K>,V> : NTable<K,V>{
    fun query(rowKey:K,colKey:K):V?
    fun query(rowColKey:Pair<K,K>):V?
}
