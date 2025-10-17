package org.audioeditor.converter;

import org.audioeditor.audiotrack.*;
import java.io.File;

public class AudioOggConverter implements Converter<Ogg> {
    @Override
    public File convertTo(Audiotrack audiotrack) {
        System.out.println("Конвертація у формат OGG...");
        return new File("output.ogg");
    }
}