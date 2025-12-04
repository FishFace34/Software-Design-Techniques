# GitHub'a Yükleme Rehberi

## ⚠️ Durum
Commit başarılı! Ancak push için authentication gerekiyor.

## 🔐 Çözüm 1: Personal Access Token (Önerilen)

### 1. GitHub'da Token Oluştur
1. GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. "Generate new token (classic)" tıkla
3. **Note:** "Milestone4 Upload"
4. **Expiration:** 90 days (veya istediğin süre)
5. **Scopes:** `repo` seç (tüm repo yetkileri)
6. "Generate token" tıkla
7. **Token'ı kopyala** (bir daha gösterilmeyecek!)

### 2. Token ile Push Yap
```powershell
# Remote URL'i token ile güncelle
git remote set-url origin https://TOKEN@github.com/FishFace34/Software-Design-Techniques.git

# Push yap
git push -u origin main
```

**Örnek:**
```powershell
git remote set-url origin https://ghp_xxxxxxxxxxxx@github.com/FishFace34/Software-Design-Techniques.git
git push -u origin main
```

---

## 🔐 Çözüm 2: GitHub CLI (gh)

### 1. GitHub CLI Kur
```powershell
winget install --id GitHub.cli
```

### 2. Login Ol
```powershell
gh auth login
```

### 3. Push Yap
```powershell
git push -u origin main
```

---

## 🔐 Çözüm 3: SSH Key

### 1. SSH Key Oluştur
```powershell
ssh-keygen -t ed25519 -C "your_email@example.com"
```

### 2. Public Key'i GitHub'a Ekle
1. `cat ~/.ssh/id_ed25519.pub` ile public key'i göster
2. GitHub → Settings → SSH and GPG keys → New SSH key
3. Key'i yapıştır ve kaydet

### 3. Remote URL'i SSH ile Değiştir
```powershell
git remote set-url origin git@github.com:FishFace34/Software-Design-Techniques.git
```

### 4. Push Yap
```powershell
git push -u origin main
```

---

## ✅ Mevcut Durum

✅ **Commit başarılı:** 62 dosya, 3549 satır eklendi
✅ **Branch:** main
❌ **Push:** Authentication gerekiyor

## 📝 Hızlı Komutlar

```powershell
# Durumu kontrol et
git status
git log --oneline

# Remote'u kontrol et
git remote -v

# Token ile push (Çözüm 1)
git remote set-url origin https://TOKEN@github.com/FishFace34/Software-Design-Techniques.git
git push -u origin main
```

---

## 🎯 Önerilen Yöntem

**Personal Access Token** en kolay ve hızlı yöntemdir.

1. GitHub'da token oluştur (5 dakika)
2. Remote URL'i güncelle
3. Push yap

**Hazır! 🚀**

