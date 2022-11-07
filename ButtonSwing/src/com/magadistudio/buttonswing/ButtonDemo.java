package com.magadistudio.buttonswing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ButtonDemo implements ActionListener{
	JLabel myLabel;

	public ButtonDemo() {

		// Create a JFrame
		JFrame jFrame = new JFrame("Button");

		// Specify FlowLayout for the layout manager
		jFrame.setLayout(new FlowLayout());

		// Give a size to our frame
		jFrame.setSize(220, 80);

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make a button
		JButton upButton = new JButton("Up");
		JButton downButton = new JButton("Down");

		// Label
		myLabel = new JLabel("Hello there");
		
//		upButton.addActionListener((ActionEvent e) -> {
//			System.out.println("Hello, Up clicked");
//			myLabel.setText("Hello, Up clicked");
//		});
		upButton.addActionListener(this);
		
//		downButton.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("Hello, Down clicked");
//				myLabel.setText("Hello, Down clicked");
//			}
//		});
		downButton.addActionListener(this);


		jFrame.add(upButton);
		jFrame.add(downButton);
//		jFrame.add(testButton);
		jFrame.add(myLabel);

		// Show it !
		jFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// Create the frame on the event dispatching thread
		SwingUtilities.invokeLater(() -> {
			new ButtonDemo();
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Our playground
		
		if(e.getActionCommand().equals("Up")) {
			System.out.println("Up button clicked");
			myLabel.setText("You pressed Up");
		}
		if(e.getActionCommand().equals("Down")) {
			System.out.println("Down button clicked");
			myLabel.setText("You pressed Down");
		}
		
//		JButton buttonPressed = (JButton) e.getSource();
//		System.out.println(buttonPressed.getText());
	}

}
