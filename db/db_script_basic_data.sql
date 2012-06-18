/*
-- Description: Script de carga de datos básicos de la base de datos
-- 		del proyecto tesis-playon.
-- Project: Playon
-- Author: Alejandro Bostico
-- Date: 18/06/2012
-- Versión Actual: 1.0
--
-- HISTORIAL DE CAMBIOS
-- Version 1.0 - Versión Inicial
*/

-- #CREATE DATABASE  IF NOT EXISTS `tesis_playon` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tesis_playon`;
-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: tesis_playon
-- ------------------------------------------------------
-- Server version	5.5.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `CuentaPlaya`
--

LOCK TABLES `CuentaPlaya` WRITE;
/*!40000 ALTER TABLE `CuentaPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `CuentaPlaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `DenunciaVehiculo`
--

LOCK TABLES `DenunciaVehiculo` WRITE;
/*!40000 ALTER TABLE `DenunciaVehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `DenunciaVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Provincia`
--

LOCK TABLES `Provincia` WRITE;
/*!40000 ALTER TABLE `Provincia` DISABLE KEYS */;
/*!40000 ALTER TABLE `Provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EstadoPublicidad`
--

LOCK TABLES `EstadoPublicidad` WRITE;
/*!40000 ALTER TABLE `EstadoPublicidad` DISABLE KEYS */;
INSERT INTO `EstadoPublicidad` (`descripcion`, `nombre`, `estadoPublicidadID`) VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3),('Aprobada y publicandose','Vigente',4),('Período de publicación vencido','Vencida',5);
/*!40000 ALTER TABLE `EstadoPublicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Promocion`
--

LOCK TABLES `Promocion` WRITE;
/*!40000 ALTER TABLE `Promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ColorVehiculo`
--

LOCK TABLES `ColorVehiculo` WRITE;
/*!40000 ALTER TABLE `ColorVehiculo` DISABLE KEYS */;
INSERT INTO `ColorVehiculo` (`nombre`, `colorVehiculoID`) VALUES ('Blanco',1),('Negro',2),('Rojo',3),('Amarillo',4),('Verde Claro',5),('Azul',6),('Celeste',7),('Gris Claro',8),('Gris Oscuro',9),('Naranja',10),('Verde Oscuro',11),('Bordo',12);
/*!40000 ALTER TABLE `ColorVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CuentaCliente`
--

LOCK TABLES `CuentaCliente` WRITE;
/*!40000 ALTER TABLE `CuentaCliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `CuentaCliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EstadoDenuncia`
--

LOCK TABLES `EstadoDenuncia` WRITE;
/*!40000 ALTER TABLE `EstadoDenuncia` DISABLE KEYS */;
INSERT INTO `EstadoDenuncia` (`descripcion`, `nombre`, `estadoDenunciaID`) VALUES ('Pendiente de auditoría','Pendiente',1),('En proceso de investigación','En Proceso',2),('Acepatada','Aceptada',3),('Rechazada','Rechazada',4),('Anulada','Anulada',5),('Dada de Baja / Cancelada','De Baja',6);
/*!40000 ALTER TABLE `EstadoDenuncia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `DenunciaPlaya`
--

LOCK TABLES `DenunciaPlaya` WRITE;
/*!40000 ALTER TABLE `DenunciaPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `DenunciaPlaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Playa`
--

LOCK TABLES `Playa` WRITE;
/*!40000 ALTER TABLE `Playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Liquidacion`
--

LOCK TABLES `Liquidacion` WRITE;
/*!40000 ALTER TABLE `Liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CategoriaVehiculo`
--

LOCK TABLES `CategoriaVehiculo` WRITE;
/*!40000 ALTER TABLE `CategoriaVehiculo` DISABLE KEYS */;
INSERT INTO `CategoriaVehiculo` (`descripcion`, `nombre`, `categoriaVehiculoID`) VALUES ('','Auto',1),('','Moto',2),('','Utilitario',3),('','PickUp / 4X4',4);
/*!40000 ALTER TABLE `CategoriaVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EstadoPlaya`
--

LOCK TABLES `EstadoPlaya` WRITE;
/*!40000 ALTER TABLE `EstadoPlaya` DISABLE KEYS */;
INSERT INTO `EstadoPlaya` (`descripcion`, `nombre`, `estadoPlayaID`) VALUES ('Pendiente de Auditoría','Pendiente',1),('Aprobada luego de auditoría','Aprobada',2),('Rechazada luego de auditoría','Rechazada',3),('Dada de baja','De Baja',4);
/*!40000 ALTER TABLE `EstadoPlaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TransaccionCliente`
--

LOCK TABLES `TransaccionCliente` WRITE;
/*!40000 ALTER TABLE `TransaccionCliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `TransaccionCliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TipoEstadia`
--

LOCK TABLES `TipoEstadia` WRITE;
/*!40000 ALTER TABLE `TipoEstadia` DISABLE KEYS */;
INSERT INTO `TipoEstadia` (`descripcion`, `nombre`, `tipoEstadiaID`) VALUES ('','Por Hora',1),('','Por Mes',2),('','Por Noche',3),('','Por Día',4),('','Por Semana',5);
/*!40000 ALTER TABLE `TipoEstadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `HistorialDeCambio`
--

LOCK TABLES `HistorialDeCambio` WRITE;
/*!40000 ALTER TABLE `HistorialDeCambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `HistorialDeCambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Foto`
--

LOCK TABLES `Foto` WRITE;
/*!40000 ALTER TABLE `Foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `Foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Vehiculo`
--

LOCK TABLES `Vehiculo` WRITE;
/*!40000 ALTER TABLE `Vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CargoEmpleado`
--

LOCK TABLES `CargoEmpleado` WRITE;
/*!40000 ALTER TABLE `CargoEmpleado` DISABLE KEYS */;
INSERT INTO `CargoEmpleado` (`descripcion`, `nombre`, `cargoEmpleadoID`) VALUES ('','Gerente General',1),('','Encargado',2),('','Playero',3);
/*!40000 ALTER TABLE `CargoEmpleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PerfilPlaya`
--

LOCK TABLES `PerfilPlaya` WRITE;
/*!40000 ALTER TABLE `PerfilPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `PerfilPlaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Abono`
--

LOCK TABLES `Abono` WRITE;
/*!40000 ALTER TABLE `Abono` DISABLE KEYS */;
/*!40000 ALTER TABLE `Abono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Pais`
--

LOCK TABLES `Pais` WRITE;
/*!40000 ALTER TABLE `Pais` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Tarifa`
--

LOCK TABLES `Tarifa` WRITE;
/*!40000 ALTER TABLE `Tarifa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Barrio`
--

LOCK TABLES `Barrio` WRITE;
/*!40000 ALTER TABLE `Barrio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Empleado`
--

LOCK TABLES `Empleado` WRITE;
/*!40000 ALTER TABLE `Empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `Empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TipoPago`
--

LOCK TABLES `TipoPago` WRITE;
/*!40000 ALTER TABLE `TipoPago` DISABLE KEYS */;
INSERT INTO `TipoPago` (`descripcion`, `nombre`, `tipoPagoID`) VALUES ('Contado Efectivo','Contado',1),('Tarjeta de débito','Tarjeta Débito',2),('Tarjeta de crédito','Tarjeta Crédito',3),('DineroMail','DineroMail',4),('Cheque','Cheque',5);
/*!40000 ALTER TABLE `TipoPago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TransaccionPlaya`
--

LOCK TABLES `TransaccionPlaya` WRITE;
/*!40000 ALTER TABLE `TransaccionPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `TransaccionPlaya` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Posicion`
--

LOCK TABLES `Posicion` WRITE;
/*!40000 ALTER TABLE `Posicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Estadia`
--

LOCK TABLES `Estadia` WRITE;
/*!40000 ALTER TABLE `Estadia` DISABLE KEYS */;
/*!40000 ALTER TABLE `Estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ModeloVehiculo`
--

LOCK TABLES `ModeloVehiculo` WRITE;
/*!40000 ALTER TABLE `ModeloVehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ModeloVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Localidad`
--

LOCK TABLES `Localidad` WRITE;
/*!40000 ALTER TABLE `Localidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TipoDoc`
--

LOCK TABLES `TipoDoc` WRITE;
/*!40000 ALTER TABLE `TipoDoc` DISABLE KEYS */;
INSERT INTO `TipoDoc` (`nombre`, `tipoDocID`) VALUES ('D.N.I.',1),('L.C.',2),('L.E.',3),('C.I.',4);
/*!40000 ALTER TABLE `TipoDoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Publicidad`
--

LOCK TABLES `Publicidad` WRITE;
/*!40000 ALTER TABLE `Publicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `DetalleEstadia`
--

LOCK TABLES `DetalleEstadia` WRITE;
/*!40000 ALTER TABLE `DetalleEstadia` DISABLE KEYS */;
/*!40000 ALTER TABLE `DetalleEstadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Sesion`
--

LOCK TABLES `Sesion` WRITE;
/*!40000 ALTER TABLE `Sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `MarcaVehiculo`
--

LOCK TABLES `MarcaVehiculo` WRITE;
/*!40000 ALTER TABLE `MarcaVehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `MarcaVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EstadoPromocion`
--

LOCK TABLES `EstadoPromocion` WRITE;
/*!40000 ALTER TABLE `EstadoPromocion` DISABLE KEYS */;
INSERT INTO `EstadoPromocion` (`descripcion`, `nombre`, `estadoPromocionID`) VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3),('Aprobada y publicandose','Vigente',4),('Período de publicación vencido','Vencida',5);
/*!40000 ALTER TABLE `EstadoPromocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Comentario`
--

LOCK TABLES `Comentario` WRITE;
/*!40000 ALTER TABLE `Comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `UsuarioSistema`
--

LOCK TABLES `UsuarioSistema` WRITE;
/*!40000 ALTER TABLE `UsuarioSistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `UsuarioSistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `RolUsuario`
--

LOCK TABLES `RolUsuario` WRITE;
/*!40000 ALTER TABLE `RolUsuario` DISABLE KEYS */;
INSERT INTO `RolUsuario` (`descripcion`, `nombre`, `rolUsuarioID`) VALUES ('Administrador del sistema','Administrador',1),('Auditor del sistema','Auditor',2),('Usuario del Area administrativa/contable','Administración',3),('Usuario sin permisos especiales','Usuario',4);
/*!40000 ALTER TABLE `RolUsuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-18 11:31:16
