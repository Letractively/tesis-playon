/*
-- Description: Script de generación de la estructura de la base de datos
-- 		del proyecto tesis-playon.
-- Project: Playon
-- Author: Alejandro Bostico
-- Date: 17/06/2012
-- Versión Actual: 1.1
--
-- HISTORIAL DE CAMBIOS
-- Version 1.0 - Versión Inicial
-- Versión 1.1 - Cambiada la Foreign Key CategoriaVehiculo.TarifaID(FK) --> Tarifa.TarifaID(PK)
--               por Cambiada la Foreign Key Tarifa.CategoriaVehiculoID(FK) --> CategoriaVehiculo.CategoriaVehiculoID(PK)
*/


/********************************************************
 * PRECAUCIÓN!!!					                    * 
 * Usar con cuidado que el script borra todas las 	    *
 * tablas antes de crearlas.				            *
 ********************************************************/


-- Descomentar la siguiente línea para borrar la base de datos
-- antes de crear las tablas en caso de que fuera necesario.
-- #DROP DATABASE IF EXISTS `tesis_playon`;

CREATE DATABASE  IF NOT EXISTS `tesis_playon` /*!40100 DEFAULT CHARACTER SET latin1 */;
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
-- Table structure for table `CuentaPlaya`
--

DROP TABLE IF EXISTS `CuentaPlaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CuentaPlaya` (
  `fechaCreacion` datetime DEFAULT NULL,
  `nroCuenta` int(11) DEFAULT NULL,
  `saldo` float DEFAULT NULL,
  `cuentaPlayaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_CuentaPlaya_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DenunciaVehiculo`
--

DROP TABLE IF EXISTS `DenunciaVehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DenunciaVehiculo` (
  `asunto` text,
  `fechaAlta` datetime DEFAULT NULL,
  `vehiculoID` int(11) DEFAULT NULL,
  `denunciaVehiculoID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`denunciaVehiculoID`),
  KEY `vehiculoID` (`vehiculoID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_DenunciaVehiculo_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_DenunciaVehiculo_Vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `Vehiculo` (`vehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Provincia`
--

DROP TABLE IF EXISTS `Provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Provincia` (
  `nombre` varchar(50) DEFAULT NULL,
  `provinciaID` int(11) NOT NULL,
  `paisID` int(11) DEFAULT NULL,
  PRIMARY KEY (`provinciaID`),
  KEY `paisID` (`paisID`),
  CONSTRAINT `FK_Provincia_Pais` FOREIGN KEY (`paisID`) REFERENCES `Pais` (`paisID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EstadoPublicidad`
--

DROP TABLE IF EXISTS `EstadoPublicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EstadoPublicidad` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `estadoPublicidadID` int(11) NOT NULL,
  PRIMARY KEY (`estadoPublicidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Promocion`
--

DROP TABLE IF EXISTS `Promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Promocion` (
  `descripcion` text,
  `descuento` float DEFAULT NULL,
  `fechaAlta` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `montoFijo` float DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `tarifaID` int(11) DEFAULT NULL,
  `promocionID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  `estadoPromocionID` int(11) NOT NULL,
  PRIMARY KEY (`promocionID`),
  KEY `estadoPromocionID` (`estadoPromocionID`),
  KEY `tarifaID` (`tarifaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_Promocion_EstadoPromocion` FOREIGN KEY (`estadoPromocionID`) REFERENCES `EstadoPromocion` (`estadoPromocionID`),
  CONSTRAINT `FK_Promocion_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Promocion_Tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `Tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ColorVehiculo`
--

DROP TABLE IF EXISTS `ColorVehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ColorVehiculo` (
  `nombre` varchar(50) NOT NULL,
  `colorVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`colorVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CuentaCliente`
--

DROP TABLE IF EXISTS `CuentaCliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CuentaCliente` (
  `fechaCreacion` datetime DEFAULT NULL,
  `nroCuenta` int(11) DEFAULT NULL,
  `saldo` float DEFAULT NULL,
  `cuentaClienteID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaClienteID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_CuentaCliente_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EstadoDenuncia`
--

DROP TABLE IF EXISTS `EstadoDenuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EstadoDenuncia` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoDenunciaID` int(11) NOT NULL,
  PRIMARY KEY (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DenunciaPlaya`
--

DROP TABLE IF EXISTS `DenunciaPlaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DenunciaPlaya` (
  `asunto` text,
  `fechaAlta` datetime DEFAULT NULL,
  `playaID` int(11) DEFAULT NULL,
  `denunciaPlayaID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`denunciaPlayaID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_DenunciaPlaya_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`),
  CONSTRAINT `FK_DenunciaPlaya_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Playa`
--

DROP TABLE IF EXISTS `Playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Playa` (
  `barrioID` int(11) DEFAULT NULL,
  `cuit` varchar(50) DEFAULT NULL,
  `disponibilidad` tinyint(1) NOT NULL,
  `domicilio` varchar(50) DEFAULT NULL,
  `estadoPlayaID` int(11) NOT NULL,
  `nombreComercial` varchar(50) DEFAULT NULL,
  `razonSocial` varchar(50) DEFAULT NULL,
  `playaID` int(11) NOT NULL,
  `estadiaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`playaID`),
  KEY `barrioID` (`barrioID`),
  KEY `estadoPlayaID` (`estadoPlayaID`),
  KEY `estadiaID` (`estadiaID`),
  CONSTRAINT `FK_Playa_Barrio` FOREIGN KEY (`barrioID`) REFERENCES `Barrio` (`barrioID`),
  CONSTRAINT `FK_Playa_Estadia` FOREIGN KEY (`estadiaID`) REFERENCES `Estadia` (`estadiaID`),
  CONSTRAINT `FK_Playa_EstadoPlaya` FOREIGN KEY (`estadoPlayaID`) REFERENCES `EstadoPlaya` (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia representa los datos administrativos de una playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Liquidacion`
--

DROP TABLE IF EXISTS `Liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Liquidacion` (
  `fecha` datetime DEFAULT NULL,
  `fechaHasta` datetime DEFAULT NULL,
  `ferchaDesde` datetime DEFAULT NULL,
  `importeTotal` float DEFAULT NULL,
  `liquidacionID` int(11) NOT NULL,
  `estadiaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`liquidacionID`),
  KEY `estadiaID` (`estadiaID`),
  CONSTRAINT `FK_Liquidacion_Estadia` FOREIGN KEY (`estadiaID`) REFERENCES `Estadia` (`estadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CategoriaVehiculo`
--

DROP TABLE IF EXISTS `CategoriaVehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CategoriaVehiculo` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `categoriaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Son los típos de vehículos. Moto, Auto, Utilitario, PickUp, etc.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EstadoPlaya`
--

DROP TABLE IF EXISTS `EstadoPlaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EstadoPlaya` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPlayaID` int(11) NOT NULL,
  PRIMARY KEY (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TransaccionCliente`
--

DROP TABLE IF EXISTS `TransaccionCliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TransaccionCliente` (
  `fecha` datetime DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `tipoPagoID` int(11) DEFAULT NULL,
  `transaccionClienteID` int(11) NOT NULL,
  `cuentaClienteID` int(11) NOT NULL,
  PRIMARY KEY (`transaccionClienteID`),
  KEY `cuentaClienteID` (`cuentaClienteID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  CONSTRAINT `FK_TransaccionCliente_CuentaCliente` FOREIGN KEY (`cuentaClienteID`) REFERENCES `CuentaCliente` (`cuentaClienteID`),
  CONSTRAINT `FK_TransaccionCliente_TipoPago` FOREIGN KEY (`tipoPagoID`) REFERENCES `TipoPago` (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoEstadia`
--

DROP TABLE IF EXISTS `TipoEstadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoEstadia` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL,
  PRIMARY KEY (`tipoEstadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='TIPOS DE ESTADIA:   * Por hora   * Por dia   * Por noche   * Por Mes   * Por Semana   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `HistorialDeCambio`
--

DROP TABLE IF EXISTS `HistorialDeCambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HistorialDeCambio` (
  `comentario` text,
  `estadoDenunciaID` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `historialDeCambioID` int(11) NOT NULL,
  `denunciaPlayaID` int(11) DEFAULT NULL,
  `denunciaVehiculoID` int(11) DEFAULT NULL,
  PRIMARY KEY (`historialDeCambioID`),
  KEY `estadoDenunciaID` (`estadoDenunciaID`),
  KEY `denunciaPlayaID` (`denunciaPlayaID`),
  KEY `denunciaVehiculoID` (`denunciaVehiculoID`),
  CONSTRAINT `FK_HistorialDeCambio_DenunciaPlaya` FOREIGN KEY (`denunciaPlayaID`) REFERENCES `DenunciaPlaya` (`denunciaPlayaID`),
  CONSTRAINT `FK_HistorialDeCambio_DenunciaVehiculo` FOREIGN KEY (`denunciaVehiculoID`) REFERENCES `DenunciaVehiculo` (`denunciaVehiculoID`),
  CONSTRAINT `FK_HistorialDeCambio_EstadoDenuncia` FOREIGN KEY (`estadoDenunciaID`) REFERENCES `EstadoDenuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Foto`
--

DROP TABLE IF EXISTS `Foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Foto` (
  `descripcion` text,
  `link` varchar(50) NOT NULL,
  `fotoID` int(11) NOT NULL,
  `perfilPlayaID` int(11) NOT NULL,
  PRIMARY KEY (`fotoID`),
  KEY `perfilPlayaID` (`perfilPlayaID`),
  CONSTRAINT `FK_Foto_PerfilPlaya` FOREIGN KEY (`perfilPlayaID`) REFERENCES `PerfilPlaya` (`perfilPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Vehiculo`
--

DROP TABLE IF EXISTS `Vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vehiculo` (
  `año` int(11) DEFAULT NULL,
  `categoriaID` int(11) DEFAULT NULL,
  `codigoBarra` varchar(50) DEFAULT NULL,
  `colorID` int(11) DEFAULT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `modeloVehiculoID` int(11) NOT NULL,
  `patente` varchar(50) DEFAULT NULL,
  `vehiculoID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`vehiculoID`),
  KEY `categoriaID` (`categoriaID`),
  KEY `modeloVehiculoID` (`modeloVehiculoID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_Vehiculo_CategoriaVehiculo` FOREIGN KEY (`categoriaID`) REFERENCES `CategoriaVehiculo` (`categoriaVehiculoID`),
  CONSTRAINT `FK_Vehiculo_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`),
  CONSTRAINT `FK_Vehiculo_ModeloVehiculo` FOREIGN KEY (`modeloVehiculoID`) REFERENCES `ModeloVehiculo` (`modeloVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Empleado`
--

DROP TABLE IF EXISTS `Empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Empleado` (
  `cargoEmpleadoID` int(11) DEFAULT NULL,
  `legajo` int(11) DEFAULT NULL,
  `empleadoID` int(11) NOT NULL,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`empleadoID`),
  KEY `cargoEmpleadoID` (`cargoEmpleadoID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_Empleado_CargoEmpleado` FOREIGN KEY (`cargoEmpleadoID`) REFERENCES `CargoEmpleado` (`cargoEmpleadoID`),
  CONSTRAINT `FK_Empleado_Usuario` FOREIGN KEY (`usuarioID`) REFERENCES `Usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CargoEmpleado`
--

DROP TABLE IF EXISTS `CargoEmpleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CargoEmpleado` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `cargoEmpleadoID` int(11) NOT NULL,
  PRIMARY KEY (`cargoEmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PerfilPlaya`
--

DROP TABLE IF EXISTS `PerfilPlaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PerfilPlaya` (
  `descripcion` text,
  `nombreComercial` varchar(50) NOT NULL,
  `perfilPlayaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`perfilPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_PerfilPlaya_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Esta clase va a contener todos los datos del perfil de la playa que se muestra en el sitio: fotos, nombre para mostrar, descripción, etc.    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Abono`
--

DROP TABLE IF EXISTS `Abono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Abono` (
  `fechaVigenciaDesde` datetime DEFAULT NULL,
  `fechaVigenciaHasta` datetime DEFAULT NULL,
  `tarifaID` int(11) DEFAULT NULL,
  `abonoID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`abonoID`),
  KEY `tarifaID` (`tarifaID`),
  KEY `clienteID` (`clienteID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_Abono_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`),
  CONSTRAINT `FK_Abono_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Abono_Tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `Tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Pais`
--

DROP TABLE IF EXISTS `Pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pais` (
  `nombre` varchar(50) DEFAULT NULL,
  `paisID` int(11) NOT NULL,
  PRIMARY KEY (`paisID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tarifa`
--

DROP TABLE IF EXISTS `Tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tarifa` (
  `fechaAlta` datetime DEFAULT NULL,
  `fechaBaja` datetime DEFAULT NULL,
  `importe` float NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL,
  `vigente` tinyint(1) DEFAULT NULL,
  `tarifaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  `categoriaVehiculoID` int(11) DEFAULT NULL,
  PRIMARY KEY (`tarifaID`),
  KEY `tipoEstadiaID` (`tipoEstadiaID`),
  KEY `categoriaVehiculoID` (`categoriaVehiculoID`),
  CONSTRAINT `FK_Tarifa_CategoriaVehiculo` FOREIGN KEY (`categoriaVehiculoID`) REFERENCES `CategoriaVehiculo` (`categoriaVehiculoID`),
  CONSTRAINT `FK_Tarifa_TipoEstadia` FOREIGN KEY (`tipoEstadiaID`) REFERENCES `TipoEstadia` (`tipoEstadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia contiene un precio de la tarifa que depende del Categoría de vehículo, tipo de estadía (mensual, por hora, etc).      CategoríaVehiculo: utilitario   TipoEstadía: Mensual   Precio/Tarifa: $720      CategoríaVehiculo: utilitario   TipoEstadía: Por hora   Precio/Tarifa: $14      CategoríaVehiculo: auto   TipoEstadía: Por hora   Precio/Tarifa: $12   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Barrio`
--

DROP TABLE IF EXISTS `Barrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Barrio` (
  `nombre` varchar(50) DEFAULT NULL,
  `barrioID` int(11) NOT NULL,
  `localidadID` int(11) DEFAULT NULL,
  PRIMARY KEY (`barrioID`),
  KEY `localidadID` (`localidadID`),
  CONSTRAINT `FK_Barrio_Localidad` FOREIGN KEY (`localidadID`) REFERENCES `Localidad` (`localidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `apellido` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `sesion` varchar(50) DEFAULT NULL,
  `usuario` varchar(50) NOT NULL,
  `usuarioID` int(11) NOT NULL,
  `tipoDocID` int(11) DEFAULT NULL,
  `nroDoc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`usuarioID`),
  KEY `tipoDocID` (`tipoDocID`),
  CONSTRAINT `FK_Usuario_TipoDoc` FOREIGN KEY (`tipoDocID`) REFERENCES `TipoDoc` (`tipoDocID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoPago`
--

DROP TABLE IF EXISTS `TipoPago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoPago` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `tipoPagoID` int(11) NOT NULL,
  PRIMARY KEY (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TransaccionPlaya`
--

DROP TABLE IF EXISTS `TransaccionPlaya`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TransaccionPlaya` (
  `fecha` datetime DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `tipoPagoID` int(11) DEFAULT NULL,
  `transaccionPlayaID` int(11) NOT NULL,
  `cuentaPlayaID` int(11) DEFAULT NULL,
  `liquidacionID` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaccionPlayaID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  KEY `cuentaPlayaID` (`cuentaPlayaID`),
  KEY `liquidacionID` (`liquidacionID`),
  CONSTRAINT `FK_TransaccionPlaya_CuentaPlaya` FOREIGN KEY (`cuentaPlayaID`) REFERENCES `CuentaPlaya` (`cuentaPlayaID`),
  CONSTRAINT `FK_TransaccionPlaya_Liquidacion` FOREIGN KEY (`liquidacionID`) REFERENCES `Liquidacion` (`liquidacionID`),
  CONSTRAINT `FK_TransaccionPlaya_TipoPago` FOREIGN KEY (`tipoPagoID`) REFERENCES `TipoPago` (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MarcaVehiculo`
--

DROP TABLE IF EXISTS `MarcaVehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MarcaVehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `marcaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`marcaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Estadia`
--

DROP TABLE IF EXISTS `Estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estadia` (
  `estadiaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`estadiaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_Estadia_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia de esta clase representa un conjunto de ingreso y egreso de los autos en cada playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ModeloVehiculo`
--

DROP TABLE IF EXISTS `ModeloVehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ModeloVehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `modeloVehiculoID` int(11) NOT NULL,
  `marcaVehiculoID` int(11) DEFAULT NULL,
  PRIMARY KEY (`modeloVehiculoID`),
  KEY `marcaVehiculoID` (`marcaVehiculoID`),
  CONSTRAINT `FK_ModeloVehiculo_MarcaVehiculo` FOREIGN KEY (`marcaVehiculoID`) REFERENCES `MarcaVehiculo` (`marcaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Localidad`
--

DROP TABLE IF EXISTS `Localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Localidad` (
  `nombre` varchar(50) DEFAULT NULL,
  `localidadID` int(11) NOT NULL,
  `provinciaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`localidadID`),
  KEY `provinciaID` (`provinciaID`),
  CONSTRAINT `FK_Localidad_Provincia` FOREIGN KEY (`provinciaID`) REFERENCES `Provincia` (`provinciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `barrioID` int(11) DEFAULT NULL,
  `cuentaClienteID` int(11) DEFAULT NULL,
  `domicilio` text,
  `nroCliente` int(11) DEFAULT NULL,
  `telefono` text,
  `clienteID` int(11) NOT NULL,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`clienteID`),
  KEY `barrioID` (`barrioID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_Cliente_Barrio` FOREIGN KEY (`barrioID`) REFERENCES `Barrio` (`barrioID`),
  CONSTRAINT `FK_Cliente_Usuario` FOREIGN KEY (`usuarioID`) REFERENCES `Usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TipoDoc`
--

DROP TABLE IF EXISTS `TipoDoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoDoc` (
  `nombre` varchar(50) NOT NULL,
  `tipoDocID` int(11) NOT NULL,
  PRIMARY KEY (`tipoDocID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Publicidad`
--

DROP TABLE IF EXISTS `Publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Publicidad` (
  `estadoPublicidadID` int(11) NOT NULL,
  `posicionID` int(11) NOT NULL,
  `urlImagen` varchar(50) DEFAULT NULL,
  `publicidadID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`publicidadID`),
  KEY `estadoPublicidadID` (`estadoPublicidadID`),
  KEY `posicionID` (`posicionID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_Publicidad_EstadoPublicidad` FOREIGN KEY (`estadoPublicidadID`) REFERENCES `EstadoPublicidad` (`estadoPublicidadID`),
  CONSTRAINT `FK_Publicidad_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Publicidad_Posicion` FOREIGN KEY (`posicionID`) REFERENCES `Posicion` (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DetalleEstadia`
--

DROP TABLE IF EXISTS `DetalleEstadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DetalleEstadia` (
  `transaccionClienteID` int(11) DEFAULT NULL,
  `empleadoID` int(11) DEFAULT NULL,
  `fechaHoraEgreso` datetime DEFAULT NULL,
  `fechaHoraIngreso` datetime DEFAULT NULL,
  `importeTotal` float DEFAULT NULL,
  `promocionID` int(11) DEFAULT NULL,
  `tarifaID` int(11) NOT NULL,
  `vehiculoID` int(11) DEFAULT NULL,
  `detalleEstadiaID` int(11) NOT NULL,
  `estadiaID` int(11) NOT NULL,
  PRIMARY KEY (`detalleEstadiaID`),
  KEY `empleadoID` (`empleadoID`),
  KEY `estadiaID` (`estadiaID`),
  KEY `promocionID` (`promocionID`),
  KEY `tarifaID` (`tarifaID`),
  KEY `transaccionClienteID` (`transaccionClienteID`),
  KEY `vehiculoID` (`vehiculoID`),
  CONSTRAINT `FK_DetalleEstadia_Empleado` FOREIGN KEY (`empleadoID`) REFERENCES `Empleado` (`empleadoID`),
  CONSTRAINT `FK_DetalleEstadia_Estadia` FOREIGN KEY (`estadiaID`) REFERENCES `Estadia` (`estadiaID`),
  CONSTRAINT `FK_DetalleEstadia_Promocion` FOREIGN KEY (`promocionID`) REFERENCES `Promocion` (`promocionID`),
  CONSTRAINT `FK_DetalleEstadia_Tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `Tarifa` (`tarifaID`),
  CONSTRAINT `FK_DetalleEstadia_TransaccionCliente` FOREIGN KEY (`transaccionClienteID`) REFERENCES `TransaccionCliente` (`transaccionClienteID`),
  CONSTRAINT `FK_DetalleEstadia_Vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `Vehiculo` (`vehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Sesion`
--

DROP TABLE IF EXISTS `Sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sesion` (
  `fechaFin` datetime DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `idSesion` varchar(50) NOT NULL,
  `sesionID` int(11) NOT NULL,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`sesionID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_Sesion_Usuario` FOREIGN KEY (`usuarioID`) REFERENCES `Usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Posicion`
--

DROP TABLE IF EXISTS `Posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Posicion` (
  `tamañoKBMax` int(11) DEFAULT NULL,
  `tamañoX` int(11) DEFAULT NULL,
  `tamañoY` int(11) DEFAULT NULL,
  `ubicacion` text,
  `posicionID` int(11) NOT NULL,
  PRIMARY KEY (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Clase que contiene los datos sobre las ubicaciones y posiciones de las publicidades. (layout de publicidades)   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EstadoPromocion`
--

DROP TABLE IF EXISTS `EstadoPromocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EstadoPromocion` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPromocionID` int(11) NOT NULL,
  PRIMARY KEY (`estadoPromocionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Comentario`
--

DROP TABLE IF EXISTS `Comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comentario` (
  `calificacion` int(11) DEFAULT NULL,
  `comentario` text NOT NULL,
  `fecha` datetime NOT NULL,
  `habilitado` tinyint(1) DEFAULT NULL,
  `playaID` int(11) DEFAULT NULL,
  `comentarioID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`comentarioID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_Comentario_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`),
  CONSTRAINT `FK_Comentario_PerfilPlaya` FOREIGN KEY (`playaID`) REFERENCES `PerfilPlaya` (`perfilPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UsuarioSistema`
--

DROP TABLE IF EXISTS `UsuarioSistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UsuarioSistema` (
  `rolUsuarioID` int(11) NOT NULL,
  `usuarioSistemaID` int(11) NOT NULL,
  PRIMARY KEY (`usuarioSistemaID`),
  KEY `rolUsuarioID` (`rolUsuarioID`),
  KEY `usuarioSistemaID` (`usuarioSistemaID`),
  CONSTRAINT `FK_UsuarioSistema_RolUsuario` FOREIGN KEY (`rolUsuarioID`) REFERENCES `RolUsuario` (`rolUsuarioID`),
  CONSTRAINT `FK_UsuarioSistema_Usuario` FOREIGN KEY (`usuarioSistemaID`) REFERENCES `Usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RolUsuario`
--

DROP TABLE IF EXISTS `RolUsuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RolUsuario` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `rolUsuarioID` int(11) NOT NULL,
  PRIMARY KEY (`rolUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Los roles van a ser: Administrador, Cliente, Dueño, Playero, Gerente, etc. y de estos van a depender los permisos que tenga cada uno dentro del sistema.   ';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-18 11:50:13
