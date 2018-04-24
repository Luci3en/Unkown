package game.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class World {

	private EntityManager entityManager;
	private Map map;

	public World() {

		this.map = new Map(50, 50);

		entityManager = new EntityManager();

		entityManager.getEntities().put(Entity.ID, new Tree(100, 100));
		entityManager.getEntities().put(Entity.ID, new Tree(200, 120));
		entityManager.getEntities().put(Entity.ID, new Tree(500, 100));

		
		
		for (int id : entityManager.getEntities().keySet()) {
			System.out.println(id);
		}
		
		entityManager.spawnEntity(map);

	}

	public void render(SpriteBatch spriteBatch, OrthographicCamera camera) {
		update(camera);

		entityManager.render(spriteBatch, this);
		entityManager.debugRender(camera);
	}

	public void update(OrthographicCamera camera) {
		focusCameraOnEntity(camera, entityManager.getPlayer());
	}

	public void focusCameraOnEntity(OrthographicCamera camera, Entity entity) {

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

}
