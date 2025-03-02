**Ride Sharing Microservices**

This guide explains how to set up and run Ride Sharing Microservices Architecture, including **Eureka Server, API Gateway, and individual microservices (Passenger, Driver, Billing).**

---

## **1. Prerequisites**  
Before running the services, ensure you have the following installed:  

- **Java 17+**  
- **Maven 3.6+**  
- **Docker (optional, for PostgreSQL)**  
- **PostgreSQL (if used as a database)**  

---

## **2. Clone the Repository**  
sh
git clone https://github.com/your-repo-name.git
cd your-repo-name

---

## **3. Build & Install Common Module**  
Since all services depend on the `common` module, build and install it first:  
sh
cd common
mvn clean install
✅ This ensures the `common-0.0.1.jar` is available for other services.  

---

## **4. Start Eureka Server**  
Eureka acts as a service registry, allowing microservices to discover each other.  
sh
cd eureka-server
mvn clean install
mvn spring-boot:run
✅ Eureka should now be available at: [http://localhost:8761](http://localhost:8761)  

---

## **5. Start API Gateway**  
Spring Cloud Gateway handles API routing for microservices.  
sh
cd gateway
mvn clean install
mvn spring-boot:run
✅ The gateway should now be running at: [http://localhost:8080](http://localhost:8080)  

---

## **6. Start Microservices**  
Each microservice (Passenger, Driver, Billing) must be started separately.  

### **Passenger Service**  
sh
cd passenger-service
mvn clean install
mvn spring-boot:run
✅ Available via Gateway: `http://localhost:8081/passenger/**`  

### **Driver Service**  
sh
cd driver-service
mvn clean install
mvn spring-boot:run
✅ Available via Gateway: `http://localhost:8082/driver/**`  

### **Billing Service**  
sh
cd driver-service
mvn clean install
mvn spring-boot:run

✅ Available via Gateway: `http://localhost:8082/driver/**`  

### **Billing Service**  
sh
cd billing-service
mvn clean install
mvn spring-boot:run
✅ Available via Gateway: `http://localhost:8083/billing/**`  

---

## **7. Verify Eureka Registration**  
Visit [http://localhost:8761](http://localhost:8761) and check if all services are registered.  

---

## **8. Test API Gateway Routing**  
Try calling the microservices via the Gateway:  
sh
curl http://localhost:8081/passenger/test
curl http://localhost:8082/driver/test
curl http://localhost:8083/billing/test
✅ If responses are received, the setup is complete! 🎉  

---

## **Troubleshooting**  
- If a service fails to register in Eureka, **check its `application.yml`** for correct Eureka configuration.  
- If you get `Cannot execute request on any known server`, make sure **Eureka Server is running before other services**.  
- If `common` module isn’t found, run:  

sh
mvn clean install -U

---

## **Conclusion**  
You have successfully set up a **Spring Cloud microservices architecture** using Eureka and Gateway. 🚀  

---

