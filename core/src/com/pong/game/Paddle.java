package com.pong.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle implements Drawable, Updatable{
    public static final int DEFAULT_WIDTH = 30, DEFAULT_HEIGHT = 100, DEFAULT_SPEED = 10;
    public static final Color DEFAULT_COLOR = Color.GREEN;

    public int x, y, width, height, speed;
    private Color color;

    private PaddleInput inputProcessor;

    public Paddle(int x, int y, PaddleInput inputProcessor) {
        this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_SPEED, DEFAULT_COLOR, inputProcessor);
    }

    public Paddle(int x, int y, int width, int height, int speed, Color color, PaddleInput inputProcessor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.color = color;
        this.inputProcessor = inputProcessor;
    }

    public void up() {
        if (y - speed >= 0) {
            y -= speed;
        }
    }

    public void down() {
        if (y + speed <= World.getHeight()) {
            y += speed;
        }
    }

    public void draw() {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    @Override
    public void update() {
        if (inputProcessor.isUpPressed()) {
            up();
        }

        if (inputProcessor.isDownPressed()) {
            down();
        }
    }
}
