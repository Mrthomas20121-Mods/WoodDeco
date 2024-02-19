package mrthomas20121.wood_deco.api;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.registries.RegistryObject;

public record BlockDecoration(RegistryObject<SlabBlock> slab, RegistryObject<StairBlock> stair) {
}
