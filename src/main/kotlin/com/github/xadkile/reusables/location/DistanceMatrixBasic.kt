package com.github.xadkile.reusables.location


/**
 * A basic implementation of [DistanceMatrix] interface using String as location identifications
 */
class DistanceMatrixBasic(
    private val fromIndexMap: Map<String, Int>,
    private val toIndexMap: Map<String, Int>,
    private val distanceValues: List<List<Double>>
) : DistanceMatrix<String, Double> {

    constructor(
        fromIndexList: List<String>,
        toIndexList: List<String>,
        distanceValue: List<List<Double>>
    ) :
            this(
                fromIndexList.mapIndexed { index, s -> Pair(s, index) }.toMap(),
                toIndexList.mapIndexed { index, s -> Pair(s, index) }.toMap(),
                distanceValue
            )

    constructor() : this(listOf(), listOf(), listOf())


    override fun getDistance(fromTo: FromTo<String>): Double? {
        return this.getDistance(fromTo.getFromId(), fromTo.getToId())
    }

    internal fun getDistance(fromIndex: Int, toIndex: Int): Double? {
        // from = index of list of distance value
        // to = index of distance value
        return try {
            distanceValues[fromIndex][toIndex]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    override fun getDistance(fromId: String, toId: String): Double? {
        return if (fromId == toId) {
            if (this.fromIndexMap.containsKey(fromId) || this.toIndexMap.containsKey(toId)) {
                0.0
            } else {
                null
            }
        } else {
            fromIndexMap[fromId]?.let { fromIndex ->
                toIndexMap[toId]?.let { toIndex ->
                    this.getDistance(fromIndex, toIndex)
                }
            }
        }
    }
}