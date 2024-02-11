package com.example.filterdatedemo.data

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

data class Statement(val id: String, val date: Date){
    private val format: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    private val localDate: LocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val displayDate: String = localDate.format(format)
}
