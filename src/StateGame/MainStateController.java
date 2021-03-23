package StateGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainStateController extends StateBasedGame {

	   public MainStateController() {
	      super("Chao World");
	   }
	   
	   public void initStatesList(GameContainer container) {
	      addState(new MainChaoWorld());
	      addState(new MainGarden());
	   }
	   
	   public static void main(String[] argv) {
	      try {
	         AppGameContainer container = new AppGameContainer(new MainStateController());
	         container.setDisplayMode(1000, 600, false);
	         container.setAlwaysRender(true);
	         container.setShowFPS(false);
	         container.start();
	      } catch (SlickException e) {
	         e.printStackTrace();
	      }
	   }
	}