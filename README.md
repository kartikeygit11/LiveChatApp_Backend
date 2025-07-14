# 💬 Live Chat App — Spring Boot Backend

**Backend** (Java 17 · Spring Boot 3) | **Realtime** (STOMP‑over‑WebSocket + SockJS) | **Database** (PostgreSQL + JPA) | **Auth** (Spring Security + JWT)  
**Frontend (live)** → https://subtle-cocada-db48a3.netlify.app/ – built with **React + Tailwind CSS + SockJS**

<p align="center">
  <img src="./Screenshot 2025-07-15 001407.png" width="800" alt="Chat UI preview">
</p>

---

## ✨ Why this project?

* **Instant messaging** via WebSocket with SockJS fallback  
* **Stateless security** – JWT + Spring Security  
* **Persistent history** stored in PostgreSQL through Spring Data JPA  
* **Container‑ready** – multi‑stage Dockerfile, Java 23 JRE image :contentReference[oaicite:0]{index=0}  
* **12‑factor friendly** – every secret is an env‑var (see `.env.example`) :contentReference[oaicite:1]{index=1}  

---

## 🔧 Tech stack

| Layer | Main libs / tools |
|-------|------------------|
| **Core** | Spring Boot 3.5, Java 17 (Temurin) |
| **Web** | spring‑boot‑starter‑web, spring‑boot‑starter‑websocket :contentReference[oaicite:2]{index=2} |
| **Data** | Spring Data JPA, PostgreSQL driver :contentReference[oaicite:3]{index=3} |
| **Security** | Spring Security 6, JWT (io.jsonwebtoken) |
| **Build & CI** | Maven Wrapper, Docker multi‑stage :contentReference[oaicite:4]{index=4} |
| **Frontend (separate repo/deploy)** | React 18, Tailwind CSS, SockJS client |

---



