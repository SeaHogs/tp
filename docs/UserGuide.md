---
layout: page
title: User Guide
---
## Introduction

Welcome to **vitalConnect**, your all-in-one desktop application for efficient clinic management, optimized for both Command Line Interface (CLI) and Graphical User Interface (GUI). Designed to streamline your clinic management tasks, VitalConnect offers the speed of a CLI with the convenience of a GUI, allowing you to effortlessly organize your patient and appointments with just a few keystrokes.

If you are relatively good at typing, having the benefit of CLI of reducing time of dragging your mouse around, vitalConnect empowers you to manage your appointments swiftly and effectively. With its intuitive interface and robust features, you can add, delete, and search for appointments, track medical information, as well as check patient contact for communication with ease.

So, whether you're a busy professional juggling multiple appointments, vitalConnect is here to simplify your life. Let's dive in and explore how vitalConnect can revolutionize the way you manage your appointment and patient.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick start

For developers, you can head over to our Developer Guide [here](https://ay2324s2-cs2103t-w08-2.github.io/tp/DeveloperGuide.html) for the technical details.

For first time users, you can first go through the steps listed under [Installation](https://ay2324s2-cs2103t-w08-2.github.io/tp/UserGuide.html#installation) and then check out the table of contents to experiment with whichever command that you are interested in.

## Table of Contents

<a id="toc"></a>

- [Introduction](#introduction)
- [Quick start](#quick-start)
- [Installation](#installation)
- [Commands](#Commands)
    - [Viewing help](#viewing-help--help)
    - [Patient Management](#patient-management)
        - [Adding a patient](#adding-a-patient--add)
        - [Editing identification information](#editing-identification-information--edit)
        - [Deleting a patient](#deleting-a-patient--delete)
        - [Listing all patients](#listing-all-patients--list)
    - [Contact Management](#contact-management)
        - [Adding contact information](#adding-contact-information--addc)
        - [Editing contact information](#editing-the-contact-information--editc)
        - [Deleting contact information](#deleting-contact-information--deletec)
        - [Listing contact information](#listing-contact-information--listc)
    - [Medical Information Management](#medical-information-management)
        - [Adding medical information](#adding-medical-information--addm)
        - [Editing medical information](#editing-the-medical-information--editm)
        - [Deleting medical information](#deleting-medical-information--deletem)
        - [Listing medical information](#listing-medical-information--listm)
    - [Appointment Management](#appointment-management)
        - [Adding an appointment](#adding-an-appointment--adda)
        - [Editing an appointment](#editing-an-appointment--edita)
        - [Deleting an appointment](#deleting-an-appointment--deletea)
        - [Listing appointments](#list-out-appointments--lista)
    - [Other features](#other-features)
        - [Locating patients by name](#locating-patients-by-name--find)
        - [Locating appointments by patient](#locating-appointments-by-patient--finda)
        - [Undoing last command](#undo--undo)
        - [Clearing all entries](#clearing-all-entries--clear)
        - [Exiting the program](#exiting-the-program--exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)
- [Known issues](#known-issues)
- [Command summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Installation

1. Ensure you have Java `11` or above installed in your Computer. You learn how to do so [here](https://www.java.com/en/download/help/download_options.html)

2. Download the latest `vitalconnect.jar` from [here](https://github.com/AY2324S2-CS2103T-W08-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your vitalConnect.

4. Open a command terminal or learn how to do so [here](https://www.google.com/search?q=how+to+open+a+command+terminal&rlz=1C1GCEA_enSG1015SG1015&oq=how+to+open+a+command+terminal&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBCDQzMThqMGoxqAIAsAIA&sourceid=chrome&ie=UTF-8)

5. Type `cd` followed by the location of the folder that you are putting the `vitalconnect.jar` file in. Find out more [here](https://www.wikihow.com/Change-Directories-in-Command-Prompt)

6. Type `java -jar vitalConnect.jar` and press Enter to launch java and run the application. A GUI should appear in a few seconds.

7. Type any command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients.

   * `add n/John Doe ic/S1234567D` : Adds a patient named `John Doe` with nric `S1234567D` to the Clinic.

   * `delete 3` : Deletes the 3rd patient shown in the current list.

   * `clear` : Deletes all patients.

   * `exit` : Exits the app.

Refer to the [Commands](#CommandsCommands) below for specific details for each of the commands.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

**:information_source: General notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME ic/NRIC`, `NAME` and `NRIC` are parameters which can be used as `add n/John Doe ic/S1234567D`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `ic/NRIC p/PHONE_NUMBER`, `p/PHONE_NUMBER ic/NRIC` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

## Patient Management
### Adding a patient : `add`

Adds a patient to the clinic using their identification information.

Format: `add ic/NRIC n/NAME`

* The NRIC **must** be a valid NRIC.

Examples:
* `add ic/S1234567D n/James Doe`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing identification information : `edit`

Edits a patient's identification information.

Format: `edit ic/NRIC n/NAME`

* The NRIC must be a NRIC of an already existing patient.
* If you would like to change the name instead of the NRIC, create a new patient using `add` then use `delete` on the outdated version.

Examples:
* `edit ic/S1234567D n/John Doe`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Deleting a patient : `delete`

Deletes the specified patient from the clinic.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* If the panel is currently not showing any patient list (e.g. showing appointment list), the default patient list is the general clinic patient list (which contains all the patients).
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
The deletion of a patient will result in the deletion of all the contact and medical information as well as all the appointments of the patient. <br> If accidentally delete a patient, can use `undo` command to recover the deleted patient. </div>

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the clinic.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

[<span style="font-size: small;">Back to Top</span>](#top)

### Listing all patients : `list`

Shows a list of all patients in the clinic.

Format: `list`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

## Contact Management

### Adding contact information : `addc`

Adds the contact information to a patient in the clinic.

Format: `addc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

**:information_source: Additional notes about the command format:**<br>
Items in square brackets are optional.<br>
  e.g `addc ic/S1234567D p/91234567 e/test@email.com`

* The NRIC must be a NRIC of an already existing patient.
* At least one of the optional fields must be provided.
* Phone number should be of 3 to 15 digits long.
* Emails should be of the format local-part@domain and adhere to the following constraints:
  1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters, and the special characters should not be adjacent to each other.
  2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
     The domain name must:
      - end with a domain label at least 2 characters long
      - have each domain label start and end with alphanumeric characters
      - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.
* Address has a max length of 50 characters, and it should not be empty upon adding.

Examples:
* `addc ic/S1234567D p/91234567`
* `addc ic/S1234567D p/91234567 e/test@email.com `
* `addc ic/S1234567D p/91234567 e/test@email.com a/123, Clementi Rd, 1234665`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing contact information : `editc`

Edits the contact information of a patient in the clinic. It is also used to add or delete certain field of the contact information.

Format: `editc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

**:information_source: Additional notes about the command format:**<br>
Items in square brackets are optional.<br>
  e.g `addc ic/S1234567D p/91234567 e/test@email.com`

* At least one of the `optional fields` must be provided.
* To delete an `optional field`, leave the `VALUE` part empty.
* If the `VALUE` part is not empty, the corresponding patient contact's field will either be updated or added with the new value.
* If all three fields of contact information (phone, email, and address) become empty, the contact information of the patient will be considered deleted. If one want to add a new contact information, please use `addc` command.

Examples:
* `editc ic/S1234567D p/91234567` will result in the phone number of the patient with NRIC `S1234567D` being updated to `91234567`.
* `editc ic/S1234567D a/` will result in the address of the patient with NRIC `S1234567D` being deleted.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Deleting contact information : `deletec`

Deletes the contact information of a patient in the clinic.

Format: `deletec ic/NRIC`

Examples:
* `deletec ic/S1234567D` will result in the deletion of the contact information of the patient with the NRIC `S1234567D`.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Listing contact information : `listc`

Lists all patients with contact information.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

## Medical Information Management

### Adding medical information : `addm`

Adds the medical information to a patient in the clinic.

Format: `addm ic/NRIC h/HEIGHT w/WEIGHT [t/ALLERGY]…​`

* The NRIC must be a NRIC of an already existing patient.

**:information_source: Additional notes about the command format:**<br>
Items in square brackets are optional.<br>
  e.g `addm ic/S1234567D h/163 w/50`
Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/ALLERGY]…​` can be used as ` ` (i.e. 0 times), `t/Amoxicillin`, `t/insulin t/iodine` etc.
  e.g. `[t/ALLERGY]…​` can be used as ` ` (i.e. 0 times), `t/Amoxicillin`, `t/insulin t/iodine` etc.

Examples:
* `addm ic/S1234567D h/163 w/50`
* `addm ic/S1234567D h/163 w/50 t/insulin t/iodine`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing medical information : `editm`

Edit the medical information of an existing person.

Format: `editm ic/NRIC [h/HEIGHT] [w/WEIGHT] [-o] [at/ALLERGY…​]`

* At least one of the optional fields must be provided.
* The overwrite notation `-o` should only appear once.
* `-o` can be placed at any position in the command.

Prefix explanation:
- `w/WEIGHT`, `h/HIGHT`: Change the current wight and height value to WEIGHT and HEIGHT.
- `at/ALLERGY`: Append this tag to existing tag.
- `-o`: Set mode for this command to overwrite, meaning all existing tag will be deleted and replaced by the new tags.

Example:
* `editm ic/S1234567D w/100 -o at/milk at/egg`

This will change the weight of person with ic S1234567D to 100 and
overwrite allergy tag to milk and egg.
* Noted that command achieving same effect could be `editm ic/S1234567D w/100 at/milk at/egg -o` or `editm ic/S1234567D -o w/100 at/milk at/egg`.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Use of prefix `-o` will delete all existing tag, including the added tag in current command before it. </div>

[<span style="font-size: small;">Back to Top</span>](#top)

### Deleting medical information : `deletem`

Deletes the medical information of a patient in the clinic.

Format: `deletem ic/NRIC`

Examples:
* `deletec ic/S1234567D` will result in the deletion of the medical information of the patient with the NRIC `S1234567D`.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Listing medical information : `listm`

Lists all patients with medical information.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

## Appointment Management

### Adding an appointment : `adda`

Adds an appointment for an exist patient to the appointment list.

Format: `adda ic/NRIC s/START_TIME d/DURATION`

* The NRIC must be a NRIC of an already existing patient.
* The START TIME should be in the format: DD/MM/YYYY HHmm where DD is day, MM is month, YYYY is year, HH is hour and mm is minute.
* The START TIME should not be earlier than the current time.
* The time length of one unit of DURATION equals 15 minutes.
* The input for DURATION should be larger than 0.

Examples:
* `adda ic/S1234567D s/02/06/2024 1300 d/2` 
* This will add an appointment for the patient with NRIC `S1234567D` start from 2nd June 2024 at 1:00 PM and end at 1:30 PM.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing an appointment : `edita`

Edits the start time and/or duration of an appointment of an existing person.

__Format:__ `edita INDEX [s/START_TIME] [d/DURATION]`

Edit both start time and duration: `edita INDEX s/START_TIME d/DURATION`

Edit only the start time: `edita INDEX s/START_TIME` 

Edit only the time duration: `edita INDEX d/DURATION` 

__`INDEX`: Index of the to be edited appointment in the appointment list__
* The index should not be out of range nor negative.

__`s/START_TIME`: Start time of the appointment__
* The start time should be in the format: __DD/MM/YYYY HHmm__.
* The start time should __not be earlier__ than now time.
* The edited  appointment time period should not overlap with other appointments.

__`d/DURATION`: the time length of the appointment__

__The input should be the number of duration unit:__
* The time length of one unit of duration equals __15 minutes__.
* The input for duration should be larger than 0.

Examples:
* `edita 1 s/02/02/2025 1300 d/4`
  * This change the time of the appointment of index 1 to Feb 2 2025 at 1pm and end at 2pm.
* `edita 1 s/02/02/2025 1300`
    * Only change the appointment start time to Feb 2 2025 at 1pm, the time duration remains the same.
* `edita 1 d/4`
    * Only change the time length of the appointment to one hour, the start time remains the same.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Deleting an appointment : `deletea`

Deletes an exist appointment from the appointment list by providing the index of the appointment in the list.

Format: `deletea INDEX`

* Deletes the appointment at the specified `INDEX`.
* The index refers to the index number shown in the displayed list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deletea 1`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### List out appointments : `lista`

List out all the appointments for the clinic.

Format: `lista`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Locating patients by name : `find`

Finds patients whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Patients matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `John Doe` and `John Bard`</br>

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Undoing last command : `undo`

Undoing the last command made.

Format: `undo`

* Commands such as `list` will not be considered as a command to undo and will undo the command before it if possible.
* If the user adds a patient and then use the `list` command. Undo will undo the addition of the patient.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Locating appointments by patient : `finda`

Find and list out all the appointment of a specific patient in the appointment list.

Format: `finda ic/NRIC`

* The NRIC must be a NRIC of an already existing patient.

Examples:
* `finda ic/S1234567D`

[<span style="font-size: small;">Back to Top</span>](#top)

## Other Features

### Undoing the previous command : `undo`

Undoes the previous command that changes the data.

Format: `undo`

### Clearing all entries : `clear`

Clears all entries from the clinic.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
This command will delete all the patients from the clinic. </div>

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Saving the data

Clinic data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If you exit the program through other means, your data will still be saved. Refer to [here](https://se-education.org/addressbook-level3/UserGuide.html#saving-the-data) for more info.
</div>

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing the data file

Clinic data are saved automatically as a JSON file `[JAR file location]/data/clinic.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Clinic will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Clinic to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Clinic home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **Regarding the edit command**, the appointment does not reflect an edited person's name correctly
3. **Regarding the undo command**, the undo command does not undo the deleted appointments associated to a person
4. **Regarding the appointments**, the deletion of patient data via 'delete' does not clear the data for the appointments.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/James Doe`
**Edit** | `edit ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/John Doe`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**List** | `list`
**Addc** | `addc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br> e.g., `addc ic/S1234567D p/91234567 e/test@email.com a/123, Clementi Rd, 1234665`
**Editc** | `editc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]` <br> e.g., `editc ic/S1234567D p/91234567 e/testing@email.com a/Blk 123 Street 4`
**Deletec** | `deletec ic/NRIC` <br> e.g., `deletec ic/S1234567D`
**Listc** | `listc`
**Addm** | `addm ic/NRIC h/HEIGHT w/WEIGHT [t/ALLERGY]…​` <br> e.g., `addm ic/S1234567D h/163 w/50 t/insulin t/iodine`
**Editm** | `editm ic/NRIC h/HEIGHT w/WEIGHT [t/ALLERGY]…` <br> e.g., `editm ic/S1234567D h/165 w/55 t/aspirin`
**Deletem** | `deletem ic/NRIC` <br> e.g., `deletem ic/S1234567D`
**Listm**   | `listm`
**Adda** | `adda ic/NRIC s/DD/MM/YYYY HHMM d/DURATION` <br> e.g., `adda ic/S1234567D s/02/02/2024 1300 d/4`
**Edita** | `edita INDEX s/DD/MM/YYYY HHMM d/DURATION` <br> e.g., `edita 1 s/02/02/2024 1400 d/4`
**Deletea** | `deletea INDEX` <br> e.g., `deletea 1`
**Lista**   | `lista`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find John Doe`
**Finda** | `finda ic/NRIC`<br> e.g., `finda ic/S1234567D
**Undo** | `undo`
**Clear** | `clear`
**Exit** | `exit`

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/James Doe`
**Edit** | `edit ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/John Doe`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**List** | `list`
**Addc** | `list`
**Editc** | `list`
**Deletec** | `list`
**Addm** | `list`
**Editm** | `list`
**Deletem** | `list`
**Listm** | `listm`
**Adda** | `list`
**Edita** | `list`
**Deletea** | `list`
**Lista** | `lista`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find John Doe`
**Finda** | `finda ic/NRIC`<br> e.g., `finda ic/S1234567D`
**Undo** | `undo`
**Clear** | `clear`
**Exit** | `exit`

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/James Doe`
**Edit** | `edit ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/John Doe`
**Delete** | `delete INDEX` <br> e.g., `delete 3`
**List** | `list`
**Addc** | `list`
**Editc** | `help`