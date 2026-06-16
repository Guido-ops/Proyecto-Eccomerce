-- Script de inicialización: crea los usuarios/schemas para cada microservicio
-- Se ejecuta automáticamente la primera vez que arranca el contenedor Oracle XE

ALTER SESSION SET "_ORACLE_SCRIPT"=TRUE;

-- Cambiar al PDB XEPDB1 (donde se conectan los microservicios)
ALTER SESSION SET CONTAINER = XEPDB1;

-- ── ms-usuarios ──────────────────────────────────────────────────────────────
CREATE USER MS_USUARIOS IDENTIFIED BY Pass1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO MS_USUARIOS;

-- ── ms-productos ─────────────────────────────────────────────────────────────
CREATE USER MS_PRODUCTOS IDENTIFIED BY Pass1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO MS_PRODUCTOS;

-- ── ms-pedidos ───────────────────────────────────────────────────────────────
CREATE USER MS_PEDIDOS IDENTIFIED BY Pass1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO MS_PEDIDOS;

-- ── ms-pagos ─────────────────────────────────────────────────────────────────
CREATE USER MS_PAGOS IDENTIFIED BY Pass1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO MS_PAGOS;

-- ── ms-inventario ────────────────────────────────────────────────────────────
CREATE USER MS_INVENTARIO IDENTIFIED BY Pass1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;
GRANT CONNECT, RESOURCE TO MS_INVENTARIO;

COMMIT;
