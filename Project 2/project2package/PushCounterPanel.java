package project2package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

public class PushCounterPanel extends JPanel{
	private int count;
	private JButton push;
	private JLabel lblPushes;
	
	public PushCounterPanel() {
		count = 0;
		push = new JButton ("Push Me!");
		push.setBounds(68, 54, 105, 23);
		push.addActionListener(new ButtonListener());
		lblPushes = new JLabel ("Pushes: 0");
		lblPushes.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblPushes.setBounds(214, 80, 105, 23);
		setLayout(null);
		
		add (push);
		add(lblPushes);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(369, 186));
		
		JButton btnClearCount = new JButton("Clear Count");
		btnClearCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count=0;
				lblPushes.setText ("Pushes: "+count);
			}
		});
		btnClearCount.setBounds(68, 101, 105, 23);
		add(btnClearCount);
	}
	
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent event){
			count++;
			lblPushes.setText ("Pushes: "+count);
		}
	}
}
