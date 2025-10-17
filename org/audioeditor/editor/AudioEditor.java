package org.audioeditor.editor;

import org.audioeditor.audiotrack.Audiotrack;
import org.audioeditor.service.AudioEditorUI;

public class AudioEditor extends AudioEditorUI implements Editor {
    private Editor selector;
    private Editor copier;
    private Editor paste;
    private Editor cut;

    @Override
    public void edit(Audiotrack audiotrack) {
        System.out.println("Редагування треку...");
    }
}