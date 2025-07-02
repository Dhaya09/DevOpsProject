# 🚀 DevOps Automation for Java Financial Calculator

This project demonstrates a complete DevOps pipeline that automates the build, test, dockerization, deployment, monitoring, and rollback of a CLI-based Java Financial Calculator. It integrates multiple DevOps tools like Jenkins, Ansible, Docker, Kubernetes, Grafana, Git, and more into a unified CI/CD process.

---

## 📘 Table of Contents

| No. | Section                                          | Page No. |
|-----|--------------------------------------------------|----------|
| 1   | About Project and Its Components                 | 3        |
| 2   | Use Case                                         | 7        |
| 3   | Project (Step-by-step Implementation)            | Refer `DevOpsProjectReport.pdf` in the repository |
| 4   | Conclusion                                       | 48       |

---

## 🛠️ Tech Stack

| Component         | Technology Used                         |
|------------------|------------------------------------------|
| Language          | Java                                     |
| Build Tool        | Maven                                    |
| Testing           | JUnit                                    |
| CI/CD             | Jenkins                                  |
| Automation        | Ansible                                  |
| Containerization  | Docker                                   |
| Orchestration     | Kubernetes (Minikube)                    |
| Monitoring        | Collectd + Graphite + Grafana            |
| Version Control   | Git                                      |
| SCM Trigger       | Git Polling (SCM Polling every 2 mins)  |

---

## 🔍 Project Overview

This project automates every stage of the DevOps lifecycle for a Java-based command-line application using a modern toolchain. It starts from setting up a Maven project and Git repository and progresses through testing with JUnit, containerization using Docker, deployment via Minikube, monitoring with Grafana + Graphite + Collectd, and complete orchestration using Ansible. Jenkins integrates these components in a robust CI/CD pipeline using both freestyle and scripted (pipeline) methods.

With source code changes automatically triggering builds through Git polling, the pipeline ensures every update is tested, deployed, and monitored in real-time. If errors are encountered, automated rollback mechanisms kick in using Jenkins and Ansible to maintain application stability.

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
