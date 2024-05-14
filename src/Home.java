import java.awt.*;

public class Home {
    private int x;
    private int y;
    public static final int width = 36;
    public static final int height = 37;

    private TankClient tankClient;
    private boolean live = true;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] imgs = null;
    static {
        imgs = new Image[]{
                toolkit.getImage(Home.class.getClassLoader().getResource("Images/home.jpg")),
        };
    }

    public Home(int x, int y, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.tankClient = tankClient;
    }

    public void draw(Graphics g){
        if (live) {
            g.drawImage(imgs[0], x, y, null);
            for (int i = 0; i < tankClient.homeWall.size(); i++) {
                CommonWall commonWall = tankClient.homeWall.get(i);
                commonWall.draw(g);
            }
        }else{
            gameOver(g);
        }
    }
    public void gameOver(Graphics g){
        tankClient.tank.clear();
        tankClient.metalWall.clear();
        tankClient.otherWall.clear();
        tankClient.bombTanks.clear();
        tankClient.theRiver.clear();
        tankClient.tree.clear();
        tankClient.bullets.clear();
        tankClient.homeTank1.setLive(false);
        Color color = g.getColor();
        g.setColor(Color.green);
        Font font = g.getFont();
        g.setFont(new Font(" ", Font.PLAIN, 40));
        g.setFont(font);
        g.setColor(color);
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}
