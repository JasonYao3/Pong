public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    // velocity x, y
    private double vy = 10.0;
    private double vx = -150.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
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
                int rightScore = Integer.parseInt(rightScoreText.text);
                rightScore++;
                rightScoreText.text = "" + rightScore;
                this.rect.x = Constants.SCREEN_WIDTH / 2.0;
                this.rect.y = Constants.SCREEN_HEIGHT / 2.0;
                this.vy = 10.0;
                this.vx = -150.0;
                if (rightScore >= Constants.WIN_SCORE) {
                    System.out.println("right WON");
                }
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
                int leftScore = Integer.parseInt(leftScoreText.text);
                leftScore++;
                leftScoreText.text = "" + leftScore;
                this.rect.x = Constants.SCREEN_WIDTH / 2.0;
                this.rect.y = Constants.SCREEN_HEIGHT / 2.0;
                this.vy = 10.0;
                this.vx = -150.0;
                if (leftScore >= Constants.WIN_SCORE) {
                    System.out.println("left WON");
                }
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
