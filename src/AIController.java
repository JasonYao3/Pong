public class AIController {
    public PlayerConstroller playerConstroller;
    public Rect ball;

    public AIController(PlayerConstroller plrConstroller, Rect ball) {
        this.playerConstroller = plrConstroller;
        this.ball = ball;
    }

    public void update(double dt) {
        playerConstroller.update(dt);

        if (ball.y < playerConstroller.rect.y) {
            playerConstroller.moveUp(dt);
        } else if (ball.y + ball.height > playerConstroller.rect.y + playerConstroller.rect.height) {
            playerConstroller.moveDown(dt);
        }
    }
}
