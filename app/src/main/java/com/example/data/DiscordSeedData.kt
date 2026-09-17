package com.example.data

import com.example.model.ItemCategory
import com.example.model.Platform
import com.example.model.PrivacyType
import com.example.model.TonAdsStatus

object DiscordSeedData {
    fun getListings(): List<ListingEntity> {
        val list = mutableListOf<ListingEntity>()
        var id = 701L

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
                    platform = Platform.DISCORD,
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
                    sellerTelegram = "@DiscordVaultBroker",
                    sellerWhatsApp = "+13125550144",
                    isFeatured = isFeatured
                )
            )
        }

        // Boosted Level 3 Gaming & Community Servers
        add("Esports & Competitive Gaming (Lvl 3 Boosted)", ItemCategory.GROUP, PrivacyType.PUBLIC, true, true, "discord.gg/esports_champions", 68000, 480.0, "Gaming & Esports", "$380/mo (Server Boosts)", "Level 3 boosted Discord server with custom vanity URL .gg/esports_champions. 250+ custom animated emojis, 384Kbps voice channels.", true)
        add("Minecraft Community & Survival SMP Hub", ItemCategory.GROUP, PrivacyType.PUBLIC, false, true, "discord.gg/blockcraft_smp", 52000, 390.0, "Minecraft Gaming", "$310/mo (In-game Ranks)", "Linked directly to established Minecraft server. In-game store integration generating monthly rank revenues.")
        add("Anime Hangout & Manga Discussion Sanctuary", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "discord.gg/anime_sanctuary", 85000, 520.0, "Anime & Manga", "$420/mo (Nitro Boosts)", "Vibrant community discussing seasonal releases. Custom Karuta, Mudae, and OwO bots configured.", true)
        add("Valorant & FPS LFG Matchmaking Server", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/valorant_lfg_hub", 64000, 360.0, "FPS Gaming", "$240/mo", "Automated team matchmaking channels and scrim tournaments. 24/7 active voice rooms.")
        add("Roblox Devs & Studio Creators Guild", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "discord.gg/roblox_creators_guild", 45000, 320.0, "Roblox & Dev", "$270/mo", "Developers sharing Lua scripts, 3D meshes, and hiring builders.")
        add("GTA V FiveM Roleplay Official Server (Whitelisted)", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "discord.gg/sanandreas_state_rp", 38000, 460.0, "FiveM Roleplay", "$410/mo (Donation Tiers)", "Custom vehicle textures, police CAD system, and whitelisted civilian applications.")
        add("Genshin Impact & Honkai Star Rail Club", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/genshin_stargazers", 59000, 340.0, "Gacha Gaming", "$230/mo", "Spiral Abyss builds, character guides, and primogem leak discussions.")
        add("League of Legends Clash & Ranked LFG", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/summoners_clash_rift", 48000, 290.0, "MOBA Gaming", "$180/mo", "Dedicated tier-based voice channels for 5v5 flex queue and Clash tournaments.")
        add("Fortnite Scrims & Tournament Practice", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/fortnite_pro_scrims", 54000, 310.0, "Battle Royale", "$210/mo", "Automated custom matchmaking bot hosting daily scrim matches.")
        add("Indie Game Developers Showcase & Jam", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/indiedevs_community", 32000, 240.0, "Game Development", "$150/mo", "Playtesting channels, devlog streams, and game jam hosting.")

        // Crypto & Web3 Alpha Discord Servers
        add("Alpha Syndicate Crypto & Whales (VIP Tier)", ItemCategory.GROUP, PrivacyType.PRIVATE, true, true, "discord.gg/alpha_syndicate_vip", 28000, 650.0, "Crypto Trading", "$590/mo (Subscriptions)", "Whitelisted membership server with Whop / Collab.Land integration. High subscription retention.", true)
        add("Solana Memecoin & DEX Callers Lounge", ItemCategory.GROUP, PrivacyType.PUBLIC, false, true, "discord.gg/solana_degens_hub", 39000, 440.0, "Solana & DePIN", "$360/mo (Sponsors)", "Raydium sniping bots, photon alerts, and caller leaderboards.")
        add("NFT Whitelist & Airdrop Farming Hub", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/airdrop_farming_net", 42000, 350.0, "Web3 & Airdrops", "$280/mo", "Step-by-step testnet guides and token claims notifications.")
        add("Algo Trading & Python Bot Developers", ItemCategory.GROUP, PrivacyType.PRIVATE, true, false, "discord.gg/algo_trading_bots", 19500, 380.0, "Trading Tech", "$290/mo", "Quantitative traders sharing backtesting frameworks and API connectors.")

        // Coding, AI & Tech Communities
        add("The Developers Lounge (Full Stack & Python)", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "discord.gg/dev_lounge_global", 72000, 430.0, "Programming", "$320/mo", "Active help forums for TypeScript, Rust, Python, and cloud infrastructure.")
        add("AI Engineering & LLM Hackers Circle", ItemCategory.GROUP, PrivacyType.PUBLIC, true, true, "discord.gg/ai_engineers_circle", 36000, 460.0, "AI & Machine Learning", "$380/mo", "Prompt engineering, local LLM quantization, and LangChain orchestration.", true)
        add("Cybersecurity & CTF Hackers Guild", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "discord.gg/ctf_hackers_guild", 44000, 370.0, "Cybersecurity", "$260/mo", "Penetration testing writeups and active ethical hacking challenges.")
        add("UI/UX Designers & Figma Creators Hub", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/figma_designers_hub", 31000, 250.0, "UI/UX Design", "$190/mo", "Design critiques, component library swaps, and freelance job leads.")

        // Music, Lo-Fi & Study Servers
        add("Study With Me & Productivity Pomodoro", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/study_together_pomodoro", 91000, 490.0, "Productivity & Study", "$350/mo (Nitro Boosts)", "Camera-on study rooms with automated 25/5 pomodoro timers and ambient music bots.")
        add("Lo-Fi Chill & Beats Producer Studio", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/lofi_beats_studio", 38000, 260.0, "Music Production", "$180/mo", "Sample sharing, beat battles, and FL Studio / Ableton workflow feedback.")
        add("Language Exchange & Polyglot Club", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/language_exchange_club", 62000, 340.0, "Languages & Culture", "$220/mo", "Rooms for English, Spanish, Japanese, French, and Mandarin practice.")

        // Discord Bots & Development
        add("Automated Server Security & Anti-Raid Bot", ItemCategory.BOT, PrivacyType.PUBLIC, true, false, "AutoShield Bot#4901", 120000, 390.0, "Security Bot", "$260/mo (Premium Tiers)", "Custom verified security bot defending against spam and mass pings across 300+ servers.")
        add("Leveling & Economy Bot (150+ Servers)", ItemCategory.BOT, PrivacyType.PUBLIC, true, false, "EcoLevel Bot#1102", 95000, 340.0, "Economy Bot", "$220/mo (Subscriptions)", "Custom economy bot with gambling, shop, and level cards.")
        add("AI Image Generator Midjourney-Style Bot", ItemCategory.BOT, PrivacyType.PUBLIC, false, true, "DreamAI Bot#7740", 68000, 370.0, "AI Discord Bot", "$290/mo", "Generates custom Stable Diffusion images directly inside chat commands.")

        // Social, Chill & Content Creator Servers
        add("Twitch Streamers & VTubers Collaboration Hub", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/vtuber_streamers_hub", 34000, 260.0, "Content Creators", "$190/mo", "Raid trains, asset sharing (3D models, overlays), and networking.")
        add("Book Club & Fantasy Worldbuilding", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/fantasy_worldbuilders", 23000, 180.0, "Literature & Writing", "$110/mo", "Monthly book votes and critique sessions for aspiring fantasy authors.")
        add("Fitness Accountability & Daily Workouts", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/fitness_gains_club", 29000, 210.0, "Fitness", "$140/mo", "Daily workout logs, PR celebration channels, and nutrition advice.")
        add("Cinema & Movie Night Streaming Club", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/cinema_night_streamers", 41000, 270.0, "Movies", "$190/mo", "Weekly coordinated movie watch parties and film analysis.")
        add("Mechanical Keyboards & Custom Audio", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/mechkeyboards_vault", 27000, 220.0, "Custom Keyboards", "$150/mo", "Sound tests, group buy schedules, and soldering tutorials.")
        add("Philosophy & Debate Society Server", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/philosophy_debate_soc", 33000, 230.0, "Debate & Philosophy", "$160/mo", "Structured voice debate rooms with designated moderators.")
        add("Memes & Internet Culture Archive", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/internet_culture_memes", 76000, 380.0, "Memes & Humor", "$260/mo", "Constant stream of trending memes, media edits, and reaction clips.")
        add("Car Enthusiasts & Track Days Discord", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/trackday_car_enthusiasts", 45000, 270.0, "Automotive & Sim", "$180/mo", "Sim racing tournaments, iRacing leagues, and real project car builds.")
        add("3D Artists, Blender & Unreal Engine Hub", ItemCategory.GROUP, PrivacyType.PUBLIC, true, false, "discord.gg/blender_unreal_creators", 58000, 390.0, "3D & Game Dev", "$270/mo", "Model showcases, texture downloads, and game studio recruitment.")
        add("Sci-Fi & Cyberpunk Worldbuilding Server", ItemCategory.GROUP, PrivacyType.PUBLIC, false, false, "discord.gg/cyberpunk_worldbuilders", 31000, 210.0, "Sci-Fi & Lore", "$150/mo", "Deep worldbuilding lore, futuristic concept art, and novel brainstorming.")

        return list
    }
}
