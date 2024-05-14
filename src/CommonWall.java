import java.awt.*;

public class CommonWall {
    public static final int width = 22;
    public static final int height = 21;
    int x;
    int y;
    private TankClient tankClient;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] imgs = null;
    static {
        imgs = new Image[]{
                toolkit.getImage(CommonWall.class.getClassLoader().getResource("Images/commonWall.gif")),
        };
    }
    public CommonWall(int x, int y, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.tankClient = tankClient;
    }
    public void draw(Graphics g){
        g.drawImage(imgs[0], x,y, null );
    }
    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }
}
