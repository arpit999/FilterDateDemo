package com.example.filterdatedemo

import java.util.Calendar
import java.util.Date

object Data {

    fun getYearToStatementsMap(statements: List<Statement>): List<Pair<String, List<Statement>>> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val uniqueYears = statements.filter { it.date.year + 1900 != currentYear }.map { it.date.year + 1900 }.distinct().sortedDescending()

        return uniqueYears.map { year ->
            year.toString() to statements.filter { it.date.year + 1900 == year }
                .sortedByDescending { it.date }
        }
    }

    // Function to get the last 12 months statements
    fun getLast12MonthsStatements(statements: List<Statement>): List<Statement> {
        val currentDate = Date()
        val last12MonthsStart = Calendar.getInstance().apply { add(Calendar.MONTH, -12) }.time
        return statements.filter { it.date >= last12MonthsStart && it.date <= currentDate }.sortedByDescending { it.date }
    }

    fun generateSemiMonthlyStatements(): List<Statement> {
        val statements = mutableListOf<Statement>()
        val currentDate = Date()
        val calendar = Calendar.getInstance()

        // Generate statements for each month in the last 3 years
        for (year in (currentDate.year - 3 + 1900)..(currentDate.year + 1900)) {
            for (month in 0 until 12) {
                calendar.set(year, month, 1)
                val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                // Generate statements for the 1st and 15th day of each month
                for (day in listOf(1, 15)) {
                    if (day <= daysInMonth) {
                        val statementDate = Date(year - 1900, month, day)
                        val id = "Statement_${statements.size + 1}"
                        statements.add(Statement(id, statementDate))
                    }
                }
            }
        }

        println("Generated semi-monthly statements for the last 20 years:")
        statements.forEach { println("Statement ID: ${it.id}, Date: ${it.date}") }

        return statements
    }

}