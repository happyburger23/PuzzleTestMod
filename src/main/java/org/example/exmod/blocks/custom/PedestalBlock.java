package org.example.exmod.blocks.custom;

import com.github.puzzle.game.block.IModBlock;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;

public class PedestalBlock implements IModBlock {
    public static final Identifier BLOCK_ID = Identifier.of(Constants.MOD_ID, "pedestal");

    @Override
    public Identifier getIdentifier() {
        return BLOCK_ID;
    }

    @Override
    public String getName() {
        return "Pedestal";
    }
}
