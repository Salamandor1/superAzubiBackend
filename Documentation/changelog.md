## Refactor-Changelog (14.07.2025)

All changes are sorted by class.

### AnimalController
1. Removed most of the code, because the base animals are for get-methods only. No changed should be made through the frontend and therefore no changes should be made through the controller class.  
2. The method for getting random animals was changed, so that the controller needs a int pathvariable to determine how many random animals should be picked and returned. 

### FightController
1. Changed path from /fights to /fight.  
2. Removed almost everything except for the resolveFight-method. Every other logic was either not needed or moved to other classes, e.g. createFight, findFight or deleteFight are transferred to the log classes, because the fight itself is not saved but its summary.

### GameController
1. Added a new class as overview for a game.
2. The GameController class controls the whole game, because every other detail is linked back to the game, like a cascade. Every "Game" links to the player "Team" which links to the five "TeamAnimal" and these have a base "Animal" linked to them. Via the GameController a body can be sent with ALL informationen that are needed. 

### TeamAnimalController
1. The TeamAnimalController was cleand up: the linked repository was removed, because the responsibility lies withing the service class.

### TeamController (former GameTeamController)
1. Changed path from /teams to /playerteam.
2. Changed methods to be more uniform with the rest of the controller classes. Moved the error catches to the service class.

### CreateAndUpdateAnimalDTO
1. Corrected the class name from CreateAndUpdateAnimal to CreateAndUpdateAnimalDTO.
2. All other changed were unintentionally (changing the type from String to CreateAndUpdateAnimalDTO for the ability makes no sense and should be removed).

### CreateAndUpdateGameDTO
1. The DTO for the Controller class, contains all fields from the model.
2. The current GameDTO contains a field for a TeamDTO.

### CreateAndUpdateTeamAnimalDTO
1. Removed the pos field, because this is managed via the Team-classes.
2. Added field for health and attack, because these values are changable contrary to the base animal fields.
3. Changed the String field to the base animal to baseAnimalName, because its clearer.

### CreateAndUpdateTeamDTO
1. Changed the fields accordingly to the new Team model: the fields for hearts, wins and rounds are now handled by the Game classes.
2. The actual team is now saved in five individual fields instead of a List, because this way it is easier for the database and the backend to retranslate the team animals into their model. (Note: a list needs some type of serialisation, where individuals fields can be translated dricetly via ID)

### Animal model
1. Made the class immutable, because once a base animal is created and saved to the database it should not be changed except by administrative users.
2. Removed all setter methods because of the immutable annotation.
3. The ID type Long was removed, instead the name of the animal functions as the ID, because no base animal should have the same name.
4. Changed the String type animalName to name because of redundancy.
5. Removed the copy constructor, because its not needed anymore (the copying is done via TeamAnimal now).

### Fight model
1. Removed the ID, because the fight is no longer saved in the database (instead only the logs are saved).
2. Renamed the other fields for more clarity.
3. Added a field for the log of the fight.
4. Removed the annotations from the Team fields, because they are also no longer saved in the database.

### FightEventType model
1. Remove the whole enum-class, because its no longer needed. Instead the fight service handles the logic.  
*(Note: At first we wanted to save a List of enums instead of a Log/String, so every step could be recreated. This was too compicated for our intent and we decided to just save a String, therefore the enum is not needed anymore. If we wanted to add some animations or similar, the frontend could work with keywords from the log, making the enum even more redundant.)*

### Game model
1. Handels some of the fields from the old team model (hearts, wins and rounds)
2. Handels the team of the player.
*(Note: by starting a fresh game, a Game object is saved to the database with some standard values for the fields and an empty team.)*
3. Every Game contains a Team object, which contains up to five teamAnimal objects.

### Team model
1. Removed the fields hearts, wins and rounds because they are handled by the game model.
2. Switched the List of TeamAnimal objects with five individual fields as explained earlier.
3. Connected every field for a TeamAnimal accordingly to the database, because every field points either to a null object or to an TeamAnimal object which is saved individually in the database.
4. Added a getter-method which saves all TeamAnimals inside an List and returns it. *(Note: even if the slot points to null, the null value is saved inside the list and needs handling.)*

### TeamAnimal model
1. Changed the name of the table to team_animal for more consistancy and a more readable name.
2. Changed the name of the id for the same purpose.
3. Changed the name of the column for the base animal to base_animal_id for the same purpose and also to differentiate better between team and base animal.
4. Removed the field for position, because the position is handelt by the team classes.
5. Added a copy constructor for the fight logic, because the original team animal needs to be unchanged, while the team animal used for fighting gets its values updated, which also updates the team itself, which is not wanted.
6. Because the team animal does not have an own field for the name of the animal but saves a reffered base animal, a getter was added to get the name of the base animal directly, without fetching the base animal first.

### AnimalRepository
1. The query for getting five random animals was changed, so that now you can get a variable amount of animals.

### GameRepository
1. Was added.

### AnimalService
1. Renamed the field for the AnimalRepository to baseAnimalRepo to better differntiate it from teamAnimal.
2. Corrected the position of the autowired annotation and removed the constructor, which is not needed.
3. Changed the error handling.
4. Removed the create, delete and update methods, because the base animals are immutable.
5. Added/Changed the method for getting random animals to match the query.
6. Added a method to transform a base animal into a team animal. This was former handled by the fight service which did not fully match its responsibility.

### FightService
1. Added more autowired connections, because they are needed.
2. Wrongly added fields for some values. They are currently needed for the logic, but should be saved inside the model class, not as class fields in the service class.
3. Removed the some methods which were needed for the database, but the fight itself is not saved to the database anymore.
4. Both findByID methods for the teams were replaced:
    - the player team is now fetch via the game object which is fetched via the id from the game controller class.
    - the enemy team is now fully randomly generated via the information based on the game fields hearts, wins and round.
5. The return type was changed to Log, so that the controller/frondend will receive the result in an instant.
6. The Log is also saved in the database for the player to look at anytime as long the game persists.
7. Many methods for the fight logic were added, please look into it.

### GameService
1. Was added (just standard database operations)

### TeamAnimalService
1. Changed the autowired from repo to service for clearer responsibiliy. The logic for the base animal repository should be handeld by its service class, not by the team animal service class. Especially with these both (base animal / team animal) the responsibility should be strictly seperated.
2. Corrected the createTeamAnimal method accordingly to previous changes. If an DTO is null it returns null as teamAnimal.
3. Added an update method.

### TeamService
1. Rewired to Service instead of Repository, same reason as before.
2. Refactored the getTeambyID method for more consistency (same functionality).
3. Changed the updateTeam method accordingly to previous changes.
4. Removed the deleteTeam method *(Note: needs to be reimplemented!)*

### Application class
1. Added some information on how to resolve problems with the database by clearing the whole database.

### Archive
1. Added an archive path for old java files which are not needed right now. Can be deleted, if certain they are not needed anymore.