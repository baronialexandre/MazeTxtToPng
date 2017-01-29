import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MazeTextToPng {

	public static void main(String[] args) {
		/*
		List<String> vargs = Arrays.asList(args);
		System.out.println(vargs);
		*/
		// init
		
		int x = 1;
		int y = 1;
		
		int base = 16; // #xxyyzz colors are in base 16
		
		String mazeInName = "text";
		String mazeOutName = "maze";
		String exception = "fuufuufufufuk";
		String ok = "done :)";
		
		char wallch = '8';
		char pathch = '.';
		
		String floorc = "ffffff"; //black
		String pathc = "ff0000"; //red
		String wallc = "000000"; //white

		// read args
		
		for (int i = 0;i<args.length;i+=2) {
			String command = args[i];
			switch (command) {
			case "-in": mazeInName = args[i+1]; break;
			case "-out": mazeOutName = args[i+1]; break;
			
			case "-wall": wallch = args[i+1].charAt(0); break;
			case "-path": pathch = args[i+1].charAt(0); break;
			
			case "-floorc": floorc = args[i+1]; break;
			case "-pathc": pathc = args[i+1]; break;
			case "-wallc": wallc = args[i+1]; break;
			}
		}
		
		int floor = Integer.parseInt(floorc, base);
		int path = Integer.parseInt(pathc, base);
		int wall = Integer.parseInt(wallc, base);

		//kebab code begins
		
		int compt = 0;
		BufferedImage Labimage = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(mazeInName+".txt"));
			x = in.readLine().length();
			y = (int) in.lines().count()+1;
			in.close();
			/*System.out.println(x);
			System.out.println(y);*/
			
			in = new BufferedReader(new FileReader(mazeInName+".txt"));
			Labimage = new BufferedImage(x, y, BufferedImage.TYPE_3BYTE_BGR);
			String line;
			while ((line = in.readLine()) != null) {
				//System.out.println(line);
				for(int i = 0, n = line.length(); i < n; i++){
					if(line.charAt(i)== wallch)
						Labimage.setRGB(i, compt, wall);
					else if(line.charAt(i)== pathch)
						Labimage.setRGB(i, compt, path);
					else
						Labimage.setRGB(i, compt, floor);
				}
				++compt;
			}
			in.close();
		} catch (IOException e) {
			System.out.println(exception);
			e.printStackTrace();
		}
		File f = new File(mazeOutName+".png");
		System.out.println(ok);
		try {
			ImageIO.write(Labimage, "PNG", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
