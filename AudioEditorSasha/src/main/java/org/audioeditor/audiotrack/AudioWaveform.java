package org.audioeditor.audiotrack;

public class AudioWaveform {

    private String audioFilePath;
    private byte[] audioData;

    public AudioWaveform(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    public void loadAudioFile() {
        // Logic...
    }

    // Метод для відображення WAVE-форм
    public void displayWaveform() {
        // Logic...
    }

    public byte[] getAudioData() {
        return audioData;
    }

    public void setAudioData(byte[] audioData) {
        this.audioData = audioData;
    }
}