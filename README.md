# BDAL Final Project

## 📌 Project Overview

This project is a **Big Data Analytics implementation** based on a large-scale dataset containing information about college majors, institutions, career outcomes, earnings, student debt, employment, occupations, and AI-related workforce indicators.

The main objective of this project is to demonstrate how Big Data technologies can be used to store, process, analyze, and extract meaningful insights from a large real-world dataset.

The project implements three major Big Data technologies:

- **Hadoop HDFS** — Distributed storage and dataset management
- **Hadoop MapReduce** — Distributed data processing and analytical operations
- **Apache Pig** — High-level data analysis and transformation

---

## 📊 Dataset

### Dataset Overview

The project uses the **College Major & Career Outcomes 2026** dataset, a large-scale CSV dataset containing information about college programs, institutions, earnings, employment, student debt, occupations, and AI-related workforce indicators.

The dataset is suitable for Big Data processing because of its large size, diverse attributes, and combination of categorical, numerical, and textual data.

### Dataset Statistics

| Property | Value |
|---|---:|
| Dataset Name | College Major & Career Outcomes 2026 |
| File Name | `college_major_career_outcomes_2026.csv` |
| File Format | CSV |
| Number of Records | **227,981** |
| Number of Attributes | **72** |
| Raw File Size | **126,340,696 bytes** |
| Approximate Size | **126.34 MB** |
| Distinct Major Titles Processed | **358** |
| Major Categories Processed | **44** |
| Storage Platform | Hadoop HDFS |
| Processing Platforms | Hadoop MapReduce & Apache Pig |

> **Note:** The record count represents the input records processed by the Hadoop MapReduce jobs. The distinct major-title and major-category counts correspond to the successfully processed analytical outputs.

---

### Dataset Dimensions

The dataset contains:

- **227,981 records**
- **72 attributes**
- Approximately **126.34 MB** of raw CSV data

This size makes the dataset appropriate for demonstrating distributed storage and processing using Hadoop technologies.

The dataset was uploaded to HDFS at:

```text
/BDAFinal1066/college/college_major_career_outcomes_2026.csv


## 🏗️ Project Architecture

```text
College Major & Career Outcomes Dataset
                    │
                    ▼
            ┌───────────────┐
            │   Hadoop HDFS │
            │    Storage    │
            └───────┬───────┘
                    │
          ┌─────────┴─────────┐
          │                   │
          ▼                   ▼
   ┌──────────────┐    ┌──────────────┐
   │  MapReduce   │    │ Apache Pig   │
   │  Processing  │    │  Processing  │
   └──────┬───────┘    └──────┬───────┘
          │                   │
          └─────────┬─────────┘
                    ▼
             Analytical Results
                    │
                    ▼
              Data Insights
````

---

## 🗄️ HDFS Implementation

The dataset was uploaded and managed using **Hadoop Distributed File System (HDFS)**.

### HDFS Dataset Location

```text
/BDAFinal1066/college/college_major_career_outcomes_2026.csv
```

### HDFS Operations Performed

* Created the project directory in HDFS
* Uploaded the dataset to HDFS
* Verified the uploaded dataset
* Checked HDFS storage information
* Analyzed HDFS block allocation
* Verified dataset size and distributed storage

### Main HDFS Commands

```bash
hdfs dfs -mkdir -p /BDAFinal1066/college

hdfs dfs -put college_major_career_outcomes_2026.csv /BDAFinal1066/college/

hdfs dfs -ls /BDAFinal1066/college

hdfs dfs -du -h /BDAFinal1066/college
```

---

# ⚙️ MapReduce Implementation

Three MapReduce programs were developed using **Java**.

## 1. Average 4-Year Earnings by Major

### Objective

Calculate the average 4-year median earnings for each college major.

### Output

```text
Major → Average 4-Year Earnings
```

### Source Code

`MapReduce/MR1/CollegeMajorAverageEarnings.java`

### JAR

`MapReduce/MR1/college-major-earnings.jar`

---

## 2. Total Employment by Major Category

### Objective

Calculate the total employment for each major category using the number of people working in-state after five years.

### Output

```text
Major Category → Total Employment
```

### Source Code

`MapReduce/MR2/CollegeCategoryEmployment.java`

### JAR

