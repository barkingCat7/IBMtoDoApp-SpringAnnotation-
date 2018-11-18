package todo.ui.button;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import todo.ui.ItemTableModel;

@Configuration
public class OtherBeans {

	@Bean
	public JTable itemTable(@Value("#{itemTableModel}") ItemTableModel itemTableModel) {
		JTable table = new JTable();
		table.setModel(itemTableModel);
		return table;
	}

	@Bean
	public List<Object> itemList() {
		List<Object> list = new ArrayList<Object>();
		list.add("Item 111");
		list.add("Item 222");
		list.add("Item 333");
		return list;
	}

	@Bean
	public ItemTableModel itemTableModel(@Value("#{itemList}") List<Object> itemList) {
		ItemTableModel itemTableModel = new ItemTableModel();
		itemTableModel.setItemList(itemList);
		return itemTableModel;
	}

	@Bean
	public DeleteButtonActionListener deleteButtonActionListener(@Value("#{itemList}") List<Object> itemList,
			@Value("#{itemTable}") JTable itemTable) {
		DeleteButtonActionListener actionListener = new DeleteButtonActionListener();
		actionListener.setList(itemList);
		actionListener.setTable(itemTable);
		return actionListener;
	}

	@Bean
	public AddNewButtonActionListener addNewButtonActionListener(@Value("#{itemList}") List<Object> itemList,
			@Value("#{itemTable}") JTable itemTable) {
		AddNewButtonActionListener actionListener = new AddNewButtonActionListener();
		actionListener.setList(itemList);
		actionListener.setTable(itemTable);
		return actionListener;
	}

}
