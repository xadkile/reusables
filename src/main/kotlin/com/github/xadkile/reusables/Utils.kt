package com.github.xadkile.reusables

/**
 * extract all subsets of a [pairList], that meet this requirement:
 *  - element of each pair in the subset contains unique values that do not appear in other pairs in the subset.
 */
fun <T:Comparable<T>> extractAllUniqueSubset(pairList:List<Pair<T,T>>):List<List<Pair<T,T>>>{
    return pairList.map{firstElement:Pair<T,T> ->
        val subset:List<Pair<T,T>> = (extractSubset(firstElement, pairList))
        subset + firstElement
    }
}

/**
 * Extract subset from [pairList] that do not contain any value of [startingElement]
 */
fun <T:Comparable<T>> extractSubset (startingElement: Pair<T, T>,
                                     pairList: List<Pair<T, T>>): List<Pair<T, T>> {
    val excludeStartingElement: List<Pair<T, T>> = pairList.filter {
        it.first != startingElement.first
                && it.first != startingElement.second
                && it.second != startingElement.first
                && it.second != startingElement.second
    }
    return if (excludeStartingElement.isEmpty()) {
        excludeStartingElement
    } else {
        extractSubset(excludeStartingElement[0],
            excludeStartingElement - excludeStartingElement[0]) + excludeStartingElement[0]
    }
}

/**
 * create all possible pairs from a List
 */
fun <T> makePairs(lst:List<T>):List<Pair<T,T>>{
    val re = mutableListOf<Pair<T,T>>()
    for(x in lst.indices){
        for(y in x until lst.size){
            re.add(Pair(lst[x],lst[y]))
        }
    }
    return re
}