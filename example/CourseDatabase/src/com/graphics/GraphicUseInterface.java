package com.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.database.table.DatabaseTableModel;

public class GraphicUseInterface {
	
public static DatabaseTableModel model;
	
	private JFrame mainWindow;
	
	public static JButton outStudent;
	public JButton clear;
	
	private JTable resTable;
	
	private JPanel buttonsPanel;
	private JPanel textPanel;
	private JPanel tablePanel;
	
	public static JTextField enterId;
	
	private JLabel printLabel;
	
	protected JComboBox<String> selector;
	private JComboBox<String> cols;
	
	public static String toQuery = "";
	public static String col = "";
	
	public void run() {
		
		mainWindow = new JFrame("SQLDatabase: Diploma projects");
			mainWindow.setSize(new Dimension(1024, 768));
			mainWindow.setLocation(new Point(600, 150));
			mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainWindow.setVisible(true);
			mainWindow.setLayout(new FlowLayout(FlowLayout.CENTER));
			mainWindow.setResizable(true);
			
		textPanel = new JPanel();
		enterId = new JTextField(30);
			textPanel.add(enterId);
		mainWindow.getContentPane().add(textPanel, BorderLayout.NORTH);
		
		printLabel = new JLabel();
		
		outStudent = new JButton("Display the table");
		clear = new JButton("Clear");
		
		buttonsPanel = new JPanel();
		buttonsPanel.add(printLabel);
		buttonsPanel.add(outStudent);
		buttonsPanel.add(clear);
		
			mainWindow.getContentPane().add(buttonsPanel);
			
			model = new DatabaseTableModel();
			resTable = new JTable(model);
			tablePanel = new JPanel(new BorderLayout());
			tablePanel.setPreferredSize(new Dimension(900,660));
			tablePanel.add(new JScrollPane(resTable), BorderLayout.CENTER);
		mainWindow.add(tablePanel);

		
		String[] tables = {
				"",
				"Students",
				"Instructors",
				"Projects",
				"Results"
		};
		String[] columns = {
				"",
				"id_student",
				"p_id",
				"id_instructor",
				"f_name",
				"l_name",
				"d_name",
				"n_group"
		};
		cols = new JComboBox<String>(columns);
		cols.setSelectedIndex(-1);
		cols.setEditable(false);
		ActionListener colList = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				col = cols.getSelectedItem().toString().toLowerCase();
				
			}
			
		};
		cols.addActionListener(colList);
		textPanel.add(cols);
		
		selector = new JComboBox<String>(tables);
		selector.setSelectedIndex(-1);
		selector.setEditable(false);
		ActionListener actionLis = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				toQuery = selector.getSelectedItem().toString().toLowerCase();
			}
			
		};
		selector.addActionListener(actionLis);
		textPanel.add(selector);
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				enterId.setText("");
				selector.setSelectedIndex(0);
				cols.setSelectedIndex(0);
				
			}
			
		});
		
	}
	
}
