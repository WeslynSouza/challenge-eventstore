# EventStore - API 

This project was developed with SpringBoot, chosen because of the easy integration with other libraries, in the form of an API, in order to allow the input and output of data in a simple and easy way.

A database was also implemented to store event information, in order to reduce the amount of information loaded in memory, using only the information that would be necessary, since we can work with an unlimited amount of events.

###Endpoints:

Several endpoints were developed in this application, some of them focused on using the methods implemented from userStore and others from userIteretor.


* GET - http://localhost:8081/EventStore/Test - used to quickly check if the connection to the API was established correctly.


* POST - http://localhost:8081/EventStore/InsertEvent - used to insert an event, through the 'insert' method of the eventStore.


* DELETE - http://localhost:8081/EventStore/RemoveAllEventByType?type=@Param - used to remove all events that were stored, from a given type, through the 'removeAll' method of the eventStore. (@Param - endpoint parameters)


* POST - http://localhost:8081/EventStore/CreateIterator - used to create the iterator according to the type and a period of the events' timestamp, using the eventStore's 'query' method.


* GET - http://localhost:8081/EventStore/MoveIterator - used to move the event pointed to in the iterator, through the 'moveNext' method of the eventIterator.


* GET - http://localhost:8081/EventStore/CurrentIterator - used to fetch and present the event that is being pointed to in the iterator, through the 'current' method of the eventIterator.


* DELETE - http://localhost:8081/EventStore/RemoveCurrentIterator - used to remove the pointed event from the iterator and from the database, through the 'remove' method of the eventIterator.


* GET - http://localhost:8081/EventStore/CloseIterator - used to close the iterator, clearing the memory data, through the eventIterator's 'close' method.

###Libraries:

To achieve a proper functioning of the application, some external libraries were also used, namely:


* [H2](https://www.h2database.com/html/main.html) - Database structure, used to store events. This library was chosen for this application because of its easy Spring integration and simple configuration.


* [Lombok](https://projectlombok.org) - Library focused on reducing code, through notations that facilitate the development of some classes. 

###Project architecture:

The project was developed in the MVC model due to the simple organization and easy understanding by
other developers. Given this, a controller was created with the main endpoints of the application,
the 'Event' class became a model and the 'EventStore' class was implemented as a service because of
the methods that interact directly with the data store. Within the application structure, a Repository
was also created to facilitate interaction with the database. 