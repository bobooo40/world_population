
# World Population

## Status 


- License [![LICENSE](https://img.shields.io/github/license/bobooo40/world_population.svg?style=flat-square)](https://github.com/bobooo40/world_population)
- Release [![Releases](https://img.shields.io/github/release/bobooo40/world_population/all.svg?style=flat-square)](https://github.com/bobooo40/world_population)
- Develop Build Status [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=develop)](https://travis-ci.org/bobooo40/world_population)
- Master Build Status [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=master)](https://travis-ci.org/bobooo40/world_population)
- SQL Integration [![Build Status](https://travis-ci.org/bobooo40/world_population.svg?branch=feature/mysql-integration)](https://travis-ci.org/bobooo40/world_population)
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

## Detailed requirements

> 32 requirements of 32 have been implemented, which is 100.0%.

| ID    | Name | Met  | Screenshot |
|-------|------|------|------------|
| 1     | All the countries in the world organised by largest population to smallest. | Yes | [1.png](/Img/1.png) |
| 2     | All the countries in a continent organised by largest population to smallest. | Yes | [2.png](/Img/2.png)  |
| 3     | All the countries in a region organised by largest population to smallest. | Yes | [3.png](/Img/3.png) |
| 4     | The top N populated countries in the world where N is provided by the user. | Yes | [4.png](/Img/4.png) |
| 5     | The top N populated countries in a continent where N is provided by the user. | Yes |  [5.png](/Img/5.png) |
| 6     | The top N populated countries in a region where N is provided by the user. | Yes | [6.png](/Img/6.png) |
| 7     | All the cities in the world organised by largest population to smallest. | Yes | [7.png](/Img/7.png) |
| 8     | All the cities in a continent organised by largest population to smallest. | Yes | [8.png](/Img/8.png)  |
| 9     | All the cities in a region organised by largest population to smallest. | Yes | [9.png](/Img/9.png) |
| 10     | All the cities in a country organised by largest population to smallest. | Yes | [10.png](/Img/10.png) |
| 11    | All the cities in a district organised by largest population to smallest. | Yes |  [11.png](/Img/11.png) |
| 12    | The top N populated cities in the world where N is provided by the user. | Yes | [12.png](/Img/12.png) |
| 13    | The top N populated cities in a continent where N is provided by the user. | Yes | [13.png](/Img/13.png) |
| 14    | The top N populated cities in a region where N is provided by the user. | Yes | [14.png](/Img/14.png)  |
| 15    | The top N populated cities in a country where N is provided by the user. | Yes | [15.png](/Img/15.png) |
| 16    | The top N populated cities in a district where N is provided by the user. | Yes | [16.png](/Img/16.png) |
| 17    | All the capital cities in the world organised by largest population to smallest. | Yes | [17.png](/Img/17.png)  |
| 18    | All the capital cities in a continent organised by largest population to smallest. | Yes | [18.png](/Img/18.png) |
| 19    | All the capital cities in a region organised by largest to smallest. | Yes | [19.png](/Img/19.png) |
| 20    | The top N populated capital cities in the world where N is provided by the user. | Yes | [20.png](/Img/20.png)  |
| 21    | The top N populated capital cities in a continent where N is provided by the user. | Yes | [21.png](/Img/21.png) |
| 22    | The top N populated capital cities in a region where N is provided by the user. | Yes | [22.png](/Img/22.png) |
| 23    | The population of people, people living in cities, and people not living in cities in each continent. | Yes |  [23.png](/Img/23.png) |
| 24    | The population of people, people living in cities, and people not living in cities in each region. | Yes | [24.png](/Img/24.png) |
| 25    | The population of people, people living in cities, and people not living in cities in each country. | Yes | [25.png](/Img/25.png) |
| 26    | The population of the world. | Yes |  [26.png](/Img/26.png) |
| 27    | The population of a continent. | Yes | [27.png](/Img/27.png) |
| 28    | The population of a region. | Yes | [28.png](/Img/28.png) |
| 29    | The population of a country. | Yes |  [29.png](/Img/29.png) |
| 30    | The population of a district. | Yes | [30.png](/Img/30.png) |
| 31    | The population of a city. | Yes | [31.png](/Img/31.png)|
| 32    | The number of people in the world who speak languages from greatest number to smallest . | Yes | [32.png](/Img/32.png)|

## Final Individual Contribution Spreadsheet
 |      | Code Review 1 | Code Review 2 | Code Review 3 | Code Review 4 | Final Deliverable |
|------|---------------|---------------|---------------|---------------|-------------------|
| BoBo Oo | 0.25 | 0.25 | 0.25 | 0.25 | 0.25 |
| Khant Tayza | 0.25 | 0.25 | 0.25 | 0.25 | 0.25 |
| Oak Soe Phone Khant | 0.25 | 0.25 | 0.25 | 0.25 | 0.25 |
| Shoon lai Yee | 0.25 | 0.25 | 0.25 | 0.25 | 0.25|
