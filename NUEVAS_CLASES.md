# Nuevas Clases Implementadas - Taller de Motos

## 📋 Resumen

Se han agregado **4 nuevas clases principales** al proyecto con sus respectivos servicios y endpoints REST:

1. **Client** - Gestión de clientes
2. **Service** - Gestión de servicios del taller
3. **SparePart** - Gestión de repuestos
4. **Inventory** - Gestión de inventario

---

## 🏢 Clase: Client

Representa los clientes del taller de motos.

### Atributos
```java
- clientId: String
- firstName: String
- lastName: String
- email: String
- phone: String
- address: String
- city: String
- documentType: String
- documentNumber: String
- registrationDate: String
```

### Métodos Principales
- `getFullName()` - Obtiene nombre completo
- `getContactInfo()` - Información de contacto
- `updateProfile()` - Actualiza perfil del cliente
- `getSummary()` - Resumen del cliente

### Endpoints REST

#### Agregar cliente
```bash
POST /prog3/client
Content-Type: application/json

{
  "clientId": "CLI001",
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "phone": "3001234567",
  "address": "Calle 10 #20-30",
  "city": "Manizales",
  "documentType": "CC",
  "documentNumber": "1234567890",
  "registrationDate": "2024-01-15"
}
```

#### Obtener todos los clientes
```bash
GET /prog3/clients
```

#### Obtener cliente por ID
```bash
GET /prog3/client/{id}
```

#### Buscar clientes por nombre
```bash
GET /prog3/clients/search/{name}
```

#### Obtener clientes por ciudad
```bash
GET /prog3/clients/city/{city}
```

#### Eliminar cliente
```bash
DELETE /prog3/client/{id}
```

---

## 🔧 Clase: Service

Representa los servicios ofrecidos por el taller.

### Atributos
```java
- serviceId: String
- serviceName: String
- description: String
- baseCost: Double
- estimatedHours: Double
- category: String
- isActive: Boolean
```

### Métodos Principales
- `calculateCost(hourlyRate)` - Calcula costo total con mano de obra
- `getServiceDetails()` - Detalles del servicio
- `getServiceInfo()` - Información del servicio
- `isAvailable()` - Verifica disponibilidad

### Endpoints REST

#### Agregar servicio
```bash
POST /prog3/service
Content-Type: application/json

{
  "serviceId": "SRV001",
  "serviceName": "Cambio de Aceite",
  "description": "Cambio de aceite y filtro",
  "baseCost": 50000,
  "estimatedHours": 0.5,
  "category": "Mantenimiento",
  "isActive": true
}
```

#### Obtener todos los servicios
```bash
GET /prog3/services
```

#### Obtener servicios activos
```bash
GET /prog3/services/active
```

#### Obtener servicio por ID
```bash
GET /prog3/service/{id}
```

#### Obtener servicios por categoría
```bash
GET /prog3/services/category/{category}
```

#### Eliminar servicio
```bash
DELETE /prog3/service/{id}
```

---

## 🔩 Clase: SparePart

Representa los repuestos disponibles en el taller.

### Atributos
```java
- partId: String
- partName: String
- brand: String
- category: String
- unitPrice: Double
- quantityInStock: Integer
- minimumStock: Integer
- vehicleType: String
- supplier: String
- location: String
```

### Métodos Principales
- `isAvailable()` - Verifica disponibilidad
- `isLowStock()` - Verifica si stock es bajo
- `updateStock(quantity)` - Actualiza cantidad
- `getTotalValue()` - Valor total del repuesto
- `getPartInfo()` - Información del repuesto
- `getSummary()` - Resumen del repuesto

### Endpoints REST

#### Agregar repuesto
```bash
POST /prog3/spare-part
Content-Type: application/json

{
  "partId": "PART001",
  "partName": "Filtro de Aire",
  "brand": "Bosch",
  "category": "Filtros",
  "unitPrice": 25000,
  "quantityInStock": 15,
  "minimumStock": 5,
  "vehicleType": "Motorcycle",
  "supplier": "Distribuidor XYZ",
  "location": "Estante A-1"
}
```

