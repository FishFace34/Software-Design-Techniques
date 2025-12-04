# IntelliJ IDEA'da Yeni Microservices Projesi Oluşturma Rehberi

## Adım 1: IntelliJ IDEA'yı Açın

## Adım 2: Yeni Proje Oluşturun

1. **File** → **New** → **Project**
2. Sol taraftan **"Maven"** seçin
3. **Project SDK:** Java 17 seçin (yoksa "Download JDK" ile indirin)
4. **Next** tıklayın

## Adım 3: Proje Bilgileri

- **GroupId:** `com.b2b`
- **ArtifactId:** `microservices-platform`
- **Version:** `1.0.0`
- **Project name:** `microservices-platform`
- **Project location:** İstediğiniz konum (örn: `D:\School 2025\SDT\FishFace34-Software-Design-Techniques-1baf017\FishFace34-Software-Design-Techniques-1baf017\microservices-platform`)
- **Next** → **Finish**

## Adım 4: Proje Yapısını Oluşturun

Proje oluşturulduktan sonra, 3 ayrı modül (module) oluşturacağız:

### 4.1. Listing Service Modülü Ekle

1. **File** → **New** → **Module**
2. **Maven** seçin
3. **GroupId:** `com.b2b`
4. **ArtifactId:** `listing-service`
5. **Version:** `1.0.0`
6. **Next** → **Finish**

### 4.2. Search Service Modülü Ekle

1. **File** → **New** → **Module**
2. **Maven** seçin
3. **GroupId:** `com.b2b`
4. **ArtifactId:** `search-service`
5. **Version:** `1.0.0`
6. **Next** → **Finish**

### 4.3. Notification Service Modülü Ekle

1. **File** → **New** → **Module**
2. **Maven** seçin
3. **GroupId:** `com.b2b`
4. **ArtifactId:** `notification-service`
5. **Version:** `1.0.0`
6. **Next** → **Finish**

## Adım 5: Her Modüle Spring Boot Bağımlılıklarını Ekleyin

Her modülün `pom.xml` dosyasına Spring Boot parent ve bağımlılıkları ekleyin.

### Listing Service pom.xml:
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
    <artifactId>listing-service</artifactId>
    <version>1.0.0</version>
    <description>B2B Land Listing Platform - Listing Management Service</description>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Search Service pom.xml:
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
    <artifactId>search-service</artifactId>
    <version>1.0.0</version>
    <description>B2B Land Listing Platform - Search and Filter Service</description>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

### Notification Service pom.xml:
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
    <artifactId>notification-service</artifactId>
    <version>1.0.0</version>
    <description>B2B Land Listing Platform - Notification Service</description>

    <properties>
        <java.version>17</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

## Adım 6: Maven Bağımlılıklarını İndirin

1. Her modülün `pom.xml` dosyasını açın
2. IntelliJ sağ üstte "Maven" sekmesini açın (yoksa View → Tool Windows → Maven)
3. Her modül için **"Reload All Maven Projects"** (🔄) butonuna tıklayın
4. Bağımlılıklar otomatik indirilecek

## Adım 7: Proje Yapısını Oluşturun

Her modül için şu klasör yapısını oluşturun:

### Listing Service:
```
listing-service/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/b2b/listingservice/
│       │       ├── ListingServiceApplication.java
│       │       ├── controller/
│       │       ├── service/
│       │       ├── domain/
│       │       ├── dto/
│       │       ├── state/
│       │       ├── builder/
│       │       └── client/
│       └── resources/
│           └── application.properties
```

### Search Service:
```
search-service/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/b2b/searchservice/
│       │       ├── SearchServiceApplication.java
│       │       ├── controller/
│       │       ├── service/
│       │       ├── strategy/
│       │       ├── client/
│       │       └── dto/
│       └── resources/
│           └── application.properties
```

### Notification Service:
```
notification-service/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/b2b/notificationservice/
│       │       ├── NotificationServiceApplication.java
│       │       ├── controller/
│       │       ├── service/
│       │       ├── domain/
│       │       └── dto/
│       └── resources/
│           └── application.properties
```

## Adım 8: Application Properties Dosyaları

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

## Adım 9: İlk Application Sınıflarını Oluşturun

### ListingServiceApplication.java:
```java
package com.b2b.listingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ListingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListingServiceApplication.class, args);
    }
}
```

### SearchServiceApplication.java:
```java
package com.b2b.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}
```

### NotificationServiceApplication.java:
```java
package com.b2b.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
```

## Adım 10: Run Configuration Oluşturun

1. Her `*Application.java` dosyasına sağ tıklayın
2. **"Run 'ApplicationName'"** seçin
3. Veya **Run** → **Edit Configurations** → **+** → **Application**
   - Name: `Listing Service`
   - Main class: `com.b2b.listingservice.ListingServiceApplication`
   - Module: `listing-service`
   - Aynısını diğer servisler için de yapın

## Adım 11: Servisleri Çalıştırın

1. Her servisi ayrı ayrı çalıştırın (Run butonuna tıklayın)
2. Veya tümünü aynı anda çalıştırmak için:
   - **Run** → **Edit Configurations**
   - **+** → **Compound**
   - Tüm servisleri ekleyin
   - **OK** → **Run**

## Sonraki Adımlar

Proje yapısı hazır olduktan sonra, kodları ekleyeceğiz. Bana haber verin, devam edelim!

