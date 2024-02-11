package com.example.filterdatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filterdatedemo.ui.theme.FilterDateDemoTheme


val filterOptions = Data.getFilterOptions(Data.generateSemiMonthlyStatements())
fun changeChipChecked(item: FilterOption, checked: Boolean) =
    filterOptions.find { it.option == item.option }?.let { task ->
        task.selected = checked
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilterDateDemoTheme {

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        // show filter options
                        FilterOptions(filterOptions)

                        FilterList(filterOptions)
                    }
                }
            }
        }
    }
}


@Composable
fun FilterOptions(filterOptions: List<FilterOption>) {
    LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
        items(filterOptions) { option ->
            FilterChip(
                filterOption = option,
                onChipClick = {
                    changeChipChecked(it, !it.selected)
                    Data.printList(com.example.filterdatedemo.filterOptions)
                }
            )
        }
    }
}

@Composable
fun FilterList(filterOption: List<FilterOption>) {
    val list = mutableListOf<Statement>()

    filterOption.forEach{
        if (it.selected){
            list += it.statement
        }
    }

    LazyColumn{
        items(list){
            Text(modifier = Modifier.fillMaxWidth(),text = it.displayDate)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(filterOption: FilterOption, onChipClick: (FilterOption) -> Unit) {

    FilterChip(
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    val filters = Data.getFilterOptions(Data.generateSemiMonthlyStatements())

    FilterDateDemoTheme {
        Column {
            FilterOptions(filterOptions)
        }
    }
}