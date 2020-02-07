
# World Population

## Status 


- License [![LICENSE](https://img.shields.io/github/license/bobooo40/world_population.svg?style=flat-square)](https://github.com/bobooo40/world_population)
- Release [![Releases](https://img.shields.io/github/release/bobooo40/world_population/all.svg?style=flat-square)](https://github.com/bobooo40/world_population)
- Develop Build Status [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=develop)](https://travis-ci.org/bobooo40/world_population)
- Build Status [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=master)](https://travis-ci.org/bobooo40/world_population)
- SQL Integration [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=feature/mysql-integration)](https://travis-ci.org/bobooo40/world_population)
- Produce City Report [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=features/produce-cities-report)](https://travis-ci.org/bobooo40/world_population)

- CodeCov [![codecov](https://codecov.io/gh/bobooo40/world_population/branch/master/graph/badge.svg)](https://codecov.io/gh/bobooo40/world_population)



## Functional requirements for World Population

- Sorting functions for population by continent, region and world. 
- Sorting all the countries by contient, region and world. 
- Sorting the countries with user defined number by continent, region and world
- Population of people differentiation in each continent, region and country 

- Sorting function for population with cities by continent, region, district and world
- Sorting all the cities by continent, region, district and world. 
- Sorting all the cities with user defined number by continent, region, district and world

- Sorting function for population with capital cities by continent, region, country, district and world
- Sorting the capital cities by continent, region, country, district and world
- Sorting the capital cities with user defined number by continent, region, country, district and world

- Population of poeple differentiation 
- Population of people differentiation in each continent, region and country

### Country Report
A country report requires the following columns:

- Code.
- Name.
- Continent.
- Region.
- Population.
- Capital.

### City Report
A city report requires the following columns:

- Name.
- Country.
- District.
- Population.

### Capital City Report
A capital city report requires the following columns:
  
- Name.
- Country.
- Population.

### Population Report
For the population reports, the following information is requested:

- The name of the continent/region/country.
- The total population of the continent/region/country.
- The total population of the continent/region/country living in cities (including a %).
- The total population of the continent/region/country not living in cities (including a %).


> 32 requirements of 32 have been implemented, which is 100.0%.

| ID    | Name | Met  | Screenshot |
|-------|------|------|------------|
| 1     | All the countries in the world organised by largest population to smallest. | Yes | image |
| 2     | All the countries in a continent organised by largest population to smallest. | Yes |   |
| 3     | All the countries in a region organised by largest population to smallest. | Yes | image |
| 4     | The top N populated countries in the world where N is provided by the user. | Yes | image |
| 5     | The top N populated countries in a continent where N is provided by the user. | Yes |   |
| 6     | The top N populated countries in a region where N is provided by the user. | Yes | image |
| 7     | All the cities in the world organised by largest population to smallest. | Yes | image |
| 8     | All the cities in a continent organised by largest population to smallest. | Yes |   |
| 9     | All the cities in a region organised by largest population to smallest. | Yes | image |
| 10     | All the cities in a country organised by largest population to smallest. | Yes | image |
| 11    | All the cities in a district organised by largest population to smallest. | Yes |   |
| 12    | The top N populated cities in the world where N is provided by the user. | Yes | image |
| 13    | The top N populated cities in a continent where N is provided by the user. | Yes | image |
| 14    | The top N populated cities in a region where N is provided by the user. | Yes |   |
| 15    | The top N populated cities in a country where N is provided by the user. | Yes | image |
| 16    | The top N populated cities in a district where N is provided by the user. | Yes | image |
| 17    | All the capital cities in the world organised by largest population to smallest. | Yes |   |
| 18    | All the capital cities in a continent organised by largest population to smallest. | Yes | image |
| 19    | All the capital cities in a region organised by largest to smallest. | Yes | image |
| 20    | The top N populated capital cities in the world where N is provided by the user. | Yes |   |
| 21    | The top N populated capital cities in a continent where N is provided by the user. | Yes | image |
| 22    | The top N populated capital cities in a region where N is provided by the user. | Yes | image |
| 23    | The population of people, people living in cities, and people not living in cities in each continent. | Yes |   |
| 24    | The population of people, people living in cities, and people not living in cities in each region. | Yes | image |
| 25    | The population of people, people living in cities, and people not living in cities in each country. | Yes | image |
| 26    | The population of the world. | Yes |   |
| 27    | The population of a continent. | Yes | image |
| 28    | The population of a region. | Yes | image |
| 29    | The population of a country. | Yes |   |
| 30    | The population of a district. | Yes | image |
| 31    | The population of a city. | Yes | image |
| 32    | The number of people in the world who speak languages from greatest number to smallest . | Yes | [Git](/img/1.png)|


 |      | Code Review 1 | Code Review 2 | Code Review 3 | Code Review 4 | Final Deliverable |
|------|---------------|---------------|---------------|---------------|-------------------|
| BoBo Oo | 0.25 | 0.25 | 0.25 | 0.25 | 0.25 |
| Khant Tayza | 0.25 | 0.25 | 0.25 | 0.25 | 0.25 |
| Oak Soe Phone Khant | 0.25 | 0.25 | 0.25 | 0.25 | 0.25 |
| Shoon lai yee | 0.25 | 0.25 | 0.25 | 0.25 | 0.25|
