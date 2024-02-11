package com.example.filterdatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filterdatedemo.data.DummyData
import com.example.filterdatedemo.data.FilterOption
import com.example.filterdatedemo.ui.theme.FilterDateDemoTheme


val filterOptions = DummyData.getFilterOptions(DummyData.generateSemiMonthlyStatements())
fun chipChangeListener(item: FilterOption, checked: Boolean) =
    filterOptions.find { it.option == item.option }?.let { task ->
        task.selected = checked
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilterDateDemoTheme {
//                MultiSelectScreen()

                SingleSelectScreen()
            }
        }
    }
}

@Composable
fun MultiSelectScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            // show filter options
            FilterOptions(filterOptions)

            FilterList(filterOptions)
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
                    chipChangeListener(it, !it.selected)
//                    Data.printList(filterOptions)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilterList(filterOption: List<FilterOption>) {

    LazyColumn {
        filterOption.forEach { option ->
            if (option.selected) {
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    ) {
                        Text(
                            text = option.option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                items(option.statement) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(), text = it.displayDate
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FilterDateDemoTheme {
        MultiSelectScreen()
    }
}