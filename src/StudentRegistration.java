import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StudentRegistration extends JFrame {
    private JPanel mainpanelfield;
    private JTextField nametextField1;
    private JTextField RegnumtextField2;
    private JTextField emailidtextField3;
    private JTable table1;
    private JLabel AddNewStudent;
    private JLabel StudentNameLabel;
    private JLabel RegistrationNumLabel;
    private JLabel EmailIdLabel;
    private JLabel DepartmentNameLabel;
    private DefaultTableModel tableModel;
    private JComboBox departcomboBox;
    private JButton addButton;
    private JButton clearButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JLabel ListofStudents;

    private void clearInputFields() {
        nametextField1.setText("");
        RegnumtextField2.setText("");
        emailidtextField3.setText("");
        departcomboBox.setSelectedIndex(0);
    }

    private boolean isValidRegistration(String regNumber) {
        try {
            if(!regNumber.matches("2020E[0-2][0-9][0-9]"))
                return true;
            else
            throw new regNoException("Registration number is not valid");
        }catch(regNoException exp){
            System.out.println(exp);
            return false;
        }
    }

    public static class regNoException extends Exception{
        public regNoException(String regNo_is_not_valid){
            super();
        }
    }

    private boolean isValidEmail(String email) {
        try {
            if(!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"))
                return true;
            else
                throw new emailException("Email is not valid");
        }catch(emailException exp){
            System.out.println(exp);
            return false;
        }
    }
    public static class emailException extends Exception{
        public emailException(String email_is_not_valid){
            super();
        }
    }
    public StudentRegistration(){
        setContentPane(mainpanelfield);
        setTitle("Student Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Registration Number");
        tableModel.addColumn("Email");
        tableModel.addColumn("Department");
        table1.setModel(tableModel);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        inputPanel.add(StudentNameLabel);
        inputPanel.add(RegistrationNumLabel);
        inputPanel.add(EmailIdLabel);
        inputPanel.add(DepartmentNameLabel);


        // Add components to the JFrame using layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table1), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nametextField1.getText();
                String regNumber= RegnumtextField2.getText();
                String email= emailidtextField3.getText();
                String department = (String) departcomboBox.getSelectedItem();

                // Validate data and add to the table model
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter your name, It is compulsory");
                } else if (regNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter your registration number, It is compulsory");
                } else if (isValidRegistration(regNumber)) {
                    JOptionPane.showMessageDialog(null, "Error: Please validate your Registration number");
                } else if (!email.isEmpty() && isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Error: Please validate your Email address");
                } else {
                    Object[] rowData = {name, regNumber, email, department};
                    DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                    model1.addRow(rowData); // Here's where you add the row
                    clearInputFields();
                }
            }
        });


        table1.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow != -1) {
                nametextField1.setText((String) tableModel.getValueAt(selectedRow, 0));
                RegnumtextField2.setText((String) tableModel.getValueAt(selectedRow, 1));
                emailidtextField3.setText((String) tableModel.getValueAt(selectedRow, 2));
                departcomboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3));
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    String name = nametextField1.getText();
                    String regNumber = RegnumtextField2.getText();
                    String email = emailidtextField3.getText();
                    String department = (String) departcomboBox.getSelectedItem();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Please enter your name, It is compulsory");
                    } else if (regNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Please enter your registration number, It is compulsory");
                    } else if (isValidRegistration(regNumber)) {
                        JOptionPane.showMessageDialog(null, "Error: Please validate your Registration number");
                    } else if (!email.isEmpty() && isValidEmail(email)) {
                        JOptionPane.showMessageDialog(null, "Error: Please validate your Email address");
                    }else {
                        tableModel.setValueAt(name, selectedRow, 0);
                        tableModel.setValueAt(regNumber, selectedRow, 1);
                        tableModel.setValueAt(email, selectedRow, 2);
                        tableModel.setValueAt(department, selectedRow, 3);
                    }

                    }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                }
            }
        });


    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentRegistration().setVisible(true);
            }
        });

    }


}