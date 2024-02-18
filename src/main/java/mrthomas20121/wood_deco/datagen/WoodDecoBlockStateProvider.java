package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodDecoBlockStateProvider extends BlockStateProvider {

    public WoodDecoBlockStateProvider(PackOutput gen, ExistingFileHelper existingFileHelper) {
        super(gen, WoodDeco.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for(IWoodType woodType : WoodTypeRegistry.getWoodTypes()) {
            for(BlockType blockType: BlockType.values()) {
                this.simpleBlock(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get());
                BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                ResourceLocation key = this.blockTexture(woodType.planks().get());
                this.stairsBlock(decoration.stair().get(), key);
                this.slabBlock(decoration.slab().get(), ForgeRegistries.BLOCKS.getKey(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()), key);
            }
        }
    }
}
