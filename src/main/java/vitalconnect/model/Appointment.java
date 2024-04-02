package vitalconnect.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an appointment with a patient. Each appointment has a patient name
 * and a scheduled date and time.
 */
public class Appointment {
    private String patientName;
    private String patientIc;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int duration;

    /**
     * Constructs an {@code Appointment} with the specified patient name and date/time.
     *
     * @param patientName The name of the patient for the appointment.
     * @param startDateTime The date and time of the appointment.
     * @param endDateTime The time duration of the appointment.
     */
    public Appointment(String patientName, String patientIc, LocalDateTime startDateTime,
                       LocalDateTime endDateTime, int duration) {
        this.patientIc = patientIc;
        this.patientName = patientName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.duration = duration;
    }

    /**
     * Returns the name of the patient associated with this appointment.
     *
     * @return The patient's name.
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Returns the NRIC of the patient associated with this appointment.
     *
     * @return The patient's ic.
     */
    public String getPatientIc() {
        return patientIc;
    }

    /**
     * Returns the date and time of the appointment.
     *
     * @return The date and time of the appointment.
     */
    public LocalDateTime getDateTime() {
        return startDateTime;
    }

    /**
     * Returns the end time of the appointment.
     *
     * @return The date and time of the appointment.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.startDateTime = dateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Appointment getCopy() {
        return new Appointment(getPatientName(), getPatientIc(),
                getDateTime(), getEndDateTime(), getDuration());
    }

    /**
     * Returns a string representation of the appointment, including the patient's name
     * and the date and time of the appointment.
     *
     * @return A string representation of the appointment.
     */
    @Override
    public String toString() {
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Appointment with " + patientName + " from "
            + formatter.format(startDateTime) + " to " + formatter.format(endDateTime);
    }

    /**
     * Checks if this appointment is equal to another object. Two appointments are equal
     * if they have the same patient name, patient NRIC, and date/time.
     *
     * @param that The object to compare with this appointment.
     * @return true if the given object represents an appointment equivalent to this appointment, false otherwise.
     */
    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(that instanceof Appointment)) {
            return false;
        }

        Appointment otherApt = (Appointment) that;
        return patientName.equals(otherApt.patientName)
                && patientIc.equals(otherApt.patientIc)
                && startDateTime.equals(otherApt.startDateTime)
                && endDateTime.equals(otherApt.endDateTime);
    }

}

