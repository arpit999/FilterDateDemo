package com.example.filterdatedemo

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Immutable
data class FilterOption(val option: String, val statement: List<Statement>, val checked: Boolean = false){
    var selected by mutableStateOf(checked)
}
