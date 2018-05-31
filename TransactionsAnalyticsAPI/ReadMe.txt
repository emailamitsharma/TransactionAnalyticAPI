API Endpoints

http://localhost:8080/N26/api/statistics/ GET

http://localhost:8080/N26/api/transactions  POST


Assumptions/Approach [suggested]

1) Considered time of server for calculation at seconds level.
2) Caching mechanism for separating data of last second so that calculation takes place on that data instantaneously.
   Rather than first filtering data from thousands of records and then perform calculations.
3) O(1) maintain avg and keep updating at each and every entry. but you can't because you are only interested in last 
   second data.
4) Cache eviction policy for removal of all records except belonging to last second.
5) Future related transactions need to be handled better as per business requirement.

Improvements
Better test cases following approach of TDD.
