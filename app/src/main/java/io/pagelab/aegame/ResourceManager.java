/**
 * Created by sanchezz985 on 20/09/2015.
 * email: sanchezz985@gmail.com
 */

package io.pagelab.aegame;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.bitmap.BitmapTextureFormat;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ResourceManager {

    //region Static

    /**
     * Gets the instance for this class
     * @return
     */
    public static ResourceManager getInstance(){ return INSTANCE; }

    //endregion

    //region Fields

    /**
     * single instance is created only
     */
    private static final ResourceManager INSTANCE = new ResourceManager();

    /**
     * Variable for texture atlas
     */
    private BuildableBitmapTextureAtlas gameTextureAtlas;

    /**
     * common objects
     */
    public MainGameActivity activity;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;

    /**
     * Textures
     */
    public ITiledTextureRegion playerTextureRegion;
    public ITiledTextureRegion enemyTextureRegion;
    public ITextureRegion platformTextureRegion;
    public ITextureRegion cloud1TextureRegion;
    public ITextureRegion cloud2TextureRegion;

    //endregion

    /**
     * Constructor is private to ensure nobody can call it from outside
     */
    private ResourceManager() {}

    //region Private Methods
    //endregion

    //region Methods

    /**
     * Method to create the Resource Manager.
     * This method can be like a initializer for the object's attributes
     *
     * @param activity
     * @param engine
     * @param camera
     * @param vbom
     */
    public void create(MainGameActivity activity, Engine engine, Camera camera, VertexBufferObjectManager vbom){
        this.activity   = activity;
        this.engine     = engine;
        this.camera     = camera;
        this.vbom       = vbom;
    }

    /**
     * Init our texture objects and try load the graphics.
     */
    public void loadGameGraphics(){

        // set the directory for our assets
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        // create the texture atlas for our assets
        gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 1024, 512, BitmapTextureFormat.RGBA_8888, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        // init our player texture region
        playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity.getAssets(), "player.png", 3, 1);

        // init our enemy texture region
        enemyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity.getAssets(), "enemy.png", 1, 2);

        // init our platform region
        platformTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "platform.png");

        // init our clouds regions
        cloud1TextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "cloud1");
        cloud2TextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity.getAssets(), "cloud2");

        // try build the texture atlas for the game
        try{

            gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(2, 0, 2));
            gameTextureAtlas.load();

        }catch(final ITextureAtlasBuilder.TextureAtlasBuilderException e){
            throw new RuntimeException("Error while loading game textures", e);
        }

    }

    //endregion
}
