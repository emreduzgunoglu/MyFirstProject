package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Monster;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	public final int tileSize = 32; // 32x32 tile
	public final int maxScreenCol = 19;
	public final int maxScreenRow = 23;
	public final int screenWidth = tileSize * maxScreenCol; // 608 pixels
	public final int screenHeight = tileSize * (maxScreenRow + 2); // 736 pixels

	public boolean gameOver = false;
	int FPS = 60;
	public int counter = 0;
	public int imageChanger = 1;

	public TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public TextInformations textInfos = new TextInformations(this);
	public Points points = new Points(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	public Monster monster = new Monster(this, keyH, 1);
	public Monster monster2 = new Monster(this, keyH, 2);
	public Monster monster3 = new Monster(this, keyH, 3);

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		setMonsters();
	}

	public void setMonsters() {
		monster.setDefaultValues();
		monster.x = monster.x - 32 * 4;
		monster2.setDefaultValues();
		monster2.x = monster.x + 32 * 8;
		monster3.setDefaultValues();
		monster3.y = 32;

	}

	public void restartGame() {

		gameOver = false;
		textInfos.lives = 3;
		Points.totalPoints = 0;
		textInfos.time = 300;
		tileM.loadMap();
		setMonsters();
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		long timer = 0;
		double frameCounter = 0;
		long passedTime = System.nanoTime();
		long currentTime;
		

		while (gameThread != null) {

			currentTime = System.nanoTime();

			frameCounter += (currentTime - passedTime) / drawInterval;
			timer += (currentTime - passedTime);
			passedTime = currentTime;

			if (frameCounter >= 1) {
				update();
				repaint();
				frameCounter--;

				counter++;
				if (counter > 12) {
					if (imageChanger == 1) {
						imageChanger = 2;
					} else if (imageChanger == 2) {
						imageChanger = 1;
					}
					counter = 0;
				}

			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (timer >= 1000000000) {
				timer = 0;
				textInfos.time--;

			}

			if (gameOver && keyH.f2Pressed) {
				restartGame();
			}

		}
	}

	public void update() {

		player.update();
		monster.update();
		monster2.update();
		monster3.update();

		if (player != null) {
			textInfos.liveCounter();
			points.checkPoints(player);

		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		player.draw(g2);
		monster.draw(g2);
		monster2.draw(g2);
		monster3.draw(g2);
		textInfos.drawText(g2);

		g2.dispose();

	}

}
