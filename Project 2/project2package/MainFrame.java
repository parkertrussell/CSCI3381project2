package project2package;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Main Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);

		
		frame.getContentPane().add(new MainPanel());
		
		frame.pack();
		frame.setVisible(true);
	}
}