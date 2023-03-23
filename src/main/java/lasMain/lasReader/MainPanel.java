package lasMain.lasReader;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JButton[] buttons = new JButton[1];
	
	public MainPanel() {
		this.setLayout(null);
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("Test");
			buttons[i].setBounds(100, 100, 100, 25);
			this.add(buttons[i]);
		}
		
		this.setVisible(true);
	}
}
