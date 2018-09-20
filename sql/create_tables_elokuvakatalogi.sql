CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `runtime` varchar(10) DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  `userrating` float(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `movie_genres` (
  `movie_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  PRIMARY KEY (`movie_id`,`genre_id`),
  KEY `fk_movies_has_genre_genre1_idx` (`genre_id`),
  KEY `fk_movies_has_genre_movies_idx` (`movie_id`),
  CONSTRAINT `fk_movies_has_genre_movies` 
  FOREIGN KEY (`movie_id`) 
  REFERENCES `movie` (`id`) 
  ON DELETE CASCADE 
  ON UPDATE NO ACTION,
  CONSTRAINT `fk_movies_has_genre_genre1` 
  FOREIGN KEY (`genre_id`) 
  REFERENCES `genre` (`id`) 
  ON DELETE CASCADE 
  ON UPDATE NO ACTION
);

*****************TÄRKEÄÄ: Nämä kyseiset arvot pitää lisätä genret tauluun tässä järjestyksessä ennen kuin yrittää lisätä elokuvia tai niiden genre viittauksia
INSERT INTO genre (name) 
values ("action"),("comedy"),("drama"),("fantasy"),("horror"),("romance"),("scifi"),("western"),("thriller"),("nogenre");

