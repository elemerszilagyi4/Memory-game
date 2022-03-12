import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static javax.sound.sampled.AudioSystem.*;

public class Audio {
    Clip clip;
    AudioInputStream audioInputStream;

    public Audio(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = getAudioInputStream(new File(path).getAbsoluteFile());
        clip = getClip();
        clip.open(audioInputStream);
    }

    public void play() {
        clip.start();
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }

}