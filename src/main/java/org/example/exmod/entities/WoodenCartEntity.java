package org.example.exmod.entities;

import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.io.CRBinDeserializer;
import finalforeach.cosmicreach.items.ItemStack;
import finalforeach.cosmicreach.items.loot.Loot;
import finalforeach.cosmicreach.rendering.entities.IEntityModel;
import finalforeach.cosmicreach.world.Zone;

public class WoodenCartEntity extends Entity {
    public static final String ENTITY_TYPE_ID = "example-mod:wooden_cart";
    //public static final String LOOT_ID = "example-mod:wooden_cart"; //loot table file registered
    //Loot loot;
    public static IEntityModel woodenCartModel;

    public WoodenCartEntity() {
        super(ENTITY_TYPE_ID);
        //this.loot = Loot.get(LOOT_ID);

        Threads.runOnMainThread(() -> {
            if (woodenCartModel == null) {
                woodenCartModel = GameSingletons.entityModelLoader.load(this, "wooden_cart.json", null, null, "wooden_cart_item.png");
            }

            this.modelInstance = (this.woodenCartModel).getNewModelInstance();
        });
    }

    public void hit(float amount) {
        super.hit(amount);
    }

    @Override
    public void onUseInteraction(Zone zone, Player player, ItemStack heldItemStack) {
        super.onUseInteraction(zone, player, heldItemStack);
    }

    protected void onDeath(Zone zone) {
        //this.loot.dropAsItems(zone, this.position);
        super.onDeath(zone);
    }

    @Override
    public void read(CRBinDeserializer deserial) {
        super.read(deserial);
        this.localBoundingBox.min.set(-1, -1, -1);
        this.localBoundingBox.max.set(1, 1, 1);
        this.localBoundingBox.update();
    }
}
