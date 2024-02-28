package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import commands.DropDoctor;
import commands.AssignDoctor;
import containers.PatientMapAccess;
import entities.Doctor;
import entities.Patient;

/**
 * The panel to display the information for a doctor, and accept operations on
 * the doctor. The
 * panel gives the doctor's name. The patients of the doctor are given, and the
 * user has the option to add another patient or
 * remove a patient.
 */
public class DoctorPanel extends JPanel {
    /**
     * Create the panel to display the doctor's information and accept operations on
     * the patient.
     *
     * @param doctor the patient whose information is to be displayed and on whom
     *               operations can be
     *               done
     */
    public DoctorPanel(Doctor doctor) {
        /*
         * The creation of the panel is placed in another method as it needs to be
         * invoked whenever
         * the doctor information of the patient is changed.
         */
        build(doctor);
    }

    /**
     * Fill in the panel to display the patient's information and accept operations
     * on the doctor.
     *
     * @param doctor the doctor whose information is to be displayed and on whom
     *               operations can be
     *               done
     */
    private void build(Doctor doctor) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel("Name: " + doctor.getName()));
        // add(new JLabel("Health number: " + patient.getHealthNumber()));

        add(new JLabel("  ")); // blank line in the panel for spacing
        add(new JLabel("Patients"));
        for (Patient patient : PatientMapAccess.dictionary().values()) {
            if (doctor.hasPatient(patient.getHealthNumber())) {
                JPanel p = listPatientPanel(doctor, patient);
                add(p);
                p.setAlignmentX(Component.LEFT_ALIGNMENT);
            }
        }

        // add an empty panel to force the add doctor and exit components to the bottom
        JPanel emptyPanel = new JPanel();
        add(emptyPanel);
        emptyPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel addPatientPanel = addPatientPanel(doctor);
        add(addPatientPanel);
        addPatientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        addPatientPanel.setMaximumSize(addPatientPanel.getPreferredSize());

        add(new JLabel("  ")); // blank line in the panel for spacing
        final JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    /**
     * A panel to display the name of a doctor for the patient. Also, a button is
     * provided to remove
     * the association of this patient with the doctor.
     *
     * @param doctor  a current doctor
     * @param patient the patient of the doctor
     * @return the panel to display the name of the doctor, with a button to remove
     *         the
     *         patient-doctor association
     */
    private JPanel listPatientPanel(final Doctor doctor, final Patient patient) {
        JPanel patientPanel = new JPanel();
        patientPanel.add(new JLabel("  "));
        patientPanel.add(new JLabel(patient.getName()));
        JButton removeButton = new JButton("remove");
        patientPanel.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                DropDoctor dropAssoc = new DropDoctor();
                dropAssoc.dropAssociation(doctor.getName(), patient.getHealthNumber());
                if (dropAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, dropAssoc.getErrorMessage());
                }
            }
        });
        return patientPanel;
    }

    /**
     * A panel to add a doctor-patient association for this doctor. The panel as a
     * prompt to enter
     * the doctor's name, and a field to enter the name.
     *
     * @param doctor the current doctor
     * @return a panel to associate a new doctor with this patient
     */
    private JPanel addPatientPanel(final Doctor doctor) {
        JPanel addPatientPanel = new JPanel();
        addPatientPanel.add(new JLabel("Add patient"));
        final JTextField textField = new JTextField(10);
        addPatientPanel.add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String input = textField.getText();
                int HealthNum = Integer.parseInt(input);
                AssignDoctor addAssoc = new AssignDoctor();
                addAssoc.assignDoctor(doctor.getName(), HealthNum);
                if (addAssoc.wasSuccessful()) {
                    // recreate the panel as it has changed
                    removeAll();
                    build(doctor);
                    // revalidate();
                    PatientFrame frame1 = new PatientFrame(HealthNum);
                    frame1.setLocation(350, 350);
                    frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame1.setVisible(true);
                    revalidate();
                } else {
                    JOptionPane.showMessageDialog(DoctorPanel.this, addAssoc.getErrorMessage());
                }
            }
        });
        return addPatientPanel;
    }

    public static final long serialVersionUID = 1;
}