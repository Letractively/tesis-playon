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

/* Descomentar la siguiente linea si el usuario existe */
-- DROP USER 'playonAdmin';
CREATE USER 'playonAdmin'@'localhost' 
	IDENTIFIED BY PASSWORD '*AEBE50B9926D86DA86D2B880AA0DF19F0EEDDEF0';

/**
 * Asignamos todos los permisos al usuario 'playonAdmin' para la BD `tesis_playon`.
 */
GRANT ALL PRIVILEGES ON TABLE `tesis_playon`.* TO 'playonAdmin'@'localhost';
