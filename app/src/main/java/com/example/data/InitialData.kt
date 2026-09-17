package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object InitialData {

    private fun item(
        id: Long,
        title: String,
        platform: Platform,
        category: ItemCategory,
        privacy: PrivacyType,
        tonAdsStatus: TonAdsStatus,
        isVerified: Boolean = false,
        isPremium: Boolean = false,
        handleOrLink: String,
        membersCount: Int,
        price: Double,
        currency: String = "USDT",
        niche: String,
        monthlyIncome: String = "",
        description: String,
        sellerTelegram: String = "@MarketSeller_TG",
        sellerWhatsApp: String = "+13125550144",
        isFeatured: Boolean = false,
        viewsCount: Int = 250,
        hoursAgo: Long = 1
    ): ListingEntity {
        val now = System.currentTimeMillis()
        val finalPrice = PriceAdjuster.adjust(
            category = category,
            platform = platform,
            tonAdsStatus = tonAdsStatus,
            isVerified = isVerified,
            isPremium = isPremium,
            rawPrice = price,
            membersCount = membersCount
        )
        return ListingEntity(
            id = id,
            title = title,
            platform = platform.name,
            category = category.name,
            privacy = privacy.name,
            tonAdsStatus = tonAdsStatus.name,
            isVerified = isVerified,
            isPremium = isPremium,
            handleOrLink = handleOrLink,
            membersCount = membersCount,
            price = finalPrice,
            currency = currency,
            description = description,
            niche = niche,
            monthlyIncome = monthlyIncome,
            sellerTelegram = sellerTelegram,
            sellerWhatsApp = sellerWhatsApp,
            isFeatured = isFeatured,
            isApproved = true,
            isSold = false,
            viewsCount = viewsCount,
            createdAt = now - (hoursAgo * 3600_000L)
        )
    }

    fun getInitialListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 1L

        // =========================================================================
        // SECTION 1: 40 TELEGRAM CHANNELS (Real channels, all 4 subcategories)
        // =========================================================================

        // --- SUB 1: Public + TON Ads ACTIVE (10 Channels) ---
        list.add(item(
            id = id++,
            title = "CoinDesk & Crypto Alerts",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@coindesk_alerts",
            membersCount = 240000,
            price = 420.0,
            niche = "Crypto & Trading",
            monthlyIncome = "$380/mo (TON Ads)",
            description = "Established Telegram crypto news and alert channel with 100% organic growth. Active TON Ads daily payouts directly to Fragment wallet. Full primary ownership transfer.",
            sellerTelegram = "@CryptoWhaleBroker",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "TechCrunch Daily Digest",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@techcrunch_daily",
            membersCount = 185000,
            price = 350.0,
            niche = "Tech & AI",
            monthlyIncome = "$290/mo (TON Ads)",
            description = "High engagement technology and venture capital news channel. Steady organic search discovery and daily TON Ads revenue. Clean history, no bot strikes.",
            sellerTelegram = "@TechMediaVendor"
        ))

        list.add(item(
            id = id++,
            title = "WallStreet Bets Official",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@wsb_crypto_hub",
            membersCount = 320000,
            price = 580.0,
            niche = "Stocks & Crypto",
            monthlyIncome = "$510/mo (TON Ads)",
            description = "Massive trading & financial memes community channel. High ad impression rate across US and EU viewers. Verified ad revenue on Telegram Fragment platform.",
            sellerTelegram = "@AlphaBrokers_TG",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "AI Prompts & Tools Daily",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@aipromptshub",
            membersCount = 145000,
            price = 290.0,
            niche = "Artificial Intelligence",
            monthlyIncome = "$240/mo (TON Ads)",
            description = "Rapidly expanding AI tools, ChatGPT prompts, and productivity hacks channel. High CTR on promoted sponsor posts.",
            sellerTelegram = "@AIVendor_Market"
        ))

        list.add(item(
            id = id++,
            title = "Bloomberg Global Markets",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@bloomberg_feed",
            membersCount = 410000,
            price = 790.0,
            niche = "Finance & Forex",
            monthlyIncome = "$680/mo (TON Ads)",
            description = "Tier-1 global financial intelligence broadcast channel. Tier-1 audience demographics with exceptional CPM rates on TON Ads.",
            sellerTelegram = "@PremierAssetGroup",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "Netflix Cinema & Series Hub",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@netflix_cinema_hd",
            membersCount = 520000,
            price = 650.0,
            niche = "Movies & Media",
            monthlyIncome = "$490/mo (TON Ads)",
            description = "Huge entertainment channel sharing trailers, ratings, and streaming recommendations. Over 80k post views per upload.",
            sellerTelegram = "@MediaVaultAdmin"
        ))

        list.add(item(
            id = id++,
            title = "Binance Whale Tracker",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@binance_whale_radar",
            membersCount = 275000,
            price = 480.0,
            niche = "Crypto & Trading",
            monthlyIncome = "$410/mo (TON Ads)",
            description = "Automated on-chain large wallet transfer alerts channel. Monetized with continuous TON Ads banners.",
            sellerTelegram = "@WhaleBrokerTG"
        ))

        list.add(item(
            id = id++,
            title = "Football Highlights & Goals HD",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@football_match_hd",
            membersCount = 310000,
            price = 390.0,
            niche = "Sports & Football",
            monthlyIncome = "$310/mo (TON Ads)",
            description = "Daily UEFA, Premier League, and Champions League goals highlights. Very active weekend traffic surges.",
            sellerTelegram = "@SportsMediaSeller"
        ))

        list.add(item(
            id = id++,
            title = "Android Apps & Tools Club",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@android_mod_apk",
            membersCount = 195000,
            price = 260.0,
            niche = "Tech & Software",
            monthlyIncome = "$190/mo (TON Ads)",
            description = "Clean utility channel providing open-source tools, icon packs, and system utilities. Active daily readers.",
            sellerTelegram = "@AppPublisherTG"
        ))

        list.add(item(
            id = id++,
            title = "Mindset & Daily Motivation",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@mindset_books_daily",
            membersCount = 160000,
            price = 210.0,
            niche = "Motivation & Books",
            monthlyIncome = "$160/mo (TON Ads)",
            description = "Book summaries, stoic philosophy quotes, and entrepreneurial motivation. Steady loyal readership.",
            sellerTelegram = "@MindsetVault"
        ))

        // --- SUB 2: Public + TON Ads OFF (10 Channels) ---
        list.add(item(
            id = id++,
            title = "Python Developers Global",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@python_dev_global",
            membersCount = 95000,
            price = 180.0,
            niche = "Programming & Code",
            monthlyIncome = "$0 (Ready for Ads)",
            description = "High quality programming channel focused on Python tutorials, Django snippets, and open-source libraries. TON Ads can be enabled immediately.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "Cyber Security & Bug Bounty",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@bugbounty_intel",
            membersCount = 130000,
            price = 270.0,
            niche = "Cyber Security",
            monthlyIncome = "$0",
            description = "CVE zero-day vulnerability alerts, ethical hacking guides, and writeups. Highly technical audience.",
            sellerTelegram = "@SecurityAssets"
        ))

        list.add(item(
            id = id++,
            title = "Anime World & Manga HD",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@animeworld_stream",
            membersCount = 280000,
            price = 320.0,
            niche = "Anime & Gaming",
            monthlyIncome = "$0",
            description = "Popular anime news, episode updates, and manga discussions. Excellent engagement and forward rates.",
            sellerTelegram = "@AnimeVendor"
        ))

        list.add(item(
            id = id++,
            title = "Startup Founders & VC Deals",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@founders_vc_hub",
            membersCount = 68000,
            price = 240.0,
            niche = "Business & Startups",
            monthlyIncome = "$0",
            description = "Early stage startup pitch decks, funding round announcements, and founder interviews. Premium demographic.",
            sellerTelegram = "@VentureBroker"
        ))

        list.add(item(
            id = id++,
            title = "English Vocabulary & IELTS Prep",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@ielts_english_master",
            membersCount = 210000,
            price = 250.0,
            niche = "Education & Language",
            monthlyIncome = "$0",
            description = "Daily vocabulary words, grammar quizzes, and IELTS test preparation materials. Extremely high quiz interaction.",
            sellerTelegram = "@EduChannelShop"
        ))

        list.add(item(
            id = id++,
            title = "Lo-Fi Beats & Ambient Music",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@lofi_chill_beats",
            membersCount = 175000,
            price = 190.0,
            niche = "Music & Relaxation",
            monthlyIncome = "$0",
            description = "Relaxing study beats, lo-fi aesthetic tracks, and ambient audio loops. Passionate audience.",
            sellerTelegram = "@AudioAssetsBroker"
        ))

        list.add(item(
            id = id++,
            title = "Web3 & NFT Drops Radar",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@web3_nft_drops",
            membersCount = 115000,
            price = 210.0,
            niche = "Crypto & Web3",
            monthlyIncome = "$0",
            description = "Airdrop guides, testnet tutorials, and Web3 project highlights. Active crypto enthusiasts.",
            sellerTelegram = "@Web3Vendor"
        ))

        list.add(item(
            id = id++,
            title = "4K Ultra HD Wallpapers",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@ultra_wallpapers_4k",
            membersCount = 390000,
            price = 310.0,
            niche = "Art & Photography",
            monthlyIncome = "$0",
            description = "Curated high-resolution mobile and desktop wallpapers. Millions of image saves every month.",
            sellerTelegram = "@VisualAssets"
        ))

        list.add(item(
            id = id++,
            title = "Amazon Deals & Coupen Glitches",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@loot_deals_coupons",
            membersCount = 230000,
            price = 340.0,
            niche = "E-Commerce Deals",
            monthlyIncome = "$0",
            description = "Instant e-commerce discount codes, price drops, and flash deal alerts. High click-through buyer traffic.",
            sellerTelegram = "@DealMarket_TG"
        ))

        list.add(item(
            id = id++,
            title = "Graphic Design & Fonts Vault",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@graphic_design_vault",
            membersCount = 88000,
            price = 150.0,
            niche = "Design & UI/UX",
            monthlyIncome = "$0",
            description = "Curated typography collections, Figma templates, and design vectors. Clean and organized.",
            sellerTelegram = "@CreativeVault"
        ))

        // --- SUB 3: Private + TON Ads ACTIVE (10 Channels) ---
        list.add(item(
            id = id++,
            title = "VIP Alpha Crypto Calls [Private]",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+vip_alpha_calls",
            membersCount = 75000,
            price = 490.0,
            niche = "Crypto VIP",
            monthlyIncome = "$420/mo (TON Ads)",
            description = "Private membership channel with active ad revenue and loyal trading community. Lifetime invite link transferred.",
            sellerTelegram = "@WhaleBrokerTG",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "Forex VIP Gold Scalpers",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+forex_gold_vip",
            membersCount = 62000,
            price = 440.0,
            niche = "Forex Trading",
            monthlyIncome = "$390/mo (TON Ads)",
            description = "Exclusive gold (XAUUSD) trading analysis broadcast channel. Premium subscriber base with TON monetization active.",
            sellerTelegram = "@ForexVault_Seller"
        ))

        list.add(item(
            id = id++,
            title = "Private Equity & Angel Syndicate",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+private_equity_vc",
            membersCount = 48000,
            price = 380.0,
            niche = "Finance & VC",
            monthlyIncome = "$310/mo (TON Ads)",
            description = "High net-worth subscriber channel sharing pre-seed deals and valuation memos. Excellent conversion rates.",
            sellerTelegram = "@VentureBroker"
        ))

        list.add(item(
            id = id++,
            title = "Crypto Futures 100x Signals",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+crypto_100x_futures",
            membersCount = 89000,
            price = 520.0,
            niche = "Futures Trading",
            monthlyIncome = "$460/mo (TON Ads)",
            description = "Leverage trading setups with verified win-rate charts. Consistent ad impressions on all daily broadcasts.",
            sellerTelegram = "@FuturesDeskTG"
        ))

        list.add(item(
            id = id++,
            title = "Elite Dropshipping Inner Circle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+dropship_innercircle",
            membersCount = 55000,
            price = 350.0,
            niche = "E-Commerce",
            monthlyIncome = "$280/mo (TON Ads)",
            description = "Winning product research reports, TikTok ad creatives, and supplier contacts. Monetized via TON Ads.",
            sellerTelegram = "@EcomAssetsShop"
        ))

        list.add(item(
            id = id++,
            title = "Secret Hollywood Screenings",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+hollywood_screenings",
            membersCount = 110000,
            price = 310.0,
            niche = "Entertainment",
            monthlyIncome = "$230/mo (TON Ads)",
            description = "Exclusive cinema reviews, festival screenings, and director interviews. Fast-growing channel.",
            sellerTelegram = "@MediaVaultAdmin"
        ))

        list.add(item(
            id = id++,
            title = "Premium Sports Betting Insights",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+sports_betting_vip",
            membersCount = 78000,
            price = 410.0,
            niche = "Sports Analytics",
            monthlyIncome = "$350/mo (TON Ads)",
            description = "Statistical probability analysis for major international soccer and basketball matches.",
            sellerTelegram = "@SportsMediaSeller"
        ))

        list.add(item(
            id = id++,
            title = "AI SaaS Growth Mastermind",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+ai_saas_mastermind",
            membersCount = 42000,
            price = 290.0,
            niche = "SaaS & AI",
            monthlyIncome = "$210/mo (TON Ads)",
            description = "Case studies on scaling micro-SaaS applications using AI models. High revenue potential.",
            sellerTelegram = "@AIVendor_Market"
        ))

        list.add(item(
            id = id++,
            title = "Crypto Memecoin Gems Radar",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+memecoin_gems_alpha",
            membersCount = 95000,
            price = 460.0,
            niche = "Memecoins",
            monthlyIncome = "$380/mo (TON Ads)",
            description = "Early trending tokens on Solana, Base, and TON. Extreme engagement rates on every broadcast post.",
            sellerTelegram = "@DegenDesk"
        ))

        list.add(item(
            id = id++,
            title = "Private Real Estate Syndicate",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+real_estate_syndicate",
            membersCount = 36000,
            price = 320.0,
            niche = "Real Estate",
            monthlyIncome = "$250/mo (TON Ads)",
            description = "High-ROI vacation rental deals, Dubai property investments, and European luxury estates.",
            sellerTelegram = "@PremierAssetGroup"
        ))

        // --- SUB 4: Private + TON Ads OFF (10 Channels) ---
        list.add(item(
            id = id++,
            title = "Darknet Cyber Threat Intel",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+threat_intel_feed",
            membersCount = 51000,
            price = 280.0,
            niche = "Cyber Security",
            monthlyIncome = "$0",
            description = "Private threat intelligence briefings for SOC analysts and security researchers.",
            sellerTelegram = "@SecurityAssets"
        ))

        list.add(item(
            id = id++,
            title = "Full Stack Developer Syndicate",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+fullstack_dev_private",
            membersCount = 64000,
            price = 220.0,
            niche = "Software Engineering",
            monthlyIncome = "$0",
            description = "System architecture diagrams, database optimizations, and backend code reviews.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "Medical Study Notes & PG Prep",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+medical_pg_notes",
            membersCount = 82000,
            price = 260.0,
            niche = "Medical Science",
            monthlyIncome = "$0",
            description = "USMLE and clinical medicine flashcards, anatomical diagrams, and surgical case notes.",
            sellerTelegram = "@EduChannelShop"
        ))

        list.add(item(
            id = id++,
            title = "Exclusive Luxury Real Estate",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+luxury_villas_intl",
            membersCount = 44000,
            price = 310.0,
            niche = "Luxury Real Estate",
            monthlyIncome = "$0",
            description = "Private listings of waterfront villas in Monaco, Miami, and Bali.",
            sellerTelegram = "@PremierAssetGroup"
        ))

        list.add(item(
            id = id++,
            title = "Stock Market Option Sellers",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+option_sellers_club",
            membersCount = 58000,
            price = 340.0,
            niche = "Options Trading",
            monthlyIncome = "$0",
            description = "Theta decay strategies, iron condors, and wheel strategy trades.",
            sellerTelegram = "@AlphaBrokers_TG"
        ))

        list.add(item(
            id = id++,
            title = "Digital Nomad & Remote Work",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+nomad_remote_jobs",
            membersCount = 72000,
            price = 190.0,
            niche = "Remote Jobs",
            monthlyIncome = "$0",
            description = "Curated high-paying remote tech and design jobs for digital nomads worldwide.",
            sellerTelegram = "@NomadAssets"
        ))

        list.add(item(
            id = id++,
            title = "Exclusive Indie Hacker Lounge",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+indie_hackers_lounge",
            membersCount = 39000,
            price = 210.0,
            niche = "Solopreneurs",
            monthlyIncome = "$0",
            description = "Transparent revenue updates from solo software developers building profitable businesses.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "Crypto Arbitrage Bot Alerts",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+crypto_arbitrage_alerts",
            membersCount = 67000,
            price = 370.0,
            niche = "Arbitrage Trading",
            monthlyIncome = "$0",
            description = "Cross-exchange price discrepancy notifications between Binance, Bybit, and OKX.",
            sellerTelegram = "@CryptoWhaleBroker"
        ))

        list.add(item(
            id = id++,
            title = "Vintage Cinema Archive",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+vintage_cinema_vault",
            membersCount = 91000,
            price = 180.0,
            niche = "Classic Cinema",
            monthlyIncome = "$0",
            description = "Digitally remastered public-domain classic films and film noir discussions.",
            sellerTelegram = "@MediaVaultAdmin"
        ))

        list.add(item(
            id = id++,
            title = "Fitness Transformation Secrets",
            platform = Platform.TELEGRAM,
            category = ItemCategory.CHANNEL,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+fitness_body_vip",
            membersCount = 53000,
            price = 160.0,
            niche = "Health & Fitness",
            monthlyIncome = "$0",
            description = "Evidence-based meal plans, strength training guides, and hypertrophy techniques.",
            sellerTelegram = "@FitnessVendor"
        ))

        // =========================================================================
        // SECTION 2: 40 TELEGRAM GROUPS (Real groups, all 4 subcategories)
        // =========================================================================

        // --- SUB 1: Public + TON Ads ACTIVE (10 Groups) ---
        list.add(item(
            id = id++,
            title = "Crypto Traders Global Lounge",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@cryptotraders_lounge",
            membersCount = 85000,
            price = 320.0,
            niche = "Crypto Discussion",
            monthlyIncome = "$260/mo (TON Ads)",
            description = "Massive interactive discussion group for bitcoin and altcoin traders. Anti-spam bot configured, real active chatting 24/7.",
            sellerTelegram = "@CryptoWhaleBroker",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "TON Ecosystem Community Chat",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@ton_community_chat",
            membersCount = 110000,
            price = 450.0,
            niche = "TON & Web3",
            monthlyIncome = "$370/mo (TON Ads)",
            description = "Official community forum discussing TON Mini-apps, smart contracts, and jettons. Generates heavy daily TON ad impressions.",
            sellerTelegram = "@TonBroker_Official"
        ))

        list.add(item(
            id = id++,
            title = "Binance Futures Traders Chat",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@binance_futures_chat",
            membersCount = 95000,
            price = 380.0,
            niche = "Trading Forum",
            monthlyIncome = "$310/mo (TON Ads)",
            description = "Fast-moving discussion of leveraged trading and risk management. Monitored by custom moderator bots.",
            sellerTelegram = "@FuturesDeskTG"
        ))

        list.add(item(
            id = id++,
            title = "Solana Degens & Trading Chat",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@solana_degens_chat",
            membersCount = 78000,
            price = 340.0,
            niche = "Solana Ecosystem",
            monthlyIncome = "$280/mo (TON Ads)",
            description = "High energy group discussing DEX raydium pairs and pump.fun tokens. Thousands of messages daily.",
            sellerTelegram = "@DegenDesk"
        ))

        list.add(item(
            id = id++,
            title = "Tech Support & IT Helpdesk",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@it_tech_support_group",
            membersCount = 65000,
            price = 210.0,
            niche = "IT & Software",
            monthlyIncome = "$150/mo (TON Ads)",
            description = "Helpful community for troubleshooting hardware, Linux servers, and software issues. Very friendly environment.",
            sellerTelegram = "@TechMediaVendor"
        ))

        list.add(item(
            id = id++,
            title = "Forex Scalping Community Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@forex_scalping_group",
            membersCount = 72000,
            price = 360.0,
            niche = "Forex Discussion",
            monthlyIncome = "$290/mo (TON Ads)",
            description = "Real-time scalping chart discussions during London and New York market opens. Active moderators.",
            sellerTelegram = "@ForexVault_Seller"
        ))

        list.add(item(
            id = id++,
            title = "Stock Market Investors Forum",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@stockmarket_investors_group",
            membersCount = 88000,
            price = 390.0,
            niche = "Equity Markets",
            monthlyIncome = "$310/mo (TON Ads)",
            description = "Fundamental analysis and quarterly earnings discussions for S&P 500 stocks.",
            sellerTelegram = "@AlphaBrokers_TG"
        ))

        list.add(item(
            id = id++,
            title = "Dropshipping & Ecom Discussion",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@dropshipping_ecom_hub",
            membersCount = 54000,
            price = 260.0,
            niche = "E-Commerce",
            monthlyIncome = "$200/mo (TON Ads)",
            description = "Shopify store owners, TikTok shop advertisers, and product sourcing specialists.",
            sellerTelegram = "@EcomAssetsShop"
        ))

        list.add(item(
            id = id++,
            title = "AI Prompts & Midjourney Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@ai_midjourney_group",
            membersCount = 92000,
            price = 280.0,
            niche = "AI Art & Design",
            monthlyIncome = "$220/mo (TON Ads)",
            description = "Creative artists sharing Stable Diffusion and Midjourney parameters and workflows.",
            sellerTelegram = "@AIVendor_Market"
        ))

        list.add(item(
            id = id++,
            title = "Affiliate Marketing Mastermind",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@affiliate_marketing_club",
            membersCount = 61000,
            price = 270.0,
            niche = "Digital Marketing",
            monthlyIncome = "$210/mo (TON Ads)",
            description = "CPA network reviews, Google Ads arbitrage, and media buyer network.",
            sellerTelegram = "@MarketingDesk"
        ))

        // --- SUB 2: Public + TON Ads OFF (10 Groups) ---
        list.add(item(
            id = id++,
            title = "Python & Django Developers Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@pythondjangogroup",
            membersCount = 48000,
            price = 160.0,
            niche = "Python Dev",
            monthlyIncome = "$0",
            description = "Collaborative programmer chat for code debugging, library recommendations, and FastAPI best practices.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "JavaScript & React Full Stack",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@react_js_devs_group",
            membersCount = 52000,
            price = 180.0,
            niche = "Web Development",
            monthlyIncome = "$0",
            description = "Next.js, TypeScript, and modern front-end state management discussions.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "Graphic Designers Worldwide Chat",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@designers_worldwide_chat",
            membersCount = 41000,
            price = 140.0,
            niche = "Design & UI",
            monthlyIncome = "$0",
            description = "Portfolio feedback, typography debates, and freelance gig referrals.",
            sellerTelegram = "@CreativeVault"
        ))

        list.add(item(
            id = id++,
            title = "Kaggle & Data Science Community",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@datascience_kaggle_chat",
            membersCount = 63000,
            price = 230.0,
            niche = "Machine Learning",
            monthlyIncome = "$0",
            description = "Machine learning engineers discussing PyTorch architectures and Kaggle competitions.",
            sellerTelegram = "@AIVendor_Market"
        ))

        list.add(item(
            id = id++,
            title = "Android Kotlin & Compose Devs",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@android_flutter_community",
            membersCount = 39000,
            price = 150.0,
            niche = "Mobile Apps",
            monthlyIncome = "$0",
            description = "Clean architectural patterns, Jetpack Compose, and Google Play Store publication guides.",
            sellerTelegram = "@AppPublisherTG"
        ))

        list.add(item(
            id = id++,
            title = "IELTS Speaking & English Club",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@ielts_speaking_club",
            membersCount = 84000,
            price = 190.0,
            niche = "Language Study",
            monthlyIncome = "$0",
            description = "Voice chat sessions and speaking partner matching for international students.",
            sellerTelegram = "@EduChannelShop"
        ))

        list.add(item(
            id = id++,
            title = "Digital Freelancers Hub Global",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@freelancers_hub_global",
            membersCount = 57000,
            price = 200.0,
            niche = "Freelancing & Work",
            monthlyIncome = "$0",
            description = "Upwork proposal tips, rate negotiation strategies, and international wire methods.",
            sellerTelegram = "@NomadAssets"
        ))

        list.add(item(
            id = id++,
            title = "Indie Game Developers Lounge",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@indiegame_devs_chat",
            membersCount = 36000,
            price = 130.0,
            niche = "Game Dev",
            monthlyIncome = "$0",
            description = "Unity, Unreal Engine 5, and Godot developers sharing devlogs and mechanics.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "UPSC & Civil Services Aspirants",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@upsc_aspirants_chat",
            membersCount = 76000,
            price = 220.0,
            niche = "Competitive Exams",
            monthlyIncome = "$0",
            description = "Study timetable accountability, current affairs analysis, and editorial discussions.",
            sellerTelegram = "@EduChannelShop"
        ))

        list.add(item(
            id = id++,
            title = "Cyber Security CTF & Hackers Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@ctf_hackers_group",
            membersCount = 68000,
            price = 250.0,
            niche = "Ethical Hacking",
            monthlyIncome = "$0",
            description = "HackTheBox walkthroughs, reverse engineering challenges, and exploit analysis.",
            sellerTelegram = "@SecurityAssets"
        ))

        // --- SUB 3: Private + TON Ads ACTIVE (10 Groups) ---
        list.add(item(
            id = id++,
            title = "Whale Traders Private Desk",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+whale_traders_desk",
            membersCount = 28000,
            price = 520.0,
            niche = "Crypto Alpha",
            monthlyIncome = "$480/mo (TON Ads)",
            description = "High barrier-to-entry private group for verified trading accounts over 50k portfolio value.",
            sellerTelegram = "@CryptoWhaleBroker",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "VIP Forex Elite Discussion",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+forex_elite_discussion",
            membersCount = 34000,
            price = 440.0,
            niche = "Forex Mastery",
            monthlyIncome = "$380/mo (TON Ads)",
            description = "Exclusive forum for institutional currency traders and prop-firm funded accounts.",
            sellerTelegram = "@ForexVault_Seller"
        ))

        list.add(item(
            id = id++,
            title = "7-Figure Amazon FBA Sellers Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+fba_sellers_elite",
            membersCount = 19500,
            price = 390.0,
            niche = "Amazon FBA",
            monthlyIncome = "$310/mo (TON Ads)",
            description = "Private group of verified brand owners sharing PPC strategies and freight forwarder rates.",
            sellerTelegram = "@EcomAssetsShop"
        ))

        list.add(item(
            id = id++,
            title = "SaaS Founders Advisory Circle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+saas_founders_circle",
            membersCount = 14200,
            price = 360.0,
            niche = "B2B SaaS",
            monthlyIncome = "$270/mo (TON Ads)",
            description = "Founders sharing churn reduction techniques, Stripe integrations, and investor updates.",
            sellerTelegram = "@VentureBroker"
        ))

        list.add(item(
            id = id++,
            title = "Angel Investors Syndicate Private",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+angel_investors_syndicate",
            membersCount = 18000,
            price = 480.0,
            niche = "Angel Deals",
            monthlyIncome = "$390/mo (TON Ads)",
            description = "Accredited investor group reviewing SAFE notes and token allocation rounds.",
            sellerTelegram = "@PremierAssetGroup"
        ))

        list.add(item(
            id = id++,
            title = "Crypto Arbitrage Traders Club",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+arbitrage_traders_vip",
            membersCount = 26000,
            price = 460.0,
            niche = "Crypto Arbitrage",
            monthlyIncome = "$370/mo (TON Ads)",
            description = "Fast execution strategies across CEX and DEX liquidity pools.",
            sellerTelegram = "@CryptoWhaleBroker"
        ))

        list.add(item(
            id = id++,
            title = "Real Estate Syndication Hub",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+realestate_syndicate_vip",
            membersCount = 15800,
            price = 380.0,
            niche = "Real Estate",
            monthlyIncome = "$290/mo (TON Ads)",
            description = "Commercial real estate and multifamily property syndication network.",
            sellerTelegram = "@PremierAssetGroup"
        ))

        list.add(item(
            id = id++,
            title = "High Ticket Affiliate Network",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+highticket_affiliates",
            membersCount = 22000,
            price = 340.0,
            niche = "Affiliate Network",
            monthlyIncome = "$260/mo (TON Ads)",
            description = "Private network sharing exclusive high-ticket commission offer links.",
            sellerTelegram = "@MarketingDesk"
        ))

        list.add(item(
            id = id++,
            title = "Solana Early Token Launchpad",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+solana_launchpad_chat",
            membersCount = 31000,
            price = 490.0,
            niche = "Solana Launch",
            monthlyIncome = "$420/mo (TON Ads)",
            description = "Verified community discussing dev burns, liquidity locks, and fair launches.",
            sellerTelegram = "@DegenDesk"
        ))

        list.add(item(
            id = id++,
            title = "Exclusive DeFi Yield Farmers",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+defi_yield_farmers_vip",
            membersCount = 25000,
            price = 430.0,
            niche = "DeFi Staking",
            monthlyIncome = "$350/mo (TON Ads)",
            description = "Concentrated liquidity provision on Uniswap v3, Aerodrome, and Curve.",
            sellerTelegram = "@CryptoWhaleBroker"
        ))

        // --- SUB 4: Private + TON Ads OFF (10 Groups) ---
        list.add(item(
            id = id++,
            title = "Full Stack Lead Architects",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+lead_architects_chat",
            membersCount = 16500,
            price = 220.0,
            niche = "System Architecture",
            monthlyIncome = "$0",
            description = "High-level technical leadership discussions for Staff & Principal engineers.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "Bug Bounty Private Hunting Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+bugbounty_private_chat",
            membersCount = 21000,
            price = 280.0,
            niche = "AppSec & Hacking",
            monthlyIncome = "$0",
            description = "Private collaboration group for HackerOne and Bugcrowd top leaderboard hunters.",
            sellerTelegram = "@SecurityAssets"
        ))

        list.add(item(
            id = id++,
            title = "Quantitative & Algo Trading Desk",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+quant_algo_trading",
            membersCount = 19000,
            price = 350.0,
            niche = "Quant Finance",
            monthlyIncome = "$0",
            description = "Backtesting statistical arbitrage strategies in C++ and Python.",
            sellerTelegram = "@AlphaBrokers_TG"
        ))

        list.add(item(
            id = id++,
            title = "Remote Tech Leads Lounge",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+remote_tech_leads",
            membersCount = 14000,
            price = 180.0,
            niche = "Tech Management",
            monthlyIncome = "$0",
            description = "Engineering managers sharing sprint planning and team compensation frameworks.",
            sellerTelegram = "@NomadAssets"
        ))

        list.add(item(
            id = id++,
            title = "Medical Doctors Clinical Cases Forum",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+clinical_cases_forum",
            membersCount = 27000,
            price = 240.0,
            niche = "Clinical Medicine",
            monthlyIncome = "$0",
            description = "Interdisciplinary case presentations and differential diagnosis challenges.",
            sellerTelegram = "@EduChannelShop"
        ))

        list.add(item(
            id = id++,
            title = "Startup Exit & M&A Network",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+startup_exit_network",
            membersCount = 11000,
            price = 410.0,
            niche = "Mergers & Acquisitions",
            monthlyIncome = "$0",
            description = "Buyers and sellers connecting for $500k to $10M internet business acquisitions.",
            sellerTelegram = "@VentureBroker"
        ))

        list.add(item(
            id = id++,
            title = "Private Equity Real Estate Desk",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+private_equity_desk",
            membersCount = 17500,
            price = 360.0,
            niche = "Real Estate",
            monthlyIncome = "$0",
            description = "Capital allocation discussions for industrial warehouses and medical office parks.",
            sellerTelegram = "@PremierAssetGroup"
        ))

        list.add(item(
            id = id++,
            title = "E-Commerce Agency Owners Group",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+agency_owners_group",
            membersCount = 23000,
            price = 290.0,
            niche = "Digital Agency",
            monthlyIncome = "$0",
            description = "Media buying scaling playbooks, client retention systems, and team hiring.",
            sellerTelegram = "@MarketingDesk"
        ))

        list.add(item(
            id = id++,
            title = "Indie Hacker Revenue Sharing Chat",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "https://t.me/+indie_revenue_chat",
            membersCount = 18200,
            price = 210.0,
            niche = "Solopreneur",
            monthlyIncome = "$0",
            description = "Bootstrapped entrepreneurs sharing transparent MRR numbers and product launches.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "Cyber Incident Responders Hub",
            platform = Platform.TELEGRAM,
            category = ItemCategory.GROUP,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+incident_responders_hub",
            membersCount = 20500,
            price = 270.0,
            niche = "DFIR & Forensics",
            monthlyIncome = "$0",
            description = "Ransomware negotiation logs, malware sandboxing results, and memory forensics.",
            sellerTelegram = "@SecurityAssets"
        ))

        // =========================================================================
        // SECTION 3: 12 TELEGRAM BOTS
        // =========================================================================
        list.add(item(
            id = id++,
            title = "ChatGPT 4o Telegram Assistant Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@gpt4o_assistant_bot",
            membersCount = 380000,
            price = 550.0,
            niche = "AI Bot",
            monthlyIncome = "$430/mo (Subscriptions)",
            description = "Turnkey AI assistant bot with active monthly paying users, Python backend on VPS, and custom fine-tuned prompts. Code & bot token transferred.",
            sellerTelegram = "@BotMarketMaster",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "Multi-Platform Video Downloader Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = true,
            handleOrLink = "@allinone_downloader_bot",
            membersCount = 650000,
            price = 720.0,
            niche = "Utility Bot",
            monthlyIncome = "$580/mo (TON Ads)",
            description = "High volume downloader bot for TikTok, YouTube Shorts, and Instagram Reels. Monetized via TON Ads broadcast triggers.",
            sellerTelegram = "@BotMarketMaster"
        ))

        list.add(item(
            id = id++,
            title = "Telegram Group Auto-Moderator Shield",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "@moderator_shield_bot",
            membersCount = 140000,
            price = 280.0,
            niche = "Security Bot",
            monthlyIncome = "$190/mo (Premium Tiers)",
            description = "Installed in over 1,200 active groups. Handles captcha verification, anti-raid protection, and spam link deletion.",
            sellerTelegram = "@SecurityAssets"
        ))

        list.add(item(
            id = id++,
            title = "SolScan & DexScreener Fast Alert Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@dexscreener_fast_bot",
            membersCount = 210000,
            price = 460.0,
            niche = "Trading Bot",
            monthlyIncome = "$370/mo (Sponsors)",
            description = "Real-time liquidity addition and market cap milestone pings. High sponsor demand from token developers.",
            sellerTelegram = "@DegenDesk"
        ))

        list.add(item(
            id = id++,
            title = "PDF Converter & OCR Master Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@pdf_converter_pro_bot",
            membersCount = 180000,
            price = 240.0,
            niche = "Productivity Bot",
            monthlyIncome = "$120/mo",
            description = "Instant PDF compression, word to pdf conversion, and image text extraction.",
            sellerTelegram = "@BotMarketMaster"
        ))

        list.add(item(
            id = id++,
            title = "Spotify & Apple Music Streamer Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@spotify_music_dl_bot",
            membersCount = 310000,
            price = 390.0,
            niche = "Music Bot",
            monthlyIncome = "$290/mo (TON Ads)",
            description = "Lightning-fast audio search and streaming directly within Telegram chats. Strong user retention.",
            sellerTelegram = "@AudioAssetsBroker"
        ))

        list.add(item(
            id = id++,
            title = "Binance Webhook Signal Execution Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PRIVATE,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = true,
            handleOrLink = "https://t.me/+binance_webhook_bot",
            membersCount = 45000,
            price = 480.0,
            niche = "Algo Trading Bot",
            monthlyIncome = "$350/mo",
            description = "Bridges TradingView webhook alerts directly to Binance Futures API with sub-second execution.",
            sellerTelegram = "@CryptoWhaleBroker"
        ))

        list.add(item(
            id = id++,
            title = "Temp Mail & Virtual SMS Receiver Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@temp_mail_receiver_bot",
            membersCount = 290000,
            price = 340.0,
            niche = "Privacy Utility",
            monthlyIncome = "$260/mo",
            description = "Generates disposable email addresses and receives confirmation codes inside Telegram.",
            sellerTelegram = "@SecurityAssets"
        ))

        list.add(item(
            id = id++,
            title = "Cloud Storage File to Link Generator",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@filetolink_cloud_bot",
            membersCount = 220000,
            price = 270.0,
            niche = "Cloud Storage",
            monthlyIncome = "$180/mo",
            description = "Turns any document, video or archive uploaded to Telegram into a direct high-speed download link.",
            sellerTelegram = "@BotMarketMaster"
        ))

        list.add(item(
            id = id++,
            title = "AI Realistic Portrait Generator Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.ACTIVE,
            isVerified = false,
            handleOrLink = "@ai_imagegen_bot",
            membersCount = 175000,
            price = 320.0,
            niche = "AI Generation",
            monthlyIncome = "$240/mo",
            description = "Generates photorealistic headshots from user selfies. Integrated Stripe checkout for token packages.",
            sellerTelegram = "@AIVendor_Market"
        ))

        list.add(item(
            id = id++,
            title = "Channel Auto-Sync Cross Poster Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@channel_forwarder_bot",
            membersCount = 98000,
            price = 190.0,
            niche = "Channel Management",
            monthlyIncome = "$140/mo",
            description = "Automatically mirrors posts between Discord, Telegram channels, and X without delays.",
            sellerTelegram = "@BotMarketMaster"
        ))

        list.add(item(
            id = id++,
            title = "Anonymous Confessions & Polls Bot",
            platform = Platform.TELEGRAM,
            category = ItemCategory.BOT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NOT_ACTIVE,
            isVerified = false,
            handleOrLink = "@anon_confession_bot",
            membersCount = 135000,
            price = 210.0,
            niche = "Social Bot",
            monthlyIncome = "$110/mo",
            description = "Anonymous messaging and feedback bot frequently used by university campus channels.",
            sellerTelegram = "@SocialBotVault"
        ))

        // =========================================================================
        // SECTION 4: 12 TELEGRAM USER ACCOUNTS (OG Handles, Verified, Premium)
        // =========================================================================
        list.add(item(
            id = id++,
            title = "@ceo - Ultra Rare 3-Letter Handle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = true,
            handleOrLink = "@ceo",
            membersCount = 1,
            price = 1450.0,
            niche = "Rare OG Handle",
            monthlyIncome = "N/A",
            description = "Prestige dictionary 3-letter handle on Telegram. Registered in 2016, verified blue badge, 1-Year Telegram Premium active. Transferred cleanly via Fragment NFT / primary phone SIM.",
            sellerTelegram = "@EliteHandleBroker",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "@ton - Brand Official Name",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = true,
            handleOrLink = "@ton",
            membersCount = 1,
            price = 1850.0,
            niche = "Fragment NFT",
            monthlyIncome = "N/A",
            description = "Ultra high-value Web3 username. Direct transfer on the TON blockchain via Fragment.com escrow.",
            sellerTelegram = "@EliteHandleBroker",
            isFeatured = true
        ))

        list.add(item(
            id = id++,
            title = "@vip - Luxury Status Profile",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = true,
            handleOrLink = "@vip",
            membersCount = 1,
            price = 1200.0,
            niche = "3-Letter Status",
            monthlyIncome = "N/A",
            description = "Legendary 3-letter short handle. Includes official Telegram Verification badge and pristine account status.",
            sellerTelegram = "@AlphaBrokers_TG"
        ))

        list.add(item(
            id = id++,
            title = "@eth - Ethereum Ecosystem Handle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = false,
            isPremium = true,
            handleOrLink = "@eth",
            membersCount = 1,
            price = 980.0,
            niche = "Crypto Brand",
            monthlyIncome = "N/A",
            description = "Legendary crypto 3-letter username. Perfect personal brand for founders, traders, or venture capitalists.",
            sellerTelegram = "@EliteHandleBroker"
        ))

        list.add(item(
            id = id++,
            title = "@dev - Software Architect Profile",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = false,
            handleOrLink = "@dev",
            membersCount = 1,
            price = 750.0,
            niche = "Developer Handle",
            monthlyIncome = "N/A",
            description = "2017 aged account with original email and complete number ownership. Zero spam history.",
            sellerTelegram = "@DevChannelBroker"
        ))

        list.add(item(
            id = id++,
            title = "@pro - Professional Business Handle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = false,
            isPremium = true,
            handleOrLink = "@pro",
            membersCount = 1,
            price = 680.0,
            niche = "Short 3-Letter",
            monthlyIncome = "N/A",
            description = "Short, memorable 3-letter dictionary handle with 1-Year Telegram Premium already activated.",
            sellerTelegram = "@AlphaBrokers_TG"
        ))

        list.add(item(
            id = id++,
            title = "@pay - Fintech Brand Identity",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = true,
            handleOrLink = "@pay",
            membersCount = 1,
            price = 890.0,
            niche = "Fintech Brand",
            monthlyIncome = "N/A",
            description = "Premium payment brand username. Clean registered status ready for business customer support.",
            sellerTelegram = "@PremierAssetGroup"
        ))

        list.add(item(
            id = id++,
            title = "@bot - Automated Service Identity",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = false,
            isPremium = true,
            handleOrLink = "@bot",
            membersCount = 1,
            price = 820.0,
            niche = "Automation Handle",
            monthlyIncome = "N/A",
            description = "Classic 3-letter handle, ideal for bot developers and software automation companies.",
            sellerTelegram = "@BotMarketMaster"
        ))

        list.add(item(
            id = id++,
            title = "@dex - Decentralized Finance Profile",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = false,
            isPremium = true,
            handleOrLink = "@dex",
            membersCount = 1,
            price = 650.0,
            niche = "Crypto & DeFi",
            monthlyIncome = "N/A",
            description = "High demand 3-letter handle across the decentralized finance sector. Clean Fragment transfer.",
            sellerTelegram = "@DegenDesk"
        ))

        list.add(item(
            id = id++,
            title = "@app - Mobile Application Handle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = false,
            handleOrLink = "@app",
            membersCount = 1,
            price = 790.0,
            niche = "Tech Handle",
            monthlyIncome = "N/A",
            description = "Flagship tech handle. Account active since 2017 with verified blue badge.",
            sellerTelegram = "@AppPublisherTG"
        ))

        list.add(item(
            id = id++,
            title = "@trade - Financial Markets Handle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            isPremium = true,
            handleOrLink = "@trade",
            membersCount = 1,
            price = 850.0,
            niche = "Trading Brand",
            monthlyIncome = "N/A",
            description = "High reputation trading username with full verification status. Ideal for professional brokers.",
            sellerTelegram = "@CryptoWhaleBroker"
        ))

        list.add(item(
            id = id++,
            title = "@web3 - Blockchain Community Handle",
            platform = Platform.TELEGRAM,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = false,
            isPremium = true,
            handleOrLink = "@web3",
            membersCount = 1,
            price = 720.0,
            niche = "Web3 Ecosystem",
            monthlyIncome = "N/A",
            description = "Global recognized handle for the decentralized web. Includes 1-year premium sub.",
            sellerTelegram = "@EliteHandleBroker"
        ))

        // =========================================================================
        // SECTION 5: 30+ ITEMS FOR EACH PLATFORM (WhatsApp, TikTok, Instagram, Facebook, YouTube, Discord)
        // =========================================================================
        list.addAll(WhatsAppSeedData.getListings())
        list.addAll(TikTokSeedData.getListings())
        list.addAll(InstagramSeedData.getListings())
        list.addAll(FacebookSeedData.getListings())
        list.addAll(YouTubeSeedData.getListings())
        list.addAll(DiscordSeedData.getListings())

        // X (Twitter) Verified Desk
        list.add(item(
            id = 901L,
            title = "Crypto & Web3 Alpha Desk (Verified Blue)",
            platform = Platform.X_TWITTER,
            category = ItemCategory.USER_ACCOUNT,
            privacy = PrivacyType.PUBLIC,
            tonAdsStatus = TonAdsStatus.NONE,
            isVerified = true,
            handleOrLink = "@web3_alpha_desk",
            membersCount = 92000,
            price = 620.0,
            niche = "Crypto Twitter",
            monthlyIncome = "$310/mo (Ad Revenue Share)",
            description = "Verified X account with active Creator Ad Revenue Share payments. High organic impression volume.",
            sellerTelegram = "@AlphaBrokers_TG",
            sellerWhatsApp = "+13125550144"
        ))

        return list
    }
}
