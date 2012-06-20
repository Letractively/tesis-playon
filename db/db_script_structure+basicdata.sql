/*
-- Description: Script de generación de la estructura y carga de datos básicos 
-- 		de la base de datos del proyecto tesis-playon.
-- Project: Playon
-- Author: Alejandro Bostico
-- Date: 20/06/2012
-- Versión Actual: 1.1
--
-- HISTORIAL DE CAMBIOS
-- Version 1.0 (18/06/2012) - Versión Inicial
-- Version 1.1 (20/06/2012) - Agregada la clase Favorito
		Agregada la foreign key TransaccionPlaya.detalleEstadiaID(FK) --> DetalleEstadia.detalleEstadiaID(PK)
*/


/********************************************************
 * PRECAUCIÓN!!!					                    * 
 * Usar con cuidado que el script borra todas las 	    *
 * tablas antes de crearlas.				            *
 ********************************************************/

-- Descomentar la siguiente línea para borrar la base de datos
-- antes de crear las tablas en caso de que fuera necesario.
-- #DROP DATABASE IF EXISTS `tesis_playon`;

CREATE DATABASE  IF NOT EXISTS `tesis_playon` /*!40100 DEFAULT CHARACTER SET latin1 */;CREATE DATABASE  IF NOT EXISTS `tesis_playon` /*!40100 DEFAULT CHARACTER SET latin1 */;
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
-- Dumping data for table `CuentaPlaya`
--
-- ORDER BY:  `cuentaPlayaID`

