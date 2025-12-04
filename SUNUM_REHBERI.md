# 🎯 Milestone 4 - Sunum Rehberi

## 📋 Sunum Öncesi Hazırlık (5 dakika)

### 1. Docker'ın Çalıştığını Kontrol Et
```powershell
docker --version
docker compose --version
```

### 2. Proje Klasörüne Git
```powershell
cd "D:\School 2025\SDT\FishFace34-Software-Design-Techniques-1baf017\FishFace34-Software-Design-Techniques-1baf017\Milestone4\microservices-platform"
```

### 3. Servislerin Çalışmadığından Emin Ol
```powershell
# Eğer çalışıyorsa durdur
docker compose down
```

---

## 🚀 ADIM 1: Projeyi Başlat (2-3 dakika)

### 1.1 Docker Compose ile Servisleri Başlat
```powershell
docker compose up --build
```

**Beklenen Çıktı:**
- ✅ Building listing-service...
- ✅ Building search-service...
- ✅ Building notification-service...
- ✅ listing-service started on port 8081
- ✅ search-service started on port 8082
- ✅ notification-service started on port 8083

**⚠️ İlk build 5-10 dakika sürebilir!**

### 1.2 Servislerin Başladığını Doğrula
**Yeni bir PowerShell penceresi açın ve:**
```powershell
# Health check'ler
Invoke-RestMethod -Uri "http://localhost:8081/actuator/health"
Invoke-RestMethod -Uri "http://localhost:8082/actuator/health"
Invoke-RestMethod -Uri "http://localhost:8083/actuator/health"
```

**Beklenen:** `{"status":"UP"}` veya benzeri

---

## 🎤 ADIM 2: Projeyi Tanıt (2-3 dakika)

### 2.1 Mimariyi Açıkla
**Söyle:**
> "Bu proje, B2B Land Listing Platform'unun microservices mimarisiyle implementasyonudur. 
> 3 bağımsız servis içeriyor:
> 1. **Listing Service** - İlan yönetimi ve state transitions
> 2. **Search Service** - Arama ve filtreleme
> 3. **Notification Service** - Bildirim yönetimi"

### 2.2 Tasarım Desenlerini Göster
**README.md'yi aç ve göster:**
- ✅ **State Pattern** - Listing lifecycle (Draft → PendingReview → Published → UnderOffer)
- ✅ **Builder Pattern** - Listing oluşturma
- ✅ **Strategy Pattern** - Sıralama algoritmaları (Price, Recency)
- ✅ **Observer Pattern** - Notification sistemi (HTTP-based)

---

## 🧪 ADIM 3: API'leri Test Et (5-7 dakika)

### 3.1 Listing Oluştur
**PowerShell'de:**
```powershell
$listing = Invoke-RestMethod -Uri "http://localhost:8081/api/listings" `
    -Method POST `
    -ContentType "application/json" `
    -Body (@{
        title = "Prime Commercial Land"
        location = "Istanbul, Turkey"
        surface = 5000.0
        zoning = "Commercial"
        priceAmount = 2500000.0
        currency = "USD"
    } | ConvertTo-Json)

Write-Host "Created Listing ID: $($listing.id)" -ForegroundColor Green
Write-Host "State: $($listing.state)" -ForegroundColor Yellow
```

**Beklenen:**
- ✅ Listing ID oluşturuldu
- ✅ State: "DraftState"

### 3.2 State Transition Göster
```powershell
$listingId = $listing.id

# Review'a gönder
Invoke-RestMethod -Uri "http://localhost:8081/api/listings/$listingId/submit-for-review" `
    -Method POST | Out-Null
Write-Host "✅ Submitted for review" -ForegroundColor Yellow

# Yayınla
Invoke-RestMethod -Uri "http://localhost:8081/api/listings/$listingId/publish" `
    -Method POST | Out-Null
Write-Host "✅ Published" -ForegroundColor Green
```

**Açıkla:**
> "State Pattern kullanarak listing'in durumunu değiştiriyoruz. 
> Her state transition'da Notification Service'e bildirim gönderiliyor."

### 3.3 Search Service'i Test Et
```powershell
# Price ascending
$results = Invoke-RestMethod -Uri "http://localhost:8082/api/search?sortBy=price_asc" -Method GET
Write-Host "Search results: $($results.Count) listings" -ForegroundColor Cyan

# Recency
$results = Invoke-RestMethod -Uri "http://localhost:8082/api/search?sortBy=recency" -Method GET
Write-Host "Sorted by recency: $($results.Count) listings" -ForegroundColor Cyan
```

**Açıkla:**
> "Search Service, Listing Service'ten veri çekiyor ve Strategy Pattern ile sıralama yapıyor."

### 3.4 Notification'ları Göster
```powershell
$notifications = Invoke-RestMethod -Uri "http://localhost:8083/api/notifications" -Method GET
$notifications | Format-Table -AutoSize
```

**Açıkla:**
> "Notification Service, Listing Service'ten gelen event'leri alıyor ve saklıyor. 
> Bu, servisler arası iletişimin çalıştığını gösteriyor."

---

## 🐳 ADIM 4: Docker'ı Göster (2-3 dakika)

### 4.1 Docker Compose Dosyasını Aç
**`docker-compose.yml` dosyasını göster:**
```yaml
services:
  listing-service:
    ports: "8081:8081"
  search-service:
    ports: "8082:8082"
  notification-service:
    ports: "8083:8083"
