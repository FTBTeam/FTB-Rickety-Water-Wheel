package dev.ftb.mods.ftbricketyww.block;

import dev.ftb.mods.ftbricketyww.config.ConfigHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RicketyWaterWheelBlockItem extends BlockItem {
    public RicketyWaterWheelBlockItem(RicketyWaterWheelBlock block, Properties properties) {
        super(block, properties);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getTagElement("BlockEntityTag") != null;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(getDurability(stack) * 13f);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return Mth.hsvToRgb(getDurability(stack) / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(stack, level, list, flag);

        int durabilityPct = (int)(getDurability(stack) * 100);
        list.add(Component.translatable("create.ftbricketyww.goggles.durability").withStyle(ChatFormatting.GRAY)
                .append(Component.literal(durabilityPct + "%").withStyle(ChatFormatting.AQUA)));
    }

    public static float getDurability(ItemStack stack) {
        CompoundTag tag = stack.getTagElement("BlockEntityTag");
        if (tag != null) {
            float durability = tag.getInt("Durability");
            return Mth.clamp(durability / ConfigHolder.maxDurability(), 0f, 1f);
        } else {
            return 1f;
        }
    }
}
