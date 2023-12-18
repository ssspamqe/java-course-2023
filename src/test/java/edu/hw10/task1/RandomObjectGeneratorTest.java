package edu.hw10.task1;

import edu.hw10.Task1.RandomObjectGenerator;
import edu.hw10.Task1.exceptions.IncorrectConstraintsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomObjectGeneratorTest {

    private static final RandomObjectGenerator ROG = new RandomObjectGenerator();
    private static final double LOWER_BOUND = -1000;
    private static final double UPPER_BOUND = 1000;

    @Test
    @DisplayName("nextObject should generate random object with its constructor")
    void nextObject_should_generateRandomObject_byConstructor() {
        RecordSample generatedRecord = (RecordSample) ROG.nextObject(RecordSample.class);

        int sampleInt = generatedRecord.sampleInt();
        float sampleFloat = generatedRecord.sampleFloat();

        assertTrue(sampleInt >= LOWER_BOUND && sampleInt <= UPPER_BOUND);
        assertTrue(sampleFloat >= LOWER_BOUND && sampleFloat <= UPPER_BOUND);
    }

    @Test
    @DisplayName("nextObject should generate random object with its static fabric method")
    void nextObject_should_generateRandomObject_byFabricMethod() {
        POJOSample generatedPOJO = (POJOSample) ROG.nextObject(POJOSample.class, "create");

        Integer sampleNotNullIntWrapper = generatedPOJO.getSampleNotNullIntWrapper();
        Double sampleDoubleWrapper = generatedPOJO.getSampleDoubleWrapper();

        assertTrue(sampleNotNullIntWrapper >= LOWER_BOUND && sampleNotNullIntWrapper <= UPPER_BOUND);
        assertTrue(sampleDoubleWrapper == null ||
            (sampleDoubleWrapper >= LOWER_BOUND && sampleDoubleWrapper <= UPPER_BOUND));
    }

    @Test
    @DisplayName("nextObject should throw an IncorrectConstraintsException" +
        " while trying to pass incorrect Min/Max constraints")
    void nextObject_should_throwException_when_passingIncorrectConstraints() {
        assertThrows(
            IncorrectConstraintsException.class,
            () -> ROG.nextObject(IncorrectConstraintsRecord.class)
        );
    }
}
