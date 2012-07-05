/*
-- Description: Script de generación de la estructura y carga de datos básicos 
-- 		de la base de datos del proyecto tesis-playon.
-- Project: Playon
-- Author: Alejandro Bostico
-- Date: 04/07/2012
-- Version: 2.0
--
-- HISTORIAL DE CAMBIOS
-- Version 1.0 (18/06/2012) - Versión Inicial
-- Version 1.1 (20/06/2012) - Agregada la clase favorito
--		Agregada la foreign key transaccion_playa.detalleEstadiaID(FK) --> 
--		detalle_estadia.detalleEstadiaID(PK)
-- Versión 1.2 (04/07/2012) - Se cambiaron los nómbres de las tablas a minúsculas y con
--		guiones bajos para compatibilidad Windows - Linux
*/


/********************************************************
 * PRECAUCIÓN!!!					* 
 * Usar con cuidado que el script borra todas las 	*
 * tablas antes de crearlas.				*
 ********************************************************/

-- Descomentar la siguiente línea para borrar la base de datos
-- antes de crear las tablas en caso de que fuera necesario.
-- DROP DATABASE IF EXISTS `tesis_playon`;

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
-- Table structure for table `cuenta_playa`
--

DROP TABLE IF EXISTS `cuenta_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_playa` (
  `fechaCreacion` datetime DEFAULT NULL,
  `nroCuenta` int(11) DEFAULT NULL,
  `saldo` float DEFAULT NULL,
  `cuentaPlayaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_cuenta_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_playa`
--
-- ORDER BY:  `cuentaPlayaID`

LOCK TABLES `cuenta_playa` WRITE;
/*!40000 ALTER TABLE `cuenta_playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia_vehiculo`
--

DROP TABLE IF EXISTS `denuncia_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denuncia_vehiculo` (
  `asunto` text,
  `fechaAlta` datetime DEFAULT NULL,
  `vehiculoID` int(11) DEFAULT NULL,
  `denunciaVehiculoID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`denunciaVehiculoID`),
  KEY `vehiculoID` (`vehiculoID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_denuncia_vehiculo_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_denuncia_vehiculo_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia_vehiculo`
--
-- ORDER BY:  `denunciaVehiculoID`

LOCK TABLES `denuncia_vehiculo` WRITE;
/*!40000 ALTER TABLE `denuncia_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `denuncia_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincia` (
  `nombre` varchar(50) NOT NULL,
  `provinciaID` int(11) NOT NULL auto_increment,
  `paisID` int(11) NOT NULL,
  PRIMARY KEY (`provinciaID`),
  KEY `paisID` (`paisID`),
  CONSTRAINT `FK_provincia_pais` FOREIGN KEY (`paisID`) REFERENCES `pais` (`paisID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--
-- ORDER BY:  `provinciaID`

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_publicidad`
--

DROP TABLE IF EXISTS `estado_publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_publicidad` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `estadoPublicidadID` int(11) NOT NULL,
  PRIMARY KEY (`estadoPublicidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_publicidad`
--
-- ORDER BY:  `estadoPublicidadID`

LOCK TABLES `estado_publicidad` WRITE;
/*!40000 ALTER TABLE `estado_publicidad` DISABLE KEYS */;
INSERT INTO `estado_publicidad` (`descripcion`, `nombre`, `estadoPublicidadID`) VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3),('Aprobada y publicandose','Vigente',4),('Período de publicación vencido','Vencida',5);
/*!40000 ALTER TABLE `estado_publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
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
  CONSTRAINT `FK_promocion_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_promocion_estado_promocion` FOREIGN KEY (`estadoPromocionID`) REFERENCES `estado_promocion` (`estadoPromocionID`),
  CONSTRAINT `FK_promocion_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--
-- ORDER BY:  `promocionID`

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color_vehiculo`
--

DROP TABLE IF EXISTS `color_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `color_vehiculo` (
  `nombre` varchar(50) NOT NULL,
  `colorVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`colorVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color_vehiculo`
--
-- ORDER BY:  `colorVehiculoID`

LOCK TABLES `color_vehiculo` WRITE;
/*!40000 ALTER TABLE `color_vehiculo` DISABLE KEYS */;
INSERT INTO `color_vehiculo` (`nombre`, `colorVehiculoID`) VALUES ('Blanco',1),('Negro',2),('Rojo',3),('Amarillo',4),('Verde Claro',5),('Azul',6),('Celeste',7),('Gris Claro',8),('Gris Oscuro',9),('Naranja',10),('Verde Oscuro',11),('Bordo',12);
/*!40000 ALTER TABLE `color_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_cliente`
--

DROP TABLE IF EXISTS `cuenta_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_cliente` (
  `fechaCreacion` datetime DEFAULT NULL,
  `nroCuenta` int(11) DEFAULT NULL,
  `saldo` float DEFAULT NULL,
  `cuentaClienteID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`cuentaClienteID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_cuenta_cliente_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_cliente`
--
-- ORDER BY:  `cuentaClienteID`

LOCK TABLES `cuenta_cliente` WRITE;
/*!40000 ALTER TABLE `cuenta_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_denuncia`
--

DROP TABLE IF EXISTS `estado_denuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_denuncia` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `estadoDenunciaID` int(11) NOT NULL,
  PRIMARY KEY (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_denuncia`
--
-- ORDER BY:  `estadoDenunciaID`

LOCK TABLES `estado_denuncia` WRITE;
/*!40000 ALTER TABLE `estado_denuncia` DISABLE KEYS */;
INSERT INTO `estado_denuncia` (`descripcion`, `nombre`, `estadoDenunciaID`) VALUES ('Pendiente de auditoría','Pendiente',1),('En proceso de investigación','En Proceso',2),('Acepatada','Aceptada',3),('Rechazada','Rechazada',4),('Anulada','Anulada',5),('Dada de Baja / Cancelada','De Baja',6);
/*!40000 ALTER TABLE `estado_denuncia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `denuncia_playa`
--

DROP TABLE IF EXISTS `denuncia_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `denuncia_playa` (
  `asunto` text,
  `fechaAlta` datetime DEFAULT NULL,
  `playaID` int(11) DEFAULT NULL,
  `denunciaPlayaID` int(11) NOT NULL,
  `clienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`denunciaPlayaID`),
  KEY `playaID` (`playaID`),
  KEY `clienteID` (`clienteID`),
  CONSTRAINT `FK_denuncia_playa_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_denuncia_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia_playa`
--
-- ORDER BY:  `denunciaPlayaID`

LOCK TABLES `denuncia_playa` WRITE;
/*!40000 ALTER TABLE `denuncia_playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `denuncia_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playa`
--

DROP TABLE IF EXISTS `playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playa` (
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
  CONSTRAINT `FK_playa_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`),
  CONSTRAINT `FK_playa_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`),
  CONSTRAINT `FK_playa_estado_playa` FOREIGN KEY (`estadoPlayaID`) REFERENCES `estado_playa` (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia representa los datos administrativos de una playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playa`
--
-- ORDER BY:  `playaID`

LOCK TABLES `playa` WRITE;
/*!40000 ALTER TABLE `playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liquidacion`
--

DROP TABLE IF EXISTS `liquidacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liquidacion` (
  `fecha` datetime DEFAULT NULL,
  `fechaHasta` datetime DEFAULT NULL,
  `ferchaDesde` datetime DEFAULT NULL,
  `importeTotal` float DEFAULT NULL,
  `liquidacionID` int(11) NOT NULL,
  `estadiaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`liquidacionID`),
  KEY `estadiaID` (`estadiaID`),
  CONSTRAINT `FK_liquidacion_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liquidacion`
--
-- ORDER BY:  `liquidacionID`

LOCK TABLES `liquidacion` WRITE;
/*!40000 ALTER TABLE `liquidacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `liquidacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_vehiculo`
--

DROP TABLE IF EXISTS `categoria_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_vehiculo` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `categoriaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Son los típos de vehículos. Moto, Auto, Utilitario, PickUp, etc.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_vehiculo`
--
-- ORDER BY:  `categoriaVehiculoID`

LOCK TABLES `categoria_vehiculo` WRITE;
/*!40000 ALTER TABLE `categoria_vehiculo` DISABLE KEYS */;
INSERT INTO `categoria_vehiculo` (`descripcion`, `nombre`, `categoriaVehiculoID`) VALUES ('','Auto',1),('','Moto',2),('','Utilitario',3),('','PickUp / 4X4',4);
/*!40000 ALTER TABLE `categoria_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_playa`
--

DROP TABLE IF EXISTS `estado_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_playa` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPlayaID` int(11) NOT NULL,
  PRIMARY KEY (`estadoPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_playa`
--
-- ORDER BY:  `estadoPlayaID`

LOCK TABLES `estado_playa` WRITE;
/*!40000 ALTER TABLE `estado_playa` DISABLE KEYS */;
INSERT INTO `estado_playa` (`descripcion`, `nombre`, `estadoPlayaID`) VALUES ('Pendiente de Auditoría','Pendiente',1),('Aprobada luego de auditoría','Aprobada',2),('Rechazada luego de auditoría','Rechazada',3),('Dada de baja','De Baja',4);
/*!40000 ALTER TABLE `estado_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccion_cliente`
--

DROP TABLE IF EXISTS `transaccion_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccion_cliente` (
  `fecha` datetime DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `tipoPagoID` int(11) DEFAULT NULL,
  `transaccionClienteID` int(11) NOT NULL,
  `cuentaClienteID` int(11) NOT NULL,
  PRIMARY KEY (`transaccionClienteID`),
  KEY `cuentaClienteID` (`cuentaClienteID`),
  KEY `tipoPagoID` (`tipoPagoID`),
  CONSTRAINT `FK_transaccion_cliente_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`),
  CONSTRAINT `FK_transaccion_cliente_cuenta_cliente` FOREIGN KEY (`cuentaClienteID`) REFERENCES `cuenta_cliente` (`cuentaClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion_cliente`
--
-- ORDER BY:  `transaccionClienteID`

LOCK TABLES `transaccion_cliente` WRITE;
/*!40000 ALTER TABLE `transaccion_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaccion_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_estadia`
--

DROP TABLE IF EXISTS `tipo_estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_estadia` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `tipoEstadiaID` int(11) NOT NULL,
  PRIMARY KEY (`tipoEstadiaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='TIPOS DE ESTADIA:   * Por hora   * Por dia   * Por noche   * Por Mes   * Por Semana   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_estadia`
--
-- ORDER BY:  `tipoEstadiaID`

LOCK TABLES `tipo_estadia` WRITE;
/*!40000 ALTER TABLE `tipo_estadia` DISABLE KEYS */;
INSERT INTO `tipo_estadia` (`descripcion`, `nombre`, `tipoEstadiaID`) VALUES ('','Por Hora',1),('','Por Mes',2),('','Por Noche',3),('','Por Día',4),('','Por Semana',5);
/*!40000 ALTER TABLE `tipo_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_de_cambio`
--

DROP TABLE IF EXISTS `historial_de_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historial_de_cambio` (
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
  CONSTRAINT `FK_historial_de_cambio_denuncia_vehiculo` FOREIGN KEY (`denunciaVehiculoID`) REFERENCES `denuncia_vehiculo` (`denunciaVehiculoID`),
  CONSTRAINT `FK_historial_de_cambio_denuncia_playa` FOREIGN KEY (`denunciaPlayaID`) REFERENCES `denuncia_playa` (`denunciaPlayaID`),
  CONSTRAINT `FK_historial_de_cambio_estado_denuncia` FOREIGN KEY (`estadoDenunciaID`) REFERENCES `estado_denuncia` (`estadoDenunciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_de_cambio`
--
-- ORDER BY:  `historialDeCambioID`

LOCK TABLES `historial_de_cambio` WRITE;
/*!40000 ALTER TABLE `historial_de_cambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `historial_de_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto`
--

DROP TABLE IF EXISTS `foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foto` (
  `descripcion` text,
  `link` varchar(50) NOT NULL,
  `fotoID` int(11) NOT NULL,
  `perfilPlayaID` int(11) NOT NULL,
  PRIMARY KEY (`fotoID`),
  KEY `perfilPlayaID` (`perfilPlayaID`),
  CONSTRAINT `FK_foto_perfil_playa` FOREIGN KEY (`perfilPlayaID`) REFERENCES `perfil_playa` (`perfilPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto`
--
-- ORDER BY:  `fotoID`

LOCK TABLES `foto` WRITE;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiculo` (
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
  CONSTRAINT `FK_vehiculo_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_vehiculo_categoria_vehiculo` FOREIGN KEY (`categoriaID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`),
  CONSTRAINT `FK_vehiculo_modelo_vehiculo` FOREIGN KEY (`modeloVehiculoID`) REFERENCES `modelo_vehiculo` (`modeloVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--
-- ORDER BY:  `vehiculoID`

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `cargoEmpleadoID` int(11) DEFAULT NULL,
  `legajo` int(11) DEFAULT NULL,
  `empleadoID` int(11) NOT NULL,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`empleadoID`),
  KEY `cargoEmpleadoID` (`cargoEmpleadoID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_empleado_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_empleado_cargo_empleado` FOREIGN KEY (`cargoEmpleadoID`) REFERENCES `cargo_empleado` (`cargoEmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--
-- ORDER BY:  `empleadoID`

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo_empleado`
--

DROP TABLE IF EXISTS `cargo_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cargo_empleado` (
  `descripcion` text,
  `nombre` varchar(50) DEFAULT NULL,
  `cargoEmpleadoID` int(11) NOT NULL,
  PRIMARY KEY (`cargoEmpleadoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_empleado`
--
-- ORDER BY:  `cargoEmpleadoID`

LOCK TABLES `cargo_empleado` WRITE;
/*!40000 ALTER TABLE `cargo_empleado` DISABLE KEYS */;
INSERT INTO `cargo_empleado` (`descripcion`, `nombre`, `cargoEmpleadoID`) VALUES ('','Gerente General',1),('','Encargado',2),('','Playero',3);
/*!40000 ALTER TABLE `cargo_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil_playa`
--

DROP TABLE IF EXISTS `perfil_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfil_playa` (
  `descripcion` text,
  `nombreComercial` varchar(50) NOT NULL,
  `perfilPlayaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`perfilPlayaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_perfil_playa_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Esta clase va a contener todos los datos del perfil de la playa que se muestra en el sitio: fotos, nombre para mostrar, descripción, etc.    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil_playa`
--
-- ORDER BY:  `perfilPlayaID`

LOCK TABLES `perfil_playa` WRITE;
/*!40000 ALTER TABLE `perfil_playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `perfil_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abono`
--

DROP TABLE IF EXISTS `abono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abono` (
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
  CONSTRAINT `FK_abono_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_abono_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_abono_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abono`
--
-- ORDER BY:  `abonoID`

LOCK TABLES `abono` WRITE;
/*!40000 ALTER TABLE `abono` DISABLE KEYS */;
/*!40000 ALTER TABLE `abono` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `nombre` varchar(50) NOT NULL,
  `paisID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`paisID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--
-- ORDER BY:  `paisID`

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarifa`
--

DROP TABLE IF EXISTS `tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarifa` (
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
  CONSTRAINT `FK_tarifa_tipo_estadia` FOREIGN KEY (`tipoEstadiaID`) REFERENCES `tipo_estadia` (`tipoEstadiaID`),
  CONSTRAINT `FK_tarifa_categoria_vehiculo` FOREIGN KEY (`categoriaVehiculoID`) REFERENCES `categoria_vehiculo` (`categoriaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia contiene un precio de la tarifa que depende del Categoría de vehículo, tipo de estadía (mensual, por hora, etc).      CategoríaVehiculo: utilitario   TipoEstadía: Mensual   Precio/tarifa: $720      CategoríaVehiculo: utilitario   TipoEstadía: Por hora   Precio/tarifa: $14      CategoríaVehiculo: auto   TipoEstadía: Por hora   Precio/tarifa: $12   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarifa`
--
-- ORDER BY:  `tarifaID`

LOCK TABLES `tarifa` WRITE;
/*!40000 ALTER TABLE `tarifa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barrio`
--

DROP TABLE IF EXISTS `barrio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barrio` (
  `nombre` varchar(50) NOT NULL,
  `barrioID` int(11) NOT NULL auto_increment,
  `localidadID` int(11) NOT NULL,
  PRIMARY KEY (`barrioID`),
  KEY `localidadID` (`localidadID`),
  CONSTRAINT `FK_barrio_localidad` FOREIGN KEY (`localidadID`) REFERENCES `localidad` (`localidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barrio`
--
-- ORDER BY:  `barrioID`

LOCK TABLES `barrio` WRITE;
/*!40000 ALTER TABLE `barrio` DISABLE KEYS */;
/*!40000 ALTER TABLE `barrio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorito`
--

DROP TABLE IF EXISTS `favorito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorito` (
  `clienteID` int(11) NOT NULL,
  `playaID` int(11) NOT NULL,
  PRIMARY KEY (`clienteID`,`playaID`),
  KEY `clienteID` (`clienteID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_favorito_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_favorito_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorito`
--
-- ORDER BY:  `clienteID`,`playaID`

LOCK TABLES `favorito` WRITE;
/*!40000 ALTER TABLE `favorito` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
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
  CONSTRAINT `FK_usuario_tipo_doc` FOREIGN KEY (`tipoDocID`) REFERENCES `tipo_doc` (`tipoDocID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--
-- ORDER BY:  `usuarioID`

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_pago`
--

DROP TABLE IF EXISTS `tipo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_pago` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `tipoPagoID` int(11) NOT NULL,
  PRIMARY KEY (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_pago`
--
-- ORDER BY:  `tipoPagoID`

LOCK TABLES `tipo_pago` WRITE;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` (`descripcion`, `nombre`, `tipoPagoID`) VALUES ('Contado Efectivo','Contado',1),('Tarjeta de débito','Tarjeta Débito',2),('Tarjeta de crédito','Tarjeta Crédito',3),('DineroMail','DineroMail',4),('Cheque','Cheque',5),('Pago con saldo de la cuenta del cliente','Cuenta',6);
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaccion_playa`
--

DROP TABLE IF EXISTS `transaccion_playa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaccion_playa` (
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
  CONSTRAINT `FK_transaccion_playa_liquidacion` FOREIGN KEY (`liquidacionID`) REFERENCES `liquidacion` (`liquidacionID`),
  CONSTRAINT `FK_transaccion_playa_cuenta_playa` FOREIGN KEY (`cuentaPlayaID`) REFERENCES `cuenta_playa` (`cuentaPlayaID`),
  CONSTRAINT `FK_transaccion_playa_detalle_estadia` FOREIGN KEY (`detalleEstadiaID`) REFERENCES `detalle_estadia` (`detalleEstadiaID`),
  CONSTRAINT `FK_transaccion_playa_tipo_pago` FOREIGN KEY (`tipoPagoID`) REFERENCES `tipo_pago` (`tipoPagoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaccion_playa`
--
-- ORDER BY:  `transaccionPlayaID`

LOCK TABLES `transaccion_playa` WRITE;
/*!40000 ALTER TABLE `transaccion_playa` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaccion_playa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca_vehiculo`
--

DROP TABLE IF EXISTS `marca_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marca_vehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `marcaVehiculoID` int(11) NOT NULL,
  PRIMARY KEY (`marcaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca_vehiculo`
--
-- ORDER BY:  `marcaVehiculoID`

LOCK TABLES `marca_vehiculo` WRITE;
/*!40000 ALTER TABLE `marca_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadia`
--

DROP TABLE IF EXISTS `estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadia` (
  `estadiaID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`estadiaID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_estadia_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Cada instancia de esta clase representa un conjunto de ingreso y egreso de los autos en cada playa.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadia`
--
-- ORDER BY:  `estadiaID`

LOCK TABLES `estadia` WRITE;
/*!40000 ALTER TABLE `estadia` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelo_vehiculo`
--

DROP TABLE IF EXISTS `modelo_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modelo_vehiculo` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `modeloVehiculoID` int(11) NOT NULL,
  `marcaVehiculoID` int(11) DEFAULT NULL,
  PRIMARY KEY (`modeloVehiculoID`),
  KEY `marcaVehiculoID` (`marcaVehiculoID`),
  CONSTRAINT `FK_modelo_vehiculo_marca_vehiculo` FOREIGN KEY (`marcaVehiculoID`) REFERENCES `marca_vehiculo` (`marcaVehiculoID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelo_vehiculo`
--
-- ORDER BY:  `modeloVehiculoID`

LOCK TABLES `modelo_vehiculo` WRITE;
/*!40000 ALTER TABLE `modelo_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `modelo_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `nombre` varchar(50) NOT NULL,
  `localidadID` int(11) NOT NULL auto_increment,
  `provinciaID` int(11) NOT NULL,
  PRIMARY KEY (`localidadID`),
  KEY `provinciaID` (`provinciaID`),
  CONSTRAINT `FK_localidad_provincia` FOREIGN KEY (`provinciaID`) REFERENCES `provincia` (`provinciaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--
-- ORDER BY:  `localidadID`

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
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
  CONSTRAINT `FK_cliente_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_cliente_barrio` FOREIGN KEY (`barrioID`) REFERENCES `barrio` (`barrioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--
-- ORDER BY:  `clienteID`

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_doc`
--

DROP TABLE IF EXISTS `tipo_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_doc` (
  `nombre` varchar(50) NOT NULL,
  `tipoDocID` int(11) NOT NULL,
  PRIMARY KEY (`tipoDocID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_doc`
--
-- ORDER BY:  `tipoDocID`

LOCK TABLES `tipo_doc` WRITE;
/*!40000 ALTER TABLE `tipo_doc` DISABLE KEYS */;
INSERT INTO `tipo_doc` (`nombre`, `tipoDocID`) VALUES ('D.N.I.',1),('L.C.',2),('L.E.',3),('C.I.',4);
/*!40000 ALTER TABLE `tipo_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicidad`
--

DROP TABLE IF EXISTS `publicidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publicidad` (
  `estadoPublicidadID` int(11) NOT NULL,
  `posicionID` int(11) NOT NULL,
  `urlImagen` varchar(50) DEFAULT NULL,
  `publicidadID` int(11) NOT NULL,
  `playaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`publicidadID`),
  KEY `estadoPublicidadID` (`estadoPublicidadID`),
  KEY `posicionID` (`posicionID`),
  KEY `playaID` (`playaID`),
  CONSTRAINT `FK_publicidad_playa` FOREIGN KEY (`playaID`) REFERENCES `playa` (`playaID`),
  CONSTRAINT `FK_publicidad_estado_publicidad` FOREIGN KEY (`estadoPublicidadID`) REFERENCES `estado_publicidad` (`estadoPublicidadID`),
  CONSTRAINT `FK_publicidad_posicion` FOREIGN KEY (`posicionID`) REFERENCES `posicion` (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicidad`
--
-- ORDER BY:  `publicidadID`

LOCK TABLES `publicidad` WRITE;
/*!40000 ALTER TABLE `publicidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_estadia`
--

DROP TABLE IF EXISTS `detalle_estadia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_estadia` (
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
  CONSTRAINT `FK_detalle_estadia_vehiculo` FOREIGN KEY (`vehiculoID`) REFERENCES `vehiculo` (`vehiculoID`),
  CONSTRAINT `FK_detalle_estadia_empleado` FOREIGN KEY (`empleadoID`) REFERENCES `empleado` (`empleadoID`),
  CONSTRAINT `FK_detalle_estadia_estadia` FOREIGN KEY (`estadiaID`) REFERENCES `estadia` (`estadiaID`),
  CONSTRAINT `FK_detalle_estadia_promocion` FOREIGN KEY (`promocionID`) REFERENCES `promocion` (`promocionID`),
  CONSTRAINT `FK_detalle_estadia_tarifa` FOREIGN KEY (`tarifaID`) REFERENCES `tarifa` (`tarifaID`),
  CONSTRAINT `FK_detalle_estadia_transaccion_cliente` FOREIGN KEY (`transaccionClienteID`) REFERENCES `transaccion_cliente` (`transaccionClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_estadia`
--
-- ORDER BY:  `detalleEstadiaID`

LOCK TABLES `detalle_estadia` WRITE;
/*!40000 ALTER TABLE `detalle_estadia` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_estadia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesion`
--

DROP TABLE IF EXISTS `sesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sesion` (
  `fechaFin` datetime DEFAULT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `idSesion` varchar(50) NOT NULL,
  `sesionID` int(11) NOT NULL,
  `usuarioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`sesionID`),
  KEY `usuarioID` (`usuarioID`),
  CONSTRAINT `FK_sesion_usuario` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`usuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesion`
--
-- ORDER BY:  `sesionID`

LOCK TABLES `sesion` WRITE;
/*!40000 ALTER TABLE `sesion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sesion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posicion`
--

DROP TABLE IF EXISTS `posicion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posicion` (
  `tamañoKBMax` int(11) DEFAULT NULL,
  `tamañoX` int(11) DEFAULT NULL,
  `tamañoY` int(11) DEFAULT NULL,
  `ubicacion` text,
  `posicionID` int(11) NOT NULL,
  PRIMARY KEY (`posicionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Clase que contiene los datos sobre las ubicaciones y posiciones de las publicidades. (layout de publicidades)   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posicion`
--
-- ORDER BY:  `posicionID`

LOCK TABLES `posicion` WRITE;
/*!40000 ALTER TABLE `posicion` DISABLE KEYS */;
/*!40000 ALTER TABLE `posicion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_promocion`
--

DROP TABLE IF EXISTS `estado_promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estado_promocion` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `estadoPromocionID` int(11) NOT NULL,
  PRIMARY KEY (`estadoPromocionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='ESTADOS:   *Vigente   *Vencida   *Cancelada   *Pendiente   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_promocion`
--
-- ORDER BY:  `estadoPromocionID`

LOCK TABLES `estado_promocion` WRITE;
/*!40000 ALTER TABLE `estado_promocion` DISABLE KEYS */;
INSERT INTO `estado_promocion` (`descripcion`, `nombre`, `estadoPromocionID`) VALUES ('Pendiente de Aprobación','Pendiente',1),('Aprobada y pendiente de publicación','Aprobada',2),('No Aprobada','Rechazada',3),('Aprobada y publicandose','Vigente',4),('Período de publicación vencido','Vencida',5);
/*!40000 ALTER TABLE `estado_promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentario` (
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
  CONSTRAINT `FK_comentario_cliente` FOREIGN KEY (`clienteID`) REFERENCES `cliente` (`clienteID`),
  CONSTRAINT `FK_comentario_perfil_playa` FOREIGN KEY (`playaID`) REFERENCES `perfil_playa` (`perfilPlayaID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--
-- ORDER BY:  `comentarioID`

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_sistema`
--

DROP TABLE IF EXISTS `usuario_sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_sistema` (
  `rolUsuarioID` int(11) NOT NULL,
  `usuarioSistemaID` int(11) NOT NULL,
  PRIMARY KEY (`usuarioSistemaID`),
  KEY `rolUsuarioID` (`rolUsuarioID`),
  KEY `usuarioSistemaID` (`usuarioSistemaID`),
  CONSTRAINT `FK_usuario_sistema_usuario` FOREIGN KEY (`usuarioSistemaID`) REFERENCES `usuario` (`usuarioID`),
  CONSTRAINT `FK_usuario_sistema_rol_usuario` FOREIGN KEY (`rolUsuarioID`) REFERENCES `rol_usuario` (`rolUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_sistema`
--
-- ORDER BY:  `usuarioSistemaID`

LOCK TABLES `usuario_sistema` WRITE;
/*!40000 ALTER TABLE `usuario_sistema` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_sistema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_usuario` (
  `descripcion` text,
  `nombre` varchar(50) NOT NULL,
  `rolUsuarioID` int(11) NOT NULL auto_increment,
  PRIMARY KEY (`rolUsuarioID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Los roles van a ser: Administrador, Cliente, Dueño, Playero, Gerente, etc. y de estos van a depender los permisos que tenga cada uno dentro del sistema.   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_usuario`
--
-- ORDER BY:  `rolUsuarioID`

LOCK TABLES `rol_usuario` WRITE;
/*!40000 ALTER TABLE `rol_usuario` DISABLE KEYS */;
INSERT INTO `rol_usuario` (`descripcion`, `nombre`, `rolUsuarioID`) VALUES ('Administrador del sistema','Administrador',1),('Auditor del sistema','Auditor',2),('usuario del Area administrativa/contable','Administración',3),('Usuario sin permisos especiales','usuario',4);
/*!40000 ALTER TABLE `rol_usuario` ENABLE KEYS */;
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
