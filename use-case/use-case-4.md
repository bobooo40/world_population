# USE CASE: 4  Produce a report on the top N populated cities (categorized by the world, their region, their continent, their country, or their district) where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user*, I want to be able to produce a report on the top N populated cities (categorized by the world, their region, their continent, their country, or their district) where N is provided by the user so that the exact number of cities on the top of the list can be extracted.

### Scope

Organisation.

### Level

Primary Task.

### Preconditions

Database contains information about the cities.

### Success End Condition

A report is available for users.

### Failed End Condition

No report is produced (or) the system produces a false report.

### Primary Actor

User

### Triggers

1. A request for top *N* populated cities information is sent to the user.
2. The user needs the report for work.

## MAIN SUCCESS SCENARIO

1. A request for top *N* populated cities information in a certain area (the world, their region, their continent, their country, or their district) is sent to the user.
2. The user captures parameter (the world, their region, their continent, their country, or their district) and top *N* populated cities to produce the report.
3. The user extracts information of the cities based on the given parameter and *N*.
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.6