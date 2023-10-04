package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;
import static java.lang.StringTemplate.STR;

public class ConsoleHangman {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String line = "";

        LOGGER.info("Starting game...");

        boolean first = true;

        gameLoop:
        while (!line.equals("exit")) {
            LOGGER.info("Starting round...");
            LOGGER.info("Input max possible mistakes");
            int maxMistakes = 5;
            maxMistakes = sc.nextInt();
            sc.nextLine();
            Session session = new Session(maxMistakes);
            line = "";
            roundLoop:
            while (!line.equals("finish")) {

                LOGGER.info(STR. "Word: \{ session.getCurrentState() }" );

                LOGGER.info("Guess a letter or type \"exit\" to exit the game, \"finish\" to finish the round");
                line = sc.nextLine();

                if (line.equals("finish")) {
                    LOGGER.info("You lost!");
                    LOGGER.info(STR. "Mysterious word was: \{ session.getWord() }" );
                    break roundLoop;
                }

                if (line.equals("exit")) {
                    LOGGER.info("Exiting the game...");
                    break gameLoop;
                }

                if (line.length() > 1) {
                    LOGGER.info("Inputted line have length 2+");
                    continue;
                }

                if (line.isEmpty()) {
                    LOGGER.info("Empty line");
                    continue;
                }

                if (!Character.isLowerCase(line.charAt(0))) {
                    LOGGER.info("Not a lowercase english letter");
                    continue;
                }

                if (session.wasPlayed(line.charAt(0))) {
                    LOGGER.info("This character was played");
                    continue;
                }

                int nowGuessed = session.tryGuess(line.charAt(0));

                if (nowGuessed > 0) {
                    LOGGER.info(STR. "Guessed \{ nowGuessed } letter(s)!" );

                    if(session.won()){
                        LOGGER.info(STR."You won! Mysterious word: \{session.getWord()}");
                        break roundLoop;
                    }

                } else {
                    LOGGER.info(STR. "Mistake! \{ session.getMistakes() }/\{ session.getMaxMistakes() }" );
                    if (session.getMaxMistakes() == session.getMistakes()) {
                        LOGGER.info("You lost!");
                        LOGGER.info(STR. "Mysterious word was: \{ session.getWord() }" );
                        break roundLoop;
                    }
                }

            }

        }

        sc.close();

    }

}
