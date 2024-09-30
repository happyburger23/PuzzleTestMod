package org.example.exmod.entities;

import com.badlogic.gdx.utils.ObjectMap;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.ItemEntity;
import finalforeach.cosmicreach.io.CRBinDeserializer;

import java.util.function.Function;
import java.util.function.Supplier;

public class CustomEntityRegisterer {
    public static final ObjectMap<String, Function<CRBinDeserializer, Entity>> entityCreators = new ObjectMap();

    public CustomEntityRegisterer() {
        //
    }


    private static Entity readToEntity(Entity e, CRBinDeserializer deserial) {
        if (deserial != null) {
            e.read(deserial);
        }

        return e;
    }

    public static void registerEntityCreator(String entityTypeId, Function<CRBinDeserializer, Entity> creator) {
        entityCreators.put(entityTypeId, creator);
    }

    public static void registerEntityCreator(String entityTypeId, Supplier<Entity> supplier) {
        registerEntityCreator(entityTypeId, (deserial) -> {
            return readToEntity((Entity)supplier.get(), deserial);
        });
    }

    public static Entity get(String entityTypeId, CRBinDeserializer deserial) {
        Function<CRBinDeserializer, Entity> c = (Function)entityCreators.get(entityTypeId);
        return c == null ? null : (Entity)c.apply(deserial);
    }

    public static Entity get(String entityTypeId) {
        return get(entityTypeId, (CRBinDeserializer)null);
    }

    static {
        registerEntityCreator(WoodenCartEntity.ENTITY_TYPE_ID, WoodenCartEntity::new);

        registerEntityCreator("base:entity_item", (deserial) -> {
            return readToEntity(new ItemEntity(), deserial);
        });
    }
}
