# scheduler-with-external-api
Scheduler to grab data from external source and api for access this source.
-------------------------------------------------
I divided the project into two parts:

	(1) background process;

	(2) controller to share endpoints.
-------------------------------------------------
A process runs in the background, which periodically polls the website 'https://jsonplaceholder.typicode.com' for data for up to two minutes.

Here is list of endpoints I used :

	https://jsonplaceholder.typicode.com/todos?userId={userId}

The data from this service will later go to the H2 database, which runs on the local machine.

Each record in the database has a timestamp that allows you to track the history of data changes.
-------------------------------------------------
Access to data collected in the database is provided by endpoints.

Here is a list of these endpoints:

	/api/todos
	/api/todos/{userId}
	/api/todos/dateRange?dateSince={yyyymmddhhmm}&dateUntil={yyyymmddhhmm}
	/api/todos/countUserRecords?userId={userId}
-------------------------------------------------
Few things about Cron :

	/*
	 * Cron expression is represented by six fields:
	 *		second, minute, hour, day of month, month, day(s) of week
	 *
	 * 		(*) means match any
	 * 		*/X means "every X"
	 * 		? ("no specific value")
	 * 
	 * 		examples :
	 * 		"0 0 * * * *" = the top of every hour of every day.
	 * 		"star/10 * * * * *" = every ten seconds.
	 * 		"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	 * 		"0 0 8,10 * * *" = 8 and 10 o'clock of every day.
	 */
	
	// For example, "0 * * * * MON-FRI" means once per minute on weekdays(at the top of the minute - the 0th second). 
	// @Scheduled(cron = "0 0 21 * * *")
	// @Scheduled(cron = "0 * * * * *")
	// @Scheduled(cron = "0 */2 * * * *")
-------------------------------------------------
Here is a list of the technologies I used to create the project:

	(1) Java 8;
	(2) Spring Boot;
	(3) Spring Data Jpa;
	(4) Spring MVC;
	(5) H2 database.
-------------------------------------------------
