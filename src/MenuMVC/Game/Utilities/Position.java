package MenuMVC.Game.Utilities;

public class Position{
	private double x;
	private double y;

	public Position(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setPosition(double dx, double dy) {
		x += dx;
		y += dy;
	}

}