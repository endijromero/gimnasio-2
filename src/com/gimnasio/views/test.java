
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

public class test {

    private JFrame frame;
    private Connection cn;
    private Statement st;
    private PreparedStatement ps;
    private String addedit;
    private JTextField txtAddress;
    private JTextField txtName;
    private JComboBox cboGender;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnExit;
    private JPanel panelinfo;
    private String pname;
    private JPanel panel;
    private JTextField txtSearch;
    private DefaultTableModel tblModel;
    JTable tblList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    test window = new test();
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
    public test() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 598, 470);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnAdd = new JButton("Add");

        btnAdd.setBounds(250, 222, 73, 30);
        frame.getContentPane().add(btnAdd);

        btnEdit = new JButton("Edit");

        btnEdit.setBounds(333, 222, 73, 30);
        frame.getContentPane().add(btnEdit);

        btnDelete = new JButton("Delete");

        btnDelete.setBounds(416, 222, 73, 30);
        frame.getContentPane().add(btnDelete);

        btnExit = new JButton("Exit");

        btnExit.setBounds(499, 222, 73, 30);
        frame.getContentPane().add(btnExit);

        panelinfo = new JPanel();
        panelinfo.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelinfo.setBounds(243, 76, 329, 135);

        frame.getContentPane().add(panelinfo);
        panelinfo.setLayout(null);

        cboGender = new JComboBox();
        cboGender.setBounds(93, 60, 90, 17);
        panelinfo.add(cboGender);

        cboGender.addItem("Female");
        cboGender.addItem("Male");

        txtAddress = new JTextField();
        txtAddress.setColumns(10);
        txtAddress.setBounds(93, 84, 206, 20);
        panelinfo.add(txtAddress);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(93, 28, 206, 20);
        panelinfo.add(txtName);

        JLabel label = new JLabel("Name :");
        label.setBounds(10, 34, 46, 14);
        panelinfo.add(label);

        JLabel label_1 = new JLabel("Gender :");
        label_1.setBounds(10, 62, 73, 14);
        panelinfo.add(label_1);

        JLabel label_2 = new JLabel("Address :");
        label_2.setBounds(10, 90, 73, 14);
        panelinfo.add(label_2);

        panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 76, 230, 86);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        txtSearch = new JTextField();
        txtSearch.setBounds(10, 26, 210, 20);
        panel.add(txtSearch);
        txtSearch.setColumns(10);

        JList list = new JList();
        list.setBounds(49, 184, 128, -103);
        panel.add(list);

        JButton btnsearch = new JButton("Search");

        btnsearch.setBounds(126, 57, 94, 18);
        panel.add(btnsearch);

        String[] columnNames = {"Name",
            "Gender",
            "Address"
        };

	//	tblList = new JTable();
        //	tblList.setBorder(new LineBorder(new Color(0, 0, 0)));
        //tblList.setBounds(55, 314, 496, 116);
        //tblList.setEnabled(false);
        tblList = new JTable(new DefaultTableModel(null, new Object[]{"Name", "Gender", "Address"}));
	//	DefaultTableModel model = new DefaultTableModel(data,columnNames);

        tblModel = (DefaultTableModel) tblList.getModel();

	//	model.addRow(new Object[]{"Column 1", "Column 2"});
        tblList = new JTable(tblModel) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tblList.setBorder(new LineBorder(new Color(0, 0, 0)));
        tblList.setBounds(55, 314, 496, 116);

		//	tblList.getColumnModel().getColumn(1).setMaxWidth(0);
        //	tblList.getColumnModel().getColumn(1).setMinWidth(0);
        //	tblList.getColumnModel().getColumn(1).setPreferredWidth(0);
		//DefaultTableModel tableModel = new DefaultTableModel() {
		 //   @Override
        //  public boolean isCellEditable(int row, int column) {
        //all cells false
        //      return false;
        //   }
        //	};
	//	tblList.setModel(tableModel);
        //tblList.setPreferredScrollableViewportSize(new Dimension(500, 70));
        //tblList.setFillsViewportHeight(true);
		//JScrollPane scrollPane = new JScrollPane(tblList);
        //frame.getContentPane().add(scrollPane);
		//frame.getContentPane().add(tblList);
        JScrollPane scrollPane = new JScrollPane(tblList);
        scrollPane.setPreferredSize(new Dimension(452, 150));
	//	scrollPane.setBounds(55, 314, 496, 116);
        //frame.getContentPane().add(scrollPane);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(49, 263, 523, 159);
        panel_1.add(scrollPane);

        frame.getContentPane().add(panel_1);

        Component[] com = panelinfo.getComponents();
		//Inside you action event where you want to disable everything  
        //Do the following  
        for (int a = 0; a < com.length; a++) {
            com[a].setEnabled(false);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gimnasio", "root", "emi_*2605");
            //	JOptionPane.showMessageDialog(null,"Connected",null, JOptionPane.INFORMATION_MESSAGE, null);

        } catch (ClassNotFoundException a) {
            a.printStackTrace();
            //	JOptionPane.showMessageDialog(null,"err1",null, JOptionPane.INFORMATION_MESSAGE, null);
        } catch (SQLException b) {
            b.printStackTrace();
            //	JOptionPane.showMessageDialog(null,"err2",null, JOptionPane.INFORMATION_MESSAGE, null);
        }

        displayList();

        btnAdd.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {

                if (btnAdd.getText().equalsIgnoreCase("Add")) {

                    btnAdd.setText("Save");
                    btnEdit.setText("Cancel");
                    btnDelete.setEnabled(false);
                    btnExit.setEnabled(false);
                    txtSearch.setEnabled(false);
                    tblList.setEnabled(false);
                    addedit = "Add";
                    Component[] com = panelinfo.getComponents();
				//Inside you action event where you want to disable everything  
                    //Do the following  
                    for (int a = 0; a < com.length; a++) {
                        com[a].setEnabled(true);
                    }
                    clear();
                    txtName.requestFocus();
                } else {

                    int a = JOptionPane.showConfirmDialog(null, "Do you want to save this record?", "Message", JOptionPane.YES_NO_OPTION);

                    if (a == 0) {

                        //try{
                        try {
                            st = cn.createStatement();
                            String gender;
                            if (cboGender.getSelectedIndex() == 1) {
                                gender = "Male";
                            } else {
                                gender = "Female";
                            }

                            if (addedit.equals("Add")) {
                                ps = cn.prepareStatement("INSERT INTO info values('" + txtName.getText() + "','" + gender + "','" + txtAddress.getText() + "')");
                            } else {
                                ps = cn.prepareStatement("UPDATE info set PName='" + txtName.getText() + "',sex ='" + gender + "',Address ='" + txtAddress.getText() + "' where Pname ='" + txtSearch.getText() + "'");
                            }
                            ps.executeUpdate();

						// while(rs.next()){
                            // 	strUser=rs.getString(1);
                            //	 	strPass=rs.getString(2);
                            //	}
                            //JOptionPane.showMessageDialog(null,"save",null, JOptionPane.INFORMATION_MESSAGE, null);	
                            defautview();
                            displayList();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        });

        tblList.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {
                int selectedRowIndex = tblList.getSelectedRow();
                //int selectedColumnIndex = tblList.getSelectedColumn();
                String selectedObject = (String) tblList.getModel().getValueAt(selectedRowIndex, 0);

                txtSearch.setText(selectedObject);
                search();

            }
        });

        btnEdit.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent arg0) {
                if (btnEdit.getText().equalsIgnoreCase("Edit")) {

                    btnAdd.setText("Save");
                    btnEdit.setText("Cancel");
                    btnDelete.setEnabled(false);
                    btnExit.setEnabled(false);
                    txtSearch.setEnabled(false);
                    tblList.setEnabled(false);
                    addedit = "Edit";
                    Component[] com = panelinfo.getComponents();
						//Inside you action event where you want to disable everything  
                    //Do the following  
                    for (int a = 0; a < com.length; a++) {
                        com[a].setEnabled(true);
                    }

                    txtName.requestFocus();
                } else {
                    defautview();
                }
            }
        });

        btnsearch.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                search();
            }
        });

        btnDelete.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Do you want to delete this record?", "Message", JOptionPane.YES_NO_OPTION);

                if (a == 0) {

                    try {
                        st = cn.createStatement();

                        ps = cn.prepareStatement("delete from info where pname ='" + txtSearch.getText() + "'");
                        ps.executeUpdate();
                        displayList();
                        clear();

                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

            }
        });

        btnExit.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

    }

    void clear() {
        txtName.setText("");
        txtAddress.setText("");
        cboGender.setSelectedIndex(0);

    }

    void defautview() {
        btnAdd.setText("Add");
        btnEdit.setText("Edit");
        btnDelete.setEnabled(true);
        btnExit.setEnabled(true);
        txtSearch.setEnabled(true);
        tblList.setEnabled(true);
        Component[] com = panelinfo.getComponents();
		//Inside you action event where you want to disable everything  
        //Do the following  
        for (int a = 0; a < com.length; a++) {
            com[a].setEnabled(false);
        }
        clear();
    }

    void displayList() {
        try {
            st = cn.createStatement();

            ResultSet rs = st.executeQuery("select * from info");
            tblModel.getDataVector().removeAllElements();
            while (rs.next()) {

                tblModel.addRow(new Object[]{rs.getString("pname"), rs.getString("sex"), rs.getString("address")});
            }

        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

    }

    void search() {
        try {
            st = cn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM info WHERE pname='" + txtSearch.getText() + "'");

		// while(rs.next()){
            //strUser=rs.getString(1);
            //strPass=rs.getString(2);
            if (rs.next()) {
                txtName.setText(rs.getString("Pname").toString());
                if (rs.getString("sex").toString().equals("Male")) {
                    cboGender.setSelectedIndex(0);
                } else {
                    cboGender.setSelectedIndex(1);
                }

                txtAddress.setText(rs.getString("address").toString());
            } else {

                JOptionPane.showMessageDialog(null, "Not Found", null, JOptionPane.INFORMATION_MESSAGE, null);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
