package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.round

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

const val S_45 = SECOND * 45
const val S_75 = SECOND * 75
const val M_45 = MINUTE * 45
const val M_75 = MINUTE * 75
const val H_22 = HOUR * 22
const val H_26 = HOUR * 26
const val Y = DAY * 365

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String =
    SimpleDateFormat(pattern, Locale("ru")).format(this)

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val diff = this.time - date.time

    return when (diff.abs) {
        in 0..SECOND -> "только что"
        in SECOND..S_45 -> getHumanizeTime(diff, "несколько секунд")
        in S_45..S_75 -> getHumanizeTime(diff, "минуту")
        in S_75..M_45 -> getHumanizeTime((roundDiff(diff, MINUTE)), minutesAsPlurals(roundDiff(diff.abs, MINUTE)))
        in M_45..M_75 -> "час назад"
        in M_75..H_22 -> getHumanizeTime(roundDiff(diff, HOUR), hoursAsPlurals(roundDiff(diff.abs, HOUR)))
        in H_22..H_26 -> "день назад"
        in H_26..Y -> getHumanizeTime(roundDiff(diff, DAY), daysAsPlurals(roundDiff(diff.abs, DAY)))
        else -> if (diff > 0) "более чем через год" else "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Long): String = when (this) {
        SECOND -> secondsAsPlurals(value)
        MINUTE -> minutesAsPlurals(value)
        HOUR -> hoursAsPlurals(value)
        DAY -> daysAsPlurals(value)
    }
}

fun getHumanizeTime(diff: Long, text: String): String =
    if (diff > 0) {
        "через $text"
    } else {
        "$text назад"
    }

fun roundDiff(diff: Long, time: Long): Long = round(diff.toFloat() / time).toLong()

enum class Plurals {
    ONE,
    FEW,
    MANY
}

val Long.asPlurals
    get() = when {
        this in 5L..20L -> Plurals.MANY
        this % 10L == 1L -> Plurals.ONE
        this % 10L in 2L..4L -> Plurals.FEW
        else -> Plurals.MANY
    }

val Long.abs
    get() = abs(this)

private fun secondsAsPlurals(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "$value секунду"
    Plurals.FEW -> "$value секунды"
    Plurals.MANY -> "$value секунд"
}

private fun minutesAsPlurals(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "$value минуту"
    Plurals.FEW -> "$value минуты"
    Plurals.MANY -> "$value минут"
}

private fun hoursAsPlurals(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "$value час"
    Plurals.FEW -> "$value часа"
    Plurals.MANY -> "$value часов"
}

private fun daysAsPlurals(value: Long) = when (value.asPlurals) {
    Plurals.ONE -> "$value день"
    Plurals.FEW -> "$value дня"
    Plurals.MANY -> "$value дней"
}