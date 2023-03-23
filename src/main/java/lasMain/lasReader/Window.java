package lasMain.lasReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(String title, int width, int height) {
		super(title);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new MainPanel();
		this.add(mainPanel);
		
		this.setVisible(true);
		
	}
}
