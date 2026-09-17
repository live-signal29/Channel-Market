package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object WhatsAppSeedData {
    fun getListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 201L

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
                    platform = Platform.WHATSAPP,
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
                    sellerTelegram = "@WhatsAppAssetBroker",
                    sellerWhatsApp = "+13125550144",
                    isFeatured = isFeatured
                )
            )
        }

        // WhatsApp Channels (Public & High Followers)
        add("TechRadar Deals & News Channel", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, true, "whatsapp.com/channel/techradar_deals", 340000, 480.0, "Tech & Gadgets", "$350/mo (Affiliate)", "Official verified WhatsApp broadcast channel sharing tech deals and mobile releases.", true)
        add("Crypto Daily Alerts & Signals", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "whatsapp.com/channel/crypto_daily_alerts", 210000, 390.0, "Crypto & Finance", "$280/mo (Sponsored)", "Fast-growing WhatsApp crypto updates channel with engaged organic followers.")
        add("Premier League Live Scores & News", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "whatsapp.com/channel/pl_football_scores", 420000, 520.0, "Sports", "$410/mo (Sponsors)", "High traffic weekend broadcasts for live football commentary and match highlights.", true)
        add("Amazon Loot & Price Error Glitches", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "whatsapp.com/channel/amazon_loot_glitches", 285000, 360.0, "Shopping & Deals", "$320/mo (Affiliate)", "Instant flash coupon notifications. Top-tier click-through buyer traffic.")
        add("Global Breaking News Headlines", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "whatsapp.com/channel/global_breaking_news", 510000, 650.0, "News & Media", "$490/mo", "Fast reliable news digests with verified publisher badge.", true)
        add("Daily Motivation & Life Mastery", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "whatsapp.com/channel/daily_motivation_quotes", 160000, 220.0, "Self Improvement", "$140/mo", "Aesthetic motivational quote cards, book summaries, and wellness advice.")
        add("Stock Market & Forex Daily Watch", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "whatsapp.com/channel/stock_forex_daily", 140000, 290.0, "Finance", "$230/mo", "Morning market briefing with key indices and commodity prices.")
        add("Android & iOS Tech Hacks", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "whatsapp.com/channel/mobile_tech_hacks", 195000, 250.0, "Software Tips", "$170/mo", "Hidden mobile settings, useful productivity apps, and shortcuts.")
        add("Luxury Real Estate & Mansions", ItemCategory.CHANNEL, PrivacyType.PUBLIC, true, false, "whatsapp.com/channel/luxury_mansions_intl", 125000, 310.0, "Real Estate", "$200/mo", "Architectural masterpieces, private estates in Dubai, Miami, and London.")
        add("Hollywood Cinema Trailers & OTT", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "whatsapp.com/channel/hollywood_ott_releases", 380000, 440.0, "Entertainment", "$330/mo", "Teasers, release schedules, and reviews for Netflix, Prime, and Disney+.")
        add("AI Tools & ChatGPT Prompts", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, true, "whatsapp.com/channel/ai_tools_daily_chat", 175000, 280.0, "AI & Productivity", "$210/mo", "Curated prompt libraries and AI tool directory updates.")
        add("Government Jobs & Career Alerts", ItemCategory.CHANNEL, PrivacyType.PUBLIC, false, false, "whatsapp.com/channel/career_govt_jobs", 310000, 340.0, "Education & Jobs", "$260/mo", "Timely employment circulars, test syllabus, and exam notifications.")

        // WhatsApp Groups (Community & Discussions)
        add("Crypto Whales VIP Discussion", ItemCategory.GROUP, PrivacyType.PRIVATE, true, false, "chat.whatsapp.com/invite/crypto_whales_vip", 1024, 190.0, "Crypto Trading", "$160/mo", "Maxed out WhatsApp group (1,024 members) with loyal high-roller trading base.")
        add("Forex Gold (XAUUSD) Scalpers Club", ItemCategory.GROUP, PrivacyType.PRIVATE, true, false, "chat.whatsapp.com/invite/forex_gold_scalpers", 1024, 210.0, "Forex", "$180/mo", "Active day traders sharing NY session levels. Constant active discussions.")
        add("Shopify & Dropshipping Mastermind", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/shopify_dropship_hub", 980, 160.0, "E-Commerce", "$130/mo", "7-figure store owners troubleshooting Facebook ads and TikTok creatives.")
        add("Full-Stack Developers Helpdesk", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/fullstack_devs_group", 1024, 140.0, "Programming", "$0", "Collaborative developer group solving React, Node.js, and Python bugs.")
        add("Digital Marketing & Media Buyers", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/digital_media_buyers", 950, 170.0, "Marketing", "$150/mo", "Google Ads, agency scaling, and lead gen discussions.")
        add("Dubai Real Estate Investors Club", ItemCategory.GROUP, PrivacyType.PRIVATE, true, false, "chat.whatsapp.com/invite/dubai_investors_club", 880, 320.0, "Real Estate", "$290/mo", "Off-plan developments and rental yield discussions in UAE.")
        add("IELTS Speaking & Academic Practice", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/ielts_speaking_room", 1024, 120.0, "Education", "$0", "Active voice note practice and grammar corrections among students.")
        add("Amazon FBA Private Label Sellers", ItemCategory.GROUP, PrivacyType.PRIVATE, false, false, "chat.whatsapp.com/invite/amazon_fba_elite", 910, 200.0, "Amazon FBA", "$170/mo", "Product research, supplier sourcing, and PPC optimizations.")
        add("Car Enthusiasts & Track Days", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/car_enthusiasts_club", 990, 110.0, "Automotive", "$0", "Modifications, tuning meetups, and track day schedules.")
        add("Graphic Designers & Freelancers", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/freelance_designers", 1024, 130.0, "Design", "$0", "Client proposals, logo reviews, and freelance referral sharing.")

        // WhatsApp Business Accounts & Communities
        add("WhatsApp Verified Business (+Green Tick)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "+44 7700 900821", 50000, 580.0, "Business Brand", "$420/mo", "Official Meta Verified green checkmark business profile with Cloud API ready.", true)
        add("US E-Commerce Support Business Profile", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, false, "+1 302 555 0192", 28000, 340.0, "Customer Service", "$210/mo", "US registered business phone number with active catalog and payment link setup.")
        add("Luxury Concierge Service (+Green Tick)", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, true, "+971 50 123 4567", 42000, 690.0, "Concierge & Travel", "$550/mo", "UAE verified luxury brand number for VIP bookings and ticketing.", true)
        add("Crypto Exchange Support Account", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, true, false, "+44 7400 123987", 35000, 420.0, "Crypto Business", "$310/mo", "2-tier business account with automated chatbot integration.")
        add("Online Academy WhatsApp Community (5 Groups)", ItemCategory.GROUP, PrivacyType.PUBLIC, false, true, "whatsapp.com/community/online_academy", 4800, 280.0, "EdTech", "$220/mo", "Organized WhatsApp Community containing 5 interconnected subject groups.")
        add("Fitness Coaching Client Community", ItemCategory.GROUP, PrivacyType.PRIVATE, false, false, "whatsapp.com/community/fitness_coaching", 2400, 210.0, "Fitness & Health", "$190/mo", "Structured community with workout challenges and nutrition guidance.")
        add("Travel Deals & Flight Glitches Community", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "whatsapp.com/community/flight_deals_glitch", 3600, 260.0, "Travel", "$200/mo", "Multi-group community alerting members to mistake fares and hotel promo codes.")
        add("Startup Pitch & Angel Community Hub", ItemCategory.GROUP, PrivacyType.PRIVATE, true, false, "whatsapp.com/community/startup_angel_hub", 1900, 350.0, "Venture Capital", "$270/mo", "High value founder network organized into announcement and regional groups.")
        add("Wedding & Event Planners Network", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/event_planners_net", 1024, 150.0, "Events & Wedding", "$0", "Vendor recommendations, catering contacts, and venue availability.")
        add("Wholesale Electronics B2B Catalog", ItemCategory.USER_ACCOUNT, PrivacyType.PUBLIC, false, false, "+86 138 0013 8000", 18500, 270.0, "Wholesale B2B", "$240/mo", "Shenzhen supply chain contact profile with established order history.")
        add("Spanish & English Language Exchange", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/language_exchange_es_en", 1024, 120.0, "Languages", "$0", "Bilingual language practice exchange group with active daily voice notes.")
        add("Gourmet Home Cooking & Sourdough Club", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "chat.whatsapp.com/invite/sourdough_cooking_club", 950, 140.0, "Culinary", "$0", "Artisan sourdough bread recipes and kitchen gadgets discussions.")

        return list
    }
}
