package org.audioeditor.app;

import org.audioeditor.audio.AudioProcessor;
import org.audioeditor.ui.WaveformCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);

        // Кнопка для вибору файлу
        Button selectFileButton = new Button("Обрати аудіофайл");
        selectFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("WAV Files", "*.wav"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                try {
                    AudioProcessor processor = new AudioProcessor(selectedFile);
                    int[] waveform = processor.getWaveform();

                    // Створюємо Canvas для відображення WAVE-форми
                    WaveformCanvas waveformCanvas = new WaveformCanvas(waveform, 800, 400);

                    // Додаємо Canvas у кореневий контейнер
                    root.getChildren().add(waveformCanvas);
                } catch (IOException | UnsupportedAudioFileException ex) {
                    System.err.println("Помилка обробки аудіофайлу: " + ex.getMessage());
                }
            }
        });

        root.getChildren().add(selectFileButton);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Аудіоредактор");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Створюємо панель інструментів
        ToolBar toolBar = new ToolBar();

        Button copyButton = new Button("Копіювати");
        Button pasteButton = new Button("Вставити");
        Button cutButton = new Button("Вирізати");

        // Додаємо обробку подій для кнопок
        copyButton.setOnAction(e -> {
            // TODO: Реалізувати копіювання обраного сегменту
            System.out.println("Копіювати сегмент");
        });

        pasteButton.setOnAction(e -> {
            // TODO: Реалізувати вставку сегменту
            System.out.println("Вставити сегмент");
        });

        cutButton.setOnAction(e -> {
            // TODO: Реалізувати вирізання сегменту
            System.out.println("Вирізати сегмент");
        });

        // Додаємо кнопки в панель інструментів
        toolBar.getItems().addAll(copyButton, pasteButton, cutButton);
        root.getChildren().add(0, toolBar);
    }

    public static void main(String[] args) {
        launch(args);
    }
}