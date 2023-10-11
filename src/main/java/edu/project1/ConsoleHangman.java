package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    private final static Logger LOGGER = LogManager.getLogger();
    static final String EXIT = "exit";
    static final String FINISH = "finish";
    static final String LOSE = "You lost!";

    private Session session;

    @SuppressWarnings("MagicNumber") public void playGame() {

        Scanner sc = new Scanner(System.in);

        String line = "";

        LOGGER.info("Starting game...");

        gameLoop:
        while (!line.equals(EXIT)) {
            LOGGER.info("Starting round...");
            LOGGER.info("Input max possible mistakes");

            int maxMistakes = 5;
            maxMistakes = sc.nextInt();
            sc.nextLine(); //idk why but this method returns empty line without reading an actual stdin

            session = new Session(maxMistakes);
            line = "";

            roundLoop:
            while (!line.equals(FINISH)) {
                LOGGER.info("Word: " + session.getCurrentState());

                LOGGER.info("Guess a letter or type \"exit\" to exit the game, \"finish\" to finish the round");
                line = sc.nextLine();

                if (line.equals(FINISH)) {
                    LOGGER.info(LOSE);
                    LOGGER.info("Mysterious word was: " + session.getWord());
                    break roundLoop;
                }

                if (line.equals(EXIT)) {
                    LOGGER.info("Exiting the game...");
                    break gameLoop;
                }

                if (isIncorrectInput(line)) {
                    continue;
                }

                int nowGuessed = session.tryGuess(line.charAt(0));

                line = getGuessCheckResult(nowGuessed);
            }
        }
        sc.close();
    }

    //Are these two methods allowed to output something?
    boolean isIncorrectInput(String line) {


        if (line.length() > 1) {
            LOGGER.info("Inputted line have length 2+");
            return true;
        }

        if (line.isEmpty()) {
            LOGGER.info("Empty line");
            return true;
        }

        if (!Character.isLetter(line.charAt(0)) || !Character.isLowerCase(line.charAt(0))) {
            LOGGER.info("Not a lowercase english letter");
            return true;
        }

        if (session.wasPlayed(line.charAt(0))) {
            LOGGER.info("This character was played");
            return true;
        }

        return false;
    }

    String getGuessCheckResult(int guessed) {
        if (guessed > 0) {
            LOGGER.info("Guessed " + guessed + " letter(s)!");
            if (session.won()) {
                LOGGER.info("You won! Word: " + session.getWord());
                return FINISH;
            }

        } else {
            LOGGER.info("Mistake! " + session.getMistakes() + "/" + session.getMaxMistakes());
            if (session.getMaxMistakes() == session.getMistakes()) {
                LOGGER.info(LOSE);
                LOGGER.info("Secret word was: " + session.getWord());
                return FINISH;
            }
        }
        return "";
    }

}
