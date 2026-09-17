package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object TikTokSeedData {
    fun getListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 301L

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
                    platform = Platform.TIKTOK,
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
                    sellerTelegram = "@TikTokAgencyDesk",
                    sellerWhatsApp = "+13125550144",
                    isFeatured = isFeatured
                )
            )
        }

        // TikTok Creator Rewards Program (Monetized Beta Accounts)
        add("Supercars & Exotic Autos (US Creator Rewards)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "@supercars_daily_official", 480000, 680.0, "Automotive", "$620/mo (Rewards Beta)", "US tax-verified account enrolled in TikTok Creator Rewards. 100% original edits, high RPM tier-1 US views.", true)
        add("True Crime & Mystery Faceless (Monetized)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@darkfiles_mysteries", 320000, 540.0, "True Crime", "$510/mo (Rewards Beta)", "Viral storytelling account with high retention rate. Clean strike history with active monthly payouts.")
        add("Historical AI & Empires (Monetized UK)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@ancient_empires_ai", 260000, 470.0, "History & Facts", "$430/mo (Rewards Beta)", "UK IP created account. High RPM viewers across UK, US, and Canada. Ready for instant payout setup.")
        add("Street Food & Culinary ASMR (Monetized)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, false, "@streetfood_bites_us", 390000, 580.0, "Food & ASMR", "$480/mo (Creator Beta)", "Explosive viewer engagement with 40M+ total likes. Clean dashboard and eligible for brand sponsorships.", true)
        add("Dark Psychology & Stoic Mindset", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@stoic_code_mindset", 210000, 390.0, "Psychology & Motivation", "$360/mo (Creator Beta)", "Faceless quotes and psychological facts. Extremely high comment and share ratio.")
        add("Space & Cosmos Astronomy 4K (Monetized)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@deepspace_wonders", 175000, 340.0, "Science & Astronomy", "$290/mo (Rewards Beta)", "Engaging NASA and space visuals with immersive ambient audio. High retention rate.")
        add("Minecraft Parkour & Reddit Stories", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@reddit_vault_parkour", 290000, 420.0, "Entertainment", "$380/mo (Creator Beta)", "Turnkey automated story channel with templates and AI voiceovers ready for the buyer.")
        add("Luxury Lifestyle & Millionaire Aesthetic", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, false, "@billionaire_vault_hq", 410000, 640.0, "Luxury & Wealth", "$590/mo (Sponsors + Beta)", "High-end jewelry, yachts, and penthouse tours. High CPM demographic.", true)
        add("Gym Motivation & Bodybuilding Edits", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@beastmode_lift_daily", 240000, 380.0, "Fitness & Gym", "$310/mo (Creator Beta)", "Classic bodybuilding motivation and gym fails. High viral clip potential.")
        add("Anime Fights 4K 60FPS Edits", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@animex_amv_official", 520000, 690.0, "Anime & Gaming", "$580/mo (Rewards Beta)", "Over 65 million cumulative likes. Incredible explore algorithm push on every sound.")

        // TikTok Shop Affiliate Approved Accounts
        add("TikTok Shop US Affiliate (Ready to Earn)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, false, "@viralfinds_usa_shop", 28000, 290.0, "TikTok Shop", "$450/mo (Affiliate Commission)", "Approved for US TikTok Shop showcase. Thousands in generated GMV with top product samples.")
        add("Beauty & Skincare TikTok Shop Showcase", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@glowup_beautytok", 45000, 340.0, "Beauty & Skincare", "$520/mo (Shop Affiliate)", "High female US audience. Product showcase with samples sent directly by beauty brands.")
        add("Home Gadgets & Amazon Finds TikTok Shop", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@clever_home_hacks", 62000, 390.0, "Gadgets & Home", "$580/mo (Shop Affiliate)", "Viral household gadgets showcase with direct commission payouts to Stripe/PayPal.", true)
        add("Fashion & Outfit Inspo TikTok Shop", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@streetwear_fits_us", 38000, 310.0, "Fashion & Outfits", "$370/mo (Shop Affiliate)", "Showcase enabled with verified US identity. High cart conversion rate.")
        add("Tech Accessories & Desk Setup Shop", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@deskgoals_gear", 54000, 360.0, "Tech & Gadgets", "$410/mo (Shop Affiliate)", "Desk mats, mechanical keyboards, and ambient lights affiliate showcase.")

        // Verified & Rare TikTok Handles
        add("TikTok Verified Blue Checkmark Account", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "@VerifiedTalent", 85000, 890.0, "Verified Brand", "$650/mo", "Official blue verified checkmark. Prestigious presence suitable for artists, brands, or creators.", true)
        add("Rare 4-Letter OG Handle @vibe", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@vibe", 110000, 750.0, "OG Handle", "$0", "Ultra-rare clean single-word OG handle. Clean username transfer with original email (OGE).", true)
        add("Rare 3-Letter Username @ton", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "@ton", 95000, 820.0, "Web3 & Crypto", "$0", "Prestigious 3-letter cryptocurrency handle on TikTok with zero strikes.")
        add("Rare 4-Letter Username @luxe", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, true, "@luxe", 65000, 560.0, "Luxury Fashion", "$0", "Clean dictionary word handle perfect for high-end fashion label or boutique.")

        // TikTok LIVE Studio & Gaming Accounts
        add("TikTok LIVE Gaming (Studio Key Enabled)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, false, "@streamer_gaming_live", 42000, 260.0, "Gaming LIVE", "$320/mo (Live Gifts)", "Unlocked TikTok Live Studio desktop streaming key (OBS / Streamlabs compatible).")
        add("Call of Duty & Warzone Clips LIVE", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@warzone_clutches_tv", 78000, 310.0, "Gaming & Esports", "$280/mo (Live Gifts)", "Active live streamer base with daily diamonds and gift earnings.")
        add("Fortnite Highlights & Trickshots", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@fortnite_vault_clips", 115000, 280.0, "Gaming", "$190/mo", "Engaged teenage gaming audience. Rapid clip sharing and sound usage.")
        add("GTA V Roleplay Cinematic Shorts", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@gtav_rp_moments", 165000, 320.0, "Gaming & RP", "$240/mo", "High-retention cinematic server clips and police chase stories.")

        // Animals, Nature & Humor Niches
        add("Cute Pets & Golden Retrievers (Viral)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@golden_puppy_daily", 340000, 430.0, "Pets & Animals", "$350/mo (Rewards Beta)", "Adorable puppy videos with 90% positive sentiment and organic viral reach.")
        add("Wild Nature & Predator Encounters", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@wildlife_safari_hd", 220000, 360.0, "Nature & Wildlife", "$290/mo (Rewards Beta)", "4K wildlife documentaries and safari encounters.")
        add("Daily Standup Comedy & Crowd Work", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@standup_comedy_clips", 380000, 480.0, "Comedy & Humor", "$420/mo (Creator Beta)", "Viral comedy club moments with high stitch and duet engagement.")
        add("Oddly Satisfying Soap & Hydraulic Press", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@oddly_satisfying_lab", 460000, 510.0, "ASMR & Satisfying", "$450/mo (Rewards Beta)", "Hypnotic ASMR crushing clips with immense watch-time metrics.")
        add("Life Hacks & Cleaning Transformations", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@deepclean_hacks_pro", 190000, 290.0, "DIY & Home", "$230/mo", "Restoration and cleaning satisfaction videos. Ideal for affiliate products.")
        add("Football Freestyle & Skills Hub", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@freestyle_skills_tok", 270000, 360.0, "Sports Skills", "$280/mo", "Street football tricks and tutorial clips.")
        add("Guitar & Piano Cover Melodies", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@acoustic_covers_vibes", 145000, 240.0, "Music & Covers", "$180/mo", "Soulful instrumentals with established community of music fans.")
        add("Film & Cinema Color Grading Edits", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@cinema_grading_aesthetics", 185000, 290.0, "Movies & Filmmaking", "$220/mo", "Aesthetic 4K cinematic scenes analyzing lighting and cinematography.")
        add("Daily Motivational Speeches & Wealth", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@wealth_motivation_tok", 250000, 360.0, "Motivation", "$290/mo (Creator Beta)", "Subtitled podcasts, founder interviews, and high-retention cinematic b-roll.")
        add("Woodworking & Resin Art Transformations", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@resin_wood_crafts", 210000, 330.0, "Crafts & DIY", "$260/mo", "Liquid glass river tables and carpentry satisfying time-lapses.")
        add("Sneaker Cleaning & Custom Restorations", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "@sneaker_restores_lab", 175000, 280.0, "Sneakers & Fashion", "$210/mo", "Dirty thrifted Jordan restorations with ASMR scrubbing audio.")

        return list
    }
}
