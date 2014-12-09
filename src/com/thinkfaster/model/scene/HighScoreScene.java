package com.thinkfaster.model.scene;

import com.thinkfaster.manager.ResourcesManager;
import com.thinkfaster.manager.SceneManager;
import com.thinkfaster.service.HighScoreService;
import com.thinkfaster.util.ConstantsUtil;
import com.thinkfaster.util.LevelDifficulty;
import com.thinkfaster.util.MathParameter;
import com.thinkfaster.util.SceneType;
import org.andengine.entity.modifier.ColorModifier;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;

/**
 * User: Breku
 * Date: 06.10.13
 */
public class HighScoreScene extends BaseScene implements IOnSceneTouchListener {

    private HighScoreService highScoreService;

    /**
     * Constructor
     *
     * @param objects object[0] - Integer score
     *                object[1] - LevelDifficulty levelDifficulty
     *                object[2] - MathParameter mathParameter
     */
    public HighScoreScene(Object... objects) {
        super(objects);
    }

    @Override
    public void createScene(Object... objects) {
        init();
        createBackground();
        createRecordsTable(objects);
        setOnSceneTouchListener(this);
    }

    private void init() {
        highScoreService = new HighScoreService();
    }

    private void createBackground() {
        attachChild(new Sprite(ConstantsUtil.SCREEN_WIDTH / 2, ConstantsUtil.SCREEN_HEIGHT / 2,
                ResourcesManager.getInstance().getRecordBackgroundTextureRegion(), vertexBufferObjectManager));

    }

    private void createRecordsTable(Object... objects) {
        if (objects.length == 0) {
            createNormalTable();
        } else {
            createTableWithAnimatedScore((Integer) objects[0], (LevelDifficulty) objects[1], (MathParameter) objects[2]);
        }
    }

    private void createNormalTable() {
        Integer scorePositionX = 210;
        Integer scorePositionY;
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            scorePositionY = 380;
            for (MathParameter mathParameter : MathParameter.values()) {
                Integer score = highScoreService.getHighScoresFor(levelDifficulty, mathParameter);
                createScoreItem(scorePositionX, scorePositionY, score);
                scorePositionY -= 85;
            }
            scorePositionX += 240;
        }
    }

    private void createTableWithAnimatedScore(Integer currentScore, LevelDifficulty currentLevelDifficulty, MathParameter currentMathParameter) {
        Integer scorePositionX = 210;
        Integer scorePositionY;
        for (LevelDifficulty levelDifficulty : LevelDifficulty.values()) {
            scorePositionY = 380;
            for (MathParameter mathParameter : MathParameter.values()) {
                if (levelDifficulty == currentLevelDifficulty && mathParameter == currentMathParameter) {
                    createAnimatedScoreItem(scorePositionX, scorePositionY, currentScore);
                } else {
                    Integer score = highScoreService.getHighScoresFor(levelDifficulty, mathParameter);
                    createScoreItem(scorePositionX, scorePositionY, score);
                }
                scorePositionY -= 85;
            }
            scorePositionX += 240;
        }
    }

    private void createScoreItem(Integer scorePositionX, Integer scorePositionY, Integer score) {
        attachChild(new Text(scorePositionX, scorePositionY, ResourcesManager.getInstance().getWhiteFont(),
                score.toString(), vertexBufferObjectManager));
    }

    private void createAnimatedScoreItem(Integer scorePositionX, Integer scorePositionY, Integer currentScore) {
        Text text = new Text(scorePositionX, scorePositionY, ResourcesManager.getInstance().getGreenFont(),
                currentScore.toString(), vertexBufferObjectManager);
        text.registerEntityModifier(new ParallelEntityModifier(
                new RotationModifier(5.0f, 0.0f, 360.0f),
                new ColorModifier(5.0f, Color.WHITE, Color.GREEN),
                new FadeInModifier(10.0f)));
        attachChild(text);
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuSceneFrom(SceneType.RECORDS);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.RECORDS;
    }

    @Override
    public void disposeScene() {
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionUp()) {
            SceneManager.getInstance().loadMenuSceneFrom(SceneType.RECORDS);
        }
        return false;
    }
}
