/**
 * Created by sanchezz985 on 20/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

import java.io.IOException;

import io.pagelab.aegame.scene.AbstractScene;
import io.pagelab.aegame.scene.GameScene;


public class MainGameActivity extends BaseGameActivity {

    // region Static
    public static final int CAMERA_WIDTH  = 480;
    public static final int CAMERA_HEIGHT = 800;
    //endregion

    //region Fields
    //endregion

    //region Private Methods
    //endregion

    //region Methods

    /**
     * TODO - Make comments for this method
     *
     * @Override
     * @return
     */
    public EngineOptions onCreateEngineOptions() {

        Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        IResolutionPolicy resolutionPolicy = new FillResolutionPolicy();
        EngineOptions engineoptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, resolutionPolicy, camera);
        engineoptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineoptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

        // for improve the quality in elements
        engineoptions.getRenderOptions().setDithering(true);
        return engineoptions;
    }

    /**
     * TODO - Make comments for this method
     *
     * @Override
     * @param pOnCreateResourcesCallback
     * @throws IOException
     */
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {

        // call the Resource Manager methods
        ResourceManager.getInstance().create(this, getEngine(), getEngine().getCamera(), getVertexBufferObjectManager());
        ResourceManager.getInstance().loadFont();
        ResourceManager.getInstance().loadGameAudio();
        ResourceManager.getInstance().loadGameGraphics();

        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    /**
     * TODO - Make comments for this method
     *
     * @Override
     * @param pOnCreateSceneCallback
     * @throws IOException
     */
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        Scene scene = new GameScene();
        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    /**
     * TODO - Make comments for this method
     *
     * @Override
     * @param pScene
     * @param pOnPopulateSceneCallback
     * @throws IOException
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {

        AbstractScene scene = (AbstractScene)pScene;
        scene.populate();

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
    //endregion
}
