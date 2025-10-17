package org.audioeditor.service;

import org.audioeditor.repository.ProjectRepository;

import java.util.List;

public class Main extends ProjectRepository {
    public static void main(String[] args) {
        AudioEditorUI ui = new AudioEditorUI();
        ui.showMenu();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public void add(Object item) {

    }

    @Override
    public void update(Object item) {

    }

    @Override
    public void delete(int id) {

    }
}