package org.example.exmod.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.github.puzzle.game.ui.screens.BasePuzzleScreen;
import finalforeach.cosmicreach.Threads;
import finalforeach.cosmicreach.ui.UI;
import org.example.exmod.block_entities.TestBlockEntity;

public class TestScreen extends BasePuzzleScreen {
    static Drawable buttonRight;
    static Drawable buttonLeft;
    static TextField.TextFieldStyle style;
    static Label.LabelStyle styleTextSize;
    static final String baseText = "line ";

    public TestScreen() {
        //
    }

    public TestScreen(TestBlockEntity tbe) {
        Threads.runOnMainThread(() -> {
            if (style.background == null) {
                style.background = new NinePatchDrawable(UI.container9Patch);
                buttonRight = new NinePatchDrawable(UI.containerBackground9Patch);
                buttonLeft = new NinePatchDrawable(UI.containerBackground9Patch);
            }
        });
    }
}
