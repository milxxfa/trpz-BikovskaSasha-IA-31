package org.audioeditor.editor;

import org.audioeditor.audiotrack.Audiotrack;
import org.audioeditor.service.AudioEditorUI;

public class AudioCut extends AudioEditorUI implements Editor {
    @Override
    public void edit(Audiotrack audiotrack) {
        System.out.println("Обрізання фрагмента аудіо...");
    }
}