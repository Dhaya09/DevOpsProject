# 🚀 DevOps Automation for Java Financial Calculator

This project demonstrates a complete DevOps pipeline that automates the build, test, dockerization, deployment, monitoring, and rollback of a CLI-based Java Financial Calculator. It integrates multiple DevOps tools like Jenkins, Ansible, Docker, Kubernetes, Grafana, Git, and more into a unified CI/CD process.

---

## 🛠️ Tech Stack

| Component         | Technology Used                         |
|------------------|------------------------------------------|
| Language          | Java                                     |
| Build Tool        | Maven                                    |
| Testing           | JUnit                                    |
| CI/CD             | Jenkins                                  |
| Deployment        | Kubernetes (Minikube)                    |
| Containerization  | Docker                                   |
| Orchestration     | Ansible                                  |
| Monitoring        | Collectd + Graphite + Grafana            |
| Version Control   | Git                                      |
| SCM Trigger       | Git Polling (SCM Polling every 2 mins)   |

---

## 🔍 Project Overview

This project automates every stage of the DevOps lifecycle for a Java-based command-line application using a modern toolchain. It starts from setting up a Maven project and Git repository and progresses through testing with JUnit, containerization using Docker, deployment via Minikube, monitoring with Grafana + Graphite + Collectd, and complete orchestration using Ansible. Jenkins integrates these components in a robust CI/CD pipeline using both freestyle and scripted (pipeline) methods.

With source code changes automatically triggering builds through Git polling, the pipeline ensures every update is tested, deployed, and monitored in real-time. If errors are encountered, automated rollback mechanisms kick in using Jenkins and Ansible to maintain application stability.

---

## 🚀 Project Workflow Summary

### 1️⃣ Code & Version Control
- The Java source code is version-controlled using **Git** and hosted on **GitHub**.
- Commits to GitHub act as triggers for the Jenkins pipeline using **SCM polling** every 2 minutes.

### 2️⃣ Continuous Integration (CI) with Jenkins
- Jenkins is used to set up:
  - A **Freestyle Project**
  - A **Declarative Pipeline** with a `Jenkinsfile`
- The pipeline detects changes and performs:
  - Compilation
  - JUnit testing
  - Docker image creation
  - Deployment
  - Monitoring setup
  - Rollback (if needed)

### 3️⃣ Build & Test
- **Maven** is used to compile the Java code and generate an executable `.jar`.
- **JUnit** is used to run automated test cases to verify application logic:
  - Valid and invalid test cases are defined for all financial operations.

### 4️⃣ Automation with Ansible
- Ansible is used to orchestrate:
  - Build
  - Test
  - Dockerize
  - Deploy
  - Monitor
  - Rollback
- A `site.yml` playbook and role-based structure help modularize and manage tasks easily.
- Jenkins executes specific tags in Ansible via shell steps.

### 5️⃣ Containerization with Docker
- The application is packaged into a **Docker image** using a Dockerfile.
- Ensures consistent environments across dev, test, and prod stages.

### 6️⃣ Kubernetes Deployment (Minikube)
- The Docker image is deployed on a **local Kubernetes cluster (Minikube)**.
- A `calculator-deployment.yaml` is used to define and manage the pod.
- Jenkins can be extended to trigger Kubernetes deployment automatically.

### 7️⃣ Monitoring with Collectd + Graphite + Grafana
- **Collectd** is configured to collect system and application metrics.
- Data is passed to **Graphite** and visualized via **Grafana** dashboards.
- Metrics include CPU, memory, disk usage, processes, etc.

### 8️⃣ Rollback Support
- Jenkins detects build or test failures.
- A **rollback stage** is triggered to restore the previous stable `.jar` build using Ansible.
- Ensures system remains available with minimal downtime.

---

## 🧠 Why This Project Matters

- **End-to-End Automation**: Covers the entire DevOps lifecycle, from code to monitoring and recovery.
- **Tool Integration**: Real-world usage of Git, Maven, Jenkins, Ansible, Docker, Kubernetes, and Grafana.
- **Fault Tolerance**: Includes rollback strategy for failed builds, making the system resilient.
- **Scalable Blueprint**: The modular pipeline and Ansible roles can be reused for other Java or microservice-based applications.

---

## 📂 Refer Full Project Report

📝 For detailed step-by-step procedures and execution screenshots, please refer to [`DevOpsProjectReport.pdf`](./DevOpsProjectReport.pdf) in the repository.

---

## 🔗 GitHub Repo

💻 [Project Source Code](https://github.com/Dhaya09/DevOpsProject)

---

## ✅ Conclusion

This project presents a complete end-to-end DevOps implementation by combining a wide array of technologies into a single CI/CD pipeline. It simplifies the software development and deployment process by making it fully automated, traceable, reliable, and testable at each stage. With Jenkins handling SCM triggers, Maven builds, and role-based Ansible playbooks, the pipeline ensures that every update is automatically tested, deployed, monitored, and recoverable in case of failure.

DevOps not only accelerates development workflows but also enhances team collaboration, monitoring, and operational stability in real-world projects. This hands-on implementation provides a foundational blueprint for organizations seeking to adopt modern DevOps methodologies.
