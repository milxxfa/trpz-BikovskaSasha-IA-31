package org.audioeditor.converter;

import org.audioeditor.audiotrack.Audiotrack;

public interface Converter<T> {
    T convertTo(Audiotrack audiotrack);
}