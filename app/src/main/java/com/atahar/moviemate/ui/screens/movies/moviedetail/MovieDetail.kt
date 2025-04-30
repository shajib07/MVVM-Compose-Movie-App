package com.atahar.moviemate.ui.screens.movies.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.atahar.moviemate.R
import com.atahar.moviemate.data.datasource.remote.ApiURL
import com.atahar.moviemate.data.model.MovieItem
import com.atahar.moviemate.data.model.artist.Cast
import com.atahar.moviemate.navigation.Screen
import com.atahar.moviemate.ui.component.CircularIndeterminateProgressBar
import com.atahar.moviemate.ui.component.ExpandingText
import com.atahar.moviemate.ui.component.text.SubtitlePrimary
import com.atahar.moviemate.ui.component.text.SubtitleSecondary
import com.atahar.moviemate.ui.theme.DefaultBackgroundColor
import com.atahar.moviemate.ui.theme.FontColor
import com.atahar.moviemate.ui.theme.SecondaryFontColor
import com.atahar.moviemate.utils.hourMinutes
import com.atahar.moviemate.utils.roundTo
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun MovieDetail(navController: NavController, movieId: Int) {

    val viewModel = hiltViewModel<MovieDetailViewModel>()

    val isLoading by viewModel.isLoading.collectAsState()
    val movieDetail by viewModel.movieDetail.collectAsState()
    val recommendedMovie by viewModel.recommendedMovie.collectAsState()
    val movieCredit by viewModel.movieCredit.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val calculatedOffset = screenHeight / 5.5f


    LaunchedEffect(Unit) {
        viewModel.movieDetail(movieId)
        viewModel.recommendedMovie(movieId)
        viewModel.movieCredit(movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultBackgroundColor)
    ) {
        CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.4f)

        movieDetail?.let { it ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .graphicsLayer {
                                alpha = 0.9f
                                scaleX = 1f
                                scaleY = 1f
                                translationX = 0f
                                translationY = 0f
                                shadowElevation = 10f
                                renderEffect = BlurEffect(8f, 8f)
                            },
                        imageModel = { ApiURL.IMAGE_URL_V2.plus(it.backdrop_path) },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            contentDescription = "Backdrop Image",
                        ),
                        component = rememberImageComponent {
                            +CircularRevealPlugin(duration = 800)
                            +ShimmerPlugin(
                                shimmer = Shimmer.Flash(
                                    baseColor = SecondaryFontColor,
                                    highlightColor = DefaultBackgroundColor
                                )
                            )
                        },
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = calculatedOffset)
                            .padding(start = 10.dp)
                    ) {
                        CoilImage(
                            modifier = Modifier
                                .size(135.dp, 180.dp) // Poster size (width x height)
                                .clip(RoundedCornerShape(10.dp))
                                .border(
                                    1.dp, Color.White, RoundedCornerShape(10.dp)
                                ),
                            imageModel = { ApiURL.IMAGE_URL.plus(it.poster_path) },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop,
                                contentDescription = "Poster Image",
                            ),
                            component = rememberImageComponent {
                                +ShimmerPlugin(
                                    shimmer = Shimmer.Flash(
                                        baseColor = SecondaryFontColor,
                                        highlightColor = DefaultBackgroundColor
                                    )
                                )
                            },
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .fillMaxWidth()
                                .align(Alignment.Bottom),
                        ) {
                            Text(
                                text = it.title, color = Color.Black, fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(
                                        text = stringResource(R.string.duration)
                                    )
                                    SubtitleSecondary(
                                        text = it.runtime.hourMinutes()
                                    )
                                }
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(
                                        text = stringResource(R.string.release_date)
                                    )
                                    SubtitleSecondary(
                                        text = it.release_date
                                    )
                                }
                            }
                            Row(modifier = Modifier.padding(top = 4.dp)) {
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(
                                        text = stringResource(R.string.language)
                                    )
                                    SubtitleSecondary(
                                        text = it.original_language
                                    )
                                }
                                Column(Modifier.weight(1f)) {
                                    SubtitlePrimary(
                                        text = stringResource(R.string.rating)
                                    )
                                    SubtitleSecondary(
                                        text = it.vote_average.roundTo(1).toString()
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 115.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.description),
                        color = FontColor,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    ExpandingText(text = it.overview, visibleLines = 3)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

