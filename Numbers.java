import java.io.IOException;

import jcurses.system.CharColor;
import jcurses.system.Toolkit;
import jcurses.widgets.*;
import jcurses.widgets.DefaultLayoutManager;
import jcurses.widgets.Window;

public class Numbers {
        public static void main(String[] args) {

            Window w = new Window(40, 20, true, "Numbers");
            DefaultLayoutManager mgr = new DefaultLayoutManager();
            mgr.bindToContainer(w.getRootPanel());

            CharColor color = new CharColor(CharColor.WHITE, CharColor.GREEN);

            w.show();

            int num = 0;

            while ( ! w.isClosed() ) {
                Toolkit.printString( ""+ num, 45, 17, color);
                num++;
            }
        }
}
