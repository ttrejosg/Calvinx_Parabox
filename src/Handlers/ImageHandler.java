package Handlers;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Tomás
 */
public class ImageHandler {
    
    public static ArrayList<File> images = new ArrayList<>();

    public ImageHandler() {
    }
    
    /**
     * Funcion que carga todos las imagenes que se encuentran en una carpeta con el fin de ser usados posteriormente
     */
    public static void loadImage(){
        File directory = new File("src/Resources/Images");
        File[] files = directory.listFiles();
        for(File file : files){
            images.add(file);
        }
    }
    
    /**
     * Busca un archivo de audio dentro del ArrayList sounds
     * @param path el nombre de la imagen a buscar
     * @return la imagen que se busca
     */
    public static Image get(String path){
        for(File file : images){
            if(file.getName().equals(path)){
                Toolkit t = Toolkit.getDefaultToolkit();
                Image image = t.getImage("src/Resources/Images/" + file.getName());
                return image;
            }
        }
        return null;
    }
}
