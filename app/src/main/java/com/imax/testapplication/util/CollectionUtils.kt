package com.imax.testapplication.util

object CollectionUtils {
    fun isEmpty(data: Collection<*>?): Boolean {
        return data == null || data.isEmpty()
    }

    fun isEmpty(data: Map<*, *>?): Boolean {
        return data == null || data.isEmpty()
    }

    fun isEmpty(data: Array<Any?>?): Boolean {
        return data == null || data.isEmpty()
    }

    fun isEmpty(data: Any?): Boolean {
        return data == null
    }

}