package `is`.uncommon.sanchita.incdecapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView

class MainActivity : AppCompatActivity() {

  companion object {
    const val DAYS = "days"
    const val WEEKS = "weeks"
    const val MONTHS = "months"
  }

  private var currentDateValue: Int = 0
  private lateinit var dateTextView: TextView
  private lateinit var incButton: Button
  private lateinit var decButton: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    dateTextView = findViewById(R.id.date)
    incButton = findViewById(R.id.plus)
    decButton = findViewById(R.id.minus)

    val incrementStream = RxView.clicks(incButton)
        .map { incrementDate() }
    val decrementStream = RxView.clicks(decButton)
        .map { decrementDate() }
    incrementStream.mergeWith(decrementStream).subscribe()

  }

  private fun incrementDate() {
    currentDateValue++
    val (displayDate, unit) = calculateDisplayDate()
    dateTextView.text = displayDate.toString() + unit
  }

  private fun decrementDate() {
    if (currentDateValue == 0) {
      dateTextView.text = "0days"
      return
    }
    currentDateValue--
    val (displayDate, unit) = calculateDisplayDate()
    dateTextView.text = displayDate.toString() + unit
  }

  private fun calculateDisplayDate(): Pair<Int, String> {
    var displayDate: Int
    var unit: String

    when {
      currentDateValue / 7 == 0 || currentDateValue == 7 -> {
        unit = DAYS
        displayDate = currentDateValue
      }
      currentDateValue / 7 == 1 -> {
        unit = WEEKS
        displayDate = currentDateValue % 7 + 1
      }
      else -> {
        unit = MONTHS
        displayDate = (currentDateValue - 14) + 2
      }
    }
    return Pair(displayDate, unit)
  }
}
