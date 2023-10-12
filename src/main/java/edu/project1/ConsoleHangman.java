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

    //Call this method to start the game
    @SuppressWarnings("MagicNumber") public void playGame() {

        Scanner sc = new Scanner(System.in);

        String line = "";

        LOGGER.info("Starting game...");

        gameLoop:
        while (!EXIT.equals(line)) {
            LOGGER.info("Starting round...");
            LOGGER.info("Input max possible mistakes");

            int maxMistakes = 5;
            maxMistakes = sc.nextInt();
            sc.nextLine(); //idk why but this method returns empty line without reading an actual stdin

            session = new Session(maxMistakes);
            line = "";

            roundLoop:
            while (!FINISH.equals(line)) {
                LOGGER.info("Word: " + session.getCurrentState());

                LOGGER.info("Guess a letter or type \"exit\" to exit the game, \"finish\" to finish the round");
                line = sc.nextLine();

                if (FINISH.equals(line)) {
                    LOGGER.info(LOSE);
                    LOGGER.info("Mysterious word was: " + session.getWord());
                    break;
                }

                if (EXIT.equals(line)) {
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

    //I need to use not more than 4 return statements according to checkstyle
    boolean isIncorrectInput(String line) {
        if (line.length() != 1) {
            LOGGER.info("Inputted line is not a single char");
            return true;
        } else if (!Character.isLetter(line.charAt(0)) || !Character.isLowerCase(line.charAt(0))) {
            LOGGER.info("Not a lowercase english letter");
            return true;
        } else if (session.wasPlayed(line.charAt(0))) {
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
