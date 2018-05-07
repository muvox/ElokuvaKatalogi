CREATE TABLE movie(
id INT NOT NULL AUTO_INCREMENT,
title varchar(250) NOT NULL,
description varchar(250),
runtime varchar(10),
image varchar(250),
userrating float(4,2),
PRIMARY KEY(id)
);