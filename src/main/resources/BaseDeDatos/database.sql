--Archivo SQL para la creación de la Base de Datos
CREATE DATABASE report_db;
USE report_db;

-- Tabla de Clientes
CREATE TABLE clientes (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(50) NOT NULL,
        apellido VARCHAR(50) NOT NULL,
        email VARCHAR(100),
        telefono VARCHAR(15));

-- Tabla de Pedidos
CREATE TABLE pedidos (
        id INT AUTO_INCREMENT PRIMARY KEY,
        cliente_id INT,
        fecha DATE,
        total DECIMAL(10, 2),
        estado VARCHAR(20),
        FOREIGN KEY (cliente_id) REFERENCES clientes(id));

-- Insertar Datos de Ejemplo
INSERT INTO clientes (nombre, apellido, email, telefono) VALUES
                                                             ('Juan', 'Pérez', 'juan.perez@example.com', '123456789'),
                                                             ('Ana', 'Gómez', 'ana.gomez@example.com', '987654321'),
                                                             ('Luis', 'Martínez', 'luis.martinez@example.com', '456123789');

INSERT INTO pedidos (cliente_id, fecha, total, estado) VALUES
                                                           (1, '2025-01-01', 150.00, 'Completado'),
                                                           (2, '2025-01-05', 200.00, 'Pendiente'),
                                                           (3, '2025-01-10', 300.00, 'Cancelado');