package com.github.xadkile.reusables.geo

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ThreeDTest {
    @Test
    fun create(){
        val threeD:ThreeD<Double> = ThreeD.create(10.2,200.3,23.3)
        assertEquals(10.2,threeD.getWidth())
        assertEquals(200.3,threeD.getLength())
        assertEquals(23.3,threeD.getHeight())

        val threeDStr:ThreeD<String> = ThreeD.create("width","length","height")
        assertEquals("width", threeDStr.getWidth())
        assertEquals("length", threeDStr.getLength())
        assertEquals("height", threeDStr.getHeight())
    }
}