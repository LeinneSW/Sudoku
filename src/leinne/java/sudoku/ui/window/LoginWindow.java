package leinne.java.sudoku.ui.window;

import leinne.java.sudoku.SudokuSystem;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends Window{

    public LoginWindow(){
        var panel = getContentPane();
        panel.setLayout(new FlowLayout());

        var lblId = new JLabel("아이디: ");
        panel.add(lblId);
        var txtId = new JTextField(10);
        panel.add(txtId);
        var lblPass = new JLabel("비밀번호 ");
        panel.add(lblPass);
        var txtPass = new JPasswordField(10);
        panel.add(txtPass);

        var btnLogin = new JButton("로그인");
        // TODO: login system with db
        btnLogin.addActionListener(e -> SudokuSystem.getInstance().getWindowManager().setCurrentWindow(new SelectProblemWindow()));
        panel.add(btnLogin);

        var btnRegister = new JButton("회원가입");
        btnRegister.addActionListener(e -> SudokuSystem.getInstance().getWindowManager().setCurrentWindow(new RegisterWindow()));
        panel.add(btnRegister);
    }
}
