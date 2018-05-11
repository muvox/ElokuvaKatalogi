CREATE TABLE movie(
id INT NOT NULL AUTO_INCREMENT,
title varchar(250) NOT NULL,
description varchar(250),
runtime varchar(10),
image varchar(250),
userrating float(4,2),
PRIMARY KEY(id)
);

CREATE TABLE `genre` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS movie_genres (
  `movie_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`movie_id`, `genre_id`),
  INDEX `fk_movies_has_genre_genre1_idx` (`genre_id` ASC),
  INDEX `fk_movies_has_genre_movies_idx` (`movie_id` ASC),
  CONSTRAINT `fk_movies_has_genre_movies`
    FOREIGN KEY (`movie_id`)
    REFERENCES movie (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movies_has_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES genre (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
