package com.thinkfaster.model.shape;

import com.thinkfaster.util.MathParameter;

/**
 * User: Breku
 * Date: 19.10.13
 */
public class MathEquation {

    private MathParameter mathParameter;
    private Integer x;
    private Integer y;
    private Integer result;

    public boolean isCorrectAndYDifferentThanZero() {
        switch (mathParameter) {
            case ADD:
            case SUB:
            case MUL:
                return isCorrect();
            case DIV:
                return y == 0 || (x / y == result && x % y == 0);
            default:
                throw new UnsupportedOperationException();
        }
    }

    public boolean isCorrect() {
        switch (mathParameter) {
            case ADD:
                return x + y == result;
            case SUB:
                return x - y == result;
            case MUL:
                return x * y == result;
            case DIV:
                return y != 0 && x / y == result && x % y == 0;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public boolean isTheDivisionRestZero() {
        switch (mathParameter) {
            case DIV:
                return x % y == 0;
        }
        return true;

    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public MathParameter getMathParameter() {
        return mathParameter;
    }

    public void setMathParameter(MathParameter mathParameter) {
        this.mathParameter = mathParameter;
    }

    @Override
    public String toString() {
        switch (mathParameter) {
            case ADD:
                if (isCorrect()) {
                    return x + " + " + y + " = " + result;
                } else {
                    return x + " + " + y + " = " + result;
                }
            case SUB:
                if (isCorrect()) {
                    return x + " - " + y + " = " + result;
                } else {
                    return x + " - " + y + " = " + result;
                }
            case MUL:
                if (isCorrect()) {
                    return x + " * " + y + " = " + result;
                } else {
                    return x + " * " + y + " = " + result;
                }
            case DIV:
                if (isCorrect()) {
                    return x + " / " + y + " = " + result;
                } else {
                    return x + " / " + y + " = " + result;
                }
            default:
                throw new UnsupportedOperationException();
        }
    }
}
