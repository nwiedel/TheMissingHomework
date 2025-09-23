package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import de.nicolas.screens.MenuScreen;
import de.nicolas.utils.game.BaseGame;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class HomeworkGame extends BaseGame {

    @Override
    public void create() {
        super.create();
        setActiveScreen(new MenuScreen());
    }
}
