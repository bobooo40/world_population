# USE CASE: 7 Generate a report on the population of the people, living in cities, and not living in cities in a certain area

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *user*, I want to be able to generate a report on the population of the people, people living in cities, and people not living in each of the following areas: continent, region, country.

### Scope

Organisation.

### Level

Primary Task.

### Preconditions

Database contains information about the population.

### Success End Condition

A report is available for users.

### Failed End Condition

No report is produced (or) the system produces a false report.

### Primary Actor

User

### Triggers

1. A request for population information (people living in cities and those who are not) is sent to the user.
2. The user needs the report for work.

## MAIN SUCCESS SCENARIO

1. A request for population information in a certain area (parameter) is sent to the user.
2. The user captures parameter (a continent, a country, a region) to produce the report.
3. The user extracts information of the specified population.
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.7