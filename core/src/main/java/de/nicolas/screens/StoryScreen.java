package de.nicolas.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import de.nicolas.actors.Background;
import de.nicolas.actors.Kelsoe;
import de.nicolas.utils.actions.Scene;
import de.nicolas.utils.actions.SceneActions;
import de.nicolas.utils.actors.BaseActor;
import de.nicolas.utils.actors.DialogBox;
import de.nicolas.utils.game.BaseGame;
import de.nicolas.utils.game.SceneSegment;
import de.nicolas.utils.screens.BaseScreen;

public class StoryScreen extends BaseScreen {

    private Scene scene;
    private Background background;
    private Kelsoe kelsoe;
    private DialogBox dialogBox;
    private BaseActor continueKey;
    private Table buttonTable;
    private BaseActor theEnd;

    @Override
    public void initialize() {
        background = new Background(0, 0, mainStage);
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        kelsoe = new Kelsoe(0, 0, mainStage);

        dialogBox = new DialogBox(0, 0, uiStage);
        dialogBox.setDialogSize(600, 150);
        dialogBox.setBackgroundColor(new Color(0.2f, 0.2f, 0.2f, 1));
        dialogBox.setVisible(false);

        continueKey = new BaseActor(0, 0, uiStage);
        continueKey.loadTexture("assets/key-C.png");
        continueKey.sizeBy(32, 32);
        continueKey.setVisible(false);

        dialogBox.addActor(continueKey);
        dialogBox.setPosition(dialogBox.getWidth(), - continueKey.getWidth(), 0);

        buttonTable = new Table();
        buttonTable.setVisible(false);

        uiTable.add().expandY();
        uiTable.row();
        uiTable.add(buttonTable);
        uiTable.row();
        uiTable.add(dialogBox);

        theEnd = new BaseActor(0, 0, mainStage);
        theEnd.loadTexture("assets/the-end.png");
        theEnd.centerAtActor(background);
        theEnd.setScale(2);
        theEnd.setOpacity(0);

        scene = new Scene();
        mainStage.addActor(scene);
        hallway();
    }

    public void hallway(){
        background.setAnimation(background.hallway);
        dialogBox.setText(" ");
        kelsoe.addAction(SceneActions.moveToOutsideLeft(0));

        scene.addSegment(new SceneSegment(background, Actions.fadeIn(1)));
        scene.addSegment(new SceneSegment(kelsoe, SceneActions.moveToScreenCenter(1)));
        scene.addSegment(new SceneSegment(dialogBox, SceneActions.show()));

        addTextSequence("Mein Name ist Kelsoe Kismet. Ich bin Student an der Albert-Ludwigs-Universität zu Freiburg");
        addTextSequence(" Ich kann manchmal etwas vergesslich sein. Im Moment suche ich meine Hausarbeit!");

        scene.addSegment(new SceneSegment(dialogBox, SceneActions.hide()));
        scene.addSegment(new SceneSegment(kelsoe, SceneActions.moveToOutsideRight(1)));
        scene.addSegment(new SceneSegment(background, Actions.fadeOut(1)));

        scene.addSegment(new SceneSegment(background, Actions.run(() ->{
            classroom();
        })));

        scene.start();
    }

    public void classroom(){
        scene.clearSegments();
        background.setAnimation(background.classroom);
        dialogBox.setText(" ");
        kelsoe.addAction(SceneActions.moveToOutsideLeft(0));

        scene.addSegment(new SceneSegment(background, Actions.fadeIn(1)));
        scene.addSegment(new SceneSegment(kelsoe, SceneActions.moveToScreenCenter(1)));
        scene.addSegment(new SceneSegment(dialogBox, Actions.show()));

        addTextSequence("Dies ist mein Klassenraum. Meine Hausarbeit ist aber nicht hier!");
        addTextSequence("Wo sollte ich wohl als nächstes danach suchen?");

        scene.addSegment(new SceneSegment(buttonTable, Actions.show()));

        TextButton scienceLabButton = new TextButton("Schaue im Wissenschaftslabor nach!",
            BaseGame.textButtonStyle);
        scienceLabButton.addListener(
            (Event e) ->{
                if (!(e instanceof InputEvent) ||
                !((InputEvent)e).getType().equals(InputEvent.Type.touchDown)){
                    return false;
                }
                scene.addSegment(new SceneSegment(buttonTable, Actions.hide()));
                addTextSequence("Supper Idee! Ich schaue im Wissenschaftslabor nach.");
                scene.addSegment(new SceneSegment(dialogBox, Actions.hide()));
                scene.addSegment(new SceneSegment(kelsoe, SceneActions.moveToOutsideLeft(1)));
                scene.addSegment(new SceneSegment(background, Actions.fadeOut(1)));
                scene.addSegment(new SceneSegment(background, Actions.run(
                    () -> {
                        scienceLab();
                    }
                )));
                return false;
            }
        );

        TextButton libraryButton = new TextButton("Schaue in der Bücherei nach!",
            BaseGame.textButtonStyle);
        libraryButton.addListener(
            (Event e) ->{
                if (!(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown)){
                    return false;
                }
                scene.addSegment(new SceneSegment(buttonTable, Actions.hide()));
                addTextSequence("Supper Idee! Ich schaue in der Bücherei nach.");
                scene.addSegment(new SceneSegment(dialogBox, Actions.hide()));
                scene.addSegment(new SceneSegment(kelsoe, SceneActions.moveToOutsideLeft(1)));
                scene.addSegment(new SceneSegment(background, Actions.fadeOut(1)));
                scene.addSegment(new SceneSegment(background, Actions.run(
                    () -> {
                        library();
                    }
                )));
                return false;
            }
        );

        buttonTable.clearChildren();
        buttonTable.add(scienceLabButton);
        buttonTable.row();
        buttonTable.add(libraryButton);

        scene.start();
    }

    public void scienceLab(){}

    public void library(){}

    @Override
    public void update(float delta) {

    }

    public void addTextSequence(String text){
        scene.addSegment(new SceneSegment(dialogBox, SceneActions.typeWriter(text)));
        scene.addSegment(new SceneSegment(continueKey, SceneActions.show()));
        scene.addSegment(new SceneSegment(background, SceneActions.pause()));
        scene.addSegment(new SceneSegment(continueKey, SceneActions.hide()));
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.C){
            scene.loadNextSegment();
        }
        return false;
    }
}
