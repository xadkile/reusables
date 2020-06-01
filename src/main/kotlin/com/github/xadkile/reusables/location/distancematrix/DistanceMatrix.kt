package com.github.xadkile.reusables.location.distancematrix

import com.github.xadkile.reusables.location.FromTo

interface DistanceMatrix<ID,DISTANCE>{
    fun getDistance(fromId:ID,toId: ID):DISTANCE?
    fun getDistance(fromTo: FromTo<ID>):DISTANCE?
}
