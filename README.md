# Project description

This is a simple feedback system that users can write feedbacks and other users can vote if the feedback
was helpful for them.

## API

The API part has been implemented with Java & Spring Boot. The data will be stored in an in-memory H2 database.

To see a demo of the backend part, see this video:

https://drive.google.com/file/d/162AOV_lr6qqIGYI9YfqSQO53eSipNSq7/view?usp=drive_link

### Tests
See `/src/test` folder for the tests.

## Frontend

The frontend of the app has been implemented with Vue 3. For the styling, Vuetify has been used.

The API communication has been done with TanStack Query and Axios.

To see a demo of the frontend part, see this video:

https://drive.google.com/file/d/1G9ZmzvRmW_AJ4v5sTOMVWdKK8eSW1AhT/view?usp=drive_link

### Unit Tests
There are unit tests for the API layer. Run this command in the `frontend` folder to run the tests:

```npm test```