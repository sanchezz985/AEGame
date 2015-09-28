/**
 * Created by sanchezz985 on 23/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.entity;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Player extends TiledSprite{

    //region Static
    //endregion

    //region Fields

    private boolean dead = false;

    //endregion

    //region Private Methods
    //endregion

    //region Methods

    /**
     * Constructor
     */
    public Player(float px, float py, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexObjectBufferManager){
        super(px, py, pTiledTextureRegion, pVertexObjectBufferManager);
        setCurrentTileIndex(2);
    }

    public boolean isDead(){
        return dead;
    }

    public void setDead(boolean dead){
        this.dead = dead;
    }

    public void turnLeft(){
        setFlippedHorizontal(true);
    }

    public void turnRigth(){
        setFlippedHorizontal(false);
    }

    public void fly(){
        setCurrentTileIndex(2);
    }

    public void fall(){
        setCurrentTileIndex(1);
    }

    public void die(){
        setDead(true);
        setCurrentTileIndex(0);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY){

        if(pSceneTouchEvent.isActionDown()){
            clearEntityModifiers();
            return true;
        }else if(pSceneTouchEvent.isActionMove()){
            setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
            return true;
        }
        return false;
    }

    //endregion

}
