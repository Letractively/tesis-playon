/*
-- Description: Script de generación del usuario y permisos de la base de datos
-- Project: Playon
-- Author: Alejandro Bostico
-- Date: 17/06/2012
-- Versión Actual: 1.0
--
-- HISTORIAL DE CAMBIOS
-- Version 1.0 - Versión Inicial
*/

-- Descomentar la siguiente linea si el usuario existe
-- #DROP USER 'playonAdmin';
CREATE USER 'playonAdmin' IDENTIFIED BY 'playonAdmin';
SET PASSWORD FOR 'playonAdmin' = PASSWORD( 'playon' );

GRANT ALL ON `tesis_playon` TO 'playonAdmin'@'%';

GRANT ALL ON `CuentaPlaya` TO 'playonAdmin'@'%';
GRANT ALL ON `DenunciaVehiculo` TO 'playonAdmin'@'%';
GRANT ALL ON `CategoriaVehiculo`  TO 'playonAdmin'@'%';
GRANT ALL ON `Provincia`  TO 'playonAdmin'@'%';
GRANT ALL ON `EstadoPublicidad`  TO 'playonAdmin'@'%';
GRANT ALL ON `Promocion`  TO 'playonAdmin'@'%';
GRANT ALL ON `ColorVehiculo`  TO 'playonAdmin'@'%';
GRANT ALL ON `CuentaCliente`  TO 'playonAdmin'@'%';
GRANT ALL ON `EstadoDenuncia`  TO 'playonAdmin'@'%';
GRANT ALL ON `DenunciaPlaya`  TO 'playonAdmin'@'%';
GRANT ALL ON `Playa`  TO 'playonAdmin'@'%';
GRANT ALL ON `Liquidacion`  TO 'playonAdmin'@'%';
GRANT ALL ON `EstadoPlaya`  TO 'playonAdmin'@'%';
GRANT ALL ON `TransaccionCliente`  TO 'playonAdmin'@'%';
GRANT ALL ON `TipoEstadia`  TO 'playonAdmin'@'%';
GRANT ALL ON `HistorialDeCambio`  TO 'playonAdmin'@'%';
GRANT ALL ON `Foto`  TO 'playonAdmin'@'%';
GRANT ALL ON `Vehiculo`  TO 'playonAdmin'@'%';
GRANT ALL ON `Empleado`  TO 'playonAdmin'@'%';
GRANT ALL ON `CargoEmpleado`  TO 'playonAdmin'@'%';
GRANT ALL ON `PerfilPlaya`  TO 'playonAdmin'@'%';
GRANT ALL ON `Abono`  TO 'playonAdmin'@'%';
GRANT ALL ON `Pais`  TO 'playonAdmin'@'%';
GRANT ALL ON `Tarifa`  TO 'playonAdmin'@'%';
GRANT ALL ON `Barrio`  TO 'playonAdmin'@'%';
GRANT ALL ON `Usuario`  TO 'playonAdmin'@'%';
GRANT ALL ON `TipoPago`  TO 'playonAdmin'@'%';
GRANT ALL ON `TransaccionPlaya`  TO 'playonAdmin'@'%';
GRANT ALL ON `MarcaVehiculo`  TO 'playonAdmin'@'%';
GRANT ALL ON `Estadia`  TO 'playonAdmin'@'%';
GRANT ALL ON `ModeloVehiculo`  TO 'playonAdmin'@'%';
GRANT ALL ON `Localidad`  TO 'playonAdmin'@'%';
GRANT ALL ON `Cliente`  TO 'playonAdmin'@'%';
GRANT ALL ON `TipoDoc`  TO 'playonAdmin'@'%';
GRANT ALL ON `Publicidad`  TO 'playonAdmin'@'%';
GRANT ALL ON `DetalleEstadia` TO 'playonAdmin'@'%';
GRANT ALL ON `Sesion`  TO 'playonAdmin'@'%';
GRANT ALL ON `Posicion`  TO 'playonAdmin'@'%';
GRANT ALL ON `EstadoPromocion`  TO 'playonAdmin'@'%';
GRANT ALL ON `Comentario`  TO 'playonAdmin'@'%';
GRANT ALL ON `UsuarioSistema`  TO 'playonAdmin'@'%';
GRANT ALL ON `RolUsuario`  TO 'playonAdmin'@'%';

