# regex-controlled-backtracking-service
A system that prevents Regular Expression Denial of Service  and handles a large amount of string matching requests, which may  include bad regexes as input which might cause catastrophic backtracking. 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Problem Statment:
Context​: One of the most common approaches for data extraction from text is through regular expressions(also 
known as 'regex'). 
 
While regular expressions are a practical and efficient approach, they come with some pitfalls. One of these 
comes in the form of a "bad regular expression". These cause the control to be lost in a backtracking limbo. 
 
Suppose you were to develop a system that handles a large amount of string matching requests, which may 
include bad regexes as input. How would you exit the backtracking limbo? 
Specifications: 
1) POST api: 
input JSON: 
{ 
"regex": “”, 
"textBody": "" 
} 
output json: 
{ 
"match": “”, 
“error”: false 
} 
Description​: - 
- "match" should contain the value picked up by the regex, for the sake of convenience just include 
the first match. 
          - If a match isn't found for that particular regex, the value should be null.  
- If the regex is a bad regex then appropriately exit the matching process, the value should be null and 
error should be true. 
 
Hints​: -  
- While stuck in backtracking, the concerned thread or process may become unresponsive, it is crucial to 
eliminate such fatal states. 
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
implementd Solution:

All The incoming requests are Asynchronous.
Default Timeout when using API with controlled Backtracking  = 1sec.
Two Case of handling regex Expressions:

1- Without controlled Backtracking [ problem that is solved]
URI: http://localhost:8080/regex-service/match/match-without-timeout
Here the service goes through the normal way how a Default Regex Engine of Java 8 works.
It would hence take a long time in case it finds a need to backtrack to find more matches.

2-With Controlled Backtracking
URI:http://localhost:8080/regex-service/match/match-with-timeout
When using a Regex expression Regex engine uses backtracking as a way to find other possibilities where it could have matched.So we cannot completely stop it because it is needed to find maxium nummber of matches. But it could get worse for some inputs where the complexity can raise to exponential. 
To Stop this from happening I have used an implementation of CharSequenceso that every time the Engine Checks for the character the time that the process takes  is tracked down.
Once the time Crosses invokedTime + timeOut_set we break away saying it is a bad Request with a bad Regex Expression for that input String else the match is found and the call returns successfully

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



