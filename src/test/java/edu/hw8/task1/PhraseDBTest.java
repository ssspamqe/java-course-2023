package edu.hw8.task1;

import edu.hw8.task1.server.phraseDB.PhraseDB;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhraseDBTest {

    PhraseDB phraseDB;

    @BeforeEach
    void initPhraseDB() {
        phraseDB = new PhraseDB(List.of("./src/test/java/edu/hw8/task1/testFile.txt"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"language", "java", "pool", "string", "Strings", "not"})
    @DisplayName("PhraseDB should return random phrase by words wich is in this phrase")
    void phraseDB_should_return_phrase_byAnyWordItContains(String word) {
        String returnedPhrase = phraseDB.getPhrase(word).get().toLowerCase();

        assertTrue(returnedPhrase.contains(word.toLowerCase()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"dvds", "aa", "p0ol", "ddddd", "jtut", "aau"})
    @DisplayName("PhraseDB should return empty optional by words which is not in this phrase")
    void phraseDB_should_return_emptyOptional_byAnyWordItNotContains(String word) {
        assertTrue(phraseDB.getPhrase(word).isEmpty());
    }

    @Test
    @DisplayName("PhraseDB should be able to add new phrases")
    void phraseDB_should_beAble_toAddNewPhrases() {
        String newPhrase = "Smart thoughts chased him.... buf he was faster.";
        String word ="him";

        phraseDB.addNewPhrase(newPhrase);
        var returnedPhrase = phraseDB.getPhrase(word).get().toLowerCase();

        assertTrue(returnedPhrase.contains(word.toLowerCase()));

    }

}
