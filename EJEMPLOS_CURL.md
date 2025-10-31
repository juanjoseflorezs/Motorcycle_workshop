# Ejemplos de Uso - Comandos CURL

## Pruebas de la API

### 1. Verificar que la API está funcionando
```bash
curl http://localhost:8080/prog3
```

**Respuesta esperada:**
```
Hola campeones - Motorcycle Workshop API
```

---

## Agregar Vehículos

### 2. Agregar una Moto (Yamaha)
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

### 3. Agregar otra Moto (Honda)
```bash
curl -X POST http://localhost:8080/prog3/motorcycle \
  -H "Content-Type: application/json" \
  -d '{
    "id": "MOTO002",
    "brand": "Honda",
    "model": "CB500F",
    "year": 2022,
    "color": "Negro",
    "price": 12000000,
    "engineCC": 471,
    "transmissionType": "Manual",
    "hasWindshield": false
  }'
```

### 4. Agregar un Carro (Toyota)
```bash
curl -X POST http://localhost:8080/prog3/car \
  -H "Content-Type: application/json" \
  -d '{
    "id": "CAR001",
    "brand": "Toyota",
    "model": "Corolla",
    "year": 2023,
    "color": "Blanco",
    "price": 35000000,
    "numberOfDoors": 4,
    "fuelType": "Gasolina",
    "fuelConsumption": 7.5
  }'
```

### 5. Agregar otro Carro (Honda Civic)
```bash
curl -X POST http://localhost:8080/prog3/car \
  -H "Content-Type: application/json" \
  -d '{
    "id": "CAR002",
    "brand": "Honda",
    "model": "Civic",
    "year": 2021,
    "color": "Azul",
    "price": 32000000,
    "numberOfDoors": 4,
    "fuelType": "Gasolina",
    "fuelConsumption": 8.2
  }'
```

### 6. Agregar un Camión (Volvo)
```bash
curl -X POST http://localhost:8080/prog3/truck \
  -H "Content-Type: application/json" \
  -d '{
    "id": "TRUCK001",
    "brand": "Volvo",
    "model": "FH16",
    "year": 2022,
    "color": "Azul",
    "price": 80000000,
    "loadCapacityTons": 25.0,
    "numberOfAxles": 3,
    "cargoType": "General"
  }'
```

### 7. Agregar otro Camión (Scania)
```bash
curl -X POST http://localhost:8080/prog3/truck \
  -H "Content-Type: application/json" \
  -d '{
    "id": "TRUCK002",
    "brand": "Scania",
    "model": "R440",
    "year": 2020,
    "color": "Rojo",
    "price": 75000000,
    "loadCapacityTons": 20.0,
    "numberOfAxles": 2,
    "cargoType": "Refrigerado"
  }'
```

---

## Consultar Vehículos

### 8. Obtener todos los vehículos
```bash
curl http://localhost:8080/prog3/vehicles
```

### 9. Obtener vehículo específico por ID
```bash
curl http://localhost:8080/prog3/vehicle/MOTO001
```

### 10. Obtener todas las motos
```bash
curl http://localhost:8080/prog3/motorcycles
```

### 11. Obtener todos los carros
```bash
curl http://localhost:8080/prog3/cars
```

### 12. Obtener todos los camiones
```bash
curl http://localhost:8080/prog3/trucks
```

---

## Calcular Costos de Mantenimiento

### 13. Costo de mantenimiento de una moto específica
```bash
curl http://localhost:8080/prog3/maintenance-cost/MOTO001
```

**Respuesta esperada:**
```
Maintenance Cost: 499000.0
```

**Cálculo:** 50,000 × (998 / 100) = 499,000

### 14. Costo de mantenimiento de un carro específico
```bash
curl http://localhost:8080/prog3/maintenance-cost/CAR001
```

**Respuesta esperada:**
```
Maintenance Cost: 100075.0
```

**Cálculo:** 100,000 + (7.5 × 10) = 100,075

### 15. Costo de mantenimiento de un camión específico
```bash
curl http://localhost:8080/prog3/maintenance-cost/TRUCK001
```

**Respuesta esperada:**
```
Maintenance Cost: 365000.0
```

**Cálculo:** 200,000 + (25 × 5,000) + (3 × 15,000) = 365,000

### 16. Costo total de mantenimiento de todos los vehículos
```bash
curl http://localhost:8080/prog3/total-maintenance-cost
```

### 17. Vehículo con mayor costo de mantenimiento
```bash
curl http://localhost:8080/prog3/highest-maintenance
```

---

## Estadísticas

### 18. Obtener estadísticas del taller
```bash
curl http://localhost:8080/prog3/statistics
```

**Respuesta esperada (después de agregar todos los vehículos):**
```
Total Vehicles: 6 | Motorcycles: 2 | Cars: 2 | Trucks: 2
```

---

## Eliminar Vehículos

### 19. Eliminar un vehículo por ID
```bash
curl -X DELETE http://localhost:8080/prog3/vehicle/MOTO002
```

### 20. Verificar que fue eliminado
```bash
curl http://localhost:8080/prog3/vehicle/MOTO002
```

**Respuesta esperada:** Error 404 Not Found

---

## Verificar Archivos CSV

Los datos se guardan automáticamente en:
```
data/vehicles.csv
```

### Contenido esperado del archivo CSV:
```
ID,Brand,Model,Year,Color,Price,Type,EngineCC,TransmissionType,HasWindshield
MOTO001,Yamaha,YZF-R1,2023,Rojo,15000000,Motorcycle,998,Manual,true
MOTO002,Honda,CB500F,2022,Negro,12000000,Motorcycle,471,Manual,false
ID,Brand,Model,Year,Color,Price,Type,NumberOfDoors,FuelType,FuelConsumption
CAR001,Toyota,Corolla,2023,Blanco,35000000,Car,4,Gasolina,7.5
CAR002,Honda,Civic,2021,Azul,32000000,Car,4,Gasolina,8.2
ID,Brand,Model,Year,Color,Price,Type,LoadCapacityTons,NumberOfAxles,CargoType
TRUCK001,Volvo,FH16,2022,Azul,80000000,Truck,25.0,3,General
TRUCK002,Scania,R440,2020,Rojo,75000000,Truck,20.0,2,Refrigerado
```

---

## Notas Importantes

1. **Reemplazar localhost:8080** si tu servidor está en otro puerto
2. **Usar Postman o Insomnia** para pruebas más visuales
3. **Los datos se guardan en CSV** automáticamente
4. **El proyecto usa Spring Boot 3.5.6** con Java 17
5. **Todos los nombres están en inglés** para mejor mantenibilidad

---

## Flujo Completo de Prueba

```bash
# 1. Verificar API
curl http://localhost:8080/prog3

# 2. Agregar vehículos (ejecutar comandos 2-7)

# 3. Consultar vehículos (ejecutar comandos 8-12)

# 4. Calcular costos (ejecutar comandos 13-17)

# 5. Ver estadísticas
curl http://localhost:8080/prog3/statistics

# 6. Eliminar un vehículo
curl -X DELETE http://localhost:8080/prog3/vehicle/MOTO002

# 7. Verificar estadísticas nuevamente
curl http://localhost:8080/prog3/statistics
```

---

**¡Listo! Tu API de Taller de Motos está funcionando correctamente.**
