package com.example.stormytipcalculator

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


// declare constants --
// // note all-caps convention + `val` + outside of MainActivity()
const val TIP_INCREMENT_PERCENT = 1
const val PEOPLE_INCREMENT_VALUE = 1
const val MIN_TIP = 0
const val MIN_PEOPLE = 1
const val MAX_TIP = 95
const val MAX_PEOPLE = 25


// note the stuff after `AppCompatActivity()` we added for this app
class MainActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {

    // declare variables --
    // // note regular case + `var` + inside MainActivity() [private]
    private var numberOfPeople = 1
    private var tipPercent = 20

    //===================================================================================
    // this comes with -- built in..... explained in video Thursday Week 01
    override fun onCreate(savedInstanceState: Bundle?) {

        // this also comes with   [=
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // set listeners for our buttons
        // // names are the `id` from activity_main.xml
        btnTipPlusPercent.setOnClickListener(this)
        btnTipMinusPercent.setOnClickListener(this)

        btnTipPlusPeople.setOnClickListener(this)
        btnTipMinusPeople.setOnClickListener(this)

        txtInputBillTotal.addTextChangedListener(this)

    }       // end `fun onCreate()`
    //===================================================================================
    //
    // still inside `MainActivity()` -- but NOT in `onCreate()`

    private fun calculateExpense() {

        val totalBill = txtInputBillTotal.text.toString().toDouble()

        val totalPerPerson : Double  = StormysTipCalculator().calculatePerEachTotal(totalBill, numberOfPeople, tipPercent)

        txtOutput_AmtPerPerson.text = String.format("$%.2f", totalPerPerson)
    }
    //-----------------------------------------------------------------------------------
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTipPlusPercent -> incrementTip()
            R.id.btnTipMinusPercent -> decrementTip()

            R.id.btnTipPlusPeople -> incrementPeople()
            R.id.btnTipMinusPeople -> decrementPeople()
        }
    }
    //-----------------------------------------------------------------------------------
    private fun incrementTip() {
        if (tipPercent != MAX_TIP) {
            tipPercent += TIP_INCREMENT_PERCENT

            txtTipCurrentTipPercent.text = String.format("%d%%", tipPercent)
        }
        calculateExpense()
    }
    //-----------------------------------------------------------------------------------
    private fun decrementTip() {
        if (tipPercent != MIN_TIP) {
            tipPercent -= TIP_INCREMENT_PERCENT

            txtTipCurrentTipPercent.text = String.format("%d%%", tipPercent)
        }
        calculateExpense()
    }
    //-----------------------------------------------------------------------------------
    private fun incrementPeople() {
        if (numberOfPeople != MAX_PEOPLE) {
            numberOfPeople += PEOPLE_INCREMENT_VALUE

            txtTipCurrentHowMany.text = numberOfPeople.toString()
        }
        calculateExpense()
    }
    //-----------------------------------------------------------------------------------
    private fun decrementPeople() {
        if (numberOfPeople != MIN_PEOPLE) {
            numberOfPeople -= PEOPLE_INCREMENT_VALUE

            txtTipCurrentHowMany.text = numberOfPeople.toString()
        }
        calculateExpense()
    }
    //===================================================================================
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (txtOutput_AmtPerPerson.text!!.isNotEmpty()) {
            calculateExpense()
        }
    }
    override fun afterTextChanged(s: Editable?) { }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    //===================================================================================
}
