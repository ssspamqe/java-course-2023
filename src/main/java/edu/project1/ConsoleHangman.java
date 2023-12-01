package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] params) {
        launch();
    }

    public static void launch() {

        LOGGER.info(AsciiPictures.TITLE);

        Scanner scanner = new Scanner(System.in);

        if (!getUserYNDecision("Wanna start?", scanner)) {
            return;
        }
        LOGGER.info("Well, starting game...");

        do {
            LOGGER.info("Starting round...");
            handleRound(scanner);
        } while (getUserYNDecision("Wanna start new round?", scanner));
    }

    private static void handleRound(Scanner scanner) {
        Session session = new Session();

        while (true) {
            LOGGER.info(session.getHangmanPicture());
            LOGGER.info("Your current state: {}", session.getCurrentState());
            LOGGER.info("Please, choose one character from these: {}", session.getRemainingChars());

            String line = getNextLine(scanner);
            if (line.length() != 1) {
                LOGGER.warn("Please, input only one character");
                continue;
            }
            char c = line.charAt(0);

            handleCharacter(c,session);

            if (session.ended()) {
                LOGGER.info("Word: {}", session.getWord());
                break;
            }
        }
    }

    private static void handleCharacter(char c,Session session) {
        if (session.wasTried(c)) {
            LOGGER.info("Such character was already played");
        } else {
            session.tryGuess(c);
            if (session.won()) {
                LOGGER.info("You won!");
                LOGGER.info(AsciiPictures.THUMB_UP_VAULT_BOY);
            } else if (session.lost()) {
                LOGGER.info(session.getHangmanPicture());
                LOGGER.info(AsciiPictures.HANGED_ART);
            }
        }
    }

    private static boolean getUserYNDecision(String questing, Scanner scanner) {
        LOGGER.warn(questing);
        LOGGER.info("Type 'y' or 'n'");

        char decision = '\n';
        while (decision != 'y' && decision != 'n') {
            decision = getNextLine(scanner).charAt(0);
        }

        return decision == 'y';
    }

    private static String getNextLine(Scanner scanner) {
        String line = "";
        while (line.isEmpty()) {
            line = scanner.next();
        }
        return line;
    }
}
