package MainGame;

public class Chao {
	private static int Power = 0;
	private static int Stamina = 0;
	private static int Hunger = 0;
	private static String Name;
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
	//Increasers
	public void IncreaseStamina(){
		Stamina+=1;
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
}
