/*
 * Copyright 2012-2013 the original author or authors.
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

package org.thinkbigthings.tictactoe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameConfig {

	@Value("${boardSize:3}")
	private int boardSize;

	@Value("${player1.symbol:X}")
	private String player1Token;

	@Value("${player1.identity:human}")
	private String player1Identity;

	@Value("${player2.symbol:X}")
	private String player2Token;

	@Value("${player2.identity:human}")
	private String player2Identity;

	public GameConfig() {

	}

	public int getBoardSize() {
		return boardSize;
	}

	public String getPlayer1Token() {
		return player1Token;
	}

	public String getPlayer1Identity() {
		return player1Identity;
	}

	public String getPlayer2Token() {
		return player2Token;
	}

	public String getPlayer2Identity() {
		return player2Identity;
	}
}