`MapReduce/MR2/college-category-employment.jar`

---

## 3. Average Student Debt by Major Category

### Objective

Calculate the average median student debt for each major category.

### Output

```text
Major Category → Average Student Debt
```

### Source Code

`MapReduce/MR3/CollegeCategoryAverageDebt.java`

### JAR

`MapReduce/MR3/college-category-average-debt.jar`

---

# 🐷 Apache Pig Implementation

Apache Pig was used to perform high-level data transformation and analysis.

Because the dataset contains CSV fields with quoted commas, the project uses the **CSVExcelStorage** loader from the Piggybank library for proper CSV parsing.

---

## Pig Operation 1

The first Pig operation performs analytical processing on the college major dataset.

### Location

`Pig/PIG1/`

---

## Pig Operation 2 — Average Earnings by Major Category

### Objective

Calculate the average 4-year earnings for each major category.

### Output

```text
Major Category → Average 4-Year Earnings
```

### Script

`Pig/PIG2/pig2_average_earnings_by_category.pig`

---

## Pig Operation 3 — Total Employment by Major Category

### Objective

Calculate the total employment for each major category.

### Output

```text
Major Category → Total Employment
```

### Script

`Pig/PIG3/pig3_total_employment_by_category.pig`

---

## Pig Operation 4 — Top 10 Categories by Employment

### Objective

Identify the top 10 major categories with the highest total employment.

### Operations Used

* GROUP
* SUM
* ORDER
* DESC
* LIMIT

### Top 10 Categories

1. Health Professions and Related Programs
2. Business, Management, Marketing, and Related Support Services
3. Liberal Arts and Sciences, General Studies and Humanities
4. Culinary, Entertainment, and Personal Services
5. Education
6. Psychology
7. Mechanic and Repair Technologies/Technicians
8. Homeland Security, Law Enforcement, Firefighting and Related Protective Services
9. Computer and Information Sciences and Support Services
10. Social Sciences

### Script

`Pig/PIG4/pig4_top10_employment.pig`

---

## Pig Operation 5 — High-Earning and Low-Debt Programs

### Objective

Identify college programs that satisfy both conditions:

```text
Median 4-Year Earnings > $70,000
AND
Median Student Debt < $30,000
```

### Operation Used

* FILTER

### Script

`Pig/PIG5/pig5_high_earning_low_debt.pig`

---

# 📁 Project Structure

```text
BDAL_Final_Project/
│
├── HDFS/
│   └── Screenshot/
│       ├── HDFS Analysis.png
│       ├── HDFS Block Analysis .png
│       └── hdfs dataset upload.png
│
├── MapReduce/
│   ├── MR1/
│   │   ├── CollegeMajorAverageEarnings.java
│   │   ├── *.class
│   │   └── college-major-earnings.jar
│   │
│   ├── MR2/
│   │   ├── CollegeCategoryEmployment.java
│   │   ├── *.class
│   │   └── college-category-employment.jar
│   │
│   ├── MR3/
│   │   ├── CollegeCategoryAverageDebt.java
│   │   ├── *.class
│   │   └── college-category-average-debt.jar
│   │
│   └── Screenshot/
│       ├── Mapreduce Operation 1.png
│       ├── Mapreduce Operation 2.png
│       ├── Mapreduce Operation 3.png
│       └── Mapreduce output 1.png
│
├── Pig/
│   ├── PIG1/
│   ├── PIG2/
│   ├── PIG3/
│   ├── PIG4/
│   ├── PIG5/
│   │
│   └── Screenshot/
│       ├── Pig operation 1.png
│       ├── Pig operation 2.png
│       ├── Pig operation 3.png
│       ├── Pig operation 4.png
│       └── Pig operation 5.png
│
├── college_major_career_outcomes_2026.csv
├── .gitattributes
└── README.md
```

---

# 🛠️ Technologies Used

| Technology       | Purpose                          |
| ---------------- | -------------------------------- |
| Hadoop HDFS      | Distributed data storage         |
| Hadoop MapReduce | Distributed data processing      |
| Apache Pig       | Data transformation and analysis |
| Java             | MapReduce programming            |
| Pig Latin        | Pig data processing              |
| Git              | Version control                  |
| GitHub           | Project repository               |
| Git LFS          | Large dataset storage            |

