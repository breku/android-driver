package com.thinkfaster.model.scene;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.util.ConstantsUtil;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;

/**
 * User: Breku
 * Date: 21.09.13
 */
public class LoadingScene extends BaseScene {

    @Override
    public void createScene(Object... objects) {
        createBackground();
        attachChild(new Text(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2, ResourcesManager.getInstance().getChalkFont(),
                "loading...", vertexBufferObjectManager));
    }

    private void createBackground() {
        attachChild(new Sprite(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2, resourcesManager.getLoadingTextureRegion(), vertexBufferObjectManager));
    }

    @Override
    public void onBackKeyPressed() {
        return;
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.LOADING;
    }

    @Override
    public void disposeScene() {
    }
}
