package ru.jevil.devintensivetest.utils

object Utils {
    fun parsFullName(fullName: String?) : Pair<String?, String?> {

        //todo fix me
        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    public fun transliteration(payload: String, divider: String = " "): String {
        //todo
        return "transliteration"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "toInitials" //todo
    }
}