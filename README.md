# task-tracker
Task tracker app for siege tool test

# Build spring boot application .jar with maven
```
mvn clean package
```

# Start the stack with docker compose
```
docker-compose up
```
# Install siege tool and run commands from test-script.sh. Example:
```
siege -r1 -c20 -i -f requests.txt
```
