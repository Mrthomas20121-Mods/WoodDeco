package mrthomas20121.wood_deco.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.HashSet;
import java.util.List;

public class WoodDecoLootGen extends LootTableProvider {

    public WoodDecoLootGen(PackOutput output) {

        super(output, new HashSet<>(), List.of(
                new LootTableProvider.SubProviderEntry(WoodDecoLootProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}