```

**Açıkla:**
> "Her servis ayrı bir container'da çalışıyor. 
> Servisler arası iletişim Docker network üzerinden yapılıyor."

### 4.2 Container'ları Göster
```powershell
docker ps
```

**Beklenen:** 3 container çalışıyor

---

## 📦 ADIM 5: Postman Collection'ı Göster (1-2 dakika)

### 5.1 Postman Collection'ı Aç
**`postman/B2B_Land_Listing.postman_collection.json` dosyasını göster**

**Açıkla:**
> "Tüm API endpoint'leri için Postman collection hazırladım. 
> Bu collection ile tüm özellikler test edilebilir."

---

## 📚 ADIM 6: README.md'yi Göster (1-2 dakika)

### 6.1 README.md'yi Aç
**Önemli bölümleri göster:**
- ✅ Proje yapısı
- ✅ Servislerin sorumlulukları
- ✅ Docker kurulum talimatları
- ✅ API endpoint'leri
- ✅ Tasarım desenleri

---

## ❓ ADIM 7: Sorulara Hazır Ol

### Olası Sorular ve Cevaplar:

**Q: Neden microservices?**
> A: "Monolithic yapıdan microservices'e geçerek, her servisi bağımsız olarak scale edebilir, 
> deploy edebilir ve maintain edebiliriz. Ayrıca her servis farklı teknoloji stack'i kullanabilir."

**Q: Servisler arası iletişim nasıl?**
> A: "HTTP REST API kullanıyoruz. Listing Service, Notification Service'e HTTP POST ile bildirim gönderiyor. 
> Search Service, Listing Service'ten HTTP GET ile veri çekiyor."

**Q: Error handling nasıl?**
> A: "RestTemplate kullanarak inter-service communication yapıyoruz. 
> Hata durumlarında servisler birbirini etkilemeden çalışmaya devam edebilir."

**Q: Database nerede?**
> A: "Şu anda in-memory storage kullanıyoruz. Production'da her servis kendi database'ine sahip olabilir."

**Q: Design patterns nerede kullanıldı?**
> A: "State Pattern - Listing lifecycle, Builder Pattern - Listing oluşturma, 
> Strategy Pattern - Sıralama algoritmaları, Observer Pattern - Notification sistemi."

---

## 🎯 ADIM 8: Özet ve Kapanış (1 dakika)

### Özetle:
1. ✅ **3 bağımsız servis** - Listing, Search, Notification
2. ✅ **Inter-service communication** - HTTP REST
3. ✅ **Docker containerization** - docker-compose ile orchestration
4. ✅ **Postman collection** - Tüm endpoint'ler için test
5. ✅ **Design patterns** - State, Builder, Strategy, Observer
6. ✅ **Comprehensive README** - Detaylı dokümantasyon

---

## ⚠️ Sorun Giderme (Acil Durumlar)

### Servisler Başlamıyorsa:
```powershell
# Container'ları temizle
docker compose down -v
docker system prune -f

# Tekrar başlat
docker compose up --build
```

### Port Zaten Kullanılıyorsa:
```powershell
# Port'ları kontrol et
netstat -ano | findstr :8081
netstat -ano | findstr :8082
netstat -ano | findstr :8083

# Eğer kullanılıyorsa, docker compose down yap
```

### Build Hatası:
```powershell
# Log'ları kontrol et
docker compose logs listing-service
docker compose logs search-service
docker compose logs notification-service
```

---

## 📝 Sunum Checklist

### Sunum Öncesi:
- [ ] Docker Desktop çalışıyor
- [ ] Proje klasörüne gidildi
- [ ] Eski container'lar temizlendi
- [ ] README.md açık
- [ ] Postman collection hazır
- [ ] PowerShell penceresi açık

### Sunum Sırasında:
- [ ] Servisler başlatıldı
- [ ] Health check'ler yapıldı
- [ ] Listing oluşturuldu
- [ ] State transition gösterildi
- [ ] Search test edildi
- [ ] Notification'lar gösterildi
- [ ] Docker gösterildi
- [ ] README gösterildi

### Sunum Sonrası:
- [ ] Sorular cevaplandı
- [ ] Container'lar durduruldu (opsiyonel)

---

## 🎉 Başarılar Dilerim!

**Toplam Sunum Süresi:** ~15-20 dakika

**Hazırlık Süresi:** 5 dakika

**Toplam:** ~25 dakika

**Not:** İlk build 5-10 dakika sürebilir, bu yüzden sunumdan önce bir kez build edip test etmen önerilir!

