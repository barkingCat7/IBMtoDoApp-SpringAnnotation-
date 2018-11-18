package todo.ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import todo.ui.button.ActionListenerButton;
import todo.ui.button.AddNewButtonActionListener;
import todo.ui.button.DeleteButtonActionListener;

@Configuration
public class BoxLayoutPanel extends JPanel {
	/**
	 * We can't use "components" as the property name, because it conflicts with
	 * an existing property on the Component superclass.
	 */
	private List<Object> panelComponents;
	private int axis;

	@Bean
	public JScrollPane itemScrollPane(@Value("#{itemTable}") JTable itemTable) {
		JScrollPane panel = new JScrollPane(itemTable);
		return panel;
	}

	@Bean(initMethod = "init")
	public ActionListenerButton deleteButton(
			@Value("#{deleteButtonActionListener}") DeleteButtonActionListener listener) {
		ActionListenerButton button = new ActionListenerButton();
		button.setText("Delete");
		button.setActionListener(listener);
		return button;
	}

	@Bean(initMethod = "init")
	public ActionListenerButton addNewButton(
			@Value("#{addNewButtonActionListener}") AddNewButtonActionListener listener) {
		ActionListenerButton button = new ActionListenerButton();
		button.setActionListener(listener);
		button.setText("Add New");
		return button;
	}

	@Bean
	public List<Object> myList1(@Value("#{itemScrollPane}") JScrollPane panel1,
			@Value("#{buttonPanel}") BoxLayoutPanel panel2) {
		List<Object> list = new ArrayList<Object>();
		list.add(panel1);
		list.add(panel2);
		return list;
	}

	@Bean
	public List<Object> myList2(@Value("#{addNewButton}") ActionListenerButton button1,
			@Value("#{deleteButton}") ActionListenerButton button2) {
		List<Object> list = new ArrayList<Object>();
		list.add(button1);
		list.add(button2);
		return list;
	}

	@Bean(initMethod = "init")
	public BoxLayoutPanel mainPanel(@Value("#{myList1}") List<Object> panelComponents) {
		BoxLayoutPanel panel = new BoxLayoutPanel();
		panel.setAxis(1);
		panel.setPanelComponents(panelComponents);
		return panel;
	}

	@Bean(initMethod = "init")
	public BoxLayoutPanel buttonPanel(@Value("#{myList2}") List<Object> panelComponents) {
		BoxLayoutPanel panel = new BoxLayoutPanel();
		panel.setAxis(0);
		panel.setPanelComponents(panelComponents);
		return panel;
	}

	public void setAxis(int axis) {
		this.axis = axis;
	}

	public void setPanelComponents(List<Object> panelComponents) {
		this.panelComponents = panelComponents;
	}

	public void init() {

		setLayout(new BoxLayout(this, axis));

		for (Iterator<Object> iter = panelComponents.iterator(); iter.hasNext();) {
			Component component = (Component) iter.next();
			add(component);
		}
	}
}