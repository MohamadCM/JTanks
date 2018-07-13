/*** In The Name of Allah ***/
package bufferstrategy;


import utility.SoundPlayer;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Program start.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class Start {
	public static String startState;
	
    public Start(String startStats) {
		startState = startStats;
		new SoundPlayer("Resources/Sounds/agree.wav").run();
		// Initialize the global thread-pool
		ThreadPool.init();
		// Show the game menu ...
		
		// After the player clicks 'PLAY' ...
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameFrame frame = null;
				try {
					frame = new GameFrame("Game Title");
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.setLocationRelativeTo(null); // put frame at center of screen
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.initBufferStrategy();
				// Create and execute the game-loop
				GameLoop game = new GameLoop(frame);
				game.init();
				ThreadPool.execute(game);
				// and the game starts ...
			}
		});
    }
}
