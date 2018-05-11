package game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.entity.Creature;
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

	public World(Player player) {

		this.shapeRenderer = new ShapeRenderer();
		this.shapeRenderer.setAutoShapeType(true);
		this.shapeRenderer.setColor(Color.RED);
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(Application.WIDTH / 2, Application.HEIGHT / 2, camera);
		this.viewport.setCamera(camera);
		this.viewport.apply();

		this.map = new Map(35, 35);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
		this.entityManager = new EntityManager();

		entityManager.getEntities().put(Entity.ID, new Tree(50, 20, this));
		entityManager.getEntities().put(Entity.ID, new Tree(80, 20, this));
		entityManager.getEntities().put(Entity.ID, new Tree(100, 20, this));

		this.entityManager.getEntities().put(Entity.ID, new Creature(10, 10));
		player.setCurrentEntityID(Entity.ID - 1);
		player.setEntity(entityManager.getEntities().get(player.getCurrentEntityID()));
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

	public void update(Player player) {
		CameraStyles.lockOnEntity(camera, entityManager.getEntities().get(player.getCurrentEntityID()));
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
