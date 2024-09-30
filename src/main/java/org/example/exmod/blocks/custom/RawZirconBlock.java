package org.example.exmod.blocks.custom;

import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.generators.BlockGenerator;
import com.github.puzzle.game.generators.BlockModelGenerator;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;

import java.util.List;

public class RawZirconBlock implements IModBlock {

    public static final Identifier BLOCK_ID = Identifier.of(Constants.MOD_ID, "raw_zircon_block");

    public BlockGenerator getBlockGenerator() {
        Identifier identifier = getIdentifier();
        BlockGenerator generator = new BlockGenerator(identifier);
        BlockGenerator.State state = generator.createBlockState("default", Constants.MOD_ID + ":raw_zircon_block", false);
        state.stateGenerators = new String[]{
                "base:slabs_all",
                "base:stairs_seamless_all"
        };
        state.dropId = identifier.getNamespace() + ":" + identifier.getName() + "[default]";
        state.blockEventsId = "base:block_events_default";
        return generator;
    }

    @Override
    public List<BlockModelGenerator> getBlockModelGenerators(Identifier blockId) {
        return IModBlock.super.getBlockModelGenerators(blockId);
    }

    @Override
    public Identifier getIdentifier() {
        return BLOCK_ID;
    }

    @Override
    public String getName() {
        return "Raw Zircon Block";
    }
}
