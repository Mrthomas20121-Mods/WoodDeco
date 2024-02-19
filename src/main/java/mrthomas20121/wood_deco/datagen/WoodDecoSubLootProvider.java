package mrthomas20121.wood_deco.datagen;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;

public class WoodDecoSubLootProvider extends BlockLootSubProvider {

    private final List<IWoodType> types;

    protected WoodDecoSubLootProvider(List<IWoodType> types) {
        super(new HashSet<>(), FeatureFlags.REGISTRY.allFlags());
        this.types = types;
    }

    @Override
    protected void generate() {
        for(IWoodType woodType: this.types) {
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
    public @NotNull Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ObjectArrayList<>();
        this.types.forEach(type -> {
            for(BlockType blockType: BlockType.values()) {
                blocks.add(WoodDecoBlocks.WOOD_BLOCKS.get(type).get(blockType).get());
                blocks.add(WoodDecoBlocks.WOOD_PART_BLOCKS.get(type).get(blockType).slab().get());
                blocks.add(WoodDecoBlocks.WOOD_PART_BLOCKS.get(type).get(blockType).stair().get());
            }
        });
        return blocks;
    }
}
