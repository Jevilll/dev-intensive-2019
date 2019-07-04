package ru.skillbranch.devintensive.extensions

fun String.truncate(number: Int = 16): String {

    val result = this.trim()

    if (result.length > number) {
        return "${result.substring(0, number).trim()}..."
    }

    return result
}

fun String.stripHtml(): String =
    this.replace("<[^>]*>".toRegex(),"")
        .replace("&.*?;".toRegex(), "")
        .replace("\\s+".toRegex(), " ")