package ru.jevil.dev_intensive.extensions

import ru.jevil.dev_intensive.models.User
import ru.jevil.dev_intensive.models.UserView
import ru.jevil.dev_intensive.utils.Utils

fun User.toUserView(): UserView {

    val nickname = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if (lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit.humanizeDiff()}"

            return UserView(
                    id,
                    fullName = "$firstName $lastName",
                    nickname = nickname,
                    initials = initials,
                    avatar = avatar,
                    status = status

            )
}
