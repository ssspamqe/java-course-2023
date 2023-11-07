package edu.hw6.Task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DiskMap implements Map<String, String> {

    private static final Logger LOGGER = LogManager.getLogger();

    File mapFile;

    BufferedWriter fileWriter;

    private int size;

    private final String DELIMITER = " ^$^ ";

    public DiskMap(String fileName) throws IOException {
        mapFile = new File(fileName);
        if (!mapFile.createNewFile()) {
            throw new IOException("Such file already exists");
        }

        fileWriter = new BufferedWriter(new FileWriter(mapFile));
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
            throw new RuntimeException("Unsuccessful tryout of reading " + mapFile);
        }
    }

    @Override
    public String get(Object key) {
        try (Scanner scanner = new Scanner(mapFile)) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());

                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }

            return null;

        } catch (Exception ex) {
            throw new RuntimeException("Unsuccessful tryout of reading " + mapFile);
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (keySet().contains(key)) {
            throw new IllegalArgumentException("Such key already exists");
        }

        try {
            fileWriter.append(key).append(DELIMITER).append(value).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    @Override
    public String remove(Object key) {
        if (keySet().contains((String) key)) {
            throw new IllegalArgumentException("Such key already exists");
        }

        var entries = entrySet();
        try {
            fileWriter.write("");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        String oldValue = "";
        for(var entry:entries){
            if (!entry.getKey().equals(key)) {
                try {
                    fileWriter.append(entry.getKey()).append(DELIMITER).append(entry.getValue()).append("\n");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else
                oldValue = entry.getValue();
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

        newMap.forEach((key, value) -> {
            try {
                fileWriter.append(key).append(DELIMITER).append(value).append("\n");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    @Override
    public void clear() {
        try {
            fileWriter.write("");
            size = 0;
        } catch (IOException e) {
            throw new RuntimeException("Unsuccessful tryout of reading " + mapFile);
        }
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
            throw new RuntimeException("Unsuccessful tryout of reading " + mapFile);
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
            throw new RuntimeException("Unsuccessful tryout of reading " + mapFile);
        }
    }

    @NotNull
    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        Set<Map.Entry<String, String>> entries = new HashSet<>();

        try (Scanner scanner = new Scanner(mapFile)) {

            while (scanner.hasNext()) {
                Entry entry = parseLine(scanner.nextLine());
                entries.add(entry);
            }

            return entries;

        } catch (Exception ex) {
            throw new RuntimeException("Unsuccessful tryout of reading " + mapFile);
        }
    }

    @Override
    public boolean remove(Object key, Object value) {
        if (get(key) == null) {
            return false;
        }

        remove(key);

        return true;
    }

    @Nullable
    @Override
    public String replace(String key, String value) {
        if (!keySet().contains(key)) {
            return null;
        }
        var oldValue = get(key);

        remove(key);

        try{
            fileWriter.append(key).append(DELIMITER).append(value).append("\n");
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }

        return oldValue;
    }

    private Entry parseLine(String line) {
        String[] data = line.split(DELIMITER);
        return new Entry(data[0], data[1]);
    }

    private void updateFile(Set<Entry> entries) {
        try {
            fileWriter.write("");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        entries.forEach(entry -> {
            try {
                fileWriter.append(entry.key).append(DELIMITER).append(entry.value).append("\n");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private final class Entry implements Map.Entry<String, String> {

        private String key;
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
