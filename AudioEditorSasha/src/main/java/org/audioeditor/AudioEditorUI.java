package org.audioeditor;

import org.audioeditor.repository.AudioRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudioEditorUI {
    private JFrame frame;
    private AudioRepository repository;

    public AudioEditorUI() {
        repository = new AudioRepository();
        frame = new JFrame("Audio Editor");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton addButton = new JButton("Add Audio");
        addButton.setBounds(50, 50, 120, 30);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repository.addAudio("mpthree", "mp3", "C:\\Users\\admin\\Desktop\\1sem\\ТРПЗ\\test2\\songs\\mpthree.mp3");
                JOptionPane.showMessageDialog(frame, "Audio added!");
            }
        });

        JButton viewButton = new JButton("View Audios");
        viewButton.setBounds(50, 100, 120, 30);
        frame.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String audios = String.join(", ", repository.getAllAudio());
                JOptionPane.showMessageDialog(frame, "Audios: " + audios);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AudioEditorUI();
    }
}
