package org.audioeditor;

import org.audioeditor.audiotrack.Flac;
import org.audioeditor.database.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        new AudioEditorUI();
    }
}