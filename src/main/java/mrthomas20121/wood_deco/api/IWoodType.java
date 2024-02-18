package mrthomas20121.wood_deco.api;

import net.minecraft.world.level.block.Block;

import java.util.Locale;
import java.util.function.Supplier;

public interface IWoodType {

    Supplier<Block> planks();

    default String getName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    String name();
}
