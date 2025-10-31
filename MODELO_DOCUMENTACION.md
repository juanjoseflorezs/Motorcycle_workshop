# Modelo de Taller de Motos - Documentación

## Descripción General

Este proyecto implementa un sistema de gestión para un taller de motos utilizando **Spring Boot**, **Lombok**, **herencia**, **polimorfismo**, **interfaces** y **clases abstractas**. Los datos se guardan en archivos **CSV**.

## Estructura del Proyecto

```
src/main/java/co/edu/umanizales/motorcycle_workshop/
├── model/
│   ├── Vehicle.java          (Clase abstracta base)
│   ├── Motorcycle.java       (Hereda de Vehicle)
│   ├── Car.java              (Hereda de Vehicle)
│   ├── Truck.java            (Hereda de Vehicle)
│   ├── Maintenance.java      (Registro de mantenimiento)
│   ├── Saveable.java         (Interfaz)
│   └── VehicleCSV.java       (Implementa Saveable)
├── service/
│   ├── VehicleService.java   (Lógica de negocio)
│   └── CSVService.java       (Manejo de archivos CSV)
└── controller/
    └── Prog3Controller.java  (Endpoints REST)
```

## Conceptos Implementados

### 1. **Clase Abstracta: Vehicle**
- Define la estructura base para todos los vehículos
- Métodos abstractos que deben implementar las subclases:
  - `getVehicleType()` - Retorna el tipo de vehículo
  - `calculateMaintenanceCost()` - Calcula el costo de mantenimiento
- Método común: `getFullDescription()` - Descripción del vehículo

### 2. **Herencia y Polimorfismo**
- **Motorcycle** - Hereda de Vehicle (motos)
- **Car** - Hereda de Vehicle (carros)
- **Truck** - Hereda de Vehicle (camiones)

Cada clase implementa sus propios cálculos de mantenimiento según sus características.

### 3. **Interfaz: Saveable**
Define el contrato para objetos que pueden guardarse en CSV:
- `toCSV()` - Convierte el objeto a formato CSV
- `getCSVHeader()` - Retorna el encabezado CSV

### 4. **Implementación: VehicleCSV**
Implementa la interfaz `Saveable` para permitir que los vehículos se guarden en archivos CSV.

### 5. **Servicios**
- **VehicleService** - Gestiona operaciones CRUD de vehículos
- **CSVService** - Maneja lectura/escritura de archivos CSV

## Endpoints REST

### Vehículos

#### Agregar Moto
```
POST /prog3/motorcycle
Content-Type: application/json

{
  "id": "MOTO001",
  "brand": "Yamaha",
  "model": "YZF-R1",
  "year": 2023,
  "color": "Rojo",
  "price": 15000000,
  "engineCC": 998,
  "transmissionType": "Manual",
  "hasWindshield": true
}
```

#### Agregar Carro
```
POST /prog3/car
Content-Type: application/json

{
  "id": "CAR001",
  "brand": "Toyota",
  "model": "Corolla",
  "year": 2023,
  "color": "Blanco",
  "price": 35000000,
  "numberOfDoors": 4,
  "fuelType": "Gasolina",
  "fuelConsumption": 7.5
}
```

#### Agregar Camión
```
POST /prog3/truck
Content-Type: application/json

{
  "id": "TRUCK001",
  "brand": "Volvo",
  "model": "FH16",
  "year": 2022,
  "color": "Azul",
  "price": 80000000,
  "loadCapacityTons": 25.0,
  "numberOfAxles": 3,
  "cargoType": "General"
}
```

### Consultas

#### Obtener todos los vehículos
```
GET /prog3/vehicles
```

#### Obtener vehículo por ID
```
GET /prog3/vehicle/{id}
```

#### Obtener todas las motos
```
GET /prog3/motorcycles
```

#### Obtener todos los carros
```
GET /prog3/cars
```

#### Obtener todos los camiones
```
GET /prog3/trucks
```

#### Calcular costo de mantenimiento de un vehículo
```
GET /prog3/maintenance-cost/{id}
```

#### Obtener costo total de mantenimiento
```
GET /prog3/total-maintenance-cost
```

#### Obtener vehículo con mayor costo de mantenimiento
```
GET /prog3/highest-maintenance
```

#### Obtener estadísticas
```
GET /prog3/statistics
```

### Eliminar

#### Eliminar vehículo por ID
```
DELETE /prog3/vehicle/{id}
```

## Cálculo de Costos de Mantenimiento

### Moto
```
Costo = 50,000 × (EngineCC / 100)
```

### Carro
```
Costo = 100,000 + (FuelConsumption × 10)
```

### Camión
```
Costo = 200,000 + (LoadCapacity × 5,000) + (Axles × 15,000)
```

## Archivos CSV

Los datos se guardan en la carpeta `data/` en la raíz del proyecto:
- `data/vehicles.csv` - Contiene todos los vehículos registrados

### Formato CSV para Motos
```
ID,Brand,Model,Year,Color,Price,Type,EngineCC,TransmissionType,HasWindshield
MOTO001,Yamaha,YZF-R1,2023,Rojo,15000000,Motorcycle,998,Manual,true
```

### Formato CSV para Carros
```
ID,Brand,Model,Year,Color,Price,Type,NumberOfDoors,FuelType,FuelConsumption
CAR001,Toyota,Corolla,2023,Blanco,35000000,Car,4,Gasolina,7.5
```

### Formato CSV para Camiones
```
ID,Brand,Model,Year,Color,Price,Type,LoadCapacityTons,NumberOfAxles,CargoType
TRUCK001,Volvo,FH16,2022,Azul,80000000,Truck,25.0,3,General
```

## Características de Lombok

Se utiliza Lombok para reducir código repetitivo:
- `@Data` - Genera getters, setters, toString, equals y hashCode
- `@NoArgsConstructor` - Constructor sin argumentos
- `@AllArgsConstructor` - Constructor con todos los argumentos

## Ejemplo de Uso Completo

1. **Iniciar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

2. **Agregar una moto**
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

3. **Obtener todas las motos**
   ```bash
   curl http://localhost:8080/prog3/motorcycles
   ```

4. **Calcular costo de mantenimiento**
   ```bash
   curl http://localhost:8080/prog3/maintenance-cost/MOTO001
   ```

5. **Ver estadísticas**
   ```bash
   curl http://localhost:8080/prog3/statistics
   ```

## Notas Importantes

- Los datos se guardan automáticamente en CSV cuando se agregan vehículos
- El proyecto utiliza Java 17 como versión mínima
- Spring Boot versión 3.5.6
- Todos los nombres están en inglés para mejor mantenibilidad internacional
- El código es simple y fácil de entender con comentarios explicativos

## Extensibilidad

Para agregar un nuevo tipo de vehículo:

1. Crear una nueva clase que herede de `Vehicle`
2. Implementar los métodos abstractos
3. Actualizar `VehicleCSV` para manejar el nuevo tipo
4. Agregar endpoints en el controlador

Ejemplo:
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

---

**Proyecto desarrollado con Spring Boot, Lombok, Herencia, Polimorfismo, Interfaces y Clases Abstractas**
