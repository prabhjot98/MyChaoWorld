package StateGame;

import java.util.Random;

public class Chao {
	private static int Power = 0;
	private static int Stamina = 0;
	private static int Hunger = 0;
	private static String Name;
	public static float ChaoX = 500;
	public static float ChaoY = 250;
	private static Random ran = new Random();
	public Chao(int i, int j, String k){
		Power = i;
		Stamina = j;
		Name = k;
	}
	// Getters
	public String getName() {
		return Name;
	}
	public int getHunger() {
		return Hunger;
	}
	public String getPower(){
		return Integer.toString(Power);
	}
	public String getStamina(){
		return Integer.toString(Stamina);
	}
	public String GetStats(){
		return "Name: "+Name+"\nPower: "+Power+"\nStamina: "+Stamina+"\nHunger: "+Hunger;
	}
	// Setters
	public void setName(String s) {
		Name = s;
	}
	public void setHunger(int x) {
		Hunger = x;
	}
	public void setPower(int x){
		Power = x;
	}
	public void setStamina(int x){
		Stamina = x;
	}
	//Increase
	public void IncreaseStamina(){
		Stamina+=1;
	}
	public float getChaoX(){
		return ChaoX;
	}
	public float getChaoY(){
		return ChaoY;
	}
	public void setChaoX(float x){
		ChaoX=x;
	}
	public void setChaoY(float y){
		ChaoY=y;
	}
	public void IncreasePower(){
		Power+=1;
	}
	//Feeds
	public void FeedChao(int stat){
		if (Hunger<91){
			Hunger+=10;
			if (stat==1){
				Power++;
			}
			if (stat==0){
				Stamina++;
			}
		}
	}
	public void GetHungry(){
		if (Hunger>0){
		Hunger-=1;
		}
	}
	// Determines location
	public void MoveChao(int timer){
		int tempx;
		if (timer/1000 == 1){
			tempx = ran.nextInt(5)+1;
			// 1 is up 2 is down 3 is right 4 is left
			if (tempx==1){
				ChaoY-= 16;
			}else if (tempx==2){
				ChaoY+= 16;
			}else if (tempx==3){
				ChaoX+= 16;
			}else if (tempx==4){
				ChaoX-= 16;
			}else if (tempx==5){
			}
			timer=0;
		}
	}
}
