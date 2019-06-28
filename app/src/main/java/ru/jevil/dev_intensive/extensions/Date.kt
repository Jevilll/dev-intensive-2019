package ru.jevil.dev_intensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

const val JUST_MINUTE = 60
const val MINUTES_45 = MINUTE * 45 / SECOND
const val MINUTES_75 = MINUTE * 75 / SECOND
const val HOURS_22 = HOUR * 22 / SECOND
const val HOURS_26 = HOUR * 26 / SECOND
const val YEAR = DAY * 360 / SECOND


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    val t = this.time

    val s = value * SECOND

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

    val curFor = Date().format()
    val outFor = date.format()

    val currentTime = Date().time

    val diffSeconds = (if (currentTime > date.time) currentTime - date.time else date.time - currentTime) / SECOND


    return when(diffSeconds) {
        in 0..1 -> TimeHumanize.JUST_NOW.text
        in 1..45 -> TimeHumanize.FEW_SECONDS_AGO.text
        in 45..75 -> TimeHumanize.MINUTE_AGO.text
        in 75..MINUTES_45 -> "${diffSeconds / 60} ${TimeHumanize.N_MINUTES_AGE.text}"
        in MINUTES_45..MINUTES_75 -> TimeHumanize.HOUR_AGO.text
        in MINUTES_75..HOURS_22 -> "${diffSeconds / 60 / 60} ${TimeHumanize.N_HOURS_AGO.text}"
        in HOURS_22..HOURS_26 -> TimeHumanize.DAY_AGO.text
        in HOURS_26..YEAR -> "${diffSeconds / 60 / 60 / 60} ${TimeHumanize.N_DAYS_AGO.text}"
        else -> TimeHumanize.OVER_A_YEAR_AGO.text
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

enum class TimeHumanize(val text: String) {
    JUST_NOW("только что"),                    //0с - 1с
    FEW_SECONDS_AGO("несколько секунд назад"), //1с - 45с
    MINUTE_AGO("минуту назад"),                //45с - 75с
    N_MINUTES_AGE(" минут назад"),             //75с - 45мин
    HOUR_AGO("час назад"),                     //45мин - 75мин
    N_HOURS_AGO(" часов назад"),               //75мин 22ч
    DAY_AGO("день назад"),                     //22ч - 26ч
    N_DAYS_AGO(" дней назад"),                 //26ч - 360д
    OVER_A_YEAR_AGO("более года назад")        //>360д
}