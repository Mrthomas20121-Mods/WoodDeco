package mrthomas20121.wood_deco.api;

public enum BlockType {
    BRICKS("%s_bricks"),
    POLISHED_PLANKS("polished_%s_planks"),
    TILE("%s_tile");

    private final String blockName;

    BlockType(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockName(String woodType) {
        return blockName.formatted(woodType);
    }
}
