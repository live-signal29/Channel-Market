package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object InstagramSeedData {
    fun getListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 401L

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
                    platform = Platform.INSTAGRAM,
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
                    sellerTelegram = "@InstaAssetsDesk",
                    sellerWhatsApp = "+13125550144",
                    isFeatured = isFeatured
                )
            )
        }

        // Instagram Verified & High-Tier Creator Accounts
        add("Official Verified Instagram (Blue Badge)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "@verified_creator_official", 120000, 950.0, "Verified Public Figure", "$780/mo (Brand Deals)", "Authentic Instagram account with official Blue Verified Badge. High authority score and instant DM trust.", true)
        add("Luxury Mansions & Architecture (@luxury_homes)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "@luxury_villas_lifestyle", 450000, 720.0, "Real Estate & Luxury", "$610/mo (Promotions)", "Top tier luxury real estate theme page. Organic explore reach with prominent architects and brokers.", true)
        add("Millionaire Mindset & Success Quotes", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, true, "@millionaire_mindset_club", 380000, 540.0, "Motivation & Business", "$480/mo (Shoutouts)", "High engagement motivation brand. 85% US, UK, and Australian audience. Regular story shoutout orders.")
        add("Supercars & Hypercars Photography 4K", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "@hypercars_photography", 340000, 510.0, "Automotive", "$420/mo (Sponsors)", "Crisp professional shots of Bugatti, Pagani, and Koenigsegg. Active partnerships with car care brands.", true)
        add("Wanderlust & Earth Landscapes", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@earth_visuals_travel", 290000, 430.0, "Travel & Nature", "$350/mo (Tourism Boards)", "Spectacular destination reels with millions of views. High save and forward rates.")
        add("Streetwear Fashion & Sneaker Drops", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@streetstyle_sneakers_inspo", 220000, 370.0, "Fashion & Sneakers", "$310/mo (Brand Collabs)", "Sneaker release dates, Virgil Abloh tributes, and outfit of the day (OOTD) curations.")
        add("Modern Interior Design & Decor Ideas", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@interiors_minimalist_home", 275000, 410.0, "Interior Design", "$330/mo (Affiliate)", "Scandinavian minimalism and modern living spaces. Exceptional affiliate revenue from decor links.")
        add("Fitness Motivation & Transformation Daily", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@shredded_fitness_daily", 310000, 460.0, "Health & Fitness", "$390/mo (Supplements)", "Workout regimens, macro meal prep, and transformation reels. Supplement sponsorship inquiries weekly.")
        add("Crypto & Web3 Wealth Desk (@cryptovault)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "@cryptovault_official", 195000, 480.0, "Crypto & Finance", "$450/mo (Token Launches)", "Established cryptocurrency theme page. High engagement in Telegram link in bio.", true)
        add("Culinary Arts & Gourmet Food Porn", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@gourmet_chef_table", 260000, 380.0, "Food & Cooking", "$290/mo (Restaurant Collabs)", "Mouth-watering cheese pulls, steak sizzling, and gourmet plating reels.")

        // Instagram Broadcast Channels (Creator Channels)
        add("Alpha Trades Broadcast Channel (140k)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "ig.me/j/alpha_trades_broadcast", 140000, 360.0, "Trading Signals", "$280/mo (VIP Upsell)", "Interactive creator broadcast channel with 40%+ story and poll reaction rates.")
        add("Daily Tech Leaks Broadcast Channel", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "ig.me/j/tech_leaks_channel", 95000, 240.0, "Tech News", "$180/mo", "Direct member notifications for iPhone and Android leaks.")
        add("Sneaker Shock Drops Broadcast", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "ig.me/j/sneaker_shock_drops", 110000, 290.0, "Sneakers & Streetwear", "$240/mo (Affiliate)", "Instant DM alerts when Nike SNKRS or Adidas Yeezys drop. High conversion.")
        add("Motivational Daily Voice Broadcast", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "ig.me/j/motivational_voice_daily", 82000, 210.0, "Self Help", "$150/mo", "Audio voice note reflections and morning mantras.")

        // Rare & OG Instagram Handles
        add("Rare 4-Letter Handle @flow", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "@flow", 95000, 980.0, "OG Handle", "$0", "Prestigious single-word dictionary handle on Instagram. Includes Original Email (OGE).", true)
        add("Rare Handle @ton", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "@ton", 88000, 920.0, "Web3 Brand", "$0", "Clean 3-letter username perfect for TON ecosystem startup or token launch.", true)
        add("Rare Handle @glow", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@glow", 64000, 780.0, "Beauty & Cosmetics", "$0", "Clean 4-letter beauty brand handle. Highly sought-after keyword.")
        add("Rare Handle @peak", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@peak", 72000, 740.0, "App & Brand", "$0", "Clean dictionary word handle suitable for sports, outdoors, or tech brand.")

        // Theme Pages (Animals, Art, Gaming, Tech, Books)
        add("Cute Puppies & Golden Dogs Club", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@golden_pups_world", 330000, 420.0, "Pets", "$310/mo", "Wholesome viral dog content with loyal pet lover community.")
        add("Digital Art & Concept Illustration", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@digitalart_feature_hub", 215000, 310.0, "Art & Illustration", "$240/mo (Artist Features)", "Showcasing global 3D Blender and Procreate digital artists. Paid feature submissions.")
        add("Cyberpunk & Sci-Fi Aesthetic", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@cyberpunk_futuristic_city", 180000, 260.0, "Aesthetics & Sci-Fi", "$190/mo", "Neon-lit Tokyo street photography and futuristic AI renders.")
        add("Vintage Cinema & Film Archives", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@cinema_vintage_frames", 165000, 250.0, "Movies & Cinematography", "$180/mo", "Historic stills, legendary quotes, and behind the scenes of classic cinema.")
        add("Book Lovers & Literary Aesthetics", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@bookstagram_library_reads", 140000, 220.0, "Books & Reading", "$160/mo (Publisher Collabs)", "Cozy reading setups, annotated books, and literary reviews.")
        add("Watch Aficionado & Horology Vault", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "@horology_watches_vault", 190000, 460.0, "Luxury Watches", "$380/mo (Dealer Sponsors)", "Rolex, Patek Philippe, and Audemars Piguet macro wrist shots. High-wealth followers.", true)
        add("Gaming Setups & Battlestations", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@battlestations_setups", 230000, 340.0, "Gaming Gear", "$270/mo (Peripheral Brands)", "RGB lighting, dual-monitor desks, and custom water-cooled PC builds.")
        add("Nature Macro Photography & Insects", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@macro_nature_wonders", 120000, 190.0, "Photography", "$120/mo", "National Geographic style extreme closeups of wildlife and flora.")
        add("Daily Comedy Memes & Viral Reels", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@viral_memes_daily_fun", 480000, 490.0, "Humor & Memes", "$420/mo (Promo Apps)", "High viral reel velocity. Consistently hitting 1M+ views per reel.")
        add("Yoga & Mindful Meditation Sanctuary", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@yoga_mindful_sanctuary", 170000, 270.0, "Wellness & Yoga", "$220/mo", "Gentle stretching routines, breathing practices, and calm living.")
        add("Coffee Culture & Latte Art Masters", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@latte_art_coffeelovers", 155000, 230.0, "Coffee & Cafes", "$170/mo", "Espresso brewing techniques, specialty roasters, and barista competitions.")
        add("Motorcycles & Cafe Racer Customs", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@caferacers_custom_garage", 205000, 320.0, "Motorcycles", "$250/mo", "Bespoke custom builds, retro scramblers, and leather riding apparel.")
        add("Aesthetic Minimalist Desk Setups", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@minimalist_workspace", 240000, 350.0, "Workspaces", "$280/mo (Affiliate)", "Clean productivity setups, mechanical keyboards, and ambient studio light setups.")
        add("Sourdough Artisan Bakery & Pastry", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@artisan_bakers_feed", 195000, 280.0, "Baking & Pastry", "$210/mo", "Golden crispy sourdough crusts, croissant laminations, and pastry tutorials.")
        add("Urban Street Photography 35mm", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@35mm_street_visuals", 160000, 240.0, "Photography", "$180/mo", "Moody analog street photography captured across Tokyo, New York, and Paris.")
        add("Daily Stoic Affirmations & Quotes", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@stoic_daily_affirm", 280000, 390.0, "Mindset & Growth", "$310/mo (E-Book Sales)", "Marcus Aurelius and Seneca reflections. High community save count on every carousel.")

        return list
    }
}
