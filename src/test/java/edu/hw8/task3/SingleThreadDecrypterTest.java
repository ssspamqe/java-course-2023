package edu.hw8.task3;

import edu.hw8.task3.decryptors.SingleThreadDecrypter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class SingleThreadDecrypterTest {

    private static List<String> DEFAULT_FILE_PATHS = List.of("./src/test/java/edu/hw8/task3/testFile.txt");

    private static int MIN_PASSWORD_LEN = 1;
    private static int MAX_PASSWORD_LEN = 3;


    private SingleThreadDecrypter decrypter = new SingleThreadDecrypter();

    @Test
    @DisplayName("single thread decrypter should be able to decrypt passwords in file")
    void decrypter_should_decryptPasswords(){
        Map<String,String> correctPasswords = new HashMap<>();
        correctPasswords.put("aaaPass","aaa");
        correctPasswords.put("0Pass","0");
        correctPasswords.put("Hx8Pass","Hx8");

        var returnedMap = decrypter.getDecryptedMap(DEFAULT_FILE_PATHS, MIN_PASSWORD_LEN,MAX_PASSWORD_LEN);

        assertThat(returnedMap).containsExactlyEntriesOf(correctPasswords);

    }

}
