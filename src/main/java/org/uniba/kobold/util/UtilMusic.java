package org.uniba.kobold.util;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UtilMusic extends Thread{

        /**
         * Attributes of the class UtilMusic
         */
        private static UtilMusic instance;
        private boolean isMuted;
        private Clip clip;

        /**
         * UtilMusic Constructor
         */
        private UtilMusic() {
                isMuted = false;
        }

        /**
         * getInstance Method to get the instance
         * @return instance
         */
        public static UtilMusic getInstance() {
                if (instance == null) {
                        instance = new UtilMusic();
                }
                return instance;
        }
        public boolean isMuted() {
                return isMuted;
        }

        /**
         * setMuted Method to mute
         * @param isMuted boolean to see if the music is muted or not
         */
        public void setMuted(boolean isMuted, JToggleButton muteMusicButton) {
                this.isMuted = isMuted;
                if (isMuted) {
                        stopClip();
                        setOffText(muteMusicButton);
                } else {
                        playClip();
                        setOnText(muteMusicButton);
                }
        }

        /**
         * setOnText Method to set the text on
         * @param muteMusicButton JToggleButton to toggle music
         */
        public static void setOnText(JToggleButton muteMusicButton) {
                muteMusicButton.setText("\uD83D\uDD0A");
        }

        /**
         * setOffText Method to set the text off
         * @param muteMusicButton JToggleButton to toggle music
         */
        public static void setOffText(JToggleButton muteMusicButton) {
                muteMusicButton.setText("\uD83D\uDD07");
        }
        /**
         * playClip Method to play the clip
         */
        public void playClip() {
                if (clip != null && !isMuted) {
                        clip.start();
                }
        }

        /**
         * stopClip Method to stop the clip
         */
        private void stopClip() {
                if (clip != null) {
                        clip.stop();
                }
        }

        /**
         * run Method to run the thread
         */
        public void run() {
                try {
                        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("src/main/resources/music/Gegagedigedagedago.wav"));
                        clip = AudioSystem.getClip();
                        clip.open(stream);
                        clip.addLineListener(event -> {
                                if (event.getType() == LineEvent.Type.STOP && !isMuted) {
                                        clip.setFramePosition(0);
                                        playClip();
                                }
                        });
                        playClip();
                } catch (UnsupportedAudioFileException e) {
                        System.err.println("Unsupported audio file: " + e.getMessage());
                } catch (IOException e) {
                        System.err.println("I/O error: " + e.getMessage());
                } catch (LineUnavailableException e) {
                        System.err.println("Line unavailable: " + e.getMessage());
                }
        }
}
