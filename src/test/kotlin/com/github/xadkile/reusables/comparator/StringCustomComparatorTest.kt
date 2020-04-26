package com.github.xadkile.reusables.comparator

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StringCustomComparatorTest {

    @Test
    fun compare() {
        val order = listOf(
            "str1","str2","str3","str4"
        )

        val comparator = StringCustomComparator(order)

        val input = listOf(
            "str3","str0","str1"
        )

        val expectation = listOf(
            "str0","str1","str3"
        )

        assertEquals(expectation,input.sortedWith(comparator))
    }
}