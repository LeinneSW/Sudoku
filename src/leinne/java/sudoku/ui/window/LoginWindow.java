package leinne.java.sudoku.ui.window;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame{

    public LoginWindow(){
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

        var button = new JButton("로그인");
        button.addActionListener(e -> {
            setVisible(false);
            WindowManager.getInstance().getSelectProblemWindow().setVisible(true);
        });
        panel.add(button);

        setSize(500, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
