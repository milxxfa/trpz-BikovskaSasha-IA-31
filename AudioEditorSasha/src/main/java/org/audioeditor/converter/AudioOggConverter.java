package org.audioeditor.converter;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import org.audioeditor.audiotrack.Audiotrack;

import java.io.File;

public class AudioOggConverter implements Converter<File> {
    private static AudioOggConverter INSTANCE;
    private EncodingAttributes encodingAttributes;
    private Encoder encoder;

    private AudioOggConverter() {
        encodingAttributes = new EncodingAttributes();
        encodingAttributes.setFormat("ogg");
        encoder = new Encoder();
    }

    public static AudioOggConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AudioOggConverter();
        }
        return INSTANCE;
    }

    @Override
    public File convertTo(Audiotrack audiotrack) {
        encodingAttributes.setAudioAttributes(audiotrack.getAudioAttributes());

        File convertedOgg = new File(audiotrack.getFileLink().getParent() + "\\" + audiotrack.getFileLink().getName() + " (converted to ogg).ogg");
        try {
            encoder.encode(audiotrack.getFileLink(), convertedOgg, encodingAttributes);
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }

        return convertedOgg;
    }
}