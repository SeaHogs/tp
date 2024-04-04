---
layout: page
title: User Guide
---
## Introduction

Welcome to **vitalConnect**, your all-in-one desktop application for efficient clinic management, optimized for both Command Line Interface (CLI) and Graphical User Interface (GUI). Designed to streamline your clinic management tasks, VitalConnect offers the speed of a CLI with the convenience of a GUI, allowing you to effortlessly organize your patient and appointments with just a few keystrokes.

If you are relatively good at typing, having the benefit of CLI of reducing time of dragging your mouse around, vitalConnect empowers you to manage your appointments swiftly and effectively. With its intuitive interface and robust features, you can add, delete, and search for appointments, track medical information, as well as check patient contact for communication with ease.

This user guide serves as your comprehensive manual for navigating vitalConnect's features and functionalities. From quick-start instructions to detailed command summaries, you'll find everything you need to maximize your productivity and streamline your management workflow.

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
    - [Adding a patient](#adding-a-patient--add)
    - [Editing identification information](#editing-identification-information--edit)
    - [Deleting a patient](#deleting-a-patient--delete)
    - [Listing all patients](#listing-all-patients--list)
    - [Adding contact information](#adding-contact-information--addc)
    - [Editing contact information](#editing-the-contact-information--editc)
    - [Deleting contact information](#deleting-contact-information--deletec)
    - [Listing contact information](#listing-contact-information--listc)
    - [Adding medical information](#adding-medical-information--addm)
    - [Editing medical information](#editing-the-medical-information--editm)
    - [Deleting medical information](#deleting-medical-information--deletem)
    - [Listing medical information](#listing-medical-information--listm)
    - [Adding an appointment](#adding-an-appointment--adda)
    - [Editing an appointment](#editing-an-appointment--edita)
    - [Deleting an appointment](#deleting-an-appointment--deletea)
    - [Listing appointments](#list-out-appointments--lista)
    - [Locating patients by name](#locating-patients-by-name--find)
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

5. Type `cd` followed by the location of the folder that you are putting the `vitalconnect.jar` file in. Find out more [here](https://www.wikihow.com/Change-Directories-in-Command-Prompt#:~:text=Open%20the%20Command%20Prompt%20(CMD,the%20root%20of%20the%20drive.)

6. Type `java -jar vitalConnect.jar` and press Enter to launch java and run the application. A GUI should appear in a few seconds.

7. Type any command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all patients.

   * `add ic/S1234567D n/John Doe` : Adds a patient named `John Doe` to the Clinic.

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

### Adding a patient : `add`

Adds a patient to the clinic.

Format: `add n/NAME ic/NRIC`

Examples:
* `add n/John Doe ic/S1234567D`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Listing all patients : `list`

Shows a list of all patients in the clinic.

Format: `list`

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

### Deleting a patient : `delete`

Deletes the specified patient from the clinic.

Format: `delete INDEX`

* Deletes the patient at the specified `INDEX`.
* The index refers to the index number shown in the displayed patient list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd patient in the clinic.
* `find Betsy` followed by `delete 1` deletes the 1st patient in the results of the `find` command.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Adding contact information : `addc`

Adds contact information to a patient in the clinic.
Format: `addc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

**:information_source: Additional notes about the command format:**<br>
* Items in square brackets are optional.<br>
  e.g `addc ic/S1234567D p/91234567 e/test@email.com`

* At least one of the optional fields must be provided.
* Rules for phone number: At least 3 digits.
* Emails should be of the format local-part@domain and adhere to the following constraints:
  1. The local-part should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters.
  2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods.
     The domain name must:
      - end with a domain label at least 2 characters long
      - have each domain label start and end with alphanumeric characters
      - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.

Examples:
* `addc ic/S1234567D p/91234567`
* `addc ic/S1234567D p/91234567 e/test@email.com `
* `addc ic/S1234567D p/91234567 e/test@email.com a/123, Clementi Rd, 1234665`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing contact information : `editc`

Edits contact information of a patient in the clinic. It is also used to add or delete certain field of the contact information.

Format: `editc ic/NRIC [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS]`

* We call `p/`, `e/`, and `a/` as the `optional fields`.
* At least one of the `optional fields` must be provided.
* If want to delete an `optional field`, leave the `optional field` empty.
* If the `optional field` already exist a value, the value will be updated with the new value.
* If the `optional field` does not previously hold a value, the new value will be added to the `optional field`.

Examples:
* `editc ic/S1234567D p/91234567` will result in the phone number of the patient with NRIC `S1234567D` being updated to `91234567`.
* `editc ic/S1234567D a/` will result in the address of the patient with NRIC `S1234567D` being deleted.
* Suppose the patient now only has a phone number, `editc ic/S1234567D e/email@test.com` will result in the email of the patient with NRIC `S1234567D` being updated to `email@test.com`.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Listing contact information : `listc`

Lists all patients with contact information.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Deleting contact information : `deletec`

Deletes contact information from a patient in the clinic.

Format: `deletec ic/NRIC`

Examples:
* `deletec ic/S1234567D` will result in the contact information of the patient with NRIC `S1234567D` being deleted.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Adding medical information : `addm`

Adds medical information to a patient in the clinic.

Format: `addm ic/NRIC h/HEIGHT w/WEIGHT [t/ALLERGY]…​`

**:information_source: Additional notes about the command format:**<br>

* Items in square brackets are optional.<br>
  e.g `addm ic/S1234567D h/163 w/50`

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/ALLERGY]…​` can be used as ` ` (i.e. 0 times), `t/Amoxicillin`, `t/insulin t/iodine` etc.
  e.g. `[t/ALLERGY]…​` can be used as ` ` (i.e. 0 times), `t/Amoxicillin`, `t/insulin t/iodine` etc.

Examples:
* `addm ic/S1234567D h/163 w/50`
* `addm ic/S1234567D h/163 w/50 t/insulin t/iodine`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Listing medical information : `listm`

Lists all patients with medical information.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Deleting medical information : `deletem`

Deletes medical information from a patient in the clinic.

Format: `deletem ic/NRIC`

Examples:
* `deletem ic/S1234567D` will result in the medical information of the patient with NRIC `S1234567D` being deleted.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Adding an appointment : `adda`

Adds an appointment for an exist patient to the appointment list.

Format: `adda ic/NRIC s/START TIME d/DURATION`

* The patient(ic) should already exist in the patient list.
* The start time should be in the format: DD/MM/YYYY HHmm.
* The start time should not be earlier than now time.
* The time length of one unit of duration equals 15 minutes.
* The input for duration should be larger than 0.

Examples:
* `adda ic/S1234567D s/ 02/02/2024 1300 d/2` 
* will add an appointment for the patient with NRIC `S1234567D` start from 2nd February 2024 at 1:00 PM and end at 1:30 PM.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing an appointment : `edita`

Edits the start time and duration of an appointment of an existing patient.

Format: `edita INDEX s/START TIME d/DURATION`

* The index should not be out of range nor negative.
* The start time should be in the format: DD/MM/YYYY HHmm.
* The start time should not be earlier than now time.
* The time length of one unit of duration equals 15 minutes.
* The input for duration should be larger than 0.
* The edited appointment should not overlap with other appointments.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Deleting an appointment : `deletea`

Delete an exist appointment from the appointment list by providing the index of the appointment
in the list.

Format: `deletea INDEX`

* The index should not be out of range nor negative.

Examples:
* `deletea 1`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### List out appointments : `lista`

List out all the appointments in the appointment list.

Format: `lista`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Clearing all entries : `clear`

Clears all entries from the clinic.

Format: `clear`

> [!CAUTION]
> This command will delete all the patients from the clinic. Please use with cautious.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Exiting the program : `exit`

Exits the program.

Format: `exit`

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Saving the data

Clinic data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[<span style="font-size: small;">Back to table of contents</span>](#toc)

### Editing the medical information : "editm"

Edit the medical information of an existing patient.

Format: `editm ic/NRIC h/HEIGHT w/WEIGHT -o at/ALLERGY…​`

* All fields are optional field but at least one should be present.
* There should only be one field `-o`.
* The order of `-o` and at does not matter, as long as `-o` exist in current command, 
all allergyTags will be new tags overwriting the old tags.

Prefix explanation:
- `-o` will set mode for this command to overwrite.
- `at/ALLERGY` append this tag to existing tag.

Example:
* `editm ic/G1234567J w/100, -o at/milk at/egg`

This will change the weight of patient with ic G1234567J to 100 and
overwrite allergy tag to milk and egg.

> [!CAUTION]
> Use if prefix `-o` will delete all existing tag, including the added tag in current command before it. 
> Please use with cautious.

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

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add** | `add ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/James Doe`
**Edit** | `edit ic/NRIC n/NAME` <br> e.g., `add ic/S1234567D n/John Doe`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
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
**Undo** | `undo`
**Clear** | `clear`
**Exit** | `exit`

[<span style="font-size: small;">Back to table of contents</span>](#toc)
