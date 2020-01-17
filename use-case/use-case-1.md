# USE CASE: 1 Produce a report on the countries (categorized by the world, their region, their continent) which are organised by the largest population to the smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user*, I want to *produce a report on the countries (categorized by the world, their region, their continent) which are organized by the largest population to the smallest population* so that the users can better filter the information of the countries.

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

### Trigger

A request for countries information is sent to the user.

## MAIN SUCCESS SCENARIO

1. A request for countries information in a certain area (e.g region) is sent to the user.
2. The user captures parameter (e.g. region, continent, the world) to produce countries report.
3. The user extracts information of the countries based on the given parameter.
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.5