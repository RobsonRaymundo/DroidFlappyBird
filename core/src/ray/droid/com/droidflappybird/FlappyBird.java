package ray.droid.com.droidflappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture passaro;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		passaro = new Texture("passaro1.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(passaro, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		passaro.dispose();
	}
}
