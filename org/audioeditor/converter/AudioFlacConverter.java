package org.audioeditor.converter;

import org.audioeditor.audiotrack.*;
import java.io.File;

public class AudioFlacConverter implements Converter<Flac> {
    @Override
    public File convertTo(Audiotrack audiotrack) {
        System.out.println("Конвертація у формат FLAC...");
        return new File("output.flac");
    }
}