package com.example.filterdatedemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class FilterOption(val option: String, val statement: List<Statement>, initialSelection: Boolean = false) {
    var selected by mutableStateOf(initialSelection)
}
