package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Inventory {

	private List<String> list;

	public Inventory() {

		Skin skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));
		this.list = new List<String>(skin);

		list.setItems("Item1", "Item2");

	}
	
	
	

}
