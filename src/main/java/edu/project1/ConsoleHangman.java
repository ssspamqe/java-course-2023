package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import static java.lang.StringTemplate.STR;

public class ConsoleHangman {

    private final static Logger LOGGER = LogManager.getLogger();
    final String constEXIT = "exit";
    final String constFINISH = "finish";
    final String constLOSE = "You lost!";

    @SuppressWarnings("MagicNumber") public void playGame() {

        Scanner sc = new Scanner(System.in);

        String line = "";

        LOGGER.info("Starting game...");

        gameLoop:
        while (!line.equals(constEXIT)) {
            LOGGER.info("Starting round...");
            LOGGER.info("Input max possible mistakes");
            int maxMistakes = 5;
            maxMistakes = sc.nextInt();
            sc.nextLine(); //idk why but this method returns empty line without reading an actual stdin
            Session session = new Session(maxMistakes);
            line = "";
            roundLoop:
            while (!line.equals(constFINISH)) {

                //LOGGER.info(STR. "Word: \{ session.getCurrentState() }" );
                LOGGER.info("Word: " + session.getCurrentState());
                //LOGGER.info("Guess a letter or type \"exit\" to
                // exit the game, \"finish\" to finish the round");
                LOGGER.info("Guess a letter or type \"exit\" to exit the game, \"finish\" to finish the round");
                line = sc.nextLine();

                if (line.equals(constFINISH)) {
                    LOGGER.info(constLOSE);
                    //LOGGER.info(STR."Mysterious word was: \{ session.getWord() }" );
                    LOGGER.info("Mysterious word was: " + session.getWord());
                    break roundLoop;
                }

                if (line.equals(constEXIT)) {
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
                    //LOGGER.info(STR. "Guessed \{ nowGuessed } letter(s)!" );
                    LOGGER.info("Guessed " + nowGuessed + " letter(s)!");
                    if (session.won()) {
                        //LOGGER.info(STR. "You won! Mysterious word: \{ session.getWord() }" );
                        LOGGER.info("You won! Word: " + session.getWord());
                        break roundLoop;
                    }

                } else {
                    //LOGGER.info(STR. "Mistake! \{ session.getMistakes() }/\{ session.getMaxMistakes() }" );
                    LOGGER.info("Mistake! " + session.getMistakes() + "/" + session.getMaxMistakes());
                    if (session.getMaxMistakes() == session.getMistakes()) {
                        LOGGER.info(constLOSE);
                        //LOGGER.info(STR. "Mysterious word was: \{ session.getWord() }" );
                        LOGGER.info("Secret word was: " + session.getWord());
                        break roundLoop;
                    }
                }

            }

        }

        sc.close();

    }

}
