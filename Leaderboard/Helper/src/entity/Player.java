/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.FrameManager;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTools;

/**
 *
 * @author farid
 */
public class Player extends Entity {

    KeyHandler keyH;
    FrameManager fManager;
    
    public final int screenX;
    public final int screenY; //buat ngeset kamera bergerak si player
    
    public Player(GamePanel gp, KeyHandler keyH, FrameManager fManager){
    	super(gp);
        this.keyH = keyH;
        this.fManager = fManager;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=32;
        solidArea.height=32;
        
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){ 
    	//buat ngeset spawn player
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*23;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage() {
    	
    	up1 = setup("/player/boy_up_1");
    	up2 = setup("/player/boy_up_2");
    	down1 = setup("/player/boy_down_1");
    	down2 = setup("/player/boy_down_2");
    	left1 = setup("/player/boy_left_1");
    	left2 = setup("/player/boy_left_2");
    	right1 = setup("/player/boy_right_1");
    	right2 = setup("/player/boy_right_2");
    }
    
    
    public void update(){
    	if(keyH.pencetAtas == true || keyH.pencetBawah == true || 
    			keyH.pencetKanan==true || keyH.pencetKiri==true) {
    		if(keyH.pencetAtas==true){
            	direction = "up";
                
            }
            else if (keyH.pencetBawah == true){
            	direction = "down";
                
            }
            else if (keyH.pencetKanan==true){
            	direction = "right";
                
            }
            else if (keyH.pencetKiri==true){
            	direction = "left";
                
            }
    		//cek tabrakan
    		collisionOn = false;
    		gp.cChecker.checkTile(this);
    		//cek npc coll
    		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
    		interactNPC(npcIndex);
    		//apabila ga tabrakan, player bisa gerak
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
    		//cek event
    		gp.eHandler.checkEvent();
    		
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
        
    }
    
    public void moveBoard() {
    	if(gp.playState == gp.beachState) {
    		if(keyH.pencetEnter == true) {
    			direction = "left";
    			worldX -= speed;
    		}
    	}
    }
    
    public void interactNPC(int i) {
    	if(i != 999 ) {
    		if(gp.keyH.pencetEnter == true) {
    		gp.npc[i].speak();
    		}
    	}
    	gp.keyH.pencetEnter = false;
    }
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
