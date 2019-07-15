package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SPECIAL -> Question.SPECIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val (validate, hint) = question.validate(answer)

        if (!validate) return "$hint\n${question.question}" to status.color

        if (question.answers.isEmpty()) return question.question to status.color

        if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            return "Отлично - это правльный ответ!\n${question.question}" to status.color
        } else {
            status = status.nextStatus()

            if (status == Status.CRITICAL) {
                status = Status.NORMAL
                question = Question.NAME

                return "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }

            return "Это неправильный ответ!\n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANCER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status = if (this.ordinal < values().lastIndex) {
            values()[this.ordinal + 1]
        } else {
            values()[0]
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun validate(answer: String): Pair<Boolean, String> = (answer.isBlank() || answer[0].isUpperCase()) to "Имя должно начинаться с заглавной буквы"

            override fun nextQuestion(): Question =
                PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun validate(answer: String): Pair<Boolean, String> = (answer.isBlank() || answer[0].isLowerCase()) to "Профессия должна начинаться со строчной буквы"
            override fun nextQuestion(): Question =
                MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun validate(answer: String): Pair<Boolean, String> = (!answer.any { it.isDigit() }) to "Материал не должен содержать цифр"

            override fun nextQuestion(): Question =
                BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validate(answer: String): Pair<Boolean, String> = (answer.toIntOrNull() != null) to "Год моего рождения должен содержать только цифры"
            override fun nextQuestion(): Question =
                SPECIAL
        },
        SPECIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validate(answer: String): Pair<Boolean, String>  = (answer.toIntOrNull() != null && answer.length == 7) to "Серийный номер содержит только цифры, и их 7"
            override fun nextQuestion(): Question =
                IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validate(answer: String): Pair<Boolean, String> = true to "Игнорируем"
            override fun nextQuestion(): Question =
                IDLE
        };


        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): Pair<Boolean, String>
    }
}