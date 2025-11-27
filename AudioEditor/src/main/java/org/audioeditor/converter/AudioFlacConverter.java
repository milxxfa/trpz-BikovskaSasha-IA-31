package org.audioeditor.converter;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import org.audioeditor.audiotrack.Audiotrack;

import java.io.File;

public class AudioFlacConverter implements Converter<File> {
    public static AudioFlacConverter INSTANCE;
    private EncodingAttributes encodingAttributes;
    private Encoder encoder;

    private AudioFlacConverter() {
        encodingAttributes = new EncodingAttributes();
        encodingAttributes.setFormat("flac");
        encoder = new Encoder();
    }

    public static AudioFlacConverter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AudioFlacConverter();
        }
        return INSTANCE;
    }

    @Override
    public File convertTo(Audiotrack audiotrack) {
        encodingAttributes.setAudioAttributes(audiotrack.getAudioAttributes());

        File convertedFlac = new File(audiotrack.getFileLink().getParent() + "\\" + audiotrack.getFileLink().getName() + " (converted to flac).flac");
        try {
            encoder.encode(audiotrack.getFileLink(), convertedFlac, encodingAttributes);
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }

        return convertedFlac;
    }
}
