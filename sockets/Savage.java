package sockets;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Savage extends JFrame {

	private JButton jb;
	private JTextArea jta;
	private DinningSavages pot;

	public Savage(DinningSavages pot, int i) {
		super("Savage " + i);
		this.pot = pot;
		jb = new JButton("Eat");
		jta = new JTextArea();

		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jb.setEnabled(false);

				Thread t = new Thread() {
					public void run() {
						String s = jta.getText();
						if (s.equals("")) {
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									jta.setText("");
									jb.setEnabled(true);
								}
							});
							return;
						}
						int type = Integer.parseInt(s);
						if (type < 0 || type >= 3) {
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									jta.setText("");
									jb.setEnabled(true);
								}
							});
							return;
						}
						pot.eat(type);

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								jta.setText("");
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
		this.setBounds(300, 300, 300, 300);
		this.setLayout(new GridLayout(2, 1));
		this.add(jta);
		this.add(jb);	
	}
}
