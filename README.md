# 🏍️ Motorcycle Workshop - Sistema de Gestión

Un proyecto educativo completo que implementa un sistema de gestión para un taller de motos utilizando **Spring Boot**, **Lombok**, **herencia**, **polimorfismo**, **interfaces** y **clases abstractas** en Java.

## 🎯 Características Principales

- ✅ **Spring Boot 3.5.6** - Framework REST moderno
- ✅ **Lombok** - Reducción de código repetitivo
- ✅ **Herencia** - Clase abstracta `Vehicle` con subclases especializadas
- ✅ **Polimorfismo** - Métodos abstractos implementados diferente por tipo
- ✅ **Interfaces** - Contrato `Saveable` para serialización CSV
- ✅ **Clases Abstractas** - Definición de comportamiento común
- ✅ **Nombres en Inglés** - Código internacional
- ✅ **Persistencia CSV** - Guardado automático de datos
- ✅ **API REST** - 15+ endpoints funcionales
- ✅ **Código Simple** - Fácil de entender y mantener

## 📁 Estructura del Proyecto

```
Motorcycle_workshop/
├── src/main/java/co/edu/umanizales/motorcycle_workshop/
│   ├── model/
│   │   ├── Vehicle.java              (Clase abstracta)
│   │   ├── Motorcycle.java           (Herencia)
│   │   ├── Car.java                  (Herencia)
│   │   ├── Truck.java                (Herencia)
│   │   ├── Maintenance.java          (Modelo)
│   │   ├── Saveable.java             (Interfaz)
│   │   ├── VehicleCSV.java           (Implementa Saveable)
│   │   └── VehicleExample.java       (Ejemplo de uso)
│   ├── service/
│   │   ├── VehicleService.java       (Lógica de negocio)
│   │   └── CSVService.java           (Manejo de CSV)
│   └── controller/
│       └── Prog3Controller.java      (Endpoints REST)
├── src/main/resources/
│   └── application.properties        (Configuración)
├── MODELO_DOCUMENTACION.md           (Documentación técnica)
├── EJEMPLOS_CURL.md                  (20+ ejemplos de uso)
├── RESUMEN_IMPLEMENTACION.md         (Resumen del proyecto)
└── README.md                         (Este archivo)
```

## 🚀 Inicio Rápido

### Requisitos
- Java 17 o superior
- Maven 3.6+
- Git (opcional)

### Instalación y Ejecución

```bash
# 1. Clonar o descargar el proyecto
git clone <repository-url>
cd Motorcycle_workshop

# 2. Compilar el proyecto
mvn clean install

# 3. Ejecutar la aplicación
mvn spring-boot:run

# 4. La API estará disponible en:
# http://localhost:8080/prog3
```

## 📚 Documentación

### Archivos de Documentación

| Archivo | Descripción |
|---------|-------------|
| **MODELO_DOCUMENTACION.md** | Documentación completa del modelo, endpoints y ejemplos |
| **EJEMPLOS_CURL.md** | 20+ ejemplos prácticos con comandos CURL |
| **RESUMEN_IMPLEMENTACION.md** | Resumen de requisitos implementados |
| **README.md** | Este archivo - Guía de inicio rápido |

## 🎓 Conceptos de POO Implementados

### 1. Herencia
```java
public abstract class Vehicle { ... }
public class Motorcycle extends Vehicle { ... }
public class Car extends Vehicle { ... }
public class Truck extends Vehicle { ... }
```

### 2. Polimorfismo
```java
// Mismo método, diferente implementación
vehicle.getVehicleType();           // "Motorcycle", "Car", "Truck"
vehicle.calculateMaintenanceCost(); // Cálculo diferente por tipo
```

### 3. Clases Abstractas
```java
public abstract class Vehicle {
    public abstract String getVehicleType();
    public abstract Double calculateMaintenanceCost();
}
```

### 4. Interfaces
```java
public interface Saveable {
    String toCSV();
    String getCSVHeader();
}
```

### 5. Encapsulación (con Lombok)
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motorcycle extends Vehicle { ... }
```

## 🔌 API REST - Endpoints Principales

### Agregar Vehículos
```bash
POST /prog3/motorcycle    # Agregar moto
POST /prog3/car           # Agregar carro
POST /prog3/truck         # Agregar camión
```

### Consultar Vehículos
```bash
GET /prog3/vehicles       # Todos los vehículos
GET /prog3/vehicle/{id}   # Vehículo específico
GET /prog3/motorcycles    # Todas las motos
GET /prog3/cars           # Todos los carros
GET /prog3/trucks         # Todos los camiones
```

### Mantenimiento
```bash
GET /prog3/maintenance-cost/{id}      # Costo de mantenimiento
GET /prog3/total-maintenance-cost     # Costo total
GET /prog3/highest-maintenance        # Mayor costo
```

### Utilidades
```bash
GET /prog3/statistics     # Estadísticas
DELETE /prog3/vehicle/{id} # Eliminar vehículo
```

## 📝 Ejemplo de Uso

### 1. Agregar una Moto
```bash
curl -X POST http://localhost:8080/prog3/motorcycle \
  -H "Content-Type: application/json" \
  -d '{
    "id": "MOTO001",
    "brand": "Yamaha",
    "model": "YZF-R1",
    "year": 2023,
    "color": "Rojo",
    "price": 15000000,
    "engineCC": 998,
    "transmissionType": "Manual",
    "hasWindshield": true
  }'
