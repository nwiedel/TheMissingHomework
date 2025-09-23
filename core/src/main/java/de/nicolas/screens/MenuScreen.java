package de.nicolas.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import de.nicolas.utils.actors.BaseActor;
import de.nicolas.utils.game.BaseGame;
import de.nicolas.utils.screens.BaseScreen;

public class MenuScreen extends BaseScreen {

    @Override
    public void initialize() {
        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadTexture("assets/notebook.jpg");
        background.setSize(800, 600);

        BaseActor title = new BaseActor(0, 0, mainStage);
        title.loadTexture("assets/missing-homework.png");

        TextButton startButton = new TextButton("Start", BaseGame.textButtonStyle);
        startButton.addListener(
            (Event e) -> {
                if (!(e instanceof InputEvent) ||
                !((InputEvent)e).getType().equals(InputEvent.Type.touchDown)){
                    return false;
                }
                BaseGame.setActiveScreen(new StoryScreen());
                return false;
            }
        );

        TextButton quitButton = new TextButton("Quit", BaseGame.textButtonStyle);
        quitButton.addListener(
            (Event e) -> {
                if (!(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown)){
                    return false;
                }
               Gdx.app.exit();
                return false;
            }
        );

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER))
            BaseGame.setActiveScreen( new StoryScreen() );
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        return false;
    }
}
