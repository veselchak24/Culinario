package com.culinario.helpers

private val secondsInYear = 31104000
private val secondsInMonth = 2592000
private val secondsInDay = 86400
private val secondsInHour = 3600
private val secondsInMinute = 60

fun timeSince(timeStampMilliseconds: Long): String {
    return timeSince((timeStampMilliseconds / 1000).toInt())
}

fun timeSince(timeStampSeconds: Int): String {
    val currentTime = System.currentTimeMillis() / 1000
    val timeSince = currentTime - timeStampSeconds

    if (timeSince > secondsInYear) {
        val years = (timeSince / secondsInYear).toInt()

        return when(years % 10) {
            1 -> "$years год назад"
            in 2..4 -> "$years года назад"
            else -> "$years лет назад"
        }
    } else if (timeSince > secondsInMonth) {
        val months = (timeSince / secondsInMonth).toInt()

        return when(months) {
            1 -> "$months месяц назад"
            in 2..4 -> "$months месяца назад"
            else -> "$months месяцев назад"
        }
    } else if(timeSince > secondsInDay) {
        val days = (timeSince / secondsInDay).toInt()

        return when(days % 10) {
            1 -> "$days день назад"
            in 2..4 -> "$days дня назад"
            else -> "$days дней назад"
        }
    } else if(timeSince > secondsInHour) {
        val hours = (timeSince / secondsInHour).toInt()

        return when(hours % 10) {
            1 -> "$hours час назад"
            in 2..4 -> "$hours часа назад"
            else -> "$hours часов назад"
        }
    } else if (timeSince > secondsInMinute) {
        val minutes = (timeSince / secondsInMinute).toInt()

        return when(minutes % 10) {
            1 -> "$minutes минута назад"
            in 2..4 -> "$minutes минуты назад"
            else -> "$minutes минут назад"
        }
    }

    return "только что"
}

