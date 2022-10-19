package model;
import javafx.animation.AnimationTimer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private File file;
    public Clip clip;

    private AudioInputStream audioInputStream;

    public Music(String url) {
        file = new File(url);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);

            try {
                clip = AudioSystem.getClip();

            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

            try {
                clip.open(audioInputStream);

            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Volume (float a){
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(a); //Reduce volume by a decibels
    }

    private int timeMusic = 0;
    private AnimationTimer musicTime;
    public void musicStart(int time){
        musicTime = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeMusic ++;
                if (timeMusic == time) {
                    timeMusic = 0;
                    clip.stop();
                    musicTime.stop();
                }
            }
        };
        clip.setMicrosecondPosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        musicTime.start();
    }

    public void musicLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void musicStop() {
        clip.stop();
    }
}
