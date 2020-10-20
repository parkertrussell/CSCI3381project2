package project2package;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame mainPanel = new JFrame();
		
		
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setLayout(new BorderLayout());

        PushCounterPanel test = new PushCounterPanel();
        mainPanel.add(test, BorderLayout.CENTER);

        mainPanel.setSize(500, 500);
        mainPanel.setVisible(true);

	}
	
}