#### Obtener todos los repuestos
```bash
GET /prog3/spare-parts
```

#### Obtener repuestos disponibles
```bash
GET /prog3/spare-parts/available
```

#### Obtener repuestos con stock bajo
```bash
GET /prog3/spare-parts/low-stock
```

#### Obtener repuesto por ID
```bash
GET /prog3/spare-part/{id}
```

#### Obtener repuestos por tipo de vehículo
```bash
GET /prog3/spare-parts/vehicle/{vehicleType}
```

#### Obtener repuestos por categoría
```bash
GET /prog3/spare-parts/category/{category}
```

#### Actualizar stock de repuesto
```bash
PUT /prog3/spare-part/{id}/stock/{quantity}
```

#### Eliminar repuesto
```bash
DELETE /prog3/spare-part/{id}
```

#### Obtener valor total del inventario
```bash
GET /prog3/spare-parts/inventory-value
```

---

## 📦 Clase: Inventory

Gestiona el inventario completo del taller.

### Atributos
```java
- inventoryId: String
- spareParts: List<SparePart>
- lastUpdated: String
- warehouseLocation: String
```

### Métodos Principales
- `addPart(part)` - Agrega repuesto
- `removePart(partId)` - Elimina repuesto
- `getPartById(partId)` - Obtiene repuesto por ID
- `getLowStockItems()` - Obtiene repuestos con stock bajo
- `getPartsByVehicleType(type)` - Obtiene repuestos por tipo
- `getPartsByCategory(category)` - Obtiene repuestos por categoría
- `getTotalInventoryValue()` - Valor total del inventario
- `getTotalPartTypes()` - Cantidad de tipos diferentes
- `getTotalQuantity()` - Cantidad total de repuestos
- `generateReport()` - Genera reporte del inventario
- `getSummary()` - Resumen del inventario

### Endpoints REST

#### Crear inventario
```bash
POST /prog3/inventory
Content-Type: application/json

{
  "inventoryId": "INV001",
  "spareParts": [],
  "lastUpdated": "2024-01-15",
  "warehouseLocation": "Bodega Principal"
}
```

#### Obtener todos los inventarios
```bash
GET /prog3/inventories
```

#### Obtener inventario por ID
```bash
GET /prog3/inventory/{id}
```

#### Obtener repuestos con stock bajo del inventario
```bash
GET /prog3/inventory/{id}/low-stock
```

#### Obtener valor total del inventario
```bash
GET /prog3/inventory/{id}/total-value
```

#### Obtener resumen del inventario
```bash
GET /prog3/inventory/{id}/summary
```

#### Generar reporte del inventario
```bash
GET /prog3/inventory/{id}/report
```

#### Eliminar inventario
```bash
DELETE /prog3/inventory/{id}
```

---

## 🛠️ Servicios Creados

### ClientService
Gestiona operaciones CRUD de clientes:
- `addClient()`, `getAllClients()`, `getClientById()`
- `getClientByEmail()`, `getClientsByCity()`
- `updateClient()`, `deleteClient()`
- `searchClientsByName()`, `getTotalClients()`

### ServiceService
Gestiona operaciones CRUD de servicios:
- `addService()`, `getAllServices()`, `getActiveServices()`
- `getServiceById()`, `getServicesByCategory()`
- `updateService()`, `deleteService()`
- `activateService()`, `deactivateService()`
- `getAverageServiceCost()`, `getTotalServices()`
- `searchServicesByName()`

### SparePartService
Gestiona operaciones CRUD de repuestos:
- `addSparePart()`, `getAllSpareParts()`, `getAvailableParts()`
- `getLowStockParts()`, `getSparePartById()`
- `getPartsByVehicleType()`, `getPartsByCategory()`, `getPartsByBrand()`
- `updatePartStock()`, `updateSparePart()`, `deleteSparePart()`
- `getTotalInventoryValue()`, `getTotalQuantity()`
- `searchPartsByName()`, `getPartsByPriceRange()`

