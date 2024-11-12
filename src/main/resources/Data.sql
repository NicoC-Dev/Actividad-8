INSERT INTO cliente (id, celular, contacto, estado, fecha_inicio, fecha_nacimiento, letra, mail, razon_social)
VALUES 
    (1, '123456789', 'Juan Perez', 0, TIMESTAMP '2024-01-01 10:00:00', TIMESTAMP '1985-07-15 00:00:00', 'A', 'juan.perez@example.com', 'Perez SA'),
    (2, '987654321', 'Maria Lopez', 0, TIMESTAMP '2024-02-01 11:00:00', TIMESTAMP '1990-10-25 00:00:00', 'B', 'maria.lopez@example.com', 'Lopez Corp');

-- Insertar datos en la tabla tipo_servicio
INSERT INTO tipo_servicio (id, codigo, denominacion, detalle, estado)
VALUES 
    (1, 101, 'Servicio Básico', 'Incluye mantenimiento básico', 0),
    (2, 102, 'Servicio Premium', 'Incluye mantenimiento avanzado', 0);

-- Insertar datos en la tabla servicio
INSERT INTO servicio (id, estado, fecha_realizacion, fecha_registro, total, cliente_id)
VALUES 
    (1, 0, TIMESTAMP '2024-03-01 09:00:00', TIMESTAMP '2024-02-28 15:00:00', 500.00, 1),
    (2, 0, TIMESTAMP '2024-04-01 10:00:00', TIMESTAMP '2024-03-31 16:00:00', 750.00, 2);

-- Insertar datos en la tabla item_servicio
INSERT INTO item_servicio (id, estado, observacion, precio, servicio_id, tipo_servicio_id)
VALUES 
    (1, 0, 'Revisión general', 100.00, 1, 1),
    (2, 0, 'Mantenimiento avanzado', 400.00, 1, 2),
    (3, 0, 'Reemplazo de partes', 300.00, 2, 1),
    (4, 0, 'Diagnóstico completo', 450.00, 2, 2);

-- Insertar datos en la tabla linea
INSERT INTO linea (id, codigo, denominacion, detalle, estado, observacion)
VALUES
    (1, 201, 'Línea Básica', 'Línea de productos básicos', 0, 'Sin observaciones'),
    (2, 202, 'Línea Premium', 'Línea de productos de alta calidad', 0, 'Incluye garantías extendidas');

-- Insertar datos en la tabla articulo_vendido
INSERT INTO articulo_venta (id, denominacion, estado, observacion, linea_id)
VALUES
    (1, 'Producto A', 0, 'Artículo en buenas condiciones', 1),
    (2, 'Producto B', 0, 'Artículo nuevo', 1),
    (3, 'Producto C', 0, 'Artículo con pequeñas marcas de uso', 2),
    (4, 'Producto D', 0, 'Artículo en perfectas condiciones', 2);