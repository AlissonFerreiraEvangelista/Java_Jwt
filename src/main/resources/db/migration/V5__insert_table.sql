INSERT INTO tb_user(
	user_id, email, password, name)
	VALUES ('9f360616-1811-4621-9b2c-6c7af609ff04', 'user@gmail.com', '$2a$10$PR8KTHSm.iIIOyykg.Nri.EjD9fRDoh5ARDVRLxYKPFiJiV2178P6', 'user');

INSERT INTO tb_user(
	user_id, email, password, name)
	VALUES ('d573dbe2-dfaf-41b1-8382-3bb9e686248f', 'admin@gmail.com', '$2a$10$Q8cW7A.PhCE0v04mmaaaJuAJWKimxhd52RfspBQ2oPssbNjkmyQNi', 'admin');

INSERT INTO tb_role(
	role_id, role_name)
	VALUES ('a4033f81-db39-4362-b137-78545815ff98', 'ROLE_USER');	

INSERT INTO tb_role(
	role_id, role_name)
	VALUES ('8cdeb8d7-b269-44e7-98f2-e6eaacca7025', 'ROLE_ADMIN');

INSERT INTO tb_users_roles(
	user_id, role_id)
	VALUES ('9f360616-1811-4621-9b2c-6c7af609ff04', 'a4033f81-db39-4362-b137-78545815ff98');
	
INSERT INTO tb_users_roles(
	user_id, role_id)
	VALUES ('d573dbe2-dfaf-41b1-8382-3bb9e686248f', '8cdeb8d7-b269-44e7-98f2-e6eaacca7025');

INSERT INTO tb_produto(
	id, nome_produto, valor)
	VALUES ('4641f665-4f70-4605-98a6-a7cb42564547', 'Melão','20,30');
INSERT INTO tb_produto(
	id, nome_produto, valor)
	VALUES ('deea1981-2478-4062-ba39-9da750654ab7', 'Limão','4,60');