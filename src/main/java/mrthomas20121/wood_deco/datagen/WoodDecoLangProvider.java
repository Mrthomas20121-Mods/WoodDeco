package mrthomas20121.wood_deco.datagen;

import mrthomas20121.wood_deco.WoodDeco;
import mrthomas20121.wood_deco.api.BlockDecoration;
import mrthomas20121.wood_deco.api.BlockType;
import mrthomas20121.wood_deco.api.IWoodType;
import mrthomas20121.wood_deco.api.WoodTypeRegistry;
import mrthomas20121.wood_deco.init.WoodDecoBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class WoodDecoLangProvider extends LanguageProvider {

    private final List<IWoodType> types;

    public WoodDecoLangProvider(PackOutput gen, String mod_id, List<IWoodType> types) {
        super(gen, mod_id, "en_us");
        this.types = types;
    }

    public WoodDecoLangProvider(PackOutput gen, List<IWoodType> types) {
        super(gen, WoodDeco.MOD_ID, "en_us");
        this.types = types;
    }

    public WoodDecoLangProvider(PackOutput gen) {
        super(gen, WoodDeco.MOD_ID, "en_us");
        this.types = WoodTypeRegistry.getWoodTypes();
    }

    @Override
    protected void addTranslations() {

        this.add("creative_tab.wood_deco.wood_blocks", "Wood Deco: Blocks");

        for(IWoodType woodType : this.types) {
            for(BlockType blockType: BlockType.values()) {
                String blockName = blockType.getBlockName(woodType.getName());
                String cap = this.capitalize(blockName);
                this.addBlock(WoodDecoBlocks.WOOD_BLOCKS.get(woodType).get(blockType), cap);
                BlockDecoration decoration = WoodDecoBlocks.WOOD_PART_BLOCKS.get(woodType).get(blockType);
                this.addBlock(decoration.slab(), cap+" Slab");
                this.addBlock(decoration.stair(), cap+" Stairs");
            }
        }
    }

    private String capitalize(String str) {
        if(str.contains("_")) {
            String[] split = str.split("_");
            StringBuilder builder = new StringBuilder();
            for(String s: split) {
                builder.append(StringUtils.capitalize(s)).append(" ");
            }

            return builder.toString();

        }

        return StringUtils.capitalize(str);
    }
}
