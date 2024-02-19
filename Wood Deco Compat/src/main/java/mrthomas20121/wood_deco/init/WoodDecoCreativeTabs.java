package mrthomas20121.wood_deco.init;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class WoodDecoCreativeTabs {

    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WoodDeco.MOD_ID);

    public static RegistryObject<CreativeModeTab> woodBlockTab = CREATIVE_TABS.register("wood_blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(WoodDecoBlocks.WOOD_BLOCKS.get(MinecraftWoodType.ACACIA).get(BlockType.BRICKS).get()))
            .title(Component.translatable("creative_tab.wood_deco.wood_blocks"))
            .displayItems((feature, output) -> {
                for(IWoodType woodType: WoodTypeRegistry.getWoodTypes()) {
                    for(BlockType blockType: BlockType.values()) {
                        output.accept((WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType).get()));
                    }
                    for(BlockType blockType: BlockType.values()) {
                        BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                        output.accept(decoration.slab().get());
                        output.accept(decoration.stair().get());
                    }
                }
            }).build());
}
