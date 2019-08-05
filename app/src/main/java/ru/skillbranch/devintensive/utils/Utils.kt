package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.transliterate

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName
                ?.trim()
                ?.replace("\\s+".toRegex(), " ")
                ?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName?.ifEmpty { null } to lastName?.ifEmpty { null }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) return null

        val firstNameInitial = firstName?.getOrNull(0)?.toUpperCase()
        val lastNameInitial = lastName?.getOrNull(0)?.toUpperCase()

        return "${if (firstName.isNullOrBlank()) "" else firstNameInitial}" +
                "${if (lastName.isNullOrBlank()) "" else lastNameInitial}"
    }

    fun transliteration(payload: String, divider: String = " "): String {

        val result = StringBuilder()

        payload.forEach {
            if (it == ' ')
                result.append(divider) else
                result.append(it.transliterate())
        }

        return result.toString()
    }

    fun validateUrl(url: String): Boolean {
        val urlRegex = Regex("^(https://)?(www.)?github.com/(?!$exclude)(\\w*(-)?\\w{2,}[^/]$)")
        return urlRegex.containsMatchIn(url)
    }

    private const val exclude = "enterprise|features|topics|collections|trending|events|marketplace|pricing|nonprofit|customer-stories|security|login|join"
}



