# Model View Controller

## What is Model View Controller?

Model View Controller is a software design pattern that is often used to seperate larger classes into smaller ones with distinguishable tasks. This way, it is for example easier to debug code as one must not skim through a large class but instead can look through the class responsible for a specifig problem.

E.g., if there is something wrong with the API endpoints, one only has to look through the Controller Class in which the endpoints are.

Based on this, this project is seperated into these types of classes and are located in the folder with the matching name:


## Model

Model classes hold attributes, getters and setters, but no specific business logic


## Service

Service classes implement functionality and business logic


## Repository

Repository classes are for database queries. As SpringBoot covers basic CRUD Operations, these are not needed to be written by hand, only the Repository class of a database entity needs to exists. However, if more complex queries are needed, they can be written in the Repository class.


## Controller

These classes hold the API endpoints 


## DTO

DTOs (Data Transfer Objects) are used in endpoints to send and return data. This way, one can control how much information of a database entry can be returned. Not always do we want to share all the info saved in a database table. To only provide the necessary information, a DTO can be useful

## Additional: EmbeddedIds

In SpringBoot, when the primary key of your database entity consists of multiple attributes, you can write a class that consists of these attributes and use this class as the type of your primary key. These data structures are called Embedded Id.
