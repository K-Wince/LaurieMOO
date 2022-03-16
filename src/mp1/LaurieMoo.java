package mp1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LaurieMoo {

	private JFrame frame;
	private JTextField guessField;
	
	private RandomMooValue moo = new RandomMooValue();
	
	int roundCount = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaurieMoo window = new LaurieMoo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LaurieMoo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		moo.setSecretValue();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMoos_1 = new JLabel("");
		lblMoos_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoos_1.setForeground(Color.WHITE);
		lblMoos_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMoos_1.setBounds(10, 197, 418, 24);
		frame.getContentPane().add(lblMoos_1);
		
		JLabel lblMoos = new JLabel("");
		lblMoos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMoos.setForeground(Color.WHITE);
		lblMoos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoos.setBounds(10, 231, 418, 24);
		frame.getContentPane().add(lblMoos);
		
		JLabel lblGuessNum = new JLabel("Attempt #1:");
		lblGuessNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGuessNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessNum.setForeground(Color.WHITE);
		lblGuessNum.setBounds(10, 11, 114, 14);
		frame.getContentPane().add(lblGuessNum);
		
		JLabel lblNewLabel = new JLabel("Perhaps.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(329, 126, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		guessField = new JTextField();
		guessField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create the values from the guess the user put in
				int guess = Integer.parseInt(guessField.getText());
				int lMooCount = moo.getLittleMooCount(guess);
				int bMooCount = moo.getBigMooCount(guess);
				String mooString = "";
				
				// Create the string on how many bigMoos and littleMoos there are
				for(int i = 0; i < bMooCount; i++) {
					mooString = mooString + "MOO! ";
				}
				for(int i = 0; i < lMooCount; i++) {
					mooString = mooString + "moo. ";
				}
				
				// If the round is not round 10 then run
				if(roundCount < 10) {
					roundCount++;                        // Increase the round
					if(bMooCount < 4) {                  // Check to see if they didn't complete the string
						lblMoos.setText(mooString);      // Reset the text boxes on GUI
						lblGuessNum.setText("Attempt #" + roundCount + ":");
					} else {
						lblMoos.setText("LaurieMOO!!!"); // If they win then put LaurieMoo
						roundCount = 99;                 // Set round count to high so it won't run again
					}
				// If the round is round 10 and completed then finish the game
				} else {
					if(roundCount != 99) {
						lblMoos_1.setText(mooString); // Show the string of moos of their final guess
						// Say they lost and give them the secret value
						lblMoos.setText(String.format("Boo hoo -- no LaurieMoo. - %04d", moo.getSecretValue()));
					}
				}
			}
		});
		
		guessField.setBounds(20, 29, 96, 20);
		frame.getContentPane().add(guessField);
		guessField.setColumns(10);
		
		JLabel lblBackImage = new JLabel("New label");
		lblBackImage.setIcon(new ImageIcon(LaurieMoo.class.getResource("/mp1/cover3.jpg")));
		lblBackImage.setBounds(0, 0, 438, 266);
		frame.getContentPane().add(lblBackImage);
	}
}
