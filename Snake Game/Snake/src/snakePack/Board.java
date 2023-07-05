package snakePack;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Timer timer = new Timer(100, this);
	// public static Color blue = new Color(11333119);
	// public static Color white = new Color(16777215);
	public String state;

	private Serpisor snake;
	private Mancare mancare;
	private SnakeGame snakeGame;

	public Board(SnakeGame g) {
		timer.start();
		state = "START";

		snakeGame = g;
		snake = g.getJucator();
		mancare = g.getMancare();

		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}

	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.pink);
		g2d.fillRect(0, 0, SnakeGame.gameWidth * SnakeGame.dimensiune + 5,
				SnakeGame.gameHeight * SnakeGame.dimensiune + 5);

		if (state == "START") {
			g2d.setColor(Color.black);
			g2d.drawString("PRESS ANY KEY", SnakeGame.gameWidth / 2 * SnakeGame.dimensiune - 50,
					SnakeGame.gameHeight / 2 * SnakeGame.dimensiune - 20);
		} else if (state == "RUNNING") {
			g2d.setColor(Color.blue);
			g2d.fillRect(mancare.getX() * SnakeGame.dimensiune, mancare.getY() * SnakeGame.dimensiune,
					SnakeGame.dimensiune, SnakeGame.dimensiune);

			g2d.setColor(Color.cyan);
			for (Rectangle r : snake.getBucateleSerpisor()) {
				g2d.fill(r);
			}
		} else if (state == "WINNER") {
			g2d.setColor(Color.red);
			g2d.drawString("Congratulations! You won! Score:" + (snake.getBucateleSerpisor().size() - 3),
					SnakeGame.gameWidth / 2 * SnakeGame.dimensiune - 100,
					SnakeGame.gameHeight / 2 * SnakeGame.dimensiune - 30);
		} else {
			g2d.setColor(Color.black);
			g2d.drawString("Game over! Your Score: " + (snake.getBucateleSerpisor().size() - 3),
					SnakeGame.gameWidth / 2 * SnakeGame.dimensiune - 70,
					SnakeGame.gameHeight / 2 * SnakeGame.dimensiune - 20);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		repaint();
		snakeGame.update();

	}
}
