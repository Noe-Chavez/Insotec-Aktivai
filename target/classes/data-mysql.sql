INSERT INTO `rol`(`id`, `nombre`, `descripcion`) VALUES (1, 'ROLE_USUARIO', 'Usuario');
INSERT INTO `rol`(`id`, `nombre`, `descripcion`) VALUES (2, 'ROLE_ADMIN', 'Administrador Total');
INSERT INTO `rol`(`id`, `nombre`, `descripcion`) VALUES (3, 'ROLE_VENTAS', 'Ventas y Cobranza');
INSERT INTO `rol`(`id`, `nombre`, `descripcion`) VALUES (4, 'ROLE_TECNICO', 'Técnico instalador');
INSERT INTO `rol`(`id`, `nombre`, `descripcion`) VALUES (5, 'ROLE_CLIENTE', 'Cliente');

-- ADMIN
INSERT INTO usuario (id, avatar, contacto, fecha_creacion, habilitado, mail, materno, nombre, password, paterno, razon_social, rfc, tel_oficina, tel_personal) 
VALUES(1, 'Image_avatar.png', 'contacto', '2022-11-15 23:18:51', 1, 'aktivai@uacm.edu.mx', 'Pruebas', 'Admin', '$2a$11$kloUlB3LlQqaOwdtwFvK9e0RVlhq/Z9mP6cn3is0J/PkllghkN5Hq', 'Desarrollo', 'razon social', 'rfc', '555555', '55555');
INSERT INTO `usuario_roles`(`usuario_id`, `roles_id`) VALUES (1, 1);
INSERT INTO `usuario_roles`(`usuario_id`, `roles_id`) VALUES (1, 2);