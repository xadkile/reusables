package com.github.xadkile.reusables.location

interface DistanceMatrix{
    fun getDistance(fromLocCode: String,toLocCode: String):Double?
    fun getDistance(fromIndex:Int,toIndex:Int):Double?
    fun getDistance(fromTo:FromTo):Double?
}

/**
 * An implementation of [DistanceMatrix] interface
 */
class DistanceMatrixImp(val fromIndexMap: Map<String, Int>,
                        val toIndexMap: Map<String, Int>,
                        val distanceValues: List<List<Double>>) : DistanceMatrix {
    override fun getDistance(fromTo: FromTo): Double? {
        return this.getDistance(fromTo.fromId,fromTo.toId)
    }

    constructor(fromIndexList: List<String>,
                toIndexList: List<String>,
                distanceValue: List<List<Double>>) :
            this(fromIndexList.mapIndexed { index, s -> Pair(s, index) }.toMap(),
                toIndexList.mapIndexed { index, s -> Pair(s, index) }.toMap(),
                distanceValue)

    constructor():this(listOf(), listOf(), listOf())

    override fun getDistance(fromIndex: Int, toIndex: Int): Double? {
        // from = index of list of distance value
        // to = index of distance value
        return try {
            distanceValues[fromIndex][toIndex]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    override fun getDistance(fromLocCode: String, toLocCode: String): Double? {
        return if(fromLocCode==toLocCode) {
            if(this.fromIndexMap.containsKey(fromLocCode) || this.toIndexMap.containsKey(toLocCode)){
                0.0
            }else{
                null
            }
        } else{
            fromIndexMap[fromLocCode]?.let { fromIndex ->
                toIndexMap[toLocCode]?.let { toIndex ->
                    this.getDistance(fromIndex, toIndex)
                }
            }
        }
    }
}