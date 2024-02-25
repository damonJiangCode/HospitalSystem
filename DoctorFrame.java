package gui;

import javax.swing.JFrame;

import containers.DoctorMapAccess;
import entities.Doctor;


/**
 * The frame for the window to display the information for a doctor, and accept operations on the
 * doctor.
 */
public class DoctorFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Create the frame to display the information for a doctor.
     *
     * @param DocName the name of the doctor
     * @precond DocName is the name of a doctor
     */
    public DoctorFrame(String DocName) {
        Doctor doctor = DoctorMapAccess.dictionary().get(DocName);
        if (doctor != null) {
            setTitle(doctor.getName() + " (" + DocName + ")");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(doctor);
            add(panel);
        } else
            throw new RuntimeException("Invalid Doctor's name " + DocName);
    }

    public static final long serialVersionUID = 1;
}
