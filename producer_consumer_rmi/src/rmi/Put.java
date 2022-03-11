package rmi;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


/**
 * Put klasa. <br>
 * Koristi Java Thread klasu za izvrsavanje zahteva.
 */
@SuppressWarnings("serial")
public class Put extends JFrame {

	JButton jb;
	JTextArea jta;

	public Put(ProducerConsumer<TextMessage> buffer) {
		super("Put");
		jb = new JButton("Put");
		jta = new JTextArea();

		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = jta.getText();
				jb.setEnabled(false);

				Thread t = new Thread() {

					@Override
					public void run() {

						TextMessage msg = new TextMessage();
						msg.setBody(text);
						buffer.put(msg);

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
		this.setLayout(new GridLayout(2, 1));
		this.setBounds(200, 200, 300, 300);
		this.add(jta);
		this.add(jb);
	}

}
