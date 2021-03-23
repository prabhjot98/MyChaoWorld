package StateGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainChaoWorld extends BasicGameState{
	public static final int ID = 0;
	private StateBasedGame MainChaoWorld;
	
	private Music music;
	private static Chao Red = new Chao(0,0,"Red");
	private static Image Background;
	private static Image Island;
	private static Image Mango;
	private static Image Blueberry;
	private static Image Peach;
	private static Image Heart;
	private static Image EmptyHand;
	private static Image FullHand;
	private static int x = 50;
	private static int y = 50;
	private static double shift = .15;
	private static Rectangle MangoHit;
	private static Rectangle BlueberryHit;
	private static Rectangle PeachHit;
	private static Rectangle Mouse;
	private static Ellipse ChaoHitBox;
	private static SpriteSheet ChaoAnimate;
	private static Animation ChaoIdle;
	private static SpriteSheet ChaoSleepSprite;
	private static SpriteSheet BaseCoin;
	private static Animation ChaoSleep;
	private static Animation Coin;
	private static Polygon IslandHitBox;
	private static int OGtime;
	private static int time;
	private static int speed = 200;
	private static int Money = 100;
	private static int timer;
	private static int tempx;
	private static float[] points = {500,140,840,310,630,420,500,350,370,420,160,310};
	private static Random ran = new Random();
	private static boolean IsHandEmpty = true;
	private static boolean ChaoIsAsleep = false;

	public int getID(){
		return ID;
	}
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		this.MainChaoWorld=game;
		music = new Music("data/Music/BackgroundMusic.wav");
		music.loop();
		music.setVolume(0.1f);
		Background = new Image ("data/Background/Background.png");
		Island = new Image ("data/Background/Island.png");
		Mango = new Image ("data/Items/Fruit/Mango.png");
		Blueberry = new Image ("data/Items/Fruit/Blueberry.png");
		Peach = new Image ("data/Items/Fruit/Peach.png");
		Heart = new Image ("data/Chao/Heart.png");
		EmptyHand = new Image("data/Hand/EmptyHand.png");
		FullHand = new Image("data/Hand/FullHand.png");
		ChaoAnimate = new SpriteSheet ("data/Chao/ChaoAnimate.png", 40, 48);
		ChaoIdle = new Animation (ChaoAnimate,speed);
		ChaoSleepSprite = new SpriteSheet ("data/Chao/ChaoSleep.png",50,38);
		ChaoSleep = new Animation (ChaoSleepSprite,speed);
		BaseCoin = new SpriteSheet ("data/Items/Coin.png",64,28);
		Coin = new Animation (BaseCoin,speed);
		MangoHit = new Rectangle(x-18, y-19, 36, 40);
		BlueberryHit = new Rectangle(x-18, y-19+50, 36, 40);
		PeachHit = new Rectangle(x-18, y-19+100, 36, 40);
		Mouse = new Rectangle(gc.getInput().getMouseX(),gc.getInput().getMouseY(),1,1);
		ChaoHitBox = new Ellipse(Red.getChaoX()+20,Red.getChaoY()+25,20,25);
		IslandHitBox = new Polygon(points);
		Chao Red = new Chao(0,0,"Red");
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		int SetTime = time+1;
		OGtime+=delta;
		time = OGtime/1000;
		if (gc.getInput().isMousePressed(0) && Money>=10 && Red.getHunger()<91){
			if(Mouse.intersects(MangoHit) && MainGarden.MangoX>0){
				Red.FeedChao(0);
				Money-=10;
				MainGarden.MangoX--;
			}
			else if(Mouse.intersects(BlueberryHit) && MainGarden.BlueberryX>0){
				Red.FeedChao(1);
				Money-=10;
				MainGarden.BlueberryX--;
			}
		}
		if (time==SetTime){
			Red.GetHungry();
			Money++;
		}
		ChaoHitBox = new Ellipse(Red.getChaoX()+20,Red.getChaoY()+25,20,25);
		Mouse = new Rectangle(gc.getInput().getMouseX(),gc.getInput().getMouseY(),1,1);
		timer +=delta;
		if (timer/1000 == 1){
			tempx = ran.nextInt(5)+1;
			// 1 is up 2 is down 3 is right 4 is left
			if (tempx==1){
				// up right
				ChaoIsAsleep=false;
				Red.ChaoY-= 16;
				Red.ChaoX-= 16;
				
			}else if (tempx==2){
				//up left
				ChaoIsAsleep=false;
				Red.ChaoY+= 16;
				Red.ChaoX-= 16;
			}else if (tempx==3){
				//down left
				ChaoIsAsleep=false;
				Red.ChaoY-= 16;
				Red.ChaoX+= 16;
			}else if (tempx==4){
				//down right
				ChaoIsAsleep=false;
				Red.ChaoY+= 16;
				Red.ChaoX+= 16;
			}else if (tempx==5){
				ChaoIsAsleep=true;
			}
			timer=0;
		}
		//Testing Chao's Location
		if (ChaoHitBox.intersects(IslandHitBox)){
			if(Red.ChaoX<500){
				if (Red.ChaoX>370 && Red.ChaoY>350){
					Red.ChaoX-=2;
					Red.ChaoY-=2;
				}else if (Red.ChaoY<310){
					Red.ChaoX+=2;
					Red.ChaoY+=2;
				}else if (Red.ChaoY>310){
					Red.ChaoX+=2;
					Red.ChaoY-=2;
				}
			}else if (Red.ChaoX>500){
				if (Red.ChaoX<630 && Red.ChaoY>350){
					Red.ChaoX+=2;
					Red.ChaoY-=2;
				}else if (Red.ChaoY<310){
					Red.ChaoX-=2;
					Red.ChaoY+=2;
				}else if (Red.ChaoY>310){
					Red.ChaoX-=2;
					Red.ChaoY-=2;
				}
			}
		}
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// Draws Background
		Background.draw();
		Island.drawCentered(500, 300);
		// Draws Fruit
		Mango.drawCentered(x,y);
		Blueberry.drawCentered(x, y+50);
		//Peach.drawCentered(x, y+100);
		// Draws Money
		Coin.draw(0,0);
		g.drawString("$"+Money, 35, 5);
		// Draws Chao
		if (!ChaoIsAsleep){
			ChaoIdle.draw(Red.ChaoX, Red.ChaoY);
		}else {
			ChaoSleep.draw(Red.ChaoX, Red.ChaoY);
		}
		// Draws Hand
		if (IsHandEmpty){
			EmptyHand.draw(gc.getInput().getMouseX()-5,gc.getInput().getMouseY()-30);
		}
		//Testing location
		
		//g.drawString(Integer.toString(gc.getInput().getMouseX()), 100, 50);
		//g.drawString(Integer.toString(gc.getInput().getMouseY()), 100, 80);
		// Shows Chao values
		g.drawString(Red.GetStats(), 900, 0);
		g.drawString("Time: "+time/60+" "+time%60, 450, 0);
		// Loading save state 1
		if (gc.getInput().isKeyPressed(Input.KEY_1)){
			File F = new File("Save1");
				Scanner input;
				try {
					input = new Scanner(F);
					Money=input.nextInt();
					Red.setName(input.next());
					Red.setPower(input.nextInt());
					Red.setStamina(input.nextInt());
					Red.setHunger(input.nextInt());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		}
		g.drawString("Current Mangos: "+MainGarden.MangoX, 0, 500);		
		g.drawString("Current Blueberries: "+MainGarden.BlueberryX, 0, 550);
		//Draw Hitboxs
		
		//g.draw(IslandHitBox);
		//g.draw(ChaoHitBox);
		
		// Ends Game
		EndGame(gc);
	}
	public void keyReleased(int key, char c) {
		if(key==Input.KEY_TAB){
	        MainChaoWorld.enterState(MainGarden.ID);
		}
	}
	
	private void EndGame(GameContainer container){
		if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			try {
				PrintWriter writer = new PrintWriter("Save1");
				writer.println(Money+"\n"+Red.getName()+"\n"+Red.getPower()+"\n"+Red.getStamina()+"\n"+Red.getHunger()+"\n");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
}
