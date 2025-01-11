package example.alaa.base.component.datepicker

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun convertMillisToDate(millis: Long? , pattern : String): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return millis?.let { Date(it) }?.let { formatter.format(it) }.orEmpty()
    }
}