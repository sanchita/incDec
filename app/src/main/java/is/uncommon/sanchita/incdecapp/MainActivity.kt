package `is`.uncommon.sanchita.incdecapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  private var currentDateValue: Int = 1
  private var displayDate: Int = 0
  private var unit: String = "days"

  private lateinit var dateTextView: TextView
  private lateinit var incButton: Button
  private lateinit var decButton: Button


  private val DAYS = "days"
  private val WEEKS = "weeks"
  private val MONTHS = "months"

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    dateTextView = findViewById(R.id.date)
    incButton = findViewById(R.id.plus)
    decButton = findViewById(R.id.minus)

    incButton.setOnClickListener{
      incrementDate()
    }

    decButton.setOnClickListener{
      decrementDate()
    }

  }

  private fun incrementDate(){

    if(currentDateValue / 7 == 0 || currentDateValue == 7){
      unit = DAYS
      displayDate = (currentDateValue)
    } else if(currentDateValue / 7 == 1){
      unit = WEEKS
      displayDate = currentDateValue % 7 + 1
    } else {
      unit = MONTHS
      displayDate = (currentDateValue - 14) + 2
    }
    currentDateValue++
    dateTextView.text = displayDate.toString() + unit
  }


  private fun decrementDate(){

    if(currentDateValue == 1){
      dateTextView.text = "0days"
      displayDate = 0
      return
    }
    if(currentDateValue / 7 == 0 || currentDateValue == 7){
      unit = DAYS
      displayDate = (currentDateValue) % 7 - 2
    } else if(currentDateValue / 7 == 1){
      unit = WEEKS
      displayDate = currentDateValue % 7 - 1
    } else {
      unit = MONTHS
      displayDate = (currentDateValue - 14) + 2
    }
    currentDateValue--
    dateTextView.text = displayDate.toString() + unit



  }

}
