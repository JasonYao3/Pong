public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;

    // velocity x, y
    private double vy = 400.0;
    private double vx = -150.0;
    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public double calculateNewVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height) /2.0) - (this.rect.y + (this.rect.height / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {

        if (vx < 0) { // left
            if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x + this.rect.width >= this.leftPaddle.x &&
                this.rect.y >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                double theta = calculateNewVelocityAngle(leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;
            } else if (this.rect.x + this.rect.width < this.leftPaddle.x) {
                System.out.println("You lost");
            }
        } else if (vx > 0)  { // right
            if (this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x + this.rightPaddle.width &&
                    this.rect.y >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height) {
                double theta = calculateNewVelocityAngle(rightPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;
            } else if (this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width) {
                System.out.println("AI has lost a point");
            }
        }

        if (vy > 0) { // down
            if (this.rect.y + this.rect.height > Constants.SCREEN_HEIGHT) {
                this.vy *= -1;
            }
        } else if (vy < 0) { // up
            if (this.rect.y < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        // position = position + velocity
        // velocity = velocity + acceleration
        this.rect.x += vx * dt;
        this.rect.y += vy * dt;
    }
}
