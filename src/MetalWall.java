import java.awt.*;

public class MetalWall {
    public static final int width = 36;
    public static final int height = 37;
    int x;
    int y;
    TankClient tankClient;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] imgs = null;
    static {
        imgs = new Image[]{
                toolkit.getImage(MetalWall.class.getClassLoader().getResource("Images/metalWall.gif")),
        };
    }


    public MetalWall(int x, int y, TankClient tankClient) {
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
