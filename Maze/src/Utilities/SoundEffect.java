/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 *
 * @author Yasen
 */
public class SoundEffect {

    private Clip clip;
    private FloatControl gainControl;

    public SoundEffect(File file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public void volumeOn(){
        gainControl.setValue(0);
    }
    
    public void volumeOff(){
        gainControl.setValue(gainControl.getMinimum());
        System.out.println("Volume On!");
    }

    public void setVolume(float value) {
        gainControl.setValue(value);
                System.out.println("Volume Off!");
    }

    public void play() {
        if (clip.isRunning()) {
            clip.stop();
        }   // Stop the player if it is still running
        clip.setFramePosition(0); // rewind to the beginning
        clip.start();     // Start playing
    }
}
