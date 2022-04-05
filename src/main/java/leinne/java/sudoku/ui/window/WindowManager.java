package leinne.java.sudoku.ui.window;

import java.awt.*;

public class WindowManager{

    private Window currentWindow = null;
    private final Dimension windowSize = new Dimension(520, 600);

    public Window getCurrentWindow(){
        return currentWindow;
    }

    public void setCurrentWindow(Window window){
        if(currentWindow == window){
            return;
        }

        window.setSize(windowSize);
        window.setLocationRelativeTo(currentWindow);
        if(currentWindow != null){
            currentWindow.close();
        }
        currentWindow = window;
        window.open();
    }

    public Dimension getWindowSize(){
        return windowSize.getSize();
    }

    public void setWindowSize(Dimension windowSize){
        this.windowSize.setSize(windowSize);
        if(currentWindow != null){
            currentWindow.setSize(this.windowSize);
        }
    }
}
