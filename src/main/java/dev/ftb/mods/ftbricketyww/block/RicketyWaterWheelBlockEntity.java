package dev.ftb.mods.ftbricketyww.block;

import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlockEntity;
import com.simibubi.create.foundation.utility.Lang;
import dev.ftb.mods.ftbricketyww.config.ConfigHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

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
                int prevDurability = durability;
                durability = Math.max(0, durability - 1);
                if (durability > ConfigHolder.maxDurability()) {
                    // in case config was changed on the fly
                    durability = ConfigHolder.maxDurability();
                    sendData();
                } else if (durability == 0) {
                    if (prevDurability > 0) {
                        level.playSound(null, worldPosition, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1f, 1f);
                        getOrCreateNetwork().updateCapacityFor(this, 0f);
                    }
                    if (level.random.nextBoolean()) {
                        Vec3 vec = Vec3.atBottomCenterOf(getBlockPos().above());
                        ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE, vec.x(), vec.y(), vec.z(), 10, 0, 0, 0, 0);
                    }
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

    @Override
    public float getGeneratedSpeed() {
        return durability > 0 ? super.getGeneratedSpeed() : 0f;
    }
}
