import java.awt.*;

public class Text {
    public boolean greedy = false;

    public void start(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(GameLauncher.size/2.0-0.5, GameLauncher.size/5.0, "Press Up, Down, Left, Right to Move Snake");
        StdDraw.text(GameLauncher.size/2.0-0.5, GameLauncher.size/10.0, "Press Space to Boost in Game");
        StdDraw.text(GameLauncher.size/2.0-0.5, 0, "Press S to Play, Press A to Auto Play");
    }

    public void update(boolean auto, int score){
        StdDraw.setPenColor(Color.BLACK);
        if (auto){
            StdDraw.textLeft(0,0,"AutoPlay" + (greedy ? " - Greedy" : ""));
        }
        StdDraw.textLeft(0,GameLauncher.size-1, Integer.toString(score));
    }

    public void win(){
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(GameLauncher.size/2.0-0.5, GameLauncher.size/1.5, "YOU ACTUALLY DESTROY THE GAME!!!");
        StdDraw.show();
    }

    public void dead(){
        StdDraw.setPenColor(Color.RED);
        StdDraw.text(GameLauncher.size/2.0-0.5, GameLauncher.size/1.5, "YOU ARE DESTROYED BY THE GAME!!!");
        StdDraw.show();
    }
}
