package com.elfaidy.notes

fun main(){
    var arr: MutableList<Byte> = mutableListOf(1,2,3,3,5,4,6,9,8)
    println(arr[0])
    println(arr[4])
    arr.toByteArray()
    println(arr[arr.size-1])
}