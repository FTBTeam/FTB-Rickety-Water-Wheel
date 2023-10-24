package dev.ftb.mods.ftbricketyww;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;

public class ClientInit {
    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Create.ID);

    public static void setup() {
        HELPER.addStoryBoard(RegistryObjects.RICKETY_WW_BLOCK, "water_wheel", KineticsScenes::waterWheel,
                AllPonderTags.KINETIC_SOURCES);
    }
}
