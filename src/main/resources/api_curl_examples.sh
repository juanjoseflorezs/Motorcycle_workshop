#!/usr/bin/env bash
# Motorcycle Workshop API - cURL examples
# How to use:
#   chmod +x api_curl_examples.sh
#   ./api_curl_examples.sh
# Or copy individual curl commands into your terminal or import this file into Postman (Import -> Raw text).

BASE_URL=${BASE_URL:-http://localhost:8080}
CONTENT_JSON=("Content-Type: application/json")

###############################################################################
# CLIENTS
###############################################################################

# Create client
curl --location --request POST "$BASE_URL/api/clients" \
  --header "Content-Type: application/json" \
  --data '{
    "clientId": "C001",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phone": "3001234567",
    "address": "Street 123",
    "city": "City",
    "documentType": "ID",
    "documentNumber": "123456",
    "registrationDate": "2025-01-01"
  }'

# Get all clients
curl --location "$BASE_URL/api/clients"

# Get client by ID
curl --location "$BASE_URL/api/clients/C001"

# Update client
curl --location --request PUT "$BASE_URL/api/clients/C001" \
  --header "Content-Type: application/json" \
  --data '{
    "clientId": "C001",
    "firstName": "John",
    "lastName": "Doe Updated",
    "email": "john@example.com",
    "phone": "3007654321",
    "address": "Street 456",
    "city": "City",
    "documentType": "ID",
    "documentNumber": "123456",
    "registrationDate": "2025-01-01"
  }'

# Delete client
curl --location --request DELETE "$BASE_URL/api/clients/C001"

# By email
curl --location "$BASE_URL/api/clients/by-email?email=john@example.com"

# By city
curl --location "$BASE_URL/api/clients/by-city?city=City"

# Search by name
curl --location "$BASE_URL/api/clients/search?q=john"

# Total clients
curl --location "$BASE_URL/api/clients/stats/total"

###############################################################################
# VEHICLES
###############################################################################

# Add motorcycle
curl --location --request POST "$BASE_URL/api/vehicles/motorcycles" \
  --header "Content-Type: application/json" \
  --data '{
    "id": "M001",
    "brand": "Honda",
    "model": "CBR500R",
    "year": 2021,
    "color": "Red",
    "price": 5000.0,
    "engineCC": 471,
    "transmissionType": "Manual",
    "hasWindshield": true
  }'

# Add car
curl --location --request POST "$BASE_URL/api/vehicles/cars" \
  --header "Content-Type: application/json" \
  --data '{
    "id": "C001",
    "brand": "Toyota",
    "model": "Corolla",
    "year": 2020,
    "color": "White",
    "price": 12000.0,
    "numberOfDoors": 4,
    "fuelType": "Gasoline",
    "fuelConsumption": 6.5
  }'

# Add truck
curl --location --request POST "$BASE_URL/api/vehicles/trucks" \
  --header "Content-Type: application/json" \
  --data '{
    "id": "T001",
    "brand": "Volvo",
    "model": "FH16",
    "year": 2019,
    "color": "Gray",
    "price": 45000.0,
    "loadCapacityTons": 25.0,
    "numberOfAxles": 3,
    "cargoType": "General"
  }'

# Get all vehicles
curl --location "$BASE_URL/api/vehicles"

# Get by ID
curl --location "$BASE_URL/api/vehicles/M001"

# Update vehicle (motorcycle example)
curl --location --request PUT "$BASE_URL/api/vehicles/M001" \
  --header "Content-Type: application/json" \
  --data '{
    "id": "M001",
    "brand": "Honda",
    "model": "CBR500R",
    "year": 2022,
    "color": "Black",
    "price": 5200.0,
    "engineCC": 471,
    "transmissionType": "Manual",
    "hasWindshield": true
  }'

# Delete vehicle
curl --location --request DELETE "$BASE_URL/api/vehicles/M001"

# Lists by type
curl --location "$BASE_URL/api/vehicles/motorcycles"
curl --location "$BASE_URL/api/vehicles/cars"
curl --location "$BASE_URL/api/vehicles/trucks"

# Stats
curl --location "$BASE_URL/api/vehicles/maintenance/highest"
curl --location "$BASE_URL/api/vehicles/stats/maintenance/total"
curl --location "$BASE_URL/api/vehicles/stats/type"

###############################################################################
# SERVICES (CATALOG)
###############################################################################

# Create service
curl --location --request POST "$BASE_URL/api/services" \
  --header "Content-Type: application/json" \
  --data '{
    "serviceId": "S001",
    "serviceName": "Oil Change",
    "description": "Change engine oil",
    "baseCost": 30.0,
    "estimatedHours": 1.0,
    "category": "Maintenance",
    "isActive": true
  }'

# Get all services
curl --location "$BASE_URL/api/services"

# Get active services
curl --location "$BASE_URL/api/services/active"

# Get by ID
curl --location "$BASE_URL/api/services/S001"

# Update service
curl --location --request PUT "$BASE_URL/api/services/S001" \
  --header "Content-Type: application/json" \
  --data '{
    "serviceId": "S001",
    "serviceName": "Oil + Filter Change",
    "description": "Change engine oil and filter",
    "baseCost": 45.0,
    "estimatedHours": 1.5,
    "category": "Maintenance",
    "isActive": true
  }'

# Delete service
curl --location --request DELETE "$BASE_URL/api/services/S001"

# Activate/Deactivate
curl --location --request POST "$BASE_URL/api/services/S001/activate"
curl --location --request POST "$BASE_URL/api/services/S001/deactivate"

