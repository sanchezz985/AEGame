package io.pagelab.aegame;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Emmanuel on 20/09/2015.
 */
public class ResourceManager {

    //single instance is created only
    private static final ResourceManager INSTANCE = new ResourceManager();

    // common objects
    public MainGameActivity activity;
    public Engine engine;
    public Camera camera;
    public VertexBufferObjectManager vbom;

    // textures
    public ITiledTextureRegion playerTextureRegion;
    public ITiledTextureRegion enemyTextureRegion;
    public ITextureRegion plataformTextureRegion;
    public ITextureRegion cloud1TextureRegion;
    public ITextureRegion cloud2TextureRegion;

    private BuildableBitmapTextureAtlas gameTextureAtlas;

    //constructor is private to ensure nobody can call it from outside
    private ResourceManager() {}

    public static ResourceManager getInstance(){ return INSTANCE; }

    public void create(MainGameActivity activity, Engine engine, Camera camera, VertexBufferObjectManager vbom){
        this.activity   = activity;
        this.engine     = engine;
        this.camera     = camera;
        this.vbom       = vbom;
    }
}
