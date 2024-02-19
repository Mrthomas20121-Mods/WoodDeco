package mrthomas20121.wood_deco.api;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Locale;
import java.util.function.Supplier;

public enum MinecraftWoodType implements IWoodType {
    ACACIA(() -> Blocks.ACACIA_PLANKS),
    BAMBOO(() -> Blocks.BAMBOO_PLANKS),
    BIRCH(() -> Blocks.BIRCH_PLANKS),
    CHERRY(() -> Blocks.CHERRY_PLANKS),
    CRIMSON(() -> Blocks.CRIMSON_PLANKS),
    DARK_OAK(() -> Blocks.DARK_OAK_PLANKS),
    JUNGLE(() -> Blocks.JUNGLE_PLANKS),
    MANGROVE(() -> Blocks.MANGROVE_PLANKS),
    OAK(() -> Blocks.OAK_PLANKS),
    SPRUCE(() -> Blocks.SPRUCE_PLANKS),
    WARPED(() -> Blocks.WARPED_PLANKS);

    private final Supplier<Block> planksBlock;

    MinecraftWoodType(Supplier<Block> planksBlock) {
        this.planksBlock = planksBlock;
    }

    @Override
    public Supplier<Block> planks() {
        return this.planksBlock;
    }
}
