package org.uniba.kobold.util;

import javax.sound.sampled.*;
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
        public void setMuted(boolean isMuted) {
                this.isMuted = isMuted;
                if (isMuted) {
                        stopClip();
                } else {
                        playClip();
                }
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
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                }
        }
}
