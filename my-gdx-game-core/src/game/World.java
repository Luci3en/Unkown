package game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.entity.Entity;
import game.entity.Tree;
import game.utility.CameraStyles;
import game.utility.EntityManager;

public class World implements Disposable {

	private OrthographicCamera camera;
	private Viewport viewport;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;

	private EntityManager entityManager;
	private Map map;

	public World() {

		this.spriteBatch = new SpriteBatch();
		this.shapeRenderer = new ShapeRenderer();
		this.shapeRenderer.setAutoShapeType(true);
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(CameraStyles.WORLD_VIEWPORT_WIDTH, CameraStyles.WORLD_VIEWPORT_HEIGHT,
				camera);
		this.viewport.setCamera(camera);
		this.viewport.apply();

		this.map = new Map(35, 35);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
		this.entityManager = new EntityManager();

		spawnEntity(new Tree(50, 40, this));
		spawnEntity(new Tree(100, 60, this));
		spawnEntity(new Tree(100, 180, this));
		spawnEntity(new Tree(80, 20, this));
		spawnEntity(new Tree(250, 50, this));
		spawnEntity(new Tree(366, 80, this));
		spawnEntity(new Tree(188, 30, this));
		spawnEntity(new Tree(200, 20, this));
		spawnEntity(new Tree(300, 80, this));
		spawnEntity(new Tree(400, 30, this));

	}

	public void render() {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();

		entityManager.render(spriteBatch, this);

		spriteBatch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin();

		entityManager.debugRender(shapeRenderer);
		shapeRenderer.end();

	}

	public void spawnEntity(Entity entity) {

		entityManager.getEntities().put(entity.getId(), entity);

		for (Tile tile : entity.getTouchedTiles()) {
			getMap().getTile(tile.getX(), tile.getY()).getEntityIDs().add(entity.getId());
		}
	}

	public void destroyEntity(Entity entity) {
		if (entityManager.getEntities().containsKey(entity.getId())) {

			// Dispose löst ein render glitch aus --> Prüfen!
			// entity.dispose();
			entityManager.getEntities().remove(entity.getId());

			for (Tile tile : entity.getTouchedTiles()) {

				// evtl. eine Hashmap für die EntitIDs in Tile benutzen dann kann man hier die
				// schleife sparen

				for (int i = 0; i < getMap().getTile(tile.getX(), tile.getY()).getEntityIDs().size(); i++) {

					int temp = getMap().getTile(tile.getX(), tile.getY()).getEntityIDs().get(i);

					if (temp == entity.getId()) {
						getMap().getTile(tile.getX(), tile.getY()).getEntityIDs().remove(i);
					}
				}

			}
		}

	}

	@Override
	public void dispose() {
		entityManager.dispose();
		tiledMapRenderer.dispose();
		shapeRenderer.dispose();

	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public OrthogonalTiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	public void setTiledMapRenderer(OrthogonalTiledMapRenderer tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public void setShapeRenderer(ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public void setSpriteBatch(SpriteBatch spriteBatch) {
		this.spriteBatch = spriteBatch;
	}

}
