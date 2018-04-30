package game.utility;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;

import game.Map;
import game.entity.Entity;

public class CameraStyles {
	
	public static void lockOnEntity(Camera camera, Entity entity) {
		
		camera.position.x = entity.getX();

		camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2,
				Map.MAP_PIXEL_WIDTH - (camera.viewportWidth / 2));

		camera.position.y = entity.getY();

		camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2,
				Map.MAP_PIXEL_HEIGHT - (camera.viewportHeight / 2));

		camera.update();
		
		
	}
	
	
}
