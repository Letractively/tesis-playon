/*
-- Description: Script de generación del usuario y permisos de la base de datos
-- Project: Playon
-- Author: Alejandro Bostico
-- Date: 04/07/2012
-- Version: 1.2
--
-- HISTORIAL DE CAMBIOS
-- Version 1.0 (17/06/2012) - Versión Inicial
-- Version 1.1 (20/06/2012) - Agregados los permisos a la tabla Favorito
-- Version 1.2 (04/07/2012) - Se cambiaron los nómbres de las tablas a minúsculas y con
--		guiones bajos para compatibilidad Windows - Linux
-- Version 2.0 (04/07/2012) - Ahora funciona!!!
*/

/**
 * Crear el usuario 'playonAdmin' con password por defecto 'playon'.
 */
-- Descomentar la siguiente linea si el usuario existe
-- DROP USER 'playonAdmin';
CREATE USER 'playonAdmin'@'localhost' IDENTIFIED BY PASSWORD '*AEBE50B9926D86DA86D2B880AA0DF19F0EEDDEF0';
-- SET PASSWORD FOR 'playonAdmin' = PASSWORD( 'playon' );

/**
 * Asignamos todos los permisos al usuario 'playonAdmin' para la BD `tesis_playon`.
 */
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.* TO 'playonAdmin'@'localhost';

/*****************************************************************************
    Comentado porque no sirve, hace lo mismo que lo de arriba. Lo dejo por las dudas
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`cuenta_playa` TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`denuncia_vehiculo` TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`categoria_vehiculo`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`provincia`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`estado_publicidad`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`promocion`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`color_vehiculo`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`cuenta_cliente`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`estado_denuncia`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`denuncia_playa`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`playa`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`liquidacion`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`estado_playa`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`transaccion_cliente`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`tipoEstadia`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`historial_de_cambio`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`foto`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`vehiculo`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`empleado`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`cargo_empleado`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`perfil_playa`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`abono`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`pais`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`tarifa`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`barrio`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`favorito`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`usuario`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`tipo_pago`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`transaccion_playa`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`marca_vehiculo`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`estadia`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`modelo_vehiculo`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`localidad`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`cliente`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`tipo_doc`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`publicidad`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`detalle_estadia` TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`sesion`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`posicion`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`estado_promocion`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`comentario`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`usuario_sistema`  TO 'playonAdmin'@'%';
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.`rol_usuario`  TO 'playonAdmin'@'%';
*/