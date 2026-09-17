package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object YouTubeSeedData {
    fun getListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 601L

        fun add(
            title: String,
            category: ItemCategory,
            privacy: PrivacyType = PrivacyType.PUBLIC,
            isVerified: Boolean = false,
            isPremium: Boolean = false,
            handleOrLink: String,
            membersCount: Int,
            price: Double,
            niche: String,
            monthlyIncome: String = "",
            description: String,
            isFeatured: Boolean = false
        ) {
            list.add(
                SeedHelper.createItem(
                    id = id++,
                    title = title,
                    platform = Platform.YOUTUBE,
                    category = category,
                    privacy = privacy,
                    tonAdsStatus = TonAdsStatus.NONE,
                    isVerified = isVerified,
                    isPremium = isPremium,
                    handleOrLink = handleOrLink,
                    membersCount = membersCount,
                    price = price,
                    niche = niche,
                    monthlyIncome = monthlyIncome,
                    description = description,
                    sellerTelegram = "@YouTubeBrokerHQ",
                    sellerWhatsApp = "+13125550144",
                    isFeatured = isFeatured
                )
            )
        }

        // YPP Monetized Channels (High AdSense RPM)
        add("Finance & Personal Wealth (YPP Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "youtube.com/@WealthBuildersHQ", 185000, 1450.0, "Finance & Investing", "$1,250/mo (AdSense)", "Top-tier finance channel with $18-$25 RPM. Approved for YouTube Partner Program with zero copyright strikes. Clean Google AdSense transfer.", true)
        add("Tech Reviews & Gadgets (YPP Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "youtube.com/@TechGeekUnbox", 240000, 1280.0, "Technology", "$980/mo (AdSense + Amazon)", "Active long-form video reviews of laptops, phones, and peripherals. High US and UK viewer proportion.", true)
        add("Full Coding & Web Dev Academy (YPP Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@CodeMastersPro", 95000, 890.0, "Programming", "$620/mo (AdSense)", "In-depth tutorials for React, Python, and cloud engineering. Clean monetization history.")
        add("True Crime & Police Interrogations (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, true, "youtube.com/@CrimeFilesDocumentary", 310000, 1350.0, "Documentary & Crime", "$1,100/mo (AdSense)", "Faceless high-retention documentaries with 30M+ total views. Engaged comment section and high watch time.", true)
        add("Military History & Battles Animated (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@WarHistoryVisualized", 160000, 920.0, "History & Warfare", "$710/mo (AdSense)", "3D animated battle maps covering WW2, Roman Empire, and Cold War. Evergreen views.")
        add("Space, Universe & Quantum Physics (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@CosmosExplorationsTV", 195000, 980.0, "Science & Astronomy", "$740/mo (AdSense)", "Deep dives into black holes and astronomical discoveries. High CPM science demographic.")
        add("Luxury Real Estate & Mega-Mansions (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@LuxeEstatesTour", 140000, 1150.0, "Real Estate", "$890/mo (AdSense)", "Cinematic 4K walkthroughs of Beverly Hills and Bel Air mansions. Sponsorship ready.")
        add("Automotive Documentary & Supercars (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@SupercarChronicles", 215000, 1050.0, "Automotive", "$820/mo (AdSense)", "History of legendary car manufacturers (Ferrari, Porsche, Koenigsegg).")
        add("Business Breakdown & Startup Case Studies", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@CompanyCaseStudies", 125000, 940.0, "Business", "$750/mo (AdSense)", "Engaging essays analyzing why businesses succeed or collapse. High average view duration.")
        add("Crypto & DeFi Explained (YPP Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@CryptoInsightsTV", 110000, 880.0, "Crypto", "$690/mo (AdSense)", "DeFi protocols, on-chain analysis, and macro outlook videos.")

        // Faceless & Automation Niches
        add("Lo-Fi Hip Hop & Chill Beats (Live Stream Enabled)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, true, "youtube.com/@ChillHopVibesRadio", 280000, 1120.0, "Music & Ambient", "$840/mo (AdSense)", "24/7 automated live stream setup included. 100% royalty-free proprietary music catalog included.", true)
        add("Rain Sounds & Sleep Ambience (Faceless)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@RainAmbienceSleep", 350000, 990.0, "Sleep & Meditation", "$760/mo (AdSense)", "10-hour seamless dark screen sleep sound videos. Stable passive search traffic every night.")
        add("Mindset & Stoic Philosophy Summaries", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@StoicWisdomDaily", 175000, 780.0, "Philosophy", "$580/mo (AdSense)", "Animated whiteboard illustrations explaining Marcus Aurelius and Seneca.")
        add("Top 10 Curiosities & Dark Facts", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@DarkFactsCuriosities", 230000, 840.0, "Trivia & Facts", "$640/mo (AdSense)", "Binge-worthy countdown videos with high viewer CTR on thumbnails.")
        add("Ancient Mythology & Folklore Legends", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@MythologyLoreHD", 155000, 710.0, "Mythology", "$520/mo (AdSense)", "Greek, Norse, and Egyptian mythological stories animated with AI.")
        add("Cooking & Outdoor Primitive BBQ", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@PrimitiveFireCooking", 320000, 1100.0, "Cooking & ASMR", "$880/mo (AdSense)", "Wild outdoor wilderness steaks and bread cooking with zero talking. International audience.")

        // YouTube Shorts Monetized Channels
        add("Viral Science Experiments (Shorts Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@ScienceReactionShorts", 680000, 820.0, "Shorts & Science", "$620/mo (Shorts Ad Pool)", "Over 180M cumulative Shorts views. Eligible for creator funds and Shorts ad revenue.")
        add("Street Interview & Trivia IQ Shorts", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@QuickIQStreetInterviews", 490000, 690.0, "Shorts & Entertainment", "$490/mo (Shorts)", "Entertaining fast-paced trivia challenge videos.")
        add("Minecraft Shorts & Funny Fails", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@MinecraftFunnyShorts", 420000, 580.0, "Gaming Shorts", "$410/mo", "Engaging game animation clips with consistent daily upload history.")
        add("Wild Animal Fight Moments Shorts", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@WildEncounterShorts", 540000, 750.0, "Nature Shorts", "$530/mo", "High intensity animal encounters edited in 9:16 format.")
        add("Satisfying Crafts & Kinetic Sand Shorts", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@SatisfyingCraftsShorts", 610000, 790.0, "ASMR Shorts", "$580/mo", "Millions of views on looping ASMR and laser cutting animations.")

        // Gaming & Esports Channels
        add("Minecraft SMP & Survival Series (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@MinecraftSMPAdventures", 290000, 960.0, "Gaming", "$740/mo (AdSense)", "Established multiplayer server let's plays with loyal teen audience.")
        add("GTA V Modding & Roleplay Stories (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@GTARPStoriesHD", 240000, 850.0, "Gaming", "$650/mo (AdSense)", "Cinematic episodic story series in Los Santos.")
        add("Valorant & CS2 Esports Highlights", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@TacticalFPSHighlights", 185000, 680.0, "Esports", "$490/mo", "Pro tournament aces and funny clutch moments.")
        add("Roblox Tycoon & Roleplay Adventures", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@RobloxTycoonAdventures", 340000, 890.0, "Roblox Gaming", "$720/mo (AdSense)", "Active player community with high release-day viewership.")

        // Verified Official & High Tier Channels
        add("Official Artist Channel (Music Note Badge)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "youtube.com/@OfficialArtistMusic", 115000, 1200.0, "Music & Artist", "$820/mo (Content ID)", "Verified Official Artist Channel (OAC) with Music Note badge and YouTube Music distribution.", true)
        add("Verified Checkmark Tech Magazine Channel", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "youtube.com/@GlobalTechDigest", 210000, 1380.0, "Verified Tech", "$960/mo", "Official gray verification checkmark with clean copyright standing and brand recognition.", true)
        add("Fitness Workout & 30-Day Challenges", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@HomeWorkoutsChallenge", 275000, 940.0, "Fitness & Health", "$710/mo", "No-equipment home workout routines and calorie burning follow-alongs.")
        add("Watercolor & Acrylic Painting Tutorials", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@AcrylicPaintingTutorials", 135000, 590.0, "Art & DIY", "$420/mo", "Peaceful landscape painting instructions. Strong community of amateur painters.")
        add("Chess Openings & Grandmaster Traps", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "youtube.com/@ChessTrapsOpenings", 165000, 780.0, "Chess & Strategy", "$560/mo", "Analysis of Magnus Carlsen games and tactical puzzles.")
        add("Aviation & Flight Cockpit Landing 4K", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@CockpitLanding4K", 195000, 870.0, "Aviation", "$640/mo", "Pilot view approaches into extreme airports worldwide.")
        add("Deep Sea & Ocean Trench Exploration", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@AbyssOceanDocumentaries", 225000, 920.0, "Nature & Marine", "$680/mo (AdSense)", "Submarine expeditions, bioluminescent creatures, and ocean mysteries.")
        add("DIY Electronic Projects & Arduino", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@ArduinoRoboticsLab", 145000, 680.0, "Robotics & Hardware", "$510/mo (AdSense + Kits)", "Smart home sensors, drone builds, and soldering guides for makers.")
        add("English Idioms & Fluent Speaking Daily", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "youtube.com/@FluentEnglishDailyBites", 310000, 890.0, "Education & ESL", "$710/mo (AdSense)", "Conversational English pronunciation drills and listening comprehension.")

        return list
    }
}
