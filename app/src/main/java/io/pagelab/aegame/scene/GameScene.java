/**
 * Created by sanchezz985 on 23/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.scene;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.debug.Debug;

import io.pagelab.aegame.entity.Player;
import io.pagelab.aegame.factory.PlayerFactory;

public class GameScene extends AbstractScene{

    //region Static
    //endregion

    //region Fields

    private Player player;
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

    //endregion

    //region Methods

    @Override
    public void populate() {
        createBackground();
        createPlayer();
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    //endregion

}
