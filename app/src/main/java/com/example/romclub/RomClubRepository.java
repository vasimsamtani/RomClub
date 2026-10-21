package com.example.romclub;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class RomClubRepository {
    private static final String PREF_BOOKMARKS = "romclub_bookmarks_pref";
    private static final String KEY_SAVED_IDS = "saved_item_ids";

    private final SharedPreferences bookmarkPrefs;
    private final List<RomModel> romsList = new ArrayList<>();
    private final List<ModuleModel> modulesList = new ArrayList<>();
    private final List<GuideModel> guidesList = new ArrayList<>();

    public RomClubRepository(Context context) {
        this.bookmarkPrefs = context.getSharedPreferences(PREF_BOOKMARKS, Context.MODE_PRIVATE);
        initData();
    }

    private void initData() {
        // --- CUSTOM ROMS ---
        romsList.add(new RomModel(
                "rom_pixelos_alioth",
                "PixelOS 14.0",
                "alioth",
                "14",
                "Official",
                "Gwolf2u",
                "2024-03-15",
                "1.9 GB",
                "• Merged March 2024 Security Patch\n• Leica Camera 5.0 included\n• Improved battery life and thermals\n• Fixed Dolby Atmos profile switching",
                "https://pixelos.net/download/alioth",
                "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
                "ROM",
                true,
                "https://pixelos.net",
                "https://t.me/PixelOSChat",
                "https://github.com/PixelOS-AOSP"
        ));

        romsList.add(new RomModel(
                "rom_evox_alioth",
                "Evolution X 9.0",
                "alioth",
                "15",
                "Official",
                "Akash",
                "2024-04-02",
                "2.1 GB",
                "• Initial Android 15 Beta Bringup\n• Evolution X The Evolver Customizations\n• KernelSU 0.9.5 built-in\n• Unlimited Google Photos backup spoofing",
                "https://evolution-x.org/device/alioth",
                "8f4e2b1928bc3a918dbec248995fc81427ae41e4649b934ca495991b7852c912",
                "ROM",
                true,
                "https://evolution-x.org",
                "https://t.me/EvolutionX",
                "https://github.com/Evolution-X"
        ));

        romsList.add(new RomModel(
                "rom_crdroid_alioth",
                "crDroid v10.4",
                "alioth",
                "14",
                "Official",
                "Ray",
                "2024-03-20",
                "1.4 GB",
                "• Vanilla build (NikGapps recommended)\n• Full crDroid Settings & UI customizations\n• Fixed refresh rate switching (60/90/120Hz)\n• Synced with latest crDroid sources",
                "https://crdroid.net/alioth/10",
                "5d41402abc4b2a76b9719d911017c592",
                "ROM",
                false,
                "https://crdroid.net",
                "https://t.me/crdroidcore",
                "https://github.com/crdroidandroid"
        ));

        romsList.add(new RomModel(
                "rom_aospa_alioth",
                "Paranoid Android Uvite 14",
                "alioth",
                "14",
                "Official",
                "AOSPA Team",
                "2024-03-28",
                "2.0 GB",
                "• Paranoid Android Uvite based on Android 14\n• Customized Paranoid Settings & Quick Settings\n• Full Leica 5.0 integration\n• Advanced thermal & battery management",
                "https://paranoidandroid.co/download/alioth",
                "7c8d9e0f1a2b3c4d5e6f7a8b9c0d1e2f",
                "ROM",
                true,
                "https://paranoidandroid.co",
                "https://t.me/paranoidandroidofficial",
                "https://github.com/AOSPA"
        ));

        romsList.add(new RomModel(
                "rom_lineage_cheetah",
                "LineageOS 21",
                "cheetah",
                "14",
                "Official",
                "Lineage Team",
                "2024-03-10",
                "1.2 GB",
                "• Official LineageOS 21 with Android 14\n• Pure clean AOSP experience\n• Fast and lightweight\n• Weekly OTA updates supported",
                "https://download.lineageos.org/devices/cheetah",
                "4a5b6c7d8e9f0123456789abcdef0123",
                "ROM",
                false,
                "https://lineageos.org",
                "https://reddit.com/r/LineageOS",
                "https://github.com/LineageOS"
        ));

        romsList.add(new RomModel(
                "rom_pixelos_cheetah",
                "PixelOS 14",
                "cheetah",
                "14",
                "Official",
                "Pranav",
                "2024-03-25",
                "2.2 GB",
                "• Pure Pixel experience with subtle enhancements\n• Google Camera 9.2 fully functional\n• Extreme battery saver improvements\n• Smooth 120Hz UI",
                "https://pixelos.net/download/cheetah",
                "3c4d5e6f7a8b9c0d1e2f3a4b5c6d7e8f",
                "ROM",
                true,
                "https://pixelos.net",
                "https://t.me/PixelOSChat",
                "https://github.com/PixelOS-AOSP"
        ));

        romsList.add(new RomModel(
                "rom_risingos_cheetah",
                "RisingOS 3.0 Elysium",
                "cheetah",
                "14",
                "Official",
                "RisingTech",
                "2024-04-05",
                "2.1 GB",
                "• RisingOS 3.0 based on Android 14 QPR3\n• Deep UI customization & Monet theming\n• Game Space v3 with GPU boost\n• Google Camera 9.2 fully operational",
                "https://risingoss.org/download/cheetah",
                "1a2b3c4d5e6f7a8b9c0d1e2f3a4b5c6d",
                "ROM",
                true,
                "https://risingoss.org",
                "https://t.me/RisingOSUpdates",
                "https://github.com/RisingTechOSS"
        ));

        romsList.add(new RomModel(
                "rom_evox_sweet",
                "Evolution X 8.5",
                "sweet",
                "14",
                "Official",
                "Aryan",
                "2024-03-18",
                "1.8 GB",
                "• Android 14 with March SPL\n• 108MP camera fully operational\n• Fast charging optimized\n• SafetyNet and Play Integrity pass out of the box",
                "https://evolution-x.org/device/sweet",
                "1a2b3c4d5e6f708192a3b4c5d6e7f809",
                "ROM",
                true,
                "https://evolution-x.org",
                "https://t.me/EvolutionX",
                "https://github.com/Evolution-X"
        ));

        romsList.add(new RomModel(
                "rom_derpfest_sweet",
                "DerpFest 14 Tangy",
                "sweet",
                "14",
                "Official",
                "DerpTeam",
                "2024-03-24",
                "1.9 GB",
                "• DerpFest Android 14 official release\n• DerpSpace customization engine\n• Built-in KernelSU root\n• Smooth 120Hz refresh rate lock",
                "https://derpfest.org/download/sweet",
                "8b7c6d5e4f3a2b1c0d9e8f7a6b5c4d3e",
                "ROM",
                true,
                "https://derpfest.org",
                "https://t.me/DerpFest5G",
                "https://github.com/DerpFest-AOSP"
        ));

        romsList.add(new RomModel(
                "rom_matrixx_marble",
                "Project Matrixx 10.4",
                "marble",
                "14",
                "Official",
                "Matrixx Dev",
                "2024-03-22",
                "2.0 GB",
                "• Matrixx Android 14 bringup for POCO F5\n• Matrixx Central customizer\n• Dolby Atmos & Leica Camera included",
                "https://projectmatrixx.org/device/marble",
                "5c6d7e8f9a0b1c2d3e4f5a6b7c8d9e0f",
                "ROM",
                true,
                "https://projectmatrixx.org",
                "https://t.me/ProjectMatrixx",
                "https://github.com/ProjectMatrixx"
        ));

        romsList.add(new RomModel(
                "rom_lineage_lemonade",
                "LineageOS 21",
                "lemonade",
                "14",
                "Official",
                "LuK1337",
                "2024-03-12",
                "1.5 GB",
                "• OxygenOS camera included\n• Alert slider fully working\n• Clean LineageOS 21 Android 14\n• Official signed builds",
                "https://download.lineageos.org/devices/lemonade",
                "7f8e9d0c1b2a3f4e5d6c7b8a9f0e1d2c",
                "ROM",
                false,
                "https://lineageos.org",
                "https://reddit.com/r/LineageOS",
                "https://github.com/LineageOS"
        ));

        // Generic fallback ROMs shown for any device
        romsList.add(new RomModel(
                "rom_generic_gsi",
                "LineageOS 21 GSI (Treble)",
                "all",
                "14",
                "Official",
                "AndyCGyan",
                "2024-03-01",
                "1.1 GB",
                "• Generic System Image for any Project Treble ARM64 device\n• Android 14 pure base\n• A/B partition support",
                "https://github.com/phhusson/treble_experimentations/releases",
                "9a8b7c6d5e4f3a2b1c0d9e8f7a6b5c4d",
                "ROM",
                false,
                "https://github.com/phhusson/treble_experimentations",
                "https://t.me/phhtreble",
                "https://github.com/phhusson/treble_experimentations"
        ));

        romsList.add(new RomModel(
                "rom_arrowos_all",
                "ArrowOS 14.0 (Community)",
                "all",
                "14",
                "Official",
                "Arrow Team",
                "2024-03-16",
                "1.3 GB",
                "• Ultra-clean minimalist AOSP base\n• Maximum battery life and low thermal footprint\n• Available for all Treble devices and community targets",
                "https://arrowos.net/download",
                "4d3e2f1a0b9c8d7e6f5a4b3c2d1e0f9a",
                "ROM",
                false,
                "https://arrowos.net",
                "https://t.me/ArrowOS",
                "https://github.com/ArrowOS"
        ));

        romsList.add(new RomModel(
                "rom_pixelexperience_all",
                "Pixel Experience 14 Plus",
                "all",
                "14",
                "Official",
                "jhenrique09",
                "2024-03-20",
                "1.8 GB",
                "• Google Pixel software experience with extra settings\n• Pixel launcher, Google dialer, Recorder, and Wallpaper & Style\n• Pass integrity out-of-the-box",
                "https://download.pixelexperience.org",
                "3e2f1a0b9c8d7e6f5a4b3c2d1e0f9a8b",
                "ROM",
                true,
                "https://download.pixelexperience.org",
                "https://t.me/pixelecosystem",
                "https://github.com/PixelExperience"
        ));

        // --- KERNELS ---
        romsList.add(new RomModel(
                "kernel_no_gravity_alioth",
                "NoGravity Kernel v4.2",
                "alioth",
                "14",
                "Official",
                "Pierre",
                "2024-03-14",
                "32 MB",
                "• Built with Proton Clang 18\n• KernelSU 0.9.5 included\n• Dynamic Stune Boost & Adreno Boost\n• Undervolted CPU & GPU frequencies for thermal stability",
                "https://github.com/Pierre2324/NGK_Alioth/releases",
                "6b7c8d9e0f1a2b3c4d5e6f708192a3b4",
                "KERNEL",
                false,
                "https://github.com/Pierre2324/NGK_Alioth",
                "https://t.me/nogravitydevelopment",
                "https://github.com/Pierre2324/NGK_Alioth"
        ));

        romsList.add(new RomModel(
                "kernel_sultan_cheetah",
                "Sultan Kernel v2.1",
                "cheetah",
                "14",
                "Official",
                "Sultanxda",
                "2024-03-08",
                "28 MB",
                "• Custom thermal governor for Tensor G2\n• Massive reduction in idle battery drain\n• Clean upstream Linux LTS patches",
                "https://github.com/kernel-sultan/tensor-kernel/releases",
                "3f2e1d0c9b8a7f6e5d4c3b2a1f0e9d8c",
                "KERNEL",
                false,
                "https://github.com/kernel-sultan/tensor-kernel",
                "https://xdaforums.com",
                "https://github.com/kernel-sultan/tensor-kernel"
        ));

        romsList.add(new RomModel(
                "kernel_mgl_sweet",
                "MGL Kernel v3.0",
                "sweet",
                "14",
                "Official",
                "Mega",
                "2024-02-28",
                "25 MB",
                "• Overclocked GPU to 950MHz for gaming\n• KernelSU root support\n• Optimized for battery and charging speed",
                "https://github.com/sweet-kernels/mgl/releases",
                "5a4b3c2d1e0f9a8b7c6d5e4f3a2b1c0d",
                "KERNEL",
                false,
                "https://github.com/sweet-kernels/mgl",
                "https://t.me/sweetkernels",
                "https://github.com/sweet-kernels/mgl"
        ));

        // --- RECOVERIES ---
        romsList.add(new RomModel(
                "rec_orangefox_alioth",
                "OrangeFox Recovery R11.1",
                "alioth",
                "14",
                "Official",
                "DarthJ躲",
                "2024-02-15",
                "64 MB",
                "• Supports Android 14 dynamic partitions & encryption\n• Built-in Magisk installer\n• Automatic backup & restore system",
                "https://orangefox.download/device/alioth",
                "2a3b4c5d6e7f8091a2b3c4d5e6f70819",
                "RECOVERY",
                false,
                "https://orangefox.download",
                "https://t.me/OrangeFoxRecovery",
                "https://github.com/OrangeFoxRecovery"
        ));

        romsList.add(new RomModel(
                "rec_twrp_cheetah",
                "TWRP Recovery 3.7.1",
                "cheetah",
                "14",
                "Official",
                "TeamWin",
                "2024-01-20",
                "58 MB",
                "• Fastboot boot support for Tensor G2\n• Decryption working for Android 14\n• MTP and ADB Sideload support",
                "https://dl.twrp.me/cheetah/",
                "1f2e3d4c5b6a708192a3b4c5d6e7f809",
                "RECOVERY",
                false,
                "https://twrp.me",
                "https://t.me/twrp_official",
                "https://github.com/TeamWin"
        ));

        // --- ROOT MODULES ---
        modulesList.add(new ModuleModel(
                "mod_pif",
                "Play Integrity Fix (NEXT)",
                "SafetyNet",
                "chiteroman & osm0sis",
                "v16.0",
                "Magisk / KernelSU / APatch",
                "Bypasses Play Integrity hardware attestation and passes MEETS_DEVICE_INTEGRITY so Google Wallet, Banking Apps, and Netflix work on rooted ROMs.",
                "https://github.com/chiteroman/PlayIntegrityFix/releases",
                "https://github.com/chiteroman/PlayIntegrityFix",
                "https://t.me/playintegrityfix"
        ));

        modulesList.add(new ModuleModel(
                "mod_v4a",
                "ViPER4Android RE",
                "Audio",
                "WSTxda",
                "v0.4.0",
                "Magisk / KernelSU",
                "The legendary Android audio processing engine. Dynamic system bass, convolver, clarity enhancer, tube simulator, and headphone surround sound.",
                "https://github.com/AndroidAudioHQ/ViPER4AndroidRE/releases",
                "https://github.com/AndroidAudioHQ/ViPER4AndroidRE",
                "https://t.me/ViPER4AndroidFX"
        ));

        modulesList.add(new ModuleModel(
                "mod_iconify",
                "Iconify Customizer",
                "Customization",
                "Mahmud0808",
                "v6.8.0",
                "Magisk / KernelSU",
                "Ultimate UI engine for AOSP ROMs: custom QS panel layouts, status bar icons, battery styles, notification shapes, Monet color engines, and lockscreen clocks.",
                "https://github.com/Mahmud0808/Iconify/releases",
                "https://github.com/Mahmud0808/Iconify",
                "https://t.me/IconifyOfficial"
        ));

        modulesList.add(new ModuleModel(
                "mod_lsposed",
                "Zygisk - LSPosed",
                "Tools",
                "LSPosed Devs",
                "v1.9.2",
                "Magisk / KernelSU / APatch",
                "Modern ART hooking framework using Zygisk. Allows running powerful Xposed mods without modifying system APKs.",
                "https://github.com/LSPosed/LSPosed/releases",
                "https://github.com/LSPosed/LSPosed",
                "https://t.me/LSPosed"
        ));

        modulesList.add(new ModuleModel(
                "mod_shamiko",
                "Shamiko Zygisk Hider",
                "SafetyNet",
                "LSPosed Team",
                "v1.0.1",
                "Magisk / KernelSU",
                "Advanced root and module hiding engine based on Zygisk. Hides modified system environment from strict enterprise and banking apps.",
                "https://github.com/LSPosed/Shamiko/releases",
                "https://github.com/LSPosed/Shamiko",
                "https://t.me/LSPosed"
        ));

        modulesList.add(new ModuleModel(
                "mod_thermal",
                "Extreme Thermal Disabler",
                "Performance",
                "CptKernel",
                "v2.5",
                "Magisk / KernelSU",
                "Removes aggressive CPU and GPU throttling in games (BGMI, COD, Genshin Impact) while maintaining safe battery charging temperatures.",
                "https://github.com/CptKernel/ThermalMod/releases",
                "https://github.com/CptKernel/ThermalMod",
                "https://t.me/thermalmods"
        ));

        modulesList.add(new ModuleModel(
                "mod_jamesdsp",
                "JamesDSP Audio Engine",
                "Audio",
                "james34602",
                "v6.2",
                "Magisk / KernelSU",
                "Audiophile-grade 32-bit floating point digital signal processor for Android with graphic and parametric EQ and binaural spatializer.",
                "https://github.com/james34602/JamesDSPManager/releases",
                "https://github.com/james34602/JamesDSPManager",
                "https://t.me/jamesdsp"
        ));

        // --- FLASHING GUIDES ---
        guidesList.add(new GuideModel(
                "guide_bootloader",
                "1. How to Unlock Bootloader",
                "The essential first step before flashing any custom recovery or ROM.",
                "Beginner",
                "10 - 15 Mins",
                "Unlocking the bootloader will COMPLETELY ERASE all internal storage. Make a full backup before proceeding!",
                Arrays.asList(
                        "Go to Settings > About Phone and tap 'Build Number' 7 times to enable Developer Options.",
                        "Navigate to Settings > System > Developer Options and enable 'OEM Unlocking' and 'USB Debugging'.",
                        "Connect your phone to PC via a high quality USB cable.",
                        "Open Terminal / Command Prompt on PC and run: 'adb reboot bootloader'.",
                        "Once in Fastboot mode, run: 'fastboot flashing unlock' (or 'fastboot oem unlock' on older phones).",
                        "Use the Volume buttons on your phone to select 'Unlock the Bootloader' and press the Power key to confirm."
                )
        ));

        guidesList.add(new GuideModel(
                "guide_recovery",
                "2. Flashing Custom Recovery (TWRP / OrangeFox)",
                "Install a custom recovery environment to flash zips and make Nandroid backups.",
                "Intermediate",
                "5 - 10 Mins",
                "Make sure you download the exact recovery img matching your device codename.",
                Arrays.asList(
                        "Download the TWRP or OrangeFox '.img' file for your device codename.",
                        "Reboot phone into Fastboot mode: 'adb reboot bootloader'.",
                        "For A/B devices with vendor_boot: 'fastboot flash vendor_boot recovery.img'.",
                        "For traditional devices with recovery partition: 'fastboot flash recovery recovery.img'.",
                        "Boot directly into recovery using: 'fastboot reboot recovery'.",
                        "Swipe to allow modifications when prompted on first boot."
                )
        ));

        guidesList.add(new GuideModel(
                "guide_clean_flash",
                "3. Clean Flash a Custom ROM",
                "The safest way to switch between different Custom ROMs without bugs or bootloops.",
                "Intermediate",
                "15 - 20 Mins",
                "Format Data is required when switching between different ROMs (e.g. MIUI to PixelOS). Back up your files!",
                Arrays.asList(
                        "Transfer the ROM zip file and GApps (if vanilla build) to an external USB OTG or PC.",
                        "Reboot into TWRP or OrangeFox recovery.",
                        "Go to Wipe > Format Data and type 'yes' to remove encryption.",
                        "Go to Advanced Wipe and wipe: Dalvik / ART Cache, Metadata, and System.",
                        "Go to Install > select the ROM .zip and swipe to flash.",
                        "If the ROM does not include Google Apps, flash your GApps zip immediately after.",
                        "Reboot System and enjoy your new Custom ROM!"
                )
        ));

        guidesList.add(new GuideModel(
                "guide_play_integrity",
                "4. Fix Banking Apps & Play Integrity",
                "Pass MEETS_DEVICE_INTEGRITY and use Google Wallet and banking apps on rooted phones.",
                "Beginner",
                "5 Mins",
                "Do NOT use older MagiskHide modules as they conflict with modern Zygisk setups.",
                Arrays.asList(
                        "Ensure you are rooted with Magisk v26+ or KernelSU with Zygisk enabled in settings.",
                        "Install the latest 'Play Integrity Fix (NEXT)' module from the Modules tab.",
                        "Install 'Shamiko' module to hide root traces from banking apps.",
                        "In Magisk settings, enable Zygisk and configure DenyList (add Google Play Services, Google Play Store, and Banking apps).",
                        "Go to Android Settings > Apps > Google Play Store > Storage and 'Clear Storage'.",
                        "Reboot phone and test with a Play Integrity Checker app."
                )
        ));
    }

    public List<RomModel> getRomsForDevice(String codename, String category, String androidVersion, String query) {
        List<RomModel> result = new ArrayList<>();
        String lowerQuery = query == null ? "" : query.trim().toLowerCase(Locale.US);
        boolean filterCategory = category != null && !category.equalsIgnoreCase("ALL");
        boolean filterAndroid = androidVersion != null && !androidVersion.equalsIgnoreCase("ALL");

        for (RomModel rom : romsList) {
            boolean matchesDevice = rom.getDeviceCodename().equalsIgnoreCase(codename)
                    || rom.getDeviceCodename().equalsIgnoreCase("all");

            boolean matchesCategory = !filterCategory || rom.getCategory().equalsIgnoreCase(category);
            boolean matchesAndroid = !filterAndroid || rom.getAndroidVersion().equalsIgnoreCase(androidVersion);
            boolean matchesQuery = lowerQuery.isEmpty()
                    || rom.getTitle().toLowerCase(Locale.US).contains(lowerQuery)
                    || rom.getMaintainer().toLowerCase(Locale.US).contains(lowerQuery);

            if (matchesDevice && matchesCategory && matchesAndroid && matchesQuery) {
                result.add(rom);
            }
        }
        return result;
    }

    public List<ModuleModel> getModules(String category, String query) {
        List<ModuleModel> result = new ArrayList<>();
        String lowerQuery = query == null ? "" : query.trim().toLowerCase(Locale.US);
        boolean filterCategory = category != null && !category.equalsIgnoreCase("ALL");

        for (ModuleModel module : modulesList) {
            boolean matchesCategory = !filterCategory || module.getCategory().equalsIgnoreCase(category);
            boolean matchesQuery = lowerQuery.isEmpty()
                    || module.getName().toLowerCase(Locale.US).contains(lowerQuery)
                    || module.getDescription().toLowerCase(Locale.US).contains(lowerQuery)
                    || module.getAuthor().toLowerCase(Locale.US).contains(lowerQuery);

            if (matchesCategory && matchesQuery) {
                result.add(module);
            }
        }
        return result;
    }

    public List<GuideModel> getGuides() {
        return new ArrayList<>(guidesList);
    }

    public boolean isBookmarked(String id) {
        Set<String> set = bookmarkPrefs.getStringSet(KEY_SAVED_IDS, new HashSet<>());
        return set != null && set.contains(id);
    }

    public void toggleBookmark(String id) {
        Set<String> set = new HashSet<>(bookmarkPrefs.getStringSet(KEY_SAVED_IDS, new HashSet<>()));
        if (set.contains(id)) {
            set.remove(id);
        } else {
            set.add(id);
        }
        bookmarkPrefs.edit().putStringSet(KEY_SAVED_IDS, set).apply();
    }
}
