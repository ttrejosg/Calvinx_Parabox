package Handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.*;

/**
 *
 * @author Tomás y Carlos
 */
public class SoundHandler {
    
    public static ArrayList<File> sounds = new ArrayList<>();
    
    private SoundHandler(){
    }
    
    /**
     * Funcion que carga todos los audios que se encuentran en una carpeta con el fin de ser usados posteriormente
     */
    public static void loadAudio(){
        File directory = new File("src\\Audio\\");
        File[] files = directory.listFiles();
        for(File file : files){
            sounds.add(file);
        }
    }
    
    /**
     * Funcionq ue crea un clip y lo abre
     * @param file el archivo de sonido que se va a reproducir
     * @return el clip a reproducir
     */
    public static Clip createClip(File file){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Audio falla");
        }
        return null;
    }
    
    /**
     * Busca un archivo de audio dentro del ArrayList sounds
     * @param path el nombre del archivo a buscar
     * @return el archivo de sonido que se busca
     */
    public static File get(String path){
        for(File file : sounds){
            if(file.getName().equals(path)) return file;
        }
        return null;
    }
    
}
