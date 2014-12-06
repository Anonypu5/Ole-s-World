package no.Strohm.game2D.state;

import no.Strohm.game2D.Game;
import no.Strohm.game2D.InputHandler;
import no.Strohm.game2D.Multiplayer.Client;
import no.Strohm.game2D.Multiplayer.Server;
import no.Strohm.game2D.graphics.Screen;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by Ole on 28/11/2014.
 */
public class StateMenuMultiplayer extends StateMenu {

	private static String[] options = new String[]{
			"Join Server",
			"Start Server",
			"Back"
	};
	private static String title = "Multiplayer";

	public StateMenuMultiplayer(InputHandler input) {
		super(options.length, multiplayerId, input);
	}

	@Override
	protected void press() {
		switch (selected) {
			case 0:
                try {
                    String ip = Inet4Address.getLocalHost().getHostAddress();
                    System.out.println("Your local ip is "+ip);
                    new Client(ip, 1999,"Elias");
                } catch (Exception e) {
                    System.out.println("Check your internet connection");
                }
				break;
			case 1:
                try {
                    Game.server = new Server(1999, 16);
                    Game.server.start();
                } catch (Exception e) {
                }
                break;
			case 2:
				setState(lastState);
				break;
		}
	}

	@Override
	public void render(Screen screen) {
		screen.renderArea(0x353535, 0, Game.WIDTH, 0, Game.HEIGHT, false);
		screen.renderText(title, (screen.w - title.length() * 8) / 2, 60, col2, false);
		for (int i = 0; i < options.length; i++) {
			screen.renderText(options[i], (screen.w - options[i].length() * 8) / 2, screen.h / 2 - 15 + (i * 10), getColor(i), false);
		}
	}
}
