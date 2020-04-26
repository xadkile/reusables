package com.github.xadkile.reusables.geo

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TwoDTest {
    @Test
    fun create(){
        val twoD:TwoD<Double> = TwoD.create(10.2,200.3)
        assertEquals(10.2,twoD.getWidth())
        assertEquals(200.3,twoD.getLength())

        val twoDStr:TwoD<String> = TwoD.create("width","length")
        assertEquals("width", twoDStr.getWidth())
        assertEquals("length", twoDStr.getLength())
    }
}