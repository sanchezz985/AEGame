/**
 * Created by sanchezz985 on 23/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.scene;

import android.widget.Toast;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.debug.Debug;

import io.pagelab.aegame.R;
import io.pagelab.aegame.entity.Player;
import io.pagelab.aegame.factory.PlayerFactory;

public class GameScene extends AbstractScene{

    //region Static
    //endregion

    //region Fields

    private Player player;

    private Text scoreText;
    //endregion

    /**
     * Constructor
     */
    public GameScene(){
        PlayerFactory.getInstance().create(vbom);
    }

    //region Private Methods

    /**
     * Creates the background with 2 entities inside it
     */
    private void createBackground(){

        // create the entity for background
        Entity background = new Entity();

        // create the clouds entities
        Sprite cloud1 = new Sprite(200, 300, res.cloud1TextureRegion, vbom);
        Sprite cloud2 = new Sprite(300, 600, res.cloud2TextureRegion, vbom);

        // attach the entities in the background
        background.attachChild(cloud1);
        background.attachChild(cloud2);

        // set background and define the color
        setBackground(new EntityBackground(0.82f, 0.96f, 0.97f, background));
    }

    /**
     * Create the main character and attach it into the scene
     */
    private void createPlayer(){
        player = PlayerFactory.getInstance().createPlayer(240, 400);
        attachChild(player);
    }

    /**
     * Create the HUD object for display the score
     */
    private void createHUD(){

        HUD hud = new HUD();
        scoreText = new Text(16, 784, res.font, "0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
        scoreText.setAnchorCenter(0, 1);
        hud.attachChild(scoreText);

        camera.setHUD(hud);
    }

    //endregion

    //region Methods

    @Override
    public void populate() {
        createBackground();
        createPlayer();
        createHUD();

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(activity, activity.getString(R.string.hello_world), Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    //endregion

}
