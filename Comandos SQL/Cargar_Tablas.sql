-- Creación de la tabla Cliente
CREATE TABLE Cliente (
    id_cliente SERIAL,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    nro_documento VARCHAR(8),
    tipo_documento VARCHAR(20),
    nacionalidad VARCHAR(20),
    email VARCHAR(30),
    telefono VARCHAR(14),
    fecha_nacimiento DATE,
    CONSTRAINT clientes_pk PRIMARY KEY (id_cliente)
);

create sequence public.cliente_sec;

-- Creación de la tabla Conceptos de Uso de Puntos
CREATE TABLE conceptos_uso_puntos (
    id_concept_pts SERIAL,
    descripcion_concepto VARCHAR(100),
    puntos_requeridos INTEGER,
    CONSTRAINT conceptos_uso_puntos_pk PRIMARY KEY (id_concept_pts)
);

create sequence public.conceptos_uso_puntos_sec;

-- Creación de la tabla Reglas de Asignación de Puntos
CREATE TABLE reglas_asignacion_puntos (
    id_reglas_pts SERIAL,
    limite_inferior INTEGER,
    limite_superior INTEGER,
    monto_equivalencia INTEGER,
    CONSTRAINT reglas_asignacion_puntos_pk PRIMARY KEY (id_reglas_pts)
);

create sequence reglas_asignacion_puntos_sec;

-- Creación de la tabla Vencimientos de Puntos
CREATE TABLE vencimientos_puntos (
    id_venc_pts SERIAL,
    fecha_inicio_validez DATE,
    fecha_fin_validez DATE,
    dias_validez_puntos INTEGER,
    CONSTRAINT vencimientos_puntos_pk PRIMARY KEY (id_venc_pts)
);

create sequence vencimientos_puntos_sec;

-- Creación de la tabla Bolsa de Puntos
CREATE TABLE bolsa_puntos (
    id_bolsa_pts SERIAL,
    id_cliente INTEGER,
    fecha_asignacion_puntaje DATE,
    fecha_caducidad_puntaje DATE,
    puntaje_asignado INTEGER,
    puntaje_utilizado INTEGER,
    saldo_puntos INTEGER,
    monto_operacion INTEGER,
    CONSTRAINT bolsa_puntos_pk PRIMARY KEY (id_bolsa_pts),
    constraint id_cliente_fk foreign key (id_cliente) references cliente (id_cliente)
);

create sequence bolsa_puntos_sec;

-- Creación de la tabla Uso de Puntos - Cabecera
CREATE TABLE uso_puntos_cabecera (
    id_uso_cabecera SERIAL,
    id_cliente INTEGER,
    puntaje_utilizado INTEGER,
    fecha DATE,
    concepto_uso_punto INTEGER,
    CONSTRAINT uso_puntos_cabecera_pk PRIMARY KEY (id_uso_cabecera),
    constraint id_cliente_fk foreign key (id_cliente) references cliente (id_cliente),
    constraint concepto_uso_punto_fk foreign key (concepto_uso_punto) references conceptos_uso_puntos (id_concept_pts)
);

create sequence uso_puntos_cabecera_sec;

-- Creación de la tabla Uso de Puntos - Detalle
CREATE TABLE uso_puntos_detalle (
    id_pts_detalle SERIAL,
    id_cabecera INTEGER,
    puntaje_utilizado INTEGER,
    id_bolsa_puntos_utilizada INTEGER,
    CONSTRAINT uso_puntos_detalle_pk PRIMARY KEY (id_pts_detalle),
    constraint bolsa_puntos_fk foreign key (id_bolsa_puntos_utilizada) references bolsa_puntos (id_bolsa_pts),
    constraint uso_puntos_cabecera_fk foreign key (id_cabecera) references uso_puntos_cabecera (id_uso_cabecera)
);

create sequence uso_puntos_detalle_sec;

