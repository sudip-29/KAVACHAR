# 🛡️ KavachAR

> **Offline-first AR safety training and competency certification for industrial workers.**

KavachAR is an Android-based vocational safety training platform designed for workers in **mining, steel, mica-processing, and other industrial environments**.

The platform uses **Jetpack Compose + ARCore** to turn a normal AR-capable smartphone into an interactive safety simulator. Workers can practice emergency procedures in their own surroundings, complete scenario-based assessments, and receive a QR-verifiable competency certificate.

The MVP focuses on two complete AR training modules:

- 🔥 **Fire & Explosion Response**
- ☠️ **Gas Leak & Confined Space Protocol**

The architecture is designed so additional safety domains can be added as content rather than requiring a separate application.

---

## 📋 Table of Contents

- [🎯 Problem](#-problem)
- [💡 Solution](#-solution)
- [🧠 Core Idea](#-core-idea)
- [✨ Features](#-features)
- [👤 User Flow](#-user-flow)
- [🔐 Guest Mode & Certification](#-guest-mode--certification)
- [🧯 MVP Training Modules](#-mvp-training-modules)
- [🎮 Assessment System](#-assessment-system)
- [📊 Competency Model](#-competency-model)
- [📡 Offline-First Architecture](#-offline-first-architecture)
- [🛠️ Technology Stack](#technology-stack)
- [🏗️ Architecture](#architecture)
- [📁 Project Structure](#-project-structure)
- [⚙️ Scenario Engine](#scenario-engine)
- [🌐 Localization](#-localization)
- [🪪 Certificate System](#-certificate-system)
- [📊 Admin Dashboard](#-admin-dashboard)
- [🚀 Setup](#-setup)
- [👷 Usage](#-usage)
- [🎯 MVP Scope](#-mvp-scope)
- [🗺️ Roadmap](#roadmap)
- [🎬 SIH Demo Flow](#-sih-demo-flow)
- [🏆 Why KavachAR](#-why-kavachar)
- [🔒 Security](#-security)
- [🤝 Contributing](#-contributing)
- [📜 License](#-license)
- [🌈 Vision](#-vision)

---

# 🎯 Problem

Industrial safety training often depends on:

```text
📖 Printed Manuals
       +
🎥 Passive Videos
       +
🏫 Classroom Training
       +
🚧 Occasional Physical Drills
````

These approaches can make repeated hands-on practice difficult, especially
when training must be delivered across geographically distributed workplaces.

### The Core Problem

> **Workers may complete safety training without demonstrating that they can actually respond correctly during an emergency.**

Physical drills can also be:

- 💰 Expensive
- ⚙️ Operationally disruptive
- 🔄 Difficult to repeat frequently
- 📋 Difficult to standardize
- 📊 Difficult to measure objectively

KavachAR addresses this gap by combining:

- 🕶️ Interactive AR scenarios
- 🎯 Behaviour-based assessment
- 🧠 Safety competency measurement
- 🔄 Targeted remediation
- 🪪 Digital certification
- 📡 Offline operation
- 🇮🇳 Regional-language support

---

# 💡 Solution

KavachAR follows a simple principle:

> ## **Don't just teach safety. Let workers practice it and prove competency.**

Instead of presenting a static lesson, the application creates a simulated emergency scenario over the user's physical surroundings.

### Example

```text
🔥 Open Fire Safety
        ↓
📱 Scan surroundings
        ↓
🕶️ Start AR emergency scenario
        ↓
🚪 Identify safe exit
        ↓
🧯 Locate extinguisher
        ↓
🎯 Perform correct extinguisher sequence
        ↓
🏃 Complete evacuation procedure
        ↓
📝 Assessment
        ↓
📊 Competency Score
        ↓
🪪 Certificate Eligibility
```

---

# 🧠 Core Idea

The platform is built around four fundamental stages:

```text
                KAVACHAR
                    │
        ┌───────────┼───────────┐
        │           │           │
       🕶️          🧠          🎯
    SIMULATE      LEARN       PROVE
        │           │           │
        └───────────┼───────────┘
                    │
                    ↓
              🪪 CERTIFY
```

### 🕶️ 1. Simulate

Use ARCore to place hazards, equipment, exits, PPE, and other training elements into the worker's environment.

### 🧠 2. Learn

Guide the worker through a structured safety procedure using visual, textual, and optional audio instructions.

### 🎯 3. Prove

Measure:

- Hazard recognition
- Decision making
- Action sequence
- PPE selection
- Response time
- Assessment performance

### 🪪 4. Certify

Issue a permanent competency certificate after the worker passes the required assessment and authenticates their account.

---

# ✨ Features

## 👋 1. Onboarding

- Guest Mode
- Sign Up
- Sign In
- First-time tutorial
- Language selection
- AR capability check

Guest Mode allows workers to explore and train without immediately creating an account.

---

## 🏠 2. Worker Dashboard

The dashboard provides a quick overview of:

- 📈 Current level
- 🎯 Training progress
- 💡 Recommended training
- ⚠️ Hazard-related warnings
- 📚 Recent attempts
- 🪪 Certification status

Example:

```text
┌────────────────────────────────┐
│ Hello, Worker 👋               │
│                                │
│ Safety Readiness               │
│ ████████░░ 82%                 │
│                                │
│ 💡 Recommended                 │
│ ┌────────────────────────────┐ │
│ │ Gas Leak Refresher         │ │
│ │ Reason: PPE errors         │ │
│ │ Start →                    │ │
│ └────────────────────────────┘ │
│                                │
│ 🔥 Fire Response        ✓      │
│ ☠️ Confined Space       →      │
└────────────────────────────────┘
```

---

## 🕶️ 3. AR Safety Training

ARCore is used to create interactive scenarios in the worker's surroundings.

Possible AR elements include:

- 🔥 Fire
- 🌫️ Smoke
- ☠️ Gas leaks
- ⚠️ Hazard zones
- 🚪 Emergency exits
- 🧯 Fire extinguishers
- 🦺 PPE
- 🪧 Safety signs
- ➡️ Evacuation routes
- 🚧 Restricted zones

> **AR should not be decorative. Every AR interaction should represent a safety decision or procedure.**

---

## 🎮 4. Interactive Safety Procedures

Workers don't simply watch a procedure.

They perform it.

Example:

```text
🧯 FIRE EXTINGUISHER

       ↓

1️⃣ PULL PIN
       ↓
2️⃣ AIM
       ↓
3️⃣ SQUEEZE
       ↓
4️⃣ SWEEP
```

Incorrect ordering affects the worker's competency score.

---

## 🎯 5. Behaviour-Based Assessment

The application evaluates what the worker **does**, not just what they know.

Example:

```text
┌─────────────────────────────────┐
│       🏆 SAFETY COMPETENCY       │
├─────────────────────────────────┤
│                                 │
│ Hazard Recognition       90%    │
│ Procedure Accuracy       85%    │
│ PPE Selection           100%    │
│ Response Time            74%    │
│                                 │
│ ─────────────────────────────── │
│ Overall                   88%   │
│                                 │
│ 🟢 STATUS: PASS                 │
└─────────────────────────────────┘
```

---

## 🚨 6. Critical Safety Rules

Not every mistake should be treated equally.

For example:

```text
Overall Score: 87%

🚨 Critical Violation

Entered confined space
without buddy verification.

        ↓

❌ CERTIFICATION BLOCKED

🔄 Remediation Required
```

This prevents a worker from passing simply by compensating for a dangerous procedural mistake with good quiz performance.

---

## 🔄 7. Adaptive Remediation

The system identifies weak competencies and recommends focused micro-training.

Example:

```text
Previous Attempt
────────────────────────

PPE Selection        🔴 55%
Buddy Procedure      🟢 90%
Hazard Recognition   🟢 85%

        ↓

💡 Recommended

"PPE Selection Micro Drill"

Practice only what you got wrong.
```

---

## 🌐 8. Hindi + Santali

KavachAR is designed for regional accessibility.

Supported languages:

- 🇬🇧 English
- 🇮🇳 हिन्दी
- ᱥᱟᱱᱛᱟᱲᱤ Santali

Training can combine:

```text
📝 Short Text
      +
🎨 Icons
      +
🕶️ AR Visuals
      +
🔊 Audio
```

This reduces dependence on long text instructions.

---

## 📡 9. Offline-First

Core training does not require continuous internet connectivity.

Available offline:

- 📚 Training modules
- 🧩 Scenario definitions
- 🧊 AR assets
- 📝 Questions
- 📊 Attempts
- 🏆 Scores
- 📜 Local training history
- 🔄 Pending sync records

When connectivity becomes available, pending records can be synchronized with the backend.

---

## 🪪 10. QR-Based Competency Certificate

A certificate is issued only after:

```text
📚 Training
   ↓
📝 Assessment
   ↓
📊 Required Competency
   ↓
🚨 No Critical Violation
   ↓
🔐 Authenticated Account
   ↓
🪪 Certificate
```

The certificate contains a unique verification identity and QR code.

---

# 👤 User Flow

```text
                         👋 ONBOARDING
                              │
                  ┌───────────┴───────────┐
                  │                       │
               👤 GUEST             🔐 SIGN UP / SIGN IN
                  │                       │
                  └───────────┬───────────┘
                              ↓
                    📖 FIRST-TIME TUTORIAL
                              ↓
                          🏠 DASHBOARD
                              │
               ┌──────────────┼──────────────┐
               ↓              ↓              ↓
             🏠 HOME       📚 TRAINING      👤 USER
               │              │              │
               │       ┌──────┼──────┐       │
               │       ↓      ↓      ↓       │
               │   Tutorial  Quiz   Exam     │
               │                              │
               │                         User Details
               │                         Certificates
               │                         Settings
               │                         History
               │                         Logout
               │
               └──────────────┬──────────────┘
                              ↓
                         🏁 COMPLETION
                              ↓
                         📝 ASSESSMENT
                              ↓
                      📊 COMPETENCY RESULT
                              ↓
                     ┌────────┴────────┐
                     │                 │
                   ❌ FAIL            ✅ PASS
                     │                 │
                🔄 Remediation        ↓
                              🔐 AUTHENTICATED?
                               /           \
                             NO             YES
                             │               │
                        Sign Up /        Generate
                        Sign In         Certificate
                             │               │
                             └──────┬────────┘
                                    ↓
                              🪪 QR CERTIFICATE
                                    ↓
                               ☁️ BACKEND SYNC
```

---

# 🔐 Guest Mode & Certification

Guest Mode is intentionally open for training accessibility.

### Guest users CAN:

- ✅ Explore the application
- ✅ Complete tutorials
- ✅ Run AR training
- ✅ Attempt quizzes
- ✅ Complete exams
- ✅ View scores
- ✅ Complete remediation

### Guest users CANNOT:

- ❌ Permanently issue a certificate
- ❌ Download an official certificate
- ❌ Create a permanent compliance record

> **Official certificates require an authenticated worker identity.**

---

## 🔑 Guest Certification Flow

```text
👤 GUEST
   ↓
📚 Complete Training
   ↓
📝 Pass Assessment
   ↓
🏆 Certificate Ready
   ↓
🔐 Authentication Required
   ↓
┌───────────────┬───────────────┐
│               │               │
↓               ↓               │
📝 SIGN UP      🔑 SIGN IN      │
│               │               │
└───────┬───────┘               │
        ↓                       │
  Authenticate Worker           │
        │                       │
        └──────────┬────────────┘
                   ↓
          🔗 Link Training Attempt
                   ↓
          🪪 Generate Certificate
                   ↓
              📥 Download
                   ↓
              ☁️ Sync
```

The completed training attempt should be stored locally before authentication.

This ensures the worker **does not lose their progress** when creating an account.

---

# 🧯 MVP Training Modules

## 🔥 Module 01 — Fire & Explosion Response

### Scenario

A simulated fire emergency is introduced into the worker's surroundings.

The worker must:

```text
🔥 Identify Fire
      ↓
🚪 Find Safe Exit
      ↓
⚠️ Avoid Hazard Zone
      ↓
🧯 Select Extinguisher
      ↓
🎯 Perform Correct Sequence
      ↓
🏃 Follow Evacuation Procedure
```

### AR Elements

```text
🔥 Fire
🌫️ Smoke
🚪 Exit
🧯 Extinguisher
⚠️ Hazard Zone
➡️ Evacuation Path
```

### Extinguisher Interaction

The MVP can simulate a simplified ordered procedure:

```text
PULL PIN
   ↓
AIM
   ↓
SQUEEZE
   ↓
SWEEP
```

Incorrect ordering can result in:

```text
❌ Incorrect Procedure

Remember:
Follow the correct extinguisher sequence.
```

---

# ☠️ Module 02 — Gas Leak & Confined Space

### Scenario

A simulated gas leak creates a virtual hazardous environment.

The worker must:

```text
☠️ Recognize Gas Hazard
       ↓
🛑 Stop Unsafe Entry
       ↓
🦺 Select PPE
       ↓
💨 Verify Ventilation
       ↓
🧪 Verify Gas Testing
       ↓
👥 Establish Buddy System
       ↓
✅ Safe Entry
```

### Virtual Hazard Zones

```text
🟢 NORMAL ZONE
       │
       ↓
🟡 WARNING ZONE
       │
       ↓
🔴 TOXIC ZONE
```

---

# 🎮 Assessment System

KavachAR supports multiple assessment types.

## 📝 Knowledge Question

```text
Which extinguisher should be selected?

○ Water
○ CO₂
○ Cooking Oil
○ Sand
```

---

## 🔢 Sequence Question

```text
Arrange correctly:

[ SWEEP ]
[ PULL PIN ]
[ AIM ]
[ SQUEEZE ]
```

---

## 🎯 Scenario Decision

```text
You detect gas near a confined space.

What should you do?

❌ Enter quickly
❌ Enter alone
✅ Stop and follow confined-space protocol
❌ Ignore the warning
```

---

## 🕶️ AR Interaction

```text
"Find the nearest safe exit."

           🚪
            ↑
         3.2 m

           👤
         Worker
```

---

# 📊 Competency Model

The worker's final score can be calculated using:

```text
                 🏆 FINAL SCORE
                       │
          ┌────────────┼────────────┐
          ↓            ↓            ↓
         🕶️           📝           🚨
        AR         Knowledge      Critical
    Performance    Assessment      Rules
        60%            30%          10%
```

Example:

```text
┌─────────────────────────────────┐
│       🏆 COMPETENCY RESULT      │
├─────────────────────────────────┤
│                                 │
│ Hazard Recognition       90%    │
│ Procedure Accuracy       85%    │
│ PPE Selection           100%    │
│ Response Time            74%    │
│                                 │
│ ─────────────────────────────── │
│ Overall                   88%   │
│                                 │
│ 🟢 CERTIFICATION ELIGIBLE       │
└─────────────────────────────────┘
```

### Suggested Passing Rule

```text
Overall Score >= 75%
        AND
No Critical Safety Violation
```

The threshold should remain configurable.

---

# 📡 Offline-First Architecture

The application follows an offline-first architecture.

```text
                    📱 Android App
                          │
               ┌──────────┴──────────┐
               │                     │
           🗄️ Local Data          📦 AR Assets
               │                     │
            Room DB              Bundled Files
               │
               ↓
        📝 Training Attempt
               │
               ↓
          🔄 Sync Queue
               │
               ↓
      🌐 Network Available?
           /          \
         ❌            ✅
         │              │
    Keep Local        Upload
                        │
                        ↓
                     ☁️ Backend
```

### Local Entities

Recommended entities:

```text
Worker
TrainingModule
TrainingStep
TrainingAttempt
Answer
SkillScore
MistakeEvent
Certificate
SyncQueue
```

---

<a id="technology-stack"></a>
# 🛠️ Technology Stack

| LayerTechnology    |                                |
| ------------------ | ------------------------------ |
| 📱 Platform        | Android 10+                    |
| 💻 Language        | Kotlin                         |
| 🎨 UI              | Jetpack Compose                |
| 🕶️ AR             | Google ARCore                  |
| 🧊 3D Rendering    | SceneView                      |
| 📦 3D Format       | GLB / GLTF                     |
| 🗄️ Local Database | Room                           |
| ⚙️ Preferences     | DataStore                      |
| 🔄 Background Sync | WorkManager                    |
| 📷 QR              | ML Kit Barcode Scanning        |
| ☁️ Backend         | Firebase / Supabase / REST API |
| 📊 Admin           | Web Dashboard                  |

---

<a id="architecture"></a>
# 🏗️ Architecture

```text
┌─────────────────────────────────────────────┐
│              🎨 UI LAYER                    │
│                                             │
│             Jetpack Compose                 │
└──────────────────────┬──────────────────────┘
                       │
                       ↓
              ViewModel / State
                       │
┌──────────────────────▼──────────────────────┐
│             🧩 FEATURE LAYER                │
│                                             │
│ Onboarding │ Auth │ Dashboard │ Training   │
│ Assessment │ Certificate │ Profile          │
└──────────────────────┬──────────────────────┘
                       │
                       ↓
┌─────────────────────────────────────────────┐
│               ⚙️ CORE LAYER                │
│                                             │
│ Models │ Room │ DataStore │ Network │ Sync │
└──────────────────────┬──────────────────────┘
                       │
              ┌────────┴────────┐
              ↓                 ↓
       🕶️ AR ENGINE        🎯 ASSESSMENT
                              ENGINE
              │                 │
         ARCore /            Scoring /
         SceneView           Questions
              │                 │
              └────────┬────────┘
                       ↓
                 🗄️ Local DB
                       │
                       ↓
                 🔄 Sync Manager
                       │
                       ↓
                    ☁️ Backend
                       │
                       ↓
                 📊 Admin Dashboard
```

---

# 📁 Project Structure

```text
KavachAR/
│
├── 📱 app/
│   └── src/
│
├── ⚙️ core/
│   ├── database/
│   │   ├── dao/
│   │   ├── entity/
│   │   └── AppDatabase.kt
│   │
│   ├── network/
│   │   ├── ApiService.kt
│   │   └── SyncManager.kt
│   │
│   ├── model/
│   │   ├── Worker.kt
│   │   ├── TrainingModule.kt
│   │   ├── TrainingStep.kt
│   │   ├── TrainingAttempt.kt
│   │   ├── SkillScore.kt
│   │   └── Certificate.kt
│   │
│   └── localization/
│
├── 🎨 feature/
│   ├── onboarding/
│   ├── auth/
│   ├── dashboard/
│   ├── training/
│   ├── assessment/
│   ├── certificate/
│   └── profile/
│
├── 🕶️ ar/
│   ├── ARSceneController.kt
│   ├── ScenarioEngine.kt
│   ├── ScenarioParser.kt
│   ├── HazardNode.kt
│   ├── TrainingAction.kt
│   └── AnchorManager.kt
│
├── 📦 assets/
│   ├── scenarios/
│   │   ├── fire.json
│   │   └── confined_space.json
│   │
│   ├── models/
│   │   ├── extinguisher.glb
│   │   ├── fire.glb
│   │   ├── smoke.glb
│   │   ├── exit_sign.glb
│   │   └── ppe.glb
│   │
│   └── audio/
│
├── 📊 admin-dashboard/
│
└── 📄 README.md
```

---

<a id="scenario-engine"></a>
# ⚙️ Scenario Engine

The scenario engine is one of the most important architectural decisions in KavachAR.

Instead of creating separate application logic for every training module, scenarios are represented as data.

```text
              🧠 Scenario Engine
                     │
          ┌──────────┼──────────┐
          ↓          ↓          ↓
        🔥 Fire     ☠️ Gas    ⚙️ Machinery
         JSON        JSON        JSON
          │          │           │
          └──────────┼───────────┘
                     ↓
                Same Engine
```

### Example Scenario

```json
{
  "module": "fire_response",
  "steps": [
    {
      "type": "FIND_TARGET",
      "target": "emergency_exit",
      "timeLimit": 20,
      "score": 10
    },
    {
      "type": "SELECT_OPTION",
      "target": "extinguisher",
      "correct": "CO2",
      "score": 10
    },
    {
      "type": "AR_INTERACTION",
      "target": "extinguisher",
      "action": "PULL_PIN",
      "score": 20
    }
  ]
}
```

### Processing Flow

```text
Scenario JSON
     ↓
ScenarioParser
     ↓
ScenarioEngine
     ↓
Training State
     ├── 🕶️ AR State
     ├── 📝 UI Instruction
     ├── 🎯 Expected Action
     └── 📊 Score
```

### Why This Matters

Adding another training domain becomes:

```text
New Scenario JSON
       +
New 3D Assets
       +
Localized Content
       ↓
🆕 New Training Module
```

This means the five industrial safety domains can become mostly **content expansion** rather than five separate application implementations.

---

# 🌐 Localization

Use Android resource localization instead of hardcoding text.

```text
res/
├── values/
│   └── strings.xml
│
├── values-hi/
│   └── strings.xml
│
└── values-sat/
    └── strings.xml
```

Training scenarios should reference localization keys instead of hardcoding instructions.

Example:

```json
{
  "step": "find_exit",
  "instructionKey": "find_nearest_safe_exit"
}
```

The UI resolves the key using the selected language.

This keeps the same scenario logic reusable across languages.

---

# 🪪 Certificate System

Certificates should be issued only to authenticated workers.

A certificate can contain:

```text
Certificate ID
Worker ID / Reference
Training Module
Competency Score
Issue Date
Validity
Verification Data
QR Code
```

The QR code should contain a compact certificate reference or signed verification payload rather than unnecessary personal information.

### Certificate Flow

```text
📚 Training
      ↓
📝 Assessment
      ↓
📊 Competency Check
      ↓
✅ PASS
      ↓
🔐 Authentication Check
      ↓
🪪 Generate Certificate
      ↓
🔢 Unique Certificate ID
      ↓
📱 QR Code
      ↓
📥 Download
      ↓
☁️ Backend Sync
```

### Verification

```text
📱 Scan QR
     ↓
🔢 Certificate ID
     ↓
🔍 Verification Service
     ↓
┌────────────────────────────┐
│ ✅ VALID CERTIFICATE        │
│                            │
│ Module: Fire Response      │
│ Score: 88%                 │
│ Status: ACTIVE             │
└────────────────────────────┘
```

For production deployment, certificate verification data should be cryptographically signed and validated by the backend.

---

# 📊 Admin Dashboard

The admin dashboard provides a high-level view of worker training and compliance.

## Overview

```text
┌──────────────────┬──────────────────┐
│ 👷 Workers       │ 🪪 Certified     │
│     1,248        │      921         │
└──────────────────┴──────────────────┘

┌──────────────────┬──────────────────┐
│ 🔄 Remediation   │ ❌ Failed       │
│      113         │       64         │
└──────────────────┴──────────────────┘
```

## Competency Analytics

```text
              🔥 Fire   ☠️ Gas   🦺 PPE
────────────────────────────────────────
Mine A          92       61       81
Mine B          76       48       91
Plant C         81       87       72
```

This allows supervisors to identify **which competencies require additional training**.

---

# 🚀 Setup

## Requirements

- Android Studio
- Kotlin
- Android SDK
- Compatible JDK
- Physical Android device for AR testing
- ARCore-supported device for AR mode

> ⚠️ **Important:** Android 10+ does not automatically mean that a device supports ARCore. Test the application on the actual target devices.

---

## 1. Clone the Repository

```bash
git clone <https://github.com/sudip-29/KAVACHAR>

cd KavachAR
```

---

## 2. Open in Android Studio

Open the repository in Android Studio and allow Gradle synchronization to complete.

---

## 3. Configure Secrets

Do not commit:

- ❌ API keys
- ❌ Signing credentials
- ❌ Service-account files
- ❌ Production secrets

Use the project's local/environment configuration for development secrets.

---

## 4. Configure Backend

Configure the backend for:

```text
Authentication
Worker Profiles
Training Attempts
Certificates
Synchronization
Dashboard Statistics
```

The worker training experience should continue functioning when the backend is unavailable.

---

## 5. Build

```bash
./gradlew assembleDebug
```

Or run the `app` configuration directly from Android Studio.

---

# 👷 Usage

## Worker

```text
1. Open KavachAR
2. Select language
3. Continue as Guest OR Sign Up / Sign In
4. Complete first-time tutorial
5. Open Dashboard
6. Select training module
7. Start AR scenario
8. Follow safety procedure
9. Complete assessment
10. View competency result
11. Complete remediation if required
12. Authenticate if using Guest Mode
13. Download certificate
```

---

## 👨‍💼 Supervisor / Verifier

```text
1. Open certificate verification
2. Scan QR code
3. Retrieve certificate
4. Check competency score
5. Verify certificate status
```

---

## 🖥️ Administrator

```text
1. Open Admin Dashboard
2. View worker statistics
3. View training completion
4. Review pass/fail rates
5. Identify weak competencies
6. Monitor certificates
7. Review training history
```

---

# 🎯 MVP Scope

## ✅ Must Have

-  Jetpack Compose application
-  Onboarding
-  Guest Mode
-  Sign Up / Sign In
-  First-time tutorial
-  Worker dashboard
-  Fire & Explosion AR module
-  Gas Leak / Confined Space AR module
-  Scenario-based assessment
-  Competency scoring
-  Hindi localization
-  Santali localization
-  Offline training
-  Authenticated certificate generation
-  QR certificate verification
-  Basic admin dashboard

---

## ⭐ High-Value Enhancements

-  🎯 Hazard Injection Mode
-  🔄 Mistake Replay
-  🧠 Adaptive Micro-Drills
-  🔊 Audio Instructions
-  📊 Competency Heatmap
-  📴 Offline QR verification cache

---

## 🚫 Avoid During the Initial Hackathon Build

Don't spend hackathon time on:

- ❌ Full-body tracking
- ❌ Complex hand tracking
- ❌ Multiplayer AR
- ❌ Custom SLAM
- ❌ Large computer-vision models
- ❌ Blockchain certificates
- ❌ IoT integration
- ❌ Complex physics simulation
- ❌ Five fully polished AR modules

> **Two polished modules + a reusable architecture > five unfinished modules.**

---

<a id="roadmap"></a>
# 🗺️ Roadmap

## 🟢 Phase 1 — SIH MVP

```text
👋 Onboarding
      ↓
🏠 Dashboard
      ↓
🔥 Fire AR
      +
☠️ Gas AR
      ↓
📝 Assessment
      ↓
📊 Competency Score
      ↓
🔐 Authentication
      ↓
🪪 QR Certificate
      ↓
📊 Admin Dashboard
```

---

## 🟡 Phase 2 — Advanced Training

- 🎯 Hazard Injection Mode
- 🔄 Mistake Replay
- 🧠 Adaptive Micro-Drills
- 🔊 Audio-guided training
- 📊 Advanced analytics
- 📴 Offline QR verification

---

## 🔴 Phase 3 — Full Platform

Additional safety domains:

```text
⚙️ Machinery Safety
⚡ Electrical Safety
🦺 PPE Compliance
🧪 Chemical Safety
🚨 Emergency Evacuation
🏥 First Aid
```

Future versions can also introduce a **Safety Officer Scenario Builder** for creating organization-specific training scenarios.

---

# 🎬 SIH Demo Flow

The strongest hackathon demo should demonstrate a complete worker journey instead of simply showing screens.

```text
👷 New Worker
      ↓
ᱥᱟᱱᱛᱟᱲᱤ Selects Language
      ↓
📖 First-Time Tutorial
      ↓
🏠 Dashboard
      ↓
🔥 Fire Emergency
      ↓
🕶️ AR Hazard Appears
      ↓
🚪 Identify Safe Exit
      ↓
🧯 Select Extinguisher
      ↓
🎯 Perform Correct Sequence
      ↓
📝 Assessment
      ↓
🚨 Critical Mistake Detected
      ↓
🔄 Targeted Remediation
      ↓
🎯 Retry
      ↓
🏆 PASS
      ↓
🔐 Sign In / Sign Up
      ↓
🪪 Certificate Generated
      ↓
📱 QR Scanned
      ↓
✅ Certificate Verified
      ↓
📊 Admin Dashboard Updated
```

---

# 🏆 Why KavachAR?

KavachAR is not simply:

> ❌ **"An AR education app"**

It is:

> ### 🕶️ A portable emergency drill simulator

combined with:

> ### 🎯 Behaviour-based competency assessment

and:

> ### 🪪 Digitally verifiable certification

while remaining:

> ### 📡 Offline-first and regionally accessible

---

# 🚨 High-Value Innovation

## 🎯 Hazard Injection Mode

A supervisor can place a virtual hazard into a physical environment.

```text
👨‍💼 Supervisor
       ↓
Select Hazard
       ↓
🔥 Fire / ☠️ Gas / ⚡ Electrical
       ↓
📱 Point Camera
       ↓
📍 Place Hazard
       ↓
👷 Worker Enters
       ↓
🔎 Worker Discovers Hazard
       ↓
🎯 Correct Response Required
       ↓
📊 Competency Score
```

This transforms the platform into:

> ## 🕶️ A Portable Emergency Drill Simulator

rather than simply an AR learning application.

---

# 🔒 Security

Basic security practices:

- 🔒 Never store plaintext passwords.
- 🔑 Never commit API keys or secrets.
- 🌐 Use HTTPS for production API communication.
- 👤 Store only necessary worker information.
- 📱 Do not put unnecessary personal information inside QR codes.
- 🪪 Use unique certificate IDs.
- 🔐 Require authentication before permanent certificate issuance.
- ✍️ Sign certificate verification data on the backend for production.
- 🛡️ Protect admin APIs with role-based authorization.
- ☁️ Validate certificate status server-side when online.

---

# 🤝 Contributing

Contributions are welcome!

Create a feature branch:

```bash
git checkout -b feature/<feature-name>
```

Make changes, test them on an Android device where applicable, and open a pull request.

Before submitting a pull request, verify:

```text
✅ Project builds
✅ Guest Mode works
✅ Offline training works
✅ AR scenarios work
✅ Assessment works
✅ Certificate authentication works
✅ QR verification works
✅ Hindi strings work
✅ Santali strings work
```

---

# 📜 License

Add the project's selected license here.

Example:

```text
MIT License
```

---

# 🌈 Vision

KavachAR aims to transform industrial safety training from:

```text
📖 Passive Learning
       ↓
📝 Course Completion
       ↓
📄 Paper Certificate
```

into:

```text
🕶️ Interactive Practice
       ↓
🎯 Behaviour-Based Assessment
       ↓
📊 Measured Competency
       ↓
🪪 Digitally Verifiable Certification
```

---

# 🛡️ KavachAR — The Safety Competency Platform

### Train Anywhere. Prove Competency. Verify Anywhere.

> **From passive safety lessons to measurable safety behaviour.**

🇮🇳 **Built for safer workplaces.**

```
```

`
`
