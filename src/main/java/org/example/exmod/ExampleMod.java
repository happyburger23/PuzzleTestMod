package org.example.exmod;

import com.github.puzzle.core.PuzzleRegistries;
import com.github.puzzle.core.localization.ILanguageFile;
import com.github.puzzle.core.localization.LanguageManager;
import com.github.puzzle.core.localization.files.LanguageFileVersion1;
import com.github.puzzle.core.resources.PuzzleGameAssetLoader;
import com.github.puzzle.game.block.DataModBlock;
import com.github.puzzle.game.events.OnPreLoadAssetsEvent;
import com.github.puzzle.game.events.OnRegisterBlockEvent;
import com.github.puzzle.game.events.OnRegisterZoneGenerators;
import com.github.puzzle.game.items.IModItem;
import com.github.puzzle.game.items.impl.BasicItem;
import com.github.puzzle.game.items.impl.BasicTool;
import com.github.puzzle.loader.entrypoint.interfaces.ModInitializer;
import finalforeach.cosmicreach.util.Identifier;
import org.example.exmod.block_entities.ExampleBlockEntity;
import org.example.exmod.blocks.Bedrock;
import org.example.exmod.blocks.custom.*;
import org.example.exmod.commands.Commands;
import org.example.exmod.items.*;
import org.example.exmod.worldgen.ExampleZoneGenerator;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.Objects;

//TODO: RECIPES
//TODO: ENTITY
//TODO: COMPLEX BLOCK/BLOCK ENTITY
//TODO: BLOCK ROTATIONS

public class ExampleMod implements ModInitializer {

    @Override
    public void onInit() {
        PuzzleRegistries.EVENT_BUS.register(this);

        Constants.LOGGER.info("Hello From INIT");
        ExampleBlockEntity.register();

        Commands.register();

        IModItem.registerItem(new ExamplePickaxe());
        IModItem.registerItem(new ExampleCyclingItem());
        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "example_item")));
        IModItem.registerItem(new BasicTool(Identifier.of(Constants.MOD_ID, "stone_sword")));

        //custom items
        IModItem.registerItem(new TestFunctionalItem());

        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "raw_zircon")));
        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "zircon")));

        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "steel_ingot")));
        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "steel_dust")));
        IModItem.registerItem(new BasicItem(Identifier.of(Constants.MOD_ID, "graphite")));
    }

    //BLOCK REGISTRY
    @Subscribe
    public void onEvent(OnRegisterBlockEvent event) {
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "diamond_block.json")));
        event.registerBlock(Bedrock::new);

        //complex blocks
        event.registerBlock(PackedLunarSoilBricks::new);
        event.registerBlock(ZirconBlock::new);
        event.registerBlock(RawZirconBlock::new);

        //simple blocks
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "chiseled_packed_lunar_soil.json")));

        //blocks with custom models
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "rail.json")));
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "pedestal.json")));
    }

    //WORLDGEN REGISTRY
    @Subscribe
    public void onEvent(OnRegisterZoneGenerators event) {
        event.registerGenerator(ExampleZoneGenerator::new);
    }

    //LANG FILE REGISTRY?
    @Subscribe
    public void onEvent(OnPreLoadAssetsEvent event) {
        ILanguageFile lang = null;
        try {
            lang = LanguageFileVersion1.loadLanguageFile(
                    Objects.requireNonNull(PuzzleGameAssetLoader.locateAsset(Identifier.of(Constants.MOD_ID, "languages/en-US.json")))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LanguageManager.registerLanguageFile(lang);
    }
}
