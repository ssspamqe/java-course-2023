package edu.hw6.Task1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class Task1Test {

    private static final String FILE_NAME = "./src/test/java/edu/hw6/Task1/map.txt";

    Map<String, String> diskMap;

    @BeforeEach
    void initMap() {
        try {
            Files.deleteIfExists(Path.of(FILE_NAME));

        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }
        diskMap = new DiskMap(FILE_NAME);
    }

    @Test
    @DisplayName("DiskMap should be able to get and return key-value pair")
    void putAndGet_should_getAndReturnEntries() {
        String key = "firstKey";
        String value = "firstValue";

        diskMap.put(key, value);
        String returnedVal = diskMap.get(key);

        assertThat(returnedVal).isEqualTo(value);
    }

    @Test
    @DisplayName("DiskMap should be able to put all entries from another map")
    void putAll_should_putEntriesFromAnotherMap() {
        Map<String, String> oldMap = new HashMap<>();
        oldMap.put("firstKey", "firstValue");
        oldMap.put("secondKey", "secondValue");

        diskMap.putAll(oldMap);
        var firstValue = diskMap.get("firstKey");
        var secondValue = diskMap.get("secondKey");

        assertThat(firstValue).isEqualTo("firstValue");
        assertThat(secondValue).isEqualTo("secondValue");
    }

    @Test
    @DisplayName("Disk map should throw and exception if trying to put newMap that have the same keys as disk map")
    void putAll_should_throwAnException_when_puttingMapWithSameKeys() {
        Map<String, String> newMap = new HashMap<>();
        newMap.put("firstKey", "firstValue");
        newMap.put("secondKey", "secondValue");

        diskMap.put("firstKey", "123");

        assertThatThrownBy(
            () -> diskMap.putAll(newMap)
        ).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("replace(key,value) should replace value and return the old one")
    void replace_should_replaceValueAndReturnTheOldOne() {
        var key = "firstKey";
        var oldValue = "oldValue";
        var newValue = "newValue";

        diskMap.put(key, oldValue);
        var returnedValue = diskMap.put(key, newValue);
        var currentValue = diskMap.get(key);

        assertThat(returnedValue).isEqualTo(oldValue);
        assertThat(currentValue).isEqualTo(newValue);
    }

    @Test
    @DisplayName("remove(key,value) should remove entry if value is equal to the old one")
    void removeKeyValue_should_removeEntry_ifValueIsEqualToTheOldOne() {
        var key = "firstKey";
        var value = "firstValue";
        var anotherValue = "anotherValue";

        diskMap.put(key, value);

        diskMap.remove(key, anotherValue);
        var returnedValue1 = diskMap.get(key);

        diskMap.remove(key, value);
        var returnedValue2 = diskMap.get(key);

        assertThat(returnedValue1).isEqualTo(value);
        assertThat(returnedValue2).isNull();
    }

    @Test
    @DisplayName("entrySet() should return a set of entries")
    void entrySet_should_return_aSetOfEntries() {
        var firstKey = "fKey";
        var firstValue = "fVal";
        var secondKey = "sKey";
        var secondValue = "sVal";

        diskMap.put(firstKey, firstValue);
        diskMap.put(secondKey, secondValue);

        var actualEntries = new HashSet<DiskMap.Entry>();
        actualEntries.add(new DiskMap.Entry(firstKey, firstValue));
        actualEntries.add(new DiskMap.Entry(secondKey, secondValue));

        var returnedEntries = diskMap.entrySet();

        assertThat(returnedEntries).containsExactlyInAnyOrderElementsOf(actualEntries);
    }

    @Test
    @DisplayName("values() should return a collection of values of diskMap")
    void values_should_return_valuesOfDiskMap() {
        var firstKey = "fKey";
        var firstValue = "fVal";
        var secondKey = "sKey";
        var secondValue = "sVal";

        var actualValues = List.of(firstValue, secondValue);

        diskMap.put(firstKey, firstValue);
        diskMap.put(secondKey, secondValue);
        var returnedValues = diskMap.values();

        assertThat(returnedValues).containsExactlyInAnyOrderElementsOf(actualValues);
    }

    @Test
    @DisplayName("keySet() should return a set of keys")
    void keySet_should_return_aSetOfKeys() {
        var firstKey = "fKey";
        var firstValue = "fVal";
        var secondKey = "sKey";
        var secondValue = "sVal";

        var actualKeys = Set.of(firstKey, secondKey);

        diskMap.put(firstKey, firstValue);
        diskMap.put(secondKey, secondValue);
        var returnedKeys = diskMap.keySet();

        assertThat(returnedKeys).containsExactlyInAnyOrderElementsOf(actualKeys);
    }

    @Test
    @DisplayName("clear() should clear the diskMap")
    void clear_should_clearTheDiskMap() {
        var key = "key";
        var value = "val";

        diskMap.put(key, value);
        diskMap.clear();
        var returnedVal = diskMap.get(key);

        assertThat(returnedVal).isNull();
    }

    @Test
    @DisplayName("Disk map should be able to count it's size")
    void diskMap_should_countSize() {
        assertThat(diskMap.size()).isEqualTo(0);

        diskMap.put("key", "val");
        assertThat(diskMap.size()).isEqualTo(1);

        diskMap.put("key2", "val2");
        assertThat(diskMap.size()).isEqualTo(2);

        diskMap.remove("adadsa");
        assertThat(diskMap.size()).isEqualTo(2);

        diskMap.remove("key", "wrong value");
        assertThat(diskMap.size()).isEqualTo(2);

        diskMap.remove("key");
        assertThat(diskMap.size()).isEqualTo(1);

        diskMap.clear();
        assertThat(diskMap.isEmpty()).isTrue();
    }

}
