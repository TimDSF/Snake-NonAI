import javax.naming.StringRefAddr;
import javax.naming.spi.StateFactory;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameLauncher {
    public static boolean up = false, dn = false, lt = false, rt = false, sp = false, spp = false, at = false, atp = false, auto = false, bst = false;
    public static int size;

    public static void main(String args[]) {
        ArgsProcessor ap = new ArgsProcessor(args);
        size = ap.nextInt("Input the size of the canvas.");

        Text t = new Text();

        StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
        StdDraw.setScale(0, size-1);
        StdDraw.setPenRadius(0.3/size);

        while(true){
            t.start();

            sp = false;
            while(!sp){
                sp = StdDraw.isKeyPressed(KeyEvent.VK_S);
//                System.out.println("STUPID"); // FIXME: Need to keep this for no reason
                double i = Math.random();
            }
            StdDraw.clear();

            Snake s = new Snake();
            Food f = new Food(s);

            while (!s.dead()) {
                up = false;
                dn = false;
                lt = false;
                rt = false;
                sp = false;
                at = false;

                for (int ct = (auto && bst?14:0); ct < 15; ct++) {
                    sp = sp || StdDraw.isKeyPressed(KeyEvent.VK_SPACE);
                    at = at || StdDraw.isKeyPressed(KeyEvent.VK_A);

                    StdDraw.show((bst || sp) ? (auto ? 0 : 5) : ((auto ? 3 : 15)));

                    up = up || StdDraw.isKeyPressed(KeyEvent.VK_UP);
                    dn = dn || StdDraw.isKeyPressed(KeyEvent.VK_DOWN);
                    lt = lt || StdDraw.isKeyPressed(KeyEvent.VK_LEFT);
                    rt = rt || StdDraw.isKeyPressed(KeyEvent.VK_RIGHT);
                }

                if (!atp && at) auto=!auto;

                if (auto){
                    if (!spp && sp) bst=!bst;
                }else{
                    bst = sp;
                }

                atp = at;
                spp = sp;

                if (auto){
                    s.autoPlay(f, t,true);
                }else{
                    s.turn(up, dn, lt, rt);
                    s.finished = false;
                }

                StdDraw.clear();

                s.update(f,true);
                t.update(auto,s.size);
                if (s.size == size*size) break;

                f.update(s);

                StdDraw.show();
            }

            if (s.size == size*size){
                t.win();
            }else{
                t.dead();
            }
        }
    }
}
