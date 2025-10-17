package org.audioeditor.converter;

import org.audioeditor.audiotrack.Audiotrack;
import java.io.File;

public interface Converter<T> {
    File convertTo(Audiotrack audiotrack);
}