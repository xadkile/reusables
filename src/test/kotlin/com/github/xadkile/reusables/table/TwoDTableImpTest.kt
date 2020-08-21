package com.github.xadkile.reusables.table

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TwoDTableImpTest {
    val rowKeys = listOf("r1","r2","r3")
    val colKeys = listOf("c1","c2","c3")
    val values = listOf(
        listOf(0,1,2),
        listOf(4,5,6),
        listOf(7,8,9)
    )
    val table = TwoDTableImp(
        rowKeys = rowKeys,
        colKeys = colKeys,
        values = values
    )
    @Test
    fun query() {
        val z = table.query("r2","c1")
        assertEquals(4,z)
    }

    @Test
    fun testQuery() {
        val z = table.query(Pair("r3","c2"))
        assertEquals(8,z)
    }
}