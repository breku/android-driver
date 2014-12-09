package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * User: Breku
 * Date: 20.10.13
 */
public class GameButton extends Sprite {

    private boolean clicked = false;

    public GameButton(final float pX, final float pY, final ITextureRegion pTextureRegion) {
        super(pX, pY, pTextureRegion, ResourcesManager.getInstance().getVertexBufferObjectManager());
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        switch (pSceneTouchEvent.getAction()) {
            case TouchEvent.ACTION_UP:
                clicked = true;
                return true;
        }
        return false;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void clearState() {
        clicked = false;
    }
}
