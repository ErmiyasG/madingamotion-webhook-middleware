# 🥋 Mandinga Motion Middleware

A Spring Boot–based middleware service that integrates multiple external activity sources (Booking, BioTrack, Rhythm), aggregates student performance data, and determines promotion eligibility based on business rules.

---

#  Overview

This service:
- Receives webhook events from external systems
- Normalizes incoming data into a unified format
- Aggregates student activity
- Applies business rules within a 24-hour window
- Triggers an outbound webhook when a student is eligible for promotion

---

#  Tech Stack

- Java 21
- Spring Boot 3.x
- Maven
- WebClient (for outbound HTTP calls)
- HMAC SHA256 (for signature validation)

---

# Running the Application Locally

## 1. Prerequisites

Make sure you have installed:
- Java 21
- Maven 3.8+
- Postman (or curl)

---

## 2. Setup Project

```bash
git clone https://github.com/ErmiyasG/madingamotion-webhook-middleware
cd madingamotion

mvn clean install

mvn spring-boot:run
```
# run 
http://localhost:9002

# all webhook requests must include

X-Signature: <HMAC_SHA256_BASE64(payload)>

**secrete key used**

```my-secret-key```

# API endpints
```
POST /webhook/booking

{
"studentId": "1",
"eventType": "RODA"
}
```

```
POST /webhook/biotrack

{
  "studentId": "1",
  "intensity": 85
}
```
```
POST /webhook/rhythm

{
  "studentId": "1",
  "duration": 25
}
```

```
**Required headers**

Content-Type: application/json
X-Signature: <generated_signature>
```