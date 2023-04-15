package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pong extends ApplicationAdapter {

	private PongInputProcessor inputProcessor;
	private Paddle leftPaddle, rightPaddle;
	private Ball ball;

	private List<Updatable> updateEntities;
	
	@Override
	public void create () {
		inputProcessor = new PongInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		int y = World.getHeight() / 2;
		int leftX = 25;
		int rightX = World.getWidth() - (Paddle.DEFAULT_WIDTH + 5);
		leftPaddle = new Paddle(leftX, y, inputProcessor);
		rightPaddle = new Paddle(rightX, y, inputProcessor);

		int ballX = World.getWidth() / 2;
		int ballY = World.getHeight() / 2;
		ball = new Ball(ballX, ballY, Arrays.asList(leftPaddle, rightPaddle));

		List<Updatable> updateEntities = new ArrayList<>();

		updateEntities.add(leftPaddle);
		updateEntities.add(rightPaddle);
		updateEntities.add(ball);

		this.updateEntities = updateEntities;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		for (Updatable u : updateEntities) {
			u.update();
		}
	}
	
	@Override
	public void dispose () {
	}

}
