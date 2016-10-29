package org.thinkbigthings.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// you can use @Value to inject command line args ('--name=whatever') or application properties

	@Autowired
	private GameConfig config;

	@Override
	public void run(String... args) {

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