### InventoryService
Gestiona operaciones de inventario:
- `createInventory()`, `getAllInventories()`, `getInventoryById()`
- `addPartToInventory()`, `removePartFromInventory()`
- `updatePartStock()`, `getPartFromInventory()`
- `getLowStockItems()`, `getPartsByVehicleType()`, `getPartsByCategory()`
- `getTotalInventoryValue()`, `getTotalPartTypes()`, `getTotalQuantity()`
- `generateInventoryReport()`, `getInventorySummary()`
- `deleteInventory()`, `getTotalValueAllInventories()`, `getTotalPartsAllInventories()`

---

## 📊 Ejemplo de Flujo Completo

### 1. Crear un cliente
```bash
curl -X POST http://localhost:8080/prog3/client \
  -H "Content-Type: application/json" \
  -d '{
    "clientId": "CLI001",
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "phone": "3001234567",
    "address": "Calle 10 #20-30",
    "city": "Manizales",
    "documentType": "CC",
    "documentNumber": "1234567890",
    "registrationDate": "2024-01-15"
  }'
```

### 2. Agregar servicios
```bash
curl -X POST http://localhost:8080/prog3/service \
  -H "Content-Type: application/json" \
  -d '{
    "serviceId": "SRV001",
    "serviceName": "Cambio de Aceite",
    "description": "Cambio de aceite y filtro",
    "baseCost": 50000,
    "estimatedHours": 0.5,
    "category": "Mantenimiento",
    "isActive": true
  }'
```

### 3. Agregar repuestos
```bash
curl -X POST http://localhost:8080/prog3/spare-part \
  -H "Content-Type: application/json" \
  -d '{
    "partId": "PART001",
    "partName": "Filtro de Aire",
    "brand": "Bosch",
    "category": "Filtros",
    "unitPrice": 25000,
    "quantityInStock": 15,
    "minimumStock": 5,
    "vehicleType": "Motorcycle",
    "supplier": "Distribuidor XYZ",
    "location": "Estante A-1"
  }'
```

### 4. Crear inventario
```bash
curl -X POST http://localhost:8080/prog3/inventory \
  -H "Content-Type: application/json" \
  -d '{
    "inventoryId": "INV001",
    "spareParts": [],
    "lastUpdated": "2024-01-15",
    "warehouseLocation": "Bodega Principal"
  }'
```

### 5. Consultar datos
```bash
# Ver todos los clientes
curl http://localhost:8080/prog3/clients

# Ver todos los servicios
curl http://localhost:8080/prog3/services

# Ver todos los repuestos
curl http://localhost:8080/prog3/spare-parts

# Ver resumen del inventario
curl http://localhost:8080/prog3/inventory/INV001/summary
```

---

## 🎯 Características Principales

✅ **Gestión de Clientes** - Registro y búsqueda de clientes  
✅ **Gestión de Servicios** - Catálogo de servicios del taller  
✅ **Gestión de Repuestos** - Control de stock y disponibilidad  
✅ **Gestión de Inventario** - Reportes y análisis de inventario  
✅ **Búsqueda Avanzada** - Filtros por categoría, tipo, ubicación  
✅ **Control de Stock** - Alertas de stock bajo  
✅ **Reportes** - Generación de reportes de inventario  
✅ **Código Simple** - Fácil de entender y mantener  

---

## 📝 Notas Importantes

- Todas las clases usan **Lombok** para reducir código
- Todos los nombres están en **inglés**
- Los servicios implementan **lógica de negocio**
- El controlador expone **endpoints REST**
- Código comentado y bien documentado
- Fácil de extender y mantener

---

## 🚀 Próximos Pasos

Puedes agregar más clases como:
- **Technician** - Técnicos del taller
- **Appointment** - Citas de mantenimiento
- **Invoice** - Facturación
- **Payment** - Gestión de pagos
- **MaintenanceRecord** - Registros de mantenimiento

---

**¡Las 4 nuevas clases están completamente implementadas y listas para usar!**

*Proyecto: Motorcycle Workshop - Taller de Motos*
