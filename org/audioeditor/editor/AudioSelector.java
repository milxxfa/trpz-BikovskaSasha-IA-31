package org.audioeditor.editor;

import org.audioeditor.audiotrack.Audiotrack;
import org.audioeditor.service.AudioEditorUI;

public class AudioSelector extends AudioEditorUI implements Editor {
    @Override
    public void edit(Audiotrack audiotrack) {
        System.out.println("Виділення частини аудіо...");
    }
}