/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTools;

/**
 *
 * @author farid
 */
public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int kunciAksi =0;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public String dialogues[][]= new String[30][30];
    public int dialogueSet =0;
    public int dialogueIndex = 0;
    
    GamePanel gp;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void draw(Graphics2D g2) {
    	BufferedImage image = null;
    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
			switch(direction) {
	        case "up":
	        	if(spriteNum == 1) {
	        		image = up1;	
	        	}
	        	if(spriteNum == 2) {
	        		image = up2;	
	        	}
	        	break;
	        case "down":
	        	if(spriteNum == 1) {
	        		image = down1;	
	        	}
	        	if(spriteNum == 2) {
	        		image = down2;	
	        	}
	        	break;
	        case "left":
	        	if(spriteNum == 1) {
	        		image = left1;	
	        	}
	        	if(spriteNum == 2) {
	        		image = left2;	
	        	}
	        	break;
	        case "right":
	        	if(spriteNum == 1) {
	        		image = right1;	
	        	}
	        	if(spriteNum == 2) {
	        		image = right2;	
	        	}
	        	break;
	        }
		   g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
    }
    
    public void setAksi() {
    	
    }
    
    public void speak() {
    	
    }
    
    public void facePlayer() {
    	switch(gp.player.direction) {
		case"up":
			direction = "down";
			break;
		case"down":
			direction = "up";
			break;
		case"left":
			direction= "right";
			break;
		case"right":
			direction="left";
			break;
		}
    }
    
    public void startDialog(Entity entity, int setNum) {
    	gp.gameState = gp.dialogueState;
    	gp.ui.npc = entity;
    	dialogueSet = setNum;	
    	
    }
    
    public void update() {
    	setAksi();
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	gp.cChecker.checkPlayer(this);
    	if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
		}
    	spriteCounter++;
        if(spriteCounter > 12) {
        	if(spriteNum == 1) {
        		spriteNum = 2;
        	}
        	else if (spriteNum == 2) {
        		spriteNum =1 ;
        	}
        	spriteCounter = 0;
        }
    }
    
    public BufferedImage setup(String imagePath) {
    	UtilityTools uTool = new UtilityTools();
    	BufferedImage image = null;
    	
    	try {
    		image = ImageIO.read(getClass().getResourceAsStream(imagePath +".png"));
    		image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return image;
    }
}
