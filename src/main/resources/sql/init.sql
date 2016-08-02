DROP TABLE license;
DROP TABLE servers;

CREATE TABLE servers(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  host VARCHAR(36),
  port INT,
  username VARCHAR(256),
  password VARCHAR(256),
  PRIMARY KEY (id)
);

CREATE TABLE license(
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  valid BOOLEAN,
  email VARCHAR(36),
  license_expires TIMESTAMP,
  trial_expires TIMESTAMP,
  server_id INT,
  CONSTRAINT server_fk FOREIGN KEY (server_id) REFERENCES servers(id) ON DELETE CASCADE ON UPDATE RESTRICT,
  PRIMARY KEY (id)
);

-- INSERT INTO license (valid) VALUES(FALSE);
-- INSERT INTO servers (host, port, username, password) VALUES('myhost1', 3, 'username1', 'verysecret1');
-- INSERT INTO servers (host, port, username, password) VALUES('myhost2', 4, 'username2', 'verysecret2');