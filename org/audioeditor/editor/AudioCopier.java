package org.audioeditor.editor;

import org.audioeditor.audiotrack.Audiotrack;
import org.audioeditor.service.AudioEditorUI;

public class AudioCopier extends AudioEditorUI implements Editor {
    @Override
    public void edit(Audiotrack audiotrack) {
        System.out.println("Копіювання фрагмента аудіо...");
    }
}