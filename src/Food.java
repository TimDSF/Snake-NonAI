import java.awt.*;

public class Food {
    public int xPos, yPos;

    public Food(Snake s){
        StdDraw.setPenColor(Color.RED);
        yPos = (int)(Math.random()*GameLauncher.size);
        xPos = (int)(Math.random()*GameLauncher.size);
        StdDraw.filledCircle(xPos,yPos,0.30);
    }

    public void update(Snake s){
        while(s.covered(xPos,yPos)){
            yPos = (int)(Math.random()*GameLauncher.size);
            xPos = (int)(Math.random()*GameLauncher.size);
        }

        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(xPos,yPos,0.30);
    }
}
