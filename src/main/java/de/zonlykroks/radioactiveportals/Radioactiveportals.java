package de.zonlykroks.radioactiveportals;

import de.zonlykroks.radioactiveportals.init.ModDamageTypes;
import net.fabricmc.api.ModInitializer;

public class Radioactiveportals implements ModInitializer {

    @Override
    public void onInitialize() {
        ModDamageTypes.init();
    }
}
