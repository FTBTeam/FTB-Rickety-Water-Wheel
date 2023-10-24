package dev.ftb.mods.ftbricketyww.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder {
    //    public static ClientConfig client;
//    private static ForgeConfigSpec configClientSpec;
    private static ForgeConfigSpec configCommonSpec;
    public static CommonConfig common;

    public static void init() {
//        final Pair<ClientConfig, ForgeConfigSpec> spec1 = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
//        client = spec1.getLeft();
//        configClientSpec = spec1.getRight();

        final Pair<CommonConfig, ForgeConfigSpec> spec2 = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        common = spec2.getLeft();
        configCommonSpec = spec2.getRight();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.configCommonSpec);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHolder.configClientSpec);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ConfigHolder::onConfigChanged);
    }

    private static void onConfigChanged(final ModConfigEvent event) {
        ModConfig config = event.getConfig();
        /*if (config.getSpec() == ConfigHolder.configClientSpec) {
            refreshClient();
        } else */if (config.getSpec() == ConfigHolder.configCommonSpec) {
            refreshCommon();
        }
    }

//    static void refreshClient() {
//    }

    static void refreshCommon() {
    }

    public static int maxDurability() {
        return common.general.maxDurability.get();
    }

    public static double stressCapacity() {
        return common.general.waterWheelCapacity.get();
    }
}
