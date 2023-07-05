package snakePack;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Serpisor {
	public ArrayList<Rectangle> bucateleSerpisor;
	// public int startPointX = ThreadLocalRandom.current().nextInt(3,
	// SnakeGame.gameWidth);
	// public int startPointY = ThreadLocalRandom.current().nextInt(3,
	// SnakeGame.gameHeight);
	public static Rectangle temp;
	public int wid = SnakeGame.gameWidth;
	public int hei = SnakeGame.gameHeight;
	public int dim = SnakeGame.dimensiune;

	public String move; // poate sa fie nimic (sa nu fie apasat niciun buton, sau sa mearga in sus, jos,
						// stanga sau dreapta)

	public Serpisor() {

		bucateleSerpisor = new ArrayList<>();

		temp = new Rectangle(SnakeGame.dimensiune, SnakeGame.dimensiune);
		temp.setLocation(SnakeGame.gameWidth / 2 * SnakeGame.dimensiune,
				SnakeGame.gameHeight / 2 * SnakeGame.dimensiune);
		bucateleSerpisor.add(temp);

		temp = new Rectangle(dim, dim);
		temp.setLocation((wid / 2 - 1) * dim, (hei / 2) * dim);
		bucateleSerpisor.add(temp);

		temp = new Rectangle(dim, dim);
		temp.setLocation((wid / 2 - 2) * dim, (hei / 2) * dim);
		bucateleSerpisor.add(temp);

		move = "NIMIC";
	}

	public void move() {
		if (move != "NIMIC") {
			Rectangle temp = new Rectangle(SnakeGame.dimensiune, SnakeGame.dimensiune);

			Rectangle first = bucateleSerpisor.get(0);

			if (move == "SUS") {
				temp.setLocation(first.x, first.y - SnakeGame.dimensiune);
			} else if (move == "JOS") {
				temp.setLocation(first.x, first.y + SnakeGame.dimensiune);
			} else if (move == "STANGA") {
				temp.setLocation(first.x - SnakeGame.dimensiune, first.y);
			} else if (move == "DREAPTA") {
				temp.setLocation(first.x + SnakeGame.dimensiune, first.y);
			}

			bucateleSerpisor.add(0, temp);
			bucateleSerpisor.remove(bucateleSerpisor.size() - 1);
		}
	}

	public void cresteSerpisor() {
		Rectangle temp = new Rectangle(SnakeGame.dimensiune, SnakeGame.dimensiune);

		Rectangle first = bucateleSerpisor.get(0);

		if (move == "SUS") {
			temp.setLocation(first.x, first.y - SnakeGame.dimensiune);
		} else if (move == "JOS") {
			temp.setLocation(first.x, first.y + SnakeGame.dimensiune);
		} else if (move == "STANGA") {
			temp.setLocation(first.x - SnakeGame.dimensiune, first.y);
		} else if (move == "DREAPTA") {
			temp.setLocation(first.x + SnakeGame.dimensiune, first.y);
		}

		bucateleSerpisor.add(0, temp);
	}

	public ArrayList<Rectangle> getBucateleSerpisor() {
		return bucateleSerpisor;
	}

	public void setBucateleSerpisor(ArrayList<Rectangle> bucateleSerpisor) {
		this.bucateleSerpisor = bucateleSerpisor;
	}

	public int getX() {
		return bucateleSerpisor.get(0).x;
	}

	public int getY() {
		return bucateleSerpisor.get(0).y;
	}

	public String getMove() {
		return move;
	}

	public void sus() {
		move = "SUS";
	}

	public void jos() {
		move = "JOS";
	}

	public void stanga() {
		move = "STANGA";
	}

	public void dreapta() {
		move = "DREAPTA";
	}

}
