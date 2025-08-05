# Devcontainer

We are working with a devcontainer for our project. In our devcontainer.json, we configured general settings, in the Dockerfile we described the plan of a Java + Maven container and in the docker-compose.yml file, we defined three services we use:

app: the Java + Maven application

db: the PostgreSQL database

pgadmin: we can reah pgAdmin in the browser via localhost:5555 to communicate with our database and for example delete tables that were generated before and that we do not need anymore

Through this setup, we ensure that every team member has the needed dependencies to work with this project