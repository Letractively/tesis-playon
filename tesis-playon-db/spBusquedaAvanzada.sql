DELIMITER $$

DROP PROCEDURE IF EXISTS `tesis_playon`.`busquedaAvanzada` $$
CREATE PROCEDURE `tesis_playon`.`busquedaAvanzada` (
IN platitud DOUBLE,
IN plongitud DOUBLE,
IN pTipoEstadia INT,
IN pCategoriaVehiculo INT,
IN pNombreComercial MEDIUMTEXT,
IN pPromociones TINYINT
)
BEGIN
    SELECT p.barrioID, p.cuit, p.disponibilidad, p.domicilio, p.telefono, p.email,
        p.estadoPlayaID, p.nombreComercial, p.latitud, p.longitud, p.razonSocial,
        p.playaID
    FROM playa p
    WHERE ((p.playaID IN (SELECT DISTINCT t.playaID
                            FROM tarifa t
                            WHERE (pTipoEstadia = 0 OR t.tipoEstadiaID = pTipoEstadia)
                                AND (pCategoriaVehiculo = 0 OR t.categoriaVehiculoID = pCategoriaVehiculo)))
            AND( pPromociones = 0 OR (p.playaID IN (SELECT DISTINCT promo.playaID
                                                        FROM promocion promo
                                                        WHERE pPromociones = 1
                                                                AND promo.estadoPromocionID=2
                                                                AND (promo.fechaInicio >= now())
                                                                AND (promo.fechaFin <= now()))))
            AND (p.estadoPlayaID = 2)
            AND (p.disponibilidad > 0));
END $$

DELIMITER ;