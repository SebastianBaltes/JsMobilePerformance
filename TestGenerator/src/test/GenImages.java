package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class GenImages {

	public static void main(String[] args) throws IOException {
		Random rnd = new Random(1);
		for (int i = 0; i < 100; i++) {
			BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = img.createGraphics();
			g.setBackground(Color.lightGray);
			g.clearRect(0, 0, img.getWidth(), img.getHeight());
			for (int j = 0; j < 100; j++) {
				g.setColor(new Color(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255)));
				g.fillOval(rnd.nextInt(img.getWidth()*2)-img.getWidth(), rnd.nextInt(img.getHeight()*2)-img.getHeight(), 
						rnd.nextInt(img.getWidth()), rnd.nextInt(img.getHeight()));
			}
			g.dispose();
			ImageIO.write(img, "png", new File("img"+i+".png"));
		}
		
	}

}
