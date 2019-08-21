INSERT INTO usuarios (nombre, apellido, username,password,enabled,email) VALUES ('antonio','marin','amiguel','$2a$10$Y90yG/RCZsFMmRAq0OuqTenkObTxGvM7CEhGfMXBFgEhDXbeb0N1q',true,'amarin@unac.edu.co');
INSERT INTO usuarios (nombre, apellido, username,password,enabled,email) VALUES ('angelica','sage','asage','$2a$10$Y90yG/RCZsFMmRAq0OuqTenkObTxGvM7CEhGfMXBFgEhDXbeb0N1q',true,'asage@unac.edu.co');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (1,1);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2,2);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2,1);


