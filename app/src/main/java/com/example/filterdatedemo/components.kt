package com.example.filterdatedemo

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.filterdatedemo.data.FilterOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(filterOption: FilterOption, onChipClick: (FilterOption) -> Unit) {

    androidx.compose.material3.FilterChip(
        onClick = { onChipClick(filterOption) },
        label = {
            Text(filterOption.option)
        },
        selected = filterOption.selected,
        leadingIcon = if (filterOption.selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}