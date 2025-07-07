# SOAP Pret Service

A Spring Boot SOAP web service for checking loan eligibility based on age and nationality.

## Overview

This service accepts `PretRequest` containing applicant information and returns `PretResponse` indicating eligibility status.

### Business Rules

- **Age**: Must be 18 or older
- **Nationality**: Must be Moroccan (accepts various forms: "marocan", "marocain", "marocaine", "maroc", "moroccan", "morocco")

## Project Structure

```
soap-pret-service/
├── src/main/java/com/example/soap/
│   ├── SoapPretServiceApplication.java    # Main Spring Boot application
│   ├── WebServiceConfig.java              # SOAP configuration
│   ├── PretEndpoint.java                  # SOAP endpoint handler
│   ├── PretService.java                   # Business logic
│   └── pret/                              # Generated from XSD
│       ├── PretRequest.java               # Request model
│       ├── PretResponse.java              # Response model
│       └── ObjectFactory.java             # JAXB factory
├── src/main/resources/
│   ├── pret.xsd                           # XSD schema
│   └── application.properties             # Application configuration
└── pom.xml                               # Maven configuration
```

## Running the Application

1. **Start the application:**
   ```bash
   mvn spring-boot:run
   ```

2. **The service will be available at:**
   - WSDL: http://localhost:8080/soap-pret/ws/pret.wsdl
   - Endpoint: http://localhost:8080/soap-pret/ws

## Testing the Service

### Sample SOAP Request (Eligible)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:pret="http://soap.example.com/pret">
   <soapenv:Header/>
   <soapenv:Body>
      <pret:pretRequest>
         <pret:nom>Ahmed Ben Ali</pret:nom>
         <pret:age>25</pret:age>
         <pret:nationalite>marocan</pret:nationalite>
      </pret:pretRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

**Expected Response:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Body>
      <ns2:pretResponse xmlns:ns2="http://soap.example.com/pret">
         <ns2:isEligible>true</ns2:isEligible>
         <ns2:reason>Eligible pour le pret</ns2:reason>
      </ns2:pretResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

### Sample SOAP Request (Age Not Appropriate)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:pret="http://soap.example.com/pret">
   <soapenv:Header/>
   <soapenv:Body>
      <pret:pretRequest>
         <pret:nom>Sara Alami</pret:nom>
         <pret:age>17</pret:age>
         <pret:nationalite>marocan</pret:nationalite>
      </pret:pretRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

**Expected Response:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Body>
      <ns2:pretResponse xmlns:ns2="http://soap.example.com/pret">
         <ns2:isEligible>false</ns2:isEligible>
         <ns2:reason>Age pas approprie</ns2:reason>
      </ns2:pretResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

### Sample SOAP Request (Wrong Nationality)

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:pret="http://soap.example.com/pret">
   <soapenv:Header/>
   <soapenv:Body>
      <pret:pretRequest>
         <pret:nom>John Smith</pret:nom>
         <pret:age>30</pret:age>
         <pret:nationalite>american</pret:nationalite>
      </pret:pretRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

**Expected Response:**
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
   <soapenv:Body>
      <ns2:pretResponse xmlns:ns2="http://soap.example.com/pret">
         <ns2:isEligible>false</ns2:isEligible>
         <ns2:reason>Nationalite doit etre marocaine</ns2:reason>
      </ns2:pretResponse>
   </soapenv:Body>
</soapenv:Envelope>
```

## Testing with cURL

```bash
curl -X POST \
  http://localhost:8080/soap-pret/ws \
  -H 'Content-Type: text/xml; charset=utf-8' \
  -H 'SOAPAction: ""' \
  -d '<?xml version="1.0" encoding="utf-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pret="http://soap.example.com/pret">
   <soapenv:Header/>
   <soapenv:Body>
      <pret:pretRequest>
         <pret:nom>Ahmed Ben Ali</pret:nom>
         <pret:age>25</pret:age>
         <pret:nationalite>marocan</pret:nationalite>
      </pret:pretRequest>
   </soapenv:Body>
</soapenv:Envelope>'
```

## Testing with SoapUI

1. Create a new SOAP project in SoapUI
2. Enter the WSDL URL: `http://localhost:8080/soap-pret/ws/pret.wsdl`
3. Use the sample requests above to test different scenarios

## Configuration

The application can be configured via `application.properties`:

- `server.port=8080` - Change the port
- `server.servlet.context-path=/soap-pret` - Change the context path
- Logging levels can be adjusted for debugging

## Build and Package

```bash
# Clean and compile
mvn clean compile

# Package as JAR
mvn clean package

# Run the packaged JAR
java -jar target/soap-pret-service-1.0.0.jar
```

## Dependencies

- Spring Boot 2.7.18
- Spring Web Services
- JAXB for XML binding
- Maven for build management




export async function POST(request: Request) {
 try {
   const {username , password } = await request.json();
   const res = await fetch(`${process.env}`) 
 } catch (error) {
   
 }  
}]



curl -X POST "http://localhost:8443/soap-pret/ws" \
  -H 'Content-Type: application/soap+xml' \
  -d '<?xml version="1.0" encoding="UTF-8"?>
  <env:Envelope xmlns:env="http://www.w3.org/2001/12/soap-envelope">
      <env:Body>
          <getPetByIdRequest xmlns="urn:com:example:petstore">
              <id>3</id>
          </getPetByIdRequest>
      </env:Body>
  </env:Envelope>'
  
  curl -X POST http://localhost:8080/pets/ \
  -H 'Content-Type: application/soap+xml' \
  -d '<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope"
               xmlns:pet="urn:com:example:petstore">
  <soap:Body>
    <pet:getPetByIdRequest>
      <pet:id>3</pet:id>
    </pet:getPetByIdRequest>
  </soap:Body>
</soap:Envelope>'


<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pret="http://soap.example.com/pret">
  <soapenv:Header/>
  <soapenv:Body>
    <pret:pretRequest>
      <pret:nom>Ali</pret:nom>
      <pret:age>30</pret:age>
      <pret:nationalite>Marocaine</pret:nationalite>
    </pret:pretRequest>
  </soapenv:Body>
</soapenv:Envelope>

#!/bin/bash

# SOAP Pret Service Test Script
# Make sure the service is running on localhost:8080

SERVICE_URL="http://localhost:8443/soap-pret/ws"
CONTENT_TYPE="text/xml; charset=utf-8"

echo "Testing SOAP Pret Service..."
echo "=============================="
echo

# Test 1: Eligible request
echo "Test 1: Eligible applicant (age=25, nationality=marocan)"
echo "-------------------------------------------------------"
curl -X POST \
  ${SERVICE_URL} \
  -H "Content-Type: ${CONTENT_TYPE}" \
  -H 'SOAPAction: ""' \
  -d @test-requests/eligible-request.xml
echo
echo

# Test 2: Age not appropriate
echo "Test 2: Age not appropriate (age=17)"
echo "------------------------------------"
curl -X POST \
  ${SERVICE_URL} \
  -H "Content-Type: ${CONTENT_TYPE}" \
  -H 'SOAPAction: ""' \
  -d @test-requests/age-not-appropriate-request.xml
echo
echo

# Test 3: Wrong nationality
echo "Test 3: Wrong nationality (nationality=american)"
echo "-----------------------------------------------"
curl -X POST \
  ${SERVICE_URL} \
  -H "Content-Type: ${CONTENT_TYPE}" \
  -H 'SOAPAction: ""' \
  -d @test-requests/wrong-nationality-request.xml
echo
echo

echo "Testing completed!"
echo "WSDL available at: ${SERVICE_URL//\/ws/}/pret.wsdl"
