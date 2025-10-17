package org.audioeditor.audiotrack;

import org.audioeditor.service.AudioEditorUI;

import java.io.*;

public class Audiotrack extends AudioEditorUI {
    public AudioAttributes getAudioAttributes() {
        return new AudioAttributes();
    }

    public File getFileLink() {
        return new File("track.wav");
    }

    public InputStream getAudioInputStream() throws FileNotFoundException {
        return new FileInputStream(getFileLink());
    }

    public Audiotrack copy() {
        return new Audiotrack();
    }
}

class AudioAttributes extends AudioEditorUI {
    private int bitrate;
    private int channels;
    private double duration;
}