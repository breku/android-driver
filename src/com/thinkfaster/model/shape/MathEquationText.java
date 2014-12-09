package com.thinkfaster.model.shape;

import com.thinkfaster.manager.ResourcesManager;
import org.andengine.entity.text.Text;

/**
 * User: Breku
 * Date: 20.10.13
 */
public class MathEquationText extends Text {

    private MathEquation mathEquation;


    public MathEquationText(final float pX, final float pY, MathEquation mathEquation) {
        super(pX, pY, ResourcesManager.getInstance().getWhiteFont(), mathEquation.toString(),
                ResourcesManager.getInstance().getVertexBufferObjectManager());
        this.mathEquation = mathEquation;
    }

    public MathEquation getMathEquation() {
        return mathEquation;
    }
}
