package com.github.xadkile.reusables.location.distancematrix


/**
 * A basic implementation of [DistanceMatrix] interface using String as location identifications and Double as distance measurement
 */
class DistanceMatrixBasic(
     fromIndexMap: Map<String, Int>,
     toIndexMap: Map<String, Int>,
     distanceValues: List<List<Double>>
) : AbstractDistanceMatrixImp<String, Double>(fromIndexMap,toIndexMap,distanceValues) {

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

    override fun getSameLocDistance(): Double {
        return 0.0
    }
}