package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by brekol on 09.12.14.
 */
public class Player extends AnimatedSprite {


    public Player(float pX, float pY, VertexBufferObjectManager pVertexBufferObjectManager, Camera camera) {
        super(pX, pY, ResourcesManager.getInstance().getPlayerRegion(), pVertexBufferObjectManager);
        camera.setChaseEntity(this);
    }
}
