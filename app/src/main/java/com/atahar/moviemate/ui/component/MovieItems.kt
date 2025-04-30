package com.atahar.moviemate.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.atahar.moviemate.data.datasource.remote.ApiURL
import com.atahar.moviemate.data.model.MovieItem
import com.atahar.moviemate.data.model.moviedetail.Genre
import com.atahar.moviemate.navigation.Screen
import com.atahar.moviemate.utils.pagingLoadingState

@Composable
fun MovieItems(
    navController: NavController,
    movieItems: LazyPagingItems<MovieItem>,
    genres: ArrayList<Genre>? = null,
    selectedName: Genre?,
    onClick: (genre: Genre) -> Unit,
) {
    val progressBar = remember { mutableStateOf(false) }

    Column {
        genres?.let {
            LazyRow(
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                )
            ) {
                items(genres) { item ->
                    SelectableGenreChip(
                        selected = item.name === selectedName?.name,
                        genre = item.name,
                        onclick = { onClick(item) }
                    )
                }
            }
        }

        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(movieItems.itemCount) { index ->
                movieItems[index]?.let {
                    MovieItemView(navController, it)
                }
            }
        }
    }

    movieItems.pagingLoadingState { isLoaded ->
        progressBar.value = isLoaded
    }

}

@Composable
fun MovieItemView(navController: NavController, item: MovieItem) {
    AsyncImage(
        model = ApiURL.IMAGE_URL.plus(item.posterPath),
        modifier = Modifier
            .size(250.dp)
            .clickable { navController.navigate(Screen.MovieDetail.route.plus("/${item.id}")) }
            .graphicsLayer(shape = RoundedCornerShape(10.dp), clip = true),
        contentScale = ContentScale.Crop,
        contentDescription = "Movie image"
    )
}
