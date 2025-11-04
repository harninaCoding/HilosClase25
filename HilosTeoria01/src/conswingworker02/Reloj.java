package conswingworker02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.css.ElementCSSInlineStyle;

import javax.swing.JSlider;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reloj extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reloj frame = new Reloj();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param incrementador
	 */
	public Reloj() {
		Incrementador incrementador=new Incrementador();
		incrementador.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSlider slider = new JSlider();
		slider.setValue(0);
		contentPane.add(slider, BorderLayout.CENTER);
		new SwingWorker<Integer, Integer>() {
			@Override
			protected Integer doInBackground() throws Exception {
				int value=0;
				do {
					value = incrementador.getValue();
					slider.setValue(value);
					Thread.sleep(100);
				} while (value < 100);
				return null;
			}
		}.execute();

//		JButton btnGo = new JButton("go");
//		btnGo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new SwingWorker<Integer, Integer>() {
//					int value = 10;
//					@Override
//					protected Integer doInBackground() throws Exception {
//						do {
//							value++;
//							slider.setValue(value);
//							Thread.sleep(100);
//						} while (value < 100);
//						return null;
//					}
//				}.execute();
//
//			}
//		});
//		contentPane.add(btnGo, BorderLayout.SOUTH);
	}

}
