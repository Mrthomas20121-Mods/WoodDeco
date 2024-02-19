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

import java.util.List;

public class WoodDecoBlockStateProvider extends BlockStateProvider {

    private final List<IWoodType> types;

    public WoodDecoBlockStateProvider(PackOutput gen, ExistingFileHelper existingFileHelper) {
        super(gen, WoodDeco.MOD_ID, existingFileHelper);
        this.types = WoodTypeRegistry.getWoodTypes();
    }

    public WoodDecoBlockStateProvider(PackOutput gen, ExistingFileHelper existingFileHelper, List<IWoodType> types) {
        super(gen, WoodDeco.MOD_ID, existingFileHelper);
        this.types = types;
    }

    @Override
    protected void registerStatesAndModels() {
        for(IWoodType woodType : this.types) {
            for(BlockType blockType: BlockType.values()) {
                this.simpleBlock(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get());
                BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                this.stairsBlock(decoration.stair().get(), this.blockTexture(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()));
                this.slabBlock(decoration.slab().get(), this.blockTexture(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()), this.blockTexture(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()));
            }
        }
    }
}
