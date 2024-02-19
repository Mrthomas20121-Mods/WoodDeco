package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.HashSet;
import java.util.List;

public class WoodDecoLootGen extends LootTableProvider {

    public WoodDecoLootGen(PackOutput output, List<IWoodType> types) {

        super(output, new HashSet<>(), List.of(
                new LootTableProvider.SubProviderEntry(() -> new WoodDecoSubLootProvider(types), LootContextParamSets.BLOCK)
        ));
    }

    public WoodDecoLootGen(PackOutput output) {

        super(output, new HashSet<>(), List.of(
                new LootTableProvider.SubProviderEntry(() -> new WoodDecoSubLootProvider(WoodTypeRegistry.getWoodTypes()), LootContextParamSets.BLOCK)
        ));
    }
}

