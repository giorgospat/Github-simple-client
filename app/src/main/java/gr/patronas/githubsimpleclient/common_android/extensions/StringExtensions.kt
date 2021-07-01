package gr.patronas.githubsimpleclient.common_android.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String?.formatDate(
    fromDateFormat: String,
    toDateFormat: String
): String {

    if (this.isNullOrEmpty()) {
        return ""
    }
    try {
        val fromDate = formatDateWithSpecificPattern(fromDateFormat)
        fromDate?.let {
            val simpleDateFormat = SimpleDateFormat(toDateFormat, Locale.getDefault())
            simpleDateFormat.timeZone = TimeZone.getDefault()
            return simpleDateFormat.format(fromDate)
        } ?: run {
            return ""
        }
    } catch (exception: Exception) {
        return ""
    }
}

@Throws(ParseException::class)
private fun String.formatDateWithSpecificPattern(fromDateFormat: String): Date? {
    val simpleDateFormat = SimpleDateFormat(fromDateFormat, Locale.getDefault())
    return simpleDateFormat.parse(this)
}