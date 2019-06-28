package ru.jevil.dev_intensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

public fun Date.humanizeDiff(date: Date = Date()): String {
    //todo // сделать
    return "humanizeDiff"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}