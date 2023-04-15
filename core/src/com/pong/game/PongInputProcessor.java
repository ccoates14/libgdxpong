package com.pong.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class PongInputProcessor implements InputProcessor, PaddleInput {

    private boolean upPressed;
    private boolean downPressed;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            upPressed = true;
        }
        if (keycode == Input.Keys.DOWN) {
            downPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.UP) {
            upPressed = false;
        }
        if (keycode == Input.Keys.DOWN) {
            downPressed = false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    // Other methods omitted for brevity

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }
}
