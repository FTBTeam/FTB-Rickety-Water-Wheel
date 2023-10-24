package dev.ftb.mods.ftbricketyww.block;

import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlock;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlockEntity;
import dev.ftb.mods.ftbricketyww.RegistryObjects;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RicketyWaterWheelBlock extends WaterWheelBlock {
    public RicketyWaterWheelBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends WaterWheelBlockEntity> getBlockEntityType() {
        return RegistryObjects.RICKETY_WW_BLOCK_ENTITY.get();
    }
}
