package com.github.xadkile.reusables.table

class TwoDTableImp<K : Comparable<K>, V>(rowKeys: List<K>, colKeys: List<K>, values: List<List<V>>) : TwoDTable<K, V> {
    private val iTable: NTable<K, V>

    init {
        val mm = mutableMapOf<List<K>, V>()
        for (rkWIndex in rowKeys.withIndex()) {
            val rkIndex = rkWIndex.index
            val rk = rkWIndex.value
            for (ckWIndex in colKeys.withIndex()) {
                val ckIndex = ckWIndex.index
                val ck = ckWIndex.value
                mm[listOf(rk, ck)] = values[rkIndex][ckIndex]
            }
        }
        this.iTable = NTableImp(mm)
    }

    override fun query(keys: List<K>): V? {
        return this.iTable.query(keys)
    }

    override fun query(vararg keys: K): V? {
        return this.iTable.query(*keys)
    }

    override fun query(rowKey: K, colKey: K): V? {
        return this.iTable.query(listOf(rowKey, colKey))
    }

    override fun query(rowColKey: Pair<K, K>): V? {
        return this.iTable.query(rowColKey.toList())
    }
}