package org.example.exmod.items;

import com.badlogic.gdx.math.Vector3;
import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.data.DataTagManifest;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.EntityCreator;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.items.ItemSlot;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.Constants;

public class WoodenCartItem implements IModItem {
    Identifier id = Identifier.of(Constants.MOD_ID, "wooden_cart_item");
    DataTagManifest tagManifest = new DataTagManifest();

    public WoodenCartItem() {
        addTexture(IModItem.MODEL_2_5D_ITEM, Identifier.of(Constants.MOD_ID, "wooden_cart_item.png"));
    }


    @Override
    public void use(ItemSlot slot, Player player) {
        Entity mob = EntityCreator.get("example-mod:wooden_cart");

        //spawn entity on the player
        player.getZone(InGame.world).allEntities.add(mob);
        mob.setPosition(player.getPosition());
    }

    //gets player look direction when looking at a face of a block
    private Vector3 getDirection(Vector3 orgen, Vector3 offset) {
        Vector3 newoffset = new Vector3(orgen).sub(offset);

        if(newoffset.x == 0) newoffset.x += 1; // add 1 to anything 0
        else if(newoffset.y == 0) newoffset.y += 1;
        else if(newoffset.z == 0) newoffset.z += 1;

        if(newoffset.x < 0 && newoffset.x > -1) newoffset.x = 0; // 0 anything that not a whole number
        if(newoffset.y < 0 && newoffset.y > -1) newoffset.y = 0;
        if(newoffset.z < 0 && newoffset.z > -1) newoffset.z = 0;

        if(newoffset.x != 0) newoffset.x *= -1; // flips
        if(newoffset.y != 0) newoffset.y *= -1;
        if(newoffset.z != 0) newoffset.z *= -1;

        return newoffset;
    }

    @Override
    public DataTagManifest getTagManifest() {
        return tagManifest;
    }

    @Override
    public String getName() {
        return "Wooden Cart Item";
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
