package sample.simple;

import sample.simple.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// you can use @Value to inject command line args ('--name=whatever') or application properties

	@Autowired
	private HelloWorldService helloWorldService;

	@Override
	public void run(String... args) {

		System.out.println(this.helloWorldService.getHelloMessage());

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(System.lineSeparator());

		boolean keepPlaying = true;
		while(keepPlaying) {
			Game game = new Game(3, "human", Board.Play.X, "computer", Board.Play.O);
			game.play();
			System.out.print("Play again? (y/n): ");
			String playerContinue = scanner.next().trim();
			if( ! playerContinue.startsWith("y")) {
				keepPlaying = false;
			}
		}

		System.out.println("THANKS FOR PLAYING!!!");

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}


}