LOCK TABLES `CuentaPlaya` WRITE;
/*!40000 ALTER TABLE `CuentaPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `CuentaPlaya` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `DenunciaVehiculo`
--
-- ORDER BY:  `denunciaVehiculoID`

LOCK TABLES `DenunciaVehiculo` WRITE;
/*!40000 ALTER TABLE `DenunciaVehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `DenunciaVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Provincia`
--
-- ORDER BY:  `provinciaID`

LOCK TABLES `Provincia` WRITE;
/*!40000 ALTER TABLE `Provincia` DISABLE KEYS */;
/*!40000 ALTER TABLE `Provincia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `EstadoPublicidad`
--
-- ORDER BY:  `estadoPublicidadID`

LOCK TABLES `EstadoPublicidad` WRITE;
/*!40000 ALTER TABLE `EstadoPublicidad` DISABLE KEYS */;
INSERT INTO `EstadoPublicidad` (`descripcion`, `nombre`, `estadoPublicidadID`) VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3),('Aprobada y publicandose','Vigente',4),('Período de publicación vencido','Vencida',5);
/*!40000 ALTER TABLE `EstadoPublicidad` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Promocion_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Promocion_EstadoPromocion` FOREIGN KEY (`estadoPromocionID`) REFERENCES `EstadoPromocion` (`estadoPromocionID`),
  CONSTRAINT `FK_Promocion_Tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `Tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Promocion`
--
-- ORDER BY:  `promocionID`

LOCK TABLES `Promocion` WRITE;
/*!40000 ALTER TABLE `Promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Promocion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ColorVehiculo`
--
-- ORDER BY:  `colorVehiculoID`

LOCK TABLES `ColorVehiculo` WRITE;
/*!40000 ALTER TABLE `ColorVehiculo` DISABLE KEYS */;
INSERT INTO `ColorVehiculo` (`nombre`, `colorVehiculoID`) VALUES ('Blanco',1),('Negro',2),('Rojo',3),('Amarillo',4),('Verde Claro',5),('Azul',6),('Celeste',7),('Gris Claro',8),('Gris Oscuro',9),('Naranja',10),('Verde Oscuro',11),('Bordo',12);
/*!40000 ALTER TABLE `ColorVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `CuentaCliente`
--
-- ORDER BY:  `cuentaClienteID`

LOCK TABLES `CuentaCliente` WRITE;
/*!40000 ALTER TABLE `CuentaCliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `CuentaCliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EstadoDenuncia`
--

DROP TABLE IF EXISTS `EstadoDenuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EstadoDenuncia` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `estadoDenunciaID` int(11) NOT NULL,
  PRIMARY KEY (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EstadoDenuncia`
--
-- ORDER BY:  `estadoDenunciaID`

LOCK TABLES `EstadoDenuncia` WRITE;
/*!40000 ALTER TABLE `EstadoDenuncia` DISABLE KEYS */;
INSERT INTO `EstadoDenuncia` (`descripcion`, `nombre`, `estadoDenunciaID`) VALUES ('Pendiente de auditoría','Pendiente',1),('En proceso de investigación','En Proceso',2),('Acepatada','Aceptada',3),('Rechazada','Rechazada',4),('Anulada','Anulada',5),('Dada de Baja / Cancelada','De Baja',6);
/*!40000 ALTER TABLE `EstadoDenuncia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `DenunciaPlaya`
--
-- ORDER BY:  `denunciaPlayaID`

LOCK TABLES `DenunciaPlaya` WRITE;
/*!40000 ALTER TABLE `DenunciaPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `DenunciaPlaya` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Playa_Estadia` FOREIGN KEY (`estadiaID`) REFERENCES `Estadia` (`estadiaID`),
  CONSTRAINT `FK_Playa_Barrio` FOREIGN KEY (`barrioID`) REFERENCES `Barrio` (`barrioID`),
  CONSTRAINT `FK_Playa_EstadoPlaya` FOREIGN KEY (`estadoPlayaID`) REFERENCES `EstadoPlaya` (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia representa los datos administrativos de una playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Playa`
--
-- ORDER BY:  `playaID`

LOCK TABLES `Playa` WRITE;
/*!40000 ALTER TABLE `Playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Playa` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Liquidacion`
--
-- ORDER BY:  `liquidacionID`

LOCK TABLES `Liquidacion` WRITE;
/*!40000 ALTER TABLE `Liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `CategoriaVehiculo`
--
-- ORDER BY:  `categoriaVehiculoID`

LOCK TABLES `CategoriaVehiculo` WRITE;
/*!40000 ALTER TABLE `CategoriaVehiculo` DISABLE KEYS */;
INSERT INTO `CategoriaVehiculo` (`descripcion`, `nombre`, `categoriaVehiculoID`) VALUES ('','Auto',1),('','Moto',2),('','Utilitario',3),('','PickUp / 4X4',4);
/*!40000 ALTER TABLE `CategoriaVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `EstadoPlaya`
--
-- ORDER BY:  `estadoPlayaID`

LOCK TABLES `EstadoPlaya` WRITE;
/*!40000 ALTER TABLE `EstadoPlaya` DISABLE KEYS */;
INSERT INTO `EstadoPlaya` (`descripcion`, `nombre`, `estadoPlayaID`) VALUES ('Pendiente de Auditoría','Pendiente',1),('Aprobada luego de auditoría','Aprobada',2),('Rechazada luego de auditoría','Rechazada',3),('Dada de baja','De Baja',4);
/*!40000 ALTER TABLE `EstadoPlaya` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_TransaccionCliente_TipoPago` FOREIGN KEY (`tipoPagoID`) REFERENCES `TipoPago` (`tipoPagoID`),
  CONSTRAINT `FK_TransaccionCliente_CuentaCliente` FOREIGN KEY (`cuentaClienteID`) REFERENCES `CuentaCliente` (`cuentaClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TransaccionCliente`
--
-- ORDER BY:  `transaccionClienteID`

LOCK TABLES `TransaccionCliente` WRITE;
/*!40000 ALTER TABLE `TransaccionCliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `TransaccionCliente` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `TipoEstadia`
--
-- ORDER BY:  `tipoEstadiaID`

LOCK TABLES `TipoEstadia` WRITE;
/*!40000 ALTER TABLE `TipoEstadia` DISABLE KEYS */;
INSERT INTO `TipoEstadia` (`descripcion`, `nombre`, `tipoEstadiaID`) VALUES ('','Por Hora',1),('','Por Mes',2),('','Por Noche',3),('','Por Día',4),('','Por Semana',5);
/*!40000 ALTER TABLE `TipoEstadia` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_HistorialDeCambio_DenunciaVehiculo` FOREIGN KEY (`denunciaVehiculoID`) REFERENCES `DenunciaVehiculo` (`denunciaVehiculoID`),
  CONSTRAINT `FK_HistorialDeCambio_DenunciaPlaya` FOREIGN KEY (`denunciaPlayaID`) REFERENCES `DenunciaPlaya` (`denunciaPlayaID`),
  CONSTRAINT `FK_HistorialDeCambio_EstadoDenuncia` FOREIGN KEY (`estadoDenunciaID`) REFERENCES `EstadoDenuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HistorialDeCambio`
--
-- ORDER BY:  `historialDeCambioID`

LOCK TABLES `HistorialDeCambio` WRITE;
/*!40000 ALTER TABLE `HistorialDeCambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `HistorialDeCambio` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Foto`
--
-- ORDER BY:  `fotoID`

LOCK TABLES `Foto` WRITE;
/*!40000 ALTER TABLE `Foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `Foto` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Vehiculo_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`),
  CONSTRAINT `FK_Vehiculo_CategoriaVehiculo` FOREIGN KEY (`categoriaID`) REFERENCES `CategoriaVehiculo` (`categoriaVehiculoID`),
  CONSTRAINT `FK_Vehiculo_ModeloVehiculo` FOREIGN KEY (`modeloVehiculoID`) REFERENCES `ModeloVehiculo` (`modeloVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vehiculo`
--
-- ORDER BY:  `vehiculoID`

LOCK TABLES `Vehiculo` WRITE;
/*!40000 ALTER TABLE `Vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `Vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Empleado_Usuario` FOREIGN KEY (`usuarioID`) REFERENCES `Usuario` (`usuarioID`),
  CONSTRAINT `FK_Empleado_CargoEmpleado` FOREIGN KEY (`cargoEmpleadoID`) REFERENCES `CargoEmpleado` (`cargoEmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empleado`
--
-- ORDER BY:  `empleadoID`

LOCK TABLES `Empleado` WRITE;
/*!40000 ALTER TABLE `Empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `Empleado` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `CargoEmpleado`
--
-- ORDER BY:  `cargoEmpleadoID`

LOCK TABLES `CargoEmpleado` WRITE;
/*!40000 ALTER TABLE `CargoEmpleado` DISABLE KEYS */;
INSERT INTO `CargoEmpleado` (`descripcion`, `nombre`, `cargoEmpleadoID`) VALUES ('','Gerente General',1),('','Encargado',2),('','Playero',3);
/*!40000 ALTER TABLE `CargoEmpleado` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `PerfilPlaya`
--
-- ORDER BY:  `perfilPlayaID`

LOCK TABLES `PerfilPlaya` WRITE;
/*!40000 ALTER TABLE `PerfilPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `PerfilPlaya` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Abono_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Abono_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`),
  CONSTRAINT `FK_Abono_Tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `Tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Abono`
--
-- ORDER BY:  `abonoID`

LOCK TABLES `Abono` WRITE;
/*!40000 ALTER TABLE `Abono` DISABLE KEYS */;
/*!40000 ALTER TABLE `Abono` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Pais`
--
-- ORDER BY:  `paisID`

LOCK TABLES `Pais` WRITE;
/*!40000 ALTER TABLE `Pais` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pais` ENABLE KEYS */;
UNLOCK TABLES;

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
  `vigente` tinyint(1) DEFAULT NULL,
  `tarifaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  `tipoEstadiaID` int(11) NOT NULL,
  `categoriaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`tarifaID`),
  KEY `categoriaVehiculoID` (`categoriaVehiculoID`),
  KEY `tipoEstadiaID` (`tipoEstadiaID`),
  CONSTRAINT `FK_Tarifa_TipoEstadia` FOREIGN KEY (`tipoEstadiaID`) REFERENCES `TipoEstadia` (`tipoEstadiaID`),
  CONSTRAINT `FK_Tarifa_CategoriaVehiculo` FOREIGN KEY (`categoriaVehiculoID`) REFERENCES `CategoriaVehiculo` (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia contiene un precio de la tarifa que depende del Categoría de vehículo, tipo de estadía (mensual, por hora, etc).      CategoríaVehiculo: utilitario   TipoEstadía: Mensual   Precio/Tarifa: $720      CategoríaVehiculo: utilitario   TipoEstadía: Por hora   Precio/Tarifa: $14      CategoríaVehiculo: auto   TipoEstadía: Por hora   Precio/Tarifa: $12   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tarifa`
--
-- ORDER BY:  `tarifaID`

LOCK TABLES `Tarifa` WRITE;
/*!40000 ALTER TABLE `Tarifa` DISABLE KEYS */;
/*!40000 ALTER TABLE `Tarifa` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Barrio`
--
-- ORDER BY:  `barrioID`

LOCK TABLES `Barrio` WRITE;
/*!40000 ALTER TABLE `Barrio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermisosUsuarios`
--

DROP TABLE IF EXISTS `PermisosUsuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermisosUsuarios` (
  `permisosUsuariosID` int(11) NOT NULL,
  PRIMARY KEY (`permisosUsuariosID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermisosUsuarios`
--
-- ORDER BY:  `permisosUsuariosID`

LOCK TABLES `PermisosUsuarios` WRITE;
/*!40000 ALTER TABLE `PermisosUsuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `PermisosUsuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Favorito`
--

DROP TABLE IF EXISTS `Favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Favorito` (
  `clienteID` int(11) NOT NULL,
  `playaID` int(11) NOT NULL,
  PRIMARY KEY (`clienteID`,`playaID`),
  KEY `clienteID` (`clienteID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_Favorito_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Favorito_Cliente` FOREIGN KEY (`clienteID`) REFERENCES `Cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Favorito`
--
-- ORDER BY:  `clienteID`,`playaID`

LOCK TABLES `Favorito` WRITE;
/*!40000 ALTER TABLE `Favorito` DISABLE KEYS */;
/*!40000 ALTER TABLE `Favorito` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Usuario`
--
-- ORDER BY:  `usuarioID`

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `TipoPago`
--
-- ORDER BY:  `tipoPagoID`

LOCK TABLES `TipoPago` WRITE;
/*!40000 ALTER TABLE `TipoPago` DISABLE KEYS */;
INSERT INTO `TipoPago` (`descripcion`, `nombre`, `tipoPagoID`) VALUES ('Contado Efectivo','Contado',1),('Tarjeta de débito','Tarjeta Débito',2),('Tarjeta de crédito','Tarjeta Crédito',3),('DineroMail','DineroMail',4),('Cheque','Cheque',5);
/*!40000 ALTER TABLE `TipoPago` ENABLE KEYS */;
UNLOCK TABLES;

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
  `detalleEstadiaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`transaccionPlayaID`),
  KEY `detalleEstadiaID` (`detalleEstadiaID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  KEY `cuentaPlayaID` (`cuentaPlayaID`),
  KEY `liquidacionID` (`liquidacionID`),
  CONSTRAINT `FK_TransaccionPlaya_Liquidacion` FOREIGN KEY (`liquidacionID`) REFERENCES `Liquidacion` (`liquidacionID`),
  CONSTRAINT `FK_TransaccionPlaya_CuentaPlaya` FOREIGN KEY (`cuentaPlayaID`) REFERENCES `CuentaPlaya` (`cuentaPlayaID`),
  CONSTRAINT `FK_TransaccionPlaya_DetalleEstadia` FOREIGN KEY (`detalleEstadiaID`) REFERENCES `DetalleEstadia` (`detalleEstadiaID`),
  CONSTRAINT `FK_TransaccionPlaya_TipoPago` FOREIGN KEY (`tipoPagoID`) REFERENCES `TipoPago` (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TransaccionPlaya`
--
-- ORDER BY:  `transaccionPlayaID`

LOCK TABLES `TransaccionPlaya` WRITE;
/*!40000 ALTER TABLE `TransaccionPlaya` DISABLE KEYS */;
/*!40000 ALTER TABLE `TransaccionPlaya` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `MarcaVehiculo`
--
-- ORDER BY:  `marcaVehiculoID`

LOCK TABLES `MarcaVehiculo` WRITE;
/*!40000 ALTER TABLE `MarcaVehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `MarcaVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Estadia`
--
-- ORDER BY:  `estadiaID`

LOCK TABLES `Estadia` WRITE;
/*!40000 ALTER TABLE `Estadia` DISABLE KEYS */;
/*!40000 ALTER TABLE `Estadia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ModeloVehiculo`
--
-- ORDER BY:  `modeloVehiculoID`

LOCK TABLES `ModeloVehiculo` WRITE;
/*!40000 ALTER TABLE `ModeloVehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ModeloVehiculo` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Localidad`
--
-- ORDER BY:  `localidadID`

LOCK TABLES `Localidad` WRITE;
/*!40000 ALTER TABLE `Localidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Localidad` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Cliente_Usuario` FOREIGN KEY (`usuarioID`) REFERENCES `Usuario` (`usuarioID`),
  CONSTRAINT `FK_Cliente_Barrio` FOREIGN KEY (`barrioID`) REFERENCES `Barrio` (`barrioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--
-- ORDER BY:  `clienteID`

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `TipoDoc`
--
-- ORDER BY:  `tipoDocID`

LOCK TABLES `TipoDoc` WRITE;
/*!40000 ALTER TABLE `TipoDoc` DISABLE KEYS */;
INSERT INTO `TipoDoc` (`nombre`, `tipoDocID`) VALUES ('D.N.I.',1),('L.C.',2),('L.E.',3),('C.I.',4);
/*!40000 ALTER TABLE `TipoDoc` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_Publicidad_Playa` FOREIGN KEY (`playaID`) REFERENCES `Playa` (`playaID`),
  CONSTRAINT `FK_Publicidad_EstadoPublicidad` FOREIGN KEY (`estadoPublicidadID`) REFERENCES `EstadoPublicidad` (`estadoPublicidadID`),
  CONSTRAINT `FK_Publicidad_Posicion` FOREIGN KEY (`posicionID`) REFERENCES `Posicion` (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Publicidad`
--
-- ORDER BY:  `publicidadID`

LOCK TABLES `Publicidad` WRITE;
/*!40000 ALTER TABLE `Publicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Publicidad` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_DetalleEstadia_Vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `Vehiculo` (`vehiculoID`),
  CONSTRAINT `FK_DetalleEstadia_Empleado` FOREIGN KEY (`empleadoID`) REFERENCES `Empleado` (`empleadoID`),
  CONSTRAINT `FK_DetalleEstadia_Estadia` FOREIGN KEY (`estadiaID`) REFERENCES `Estadia` (`estadiaID`),
  CONSTRAINT `FK_DetalleEstadia_Promocion` FOREIGN KEY (`promocionID`) REFERENCES `Promocion` (`promocionID`),
  CONSTRAINT `FK_DetalleEstadia_Tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `Tarifa` (`tarifaID`),
  CONSTRAINT `FK_DetalleEstadia_TransaccionCliente` FOREIGN KEY (`transaccionClienteID`) REFERENCES `TransaccionCliente` (`transaccionClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetalleEstadia`
--
-- ORDER BY:  `detalleEstadiaID`

LOCK TABLES `DetalleEstadia` WRITE;
/*!40000 ALTER TABLE `DetalleEstadia` DISABLE KEYS */;
/*!40000 ALTER TABLE `DetalleEstadia` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Sesion`
--
-- ORDER BY:  `sesionID`

LOCK TABLES `Sesion` WRITE;
/*!40000 ALTER TABLE `Sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sesion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Posicion`
--
-- ORDER BY:  `posicionID`

LOCK TABLES `Posicion` WRITE;
/*!40000 ALTER TABLE `Posicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Posicion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `EstadoPromocion`
--
-- ORDER BY:  `estadoPromocionID`

LOCK TABLES `EstadoPromocion` WRITE;
/*!40000 ALTER TABLE `EstadoPromocion` DISABLE KEYS */;
INSERT INTO `EstadoPromocion` (`descripcion`, `nombre`, `estadoPromocionID`) VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3),('Aprobada y publicandose','Vigente',4),('Período de publicación vencido','Vencida',5);
/*!40000 ALTER TABLE `EstadoPromocion` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `Comentario`
--
-- ORDER BY:  `comentarioID`

LOCK TABLES `Comentario` WRITE;
/*!40000 ALTER TABLE `Comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Comentario` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `FK_UsuarioSistema_Usuario` FOREIGN KEY (`usuarioSistemaID`) REFERENCES `Usuario` (`usuarioID`),
  CONSTRAINT `FK_UsuarioSistema_RolUsuario` FOREIGN KEY (`rolUsuarioID`) REFERENCES `RolUsuario` (`rolUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UsuarioSistema`
--
-- ORDER BY:  `usuarioSistemaID`

LOCK TABLES `UsuarioSistema` WRITE;
/*!40000 ALTER TABLE `UsuarioSistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `UsuarioSistema` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `RolUsuario`
--
-- ORDER BY:  `rolUsuarioID`

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

-- Dump completed on 2012-06-20  1:16:33
