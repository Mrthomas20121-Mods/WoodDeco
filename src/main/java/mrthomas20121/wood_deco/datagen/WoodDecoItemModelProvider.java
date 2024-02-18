package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WoodDecoItemModelProvider extends ItemModelProvider {

    public WoodDecoItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, WoodDeco.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for(IWoodType woodType : WoodTypeRegistry.getWoodTypes()) {

            for(BlockType blockType: BlockType.values()) {
                this.itemBlock(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get());
                BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                this.itemBlock(decoration.stair().get());
                this.itemBlock(decoration.slab().get());
            }
        }
    }

    public String blockName(Block block) {
        ResourceLocation resource = ForgeRegistries.BLOCKS.getKey(block);
        // make sure the block is not null
        if (resource != null) {
            return resource.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public String itemName(Item item) {
        ResourceLocation resource = ForgeRegistries.ITEMS.getKey(item);
        // make sure the item is not null
        if (resource != null) {
            return resource.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation texture(String name, String location) {
        return this.modLoc("block/" + location + name);
    }

    public void itemBlock(Block block) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
    }
}
