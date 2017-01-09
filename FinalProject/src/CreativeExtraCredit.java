//Secret functionality (doccumentation defeats the purpose)

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class CreativeExtraCredit {

    public void playSound() throws IOException {
        setOutputVolume((float) 7.0);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/audioFile.wav").getAbsoluteFile());
            javax.sound.sampled.Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            while (clip.isActive()) {
                setOutputVolume((float) 7.0);
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

    }

    public static void setOutputVolume(float value) {
        String command = "set volume " + value;
        try {
            ProcessBuilder pb = new ProcessBuilder("osascript", "-e", command);
            pb.directory(new File("/usr/bin"));
            StringBuffer output = new StringBuffer();
            Process p = pb.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
