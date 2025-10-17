package org.audioeditor.audiotrack;

import org.audioeditor.service.AudioEditorUI;

import java.io.*;
import javax.sound.sampled.*;
import java.util.*;

/**
 * Простий утилітний клас для побудови (апроксимації) хвильової форми з аудіо.
 * Працює з InputStream, повертає масив амплітуд (double) нормалізований від 0..1.
 * Це легка реалізація — для реальних задач краще використовувати бібліотеки (TarsosDSP, JAudiotagger тощо).
 */
public class AudioWaveform extends AudioEditorUI {

    /**
     * Зчитати з AudioInputStream і повернути просту хвильову форму (nSamples точок).
     */
    public static double[] generateWaveform(Audiotrack track, int nSamples) {
        if (track == null || nSamples <= 0) return new double[0];

        try (InputStream in = track.getAudioInputStream()) {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
            AudioFormat fmt = ais.getFormat();
            int frameSize = fmt.getFrameSize() > 0 ? fmt.getFrameSize() : 2;
            long frames = ais.getFrameLength();
            long totalBytes = frames * frameSize;

            // Читати порціями і обчислювати RMS для блоків
            byte[] buffer = new byte[frameSize * 1024];
            List<Double> samples = new ArrayList<>();
            long readBytes = 0;
            int bytesRead;
            double blockSum = 0;
            int blockCount = 0;
            long bytesPerSamplePoint = Math.max(1, totalBytes / nSamples);

            while ((bytesRead = ais.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i += frameSize) {
                    // просто інтерпретуємо перший байт кадру як амплітуду (спрощено)
                    int value = buffer[i];
                    blockSum += Math.abs(value);
                    blockCount++;
                    readBytes += frameSize;

                    if (readBytes >= bytesPerSamplePoint) {
                        double avg = (blockCount > 0) ? (blockSum / blockCount) : 0;
                        samples.add(avg);
                        blockSum = 0;
                        blockCount = 0;
                        readBytes = 0;
                    }
                }
            }

            // нормалізація
            double max = samples.stream().mapToDouble(Double::doubleValue).max().orElse(1.0);
            double[] waveform = new double[samples.size()];
            for (int i = 0; i < samples.size(); i++) {
                waveform[i] = max > 0 ? samples.get(i) / max : 0;
            }
            // якщо розмір менший за nSamples — розширити нулями/інтерполяцією
            if (waveform.length < nSamples) {
                double[] out = new double[nSamples];
                System.arraycopy(waveform, 0, out, 0, waveform.length);
                return out;
            } else if (waveform.length > nSamples) {
                double[] out = new double[nSamples];
                for (int i = 0; i < nSamples; i++) out[i] = waveform[(int) ((long) i * waveform.length / nSamples)];
                return out;
            }
            return waveform;
        } catch (UnsupportedAudioFileException | IOException ex) {
            ex.printStackTrace();
            return new double[0];
        }
    }

    /**
     * Зберегти хвильову форму у текстовий файл (по одній точці на рядок) — опційно для виводу.
     */
    public static void saveWaveformToFile(double[] waveform, File outFile) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"))) {
            for (double v : waveform) {
                w.write(Double.toString(v));
                w.newLine();
            }
        }
    }
}
