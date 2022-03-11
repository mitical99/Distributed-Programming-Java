package sockets;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Chef extends JFrame {

	private JButton jb;
	private JTextArea jta;
	private DinningSavages pot;
	public Chef(DinningSavages pot) {
		super("Chef");
		jb=new JButton("Cook");
		jta=new JTextArea();
		
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jb.setEnabled(false);
				
				Thread t = new Thread() {
					
					public void run() {
						int type = pot.cook();
						
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								jta.setText(String.valueOf(type));
								jb.setEnabled(true);
							}
						});
					}
				};
				t.start();
			}
			
		});
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(600, 300, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(jb);		
		this.add(jta);

	}
}
