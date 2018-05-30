package view;
import java.awt.EventQueue;

import javax.swing.JFrame; 
import javax.swing.JTabbedPane; 

import java.awt.BorderLayout; 

import javax.swing.JButton; 
import javax.swing.JScrollPane;
import javax.swing.JTextPane; 
import javax.swing.JTextField; 
import javax.swing.JLabel; 
import javax.swing.JTable; 
import javax.swing.JComboBox; 
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints; 
import java.awt.Insets; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import modell.*;
import connector.*;
import controller.*;

public class gui {

private JFrame frame;
private JTextField txtSkrivInKurskodpersonnummer;
private JTextField txtPersonnummer;
private JTextField txtsNamn;
private JTextField txtAdress;
private JTextField txtKursnummer;
private JTextField textField_1;
private static Controller controller;
private JTextField txtPong;
private JTextField txtcNamn;
private JTable Student;
private JTable Kurs;
private JTable CourseStudent;
private JTextField TxtSpnrRegStudied;
private JTextField txtCcodetoStudied;
private JTextField txtKurskodgrade;
private JTextField txtKurskod;
private JTable table;
private JTextField txtSpnrforGrade;
private JTextField textFieldProcent;


/**
 * Launch the application.
 */
public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
        public void run() {
        	controller = new Controller();
            try {
                gui window = new gui();
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
public gui() {
    initialize();
}

/**
 * Initialize the contents of the frame.
 */
private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 980, 748);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(0, 0, 606, 21);
    frame.getContentPane().add(tabbedPane);

    JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.addTab("New tab", null, tabbedPane_1, null);

    JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.addTab("New tab", null, tabbedPane_2, null);

    JLabel lblAngeKurskodpersonnummer = new JLabel("Ange kurskod/personnummer");
    lblAngeKurskodpersonnummer.setBounds(10, 19, 190, 16);
    frame.getContentPane().add(lblAngeKurskodpersonnummer);

    txtSkrivInKurskodpersonnummer = new JTextField();
    txtSkrivInKurskodpersonnummer.setBounds(10, 40, 134, 28);
    frame.getContentPane().add(txtSkrivInKurskodpersonnummer);
    txtSkrivInKurskodpersonnummer.setColumns(10);

    JScrollPane spKurs = new JScrollPane();
    spKurs.setBounds(323, 114, 294, 153);
    frame.getContentPane().add(spKurs);
    
    String[] colkurs = {"Course ID", "Course Name", "Course Points"};
    DefaultTableModel tableKurs = new DefaultTableModel (colkurs, 0);
    
    
     Kurs = new JTable(tableKurs);
    spKurs.setViewportView(Kurs);

    JButton btnSkKurs = new JButton("Sök Kurs");
    btnSkKurs.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    String ccode = txtSkrivInKurskodpersonnummer.getText();
	if(!ccode.isEmpty()) {
	Course c = null;
	try {
		c = controller.findCourse(ccode);
	} catch (SQLException e1) {
		
		e1.printStackTrace();
	}
	Object[] courseData = {c.getCcode(), c.getcName(), c.getPoints() };
	tableKurs.addRow(courseData);
	}
}
});
    btnSkKurs.setBounds(127, 73, 99, 29);
    frame.getContentPane().add(btnSkKurs);
    
    JScrollPane spStudent = new JScrollPane();
    spStudent.setBounds(10, 114, 294, 153);
    frame.getContentPane().add(spStudent);
    
    String[] colstudent = {"Student ID", "Student Name", "Student Adress"};
    DefaultTableModel tableStudent = new DefaultTableModel (colstudent, 0);
    
    
     JTable Student = new JTable(tableStudent);
    spStudent.setViewportView(Student);
    

    JButton btnSkStudent = new JButton("Sök Student");
    btnSkStudent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String spnr = txtSkrivInKurskodpersonnummer.getText();
    		if(!spnr.isEmpty()) {
    		Student s = null;
			try {
				s = controller.findStudent(spnr);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
    		Object[] studData = {s.getSpnr(), s.getsName(), s.getsadress()};
    		tableStudent.addRow(studData);
    		}
    	}
    });
    
    btnSkStudent.setBounds(10, 73, 119, 29);
    frame.getContentPane().add(btnSkStudent);

    JLabel lblLggTillStudent = new JLabel("Lägg till Student");
    lblLggTillStudent.setBounds(10, 325, 103, 16);
    frame.getContentPane().add(lblLggTillStudent);

    txtPersonnummer = new JTextField();
    txtPersonnummer.setBounds(10, 347, 134, 28);
    txtPersonnummer.setText("Personnummer");
    frame.getContentPane().add(txtPersonnummer);
    txtPersonnummer.setColumns(10);

    txtsNamn = new JTextField();
    txtsNamn.setBounds(10, 380, 134, 28);
    txtsNamn.setText("Namn");
    frame.getContentPane().add(txtsNamn);
    txtsNamn.setColumns(10);

    txtAdress = new JTextField();
    txtAdress.setBounds(10, 413, 134, 28);
    txtAdress.setText("Adress");
    txtAdress.setColumns(10);
    frame.getContentPane().add(txtAdress);

    JLabel lblVljKurs = new JLabel("Välj kurs");
    lblVljKurs.setBounds(14, 513, 99, 16);
    frame.getContentPane().add(lblVljKurs);

    JComboBox<String> comboBox = new JComboBox<String>();
    comboBox.setBounds(10, 529, 134, 27);
    frame.getContentPane().add(comboBox);
   	try {
   		
   		String[] strArray = controller.convertCoursestoArray();
   		for (String s : strArray){
   			comboBox.addItem(s);
   		}
   	} catch (SQLException e1) {
   		e1.printStackTrace();
   	}


    JButton btnRegisterastud = new JButton("Registrera Student"); 
    btnRegisterastud.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
    		String spnr = txtPersonnummer.getText().trim();
    		String sname = txtsNamn.getText().trim();
    		String sadress = txtAdress.getText().trim();
    		
    		
    		try {
    			controller.addStudent(spnr, sname, sadress);
    			
    			}catch(SQLException e2) {
    		} 
    	}
    });
    btnRegisterastud.setBounds(10, 448, 134, 29);
    frame.getContentPane().add(btnRegisterastud);

    JLabel lblLggTillKurs = new JLabel("Lägg till Kurs");
    lblLggTillKurs.setBounds(174, 325, 103, 16);
    frame.getContentPane().add(lblLggTillKurs);

    txtKursnummer = new JTextField();
    txtKursnummer.setText("Kurskod");
    txtKursnummer.setColumns(10);
    txtKursnummer.setBounds(174, 347, 134, 28);
    frame.getContentPane().add(txtKursnummer);
    
    txtcNamn = new JTextField();
    txtcNamn.setText("Namn");
    txtcNamn.setColumns(10);
    txtcNamn.setBounds(174, 380, 134, 28);
    frame.getContentPane().add(txtcNamn);
    
    txtPong = new JTextField();
    txtPong.setText("Poäng");
    txtPong.setColumns(10);
    txtPong.setBounds(174, 413, 134, 28);
    frame.getContentPane().add(txtPong);

   

    JButton regcurbutton = new JButton("Registrera Kurs");
    regcurbutton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String ccode = txtKursnummer.getText();
    		String cName = txtcNamn.getText();
    		String points = txtPong.getText();
    		comboBox.addItem(ccode);
    		
    		try {
    			controller.addCourse(ccode, cName, points);
    		}catch (SQLException e3) {
    			e3.printStackTrace();
    			
    		}
    		

    	}
    }); 
    regcurbutton.setBounds(175, 448, 133, 29);
    frame.getContentPane().add(regcurbutton);

    JLabel lblSttBetyg = new JLabel("Sätt betyg");
    lblSttBetyg.setBounds(354, 325, 80, 16);
    frame.getContentPane().add(lblSttBetyg);


    JComboBox<String> comboBoxGrade = new JComboBox<String>();
	comboBoxGrade.addItem("A");
	comboBoxGrade.addItem("B");
	comboBoxGrade.addItem("C");
	comboBoxGrade.addItem("D");
	comboBoxGrade.addItem("E");
	comboBoxGrade.addItem("U");
    comboBoxGrade.setBounds(354, 415, 119, 27);
    frame.getContentPane().add(comboBoxGrade);
 
	
	TxtSpnrRegStudied = new JTextField();
    TxtSpnrRegStudied.setText("Personnummer");
    TxtSpnrRegStudied.setColumns(10);
    TxtSpnrRegStudied.setBounds(354, 347, 119, 28);
    frame.getContentPane().add(TxtSpnrRegStudied);
    
    txtCcodetoStudied = new JTextField();
    txtCcodetoStudied.setText("Kurskod");
    txtCcodetoStudied.setColumns(10);
    txtCcodetoStudied.setBounds(354, 380, 119, 28);
    frame.getContentPane().add(txtCcodetoStudied);
    
    JButton btnNewButton = new JButton("Sätt betyg");
    btnNewButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	String spnr = TxtSpnrRegStudied.getText();
        	String ccode = txtCcodetoStudied.getText();
        	String grade = comboBoxGrade.getSelectedItem().toString();
        	
        	try {
        		controller.addStudentToStudied(spnr, ccode, grade);
        	} catch (SQLException e11) {
        		
        	}
        	
        }
    });
    btnNewButton.setBounds(356, 448, 117, 29);
    frame.getContentPane().add(btnNewButton);
    
    JButton registrera1 = new JButton("Registera");
    registrera1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	    		String spnr = txtPersonnummer.getText().trim();
    	    		String ccode = comboBox.getSelectedItem().toString();
    	    		try {
    	    			controller.addStudentToCourse(spnr, ccode);
    	    			
    	    			}catch(SQLException e12) 
    	    		{
    	    				e12.printStackTrace();
    	    		} 
    	    	}
    	    });
    registrera1.setBounds(6, 554, 87, 29);
    frame.getContentPane().add(registrera1);
    
    JButton btnRaderaStudent = new JButton("Radera Student");
    btnRaderaStudent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String spnr = txtPersonnummer.getText().trim();
    		try {
    			controller.deleteStudent(spnr);
    		} catch (SQLException e13) {
    			e13.printStackTrace();
    		}
    	}
    	
    });
    btnRaderaStudent.setBounds(12, 477, 132, 29);
    frame.getContentPane().add(btnRaderaStudent);
    
    JButton btnRaderaKurs = new JButton("Radera Kurs");
    btnRaderaKurs.addActionListener ( new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String ccode = txtKursnummer.getText().trim();
    		comboBox.removeItem(ccode);
    		try {
    			controller.deleteCourse(ccode);
    		} catch (SQLException e14) {
    			e14.printStackTrace();
    		}
    	}    
});
    btnRaderaKurs.setBounds(174, 477, 134, 29);
    frame.getContentPane().add(btnRaderaKurs);
    
    txtKurskodgrade = new JTextField();
    txtKurskodgrade.setText("Kurskod");
    txtKurskodgrade.setColumns(10);
    txtKurskodgrade.setBounds(633, 40, 119, 28);
    frame.getContentPane().add(txtKurskodgrade);
    
    JScrollPane spCourseStudents = new JScrollPane();
    spCourseStudents.setBounds(633, 114, 294, 153);
    frame.getContentPane().add(spCourseStudents);
    
    String[] colCourseStudents = {"Student ID", "Course ID","grade"};
    DefaultTableModel tableCourseStudents = new DefaultTableModel (colCourseStudents, 0);
    
        
    JTable CourseStudents = new JTable(tableCourseStudents);
    spCourseStudents.setViewportView(CourseStudents);
    
    
    JButton btnSkStudenter = new JButton("Sök Studenter");
    btnSkStudenter.addActionListener (new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		tableCourseStudents.setRowCount(0);
    		String ccode = txtKurskodgrade.getText().trim();
    		try {
    			ArrayList<Studied> studiedlist = new ArrayList<Studied>();
    			studiedlist = controller.getGradeList(ccode);
    			for ( int i = 0; i < studiedlist.size(); i++ ) {
    				String spnr = studiedlist.get(i).getSpnr();
    				String Ccode = studiedlist.get(i).getCcode();
    				String grade = studiedlist.get(i).getGrade();
    				Object[] colCourseStudents = {spnr, Ccode, grade};
    				tableCourseStudents.addRow(colCourseStudents);
    			}
    		} catch (SQLException e15) {
    			e15.printStackTrace();
    		}
    	}
    });
    btnSkStudenter.setBounds(633, 73, 117, 29);
    frame.getContentPane().add(btnSkStudenter);
    
    String [] colCourseStudent = {"Student ID", "Course ID", "Grade"};
    
    DefaultTableModel tableCourseStudent = new DefaultTableModel (colCourseStudent, 0);
    
    
    JButton btnNewButton_1 = new JButton("Sök Student");
    btnNewButton_1.addActionListener(new ActionListener()
     {
    	public void actionPerformed(ActionEvent e) {
    		tableCourseStudents.setRowCount(0);
    		String spnr =  txtSpnrforGrade.getText().trim();
    		String ccode = txtKurskodgrade.getText().trim();
    		try {
    			ArrayList<Studied> list = new ArrayList<Studied>();
    			list = controller.getGradeForCourse(spnr, ccode);
    			for (int i = 0; i < list.size(); i++) {
    				String Spnr = list.get(i).getSpnr();
    				String Ccode = list.get(i).getCcode();
    				String Grade = list.get(i).getGrade();
    				Object [] colCourseStudents = {Spnr, Ccode, Grade};
    				tableCourseStudents.addRow(colCourseStudents);
    			}
    		} catch (SQLException e16) {
    			e16.printStackTrace();
    		}
    	}
    	
    });
    btnNewButton_1.setBounds(751, 73, 117, 29);
    frame.getContentPane().add(btnNewButton_1);
    
    JButton btnRensastudentLista = new JButton("Rensa");
    btnRensastudentLista.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	    while (tableStudent.getRowCount() > 0) {
    	    	for(int i = 0; i < tableStudent.getRowCount(); i++) {
    	    		tableStudent.removeRow(i);
    	    	}
    	    }
    	}
    }
    );
    btnRensastudentLista.setBounds(12, 265, 117, 29);
    frame.getContentPane().add(btnRensastudentLista);
    
    JButton btnRensakurslista = new JButton("Rensa");
    btnRensakurslista.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		while(tableKurs.getRowCount() > 0) {
    			for(int i = 0; i < tableKurs.getRowCount(); i++) {
    				tableKurs.removeRow(i);
    			}
    		}
    	}
    });
    btnRensakurslista.setBounds(323, 265, 117, 29);
    frame.getContentPane().add(btnRensakurslista);
    
    JButton btnRensastuderatlista = new JButton("Rensa");
    btnRensastuderatlista.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		while(tableCourseStudents.getRowCount() > 0) {
    			for(int i = 0; i < tableCourseStudents.getRowCount(); i++) {
    				tableCourseStudents.removeRow(i);
    			}
    		}
    	}
    });
    btnRensastuderatlista.setBounds(629, 265, 117, 29);
    frame.getContentPane().add(btnRensastuderatlista);
    
    JButton btnRaderaStudentFrn = new JButton("Radera student från kurs");
    btnRaderaStudentFrn.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	String spnr = txtPersonnummer.getText();
    	String ccode = comboBox.getSelectedItem().toString();
    	try {
    		controller.removeStudentFromCourse(spnr, ccode);
    	} catch (SQLException e16) {
    		e16.printStackTrace();
    	}
    	}
    });
    btnRaderaStudentFrn.setBounds(94, 554, 117, 29);
    frame.getContentPane().add(btnRaderaStudentFrn);
    
    txtKurskod = new JTextField();
    txtKurskod.setText("Kurskod");
    txtKurskod.setBounds(633, 347, 134, 28);
    frame.getContentPane().add(txtKurskod);
    txtKurskod.setColumns(10);
    
    JScrollPane spStudies = new JScrollPane();
    spStudies.setBounds(633, 376, 294, 153);
    frame.getContentPane().add(spStudies);
    
    String [] colStudies = {"Student ID", "Student Name"};
    DefaultTableModel tableStudies = new DefaultTableModel(colStudies, 0);
    
    JTable Studies = new JTable(tableStudies);
    spStudies.setViewportView(Studies);
    
    JButton btnNewButton_2 = new JButton("New button");
    btnNewButton_2.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String ccode = txtKurskod.getText().trim();
    		try {
    			ArrayList<Student> studiesList = new ArrayList<Student>();
    			studiesList = controller.getCurrentStudies(ccode);
    			for (int i = 0; i < studiesList.size(); i++) {
    				String Spnr = studiesList.get(i).getSpnr();
    				String sname = studiesList.get(i).getsName();
    				Object [] colStudies = {Spnr, sname};
    				tableStudies.addRow(colStudies);
    			}
    		} catch (SQLException e17) {
    			e17.printStackTrace();
    		}
    		}	
    });
    btnNewButton_2.setBounds(768, 347, 117, 29);
    frame.getContentPane().add(btnNewButton_2);
    
    JButton btnHögstgenomströmning = new JButton("Genomströmning");
    btnHögstgenomströmning.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
				String flow = controller.getStudentFlow();
				txtSkrivInKurskodpersonnummer.setText(flow);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	}
    });
    btnHögstgenomströmning.setBounds(221, 73, 117, 29);
    frame.getContentPane().add(btnHögstgenomströmning);
    
    txtSpnrforGrade = new JTextField();
    txtSpnrforGrade.setText("Personnummer");
    txtSpnrforGrade.setColumns(10);
    txtSpnrforGrade.setBounds(751, 40, 134, 28);
    frame.getContentPane().add(txtSpnrforGrade);
    
    JButton button = new JButton("Rensa");
    button.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		while(tableStudies.getRowCount() > 0) {
    			for(int i = 0; i < tableStudies.getRowCount(); i++) {
    				tableStudies.removeRow(i);
    			}
    		}
    	}
    });
    button.setBounds(629, 528, 117, 29);
    frame.getContentPane().add(button);
    
    JLabel lblProcent = new JLabel("Procent");
    lblProcent.setBounds(239, 513, 99, 16);
    frame.getContentPane().add(lblProcent);
    
    textFieldProcent = new JTextField();
    textFieldProcent.setEditable(false);
    textFieldProcent.setBounds(237, 527, 54, 28);
    frame.getContentPane().add(textFieldProcent);
    textFieldProcent.setColumns(10);
	
    
    JButton btnProcentKurs = new JButton("Beräkna procent");
    btnProcentKurs.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		String ccode = comboBoxGrade.getSelectedItem().toString();
    		try {
    			textFieldProcent.setText(controller.getPercentageGradeA(ccode));
    		
    		} catch (SQLException e18) {
    			e18.printStackTrace();
    		}
    	}
    });
    btnProcentKurs.setBounds(223, 554, 134, 29);
    frame.getContentPane().add(btnProcentKurs);
    
    
    
}
}