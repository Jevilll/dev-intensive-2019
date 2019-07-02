package ru.skillbranch.devintensive.models

class UserView(
        val id: String,
        val fullName: String,
        val nickname: String,
        var avatar: String? = null,
        var status: String? = "offline",
        var initials: String?
) {
    fun printMe() {
        println("""
            id:$id
            fullName:$fullName
            nickname:$nickname
            avatar: String?:$avatar
            status: String?:$status
            initials:$initials
        """.trimIndent())
    }
}