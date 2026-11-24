package MenuMVC.Game.Utilities;

public class PlayerControls {
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private int velX = 0;
    private int velY = 0;

    public void setUp() {
        movingUp = true;
        updateVelocity();
    }

    public void setDown() {
        movingDown = true;
        updateVelocity();
    }

    public void setLeft() {
        movingLeft = true;
        updateVelocity();
    }

    public void setRight() {
        movingRight = true;
        updateVelocity();
    }

    public void releaseUp() {
        movingUp = false;
        updateVelocity();
    }

    public void releaseDown() {
        movingDown = false;
        updateVelocity();
    }

    public void releaseLeft() {
        movingLeft = false;
        updateVelocity();
    }

    public void releaseRight() {
        movingRight = false;
        updateVelocity();
    }

    private void updateVelocity() {
        velX = (movingRight ? 1 : 0) - (movingLeft ? 1 : 0);
        velY = (movingDown ? 1 : 0) - (movingUp ? 1 : 0);
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }
}
