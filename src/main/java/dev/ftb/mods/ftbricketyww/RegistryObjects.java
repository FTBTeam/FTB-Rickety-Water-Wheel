package dev.ftb.mods.ftbricketyww;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlock;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelInstance;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelRenderer;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.ftb.mods.ftbricketyww.block.RicketyWaterWheelBlock;
import dev.ftb.mods.ftbricketyww.block.RicketyWaterWheelBlockEntity;
import dev.ftb.mods.ftbricketyww.block.RicketyWaterWheelBlockItem;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class RegistryObjects {
    static {
        FTBRicketyWaterWheel.REGISTRATE.get().creativeModeTab(() -> AllCreativeModeTabs.BASE_CREATIVE_TAB);
        FTBRicketyWaterWheel.REGISTRATE.get().setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                        .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }

    public static final BlockEntry<RicketyWaterWheelBlock> RICKETY_WW_BLOCK =
            FTBRicketyWaterWheel.REGISTRATE.get().block("rickety_water_wheel", RicketyWaterWheelBlock::new)
                    .initialProperties(SharedProperties::wooden)
                    .properties(p -> p.color(MaterialColor.DIRT))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate(
                            (c, p) -> BlockStateGen.directionalBlockIgnoresWaterlogged(c, p, s -> AssetLookup.partialBaseModel(c, p)))
                    .transform(BlockStressDefaults.setCapacity(32.0))
                    .transform(BlockStressDefaults.setGeneratorSpeed(WaterWheelBlock::getSpeedRange))
                    .item(RicketyWaterWheelBlockItem::new)
                    .transform(customItemModel())
                    .register();

    public static final BlockEntityEntry<RicketyWaterWheelBlockEntity> RICKETY_WW_BLOCK_ENTITY = FTBRicketyWaterWheel.REGISTRATE.get()
            .blockEntity("rickety_water_wheel", RicketyWaterWheelBlockEntity::new)
            .instance(() -> WaterWheelInstance::standard, false)
            .validBlocks(RICKETY_WW_BLOCK)
            .renderer(() -> WaterWheelRenderer::standard)
            .register();

    public static void init() {
    }
}
