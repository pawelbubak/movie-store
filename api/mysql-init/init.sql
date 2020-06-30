create database if not exists `movie-store`;

use `movie-store`;

create table if not exists users(
  id int unsigned auto_increment primary key,
  username varchar(50) not null unique,
  email varchar(50) not null unique,
  password varchar(250),
  role varchar(20) check (role in ('CUSTOMER','MODERATOR','ADMIN')),
  enabled boolean default false,
  locked boolean default false
);

insert into users
  (id, username, email, password, role, enabled)
values
  (1,'admin','admin@take.com','$2y$10$vAsNRvkBqSSQ1qpogEcxHO8859gdn3aHuuHUTHcr9OnEUrNg4cHOO','ADMIN',true);

create table if not exists movies(
  id int unsigned auto_increment primary key,
  title varchar(400) not null,
  category varchar(50) check (category in ('DRAMA','COMEDY','FAMILY','ACTION','SCI-FI', 'CLASSICS')),
  year char(4),
  cast varchar(4000),
  director varchar(4000),
  story varchar(4000),
  price numeric(5, 2)
);

insert into movies
  (id, title, category, year, cast, director, story, price)
values
  (1,'Full Metal Jacket','ACTION','1987','Matthew Modine, Vincent D''Onofrio, et al.','Stanley Kubrick','Bardzo
ciekawy film. Jak wszystkie inne...',45),
  (2,'American Pie 2','COMEDY','2001','Jason Biggs, Seann William Scott, et al.','James B. Rogers (II)','Bardzo
ciekawy film. Jak wszystkie inne...',55),
  (3,'Lost Highway','DRAMA','1997','Bill Pullman, Patricia Arquette, et al.','David Lynch','Bardzo ciekawy film. Jak
wszystkie inne...',65),
  (4,'The Naked Gun','COMEDY','1988','Leslie Nielsen, Priscilla Presley, et al.','David Zucker','Bardzo ciekawy film.
Jak wszystkie inne...',50),
  (5,'The Naked Gun 2 1/2 - The Smell of Fear','COMEDY','1991','Leslie Nielsen, Priscilla Presley, et al.','David
Zucker','Bardzo ciekawy film. Jak wszystkie inne...',65),
  (6,'Armageddon','SCI-FI','1998','Bruce Willis, Billy Bob Thornton, et al.','Michael Bay','Bardzo ciekawy film. Jak
wszystkie inne...',40),
  (7,'Reservoir Dogs ','ACTION','1992','Harvey Keitel, Tim Roth, et al.','Quentin Tarantino','Bardzo ciekawy film. Jak
wszystkie inne...',40),
  (8,'Con Air','ACTION','1997','Nicolas Cage, John Cusack, et al.','Simon West','Bardzo ciekawy film. Jak wszystkie
inne...',65),
  (9,'Cube','SCI-FI','1998','Nicole de Boer, Nicky Guadagni, et al.','Vincenzo Natali','Bardzo ciekawy film. Jak
wszystkie inne...',40),
  (10,'What Women Want','COMEDY','2000','Mel Gibson, Helen Hunt, et al.','Nancy Meyers','Bardzo ciekawy film. Jak
wszystkie inne...',50),
  (11,'The Family Man','FAMILY','2000','Nicolas Cage, Téa Leoni, et al.','Brett Ratner','Bardzo ciekawy film. Jak
wszystkie inne...',50),
  (12,'Titanic','DRAMA','1997','Leonardo DiCaprio, Kate Winslet, et al.','James Cameron','Bardzo ciekawy film. Jak
wszystkie inne...',65),
  (13,'Dances with Wolves','DRAMA','1990','Kevin Costner, Mary McDonnell, et al.','Kevin Costner','Bardzo ciekawy film.
Jak wszystkie inne...',60),
  (14,'Platoon','ACTION','1996','Tom Berenger, Willem Dafoe, et al.','Oliver Stone','Bardzo ciekawy film. Jak wszystkie
inne...',60),
  (15,'Desperado','ACTION','1995','Antonio Banderas, Salma Hayek, et al.','Robert Rodriguez','Bardzo ciekawy film. Jak
wszystkie inne...',55),
  (16,'Mallrats','COMEDY','1995','Jeremy London, Jason Lee, et al.','Scott Mosier, Kevin Smith','Bardzo ciekawy film.
Jak wszystkie inne...',55),
  (17,'South Park: Bigger, Longer and Uncut ','COMEDY','1999','Starring: Trey Parker, Matt Stone, et al.','Trey
Parker','Bardzo ciekawy film. Jak wszystkie inne...',45),
  (18,'Clerks','COMEDY','1994','Brian O''Halloran, Jeff Anderson, et al.','Kevin Smith','Bardzo ciekawy film. Jak
wszystkie inne...',50),
  (19,'Chasing Amy','COMEDY','1997','Ben Affleck, Joey Lauren Adams, et al.','Kevin Smith','Bardzo ciekawy film. Jak
wszystkie inne...',65),
  (20,'Pearl Harbor','ACTION','2001','Ben Affleck, Kate Beckinsale, et al.','Michael Bay','Bardzo ciekawy film. Jak
wszystkie inne...',55),
  (21,'Cast Away','FAMILY','2000','Tom Hanks, Helen Hunt, et al.','Robert Zemeckis','Bardzo ciekawy film. Jak wszystkie
inne...',50),
  (22,'Dogma','COMEDY','1999','Ben Affleck, Matt Damon, et al.','Scott Mosier, Kevin Smith','Bardzo ciekawy film. Jak
wszystkie inne...',45),
  (23,'Pokemon the First Movie: Mewtwo Strikes Back','FAMILY','1999','','Michael Haigney, Kunihiko Yuyama','Bardzo
ciekawy film. Jak wszystkie inne...',45),
  (24,'Pokemon - The Movie 2000','FAMILY','1999','Starring: Veronica Taylor, et al.','Michael Haigney','Bardzo ciekawy
film. Jak wszystkie inne...',45),
  (25,'American Psycho','DRAMA','2000','Christian Bale, et al.','Mary Harron','Bardzo ciekawy film. Jak wszystkie inne.
..',50),
  (26,'Stuart Little','FAMILY','1999','Michael J. Fox, Geena Davis, et al.','Rob Minkoff','Bardzo ciekawy film. Jak
wszystkie inne...',45),
  (27,'Star Wars - Episode I, The Phantom Menace ','SCI-FI','1999','Liam Neeson, Ewan McGregor, et al.','George Lucas',
   'Bardzo ciekawy film. Jak wszystkie inne...',45),
  (28,'Star Wars - Episode IV, A New Hope ','SCI-FI','1977','Mark Hamill, Harrison Ford, et al.','George Lucas','Bardzo
ciekawy film. Jak wszystkie inne...',55),
  (29,'Star Wars - Episode V, The Empire Strikes Back (Special Edition) ','SCI-FI','1980','Mark Hamill, Harrison Ford,
et al.','Irvin Kershner','Bardzo ciekawy film. Jak wszystkie inne...',40),
  (30,'Star Wars - Episode VI, Return of the Jedi (Special Edition) ','SCI-FI','1983','Mark Hamill, Harrison Ford, et
al.','Richard Marquand','Bardzo ciekawy film. Jak wszystkie inne...',55),
  (31,'Aces Go Places 5 ','COMEDY','1989','Sam Hui, et al..','Chia-Liang Liu','Bardzo ciekawy film. Jak wszystkie inne.
..',55),
  (32,'Gone In 60 Seconds ','ACTION','2000','Nicolas Cage, Angelina Jolie, et al.','Dominic Sena','Bardzo ciekawy film.
Jak wszystkie inne...',50),
  (33,'American Gigolo ','DRAMA','1980','Richard Gere, Lauren Hutton, et al.','Paul Schrader','Bardzo ciekawy film. Jak
wszystkie inne...',40),
  (34,'Deuce Bigalow: Male Gigolo','COMEDY','1999','Rob Schneider, William Forsythe, et al.','Mike Mitchell (VI)',
   'Bardzo ciekawy film. Jak wszystkie inne...',45),
  (35,'Where Eagles Dare','CLASSICS','1969','Richard Burton, Clint Eastwood, et al.','Brian G. Hutton','Bardzo ciekawy
film. Jak wszystkie inne...',45),
  (36,'The Great Escape','CLASSICS','1963','Steve McQueen, James Garner, et al.','John Sturges','Bardzo ciekawy film.
Jak wszystkie inne...',45),
  (37,'The Naked Gun 33 1/3 - The Final Insult  ','COMEDY','1994','Leslie Nielsen, Priscilla Presley, et al.','Peter
Segal','Bardzo ciekawy film. Jak wszystkie inne...',50),
  (38,'Commando','ACTION','1985','Arnold Schwarzenegger, Rae Dawn Chong, et al.','Mark L. Lester','Bardzo ciekawy film.
Jak wszystkie inne...',65),
  (39,'The Running Man','ACTION','1987','Arnold Schwarzenegger, Maria Conchita Alonso, et al.','Paul Michael Glaser',
   'Bardzo ciekawy film. Jak wszystkie inne...',45),
  (40,'Total Recall','SCI-FI','1990','Arnold Schwarzenegger, Sharon Stone, et al.','Paul Verhoeven','Bardzo ciekawy
film. Jak wszystkie inne...',60),
  (41,'The Terminator','SCI-FI','1984','Arnold Schwarzenegger, Michael Biehn, et al.','James Cameron','Bardzo ciekawy
film. Jak wszystkie inne...',60),
  (42,'Terminator 2: Judgment Day ','SCI-FI','1991','Arnold Schwarzenegger, Linda Hamilton, et al.','James Cameron',
   'Bardzo ciekawy film. Jak wszystkie inne...',65),
  (43,'Casablanca','CLASSICS','1943','Humphrey Bogart, Ingrid Bergman, et al.','Michael Curtiz','Bardzo ciekawy film.
Jak wszystkie inne...',65),
  (44,'The Maltese Falcon','CLASSICS','1941','Humphrey Bogart, Mary Astor, et al.','John Huston','Bardzo ciekawy film.
Jak wszystkie inne...',55),
  (45,'1941','COMEDY','1971','Dan Aykroyd, John Belushi, et al.','Steven Spielberg','Bardzo ciekawy film. Jak wszystkie
inne...',55),
  (46,'Tora Tora Tora','DRAMA','1970','Martin Balsam, Sô Yamamura, et al.','Richard Fleischer, Toshio Masuda','Bardzo
ciekawy film. Jak wszystkie inne...',50),
  (47,'E.T. The Extra-Terrestrial','SCI-FI','1982','Henry Thomas, Peter Coyote, et al.','Steven Spielberg','Bardzo
ciekawy film. Jak wszystkie inne...',50),
  (48,'The Mask','COMEDY','1994','Jim Carrey, Cameron Diaz, et al.','Chuck Russell','Bardzo ciekawy film. Jak wszystkie
inne...',50),
  (49,'Raiders of the Lost Ark','ACTION','1981','Harrison Ford, Karen Allen, et al.','Steven Spielberg','Bardzo ciekawy
film. Jak wszystkie inne...',45),
  (50,'Indiana Jones and the Temple of Doom ','ACTION','1984','Harrison Ford, Kate Capshaw, et al.','Steven Spielberg',
   'Bardzo ciekawy film. Jak wszystkie inne...',60),
  (51,'Indiana Jones and the Last Crusade ','ACTION','1989','Harrison Ford, Sean Connery, et al.','Steven Spielberg',
   'Bardzo ciekawy film. Jak wszystkie inne...',55),
  (52,'Gladiator','ACTION','2000','Russell Crowe, Joaquin Phoenix, et al.','Ridley Scott','Bardzo ciekawy film. Jak
wszystkie inne...',50),
  (53,'American Beauty','DRAMA','1999','Kevin Spacey, Annette Bening, et al.','Sam Mendes','Bardzo ciekawy film. Jak
wszystkie inne...',45),
  (54,'Quo Vadis? ','CLASSICS','1951','Robert Taylor, Deborah Kerr, et al.','Mervyn LeRoy','Bardzo ciekawy film. Jak
wszystkie inne...',45),
  (55,'Predator','SCI-FI','1987','Arnold Schwarzenegger, Carl Weathers, et al.','John McTiernan','Bardzo ciekawy film.
Jak wszystkie inne...',45),
  (56,'Wild Orchid','DRAMA','1990','Mickey Rourke, Jacqueline Bisset, et al.','Zalman King','Bardzo ciekawy film. Jak
wszystkie inne...',60),
  (57,'Pulp Fiction','COMEDY','1994','John Travolta, Samuel L. Jackson, et al.','Quentin Tarantino','Bardzo ciekawy
film. Jak wszystkie inne...',50),
  (58,'Who Framed Roger Rabbit ','COMEDY','1988','Bob Hoskins, et al.','Robert Zemeckis','Bardzo ciekawy film. Jak
wszystkie inne...',50),
  (59,'Air Force One ','ACTION','1997','Harrison Ford, Gary Oldman, et al.','Wolfgang Petersen','Bardzo ciekawy film.
Jak wszystkie inne...',65),
  (60,'Murder on the Orient Express','CLASSICS','1974','Albert Finney, Lauren Bacall, et al.','Sidney Lumet','Bardzo
ciekawy film. Jak wszystkie inne...',40),
  (61,'Mad Max','SCI-FI','1980','Mel Gibson, et al.','George Miller (II)','Bardzo ciekawy film. Jak wszystkie inne...',
   40),
  (62,'Traffic','ACTION','2000','Benicio Del Toro, Michael Douglas, et al.','Steven Soderbergh','Bardzo ciekawy film.
Jak wszystkie inne...',50),
  (63,'Nurse Betty','COMEDY','2000','Morgan Freeman, Renée Zellweger, et al.','Neil LaBute','Bardzo ciekawy film. Jak
wszystkie inne...',50),
  (64,'Bridget Jones''s Diary ','FAMILY','2001','Renée Zellweger, Colin Firth, et al.','Sharon Maguire','Bardzo ciekawy
film. Jak wszystkie inne...',55),
  (65,'Chocolat','FAMILY','2001','Juliette Binoche, Judi Dench, et al.','Lasse Hallström','Bardzo ciekawy film. Jak
wszystkie inne...',55),
  (66,'Beavis and Butt-Head Do America','COMEDY','1996','Mike Judge, et al.','Yvette Kaplan, Mike Judge','Bardzo
ciekawy film. Jak wszystkie inne...',60);

create table if not exists cart_items(
                                         user_id int unsigned not null,
                                         movie_id int unsigned not null,
                                         quantity int,
                                         constraint pk_cart_items primary key (user_id, movie_id),
                                         constraint fk_users_cart_items foreign key (user_id) references users (id),
                                         constraint fk_movies_cart_items foreign key (movie_id) references movies (id)
);
