package dev.ftb.mods.ftbricketyww.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class CommonConfig {
    public static class General {
        public IntValue maxDurability;
        public ForgeConfigSpec.DoubleValue waterWheelCapacity;
    }

    public final General general = new General();

    CommonConfig(ForgeConfigSpec.Builder builder) {
        builder.push("General");

        general.maxDurability = builder.comment("Max durability for the (rickety) Water Wheel; the number of ticks before it will break while the network it's connected to has any stress on it. Default of 36000 ticks = 30 minutes.")
                .translation("ftbricketyww.config.max_durability")
                .defineInRange("max_durability", 36000, 1, Integer.MAX_VALUE);

        general.waterWheelCapacity = builder.comment("Rickety Water Wheel stress capacity.")
                .translation("ftbricketyww.config.water_wheel_stress_capacity")
                .defineInRange("water_wheel_stress_capacity", 32.0, 1.0, Double.MAX_VALUE);

        builder.pop();
    }
}
