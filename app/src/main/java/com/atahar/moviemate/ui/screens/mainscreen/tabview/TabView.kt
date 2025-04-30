package com.atahar.moviemate.ui.screens.mainscreen.tabview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.atahar.moviemate.R
import com.atahar.moviemate.navigation.Screen
import com.atahar.moviemate.utils.ACTIVE_MOVIE_TAB
import com.atahar.moviemate.utils.ACTIVE_TV_SERIES_TAB
import com.atahar.moviemate.utils.singleTopNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MovieTvSeriesTabView(
    navigator: NavHostController,
    pagerState: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(
        TabItem(
            title = stringResource(R.string.movie),
            icon = Icons.Filled.Movie
        ),
        TabItem(
            title = stringResource(R.string.tv_series),
            icon = Icons.Filled.LiveTv
        )
    )

    TabRow(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp),
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .clip(MaterialTheme.shapes.small)
                    .padding(horizontal = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                height = 3.dp
            )
        },
        divider = {
            Divider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                thickness = 1.dp
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { handleTabClick(navigator, pagerState, coroutineScope) },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = tab.title,
                            modifier = Modifier.size(20.dp),
                            tint = if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            }
                        )
                        Text(
                            text = tab.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = if (pagerState.currentPage == index) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Normal
                                }
                            ),
                            color = if (pagerState.currentPage == index) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            }
                        )
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        handleTabClick(navigator, pagerState, coroutineScope)
    }
}

private data class TabItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private fun handleTabClick(
    navigator: NavHostController,
    pagerState: PagerState,
    coroutineScope: CoroutineScope
) {
    val index = pagerState.currentPage
    if (index == ACTIVE_MOVIE_TAB) {
        navigator.singleTopNavigator(Screen.NowPlayingMovie.route)
    } else if (index == ACTIVE_TV_SERIES_TAB) {
        navigator.singleTopNavigator(Screen.AiringTodayTvSeries.route)
    }
    coroutineScope.launch {
        pagerState.animateScrollToPage(index)
    }
}

@Composable
fun FavoriteTabView(navigator: NavHostController) {
}