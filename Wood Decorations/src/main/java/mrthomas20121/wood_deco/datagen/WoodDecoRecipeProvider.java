package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Consumer;

public class WoodDecoRecipeProvider extends RecipeProvider {

    private final List<IWoodType> types;
    private final String modid;

    public WoodDecoRecipeProvider(PackOutput pOutput, List<IWoodType> types, String modid) {
        super(pOutput);
        this.types = types;
        this.modid = modid;
    }

    public WoodDecoRecipeProvider(PackOutput pOutput) {
        super(pOutput);
        this.types = WoodTypeRegistry.getWoodTypes();
        this.modid = WoodDeco.MOD_ID;
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        for(IWoodType type: this.types) {
            ShapedRecipeBuilder
                    .shaped(RecipeCategory.BUILDING_BLOCKS, WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.POLISHED_PLANKS).get(), 4)
                    .define('Y', type.planks().get())
                    .define('X', Items.STICK)
                    .pattern("YXY")
                    .pattern("X X")
                    .pattern("YXY")
                    .unlockedBy(getHasName(type.planks().get()), has(type.planks().get()))
                    .save(consumer, new ResourceLocation(modid, "crafting/"+BlockType.POLISHED_PLANKS.getBlockName(type.getName())));

            ShapedRecipeBuilder
                    .shaped(RecipeCategory.BUILDING_BLOCKS, WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.BRICKS).get(), 4)
                    .define('X', type.planks().get())
                    .pattern("XX")
                    .pattern("XX")
                    .unlockedBy(getHasName(WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.TILE).get()), has(WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.TILE).get()))
                    .save(consumer, new ResourceLocation(modid, "crafting/"+BlockType.BRICKS.getBlockName(type.getName())));

            ShapedRecipeBuilder
                    .shaped(RecipeCategory.BUILDING_BLOCKS, WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.TILE).get(), 4)
                    .define('X', WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.BRICKS).get())
                    .define('Y', Items.STICK)
                    .pattern(" X ")
                    .pattern("XYX")
                    .pattern(" X ")
                    .unlockedBy(getHasName(WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.TILE).get()), has(WoodDecoBlocks.WOOD_BLOCKS.get(type).get(BlockType.TILE).get()))
                    .save(consumer, new ResourceLocation(modid, "crafting/"+BlockType.TILE.getBlockName(type.getName())));

            slabStairRecipes(consumer, type, BlockType.POLISHED_PLANKS);
            slabStairRecipes(consumer, type, BlockType.TILE);
            slabStairRecipes(consumer, type, BlockType.BRICKS);
        }
    }

    protected void slabStairRecipes(Consumer<FinishedRecipe> consumer, IWoodType woodType, BlockType blockType) {
        BlockDecoration deco = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, deco.slab().get(), Ingredient.of(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()))
                .unlockedBy(getHasName(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()), has(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()))
                .save(consumer, "crafting/"+blockType.getBlockName(woodType.getName())+"_slab");

        stairBuilder(deco.stair().get(), Ingredient.of(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()))
                .unlockedBy(getHasName(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()), has(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()))
                .save(consumer, "crafting/"+blockType.getBlockName(woodType.getName())+"_stairs");
    }
}
