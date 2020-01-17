# USE CASE: 6 Produce a report on the top N populated capital cities (the world, a region, a continent) where N is provided by the user

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user*, I want to be able to produce a report on the top N populated capital cities (the world, a region, a continent) where N is provided by the user so that the exact number of capital cities on the top of the list can be extracted.

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

1. A request for top *N* populated capital cities information is sent to the user.
2. The user needs the report for work.

## MAIN SUCCESS SCENARIO

1. A request for top *N* populated capital cities information in a certain area (the world, a region, a continent) is sent to the user.
2. The user captures parameter (the world, a region, a continent) and top *N* populated capital cities to produce the report.
3. The user extracts information of the capital cities based on the given parameter and *N* (limitation).
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.6