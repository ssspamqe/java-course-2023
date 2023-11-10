package edu.hw6.Task1;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    File mapFile;

    private int size = 0;

    private FileWorker fileWorker;

    private static final String DELIMITER = ":";

    private static final String UNSUCCESSFUL_READING_FILE = "Unsuccessful tryout of reading ";

    public DiskMap(String fileName) {
        mapFile = new File(fileName);
        try {
            mapFile.createNewFile();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        fileWorker = new FileWorker(mapFile.getPath());

        size = entrySet().size();
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
        return entrySet().stream()
            .anyMatch(mapEntry -> mapEntry.getValue().equals(value));
    }

    @Override
    public String get(Object key) {

        var value = entrySet().stream()
            .filter(mapEntry -> mapEntry.getKey().equals(key))
            .findFirst();

        if (value.isEmpty()) {
            return null;
        }
        return value.get().getValue();
    }

    @Nullable
    @Override
    public String put(String key, String value) {

        var oldValue = get(key);

        remove(key);

        fileWorker.appendLine(new Entry(key, value).toString());

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

        fileWorker.appendAllLines(
            newMap.entrySet()
                .stream()
                .map(Object::toString)
                .toList()
        );
    }

    @Override
    public String remove(Object key) {
        var oldValue = get(key);

        if (oldValue != null) {
            size--;

            var entries = entrySet();
            entries.remove(new Entry((String) key, oldValue));

            fileWorker.clear();
            fileWorker.appendAllLines(
                entries
                    .stream()
                    .map(Object::toString)
                    .toList()
            );
        }

        return oldValue;
    }

    @Override
    public void clear() {
        fileWorker.clear();
        size = 0;
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return entrySet().stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return entrySet().stream()
            .map(Map.Entry::getValue)
            .collect(Collectors.toList());
    }

    @Override
    public @NotNull Set<Map.Entry<String, String>> entrySet() {
        return fileWorker.getAllLines()
            .stream()
            .map(this::parseLine)
            .collect(Collectors.toSet());
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

        fileWorker.appendLine(new Entry(key,value).toString());

        return oldValue;
    }

    private Entry parseLine(String line) {
        String[] data = line.split(DELIMITER);
        return new Entry(data[0], data[1]);
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

        @Override
        public String toString() {
            return key + DELIMITER + value;
        }
    }

}
