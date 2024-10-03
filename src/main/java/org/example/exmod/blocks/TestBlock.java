package org.example.exmod.blocks;

import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.generators.BlockEventGenerator;
import com.github.puzzle.game.generators.BlockGenerator;
import com.github.puzzle.game.generators.BlockModelGenerator;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;
import org.example.exmod.block_entities.TestBlockEntity;

import java.util.List;
import java.util.Map;

public class TestBlock implements IModBlock {
    public static final Identifier BLOCK_ID = Identifier.of(Constants.MOD_ID, "test_block");
    public static final Identifier ALL_TEXTURE = Identifier.of("base", "textures/blocks/coal.png");

    @Override
    public Identifier getIdentifier() {
        return BLOCK_ID;
    }

    @Override
    public String getName() {
        return "Test Block Entity";
    }

    @Override
    public BlockGenerator getBlockGenerator() {
        BlockGenerator generator = new BlockGenerator(BLOCK_ID);
        generator.createBlockState("default", "model", true, "events", true);
        generator.addBlockEntity(TestBlockEntity.id.toString(), Map.of());
        return generator;
    }

    @Override
    public List<BlockModelGenerator> getBlockModelGenerators(Identifier blockId) {
        BlockModelGenerator generator = new BlockModelGenerator(blockId, "model");
        generator.createTexture("all", ALL_TEXTURE);
        generator.createCuboid(0, 0, 0, 16, 16, 16, "all");
        return List.of(generator);
    }

    @Override
    public List<BlockEventGenerator> getBlockEventGenerators(Identifier blockId) {
        BlockEventGenerator generator = new BlockEventGenerator(blockId, "events");
        return List.of(generator);
    }
}
