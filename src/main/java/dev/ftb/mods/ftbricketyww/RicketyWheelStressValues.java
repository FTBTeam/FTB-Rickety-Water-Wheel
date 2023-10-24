package dev.ftb.mods.ftbricketyww;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.utility.Couple;
import dev.ftb.mods.ftbricketyww.config.ConfigHolder;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public enum RicketyWheelStressValues implements BlockStressValues.IStressValueProvider {
    INSTANCE;

    @Override
    public double getImpact(Block block) {
        return 0;
    }

    @Override
    public double getCapacity(Block block) {
        return ConfigHolder.stressCapacity();
    }

    @Override
    public boolean hasImpact(Block block) {
        return false;
    }

    @Override
    public boolean hasCapacity(Block block) {
        return true;
    }

    @Nullable
    @Override
    public Couple<Integer> getGeneratedRPM(Block block) {
        return Couple.create(8, 8);
    }
}
