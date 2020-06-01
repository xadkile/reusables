package com.github.xadkile.reusables.location.distancematrix

import com.github.xadkile.reusables.location.FromTo

abstract class AbstractDistanceMatrixImp <ID:Comparable<ID>, D:Comparable<D>> (
    private val fromIndexMap: Map<ID, Int>,
    private val toIndexMap: Map<ID, Int>,
    private val distanceValues: List<List<D>>
) : DistanceMatrix<ID, D> {


    /**
     * return the default value for distance between two points with the same id
     */
    abstract fun getSameLocDistance():D

    override fun getDistance(fromId: ID, toId: ID): D? {
        return if (fromId == toId) {
            if (this.fromIndexMap.containsKey(fromId) || this.toIndexMap.containsKey(toId)) {
                this.getSameLocDistance()
            } else {
                null
            }
        } else {
            fromIndexMap[fromId]?.let { fromIndex ->
                toIndexMap[toId]?.let { toIndex ->
                    this.getDistanceWithIndex(fromIndex, toIndex)
                }
            }
        }
    }

    override fun getDistance(fromTo: FromTo<ID>): D? {
        return this.getDistance(fromTo.getFromId(),fromTo.getToId())
    }

    internal fun getDistanceWithIndex(fromIndex: Int, toIndex: Int): D? {
        // from = index of list of distance value
        // to = index of distance value
        return try {
            distanceValues[fromIndex][toIndex]
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }
}