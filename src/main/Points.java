package main;

import entity.Entity;
import tile.TileManager;

public class Points {

	GamePanel gp;
	TileManager tileM;

	public static int totalPoints = 0;

	public Points(GamePanel gp) {

		this.gp = gp;

	}

	public void checkPoints(Entity entity) {

		int entityCenterX = entity.x + (gp.tileSize / 2);
		int entityCenterY = entity.y + (gp.tileSize / 2);

		int entityCol = entityCenterX / gp.tileSize;
		int entityRow = entityCenterY / gp.tileSize;

		int oldValue = gp.tileM.mapTileNum[entityCol][entityRow];
		if (oldValue > 0) {
			gp.tileM.mapTileNum[entityCol][entityRow] = 0;
			if (oldValue == 16)
				totalPoints = totalPoints + 5;
			else if (oldValue == 17)
				totalPoints = totalPoints + 50;
			else if (oldValue == 18)
				totalPoints = totalPoints + 100;

			if (totalPoints == 1430) {

				gp.gameOver = true;
			}
		}

	}

}
