DROP TABLE servers;
DROP TABLE license;

CREATE TABLE license(
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  valid BOOLEAN,
  email VARCHAR(36),
  licenseExpires TIMESTAMP,
  trialExpires TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE servers(
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  host VARCHAR(36),
  port INTEGER,
  username VARCHAR(256),
  password VARCHAR(256),
  license INTEGER,
  CONSTRAINT license_fk FOREIGN KEY (license) REFERENCES license(id),
  PRIMARY KEY (id)
);

-- INSERT INTO license (valid) VALUES(FALSE);
-- INSERT INTO servers (host, port, username, password) VALUES('myhost1', 3, 'username1', 'verysecret1');
-- INSERT INTO servers (host, port, username, password) VALUES('myhost2', 4, 'username2', 'verysecret2');