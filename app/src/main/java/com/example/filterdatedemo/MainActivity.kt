package com.example.filterdatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filterdatedemo.ui.theme.FilterDateDemoTheme


//val _itemList = mutableStateListOf<FilterOption>()
//val itemList: List<FilterOption> = _itemList
val _filterOptions = Data.getFilterOptions(Data.generateSemiMonthlyStatements()).toMutableStateList()
fun changeChipChecked(item: FilterOption, checked: Boolean) =
    _filterOptions.find { it.option == item.option }?.let { task ->
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
                        FilterOptions(_filterOptions)
                    }
                }
            }
        }
    }
}


@Composable
fun FilterOptions(filterOptions: List<FilterOption>) {
    LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
        itemsIndexed(filterOptions) { index, option ->
            FilterChip(
                filterOption = option,
                onChipClick = {

                    changeChipChecked(it, !it.selected)
//                    it[index] = _itemList[index].copy(enable = !it.checked)
                    Data.printList(_filterOptions)
                }
            )
        }
    }

//    Row {
//        filterOptions.forEachIndexed { index, filterOption ->
//            FilterChip(filterOption) {
//                _itemList[index] = _itemList[index].copy(enable = !it.enable)
//                Data.printList(itemList)
//            }
//        }
//    }

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
            FilterOptions(_filterOptions)
        }
    }
}