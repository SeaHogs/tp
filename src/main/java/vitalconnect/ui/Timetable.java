package vitalconnect.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import vitalconnect.model.Appointment;

/**
 * A custom UI component representing a timetable view with appointments displayed using a calendar view.
 * This class extends the UiPart class to load its UI from an FXML file.
 */
public class Timetable extends UiPart<Region> {
    private static final String FXML = "Timetable.fxml";
    private final CalendarView calendarView;
    private final ObservableList<Appointment> appointmentList;

    /**
     * Constructs a new Timetable instance.
     * @param appointmentList The list of appointments to be displayed in the timetable.
     */
    public Timetable(ObservableList<Appointment> appointmentList) {
        super(FXML);
        this.appointmentList = appointmentList;
        calendarView = new CalendarView();
        setUpCalendarView();

        // A listener to update the calendar view when the appointment list changes
        appointmentList.addListener((ListChangeListener<Appointment>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    calendarView.getCalendarSources().get(0).getCalendars().get(0).clear();
                    for (Appointment updatedAppointment : appointmentList) {
                        addAppointmentToCalendar(updatedAppointment);
                    }
                } else if (change.wasRemoved()) {
                    for (Appointment removedAppointment : change.getRemoved()) {
                        removeAppointmentFromCalendar(removedAppointment);
                    }
                } else if (change.wasUpdated()) {
                    // dummy code to test where edit is going
                    // edita does not go through model as expected
                    // calendarView.getCalendarSources().get(0).getCalendars().get(0).clear();
                    // for (Appointment updatedAppointment : appointmentList) {
                        // addAppointmentToCalendar(updatedAppointment);
                    // }
                }
            }
        });
    }

    private void addAppointmentToCalendar(Appointment appointment) {
        Entry<String> entry = new Entry<>(appointment.getPatientName() + " " + appointment.getPatientIc());
        entry.setInterval(appointment.getDateTime(), appointment.getEndDateTime());
        calendarView.getCalendarSources().get(0).getCalendars().get(0).addEntries(entry);
    }

    private void removeAppointmentFromCalendar(Appointment appointment) {
        calendarView.getCalendarSources().get(0).getCalendars().get(0)
                .findEntries(appointment.getPatientName() + " " + appointment.getPatientIc())
                .forEach(entry -> calendarView.getCalendarSources().get(0)
                        .getCalendars()
                        .get(0)
                        .removeEntry((Entry<?>) entry));
    }



    /**
     * Sets up the calendar view with appropriate configurations and adds appointments to display.
     */
    private void setUpCalendarView() {
        // Set up the calendar view
        Calendar appointmentOfTheDay = new Calendar("appointment");
        appointmentOfTheDay.setStyle(Calendar.Style.STYLE2);
        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setShowToolBar(false);

        calendarView.setShowAddCalendarButton(false);

        //adding entries here
        addEntriesToCalendar(appointmentOfTheDay);
        myCalendarSource.getCalendars().add(appointmentOfTheDay);

        calendarView.setShowAddCalendarButton(false);
        calendarView.setShowPrintButton(false);
        calendarView.setShowSourceTrayButton(false);
        calendarView.setShowSearchField(false);
        calendarView.setShowSearchResultsTray(false);
        calendarView.setShowToolBar(false);
        calendarView.setShowDeveloperConsole(false);
        calendarView.setShowPageSwitcher(false);
        calendarView.getCalendarSources().setAll(myCalendarSource);
        calendarView.getDayPage().setContextMenu(null);
        calendarView.setEntryContextMenuCallback(param -> null);
        calendarView.setEntryDetailsPopOverContentCallback(param -> {
            Entry<?> entry = param.getEntry();
            Label label = new Label("Appointment of:" + entry.getTitle() + "\n"
                    + entry.getStartTime() + " - " + entry.getEndTime());
            label.setWrapText(true);
            label.setStyle("-fx-text-fill: darkblue;");
            return label;
        });

        try {
            calendarView.setEntryFactory(param -> null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread updateTimeThread = getUpdateTimeThread();
        updateTimeThread.start();
    }

    private Thread getUpdateTimeThread() {
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 30 seconds
                        sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        return updateTimeThread;
    }

    private void addEntriesToCalendar(Calendar appointmentOfTheDay) {
        List<Appointment> filteredAppointments = new ArrayList<>(appointmentList);

        for (Appointment app : filteredAppointments) {
            Entry<String> entry = new Entry<>(app.getPatientName() + " " + app.getPatientIc());
            entry.changeStartDate(app.getDateTime().toLocalDate());
            entry.changeEndDate(app.getDateTime().toLocalDate());
            entry.changeStartTime(app.getDateTime().toLocalTime());
            entry.changeEndTime(app.getDateTime().toLocalTime().plusHours(1));
            appointmentOfTheDay.addEntries(entry);
        }
    }

    protected CalendarView getCalendarView() {
        return calendarView;
    }
}
