package com.example.sampleapp

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapp.ui.theme.colorBlue
import com.example.sampleapp.ui.theme.colorUnselectedTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyTabs() {
    val tabItems = listOf(
        TabItem(
            text = "Personal",
            icon = Icons.Default.Person
        ),
        TabItem(
            text = "KYC",
            icon = Icons.Default.Check
        )
    )
    val pagerState = rememberPagerState(pageCount = tabItems.size)
    val coroutineScope = rememberCoroutineScope()

    Column {

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .height(50.dp),
            indicator = {

            }
        ) {
            tabItems.forEachIndexed { index, tab ->

                val tabBackgroundColor = remember {
                    Animatable(Color.White)
                }

                val boxBackgroundColor = remember {
                    Animatable(colorUnselectedTab)
                }

                LaunchedEffect(pagerState.currentPage == index) {
                    tabBackgroundColor.animateTo(
                        if (pagerState.currentPage == index) colorUnselectedTab
                        else Color.White
                    )
                    boxBackgroundColor.animateTo(
                        if (pagerState.currentPage == index) Color.White
                        else colorUnselectedTab
                    )
                }

                Tab(
                    selected = pagerState.currentPage == index,
                    modifier = Modifier
//                        .clip(
//                            shape = if (pagerState.currentPage == index) RoundedCornerShape(
//                                topStart = 10.dp,
//                                bottomStart = 0.dp,
//                                topEnd = 10.dp,
//                                bottomEnd = 0.dp
//                            ) else RoundedCornerShape(
//                                topStart = 0.dp,
//                                bottomStart = 10.dp,
//                                topEnd = 0.dp,
//                                bottomEnd = 0.dp
//                            )
//                        )
                        .background(color = tabBackgroundColor.value),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }) {
                    Row(
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                shape = if (pagerState.currentPage == index) RoundedCornerShape(
                                    topStart = 10.dp,
                                    bottomStart = 0.dp,
                                    topEnd = 10.dp,
                                    bottomEnd = 0.dp
                                ) else RoundedCornerShape(
                                    topStart = 0.dp,
                                    bottomStart = 10.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 0.dp
                                )
                            )
                            .background(color = boxBackgroundColor.value)
                    ) {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = null,
                            tint = colorBlue
                        )
                        Text(
                            text = tab.text,
                            style = if (pagerState.currentPage == index)
                                TextStyle(color = colorBlue, fontSize = 11.sp)
                            else TextStyle(color = Color.Black, fontSize = 11.sp)
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) { page ->
            Text(
                text = tabItems[page].text,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 50.dp),
                color = MaterialTheme.colors.primary
            )
        }
    }
}

data class TabItem(
    val text: String,
    val icon: ImageVector
)