package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WoodDecoTagsProvider extends BlockTagsProvider {

    private final List<IWoodType> types;

    public WoodDecoTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper, List<IWoodType> types) {
        super(output, lookupProvider, WoodDeco.MOD_ID, existingFileHelper);
        this.types = types;
    }

    public WoodDecoTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WoodDeco.MOD_ID, existingFileHelper);
        this.types = WoodTypeRegistry.getWoodTypes();
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for(IWoodType woodType: this.types) {
            for(BlockType blockType: BlockType.values()) {
                BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                tag(BlockTags.MINEABLE_WITH_AXE).add(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get(), decoration.slab().get(), decoration.stair().get());
            }
        }
    }
}