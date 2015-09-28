/**
 * Created by sanchezz985 on 23/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.scene;

import android.widget.Toast;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.collision.CollisionHandler;
import org.andengine.engine.handler.collision.ICollisionCallback;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.RotationAtModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.EntityBackground;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.ease.EaseExponentialIn;

import io.pagelab.aegame.R;
import io.pagelab.aegame.entity.Player;
import io.pagelab.aegame.factory.PlayerFactory;

public class GameScene extends AbstractScene implements IAccelerationListener{

    //region Static
    //endregion

    //region Fields

    private Player player;

    private Text scoreText;

    private AnimatedSprite fly;

    private float lastX = 0;

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

    /**
     * Animate the enemy in the scene
     */
    private void animateFly(){

        // animating the fly
        fly = new AnimatedSprite(240, 200, res.enemyTextureRegion, vbom);
        fly.animate(125);
        //fly.registerEntityModifier(new LoopEntityModifier(new RotationModifier(2, 0, 360)));
        attachChild(fly);

    }

    /**
     * Attach the event for move our player into the scene
     */
    private void movePlayer(){

        setOnSceneTouchListener(new IOnSceneTouchListener() {
            @Override
            public boolean onSceneTouchEvent(Scene pScene, TouchEvent
                    pSceneTouchEvent) {
                if (pSceneTouchEvent.isActionDown()) {
                    player.clearEntityModifiers();
                    player.registerEntityModifier(new MoveModifier(1,
                            player.getX(), player.getY(), pSceneTouchEvent.getX(),
                            pSceneTouchEvent.getY()));
                    return true;
                }
                return false;
            }
        });

    }
    //endregion

    //region Methods

    @Override
    public void populate() {

        createBackground();
        createPlayer();
        createHUD();
        animateFly();
        movePlayer();
        registerTouchArea(player);
        engine.enableAccelerationSensor(activity, this);

        ICollisionCallback myCollisionCallback = new ICollisionCallback(){

            @Override
            public boolean onCollision(IShape pCheckShape, IShape pTargetShape) {
                fly.setColor(Color.RED);
                return false;
            }
        };

        CollisionHandler myCollisionHandler = new CollisionHandler(myCollisionCallback, fly, player);
        registerUpdateHandler(myCollisionHandler);

    }

    @Override
    public void onPause() {
        engine.disableAccelerationSensor(activity);
    }

    @Override
    public void onResume() {
        engine.enableAccelerationSensor(activity, this);
    }

    @Override
    public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {

    }

    @Override
    public void onAccelerationChanged(AccelerationData pAccelerationData) {

        if(Math.abs(pAccelerationData.getX() - lastX) > 0.5){
            if(pAccelerationData.getX() > 0){
                player.turnRigth();
            }else {
                player.turnLeft();
            }
            lastX = pAccelerationData.getX();
        }

        player.setX(player.getX() + pAccelerationData.getX());
    }

    //endregion

}
