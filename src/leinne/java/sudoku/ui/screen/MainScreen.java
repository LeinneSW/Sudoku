package leinne.java.sudoku.ui.screen;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame{

    public MainScreen(){
        super("스도쿠");
        var panel = getContentPane();
        panel.setLayout(new FlowLayout());

        var lblId = new JLabel("아이디: ");
        panel.add(lblId);
        var txtId = new JTextField(10);
        panel.add(txtId);
        var lblPass = new JLabel("비밀번호 ");
        panel.add(lblPass);
        var txtPass = new JTextField(10);
        panel.add(txtPass);

        var button = new JButton("게임 시작");
        button.addActionListener(e -> {
            setVisible(false);
            var frame = new SudokuGameScreen();
            frame.setProblem("000806010060000000809000000010000000000050090050000001000000007000009000320001000");
            frame.setVisible(true);
        });
        panel.add(button);

        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
