package dev.ftb.mods.ftbricketyww;

import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.ftb.mods.ftbricketyww.config.ConfigHolder;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FTBRicketyWaterWheel.MOD_ID)
public class FTBRicketyWaterWheel {
    public static final String MOD_ID = "ftbricketyww";
    public static final NonNullSupplier<CreateRegistrate> REGISTRATE
            = NonNullSupplier.lazy(() -> CreateRegistrate.create(MOD_ID));
    public static final Logger LOGGER = LogManager.getLogger();

    public FTBRicketyWaterWheel() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        MOD_BUS.addListener(this::commonSetup);
        MOD_BUS.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);

        REGISTRATE.get().registerEventListeners(MOD_BUS);

        ConfigHolder.init();

        RegistryObjects.init();

        BlockStressValues.registerProvider(MOD_ID, RicketyWheelStressValues.INSTANCE);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientInit.setup();
    }
}
