package leinne.java.sudoku.ui.window;

public class WindowManager{

    private static final WindowManager instance = new WindowManager();

    public static WindowManager getInstance(){
        return instance;
    }

    private WindowManager(){}

    private final LoginWindow loginWindow = new LoginWindow();
    private final InGameWindow inGameWindow = new InGameWindow();
    private final SelectProblemWindow selectProblemWindow = new SelectProblemWindow();

    public LoginWindow getLoginWindow(){
        return loginWindow;
    }

    public InGameWindow getInGameWindow(){
        return inGameWindow;
    }

    public SelectProblemWindow getSelectProblemWindow(){
        return selectProblemWindow;
    }
}
