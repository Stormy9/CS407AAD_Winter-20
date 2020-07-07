package com.example.stormytipcalculator


const val HUNDRED_PERCENT = 100.00


class StormysTipCalculator {

    fun calculatePerEachTotal(totalBill : Double, numberOfPeople : Int, tipPercent : Int) : Double {

        val totalExpense = ((HUNDRED_PERCENT + tipPercent) / HUNDRED_PERCENT) * totalBill

        val perEachTotal = totalExpense / numberOfPeople

        return perEachTotal

    }
}