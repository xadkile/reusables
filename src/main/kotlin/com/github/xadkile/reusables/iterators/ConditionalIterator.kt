package com.github.xadkile.reusables.iterators

/**
 * An iterator that select the next element base on condition checked by [initSelector]
 * @param selector initial selector
 * @property lst the list of item
 * @property currentElement the current element
 * @property usedIndices indices of items that have been consumed by [next]
 * @property cachedNextValue a cached value to store the next value to be returned on the next [next] call
 * WARNING: this class is not thread safe, should only be used on a single thread
 */
class ConditionalIterator<T>(selector: CarriedOverSelector<T>, private var lst: List<T>) : Iterator<T> {

    private var currentElement: IndexedValue<T>? = null
    private val usedIndices = mutableListOf<Int>()
    private var cachedNextValue: IndexedValue<T>? = null
    private var iterSelector = selector

    override fun next(): T {
        if (this.hasNext()) {
            val nextValue: IndexedValue<T>? = this.cachedNextValue
            if (nextValue != null) {
                this.currentElement = nextValue
                this.usedIndices.add(nextValue.index)
                this.iterSelector = this.iterSelector.carryOver(nextValue.value)
                return nextValue.value
            } else {
                throw Exception("does not has next element")
            }
        } else {
            throw Exception("does not has next element")
        }
    }

    override fun hasNext(): Boolean {
        val currentIndex: Int? = this.currentElement?.index
        val filteredList: List<IndexedValue<T>> = this.getFilteredList().filter { (index: Int, _: T) -> index != currentIndex }
        val nextValue: IndexedValue<T>? = try {
            filteredList.first { this.iterSelector.elementCondition(it.value) }
        } catch (e: NoSuchElementException) {
            null
        }
        this.cachedNextValue = nextValue
        return nextValue != null
    }

    /**
     *
     */
    private fun getFilteredList(): List<IndexedValue<T>> {
        return this.lst.withIndex().filter { (index, _) -> this.usedIndices.contains(index).not() }
    }

    /**
     * Get the unused, left over item of list iterated over by this iterator
     */
    fun getTheRest(): List<T> {
        return this.lst.withIndex().filter {
            this.usedIndices.contains(it.index).not()
        }.map { it.value }
    }
}
