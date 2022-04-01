package leinne.java.sudoku.ui.window;

public class WindowManager{

    private static final WindowManager instance = new WindowManager();

    public static WindowManager getInstance(){
        return instance;
    }

    private WindowManager(){}

    private Window currentWindow = null;

    public Window getCurrentWindow(){
        return currentWindow;
    }

    public void setCurrentWindow(Window window){
        if(currentWindow == window){
            return;
        }

        window.open();
        if(currentWindow != null){
            currentWindow.close();
        }
        currentWindow = window;
    }
}
