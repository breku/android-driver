package com.thinkfaster.model.scene;

import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.model.shape.GameTypeMenuItem;
import com.thinkfaster.service.HighScoreService;
import com.thinkfaster.util.ConstantsUtil;
import com.thinkfaster.util.LevelDifficulty;
import com.thinkfaster.util.MathParameter;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;

/**
 * User: Breku
 * Date: 08.10.13
 */
public class GameTypeScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene menuScene;
    private HighScoreService highScoreService;
    private Boolean multiplayer;

    public GameTypeScene(boolean multiplayer) {
        super(multiplayer);
    }

    @Override
    public void createScene(Object... objects) {
        init(objects);
        createBackground();
        createButtons();
    }

    private void init(Object... objects) {
        multiplayer = (Boolean) objects[0];
        highScoreService = new HighScoreService();
    }

    private void createBackground() {
        attachChild(new Sprite(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2, resourcesManager.getBackgroundGameTypeTextureRegion(), vertexBufferObjectManager));
    }

    private void createButtons() {
        menuScene = new MenuScene(camera);
        menuScene.setPosition(0, 0);
        menuScene.setBackgroundEnabled(false);
        menuScene.buildAnimations();


        Integer scorePositionX = 210;
        Integer scorePositionY;
        Integer gameTypeMenuItemID = 1;
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            scorePositionY = 390;
            for (MathParameter mathParameter : MathParameter.values()) {

                if (highScoreService.isLevelUnlocked(levelDifficulty, mathParameter)) {
                    menuScene.addMenuItem(new GameTypeMenuItem(gameTypeMenuItemID++, levelDifficulty, mathParameter, scorePositionX, scorePositionY - 4));
                    createStars(scorePositionX, scorePositionY - 15, levelDifficulty, mathParameter);
                } else {
                    // Lock
                    attachChild(new Sprite(scorePositionX, scorePositionY - 10, resourcesManager.getLockTextureRegion(), vertexBufferObjectManager));
                }

                scorePositionY -= 85;
            }
            scorePositionX += 240;
        }

        menuScene.setOnMenuItemClickListener(this);

        setChildScene(menuScene);

    }

    private void createStars(Integer positionX, Integer positionY, LevelDifficulty levelDifficulty, MathParameter mathParameter) {
        Integer score = highScoreService.getHighScoresFor(levelDifficulty, mathParameter);
        final int STAR_STRIDE = 25;

        if (score < 30) {
            // 0 Gold stars

            attachChild(new Sprite(positionX - STAR_STRIDE, positionY, resourcesManager.getStarWhiteTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX, positionY, resourcesManager.getStarWhiteTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX + STAR_STRIDE, positionY, resourcesManager.getStarWhiteTextureRegion(), vertexBufferObjectManager));

        } else if (score >= 30 && score < 40) {
            // 1 Gold stars

            attachChild(new Sprite(positionX - STAR_STRIDE, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX, positionY, resourcesManager.getStarWhiteTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX + STAR_STRIDE, positionY, resourcesManager.getStarWhiteTextureRegion(), vertexBufferObjectManager));

        } else if (score >= 40 && score < 50) {
            // 2 Gold stars

            attachChild(new Sprite(positionX - STAR_STRIDE, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX + STAR_STRIDE, positionY, resourcesManager.getStarWhiteTextureRegion(), vertexBufferObjectManager));

        } else if (score >= 50 && score < 80) {
            // 3 Gold stars

            attachChild(new Sprite(positionX - STAR_STRIDE, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX + STAR_STRIDE, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
        } else if (score >= 80) {
            // 3 Gold stars + awesome caption

            attachChild(new Sprite(positionX - STAR_STRIDE, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX + STAR_STRIDE, positionY, resourcesManager.getStarGoldTextureRegion(), vertexBufferObjectManager));
            attachChild(new Sprite(positionX, positionY - 20, resourcesManager.getAwesomeTextureRegion(), vertexBufferObjectManager));
        }


    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuSceneFrom(SceneType.GAMETYPE);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.GAMETYPE;
    }

    @Override
    public void disposeScene() {
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        if (highScoreService.isLevelUnlocked(((GameTypeMenuItem) pMenuItem).getLevelDifficulty(), ((GameTypeMenuItem) pMenuItem).getMathParameter())) {
            SceneManager.getInstance().loadGameScene(((GameTypeMenuItem) pMenuItem).getLevelDifficulty(), ((GameTypeMenuItem) pMenuItem).getMathParameter(), multiplayer);
            return true;
        }
        return false;

    }
}
