package leinne.java.sudoku.ui.screen;

public class ScreenManager{

    private static final ScreenManager instance = new ScreenManager();

    public static ScreenManager getInstance(){
        return instance;
    }

    private ScreenManager(){}

    private final LoginScreen loginScreen = new LoginScreen();
    private final InGameScreen inGameScreen = new InGameScreen();
    private final SelectProblemScreen selectProblemScreen = new SelectProblemScreen();

    public LoginScreen getLoginScreen(){
        return loginScreen;
    }

    public InGameScreen getInGameScreen(){
        return inGameScreen;
    }

    public SelectProblemScreen getSelectProblemScreen(){
        return selectProblemScreen;
    }
}
