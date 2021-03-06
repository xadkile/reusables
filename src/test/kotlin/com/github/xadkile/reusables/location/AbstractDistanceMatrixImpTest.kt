package com.github.xadkile.reusables.location

import com.github.xadkile.reusables.location.distancematrix.DistanceMatrixBasic
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class AbstractDistanceMatrixImpTest {

    private val distanceMatrix =
        DistanceMatrixBasic(
            fromIndexList = listOf("loc1", "loc2", "loc3"),
            toIndexList = listOf("loc1", "loc2", "loc3"),
            distanceValue = listOf(
                listOf(0.0, 300.0, 400.0),
                listOf(101.0, 0.0, 501.0),
                listOf(402.0, 503.0, 0.0)
            )
        )

    @Test
    fun getDistance() {
        val distanceNull:Double? = distanceMatrix.getDistance("not exist 1","not exist 2")
        assertNull(distanceNull)
        val validDistance1: Double? = distanceMatrix.getDistance("loc1","loc2")
        assertNotNull(validDistance1)
        assertEquals(300.0,validDistance1,0.0)
    }

    @Test
    fun getDistance2(){
        val distanceSame:Double? = distanceMatrix.getDistance("loc1","loc1")
        assertNotNull(distanceSame)
        assertEquals(0.0,distanceSame,0.001)
        val distanceNull:Double? = distanceMatrix.getDistance("notExist","notExist")
        assertNull(distanceNull)
    }
}