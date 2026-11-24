package MenuMVC.Game.Utilities;

public class Dimension {
    private Position position;
    private int width;
    private int height;

    public Dimension(Position position, int width, int height){
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public double getX(){
        return position.getX();
    }

    public double getY(){
        return position.getY();
    }

    public void setPosition(double dx, double dy) {
		position.setPosition(dx, dy);
	}
}
