/**
 * Created by sanchezz985 on 23/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame.factory;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import io.pagelab.aegame.ResourceManager;
import io.pagelab.aegame.entity.Player;

public class PlayerFactory {

    //region Static

    /**
     * Get the instance for PlayerFactory
     * @return
     */
    public static PlayerFactory getInstance(){
        return INSTANCE;
    }

    //endregion

    //region Fields
    private static PlayerFactory INSTANCE = new PlayerFactory();
    private VertexBufferObjectManager vbom;
    //endregion

    /**
     * Constructor as private for not instance this class
     */
    private PlayerFactory(){}

    //region Private Methods
    //endregion

    //region Methods

    /**
     * Init the fields for this class
     * @param vbom
     */
    public void create(VertexBufferObjectManager vbom){
        this.vbom = vbom;
    }

    /**
     * Create the instance fot our player
     * @param x
     * @param y
     * @return
     */
    public Player createPlayer(float x, float y){

        Player player = new Player(x, y, ResourceManager.getInstance().playerTextureRegion, vbom);
        player.setZIndex(2);
        return player;
    }

    //endregion

}
