package com.github.xadkile.reusables.location

data class BasicFromTo(private val fromId:String,private  val toId:String):FromTo<String>{
    constructor():this("","")

    override fun getFromId(): String {
        return this.fromId
    }

    override fun getToId(): String {
        return this.toId
    }
}