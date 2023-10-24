package dev.ftb.mods.ftbricketyww.block;

import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlockEntity;
import com.simibubi.create.foundation.utility.Lang;
import dev.ftb.mods.ftbricketyww.config.ConfigHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class RicketyWaterWheelBlockEntity extends WaterWheelBlockEntity {
    private int durability;

    public RicketyWaterWheelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        durability = ConfigHolder.maxDurability();
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide() && hasNetwork()) {
            if (stress > 0f) {
                durability--;
                if (durability > ConfigHolder.maxDurability()) {
                    // in case config was changed on the fly
                    durability = ConfigHolder.maxDurability();
                    sendData();
                } else if (durability <= 0) {
                    level.playSound(null, worldPosition, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1f, 1f);
                    level.destroyBlock(worldPosition, false);
                } else if (durability % (ConfigHolder.maxDurability() / 100) == 0) {
                    // sync to client (also does a setChanged())
                    notifyUpdate();
                } else {
                    setChanged();
                }
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        boolean added = super.addToGoggleTooltip(tooltip, isPlayerSneaking);

        int durabilityPct = (int) ((durability / (float) ConfigHolder.maxDurability()) * 100);
        Lang.translate("ftbricketyww.goggles.durability")
                .style(ChatFormatting.GRAY)
                .text(ChatFormatting.AQUA, durabilityPct + "%")
                .forGoggles(tooltip);

        return added;
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);

        durability = compound.getInt("Durability");
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);

        compound.putInt("Durability", durability);
    }
}
