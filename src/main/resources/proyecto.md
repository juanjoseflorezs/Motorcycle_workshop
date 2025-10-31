Enunciado del Proyecto: Sistema de Gestión de un Taller de Motos de Carreras



El proyecto consiste en desarrollar una API REST en Java (versión superior a Java 23) utilizando Spring Boot y Lombok, para administrar el funcionamiento de un taller especializado en la reparación y mantenimiento de motos de carreras.



El sistema deberá permitir realizar operaciones CRUD sobre las distintas entidades del taller, almacenando la información en un archivo separado por comas (.csv) que simule una base de datos.



La aplicación deberá reflejar de forma clara los principios de la Programación Orientada a Objetos (POO):



Encapsulamiento: Control de acceso y modificación de atributos mediante métodos de acceso.
Herencia: Jerarquía entre tipos de motos (por ejemplo, MotoCarrera, MotoCross, MotoSuperbike heredando de una clase abstracta Moto).
Polimorfismo: Comportamientos diferenciados en métodos compartidos (como calcularCostoReparacion() o realizarMantenimiento()).
Interfaces: Definición de contratos para operaciones comunes como diagnóstico, reparación o facturación.
Composición y Agregación: Relaciones entre clases como OrdenReparacion que contiene una lista de ServicioRealizado, o Mecanico asociado a varias OrdenReparacion.




El sistema deberá incluir al menos 10 clases, entre las cuales pueden estar:



Moto (abstracta)
MotoCarrera, MotoCross, MotoSuperbike (herencia)
Cliente, Mecanico, OrdenReparacion, ServicioRealizado, Repuesto, Factura, Proveedor
Enumeradores para EstadoReparacion, TipoServicio, NivelDificultad
records para representar datos inmutables como resúmenes de mantenimiento o registros de auditoría.