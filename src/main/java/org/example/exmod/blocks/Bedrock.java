package org.example.exmod.blocks;

import com.github.puzzle.game.block.IModBlock;
import com.github.puzzle.game.generators.BlockEventGenerator;
import com.github.puzzle.game.generators.BlockGenerator;
import com.github.puzzle.game.generators.BlockModelGenerator;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.items.Item;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.ui.UI;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.Zone;
import org.example.exmod.Constants;
import org.example.exmod.block_entities.ExampleBlockEntity;

import java.util.List;
import java.util.Map;

public class Bedrock implements IModBlock {

    public static final Identifier BLOCK_ID = Identifier.of(Constants.MOD_ID, "bedrock");

    public static final Identifier ALL_TEXTURE = Identifier.of("base", "textures/blocks/lunar_soil.png");

    @Override
    public Identifier getIdentifier() {
        return BLOCK_ID;
    }

    @Override
    public String getName() {
        return "Bedrock Block Entity";
    }

    @Override
    public void onBreak(Zone zone, Player player, BlockState blockState, BlockPosition position) {
        ItemSlot slot = UI.hotbar.getSelectedSlot();
        if(slot == null) return;
        if(slot.itemStack != null) {
            Item selected = slot.itemStack.getItem();
            String itemId = selected.getID();
            if(itemId.startsWith(BLOCK_ID.toString())) {
                // make the block breakable when the player holds bedrock
                IModBlock.super.onBreak(zone, player, blockState, position);
            }
        }
        // make the block unbreakable, by omitting the super call here
    }

    @Override
    public BlockGenerator getBlockGenerator() {
        BlockGenerator generator = new BlockGenerator(BLOCK_ID);
        generator.createBlockState("default", "model", true, "events", true);
        generator.addBlockEntity(ExampleBlockEntity.id.toString(), Map.of());
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