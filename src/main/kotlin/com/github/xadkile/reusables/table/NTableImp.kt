package com.github.xadkile.reusables.table

class NTableImp <K:Comparable<K>,V>(
    internal val internalMap:Map<List<K>,V> = emptyMap()
):NTable<K,V>{
    override fun query(keys: List<K>): V? {
        return this.internalMap[keys]
    }

    override fun query(vararg keys: K): V? {
        return this.internalMap[keys.toList()]
    }
}