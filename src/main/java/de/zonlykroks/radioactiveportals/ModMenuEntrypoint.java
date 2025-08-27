package de.zonlykroks.radioactiveportals;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.text.Text;

public class ModMenuEntrypoint implements ModMenuApi {

    public static int timeTillDeathInSeconds = 120;

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .category(ConfigCategory.createBuilder().option(Option.<Integer>createBuilder()
                        .name(Text.literal("Time till death"))
                        .description(OptionDescription.of(Text.literal("Time in seconds till death when standing in radioactive portal")))
                        .binding(
                                120,
                                () -> timeTillDeathInSeconds,
                                value -> timeTillDeathInSeconds = value
                        ).build()).build())
                .build()
                .generateScreen(parentScreen);
    }
}
