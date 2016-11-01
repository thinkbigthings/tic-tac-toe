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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game")
public class GameConfig {

	// TODO be able to use immutable configuration
    // https://github.com/spring-projects/spring-boot/issues/1254
	// http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html

	private int boardSize = 3;
	private String tokenPlayer1 = "X";
	private String tokenPlayer2 = "O";
	private String identityPlayer1 = "human";
	private String identityPlayer2 = "computer";

	public GameConfig() {

	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public String getTokenPlayer1() {
		return tokenPlayer1;
	}

	public void setTokenPlayer1(String tokenPlayer1) {
		this.tokenPlayer1 = tokenPlayer1;
	}

	public String getTokenPlayer2() {
		return tokenPlayer2;
	}

	public void setTokenPlayer2(String tokenPlayer2) {
		this.tokenPlayer2 = tokenPlayer2;
	}

	public String getIdentityPlayer1() {
		return identityPlayer1;
	}

	public void setIdentityPlayer1(String identityPlayer1) {
		this.identityPlayer1 = identityPlayer1;
	}

	public String getIdentityPlayer2() {
		return identityPlayer2;
	}

	public void setIdentityPlayer2(String identityPlayer2) {
		this.identityPlayer2 = identityPlayer2;
	}
}
