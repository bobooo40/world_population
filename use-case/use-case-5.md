# USE CASE: 5 Produce a report on all of the capital cities (categorized by the world, a region, a continent) which are organised by the largest population to the smallest

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user*, I want to *produce a report on the capital cities (categorized by the world, a region, a continent) which are organized by the largest population to the smallest population* so that the users can better filter the information of the capital cities.

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

1. A request for capital cities information is sent to the user.
2. The user needs the report for work.

## MAIN SUCCESS SCENARIO

1. A request for capital cities information in a certain area (parameter) is sent to the user.
2. The user captures parameter (a region, a continent, the world) to produce capital cities report.
3. The user extracts information of the capital cities based on the given parameter.
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.7