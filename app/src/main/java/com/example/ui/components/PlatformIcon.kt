package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Platform
import com.example.ui.theme.LightBorder
import com.example.ui.theme.LightSurface
import com.example.ui.theme.LightTextPrimary
import com.example.ui.theme.LightTextSecondary
import com.example.ui.theme.MarketPrimary

@Composable
fun PlatformIconBadge(
    platform: Platform,
    size: Dp = 40.dp,
    modifier: Modifier = Modifier
) {
    val drawableRes = when (platform) {
        Platform.TELEGRAM -> R.drawable.ic_social_telegram
        Platform.WHATSAPP -> R.drawable.ic_social_whatsapp
        Platform.TIKTOK -> R.drawable.ic_social_tiktok
        Platform.INSTAGRAM -> R.drawable.ic_social_instagram
        Platform.FACEBOOK -> R.drawable.ic_social_facebook
        Platform.YOUTUBE -> R.drawable.ic_social_youtube
        Platform.X_TWITTER -> R.drawable.ic_social_x_twitter
        Platform.DISCORD -> R.drawable.ic_social_discord
        Platform.OTHER -> null
    }

    if (drawableRes != null) {
        Icon(
            painter = painterResource(id = drawableRes),
            contentDescription = platform.displayName,
            tint = Color.Unspecified,
            modifier = modifier
                .size(size)
                .clip(CircleShape)
        )
    } else {
        Box(
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .background(Color(0xFF64748B)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = "Other",
                tint = Color.White,
                modifier = Modifier.size(size * 0.55f)
            )
        }
    }
}

/**
 * Top Categories Circular Item matching user's uploaded mockup:
 * Clean circular white button with official social logo + clean label
 */
@Composable
fun CategoryIconItem(
    platform: Platform?,
    title: String = platform?.displayName ?: "More",
    isSelected: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .shadow(elevation = if (isSelected) 4.dp else 1.dp, shape = CircleShape)
                .clip(CircleShape)
                .background(if (isSelected) MarketPrimary.copy(alpha = 0.1f) else LightSurface)
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) MarketPrimary else LightBorder,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (platform != null) {
                PlatformIconBadge(platform = platform, size = 36.dp)
            } else {
                // "+ More" button as in the image mockup
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF334155)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "More",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSelected) MarketPrimary else LightTextPrimary,
            maxLines = 1
        )
    }
}

