package snakePack;

//import java.awt.Dimension;
import java.awt.Rectangle;
//import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class SnakeGame implements KeyListener {

	public JFrame jframe;
	public Board board;

	// public static SnakeGame snake;

	public Serpisor jucator;

	// public static int SUS = 8, JOS = 2, STANGA = 4, DREAPTA = 6;

	// public static int SCALE = 10;

	public ArrayList<Rectangle> bucateleSerpisor = new ArrayList<Rectangle>();

	// public int ticks = 0;
	// public int directie = JOS;

	public static int gameWidth = 40;
	public static int gameHeight = 30;
	public static int dimensiune = 15;

	// public Rectangle capusor;
	public Mancare mancare;

	public SnakeGame() {
		// dim = Toolkit.getDefaultToolkit().getScreenSize();

		jucator = new Serpisor();
		mancare = new Mancare(jucator);
		board = new Board(this);
		jframe = new JFrame();
		jframe.add(board);
		jframe.setTitle("Snake");
		jframe.setSize(gameWidth * dimensiune + 2, gameHeight * dimensiune + dimensiune + 4);
		jframe.setVisible(true);
		// jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 -
		// jframe.getHeight() / 2);

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void start() {
		board.state = "RUNNING";
	}

	public void update() {
		if (board.state == "RUNNING") {
			if (coliziuneMancare()) {
				jucator.cresteSerpisor();
				mancare.randomSpawn(jucator);
			} else if (coliziunePerete() || coliziuneCoada())
				board.state = "END";
			else if (castigator())
				board.state = "WINNER";
			else
				jucator.move();
		}
	}

	public boolean castigator() {
		if (jucator.getBucateleSerpisor().size() == (80 + 3)) // Ca sa fie n, mai trebuie adaugat inca 3: n + 3
			return true;
		return false;
	}

	public boolean coliziunePerete() {
		if (jucator.getX() < 0 || jucator.getX() >= SnakeGame.gameWidth * dimensiune || jucator.getY() < 0
				|| jucator.getY() >= SnakeGame.gameHeight * dimensiune) {
			return true;
		}
		return false;

	}

	public boolean coliziuneMancare() {
		if (jucator.getX() == mancare.getX() * dimensiune && jucator.getY() == mancare.getY() * dimensiune)
			return true;
		return false;
	}

	public boolean coliziuneCoada() {
		for (int i = 1; i < jucator.getBucateleSerpisor().size(); i++)
			if (jucator.getX() == jucator.getBucateleSerpisor().get(i).x
					&& jucator.getY() == jucator.getBucateleSerpisor().get(i).y)
				return true;
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (board.state == "RUNNING") {
			if (keyCode == KeyEvent.VK_UP && jucator.getMove() != "JOS") {
				jucator.sus();
			} else if (keyCode == KeyEvent.VK_DOWN && jucator.getMove() != "SUS") {
				jucator.jos();
			} else if (keyCode == KeyEvent.VK_LEFT && jucator.getMove() != "STANGA") {
				jucator.stanga();
			} else if (keyCode == KeyEvent.VK_RIGHT && jucator.getMove() != "DREAPTA") {
				jucator.dreapta();
			}
		} else {
			this.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public JFrame getJframe() {
		return jframe;
	}

	public void setJframe(JFrame jframe) {
		this.jframe = jframe;
	}

	public Serpisor getJucator() {
		return jucator;
	}

	public void setJucator(Serpisor jucator) {
		this.jucator = jucator;
	}

	public Mancare getMancare() {
		return mancare;
	}

	public void setMancare(Mancare mancare) {
		this.mancare = mancare;
	}

}
