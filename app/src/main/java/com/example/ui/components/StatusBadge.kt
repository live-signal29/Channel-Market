package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus
import com.example.ui.theme.DangerRed
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkTextMuted
import com.example.ui.theme.PremiumPurple
import com.example.ui.theme.TelegramCyan
import com.example.ui.theme.TonGold
import com.example.ui.theme.VerifiedBadgeBlue
import com.example.ui.theme.WhatsAppGreen

@Composable
fun TagChip(
    text: String,
    icon: ImageVector? = null,
    textColor: Color,
    bgColor: Color,
    borderColor: Color = Color.Transparent,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(bgColor, RoundedCornerShape(6.dp))
            .border(1.dp, borderColor, RoundedCornerShape(6.dp))
            .padding(horizontal = 7.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun TonAdsBadge(status: TonAdsStatus, modifier: Modifier = Modifier) {
    when (status) {
        TonAdsStatus.ACTIVE -> {
            TagChip(
                text = "TON Ads Active",
                icon = Icons.Default.Campaign,
                textColor = Color(0xFFB45309), // Dark Amber for crisp contrast
                bgColor = Color(0xFFFEF3C7),
                borderColor = Color(0xFFFCD34D),
                modifier = modifier
            )
        }
        TonAdsStatus.NOT_ACTIVE -> {
            TagChip(
                text = "TON Ads Off",
                icon = Icons.Default.Campaign,
                textColor = Color(0xFF475569),
                bgColor = Color(0xFFF1F5F9),
                borderColor = Color(0xFFCBD5E1),
                modifier = modifier
            )
        }
        TonAdsStatus.NONE -> Unit
    }
}

@Composable
fun PrivacyBadge(privacy: PrivacyType, modifier: Modifier = Modifier) {
    if (privacy == PrivacyType.PUBLIC) {
        TagChip(
            text = "Public",
            icon = Icons.Default.Public,
            textColor = Color(0xFF0369A1), // Deep sky blue
            bgColor = Color(0xFFE0F2FE),
            borderColor = Color(0xFFBAE6FD),
            modifier = modifier
        )
    } else {
        TagChip(
            text = "Private",
            icon = Icons.Default.Lock,
            textColor = Color(0xFF6B21A8), // Deep purple
            bgColor = Color(0xFFF3E8FF),
            borderColor = Color(0xFFDDD6FE),
            modifier = modifier
        )
    }
}

@Composable
fun VerifiedBadge(modifier: Modifier = Modifier) {
    TagChip(
        text = "Verified",
        icon = Icons.Default.CheckCircle,
        textColor = Color(0xFF1D4ED8), // Deep royal blue
        bgColor = Color(0xFFEFF6FF),
        borderColor = Color(0xFFBFDBFE),
        modifier = modifier
    )
}

@Composable
fun PremiumBadge(modifier: Modifier = Modifier) {
    TagChip(
        text = "Premium",
        icon = Icons.Default.Star,
        textColor = Color(0xFF7C3AED), // Deep violet
        bgColor = Color(0xFFF5F3FF),
        borderColor = Color(0xFFDDD6FE),
        modifier = modifier
    )
}

@Composable
fun EscrowGuaranteedBadge(modifier: Modifier = Modifier) {
    TagChip(
        text = "Escrow Safe",
        icon = Icons.Default.Security,
        textColor = Color(0xFF15803D), // Deep green
        bgColor = Color(0xFFDCFCE7),
        borderColor = Color(0xFF86EFAC),
        modifier = modifier
    )
}

@Composable
fun FeaturedBadge(modifier: Modifier = Modifier) {
    TagChip(
        text = "Featured",
        icon = Icons.Default.Star,
        textColor = Color(0xFF9D174D), // Deep Rose/Pink
        bgColor = Color(0xFFFCE7F3),
        borderColor = Color(0xFFF472B6),
        modifier = modifier
    )
}

@Composable
fun SoldBadge(modifier: Modifier = Modifier) {
    TagChip(
        text = "SOLD",
        textColor = Color(0xFF991B1B), // Deep red
        bgColor = Color(0xFFFEE2E2),
        borderColor = Color(0xFFFCA5A5),
        modifier = modifier
    )
}

