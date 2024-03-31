package vitalconnect.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vitalconnect.commons.exceptions.IllegalValueException;
import vitalconnect.model.Appointment;

/**
 * A Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private final String patientName;
    private final String patientIc;
    private final String dateTime;
    private final String endTime;
    private int duration = 0;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientName") String patientName,
                                  @JsonProperty("patientIc") String patientIc,
                                  @JsonProperty("dateTime") String dateTime,
                                  @JsonProperty("endTime") String endTime,
                                  @JsonProperty("duration") int duration) {
        if (patientName == null || patientIc == null || dateTime == null || endTime == null) {
            throw new IllegalArgumentException("Missing fields in Appointment data.");
        }
        this.patientName = patientName;
        this.patientIc = patientIc;
        this.dateTime = dateTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        patientName = source.getPatientName();
        patientIc = source.getPatientIc();
        dateTime = source.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        endTime = source.getEndDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        duration = source.getDuration();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (patientName == null || patientIc == null || dateTime == null || endTime == null) {
            throw new IllegalValueException("Missing fields in Appointment data.");
        }

        LocalDateTime localDateTime;
        LocalDateTime localEndDateTime;
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            localEndDateTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Incorrect format for appointment dateTime. Expected dd/MM/yyyy HHmm.");
        }

        return new Appointment(patientName, patientIc, localDateTime, localEndDateTime, duration);
    }
}

