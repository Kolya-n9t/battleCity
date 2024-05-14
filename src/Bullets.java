import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bullets {
    public static int speedX = 12;
    public static int speedY = 12;
    public static final int width = 10;
    public static final int height = 10;
    private int x;
    private int y;
    private Direction direction;
    private boolean good;
    private boolean live = true;
    private TankClient tankClient;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] imgs = null;
    private static Map<String, Image> imgs_m = new HashMap<>();
    static {
        imgs = new Image[]{
                toolkit.getImage(Bullets.class.getClassLoader().getResource("Images/bulletL.gif")),
                toolkit.getImage(Bullets.class.getClassLoader().getResource("Images/bulletU.gif")),
                toolkit.getImage(Bullets.class.getClassLoader().getResource("Images/bulletR.gif")),
                toolkit.getImage(Bullets.class.getClassLoader().getResource("Images/bulletD.gif")),
        };
        imgs_m.put("L", imgs[0]);
        imgs_m.put("U", imgs[1]);
        imgs_m.put("R", imgs[2]);
        imgs_m.put("D", imgs[3]);
    }

    public Bullets(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Bullets(int x, int y, boolean good, Direction direction, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.good = good;
        this.tankClient = tankClient;
    }
    public void move(){
        switch (direction){
            case L -> {x -=speedX; break;}
            case U -> {y -=speedY; break;}
            case D -> {y +=speedY; break;}
            case R -> {x +=speedX; break;}
            case STOP -> {break;}
        }
        if(x < 0 || y < 0 || x > TankClient.frame_width || y > TankClient.frame_height){
            live = false;
        }
    }
    public void draw(Graphics g){
        if (!live){
            tankClient.bullets.remove(this);
            return;
        }
        switch (direction){
            case L -> {g.drawImage(imgs_m.get("L"),x,y,null);}
            case U -> {g.drawImage(imgs_m.get("U"),x,y,null);}
            case D -> {g.drawImage(imgs_m.get("D"),x,y,null);}
            case R -> {g.drawImage(imgs_m.get("R"),x,y,null);}
        }
        move();
    }

    public boolean isGood() {
        return good;
    }

    public boolean isLive(){
        return live;
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
    public boolean hitTanks(java.util.List<Tank> tankList){
        for (int i = 0; i < tankList.size(); i++) {
            if (hitTank(tankList.get(i))){
                return true;
            }
        }
        return false;
    }

    public boolean hitTank(Tank tank){
        if (this.live && this.getRect().intersects(tank.getRect())&&tank.isLive()&&this.good!=tank.isGood()){
            BombTank bombTank = new BombTank(tank.getX(), tank.getY(), tankClient);
            tankClient.bombTanks.add(bombTank);
            if (tank.isGood()){
                tank.setLife(tank.getLife()-50);
                if (tank.getLife()<=0){
                    tank.setLive(false);
                }

            }else{
                tank.setLive(false);
            }
            this.live=false;
            return true;
        }
        return false;
    }
    public boolean hitWall(CommonWall wall){
        if (this.live && this.getRect().intersects(wall.getRect())){
            this.live=false;
            this.tankClient.otherWall.remove(wall);
            this.tankClient.homeWall.remove(wall);
            return true;
        }
        return false;
    }
    public boolean hitWall(MetalWall metalWall){
        if (this.live && this.getRect().intersects(metalWall.getRect())){
            this.live=false;
            return true;
        }
        return false;
    }
    public boolean hitBullet(Bullets bullets){
        if (this.live && this.getRect().intersects(bullets.getRect())){
            this.live=false;
            this.tankClient.bullets.remove(bullets);
            return true;
        }
        return false;
    }
    public boolean hitHome(){
        if (this.live && this.getRect().intersects(tankClient.home.getRect())){
            this.live=false;
            this.tankClient.home.setLive(false);
            return true;
        }
        return false;
    }
}



