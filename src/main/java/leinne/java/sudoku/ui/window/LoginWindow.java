package leinne.java.sudoku.ui.window;

import leinne.java.sudoku.SudokuSystem;
import leinne.java.sudoku.ui.component.ClickableLabel;
import leinne.java.sudoku.ui.component.HintPasswordField;
import leinne.java.sudoku.ui.component.HintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow extends Window{

    protected void initDesign(){
        var panel = getContentPane();
        panel.setLayout(null);

        var lblTitle = new JLabel("스도쿠");
        lblTitle.setFont(new Font("NanumGothic", Font.BOLD, 46));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(158, 40, 190, 80);
        panel.add(lblTitle);

        var txtId = new HintTextField("아이디");
        txtId.setFont(new Font("NanumGothic", Font.PLAIN, 17));
        txtId.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                if(txtId.getText().length() > 19) {
                    e.consume();
                }
            }
        });
        txtId.setBounds(60, 155, 386, 45);
        panel.add(txtId);

        var txtPass = new HintPasswordField("비밀번호");
        txtPass.setFont(new Font("NanumGothic", Font.PLAIN, 17));
        txtPass.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                if(txtPass.getPassword().length > 15) {
                    e.consume();
                }
            }
        });
        txtPass.setBounds(60, 215, 386, 45);
        panel.add(txtPass);

        // TODO: auto login
        var chkAutoLogin = new JCheckBox("로그인 상태 유지");
        chkAutoLogin.setFont(new Font("NanumGothic", Font.PLAIN, 16));
        chkAutoLogin.setBounds(57, 260, 200, 40);
        panel.add(chkAutoLogin);

        // TODO: login
        var btnLogin = new JButton("로그인");
        btnLogin.setFont(new Font("NanumGothic", Font.BOLD, 18));
        btnLogin.addActionListener(event -> {
            var system = SudokuSystem.getInstance();
            if(system.getDBManager().checkAccount(txtId.getText(), new String(txtPass.getPassword()))){
                system.getWindowManager().setCurrentWindow(new SelectProblemWindow());
            }else{
                JOptionPane.showConfirmDialog(this, "아이디 혹은 비밀번호를 확인하세요.", "로그인 실패", JOptionPane.DEFAULT_OPTION);
            }
        });
        btnLogin.setBounds(60, 315, 386, 45);
        panel.add(btnLogin);

        var btnFindPass = new ClickableLabel("비밀번호 찾기");
        btnFindPass.setBounds(133, 385, 100, 32);
        btnFindPass.setClickListener((e -> {
            // TODO: find password
            JOptionPane.showConfirmDialog(
                this,
                "아직 구현되지 않은 기능입니다.",
                "준비중",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE
            );
        }));
        panel.add(btnFindPass);

        var lblLine = new JLabel("|");
        lblLine.setForeground(new Color(75, 75, 75));
        lblLine.setFont(new Font("NanumGothic", Font.PLAIN, 16));
        lblLine.setBounds(252, 385, 10, 32);
        panel.add(lblLine);

        var btnRegister = new ClickableLabel("회원가입");
        btnRegister.setClickListener((e -> {
            // TODO: register
            JOptionPane.showConfirmDialog(
                this,
                "아직 구현되지 않은 기능입니다.",
                "준비중",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE
            );
        }));
        btnRegister.setBounds(273, 385, 70, 32);
        panel.add(btnRegister);
    }
}
