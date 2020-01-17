# USE CASE: 9 Generate a report on the number of people who speak different languages, and the percentage of the world population
## CHARACTERISTIC INFORMATION

### Goal in Context

The organisation wants to be able to generate a report on the number of people who speak the languages in the parentheses (Chinese, English, Hindi, Spanish, Arabic) from greatest number to smallest, including the percentage of the world population.

### Scope

Organisation.

### Level

Primary Task.

### Preconditions

Database contains information about the population and languages people speak.

### Success End Condition

A report is available for users.

### Failed End Condition

No report is produced (or) the system produces a false report.

### Primary Actor

User

### Triggers

1. A request for the number of people who speak certain languages is sent to the user.
2. The user needs the report for work.

## MAIN SUCCESS SCENARIO

1. A request for the number of people who speak certain languages (parameter) is sent to the user.
2. The user captures parameter (Chinese, English, Hindi, Spanish, Arabic) to produce the report.
3. The user extracts information of the specified the number of people who speak certain languages.
4. The report is produced.

## EXTENSIONS

3. **Parameter doesn't exist**

## SUB-VARIATIONS

none

## SCHEDULE

**DUE DATE**: Release version-0.1-alpha-0.8