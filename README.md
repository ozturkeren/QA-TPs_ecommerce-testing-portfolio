# 🛒 E-Commerce Testing Portfolio Project

[![CI](https://github.com/<YOUR_USERNAME>/ecommerce-testing-portfolio/actions/workflows/ci.yml/badge.svg)](https://github.com/<YOUR_USERNAME>/ecommerce-testing-portfolio/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

This project is part of my **QA Test Engineering portfolio**.  
It demonstrates skills in **manual testing**, **UI automation with Selenium**, **assertion design with AssertJ**, **reporting with Allure**, and **CI/CD integration via GitHub Actions**.

---

## 📌 Project Overview
- Application under test: [SauceDemo](https://www.saucedemo.com/)  
- Scope:
  - Manual test cases for login and checkout
  - Selenium WebDriver automation (Java + Gradle + JUnit 5)
  - Page Object Model (POM) design
  - Fluent assertions with AssertJ
  - Allure reporting with screenshots
  - CI pipeline using GitHub Actions (headless browser execution)

---

## 🛠 Tech Stack
- **Language**: Java 21  
- **Build Tool**: Gradle  
- **Frameworks**: Selenium WebDriver, JUnit 5, AssertJ  
- **Reporting**: Allure  
- **CI/CD**: GitHub Actions  
- **IDE**: IntelliJ IDEA (Community Edition)

---

## ▶️ Running Tests Locally

### Prerequisites
- Java 21 installed and `JAVA_HOME` configured  
- Chrome installed (latest stable)  
- Allure CLI (optional, for local reports)

### Run tests
```bash
# Windows
.\gradlew.bat test

# Linux / macOS
./gradlew test
