# Health Insurance Claim Processing - Spring Batch

![Spring](https://img.shields.io/badge/Spring-Framework-brightgreen?style=for-the-badge&logo=spring&logoColor=white)
![Spring Batch](https://img.shields.io/badge/Spring%20Batch-5.0-green?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=oracle&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange?style=for-the-badge&logo=mysql&logoColor=white)

## Overview
The **Health Insurance Claim Processing** project is a robust batch processing application built with **Spring Batch**. It is designed to automate the handling of health insurance claims, including data ingestion, validation, processing, and reporting. This application showcases best practices in batch processing, making it efficient and scalable for handling large datasets.

### Key Features
- **Claim Data Ingestion**:
  - Reads claim data from CSV or other flat file formats.
  - Supports bulk data imports.
- **Data Validation**:
  - Validates required fields and checks for data integrity.
  - Logs invalid records for future review.
- **Batch Processing**:
  - Processes claims in chunks, ensuring memory efficiency.
  - Implements retry mechanisms for transient errors.
- **Reporting**:
  - Generates summary reports of processed and failed claims.
  - Supports exporting reports in user-friendly formats.
- **Configurable**:
  - Job parameters allow dynamic configurations for each batch run.
  - Easily adaptable to new file formats or processing rules.

### Technologies Used
- **Spring Batch**: For batch processing and job orchestration.
- **Spring Boot**: For application configuration and running the application.
- **Java 17**: As the programming language.
- **MySQL**: For storing batch metadata and processed data.
- **Maven**: For dependency management.

---

## Setup Instructions

### Prerequisites
- **Java 17**: Make sure Java is installed and added to your system's PATH.
- **Maven**: For building the project.
- **Git**: For cloning the repository.

### Clone the Repository
```bash
git clone https://github.com/samar-ait/health-insurance-claim.git
cd health-insurance-claim
```
### Build the Project
```bash
mvn clean install
```
### Configure the Application
Edit the application.properties file to set up any required configurations, such as:
*Input file path for claims data.
*Database connection settings (if not using H2).
### Run the Application
Start the batch processing job with:
```bash
mvn spring-boot:run
```
### Verify the Results
Check the console logs for processing details.
Review the generated reports or logs for processed and invalid claims.

