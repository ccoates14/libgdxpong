package com.pong.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;
import java.util.Random;

public class Ball implements Drawable, Updatable{
    public static final int DEFAULT_RADIUS = 30, DEFAULT_SPEED = 10;
    public static final Color DEFAULT_COLOR = Color.GREEN;

    private int x, y, radius, speedX, speedY;
    private Color color;

    private List<Paddle> paddles;

    public Ball(int x, int y, List<Paddle> paddles) {
        this(x, y, DEFAULT_RADIUS, (new Random().nextInt(DEFAULT_SPEED) + 1) * 
                        (new Random().nextBoolean() ? -1 : 1),
                (new Random().nextInt(DEFAULT_SPEED) + 1) * 
                        (new Random().nextBoolean() ? -1 : 1)
                , DEFAULT_COLOR, paddles);
    }

    public Ball(int x, int y, int radius, int speedX, int speedY, Color color, List<Paddle> paddles) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speedX = speedX;
        this.color = color;
        this.paddles = paddles;
    }

    public void draw() {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
    }

    public void update() {
        boolean paddleCollision = false;

        //iterate over paddles and see if and how we are colliding
        for (Paddle p : paddles) {
            if (isPaddleCollided(x, y, p)) {
                int nextX = x + (speedX * -1);
                
                speedX = isPaddleCollided(nextX, y, p) ? speedX * -1 : speedX;

                int nextY = y + (speedY * -1);

                speedY = isPaddleCollided(x, nextY, p) ? speedY * -1 : speedY;

                paddleCollision = true;

                //this is gross but for pong we always know there are two paddles
                break;
            }
        }

        if (!paddleCollision) {
            speedX *= isVerticalBoundaryCollision(x) ? -1 : 1;
            speedY *= isHorizontalBoundaryCollision(y) ? -1 : 1;
        }

        x += speedX;
        y += speedY;
    }

    private boolean isPaddleCollided(int nextX, int nextY, Paddle p) {
        boolean collided = false;
        if (nextX >= p.x && nextX <= p.x + p.width) {
            if (nextY >= p.y && nextY <= p.y + p.height) {
                collided = true;
            }
        }
        return collided;
    }

    private boolean isVerticalBoundaryCollision(int x) {
        return x <= 0 || x >= World.getWidth();
    }

    private boolean isHorizontalBoundaryCollision(int y) {
        return y <= 0 || y >= World.getHeight();
    }

}
