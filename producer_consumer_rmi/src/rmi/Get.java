package rmi;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


/**
 * Get klasa.<br>
 * Koristi Java Thread klasu za izvrsavanje zahteva.
 */
@SuppressWarnings("serial")
public class Get extends JFrame {

	JButton jb;
	JTextArea jta;

	public Get(ProducerConsumer<TextMessage> buffer) {
		super("Get");
		jb = new JButton("Get");
		jta = new JTextArea();

		jta.setEditable(false);

		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jb.setEnabled(false);

				Thread t = new Thread() {

					@Override
					public void run() {
						TextMessage item = buffer.get();

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								jta.setText(item.getBody());
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
		this.setLayout(new GridLayout(2, 1));
		this.setBounds(600, 200, 300, 300);
		this.add(jb);
		this.add(jta);
	}

}
