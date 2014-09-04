package no.Strohm.game2D.state;

import no.Strohm.game2D.InputHandler;
import no.Strohm.game2D.graphics.Screen;

/**
 * Created by Ole on 14/12/13.
 */
public abstract class StateMenu extends State {

    int pressedTimer = 10;
    protected int anim = 0;
    protected int selected = 0;
    protected final int selectionCount;
    private int colInterval = 46;
    int col1 = 0x5DF24F;
    int col2 = 0x14CC00;

    public StateMenu(int selectionCount, int id, InputHandler input) {
        super(id, input);
        this.selectionCount = selectionCount;
    }

    public int getColor(int sel) {
        return (selected == sel && anim % colInterval < colInterval / 2 ? col2 : col1);
    }

    public void tick() {
        if (pressedTimer > 0) pressedTimer--;
        int xm = 0;
        if (input.up) xm--;
        else if (input.down) xm++;
        if (xm != 0) moveSelection(xm);

        if (input.enter && pressedTimer <= 0) {
            pressedTimer = 30;
            int state = getCurStateID();
            press();
            if (getCurStateID() != state) {
                selected = 0;
            }
        }

        if (anim < 7500) anim++;
        else anim = 0;
    }

    private void moveSelection(int xm) {
        if (pressedTimer > 0) return;
        selected += xm;
        if (selected < 0) selected += selectionCount;
        if (selected >= selectionCount) selected -= selectionCount;
        pressedTimer += 10;
    }

    protected abstract void press();

    public abstract void render(Screen screen);
}
