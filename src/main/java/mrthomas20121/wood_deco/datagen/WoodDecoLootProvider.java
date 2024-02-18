package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WoodDecoLootProvider extends BlockLootSubProvider {

    protected WoodDecoLootProvider() {
        super(new HashSet<>(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for(IWoodType woodType: WoodTypeRegistry.getWoodTypes()) {
            for(BlockType blockType: BlockType.values()) {
                RegistryObject<Block> block = WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType);
                this.dropSelf(block.get());
                BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                this.add(decoration.slab().get(), this::createSlabItemTable);
                this.dropSelf(decoration.stair().get());
            }
        }
    }

    @Override
    public Iterable<Block> getKnownBlocks() {
        return WoodDecoBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
    }
}
