package project2package;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import project2package.AllData;
import project2package.Person;

import javax.swing.event.ChangeEvent;

public class MainPanel extends JPanel {
	private final int WIDTH = 800, HEIGHT = 500;

	private int delay;

	private JPanel controlPanel;
	private ReboundPanel imagePanel;
	private JSlider personSlider;
	private JLabel lblInfectedNum;
	private JTextField speedText;

	private AllData myData;
	private JTextArea textArea;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenuItem mntmOpen;
	private JTextField textField;

	//-----------------------------------------------------------------
	//  Sets up the panel, including the timer for the animation.
	//-----------------------------------------------------------------
	public MainPanel()
	{
		super(new BorderLayout());
		delay = 20;

		final String DATA_FILE = "contactData.txt";
		AllData people = new AllData(DATA_FILE);

		//prints initial read to make compare to changed data below
		myData = new AllData(DATA_FILE);
		System.out.println(myData);

		//delete the data:
		//00009,Rachel,Rosenbaum,1986-04-11,5012309273,negative,00001,00010
		//this adds the data back, then outputs to show it in the data. Will NOT save between runs unless
		//user uses the "Save Data" button.
		Person p = new Person("00009","Rachel","Rosenbaum",
				LocalDate.of(1986, 04, 11),"55012309273","negative");
		myData.add(p);

		System.out.println(myData);

		controlPanel = new JPanel();
		controlPanel.setPreferredSize (new Dimension(WIDTH / 2, HEIGHT));
		controlPanel.setBackground (Color.black);
		controlPanel.setLayout(null);
		//   imagePanel = new ReboundPanel(WIDTH/2,HEIGHT);

		setPreferredSize (new Dimension(WIDTH, HEIGHT));
		setBackground (Color.white);
		add(controlPanel, BorderLayout.WEST);

		personSlider = new JSlider();
		personSlider.setValue(1);
		personSlider.setMinorTickSpacing(1);
		personSlider.setMinimum(1);
		personSlider.setMaximum(4);
		personSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				imagePanel.setDelay(personSlider.getValue());
				delay = personSlider.getValue();
				speedText.setText(""+delay);
			}
		});
		personSlider.setPaintTicks(true);
		personSlider.setPaintLabels(true);
		personSlider.setMajorTickSpacing(1);
		personSlider.setBounds(227, 98, 167, 44);
		controlPanel.add(personSlider);

		lblInfectedNum = new JLabel("Num of People:");
		lblInfectedNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInfectedNum.setForeground(Color.WHITE);
		lblInfectedNum.setBounds(10, 87, 133, 24);
		controlPanel.add(lblInfectedNum);

		speedText = new JTextField();
		speedText.setText(""+myData.size());
		speedText.setBounds(131, 87, 86, 20);
		controlPanel.add(speedText);
		speedText.setColumns(10);

		Iterator<String> iter = myData.getIterator();
		ArrayList<String> ids = new ArrayList<String>();
		while (iter.hasNext()) {
			ids.add(iter.next());
		}

		JComboBox idComboBox = new JComboBox();
		idComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idComboBox.getSelectedItem().toString();
				Person p = myData.findPerson(id);
				textArea.setText(p.getfName() + " " + p.getlName());
			}	      });
		idComboBox.setModel(new DefaultComboBoxModel(ids.toArray()));
		idComboBox.setBounds(251, 257, 143, 29);
		controlPanel.add(idComboBox);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(270, 360, 124, 99);
		controlPanel.add(scrollPane_1);

		JTextArea textArea_2 = new JTextArea();
		scrollPane_1.setColumnHeaderView(textArea_2);
		textArea_2.setVisible(false);


		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		controlPanel.add(menuBar);

		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);

		mntmOpen = new JMenuItem("open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser("./");

				int returnValue = jfc.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					imagePanel.changeImage(selectedFile.getAbsolutePath());
				}  

			}
		});
		mnFile.add(mntmOpen);
		//  lblNewLabel.setIcon(new ImageIcon(MainPanel.class.getResource("/guiAnimation/Dog.gif")));

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainPanel.class.getResource("/project2package/CovidIcon.jpg")));
		label.setBounds(36, 0, 318, 72);
		controlPanel.add(label);

		JScrollPane allPeople = new JScrollPane();
		allPeople.setBounds(10, 148, 143, 257);
		controlPanel.add(allPeople);
		
				JTextArea textArea_1 = new JTextArea();
				allPeople.setViewportView(textArea_1);
				textArea_1.setText(myData.toString());

		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myData.writeFile();
			}
		});
		btnSaveData.setBounds(10, 447, 101, 24);
		controlPanel.add(btnSaveData);

		JButton btnSeePersonBy = new JButton("See Testing Facilities");
		btnSeePersonBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = personSlider.getValue();
				if (index == 1)
				{
					textField.setText("Sherwood Urgent - 222 Dave Ward Dr.");
				}
				else if (index == 2)
				{
					textField.setText("Conway Regional - 2302 College Ave.");
				}
				else if (index == 3)
				{
					textField.setText("MedExpress Urgent Care - 805 E Oak St.");
				}
				else if (index == 4)
				{
					textField.setText("Baptist Health - 1555 Exchange Ave.");
				}
			}
		});
		btnSeePersonBy.setBounds(231, 147, 163, 29);
		controlPanel.add(btnSeePersonBy);

		textField = new JTextField();
		textField.setBounds(163, 186, 231, 22);
		controlPanel.add(textField);
		textField.setColumns(10);

		JLabel lblTestingFacilities = new JLabel("Testing Facilities");
		lblTestingFacilities.setBounds(270, 82, 101, 13);
		lblTestingFacilities.setForeground(Color.WHITE);
		controlPanel.add(lblTestingFacilities);

		textArea = new JTextArea();
		textArea.setBounds(221, 291, 173, 27);
		controlPanel.add(textArea);

		JCheckBox chckbxShowContacts = new JCheckBox("Show Contacts");
		chckbxShowContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowContacts.isSelected())
				{
					textArea_2.setVisible(true);
				}
				else
				{
					textArea_2.setVisible(false);
				}

			}	      });
		chckbxShowContacts.setBounds(301, 324, 93, 21);
		controlPanel.add(chckbxShowContacts);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idComboBox.getSelectedItem().toString();
				Person p = myData.findPerson(id);
				textArea_2.setText(p.getContacts().toString());
			}
		});
		btnUpdate.setBounds(309, 469, 85, 21);
		controlPanel.add(btnUpdate);



		// add(imagePanel, BorderLayout.EAST);

	}
}
