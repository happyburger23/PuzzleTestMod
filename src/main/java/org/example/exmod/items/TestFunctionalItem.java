package org.example.exmod.items;

import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.data.DataTagManifest;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.EntityCreator;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;

public class TestFunctionalItem implements IModItem {
    Identifier id = Identifier.of(Constants.MOD_ID, "test_functional_item");
    DataTagManifest tagManifest = new DataTagManifest();

    public TestFunctionalItem() {
        addTexture(IModItem.MODEL_2_5D_ITEM, Identifier.of(Constants.MOD_ID, "test_functional_item.png"));
    }

    @Override
    public void use(ItemSlot slot, Player player) {
        Entity mob = EntityCreator.get("base:entity_drone_interceptor");

        player.getZone(InGame.world).allEntities.add(mob);
        mob.setPosition(player.getPosition());

        //IModItem.super.use(slot, player);
    }

    @Override
    public DataTagManifest getTagManifest() {
        return tagManifest;
    }

    @Override
    public String getName() {
        return "Test Functional Item";
    }

    @Override
    public Identifier getIdentifier() {
        return id;
    }

    @Override
    public boolean isTool() {
        return false;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean isCatalogHidden() {
        return false;
    }
}