# Queries & stats
curl --location "$BASE_URL/api/services/by-category?category=Maintenance"
curl --location "$BASE_URL/api/services/search?q=oil"
curl --location "$BASE_URL/api/services/stats/average-cost"
curl --location "$BASE_URL/api/services/stats/total"

###############################################################################
# SPARE PARTS
###############################################################################

# Create spare part
curl --location --request POST "$BASE_URL/api/spare-parts" \
  --header "Content-Type: application/json" \
  --data '{
    "partId": "P001",
    "name": "Oil Filter",
    "brand": "Bosch",
    "category": "Filters",
    "unitPrice": 10.0,
    "quantityInStock": 20,
    "minimumStock": 5,
    "vehicleType": "Motorcycle",
    "supplier": "Supplier A",
    "location": "A1-01"
  }'

# Get all parts
curl --location "$BASE_URL/api/spare-parts"

# Get by ID
curl --location "$BASE_URL/api/spare-parts/P001"

# Update part
curl --location --request PUT "$BASE_URL/api/spare-parts/P001" \
  --header "Content-Type: application/json" \
  --data '{
    "partId": "P001",
    "name": "Oil Filter Premium",
    "brand": "Bosch",
    "category": "Filters",
    "unitPrice": 12.5,
    "quantityInStock": 25,
    "minimumStock": 5,
    "vehicleType": "Motorcycle",
    "supplier": "Supplier A",
    "location": "A1-02"
  }'

# Delete part
curl --location --request DELETE "$BASE_URL/api/spare-parts/P001"

# Availability & stock
curl --location "$BASE_URL/api/spare-parts/available"
curl --location "$BASE_URL/api/spare-parts/low-stock"
curl --location --request PATCH "$BASE_URL/api/spare-parts/P001/stock?quantity=5"

# Filters & search
curl --location "$BASE_URL/api/spare-parts/by-vehicle-type?vehicleType=Motorcycle"
curl --location "$BASE_URL/api/spare-parts/by-category?category=Filters"
curl --location "$BASE_URL/api/spare-parts/by-brand?brand=Bosch"
curl --location "$BASE_URL/api/spare-parts/search?q=filter"
curl --location "$BASE_URL/api/spare-parts/by-price-range?min=0&max=1000"

# Stats
curl --location "$BASE_URL/api/spare-parts/stats/total-value"
curl --location "$BASE_URL/api/spare-parts/stats/total-quantity"

###############################################################################
# INVENTORIES
###############################################################################

# Create inventory
curl --location --request POST "$BASE_URL/api/inventories" \
  --header "Content-Type: application/json" \
  --data '{
    "inventoryId": "INV-001",
    "warehouseLocation": "Main Warehouse"
  }'

# Get all inventories
curl --location "$BASE_URL/api/inventories"

# Get by ID
curl --location "$BASE_URL/api/inventories/INV-001"

# Add part to inventory
curl --location --request POST "$BASE_URL/api/inventories/INV-001/parts" \
  --header "Content-Type: application/json" \
  --data '{
    "partId": "P002",
    "name": "Brake Pads",
    "brand": "Brembo",
    "category": "Brakes",
    "unitPrice": 40.0,
    "quantityInStock": 10,
    "minimumStock": 3,
    "vehicleType": "Motorcycle",
    "supplier": "Supplier B",
    "location": "B2-01"
  }'

# Get part from inventory
curl --location "$BASE_URL/api/inventories/INV-001/parts/P002"

# Update stock in inventory
curl --location --request PATCH "$BASE_URL/api/inventories/INV-001/parts/P002/stock?quantity=5"

# Remove part from inventory
curl --location --request DELETE "$BASE_URL/api/inventories/INV-001/parts/P002"

# Low stock / filters
curl --location "$BASE_URL/api/inventories/INV-001/parts/low-stock"
curl --location "$BASE_URL/api/inventories/INV-001/parts/by-vehicle-type?vehicleType=Motorcycle"
curl --location "$BASE_URL/api/inventories/INV-001/parts/by-category?category=Brakes"

# Stats & report
curl --location "$BASE_URL/api/inventories/INV-001/stats/total-value"
curl --location "$BASE_URL/api/inventories/INV-001/stats/total-types"
curl --location "$BASE_URL/api/inventories/INV-001/stats/total-quantity"
curl --location "$BASE_URL/api/inventories/INV-001/report"
curl --location "$BASE_URL/api/inventories/INV-001/summary"

# Delete inventory
curl --location --request DELETE "$BASE_URL/api/inventories/INV-001"

###############################################################################
# MAINTENANCES
###############################################################################

# Create maintenance
curl --location --request POST "$BASE_URL/api/maintenances" \
  --header "Content-Type: application/json" \
  --data '{
    "maintenanceId": "MT001",
    "vehicleId": "M001",
    "maintenanceDate": "2025-01-10",
    "description": "General service",
    "cost": 120.0,
    "technician": "Tech A",
    "status": "OPEN"
  }'

# Get all maintenances
curl --location "$BASE_URL/api/maintenances"

# Get by ID
curl --location "$BASE_URL/api/maintenances/MT001"

# Get by vehicle
curl --location "$BASE_URL/api/maintenances/by-vehicle/M001"

# Update maintenance
curl --location --request PUT "$BASE_URL/api/maintenances/MT001" \
  --header "Content-Type: application/json" \
  --data '{
    "maintenanceId": "MT001",
    "vehicleId": "M001",
    "maintenanceDate": "2025-01-12",
    "description": "General service + brakes",
    "cost": 180.0,
    "technician": "Tech B",
    "status": "CLOSED"
  }'

# Delete maintenance
curl --location --request DELETE "$BASE_URL/api/maintenances/MT001"
