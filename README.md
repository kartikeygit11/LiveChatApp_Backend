# 💬 Live Chat App — Spring Boot Backend

**Backend** (Java 17 · Spring Boot 3) | **Realtime** (STOMP‑over‑WebSocket + SockJS) | **Database** (PostgreSQL + JPA) | **Auth** (Spring Security + JWT)  
**Frontend (live)** → https://subtle-cocada-db48a3.netlify.app/ – built with **React + Tailwind CSS + SockJS**

<p align="center">
  <img src="https://raw.githubusercontent.com/yourjhay/simple-chat/master/ChatUi2.png" width="420" alt="Chat UI preview">
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

## 🗂 Project structure

```text
.
├─ src/main/java/com/chat
│  ├─ config/            ← WebSocket & Security configs
│  ├─ domain/            ← JPA entities (User, Room, Message)
│  ├─ repository/        ← Spring‑Data interfaces
│  ├─ service/           ← Business logic
│  └─ controller/        ← REST + STOMP endpoints
├─ src/main/resources
│  ├─ application.properties  ← env‑driven settings
│  └─ static/ (optional docs)
├─ Dockerfile            ← multi‑stage build
├─ pom.xml               ← dependencies & plugins
└─ .mvn/, mvnw*          ← Maven wrapper

## 📡 API & WebSocket

| Protocol | Endpoint                     | Purpose                |
|----------|-----------------------------|------------------------|
| **REST** | `GET /api/rooms`            | List chat rooms        |
| **REST** | `POST /api/rooms`           | Create a room          |
| **REST** | `GET /api/messages/{roomId}`| Paginated history      |
| **WS (STOMP)** | `/ws`                 | Connect SockJS/WebSocket |
| **Topic** | `/topic/rooms/{roomId}`    | Broadcast messages     |

> **Swagger/OpenAPI UI** is auto‑exposed at **`/swagger-ui.html`** (dev profile).

---

