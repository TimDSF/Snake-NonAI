import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class Snake {

    public int xPos;
    public int yPos;
    public int size;
    public Vector<Integer> x = new Vector<Integer>();
    public Vector<Integer> y = new Vector<Integer>();
    public int dx;
    public int dy;

    public boolean finished;
    public Queue<Integer> dxs = new LinkedList<Integer>();
    public Queue<Integer> dys = new LinkedList<Integer>();

    public boolean tR = false, tL = false;

    public Snake() {
        size = 3;

        xPos = (GameLauncher.size-3);
        yPos = (int) (Math.random() * (GameLauncher.size));

        x.add(xPos);
        x.add(xPos + 1);
        x.add(xPos + 2);

        y.add(yPos);
        y.add(yPos);
        y.add(yPos);

        dx = -1;
        dy = 0;
    }

    public Snake(Snake s){
        size = s.size;
        xPos = s.xPos;
        yPos = s.yPos;
        dx = s.dx;
        dy = s.dy;

        x.addAll(s.x);
        y.addAll(s.y);

        finished = s.finished;
        dxs.addAll(s.dxs);
        dys.addAll(s.dys);
    }

    public void autoTurnBFS(Food f, Text t){
        if (!finished){
            dxs.clear();
            dys.clear();

            Snake u = null, v = null;

            Queue<Snake> q = new LinkedList<Snake>();
            q.add(this);

            HashSet<Integer> hs = new HashSet<Integer>();
            hs.add(xPos * GameLauncher.size*2 + yPos);
            while(!q.isEmpty()){
                u = q.peek();
                q.remove();

                v = new Snake(u);
                v.update(f,false);
                if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                    v.dxs.add(v.dx);
                    v.dys.add(v.dy);
                    v.tL = false;
                    v.tR = false;
                    q.add(v);
                    if (v.xPos == f.xPos && v.yPos == f.yPos){
                        finished = true;
                        break;
                    }
                }
                if (u.dy == 1){
                    v = new Snake(u);
                    v.turn(false, false, false, true);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = false;
                        v.tR = true;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }

                    v = new Snake(u);
                    v.turn(false, false, true, false);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = true;
                        v.tR = false;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }
                }else if (u.dy == -1){
                    v = new Snake(u);
                    v.turn(false, false, true, false);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = false;
                        v.tR = true;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }

                    v = new Snake(u);
                    v.turn(false, false, false, true);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = true;
                        v.tR = false;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }
                }else if (u.dx == 1){
                    v = new Snake(u);
                    v.turn(false, true, false, false);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = false;
                        v.tR = true;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }

                    v = new Snake(u);
                    v.turn(true, false, false, false);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = true;
                        v.tR = false;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }
                }else if (u.dx == -1){
                    v = new Snake(u);
                    v.turn(true, false, false, false);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = false;
                        v.tR = true;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }

                    v = new Snake(u);
                    v.turn(false, true, false, false);
                    v.update(f,false);
                    if (!v.dead() && hs.add(v.xPos * GameLauncher.size*2 + v.yPos)){
                        v.dxs.add(v.dx);
                        v.dys.add(v.dy);
                        v.tL = true;
                        v.tR = false;
                        q.add(v);
                        if (v.xPos == f.xPos && v.yPos == f.yPos){
                            finished = true;
                            break;
                        }
                    }
                }
            }
            if (!finished){
                t.greedy = true;
//                this.avoidTail();
//                this.autoTurnGreedy(dy == 1, dy == -1, dx == -1, dx == 1, true);
                this.autoTurnGreedy(this.y.get(size-1)<this.yPos, this.y.get(size-1)>this.yPos, this.x.get(size-1)>this.xPos, this.x.get(size-1)<this.xPos, f,true);
            }else{
                if (v.survive(f)){
                    dxs.addAll(v.dxs);
                    dys.addAll(v.dys);
                }else{
                    finished = false;
                    t.greedy = true;
//                    this.autoTurnGreedy(dy == 1, dy == -1, dx == -1, dx == 1, true);
                    this.autoTurnGreedy(this.y.get(size-1)<this.yPos, this.y.get(size-1)>this.yPos, this.x.get(size-1)>this.xPos, this.x.get(size-1)<this.xPos, f, true      );

//                    this.avoidTail();
//                    System.out.println("Trapped after Food! Avoiding tail: " + (size-3));
                }
            }
        }
        if (finished){
            dx = dxs.peek();
            dxs.remove();
            dy = dys.peek();
            dys.remove();
            if (dxs.isEmpty()) finished = false;
        }
    }

    public void autoTurnGreedy(boolean up, boolean dn, boolean lt, boolean rt, Food f,boolean survival){

        Snake sU = new Snake(this);
        Snake sD = new Snake(this);
        Snake sL = new Snake(this);
        Snake sR = new Snake(this);

        sU.turn(true, false, false, false);
        sD.turn(false, true, false, false);
        sL.turn(false, false, true, false);
        sR.turn(false, false, false, true);

        sU.move(f);
        sD.move(f);
        sL.move(f);
        sR.move(f);

        boolean mU = !coveredNoTail(xPos,yPos+1) && !edged(xPos, yPos+1) && (!survival || sU.survive(f)),
                mD = !coveredNoTail(xPos,yPos-1) && !edged(xPos, yPos-1) && (!survival || sD.survive(f)),
                mL = !coveredNoTail(xPos-1,yPos) && !edged(xPos-1, yPos) && (!survival || sL.survive(f)),
                mR = !coveredNoTail(xPos+1,yPos) && !edged(xPos+1, yPos) && (!survival || sR.survive(f));

//        if (survival) System.out.println(mU + " " + mD + " " + mL + " " + mR);

        int xx = xPos + dx, yy = yPos + dy;

        if (coveredNoTail(xx,yy)){
            for (int i = 0; i < size-1 ; ++ i){
                if (x.get(i) == xx && y.get(i) == yy){
                    if (dx == x.get(i+1)-x.get(i) && dy == y.get(i+1)-y.get(i)){
                        dx = x.get(i)-x.get(i-1);
                        dy = y.get(i)-y.get(i-1);
                    }else{
                        dx = x.get(i+1)-x.get(i);
                        dy = y.get(i+1)-y.get(i);
                    }
                    break;
                }
            }
        }else if (!mU && !mD && !mL && !mR) {
            return;
        }else if (mU && !mD && !mL && !mR){
            turnU();
        }else if (!mU && mD && !mL && !mR){
            turnD();
        }else if (!mU && !mD && mL && !mR){
            turnL();
        }else if (!mU && !mD && !mL && mR){
            turnR();
        }else if (dx == 1){
            if (rt){
                if (mR){
                    turnR();
                }else if (mD){
                    turnD();
                }else if (mU){
                    turnU();
                }
            }else if (dn) {
                if (mD){
                    turnD();
                }else if (mR){
                    turnR();
                }else if (mU){
                    turnU();
                }
            }else if (up){
                if (mU){
                    turnU();
                }else if (mR){
                    turnR();
                }else if (mD){
                    turnD();
                }
            }else if (lt){
                if (dn && mD){
                    turnD();;
                }else if (up && mU){
                    turnU();
                }else if (mD){
                    turnD();
                }else if (mU){
                    turnU();
                }
            }
        }else if (dx == -1){
            if (lt){
                if (mL){
                    turnL();
                }else if (mU){
                    turnU();
                }else if (mD){
                    turnD();
                }
            }else if (up){
                if (mU){
                    turnU();
                }else if (mL){
                    turnL();
                }else if (mD){
                    turnD();
                }
            }else if (dn){
                if (mD){
                    turnD();
                }else if (mL){
                    turnL();
                }else if (mU) {
                    turnR();
                }
            }else if (rt){
                if (up && mU){
                    turnU();
                }else if (dn && mD){
                    turnD();;
                }else if (mU){
                    turnU();
                }else if (mD){
                    turnD();
                }
            }
        }else if (dy == 1){
            if (up){
                if (mU){
                    turnU();
                }else if (mR){
                    turnR();
                }else if (mL){
                    turnL();
                }
            }else if (rt){
                if (mR){
                    turnR();
                }else if (mU){
                    turnU();
                }else if (mL){
                    turnL();
                }
            }else if (lt){
                if (mL){
                    turnL();
                }else if (mU){
                    turnU();
                }else if (mR)
                    turnR();
            }else if (dn){
                if (rt && mR){
                    turnR();;
                }else if (lt && mL){
                    turnL();
                }else if (mR){
                    turnR();
                }else if (mL){
                    turnL();
                }
            }
        }else if (dy == -1){
            if (dn){
                if (mD){
                    turnD();
                }else if (mL){
                    turnL();
                }else if (mR){
                    turnR();
                }
            }else if (rt){
                if (mR){
                    turnR();
                }else if (mD){
                    turnD();
                }else if (mL){
                    turnL();
                }
            }else if (lt) {
                if (mL) {
                    turnL();
                }else if (mD) {
                    turnD();
                }else if (mR){
                    turnR();
                }
            }else if (up){
                if (lt && mL){
                    turnL();
                }else if (rt && mR){
                    turnR();;
                }else if (mL){
                    turnL();
                }else if (mR){
                    turnR();
                }
            }
        }
    }

    public void autoPlay(Food f, Text t, boolean BFS){
        if (BFS) {
            t.greedy = false;
            autoTurnBFS(f,t);
        }else{
            t.greedy = true;
            autoTurnGreedy(yPos<f.yPos,yPos>f.yPos,xPos>f.xPos,xPos<f.xPos, f, false);
        }
    }

    public void turnU(){
        dx = 0;
        dy = 1;

    }

    public void turnD(){
        dx = 0;
        dy = -1;
    }

    public void turnL(){
        dx = -1;
        dy = 0;
    }

    public void turnR() {
        dx = 1;
        dy = 0;
    }

    public void turn(boolean up, boolean dn, boolean lt, boolean rt){
        if (up && dy == 0){
            dx = 0;
            dy = 1;
        }
        else if (dn && dy == 0){
            dx = 0;
            dy = -1;
        }
        else if (lt && dx == 0){
            dx = -1;
            dy = 0;
        }
        else if (rt && dx == 0){
            dx = 1;
            dy = 0;
        }
    }

    public void move(Food f){
        xPos += dx;
        yPos += dy;

        x.insertElementAt(xPos,0);
        y.insertElementAt(yPos,0);

        if (f!= null && xPos == f.xPos && yPos == f.yPos){
            size ++;
        }else{
            x.remove(size);
            y.remove(size);
        }
    }

    public void display(){
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledSquare(GameLauncher.size/2.0-0.5,GameLauncher.size/2.0-0.5,GameLauncher.size/2.0+1);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledSquare(GameLauncher.size/2.0-0.5,GameLauncher.size/2.0-0.5,GameLauncher.size/2.0);

        int i, xp, xx, xn, yp, yy, yn;
        for(i = 1; i < size-1; i ++){

            // body
            StdDraw.setPenColor(new Color(i*200/size,255,i*200/size));
            xp=x.get(i-1);
            xx=x.get(i);
            xn=x.get(i+1);
            yp=y.get(i-1);
            yy=y.get(i);
            yn=y.get(i+1);
            if (xp + 1 == xx && yy + 1 == yn || xn + 1 == xx && yy + 1 == yp){
                StdDraw.filledPolygon(  new double[]{xx, xx+0.45, xx+0.45, xx-0.45, xx-0.45, xx     },
                                        new double[]{yy, yy     , yy+0.45, yy+0.45, yy-0.45, yy-0.45});
                StdDraw.filledCircle(xx,yy,0.45);
            }else if (xp - 1 == xx && yy + 1 == yn || xn - 1 == xx && yy + 1 == yp){
                StdDraw.filledPolygon(  new double[]{xx, xx-0.45, xx-0.45, xx+0.45, xx+0.45, xx     },
                                        new double[]{yy, yy     , yy+0.45, yy+0.45, yy-0.45, yy-0.45});
                StdDraw.filledCircle(xx,yy,0.45);
            }else if (xp + 1 == xx && yy - 1 == yn || xn + 1 == xx && yy - 1 == yp){
                StdDraw.filledPolygon(  new double[]{xx, xx     , xx-0.45, xx-0.45, xx+0.45, xx+0.45},
                                        new double[]{yy, yy+0.45, yy+0.45, yy-0.45, yy-0.45, yy     });
                StdDraw.filledCircle(xx,yy,0.45);
            }else if (xp - 1 == xx && yy - 1 == yn || xn - 1 == xx && yy - 1 == yp){
                StdDraw.filledPolygon(  new double[]{xx, xx-0.45, xx-0.45, xx+0.45, xx+0.45, xx     },
                                        new double[]{yy, yy     , yy-0.45, yy-0.45, yy+0.45, yy+0.45});
                StdDraw.filledCircle(xx,yy,0.45);
            }else{
                StdDraw.filledSquare(x.get(i),y.get(i),0.45);
            }
            // body line
            StdDraw.setPenColor(new Color(100,i*220/size,255-i*100/size));
            StdDraw.line(x.get(i-1),y.get(i-1),x.get(i),y.get(i));
        }

        xp=x.get(i-1);
        xx=x.get(i);
        yp=y.get(i-1);
        yy=y.get(i);

        // tail line
        StdDraw.setPenColor(new Color(100,i*220/size,255-i*100/size));
        StdDraw.line(x.get(i-1),y.get(i-1),x.get(i),y.get(i));
        // tail
        if (xp + 1 == xx) {
            StdDraw.filledPolygon(   new double[]{xx-0.45, xx-0.45, xx+0.40, xx+0.40},
                                     new double[]{yy-0.40, yy+0.40, yy+0.25, yy-0.25});
        }else if (xp - 1 == xx) {
            StdDraw.filledPolygon(   new double[]{xx+0.45, xx+0.45, xx-0.40, xx-0.40},
                                     new double[]{yy-0.40, yy+0.40, yy+0.25, yy-0.25});
        }else if (yp + 1 == yy){
            StdDraw.filledPolygon(   new double[]{xx-0.40, xx+0.40, xx+0.25, xx-0.25},
                                     new double[]{yy-0.45, yy-0.45, yy+0.40, yy+0.40});
        }else if (yp - 1 == yy){
            StdDraw.filledPolygon(   new double[]{xx-0.40, xx+0.40, xx+0.25, xx-0.25},
                                     new double[]{yy+0.45, yy+0.45, yy-0.40, yy-0.40});
        }

        // head
        StdDraw.setPenColor(new Color(100,0,255));
             if (dx == 1) StdDraw.filledPolygon(    new double[]{xPos-0.45, xPos-0.45, xPos+0.45, xPos+0.45},
                                                    new double[]{yPos-0.45, yPos+0.45, yPos+0.30, yPos-0.30});
        else if (dx == -1) StdDraw.filledPolygon(   new double[]{xPos+0.45, xPos+0.45, xPos-0.45, xPos-0.45},
                                                    new double[]{yPos-0.45, yPos+0.45, yPos+0.30, yPos-0.30});
        else if (dy == 1) StdDraw.filledPolygon(    new double[]{xPos-0.45, xPos+0.45, xPos+0.30, xPos-0.30},
                                                    new double[]{yPos-0.45, yPos-0.45, yPos+0.45, yPos+0.45});
        else if (dy == -1) StdDraw.filledPolygon(   new double[]{xPos-0.45, xPos+0.45, xPos+0.30, xPos-0.30},
                                                    new double[]{yPos+0.45, yPos+0.45, yPos-0.45, yPos-0.45});
    }

    public void update(Food f, boolean display){
        move(f);
        if (display) display();
    }

    public boolean survive(Food f){
        Snake t = new Snake(this);
        HashSet<Integer> hs = new HashSet<Integer>();
        hs.add(t.x.get(t.size-1)*GameLauncher.size*2 + t.y.get(t.size-1));

        for (int i=0; i<size-1; i++){
//            t.autoTurnGreedy(dy == 1, dy == -1, dx == -1,dx == 1, false);
            t.autoTurnGreedy(t.y.get(size-1)>t.yPos, t.y.get(size-1)<t.yPos, t.x.get(size-1)<t.xPos,t.x.get(size-1)>t.xPos, f,false);
            t.move(f);
            hs.add(t.x.get(t.size-1)*GameLauncher.size*2 + t.y.get(t.size-1));
            if (t.dead()) return false;
            if (hs.contains(t.xPos * GameLauncher.size*2 + t.yPos)) return true;
        }
        return true;
    }

    public boolean dead(){
        if (coveredNoHead(xPos,yPos) || edged(xPos,yPos)) return true;
        return false;
    }

    public boolean covered(int xx, int yy){
        for (int i = 0; i < size ; i ++){
            if (x.get(i) == xx && y.get(i) == yy) return true;
        }
        return false;
    }

    public boolean coveredNoHead(int xx, int yy){
        for (int i = 1; i < size ; i ++){
            if (x.get(i) == xx && y.get(i) == yy) return true;
        }
        return false;
    }

    public boolean coveredNoTail(int xx, int yy){
        for (int i = 0; i < size - 1 ; i ++){
            if (x.get(i) == xx && y.get(i) == yy) return true;
        }
        return false;
    }

    public boolean edged(int xx, int yy){
        if (xx < 0 || xx >= GameLauncher.size || yy < 0 || yy >= GameLauncher.size) return true;
        return false;
    }
}