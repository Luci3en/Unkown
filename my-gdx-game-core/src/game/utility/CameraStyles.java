package game.utility;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

import game.Application;
import game.Map;
import game.entity.Entity;

public class CameraStyles {
	
	
	public final static int WORLD_VIEWPORT_WIDTH = Application.WIDTH / 2;
	public final static int WORLD_VIEWPORT_HEIGHT = Application.HEIGHT / 2;

	public static void lockOnEntity(Camera camera, Entity entity) {

		camera.position.x = entity.getX();

		camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2,
				Map.MAP_PIXEL_WIDTH - (camera.viewportWidth / 2));

		camera.position.y = entity.getY();

		camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2,
				Map.MAP_PIXEL_HEIGHT - (camera.viewportHeight / 2));

		camera.update();

	}

	public static void zoomCamera(OrthographicCamera camera) {

		camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100 / camera.viewportWidth);

		return;

	}

}
