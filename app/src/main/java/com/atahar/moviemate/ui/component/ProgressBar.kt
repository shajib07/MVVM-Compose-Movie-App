package com.atahar.moviemate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean, verticalBias: Float) {
    if (isDisplayed) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize().background(Color.Red.copy(alpha = 0.5f)),
        ) {
            val (progressBar) = createRefs()
            val topBias = createGuidelineFromTop(verticalBias)
            LinearProgressIndicator(
                modifier = Modifier.constrainAs(progressBar)
                {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
                color = Color.Red,
                trackColor = Color.Green,
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
            )
        }

    }
}