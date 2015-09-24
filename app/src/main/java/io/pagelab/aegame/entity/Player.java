/**
 * Created by sanchezz985 on 23/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.entity;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Player extends TiledSprite{

    //region Static
    //endregion

    //region Fields

    private boolean dead = false;

    //endregion

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
        setFlippedVertical(true);
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

    //region Private Methods
    //endregion

    //region Methods
    //endregion

}