```

### 2. Obtener Todas las Motos
```bash
curl http://localhost:8080/prog3/motorcycles
```

### 3. Calcular Costo de Mantenimiento
```bash
curl http://localhost:8080/prog3/maintenance-cost/MOTO001
```

### 4. Ver Estadísticas
```bash
curl http://localhost:8080/prog3/statistics
```

## 💾 Almacenamiento de Datos

Los datos se guardan automáticamente en archivos CSV:

```
data/vehicles.csv
```

### Ejemplo de Contenido CSV
```csv
ID,Brand,Model,Year,Color,Price,Type,EngineCC,TransmissionType,HasWindshield
MOTO001,Yamaha,YZF-R1,2023,Rojo,15000000,Motorcycle,998,Manual,true
```

## 🧮 Cálculo de Costos de Mantenimiento

| Tipo | Fórmula | Ejemplo |
|------|---------|---------|
| **Moto** | 50,000 × (EngineCC / 100) | 50,000 × (998 / 100) = 499,000 |
| **Carro** | 100,000 + (FuelConsumption × 10) | 100,000 + (7.5 × 10) = 100,075 |
| **Camión** | 200,000 + (Capacity × 5,000) + (Axles × 15,000) | 200,000 + (25 × 5,000) + (3 × 15,000) = 365,000 |

## 🔧 Tecnologías Utilizadas

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web-services</artifactId>
    <version>3.5.6</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

## 📊 Diagrama de Clases

```
┌─────────────────────────────────────┐
│      Vehicle (Abstracta)            │
├─────────────────────────────────────┤
│ - id: String                        │
│ - brand: String                     │
│ - model: String                     │
│ - year: Integer                     │
│ - color: String                     │
│ - price: Double                     │
├─────────────────────────────────────┤
│ + getVehicleType(): String (abs)    │
│ + calculateMaintenanceCost(): Double│
│ + getFullDescription(): String      │
└─────────────────────────────────────┘
         ▲           ▲           ▲
         │           │           │
    ┌────┴─┐    ┌────┴─┐    ┌───┴───┐
    │      │    │      │    │       │
┌───┴──┐ ┌─┴──┐ ┌─┴──┐ ┌─┴──┐ ┌──┴──┐
│Moto  │ │Car │ │Truck│ │...│ │...  │
└──────┘ └────┘ └─────┘ └────┘ └─────┘

┌──────────────────────────┐
│   Saveable (Interfaz)    │
├──────────────────────────┤
│ + toCSV(): String        │
│ + getCSVHeader(): String │
└──────────────────────────┘
         ▲
         │
    ┌────┴─────────┐
    │              │
┌───┴──────┐  ┌───┴──────┐
│VehicleCSV│  │Maintenance│
└──────────┘  └───────────┘
```

## 🧪 Pruebas

### Ejecutar Ejemplo de Código
```bash
# Compilar
mvn clean compile

# Ejecutar la clase de ejemplo
mvn exec:java -Dexec.mainClass="co.edu.umanizales.motorcycle_workshop.model.VehicleExample"
```

### Pruebas con CURL
Ver archivo **EJEMPLOS_CURL.md** para 20+ ejemplos completos.

## 📖 Aprendizaje

Este proyecto es excelente para aprender:
- ✅ Herencia en Java
- ✅ Polimorfismo y métodos abstractos
- ✅ Interfaces y contratos
- ✅ Spring Boot REST APIs
- ✅ Manejo de archivos CSV
- ✅ Arquitectura de servicios
- ✅ Anotaciones con Lombok
- ✅ Patrones de diseño

## 🤝 Contribuciones

Para extender el proyecto:

1. **Agregar nuevo tipo de vehículo:**
   ```java
   public class Bus extends Vehicle {
       private Integer numberOfSeats;
       
       @Override
       public String getVehicleType() {
           return "Bus";
       }
       
       @Override
       public Double calculateMaintenanceCost() {
           return 150000.0 * (numberOfSeats / 50.0);
       }
   }
   ```

2. **Actualizar VehicleCSV** para manejar el nuevo tipo

3. **Agregar endpoints** en el controlador

## 📞 Soporte

Para más información:
- 📄 Ver **MODELO_DOCUMENTACION.md**
- 💡 Ver **EJEMPLOS_CURL.md**
- 📋 Ver **RESUMEN_IMPLEMENTACION.md**

## 📄 Licencia

Este proyecto es de código abierto y está disponible para propósitos educativos.

## ✨ Características Destacadas

- **Código Simple** - Fácil de entender incluso para principiantes
- **Bien Documentado** - Comentarios en inglés en todo el código
- **Ejemplos Prácticos** - 20+ ejemplos de uso con CURL
- **Persistencia Automática** - Los datos se guardan en CSV
- **Extensible** - Fácil agregar nuevas funcionalidades
- **Educativo** - Perfecto para aprender POO y Spring Boot

---

**¡Bienvenido al Taller de Motos! 🏍️**

*Proyecto desarrollado con Spring Boot, Lombok, Herencia, Polimorfismo, Interfaces y Clases Abstractas*

**Versión:** 1.0.0  
**Java:** 17  
**Spring Boot:** 3.5.6  
**Estado:** ✅ Completo y Funcional
