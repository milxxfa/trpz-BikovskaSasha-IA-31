package org.audioeditor.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioProcessor {
    private File audioFile;

    public AudioProcessor(File audioFile) {
        this.audioFile = audioFile;
    }

    public int[] getWaveform() throws IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioInputStream.getFormat();
        byte[] audioBytes = audioInputStream.readAllBytes();

        // Підтримка лише 16-бітного PCM
        if (format.getSampleSizeInBits() != 16 || format.isBigEndian()) {
            throw new UnsupportedAudioFileException("Підтримуються лише 16-бітні PCM файли!");
        }

        int[] samples = new int[audioBytes.length / 2];
        for (int i = 0; i < samples.length; i++) {
            int low = audioBytes[2 * i] & 0xFF;
            int high = audioBytes[2 * i + 1];
            samples[i] = (high << 8) | low;
        }

        return samples;
    }
}