package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object FacebookSeedData {
    fun getListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 501L

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
                    platform = Platform.FACEBOOK,
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
                    sellerTelegram = "@FBMetaAssetsShop",
                    sellerWhatsApp = "+13125550144",
                    isFeatured = isFeatured
                )
            )
        }

        // Facebook Pages Monetized (In-Stream Ads & Performance Bonus)
        add("Daily Life Hacks & DIY (In-Stream Ads Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "facebook.com/DailyLifeHacksOfficial", 620000, 780.0, "DIY & Crafts", "$680/mo (In-Stream Ads)", "Fully monetized Meta business page with green policy badge. Active monthly payout to bank/PayPal.", true)
        add("Street Food & Master Recipes (In-Stream Ads)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "facebook.com/GlobalStreetFoodTours", 480000, 620.0, "Food & Cooking", "$540/mo (Meta Payouts)", "Engaged audience across US, UK, and South Asia. Videos consistently clear 500k+ 3-second views.", true)
        add("Wild Animal Encounters (Reels Bonus Enabled)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, true, "facebook.com/WildNatureEncountersHD", 350000, 480.0, "Nature & Animals", "$410/mo (Reels Bonus)", "Viral wildlife reels with active Meta performance bonus program approval. Clean strike dashboard.")
        add("Classic Golden Cinema Clips (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "facebook.com/GoldenCinemaRewind", 410000, 520.0, "Movies & Nostalgia", "$460/mo (In-Stream Ads)", "Nostalgic film clips and TV moments with high share velocity among 35+ demographic.")
        add("Mind-Blowing Science Facts (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "facebook.com/ScienceWondersDaily", 290000, 390.0, "Science & Education", "$340/mo (In-Stream Ads)", "Short educational explainers and fascinating science trivia. High engagement.")
        add("Restoration & Woodworking Crafts (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "facebook.com/AntiqueRestorationsLab", 380000, 510.0, "Restoration & Wood", "$430/mo (In-Stream Ads)", "Hypnotic rusty tool restorations. Exceptional watch time and CPM revenue.")
        add("Stand-up Comedy & Prank Reels (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "facebook.com/ComedyLaughClub", 530000, 590.0, "Comedy & Entertainment", "$510/mo (In-Stream Ads)", "Millions of monthly reel views with established organic follower base.", true)
        add("Health, Yoga & Wellness Tips (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "@HealthWellnessDigestFB", 240000, 340.0, "Health & Wellness", "$280/mo (Meta Payouts)", "Daily wellness checklists, natural remedies, and stretch routines.")
        add("Gaming Moments & Glitches TV (Monetized)", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "facebook.com/EpicGamingMomentsTV", 310000, 410.0, "Gaming & Esports", "$330/mo (Level Up / Stars)", "Facebook Gaming Level Up creator page with Stars and In-Stream ad monetization active.")
        add("Automotive News & Drag Races", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "facebook.com/SupercarDragRaces", 270000, 360.0, "Automotive", "$290/mo (Ads)", "Quarter-mile drag race battles and automotive reviews.")

        // Facebook Verified Pages (Blue Verification Badge)
        add("Verified Blue Badge Public Figure Page", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "facebook.com/OfficialVerifiedFigure", 150000, 920.0, "Verified Public Figure", "$750/mo (Brand Sponsors)", "Official Meta Blue Verified checkmark page. Instant trust score, suitable for brand rebranding.", true)
        add("Verified Blue Badge Media Publisher Page", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "facebook.com/GlobalNewsDigestMedia", 230000, 1100.0, "Verified News Brand", "$850/mo", "Official Verified News Publisher with instant article approval and top recommendation tier.", true)
        add("Verified Blue Badge Tech Influencer", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "facebook.com/TechReviewerPro", 95000, 790.0, "Verified Creator", "$580/mo", "Authoritative tech influencer presence with direct reach to Meta support representatives.", true)

        // Facebook Groups (Active Discussions & Communities)
        add("Real Estate Investors & Wholesalers (140k)", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "facebook.com/groups/realestate_wholesaling", 140000, 420.0, "Real Estate", "$350/mo (Pinned Sponsors)", "Massive group for deal finding, off-market properties, and hard money lending.")
        add("Amazon FBA & E-Commerce Sellers Hub", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/amazon_fba_sellers_hub", 95000, 310.0, "E-Commerce", "$240/mo (Software Sponsors)", "Discussion of Helium10 strategies, product validation, and supplier verifications.")
        add("Crypto & Bitcoin Traders Club (110k)", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "facebook.com/groups/crypto_bitcoin_club", 110000, 360.0, "Crypto Trading", "$310/mo", "Real-time chart discussions and technical analysis threads.")
        add("Moms & Parenting Advice Community (180k)", ItemCategory.GROUP, PrivacyType.PRIVATE, false, false, "facebook.com/groups/moms_parenting_circle", 180000, 390.0, "Parenting & Family", "$280/mo", "Supportive, loyal community of mothers sharing toddler advice, recipes, and recommendations.")
        add("Work From Home & Remote Tech Jobs (220k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/remote_jobs_workfromhome", 220000, 450.0, "Jobs & Careers", "$380/mo (Job Board Ads)", "Recruiters and job seekers networking daily. High job posting activity.")
        add("Gardening, Homesteading & Plants (165k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/gardening_plants_greenhouse", 165000, 320.0, "Home & Garden", "$210/mo", "Seed swapping, pest control tips, and beautiful backyard harvests.")
        add("Woodworking & Carpentry Projects (130k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/woodworking_custom_builds", 130000, 270.0, "Crafts & DIY", "$180/mo", "Custom furniture builds, epoxy tables, and CNC tool recommendations.")
        add("Vintage Cars & Classic Muscle Restorations", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/classic_muscle_cars_usa", 115000, 260.0, "Classic Cars", "$170/mo", "Barn finds, parts exchange, and show schedules.")
        add("Affiliate Marketing & Side Hustles (98k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/affiliate_side_hustles", 98000, 290.0, "Digital Marketing", "$250/mo", "Passive income case studies, ClickBank reviews, and funnel designs.")
        add("Home Decor & Thrifting Glitches (145k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/home_decor_thrift_finds", 145000, 310.0, "Decor & Shopping", "$220/mo", "IKEA hacks, thrift store flips, and vintage home aesthetic inspiration.")

        // More Groups & Pages
        add("Dog Lovers & Rescue Stories Group (210k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/dog_rescue_stories", 210000, 380.0, "Pets", "$260/mo", "Heartwarming pet recovery updates and adoption networks.")
        add("Baking & Cake Decorating Art (125k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/cake_decorating_art", 125000, 240.0, "Baking & Desserts", "$160/mo", "Fondant sculptures, wedding cakes, and sourdough bread guides.")
        add("Used Car Buy & Sell Marketplace Group (170k)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/used_cars_marketplace", 170000, 390.0, "Marketplace", "$310/mo (Featured Listings)", "Direct local auto deals with seller listing fees.")
        add("Photography Critiques & Lightroom Presets", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/photography_lightroom_hub", 88000, 210.0, "Photography", "$150/mo", "Before-and-after photo grading and free preset giveaways.")
        add("Indie Hacker & Software Solopreneurs", ItemCategory.GROUP, PrivacyType.PRIVATE, true, false, "facebook.com/groups/saas_solopreneurs", 56000, 280.0, "Software Business", "$230/mo", "Micro-conf discussion and software acquisition deals.")
        add("Camping, Hiking & Bushcraft Gear", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/bushcraft_camping_gear", 105000, 230.0, "Outdoors", "$160/mo", "Tent reviews, survival knives, and trail guides.")
        add("Meal Prep & Healthy Keto Recipes", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/keto_mealprep_healthy", 135000, 270.0, "Diet & Nutrition", "$200/mo", "Low-carb meal schedules and macro calculations.")
        add("Graphic Designers & Logo Portfolios", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/graphic_designers_showcase", 92000, 220.0, "Design & Branding", "$170/mo", "Client feedback, brand identity guidelines, and vector design tips.")
        add("Gold & Silver Bullion Coin Stackers", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/silver_gold_stackers", 78000, 260.0, "Precious Metals", "$190/mo", "Physical precious metals collecting, coin grading, and spot price discussions.")
        add("Solar Energy & Off-Grid Living Group", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/offgrid_solar_living", 112000, 290.0, "Off-Grid & Solar", "$210/mo", "Battery banks, solar inverters, and sustainable homesteading setups.")
        add("Classic Rock & Vinyl Record Collectors", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "facebook.com/groups/vintage_vinyl_records", 85000, 210.0, "Music & Vinyl", "$140/mo", "First pressings, turntable equipment, and record store discoveries.")

        return list
    }
}
