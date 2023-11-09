package edu.hw6.Task1;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    File mapFile;

    private int size = 0;

    private static final String DELIMITER = " ^$^ ";

    private static final String UNSUCCESSFUL_READING_FILE = "Unsuccessful tryout of reading ";

    public DiskMap(String fileName) {
        mapFile = new File(fileName);
        try {
            mapFile.createNewFile();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        try (Scanner scanner = new Scanner(mapFile)) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());

                if (entry.getValue().equals(value)) {
                    return true;
                }
            }

            return false;

        } catch (Exception ex) {
            throw new RuntimeException(UNSUCCESSFUL_READING_FILE + mapFile);
        }
    }

    @Override
    public String get(Object key) {
        try (Scanner scanner = new Scanner(mapFile.toPath())) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());

                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }

            return null;

        } catch (Exception ex) {
            throw new RuntimeException(UNSUCCESSFUL_READING_FILE + mapFile);
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {

        var oldValue = get(key);

        remove(key);

        try (FileWriter fileWriter = new FileWriter(mapFile, true)) {
            fileWriter.append(key).append(DELIMITER).append(value).append("\n");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        if (oldValue == null) {
            size++;
        }
        return oldValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> newMap) {
        var keyIntersection = keySet();
        keyIntersection.retainAll(newMap.keySet());

        if (!keyIntersection.isEmpty()) {
            throw new RuntimeException("Intersection between keysets is not allowed");
        }

//        try(FileWriter fileWriter = new FileWriter(mapFile,true)){
//
//        }
//
//        openFileWriter();
//        newMap.forEach((key, value) -> {
//            try {
//                fileWriter.append(key).append(DELIMITER).append(value).append("\n");
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//        closeFileWriter();

    }

    @Override
    public String remove(Object key) {
        var oldValue = get(key);

        if (oldValue != null) {
            size--;
            var entries = entrySet();
            entries.remove(new Entry((String) key, oldValue));
            updateFile(entries);
        }

        return oldValue;
    }

    @Override
    public void clear() {
//        try {
//
//            fileWriter = new FileWriter(mapFile);
//            fileWriter.write("");
//            fileWriter.close();
//
//            size = 0;
//
//        } catch (IOException e) {
//            throw new RuntimeException(UNSUCCESSFUL_READING_FILE + mapFile);
//        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {

        Set<String> keys = new HashSet<>();

        try (Scanner scanner = new Scanner(mapFile)) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());
                keys.add(entry.getKey());
            }

            return keys;

        } catch (Exception ex) {
            throw new RuntimeException(UNSUCCESSFUL_READING_FILE + mapFile);
        }
    }

    @NotNull
    @Override
    public Collection<String> values() {
        Set<String> values = new HashSet<>();
        try (Scanner scanner = new Scanner(mapFile)) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());
                values.add(entry.getValue());
            }

            return values;

        } catch (Exception ex) {
            throw new RuntimeException(UNSUCCESSFUL_READING_FILE + mapFile);
        }
    }

    @Override
    public @NotNull Set<Map.Entry<String, String>> entrySet() {
        Set<Map.Entry<String, String>> entries = new HashSet<>();

        try (Scanner scanner = new Scanner(mapFile)) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());
                entries.add(entry);
            }
            return entries;

        } catch (Exception ex) {
            throw new RuntimeException(UNSUCCESSFUL_READING_FILE + mapFile);
        }
    }

    @Override
    public boolean remove(Object key, Object value) {
        if (get(key).equals(value)) {
            remove(key);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public String replace(String key, String value) {
        var oldValue = get(key);

        remove(key);

        openFileWriter();
        try {
            //fileWriter.append(key).append(DELIMITER).append(value).append("\n");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        closeFileWriter();

        return oldValue;
    }

    private Entry parseLine(String line) {
        String[] data = line.split(DELIMITER);
        return new Entry(data[0], data[1]);
    }

    private void updateFile(Set<Map.Entry<String, String>> entries) {

        clear();

        openFileWriter();
        entries.forEach(entry -> {
            try {
                //fileWriter.append(entry.getKey()).append(DELIMITER).append(entry.getValue()).append("\n");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        closeFileWriter();
    }

    private void openFileWriter() {
//        try {
//            var fileWriter = new FileWriter(mapFile);
//
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
    }

    private void closeFileWriter() {
//        try {
//            var fileWriter.close();
//
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//
//        }
    }

    private static final class Entry implements Map.Entry<String, String> {

        private final String key;
        private String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return hashCode() == obj.hashCode();
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

}
