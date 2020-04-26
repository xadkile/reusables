package com.github.xadkile.reusables.location

interface DistanceMatrix<ID,DISTANCE>{
    fun getDistance(fromId:ID,toId: ID):DISTANCE?
    fun getDistance(fromTo:FromTo<ID>):DISTANCE?
}
