DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Cards;

CREATE TABLE Cards (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 pan VARCHAR(20) NOT NULL,
 pin VARCHAR(4) NOT NULL
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  balance NUMERIC(20, 2) DEFAULT 0,
  number VARCHAR(50),
  card_id INT NOT NULL,
  foreign key (card_id) references Cards(id)
);

