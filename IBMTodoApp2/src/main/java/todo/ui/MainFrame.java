package todo.ui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainFrame extends JFrame {

	@Bean(initMethod = "init")
	public MainFrame makeFrame(@Value("#{mainPanel}") BoxLayoutPanel panel) {
		MainFrame frame = new MainFrame();
		frame.setTitle("My To Do List");
		frame.setContentPane(panel);
		return frame;
	}

	public void init() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(600, 400));

		setVisible(true);
		setState(Frame.NORMAL);
		show();
	}

}