package com.khopa.core.views.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Scene with changeable input adaptater controllers
 * @author Clément Perreau
 * @date 14/07/2014
 */
public class ControlableScene extends Scene{

    /**
     * Switchable scene controller
     */
    protected SceneControler controler;

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (controler != null) {
            return controler.touchDragged(screenX, screenY, pointer);
        } else {
            return super.touchDragged(screenX, screenY, pointer);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("TEST", "TOUCHDOWN");
        if (controler != null) {
            return controler.touchDown(screenX, screenY, pointer, button);
        } else {
            return super.touchDown(screenX, screenY, pointer, button);
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (controler != null) {
            return controler.touchUp(screenX, screenY, pointer, button);
        } else {
            return super.touchUp(screenX, screenY, pointer, button);
        }
    }

    @Override
    public boolean scrolled(int amount) {
        if (controler != null) {
            return controler.scrolled(amount);
        } else {
            return super.scrolled(amount);
        }
    }

    @Override
    public boolean keyTyped(char character) {
        if (controler != null) {
            return controler.keyTyped(character);
        } else {
            return super.keyTyped(character);
        }
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (controler != null) {
            return controler.keyUp(keyCode);
        } else {
            return super.keyUp(keyCode);
        }
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (controler != null) {
            return controler.keyDown(keyCode);
        } else {
            return super.keyDown(keyCode);
        }
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (controler != null) {
            return controler.mouseMoved(screenX, screenY);
        } else {
            return super.mouseMoved(screenX, screenY);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (controler != null) controler.update(delta);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if (controler != null) {
            return controler.touchDown(x, y, pointer, button);
        } else {
            return false;
        }
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log("TEST", "TAP");
        if (controler != null) {
            return controler.tap(x, y, count, button);
        } else {
            return false;
        }
    }

    @Override
    public boolean longPress(float x, float y) {
        Gdx.app.log("TEST", "LongPress");
        if (controler != null) {
            return controler.longPress(x, y);
        } else {
            return false;
        }
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log("TEST", "Fling");
        if (controler != null) {
            return controler.fling(velocityX, velocityY, button);
        } else {
            return false;
        }
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log("TEST", "PAN");
        if (controler != null) {
            return controler.pan(x,y,deltaX,deltaY);
        } else {
            return false;
        }
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("TEST", "PANSTOP");
        if (controler != null) {
            return controler.panStop(x,y,pointer,button);
        } else {
            return false;
        }
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        Gdx.app.log("TEST", "ZOOM");
        if (controler != null) {
            return controler.zoom(initialDistance, distance);
        } else {
            return false;
        }
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        Gdx.app.log("TEST", "PINCH");
        if (controler != null) {
            return controler.pinch(initialPointer1, initialPointer2, pointer1, pointer2);
        } else {
            return false;
        }
    }

    // ----- Getters - Setters ------ \\

    public SceneControler getControler() {
        return controler;
    }

    public void setControler(SceneControler controler) {
        this.controler = controler;
    }
}
