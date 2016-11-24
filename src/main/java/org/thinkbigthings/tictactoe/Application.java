package org.thinkbigthings.tictactoe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private GameConfig config;

	@Inject
	public Application(GameConfig providedConfig) {
		config = providedConfig;
	}

	@Override
	public void run(String... args) {

		if(args.length > 0 && args[0].contains("help")) {
			System.out.println("The properties can be set in application.properties, or overridden from the command line");
			System.out.println("sample arguments are: --boardSize=3 --player1.identity=computer --player2.identity=computer --player1.symbol=X --player2.symbol=O");
			return;
		}

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(System.lineSeparator());

		boolean keepPlaying = true;
		while(keepPlaying) {
			Game game = new Game(config);
			game.play();
			System.out.print("Play again? (yes/no): ");
			String playerContinue = scanner.next().trim();
			if( ! playerContinue.equals("yes")) {
				keepPlaying = false;
			}
		}

		System.out.println("THANKS FOR PLAYING!!!");

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}


}
