package de.nicolas.utils.actions;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Action;
import de.nicolas.utils.actors.BaseActor;

public class SetAnimationAction extends Action {

    protected Animation animationToDisplay;

    public SetAnimationAction(Animation animation){
        this.animationToDisplay = animation;
    }

    @Override
    public boolean act(float v) {
        BaseActor baseActor = (BaseActor) target;
        baseActor.setAnimation(animationToDisplay);
        return true;
    }
}
