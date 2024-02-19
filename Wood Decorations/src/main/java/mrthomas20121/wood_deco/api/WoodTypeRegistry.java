package mrthomas20121.wood_deco.api;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.util.Arrays;
import java.util.List;

public class WoodTypeRegistry {

    private static final List<IWoodType> WOOD_TYPES = new ObjectArrayList<>();

    static {
        WOOD_TYPES.addAll(Arrays.stream(MinecraftWoodType.values()).toList());
    }

    public static List<IWoodType> getWoodTypes() {
        return WOOD_TYPES;
    }

    public static void addWoodType(IWoodType woodType) {
        WOOD_TYPES.add(woodType);
    }
}
