package vitalconnect.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.DayPage;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
        calendarView.getDayPage().setContextMenu(new ContextMenu());
        calendarView.setEntryContextMenuCallback(param -> null);
        try {
            customizeDayPageContextMenu();
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
        //get today's appointments
        List<Appointment> filteredAppointments = appointmentList.stream()
                .filter(appointment -> Objects.equals(appointment
                        .getDateTime().toLocalDate(), LocalDate.now()))
                .collect(Collectors.toList());

        for (Appointment app : filteredAppointments) {
            Entry<String> entry = new Entry<>(app.getPatientName() + " " + app.getPatientIc() + "\nend time:"
                    + app.getEndDateTime().toLocalTime());
            entry.changeStartDate(app.getDateTime().toLocalDate());
            entry.changeEndDate(app.getDateTime().toLocalDate());
            entry.changeStartTime(app.getDateTime().toLocalTime());
            entry.changeEndTime(app.getDateTime().toLocalTime().plusHours(1));
            appointmentOfTheDay.addEntries(entry);
        }
    }

    private void customizeDayPageContextMenu() {
        // Get the DayPage instance from the calendar view
        DayPage dayPage = calendarView.getDayPage();

        // Create a custom context menu
        ContextMenu customContextMenu = new ContextMenu();

        // Add items you want to keep in the context menu
        MenuItem item1 = new MenuItem("Custom Option 1");
        MenuItem item2 = new MenuItem("Custom Option 2");
        SeparatorMenuItem separator = new SeparatorMenuItem();
        customContextMenu.getItems().addAll(item1, item2, separator);

        // Set the custom context menu to the DayPage
        dayPage.setContextMenu(customContextMenu);
    }

    protected CalendarView getCalendarView() {
        return calendarView;
    }
}
