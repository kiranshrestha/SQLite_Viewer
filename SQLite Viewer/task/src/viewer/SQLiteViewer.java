package viewer;

import org.sqlite.SQLiteDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLiteViewer extends JFrame {
    
    private JPanel panel1;
    private JButton OpenFileButton;
    private JComboBox TablesComboBox;
    private JButton ExecuteQueryButton;
    private JTextArea QueryTextArea;
    private JTextField FileNameTextField;
    private String baseUrl = "jdbc:sqlite:";
    private JTable jTable;
    SQLiteDataSource sqLiteDataSource;
    private String tableName;

    private void createUIComponents() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 900);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("SQLite Viewer");


        FileNameTextField = new JTextField();
        FileNameTextField.setName("FileNameTextField");
        FileNameTextField.setBounds(0, 0, 600, 30);
        add(FileNameTextField);

        OpenFileButton = new JButton();
        OpenFileButton.setName("OpenFileButton");
        OpenFileButton.setText("Open");
        OpenFileButton.addActionListener(e -> {

        });
        OpenFileButton.setBounds(600, 0, 100, 30);
        add(OpenFileButton);

        TablesComboBox = new JComboBox();
        TablesComboBox.setName("TablesComboBox");
        TablesComboBox.setBounds(0, 40, 700, 30);
        add(TablesComboBox);

        QueryTextArea = new JTextArea();
        QueryTextArea.setName("QueryTextArea");
        QueryTextArea.setBounds(0, 80, 600, 30);
        QueryTextArea.setEnabled(false);
        add(QueryTextArea);

        ExecuteQueryButton = new JButton("Execute");
        ExecuteQueryButton.setName("ExecuteQueryButton");
        ExecuteQueryButton.setBounds(600, 80, 100, 30);
        add(ExecuteQueryButton);
        ExecuteQueryButton.setEnabled(false);

        jTable = new JTable();
        jTable.setName("Table");
        jTable.setBounds(0, 100, 700, 200);

        add(jTable);

        setVisible(true);

    }

    public SQLiteViewer() {
        createUIComponents();
        setVisible(true);

        OpenFileButton.addActionListener(al -> {
            sqLiteDataSource = new SQLiteDataSource();
            sqLiteDataSource.setUrl(baseUrl.concat(FileNameTextField.getText()));
            File file = new File(FileNameTextField.getText());
            if (!file.exists()) {
                JOptionPane.showMessageDialog(new Frame(), "File doesn't exist!");
                ExecuteQueryButton.setEnabled(false);
                QueryTextArea.setEnabled(false);
            } else {
                try(Connection con = sqLiteDataSource.getConnection()) {
                    if(con.isValid(5)) {
                        try(Statement statement = con.createStatement()) {
                            ResultSet rs = statement.executeQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%'");
                            QueryTextArea.setEnabled(true);
                            TablesComboBox.removeAllItems();
                            ExecuteQueryButton.setEnabled(true);
                            while(rs.next()){
                                TablesComboBox.addItem(rs.getString(1));
                            }

                        }
                    }
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(new Frame(), throwables.getMessage());
                }
            }

        });

        TablesComboBox.addItemListener(e -> {
            if(e!=null) {
                tableName = e.getItem().toString();
                QueryTextArea.setText("SELECT * FROM " + tableName + ";");
            }
        });

        ExecuteQueryButton.addActionListener(e -> {

            try (Connection con = sqLiteDataSource.getConnection()) {
                if (con.isValid(5)) {
                    try (Statement statement = con.createStatement()) {
                        ResultSet rs = statement.executeQuery(QueryTextArea.getText());

                        int columnCount = rs.getMetaData().getColumnCount();
                        String[] cols = new String[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            cols[i-1] = rs.getMetaData().getColumnName(i);
                        }
                        ArrayList<Object[]> data = new ArrayList<>();
                        while (rs.next()) {
                            Object[] row = new Object[columnCount];
                            for (int i = 1; i <= columnCount; i++) {
                                row[i-1] = rs.getString(i);
                            }
                            data.add(row);
                        }
                        setTableWithData(cols,data);
                    }
                }
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(new Frame(), throwables.getMessage());
            }
        });


    }

    public void setTableWithData(String[] cols, ArrayList<Object[]> data) {
        DefaultTableModel tableModel = new DefaultTableModel(0,0);
        tableModel.setColumnIdentifiers(cols);
        for (Object[] datum : data) {
            tableModel.addRow(datum);
        }
        jTable.setModel(tableModel);
        setVisible(true);
    }


}
