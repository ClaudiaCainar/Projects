package snakePack;
import java.awt.Rectangle;

public class Mancare {

	public int x;
	public int y;
	// public Rectangle mancare;

	public Mancare(Serpisor jucator) {
		this.randomSpawn(jucator);
	}

	public void randomSpawn(Serpisor jucator) {
		boolean onSnake = true;

		while (onSnake) {
			onSnake = false;

			x = (int) (Math.random() * SnakeGame.gameWidth - 1);
			y = (int) (Math.random() * SnakeGame.gameHeight - 1);
			// mancare.x = ThreadLocalRandom.current().nextInt(2, SnakeGame.gameWidth);
			// mancare.y = ThreadLocalRandom.current().nextInt(2, SnakeGame.gameHeight);

			for (Rectangle rectangle : jucator.getBucateleSerpisor()) {
				if (rectangle.x == x && rectangle.y == y)
					onSnake = true;
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
