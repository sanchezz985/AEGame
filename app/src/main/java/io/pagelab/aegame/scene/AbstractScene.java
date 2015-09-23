/**
 * Created by sanchezz985 on 22/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import io.pagelab.aegame.MainGameActivity;
import io.pagelab.aegame.ResourceManager;

public abstract class AbstractScene extends Scene {

    // region Static
    // endregion

    //region Fields

    protected ResourceManager res = ResourceManager.getInstance();
    protected Engine engine = res.engine;
    protected MainGameActivity activity = res.activity;
    protected VertexBufferObjectManager vbom = res.vbom;
    protected Camera camera = res.camera;

    //endregion

    //region Private Methods
    //endregion

    //region Methods

    public abstract void populate();

    public void destroy(){}

    public void onBackKeyPressed() { Debug.d("Back key pressed"); }

    public abstract void onPause();

    public abstract void onResume();

    //endregion
}
