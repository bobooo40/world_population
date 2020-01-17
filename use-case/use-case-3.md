# USE CASE: 3 Produce a report on the top N populated countries (categorized by the world, their region, their continent) where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user*, I want to be able to produce a report on the top N populated countries (categorized by the world, their region, their continent) where N is provided by the user so that the exact number of countries on the top of the list can be extracted.

### Scope

Organisation.

### Level

Primary Task.

### Preconditions

Database contains information about the countries.

### Success End Condition

A report is available for users.

### Failed End Condition

No report is produced (or) the system produces a false report.

### Primary Actor

User

### Triggers

A request for countries information is sent to the user.
The user needs the report for work.

## MAIN SUCCESS SCENARIO

1. A request for top *N* populated countries information is sent to the user.
2. The user captures parameter (e.g. region, continent, the world) and top *N* populated countries to produce the report.
3. The user extracts information of the countries based on the given parameter and *N*.
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.6