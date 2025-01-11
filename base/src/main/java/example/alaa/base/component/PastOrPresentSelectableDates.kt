package example.alaa.base.component

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.time.LocalDate
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
object PastOrPresentSelectableDates: SelectableDates {
    @SuppressLint("NewApi")
    private val now = LocalDate.now()
    @SuppressLint("NewApi")
    private val dayStart = now.atTime(0, 0, 0, 0).toEpochSecond(ZoneOffset.UTC) * 1000

    @ExperimentalMaterial3Api
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis >= dayStart
    }

    @SuppressLint("NewApi")
    @ExperimentalMaterial3Api
    override fun isSelectableYear(year: Int): Boolean {
        return year == now.year
    }
}