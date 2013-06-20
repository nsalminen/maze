package Utilities;

import java.io.*;
import javax.sound.sampled.*;

/**
 * This class contains an Audio file and controlls to use it in the game
 * 
 * @author Yasen
 */
public class SoundEffect {

    private Clip clip;
    private FloatControl gainControl;
    private boolean playing = false;
    /**
     * Opens an Audio Stream
     * 
     * @param file A File object pointing to a sound file
     */
    public SoundEffect(File file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            //clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
          
        }
    }
    
    /**
     * turns Volume to 0
     */
    public void volumeOn(){
        gainControl.setValue(0);
    }
    /**
     * turns off the sound
     */
    public void volumeOff(){
        gainControl.setValue(gainControl.getMinimum());
    }    
    /**
     * @return Whether this Soundeffect is playing or not
     */
    public boolean isPlaying(){
       return playing;
    }
    /**
     * Sets the volume to a certain amount
     * @param value a Float between -80 and 6 that determines the volume 
     */        
    public void setVolume(float value) {
        gainControl.setValue(value);
    }
    /**
     * Activates the soundeffect
     */
    public void play() {
        if (clip.isRunning()) {
            clip.stop();
        }   // Stop the player if it is still running
        clip.setFramePosition(0); // rewind to the beginning
        clip.start();     // Start playing
        playing = true;
    }
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }   // Stop the player if it is still running
       
    }
}
