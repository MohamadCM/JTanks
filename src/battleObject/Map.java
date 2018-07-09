package battleObject;

import bufferstrategy.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class create and design map of the game .
 *
 * @author Azhdari Muhammad
 * @since summer 2018
 * @version 1.0
 *
 */

public class Map {

    private int[][] mapResource ;
    private GameState state ;
    private Graphics2D g2d ;
    private BufferedImage wall ;
    private BufferedImage teazel ;
    private BufferedImage area ;
    private BufferedImage plant ;
    private BufferedImage hardWall ;
    public static int xOffset ;
    public static int yOffset ;


    public Map ( GameState state , Graphics2D g2d ) throws IOException {

        mapResource = new int[30][15] ;
        this.state = state ;
        this.g2d = g2d ;
        wall = ImageIO.read(new File("Resources/Images/softWall.png")) ;
        teazel = ImageIO.read( new File("Resources/Images/teazel.png")) ;
        area = ImageIO.read( new File("Resources/Images/RedEarth.png")) ;
        plant = ImageIO.read( new File("Resources/Images/plant.png")) ;
        hardWall = ImageIO.read( new File("Resources/Images/hardWall.png")) ;
        initializeMap();

    }

    /**
     * The Method the paint the map from resource array .
     */

    public void paintMap () {



        for (int i = 0; i < 30 ; i ++ ) {
            for (int j = 0 ; j < 15 ; j ++ ) {

//                System.out.printf("(%d , %d)\n" , i * 100 + xOffset , j * 100 + yOffset);

                // avoiding out of range of xOffset and YOffset :
                if (xOffset > 0 )  xOffset = 0 ;
                if (yOffset > 0 )  yOffset = 0 ;
                if ( yOffset + 1500 < 600 )  yOffset = -900 ;
                if ( xOffset + 3000 < 1200 )  xOffset = - 1800 ;


                if (mapResource[i][j] == 2) {
                    g2d.drawImage(wall, null, i * 100 + xOffset, j * 100 + yOffset);
                }else if (mapResource[i][j] == 1) {
                    g2d.drawImage(hardWall, null, i * 100 + xOffset, j * 100 + yOffset);
                }
                else if (mapResource[i][j] == 5) {
                    g2d.drawImage(teazel, null, i * 100 + xOffset, j * 100 + yOffset);
                } else if ( mapResource[i][j] == 4) {
                    g2d.drawImage(plant, null, i * 100 + xOffset, j * 100 + yOffset);
                }
                // enemy tank ...
                else   {
                    g2d.drawImage(area, null, i * 100 + xOffset, j * 100 + yOffset);
                }
            }

        }

    }

    private void initializeMap () {

        try {
            File map = new File("Resources/Save/Map.txt") ;
            FileReader fileReader = new FileReader(map) ;
            BufferedReader bufferedReader = new BufferedReader(fileReader) ;
            String line ;
            int j = 0 ;

            while ((line = bufferedReader.readLine()) != null) {

                for (int i = 0 ; i < line.length() ; i ++ ) {
//                    System.out.printf("(%d , %d) \n" , i , j);
                    mapResource[i][j] = Character.getNumericValue(line.charAt(i)) ;
                }

                j++ ;

            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}