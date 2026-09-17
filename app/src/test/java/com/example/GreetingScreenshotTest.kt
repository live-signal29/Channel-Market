package com.example

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import com.example.data.ListingEntity
import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus
import com.example.ui.components.ListingCard
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun greeting_screenshot() {
    val sampleListing = ListingEntity(
      id = 1,
      title = "Crypto VIP Trading Signals",
      platform = Platform.TELEGRAM.name,
      category = ItemCategory.CHANNEL.name,
      privacy = PrivacyType.PUBLIC.name,
      tonAdsStatus = TonAdsStatus.ACTIVE.name,
      isVerified = true,
      isPremium = true,
      handleOrLink = "@cryptovip_signals",
      membersCount = 58400,
      price = 850.0,
      currency = "USDT",
      description = "Top rated crypto trading signals channel.",
      niche = "Crypto & Trading",
      monthlyIncome = "$420/mo",
      sellerTelegram = "@CryptoBoss",
      sellerWhatsApp = "+923001122334",
      isFeatured = true,
      isApproved = true,
      isSold = false,
      isFavorite = false
    )

    composeTestRule.setContent {
      MyApplicationTheme {
        ListingCard(
          listing = sampleListing,
          onClick = {},
          onToggleFavorite = {},
          modifier = Modifier.padding(16.dp)
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }
}
