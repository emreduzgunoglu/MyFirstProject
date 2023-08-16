package main;

import entity.Entity;
import entity.Entity.DirectionEnum;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gp = gp;
	}
	
	public boolean checkTile(Entity entity, DirectionEnum checkDirection) {
		boolean collision = false;
		int LeftWorldX = entity.x + entity.invisibleRect.x;
		int RightWorldX = entity.x + entity.invisibleRect.x + entity.invisibleRect.width;
		int TopWorldY = entity.y + entity.invisibleRect.y;
		int BotWorldY = entity.y + entity.invisibleRect.y + entity.invisibleRect.height;
		
		int LeftCol = LeftWorldX/32;
		int RightCol = RightWorldX/32;
		int TopRow = TopWorldY/32;
		int BotRow = BotWorldY/32;
		
		int tileNum1, tileNum2;
		
		switch(checkDirection) {
		case UP:
			TopRow = (TopWorldY - 4)/32;
			tileNum1 = gp.tileM.mapTileNum[LeftCol][TopRow];
			tileNum2 = gp.tileM.mapTileNum[RightCol][TopRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ) {
				collision = true;
			}
			
			break;
		case DOWN:
			BotRow = (BotWorldY + 4)/32;
			tileNum1 = gp.tileM.mapTileNum[LeftCol][BotRow];
			tileNum2 = gp.tileM.mapTileNum[RightCol][BotRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ) {
				collision = true;
			}
			
			break;
		case LEFT:
			LeftCol = (LeftWorldX - 4)/32;
			tileNum1 = gp.tileM.mapTileNum[LeftCol][TopRow];
			tileNum2 = gp.tileM.mapTileNum[LeftCol][BotRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ) {
				collision = true;
			}
			break;
		case RIGHT:
			RightCol = (RightWorldX + 4)/32;
			tileNum1 = gp.tileM.mapTileNum[RightCol][TopRow];
			tileNum2 = gp.tileM.mapTileNum[RightCol][BotRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ) {
				collision = true;
			}
			break;
		default:
			break;
		}
		return collision;
	}
}
