/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.simple;

import sample.simple.player.HumanPlayer;
import sample.simple.player.Player;
import sample.simple.player.RandomPlayer;
import sample.simple.service.HelloWorldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// you can use @Value to inject command line args ('--name=whatever') or application properties

	@Autowired
	private HelloWorldService helloWorldService;

	@Override
	public void run(String... args) {

		System.out.println(this.helloWorldService.getHelloMessage());

		// TODO refactor this into a game and allow user to play another game after a game ends

		// TODO write unit tests

		// TODO put board dimensions in properties file and command line

		// TODO specify player letters X and O in properties or readme

		// TODO specify human or AI as player 1 or player 2 in properties or command line

		// TODO update usage in README

		// TODO keep track of score between multiple games

		// TODO write logs to a file instead of standard out so UI is better.

		// TODO write a winning algorithm for the AI

		Player p1 = new HumanPlayer(Board.Play.X, System.in);
		Player p2 = new RandomPlayer(Board.Play.O, 3);
		Player currentPlayer = p1;
		Board currentBoard = new Board(3);
		boolean gameInProgress = true;

		System.out.println(currentBoard);

		while( gameInProgress) {
			
			System.out.print("Player " + currentPlayer.getPlaySymbol() + ": Enter your move: ");

			currentBoard = currentPlayer.getNextMove(currentBoard);

			System.out.println();
			System.out.println(currentBoard);

			if(currentBoard.isWinner(currentPlayer.getPlaySymbol())) {
				System.out.println("PLAYER " + currentPlayer.getPlaySymbol()+ " WINS!!!");
				gameInProgress = false;
			}
			else if(currentBoard.isFull()) {
				System.out.println("ITS A DRAW");
				gameInProgress = false;
			}

			currentPlayer = (currentPlayer == p1) ? p2 : p1;
		}

		System.out.println();

	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}


}
