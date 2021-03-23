package StateGame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainGarden extends BasicGameState{
	
	public static final int ID = 1;
	//Images
	private static Image Mango;
	private static Image Blueberry;
	// Food Quantity
	public static int MangoX = 0;
	public static int BlueberryX = 0;

	private static Image Garden;
	
	private StateBasedGame MainGarden;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		this.MainGarden=game;
		Garden = new Image ("data/Background/Garden.png");
		Mango = new Image ("data/Items/Fruit/Mango.png");
		Blueberry = new Image ("data/Items/Fruit/Blueberry.png");
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		//Draws Garden 
		//g.clear();
		Garden.draw(0, 0, 1000, 600);
		if(rando==0){
			Mango.draw(400,300);
			g.drawString("Mango +1", 450, 315);
		}else if(rando==1){
			Blueberry.draw(600,300);
			g.drawString("Blueberry +1", 650, 315);
		}
		g.drawString("Current Mangos: "+MangoX, 0, 500);		
		g.drawString("Current Blueberries: "+BlueberryX, 0, 550);		
	}
	private static Random ran = new Random();
	private static int rando;
	private static int time;
	private static int OGtime;
	private static int timer;
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		int SetTime = time+1;
		OGtime+=delta;
		time = OGtime/1000;
		timer+=delta;
		if (timer/1000  == 1){
			rando = ran.nextInt(2);
			timer=0;
			if (rando==0){
				MangoX++;
			}else if(rando==1){
				BlueberryX++;
			}
		}
	}
	
	public int getID() {
		return ID;
	}
	public void keyReleased(int key, char c) {
		if(key==Input.KEY_TAB){
	        MainGarden.enterState(MainChaoWorld.ID);
		}
	}
}
