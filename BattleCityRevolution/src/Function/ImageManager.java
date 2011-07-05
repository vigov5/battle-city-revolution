package Function;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;


public class ImageManager  {
    private String imagedir;
    private int w,h;

    public ImageManager (String path, int w, int h){
        this.w=w;
        this.h=h;
        File file = new File(path);
        if (!file.exists()) System.out.println("ERROR");
        	//path = "resources/img/noimage.jpg";
        this.imagedir=path;
    }

    //Resize Image
    public Image GetImage(){
    	ImageIcon icon=new ImageIcon(imagedir);
    	Image img = icon.getImage();
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}