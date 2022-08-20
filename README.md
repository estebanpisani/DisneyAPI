# DisneyAPI
API REST developed for the [Alkemy's](https://www.alkemy.org/) **Challenge Backend - Java Spring Boot**.

## Challenge
Development of an API to explore the world of Disney, which will allow knowing and modifying the characters that compose it and understand in which films they participated. On the other hand, expose the information so that any frontend can consume it.

## Features

- [ ] Character CRUD
- [ ] Movies CRUD
- [ ] Genres CRUD
- [ ] User Authentication
- [ ] Email notificacion (SendGrid)

## Endpoints

### User Authentication

- `/auth/login`
- `/auth/register`

### Characters

- `[GET] /characters`: list all characters with basic data (id,name,image). It allows to use query params(name, age, weight, movies, order).
- `[POST] /characters`: save a new character on database.
- `[GET] /characters/:id`: get details of a specific character (id, name, image, age, weight, story, related movies).
- `[PUT] /characters/:id`: update data of a specific character.
- `[DELETE] /characters/:id`: delete a character from the database.

### Movies

- `[GET] /movies`: list all movies with basic data (id, title, image, creation date). It allows to use query params(name, genre, order).
- `[POST] /movies`: save a new movie on database.
- `[GET] /movies/:id`: get details of a specific movie (id, title, image, creation date, rate, genre, related characters).
- `[PUT] /movies/:id`: update data of a specific movie.
- `[DELETE] /movies/:id`: delete a movie from the database.

### Genres

- `[GET] /genres`: list all genres.
- `[POST] /genres`: save a new genre on database.
- `[GET] /genres/:id`: get details of a specific genre.
- `[PUT] /genres/:id`: update data of a specific genre.
- `[DELETE] /movies/:id`: delete a genre from the database.

## Getting Started
You must some enviroment variables:
 - `API_KEY`: api key provided by email sender.
 - `EMAIL`: email sender's address.
 - `SECRET_KEY`: jwt secret key.
 - `DB_URL`: database url.
 - `DB_USERNAME`: database username.
 - `DB_PASSWORD`: database password.

## Credits

### Author
Esteban Pisani
