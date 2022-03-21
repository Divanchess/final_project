INSERT INTO Cards (id, pan, pin) VALUES
 (1,'1120391203','3012'),
 (2,'5043857345','3554'),
 (3,'6546456455','4700');

INSERT INTO Accounts (id,balance,number,card_id) VALUES
  (1,123.51,'3424FDRF345',1),
  (2,654.00,'65476FDFGF4',2),
  (3,967.44,'2424GFDGH46',3);

commit;