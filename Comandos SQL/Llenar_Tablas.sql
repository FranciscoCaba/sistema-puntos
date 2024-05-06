INSERT INTO public.cliente (nombre,apellido,nro_documento,tipo_documento,nacionalidad,email,telefono,fecha_nacimiento) VALUES
	 ('Osmani','Mestre','5072500','Cedula','Paraguaya','manimestre018@gmail.com','0982253227','11-12-2000'),
	 ('Derlis','Caceres','4879101','Cedula','Paraguaya','derliscaceres@gmail.com','0991334903','6-03-1995'),
	 ('Francisco','Cabanas','5194589','Cedula','Paraguaya','francorv2@gmail.com','0981455237','5-01-2001'),
	 ('Maria','Perez','5913004','Cedula','Paraguaya','cicloncita98@gmail.com','0971338493','10-04-2001');
	 
select * from cliente c


INSERT INTO public.conceptos_uso_puntos (descripcion_concepto,puntos_requeridos) VALUES
	 ('vale de premio','15'),
	 ('vale de consumicion','10'),
	 ('vale de descuento','3');
	
select * from conceptos_uso_puntos cup 

INSERT INTO public.reglas_asignacion_puntos  (limite_inferior ,limite_superior,monto_equivalencia) VALUES
	 (0,199999,50000),
	 (200000,499999,30000),
	 (500000,1000000000,20000);
	 
select * from reglas_asignacion_puntos rap 




INSERT INTO public.vencimientos_puntos  (fecha_inicio_validez ,fecha_fin_validez ,dias_validez_puntos) VALUES
	 ('1-01-2024','6-1-2024',90),
	 ('6-2-2024','10-1-2024',60),
	 ('10-2-2024','12-28-2024',150);
	
select * from vencimientos_puntos vp 

-- Si su Postgres esta en ingles utilice el insert ya definido si no lo esta utilice el siguiente codigo en cambio

/* INSERT INTO public.vencimientos_puntos  (fecha_inicio_validez ,fecha_fin_validez ,dias_validez_puntos) VALUES
     ('1-1-2024','1-6-2024',90),
     ('2-6-2024','1-10-2024',60),
     ('2-10-2024','28-12-2024',150);
    
select * from vencimientos_puntos vp */
