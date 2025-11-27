package org.audioeditor.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class WaveformCanvas extends Canvas {
    private int[] waveform;
    private double selectionStart = -1; // Початок виділення
    private double selectionEnd = -1;   // Кінець виділення

    public WaveformCanvas(int[] waveform, double width, double height) {
        super(width, height);
        this.waveform = waveform;

        // Додаємо обробку подій миші
        setOnMousePressed(event -> onMousePressed(event));
        setOnMouseDragged(event -> onMouseDragged(event));
        setOnMouseReleased(event -> onMouseReleased(event));

        // Малюємо хвилю
        drawWaveform(waveform, width, height);
    }

    // Обробка натискання миші
    private void onMousePressed(MouseEvent event) {
        selectionStart = event.getX();
        selectionEnd = -1;
    }

    // Обробка перетягування миші
    private void onMouseDragged(MouseEvent event) {
        selectionEnd = event.getX();
        drawWaveformWithSelection(); // Перемальовуємо хвилю з виділенням
    }

    // Обробка відпускання миші
    private void onMouseReleased(MouseEvent event) {
        selectionEnd = event.getX();
        drawWaveformWithSelection();
    }

    // Малювання хвилі з урахуванням виділення
    private void drawWaveformWithSelection() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        drawWaveform(waveform, getWidth(), getHeight());

        // Малюємо виділення (якщо є)
        if (selectionStart != -1 && selectionEnd != -1) {
            gc.setFill(Color.rgb(0, 0, 255, 0.3)); // Прозоре синє виділення
            double x1 = Math.min(selectionStart, selectionEnd);
            double x2 = Math.max(selectionStart, selectionEnd);
            gc.fillRect(x1, 0, x2 - x1, getHeight());
        }
    }

    // Метод для малювання хвилі (без змін)
    private void drawWaveform(int[] waveform, double width, double height) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        double centerY = height / 2;
        for (int i = 0; i < waveform.length - 1; i++) {
            double x1 = i * width / waveform.length;
            double y1 = centerY - (waveform[i] / 32768.0) * centerY;
            double x2 = (i + 1) * width / waveform.length;
            double y2 = centerY - (waveform[i + 1] / 32768.0) * centerY;

            gc.strokeLine(x1, y1, x2, y2);
        }
    }

    // Геттери для координат виділення
    public double getSelectionStart() {
        return selectionStart;
    }

    public double getSelectionEnd() {
        return selectionEnd;
    }
}