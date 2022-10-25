import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL implements KeyListener {
    private boolean keyPressed[] = new boolean[128];

    @Override
    public void keyTyped(KeyEvent keyEevent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEevent) {
        keyPressed[keyEevent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEevent) {
        keyPressed[keyEevent.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode) {
        return keyPressed[keyCode];
    }
}
