import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.imageio.ImageIO;

public class MazeTextToPng {

	public static void main(String[] args) {
		/*image size = maze size * 2 + 1 (BOTH AXIS ARE INVERTED)
		 * expl : CLabyrinthe lab (10,25) gives us new BufferedImage(51, 21)
		 */
		BufferedImage Labimage = new BufferedImage(85, 159, BufferedImage.TYPE_3BYTE_BGR);
		int compt = 0;
		// wall RGB
		int rw = 0;
		int gw = 0;
		int bw = 0;
		// not wall RGB
		int r = 255;
		int g = 255;
		int b = 255;
		// Entrance to exit path RGB
		int rc = 195;
		int gc = 0;
		int bc = 195;
		int vide = (r << 16) | (g << 8) | b;
		int chemin = (rc << 16) | (gc << 8) | bc;
		int mur = (rw << 16) | (gw << 8) | bw;
		try {
			BufferedReader in = new BufferedReader(new FileReader("mazehere/text.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				//System.out.println(line);
				for(int i = 0, n = line.length(); i < n; i++){
					if(line.charAt(i)== '8')
						Labimage.setRGB(compt, i, mur);
					else if(line.charAt(i)== '.')
						Labimage.setRGB(compt, i, chemin);
					else
						Labimage.setRGB(compt, i, vide);
				}
				++compt;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("fuufuufufufuk");
			e.printStackTrace();
		}
		File f = new File("Maze.png");
		System.out.println("done :)");
		try {
			ImageIO.write(Labimage, "PNG", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
