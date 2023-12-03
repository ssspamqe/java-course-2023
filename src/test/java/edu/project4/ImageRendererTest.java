package edu.project4;

import edu.project4.fractalGeneration.graphics.PixelCanvas;
import edu.project4.imageRenderer.FileFormat;
import edu.project4.imageRenderer.ImageRenderer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageRendererTest {

    private static final Path DEFAULT_TEST_FILES_DIRECTORY =
        Path.of("./src/test/java/edu/project4/imageRendererTestFiles");
    private static final String DEFAULT_FILE_NAME = "img";
    private static final FileFormat DEFAULT_FILE_FORMAT = FileFormat.PNG;

    private PixelCanvas canvas = new PixelCanvas(10, 10);

    @BeforeEach
    void clearTestFiles() throws IOException {
        FileUtils.cleanDirectory(new File(DEFAULT_TEST_FILES_DIRECTORY.toString()));
    }

    @Test
    @DisplayName("Image renderer should render and save pixel canvases")
    void imageRenderer_should_renderAndSavePixelCanvases4() {
        ImageRenderer.renderImage(canvas, DEFAULT_TEST_FILES_DIRECTORY, DEFAULT_FILE_NAME, DEFAULT_FILE_FORMAT);

        var filePath = Path.of(DEFAULT_TEST_FILES_DIRECTORY + "\\" + DEFAULT_FILE_NAME + "." + DEFAULT_FILE_FORMAT);

        assertTrue(Files.exists(filePath));
    }

    @Test
    @DisplayName("Image renderer should enumerate files if file is already exist")
    void imageRenderer_should_enumerateFilesIfFileAlreadyExist() {
        ImageRenderer.renderImage(canvas, DEFAULT_TEST_FILES_DIRECTORY, DEFAULT_FILE_NAME, DEFAULT_FILE_FORMAT);
        ImageRenderer.renderImage(canvas, DEFAULT_TEST_FILES_DIRECTORY, DEFAULT_FILE_NAME, DEFAULT_FILE_FORMAT);

        var filePath = Path.of(DEFAULT_TEST_FILES_DIRECTORY + "\\" + DEFAULT_FILE_NAME + "1." + DEFAULT_FILE_FORMAT);

        assertTrue(Files.exists(filePath));
    }
}
