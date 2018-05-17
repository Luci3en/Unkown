package game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.entity.Entity;
import game.entity.Tree;
import game.utility.CameraStyles;
import game.utility.EntityManager;

public class World {

	private OrthographicCamera camera;
	private Viewport viewport;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private ShapeRenderer shapeRenderer;

	private EntityManager entityManager;
	private Map map;

	public World() {

		// World braucht eine dispose() um die Sprites der ganzen Entitys frei zu geben

		this.shapeRenderer = new ShapeRenderer();
		this.shapeRenderer.setAutoShapeType(true);
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(CameraStyles.WORLD_VIEWPORT_WIDTH, CameraStyles.WORLD_VIEWPORT_HEIGHT,
				camera);
		this.viewport.setCamera(camera);
		this.viewport.apply();

		this.map = new Map(35, 35);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
		this.entityManager = new EntityManager(map);

		entityManager.getEntities().put(Entity.ID, new Tree(50, 40, this));
		entityManager.getEntities().put(Entity.ID, new Tree(80, 20, this));
		entityManager.getEntities().put(Entity.ID, new Tree(100, 60, this));

		entityManager.getEntities().put(Entity.ID, new Tree(90, 180, this));
		entityManager.getEntities().put(Entity.ID, new Tree(61, 200, this));
		entityManager.getEntities().put(Entity.ID, new Tree(100, 190, this));

		entityManager.getEntities().put(Entity.ID, new Tree(200, 20, this));
		entityManager.getEntities().put(Entity.ID, new Tree(300, 80, this));
		entityManager.getEntities().put(Entity.ID, new Tree(400, 20, this));

	}

	public void render(SpriteBatch spriteBatch) {
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();

		entityManager.render(spriteBatch, map);

		spriteBatch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin();

		entityManager.debugRender(shapeRenderer);
		shapeRenderer.end();

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

}
