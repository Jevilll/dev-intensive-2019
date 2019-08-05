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

fun String.validateUrl(): Boolean {
    val urlRegex = Regex("^(https://)?(www.)?github.com/(?!$URI_EXCLUDE)(\\w*(-)?\\w{2,}[^/]$)")
    return this.isEmpty() || urlRegex.containsMatchIn(this)
}

private const val URI_EXCLUDE = "enterprise|features|topics|collections|trending|events|marketplace|pricing|nonprofit|customer-stories|security|login|join"