package game;

import java.util.Map.Entry;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.entity.Creature;
import game.entity.Entity;
import game.entity.EntityManager;
import game.entity.Tree;

public class World {

	private Player player;

	private OrthographicCamera camera;
	private Viewport viewport;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;

	private EntityManager entityManager;
	private Map map;

	public World(float widht, float height) {

		this.shapeRenderer = new ShapeRenderer();
		this.shapeRenderer.setAutoShapeType(true);
		this.spriteBatch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(widht, height, camera);
		this.viewport.setCamera(camera);
		this.viewport.apply();

		this.player = new Player();

		this.map = new Map(50, 50);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
		this.entityManager = new EntityManager();

		this.entityManager.getEntities().put(Entity.ID, new Tree(100, 70, this));
		this.entityManager.getEntities().put(Entity.ID, new Tree(200, 120, this));
		this.entityManager.getEntities().put(Entity.ID, new Tree(500, 100, this));
		this.entityManager.getEntities().put(Entity.ID, new Tree(500, 95, this));

		this.entityManager.getEntities().put(Entity.ID, new Creature(10, 10));

		this.player.setCurrentEntityID(Entity.ID - 1);

	}

	public void render() {

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

	public void update() {
		focusCameraOnEntity(entityManager.getEntities().get(player.getCurrentEntityID()));
		player.update(entityManager);
	}

	public void focusCameraOnEntity(Entity entity) {

		camera.position.x = entity.getX();

		camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2,
				Map.MAP_PIXEL_WIDTH - (camera.viewportWidth / 2));

		camera.position.y = entity.getY();

		camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2,
				Map.MAP_PIXEL_HEIGHT - (camera.viewportHeight / 2));

		camera.update();

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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
