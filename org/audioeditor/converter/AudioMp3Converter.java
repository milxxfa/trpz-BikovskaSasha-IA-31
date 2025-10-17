package org.audioeditor.converter;

import org.audioeditor.audiotrack.*;
import java.io.File;

public class AudioMp3Converter implements Converter<Mp3> {
    @Override
    public File convertTo(Audiotrack audiotrack) {
        System.out.println("Конвертація у формат MP3...");
        return new File("output.mp3");
    }
}