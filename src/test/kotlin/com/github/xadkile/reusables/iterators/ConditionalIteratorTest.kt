package com.github.xadkile.reusables.iterators

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class ConditionalIteratorTest {

    @Test
    fun hasNext() {
        val list = listOf(1,2,3)
        val iterator1 = ConditionalIterator(object : CarriedOverSelector<Int> {
            override fun carryOver(newValue: Int): CarriedOverSelector<Int> {
                return this
            }

            override fun elementCondition(e: Int): Boolean {
                return e > 4
            }
        }, list)
        assertFalse(iterator1.hasNext())

        val iterator2 = ConditionalIterator(object : CarriedOverSelector<Int> {
            override fun carryOver(newValue: Int): CarriedOverSelector<Int> {
                return this
            }

            override fun elementCondition(e: Int): Boolean {
                return e >= 2
            }
        }, list)
        Assertions.assertTrue(iterator2.hasNext())
    }

    @Test
    fun next_1() {
        /**
         * select even items
         */
        class NNES2 : CarriedOverSelector<Double> {
            override fun carryOver(newValue: Double): CarriedOverSelector<Double> {
                return this
            }
            override fun elementCondition(e: Double): Boolean {
                return e.toInt() % 2 == 0
            }
        }

        val llst = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 9.0)
        val iterator = ConditionalIterator(NNES2(), llst)
        val result = mutableListOf<Double>()
        val expectation = listOf(2.0, 4.0, 6.0)
        while (iterator.hasNext()) {
            result.add(iterator.next())
        }
        Assertions.assertEquals(expectation, result)
        assertFalse(iterator.hasNext())
    }


    /**
     * select item that is one more of the previous value, output is carried over as input of the next iteration
     */
    @Test
    fun next_2() {

        /**
         * select element that is 1 or equal the carried over value
         */
        class NNS3(val vv: Double) : CarriedOverSelector<Double> {
            override fun carryOver(newValue: Double): CarriedOverSelector<Double> {
                return NNS3(newValue)
            }
            override fun elementCondition(e: Double): Boolean {
                return e == this.vv + 1 || e == vv
            }
        }

        val llst = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 9.0)
        val initValue = 2.0
        val iterator: ConditionalIterator<Double> = ConditionalIterator(NNS3(initValue), llst)

        val result = mutableListOf<Double>()
        val expectation = listOf(2.0, 3.0, 4.0, 5.0, 6.0)
        while (iterator.hasNext()) {
            val vv: Double = iterator.next()
            result.add(vv)
        }
        Assertions.assertEquals(expectation, result)
        assertFalse(iterator.hasNext())
    }
}