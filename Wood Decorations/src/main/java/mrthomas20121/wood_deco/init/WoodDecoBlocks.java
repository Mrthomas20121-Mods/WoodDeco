package mrthomas20121.wood_deco.init;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class WoodDecoBlocks {

    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WoodDeco.MOD_ID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WoodDeco.MOD_ID);

    public static Map<IWoodType, Map<BlockType, RegistryObject<Block>>> WOOD_BLOCKS = registerWood();
    public static Map<IWoodType, Map<BlockType, BlockDecoration>> WOOD_PART_BLOCKS = registerWoodDecoration();
    private static RegistryObject<Block> register(String name) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<SlabBlock> registerSlab(String name) {
        RegistryObject<SlabBlock> block = BLOCKS.register(name, () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static RegistryObject<StairBlock> registerStairs(String name, Supplier<Block> b) {
        RegistryObject<StairBlock> block = BLOCKS.register(name, () -> new StairBlock(() -> b.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static Map<IWoodType, Map<BlockType, RegistryObject<Block>>> registerWood() {
        Map<IWoodType, Map<BlockType, RegistryObject<Block>>> map = new HashMap<>();
        for(IWoodType woodType: WoodTypeRegistry.getWoodTypes()) {
            Map<BlockType, RegistryObject<Block>> m = new HashMap<>();

            for(BlockType blockType: BlockType.values()) {
                m.put(blockType, register(blockType.getBlockName(woodType.getName())));
            }

            map.put(woodType, m);
        }
        return map;
    }

    private static Map<IWoodType, Map<BlockType, BlockDecoration>> registerWoodDecoration() {
        Map<IWoodType, Map<BlockType, BlockDecoration>> map = new HashMap<>();
        for(IWoodType woodType: WoodTypeRegistry.getWoodTypes()) {
            Map<BlockType, BlockDecoration> m = new HashMap<>();

            for(BlockType blockType: BlockType.values()) {
                String name = blockType.getBlockName(woodType.getName());
                m.put(blockType, new BlockDecoration(registerSlab(name+"_slab"), registerStairs(name+"_stairs", woodType.planks())));
            }

            map.put(woodType, m);
        }
        return map;
    }
}
