package com.example.gachimaster.data

class Task {
    var id: Long = 0
    var name: String = ""
    var favorit: Boolean = false
    var isDone: Boolean = false
    var zametka: String = ""
    var underTasckList: ArrayList<SmallTask> = arrayListOf()

}