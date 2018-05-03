package game;

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
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(Application.WIDTH / 2, Application.HEIGHT / 2, camera);
		this.viewport.setCamera(camera);
		this.viewport.apply();

		this.map = new Map(35, 35);
		this.tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
		this.entityManager = new EntityManager();



		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				
			 double temp = Math.random() * 100 + 80;
			 
			 System.out.println(temp);
				
				entityManager.getEntities().put(Entity.ID, new Tree(i*32 + (float)temp, j*32 + (float) temp, this));
			}
		}
		
		
		
		this.entityManager.getEntities().put(Entity.ID, new Creature(10, 10));

		player.setCurrentEntityID(Entity.ID - 1);

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

	//	entityManager.debugRender(shapeRenderer);
		shapeRenderer.end();

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
