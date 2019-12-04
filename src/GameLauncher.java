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

                for (int ct = 0; ct < 15; ct++) {
                    sp = sp || StdDraw.isKeyPressed(KeyEvent.VK_SPACE);
                    at = at || StdDraw.isKeyPressed(KeyEvent.VK_A);

                    StdDraw.show(bst ? (auto ? 0 : 3) : ((auto ? 5 : 15)));

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


                if (s.size == size*size - 1) break;

                StdDraw.clear();

                s.update(f,true);
                f.update(s);
                t.update(auto,s.size);

                StdDraw.show();
            }

            if (s.size == size*size - 1){
                t.win();
            }else{
                t.dead();
            }
        }
    }
}
