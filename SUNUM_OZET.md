# 🎯 Sunum Özeti - Hızlı Referans

## ⚡ Hızlı Başlangıç (5 dk)

```powershell
# 1. Proje klasörüne git
cd "D:\School 2025\SDT\FishFace34-Software-Design-Techniques-1baf017\FishFace34-Software-Design-Techniques-1baf017\Milestone4\microservices-platform"

# 2. Servisleri başlat
docker compose up --build

# 3. Yeni PowerShell penceresi - Health check
Invoke-RestMethod -Uri "http://localhost:8081/actuator/health"
Invoke-RestMethod -Uri "http://localhost:8082/actuator/health"
Invoke-RestMethod -Uri "http://localhost:8083/actuator/health"
```

## 🧪 Test Komutları

```powershell
# Listing Oluştur
$listing = Invoke-RestMethod -Uri "http://localhost:8081/api/listings" `
    -Method POST -ContentType "application/json" `
    -Body (@{title="Test";location="Istanbul";surface=5000.0;zoning="Commercial";priceAmount=1000000.0;currency="USD"} | ConvertTo-Json)

# Review'a Gönder
Invoke-RestMethod -Uri "http://localhost:8081/api/listings/$($listing.id)/submit-for-review" -Method POST

# Yayınla
Invoke-RestMethod -Uri "http://localhost:8081/api/listings/$($listing.id)/publish" -Method POST

# Ara
Invoke-RestMethod -Uri "http://localhost:8082/api/search?sortBy=recency" -Method GET

# Bildirimler
Invoke-RestMethod -Uri "http://localhost:8083/api/notifications" -Method GET
```

## 📋 Sunum Sırası

1. ✅ **Mimariyi Açıkla** (2 dk) - 3 servis, sorumluluklar
2. ✅ **Servisleri Başlat** (2 dk) - docker compose up
3. ✅ **API Test Et** (5 dk) - Listing, State, Search, Notification
4. ✅ **Docker Göster** (2 dk) - docker-compose.yml, container'lar
5. ✅ **Postman Göster** (1 dk) - Collection dosyası
6. ✅ **README Göster** (1 dk) - Dokümantasyon
7. ✅ **Sorular** (5 dk)

**Toplam: ~18 dakika**

## 🎯 Ana Noktalar

- ✅ 3 bağımsız microservice
- ✅ Inter-service HTTP communication
- ✅ Docker containerization
- ✅ 4 Design Pattern (State, Builder, Strategy, Observer)
- ✅ Postman collection
- ✅ Comprehensive README

## ⚠️ Acil Durum

```powershell
# Servisler başlamıyorsa
docker compose down -v
docker compose up --build
```

**Detaylı rehber:** `SUNUM_REHBERI.md`

