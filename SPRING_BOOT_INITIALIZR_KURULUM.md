# Spring Boot Initializr ile Proje Kurulumu

## Adım 1: IntelliJ IDEA'yı Açın

## Adım 2: Spring Initializr ile Proje Oluşturun

1. **File** → **New** → **Project**
2. Sol taraftan **"Spring Initializr"** seçin
3. **Project SDK:** Java 17 seçin (yoksa "Download JDK" ile indirin)
4. **Next** tıklayın

## Adım 3: Proje Bilgileri

### Ana Proje (Parent Project):
- **Name:** `microservices-platform`
- **Group:** `com.b2b`
- **Artifact:** `microservices-platform`
- **Type:** `Maven`
- **Language:** `Java`
- **Packaging:** `Pom` (Parent POM olacak)
- **Java Version:** `17`
- **Project Metadata:**
  - Group: `com.b2b`
  - Artifact: `microservices-platform`
  - Name: `microservices-platform`
  - Description: `B2B Land Listing Platform - Microservices Architecture`
  - Package name: `com.b2b`
- **Next** tıklayın

### Dependencies:
**Henüz dependency eklemeyin** - Sadece proje yapısını oluşturuyoruz.

- **Next** → **Finish**

## Adım 4: Parent POM'u Düzenleyin

Oluşturulan `pom.xml` dosyasını açın ve şu şekilde düzenleyin:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.0</version>
        <relativePath/>
    </parent>

    <groupId>com.b2b</groupId>
    <artifactId>microservices-platform</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>
    <name>B2B Land Listing Platform</name>
    <description>B2B Land Listing Platform - Microservices Architecture</description>

    <properties>
        <java.version>17</java.version>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <modules>
        <!-- Modüller buraya eklenecek -->
    </modules>

</project>
```

## Adım 5: Listing Service Modülünü Oluşturun

1. **File** → **New** → **Module**
2. **Spring Initializr** seçin
3. **Next** tıklayın

### Listing Service Bilgileri:
- **Name:** `listing-service`
- **Group:** `com.b2b`
- **Artifact:** `listing-service`
- **Type:** `Maven`
- **Language:** `Java`
- **Packaging:** `Jar`
- **Java Version:** `17`
- **Project Metadata:**
  - Group: `com.b2b`
  - Artifact: `listing-service`
  - Name: `listing-service`
  - Description: `Listing Management Service`
  - Package name: `com.b2b.listingservice`
- **Next** tıklayın

### Dependencies (Listing Service için):
- **Spring Web** (Web)
- **Spring Boot Actuator** (Ops)
- **Validation** (I/O)
- **Next** → **Finish**

## Adım 6: Search Service Modülünü Oluşturun

1. **File** → **New** → **Module**
2. **Spring Initializr** seçin
3. **Next** tıklayın

### Search Service Bilgileri:
- **Name:** `search-service`
- **Group:** `com.b2b`
- **Artifact:** `search-service`
- **Type:** `Maven`
- **Language:** `Java`
- **Packaging:** `Jar`
- **Java Version:** `17`
- **Project Metadata:**
  - Group: `com.b2b`
  - Artifact: `search-service`
  - Name: `search-service`
  - Description: `Search and Filter Service`
  - Package name: `com.b2b.searchservice`
- **Next** tıklayın

### Dependencies (Search Service için):
- **Spring Web** (Web)
- **Spring Boot Actuator** (Ops)
- **Spring Reactive Web** (Web) - WebFlux için
- **Next** → **Finish**

## Adım 7: Notification Service Modülünü Oluşturun

1. **File** → **New** → **Module**
2. **Spring Initializr** seçin
3. **Next** tıklayın

### Notification Service Bilgileri:
- **Name:** `notification-service`
- **Group:** `com.b2b`
- **Artifact:** `notification-service`
- **Type:** `Maven`
- **Language:** `Java`
- **Packaging:** `Jar`
- **Java Version:** `17`
- **Project Metadata:**
  - Group: `com.b2b`
  - Artifact: `notification-service`
  - Name: `notification-service`
  - Description: `Notification Service`
  - Package name: `com.b2b.notificationservice`
- **Next** tıklayın

### Dependencies (Notification Service için):
- **Spring Web** (Web)
- **Spring Boot Actuator** (Ops)
- **Next** → **Finish**

## Adım 8: Parent POM'a Modülleri Ekleyin

`microservices-platform/pom.xml` dosyasını açın ve `<modules>` bölümünü güncelleyin:

```xml
<modules>
    <module>listing-service</module>
    <module>search-service</module>
    <module>notification-service</module>
</modules>
```

## Adım 9: Maven Projelerini Reload Edin

1. Sağ üstte **Maven** sekmesini açın (yoksa: **View** → **Tool Windows** → **Maven**)
2. **Reload All Maven Projects** (🔄) butonuna tıklayın
3. Bağımlılıkların indirilmesini bekleyin

## Adım 10: Application Properties Dosyalarını Oluşturun

### listing-service/src/main/resources/application.properties:
```properties
server.port=8081
spring.application.name=listing-service
notification.service.url=http://localhost:8083
management.endpoints.web.exposure.include=health,info
```

### search-service/src/main/resources/application.properties:
```properties
server.port=8082
spring.application.name=search-service
listing.service.url=http://localhost:8081
management.endpoints.web.exposure.include=health,info
```

### notification-service/src/main/resources/application.properties:
```properties
server.port=8083
spring.application.name=notification-service
management.endpoints.web.exposure.include=health,info
```

## Adım 11: Proje Yapısını Kontrol Edin

Proje yapısı şöyle olmalı:

```
microservices-platform/
├── pom.xml (Parent POM)
├── listing-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/b2b/listingservice/
│           │       └── ListingServiceApplication.java
│           └── resources/
│               └── application.properties
├── search-service/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/b2b/searchservice/
│           │       └── SearchServiceApplication.java
│           └── resources/
│               └── application.properties
└── notification-service/
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── com/b2b/notificationservice/
            │       └── NotificationServiceApplication.java
            └── resources/
                └── application.properties
```

## Adım 12: İlk Test - Servisleri Çalıştırın

Her `*Application.java` dosyasına sağ tıklayıp **"Run"** yapın:

1. **ListingServiceApplication** → Run
2. **SearchServiceApplication** → Run (yeni terminal/run configuration)
3. **NotificationServiceApplication** → Run (yeni terminal/run configuration)

Tarayıcıda test edin:
- http://localhost:8081/actuator/health
- http://localhost:8082/actuator/health
- http://localhost:8083/actuator/health

## ✅ Kurulum Tamamlandı!

Artık proje yapısı hazır. Sonraki adımlarda:
1. Domain modellerini ekleyeceğiz
2. State pattern'i implement edeceğiz
3. Builder pattern'i ekleyeceğiz
4. REST Controller'ları oluşturacağız
5. Servisler arası iletişimi kuracağız

**Kurulumu tamamladıktan sonra bana haber verin, devam edelim!** 🚀

