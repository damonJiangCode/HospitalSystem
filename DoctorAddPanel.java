package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;


import commands.NewDoctor;


/**
 * The panel to obtain data for the creation of a doctor, and to cause the doctor to be created.
 */
public class DoctorAddPanel extends JPanel {
    /* Declare the components of the panel needed by inner classes. */

    /**
     * A text area to be used to display an error if one should occur.
     */
    JTextArea error = null;

    /**
     * A panel for the entry of the name of a new doctor.
     */
    ValueEntryPanel namePanel;

    /**
     * A panel for the entry of the position of a new doctor.
     */
    ValueEntryPanel SurgeonPanel;


    /**
     * Create the panel to obtain data for the creation of a doctor, and to cause the doctor to be
     * created.
     */
    public DoctorAddPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        // add a label with a prompt to enter the doctor data
        JLabel prompt = new JLabel("Enter Doctor Information");
        prompt.setSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of the doctor's name
        namePanel = new ValueEntryPanel("name");
        namePanel.setSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        // add a panel with the field for the entry of checking the doctor is surgeon or not
        SurgeonPanel = new ValueEntryPanel("Is the doctor a surgeon? (yes or no)");
        SurgeonPanel.setSize(SurgeonPanel.getPreferredSize());
        add(SurgeonPanel);
        SurgeonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());


        // add a button to submit the information and create the doctor
        JButton submitButton = new JButton("Submit");
        submitButton.setSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitListener());
        add(Box.createVerticalGlue());
    }

    /**
     * The class listening for the press of the submit button. It accesses the name
     * entered, and uses them to add a new Doctor to the system.
     */
    private class SubmitListener implements ActionListener {
        /**
         * When the submit button is pressed, access the name, and use
         * them to add a new Doctor to the system.
         */
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                // remove error from the previous submission
                remove(error);
                error = null;
            }
            String name = namePanel.getValueAsString();
            String response = SurgeonPanel.getValueAsString();

            NewDoctor addDoc = new NewDoctor();

            if (response.charAt(0) == 'y' || response.charAt(0) == 'Y') {
                addDoc.addDoctor(name, true);
            }else if(response.charAt(0) == 'n' || response.charAt(0) == 'N'){
                addDoc.addDoctor(name, false);
            }
            if (addDoc.wasSuccessful()) {
                getTopLevelAncestor().setVisible(false);
            }else {
                error = new JTextArea(SplitString.at(addDoc.getErrorMessage(), 40));
                error.setMaximumSize(error.getPreferredSize());
                add(error);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(Box.createVerticalGlue());
                revalidate(); // redraw the window as it has changed
            }
        }
    }

    public static final long serialVersionUID = 1;
}
