package com.example.filterdatedemo

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filterdatedemo.ui.theme.FilterDateDemoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleSelectScreen() {

    var selectedFilter by remember { mutableStateOf(filterOptions.first()) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {

            // show filter options
            LazyRow(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 8.dp)) {
                items(filterOptions) { option ->
                    FilterChip(
                        selected = option == selectedFilter,
                        filterOption = option,
                        onChipClick = {
                            selectedFilter = it
                        }
                    )
                }
            }

            LazyColumn {
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    ) {
                        Text(
                            text = selectedFilter.option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                items(selectedFilter.statement) {
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
fun PreviewSingleSelect() {
    FilterDateDemoTheme {
        SingleSelectScreen()
    }
}