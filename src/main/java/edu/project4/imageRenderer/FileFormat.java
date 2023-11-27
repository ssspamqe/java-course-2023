package edu.project4.imageRenderer;

public enum FileFormat {
    PNG(".png"),
    JPEG(".jpeg"),
    JPG(".jpg");

    private String stringRepresentation;

    private FileFormat(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
}
