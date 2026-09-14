# BDAL Final Project

## 📌 Project Overview

This project is a **Big Data Analytics implementation** using a large-scale College Major & Career Outcomes dataset. The project demonstrates how distributed Big Data technologies can be used to store, process, analyze, and extract meaningful insights from educational and career-related data.

The project implements:

- **Hadoop HDFS** — Distributed data storage
- **Hadoop MapReduce** — Distributed data processing
- **Apache Pig** — High-level data analysis and transformation

The analysis focuses mainly on **college major earnings, employment, student debt, occupations, and career outcomes**.

---

## 📊 Dataset

### Dataset Information

The project uses the **College Major & Career Outcomes 2026** dataset.

| Property | Details |
|---|---|
| File Name | `college_major_career_outcomes_2026.csv` |
| Format | CSV |
| Records | **227,981** |
| Attributes | **72** |
| File Size | **126,340,696 bytes (~126.34 MB)** |
| Distinct Major Titles | **358** |
| Major Categories | **44** |
| Storage | Hadoop HDFS |

### Main Data Areas

The dataset contains information related to:

- Institutions and campus details
- College majors and CIP classifications
- Credential and degree information
- Tuition and admission
- 1-year, 4-year, and 5-year earnings
- Employment outcomes
- Student debt and loan payments
- Occupation and labor-market information
- BLS and O*NET occupation data
- AI and technology-related indicators

### Important Analytical Fields

| Field | Description |
|---|---|
| `cip_title` | College major title |
| `cip_family_title` | Major category |
| `median_earnings_4yr_usd` | Median 4-year earnings |
| `median_earnings_1yr_usd` | Median 1-year earnings |
| `median_earnings_5yr_usd` | Median 5-year earnings |
| `count_working_in_state_5yr` | Number working in-state after 5 years |
| `median_debt_usd` | Median student debt |
| `median_monthly_payment_usd` | Median monthly payment |
| `earnings_growth_pct_1yr_to_5yr` | Earnings growth |
| `occupation_median_wage_2024_usd` | Occupation median wage |
| `occupation_growth_pct_2024_34` | Occupation growth |
| `ai_software_occupation_share` | AI software usage indicator |

The dataset contains a mixture of **categorical, numerical, textual, percentage, geographic, and status-based attributes**, making it suitable for distributed Big Data analysis.

---

## 🏗️ Project Architecture


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

# 🗄️ HDFS Implementation

The dataset was stored and managed using **Hadoop Distributed File System (HDFS)**.

### HDFS Dataset Location

```text
/BDAFinal1066/college/college_major_career_outcomes_2026.csv
```

### Operations Performed

* Created the HDFS project directory
* Uploaded the dataset
* Verified the uploaded file
* Checked HDFS storage information
* Analyzed HDFS block allocation
* Verified dataset size

### Main Commands

```bash
hdfs dfs -mkdir -p /BDAFinal1066/college

hdfs dfs -put college_major_career_outcomes_2026.csv /BDAFinal1066/college/

hdfs dfs -ls /BDAFinal1066/college

hdfs dfs -du -h /BDAFinal1066/college
```

---

# ⚙️ MapReduce Implementation

Three Java-based MapReduce programs were implemented to perform distributed analytical operations.

## 1. Average 4-Year Earnings by Major

### Objective

Calculate the average 4-year median earnings for each college major.

### Output

```text
Major → Average 4-Year Earnings
```

### Files

```text
MapReduce/MR1/CollegeMajorAverageEarnings.java
MapReduce/MR1/college-major-earnings.jar
```

---

## 2. Total Employment by Major Category

### Objective

Calculate total employment for each major category using the number of people working in-state after five years.

### Output

```text
Major Category → Total Employment
```

### Files

```text
MapReduce/MR2/CollegeCategoryEmployment.java
MapReduce/MR2/college-category-employment.jar
```

---

## 3. Average Student Debt by Major Category

### Objective

Calculate the average median student debt for each major category.

### Output

```text
Major Category → Average Student Debt
```

### Files

```text
MapReduce/MR3/CollegeCategoryAverageDebt.java
MapReduce/MR3/college-category-average-debt.jar
```

---

# 🐷 Apache Pig Implementation

Apache Pig was used for high-level data transformation, filtering, grouping, aggregation, sorting, and ranking.

Because the dataset contains CSV fields with quoted commas, the project uses **`CSVExcelStorage()`** from the Piggybank library for proper CSV parsing.

---

## Pig Operation 1

The first Pig operation performs analytical processing on the college major dataset.

### Location

```text
Pig/PIG1/
```

---

## Pig Operation 2 — Average Earnings by Major Category

### Objective

Calculate the average 4-year earnings for each major category.

### Output

```text
Major Category → Average 4-Year Earnings
```

### Script

```text
Pig/PIG2/pig2_average_earnings_by_category.pig
```

---

## Pig Operation 3 — Total Employment by Major Category

### Objective

Calculate total employment for each major category.

### Output

```text
Major Category → Total Employment
```

### Script

```text
Pig/PIG3/pig3_total_employment_by_category.pig
```

---

## Pig Operation 4 — Top 10 Categories by Employment

### Objective

Identify the top 10 major categories based on total employment.

### Operations Used

* `GROUP`
* `SUM`
* `ORDER`
* `DESC`
* `LIMIT`

### Top 10 Categories

| Rank | Major Category                                                                   |
| ---: | -------------------------------------------------------------------------------- |
|    1 | Health Professions and Related Programs                                          |
|    2 | Business, Management, Marketing, and Related Support Services                    |
|    3 | Liberal Arts and Sciences, General Studies and Humanities                        |
|    4 | Culinary, Entertainment, and Personal Services                                   |
|    5 | Education                                                                        |
|    6 | Psychology                                                                       |
|    7 | Mechanic and Repair Technologies/Technicians                                     |
|    8 | Homeland Security, Law Enforcement, Firefighting and Related Protective Services |
|    9 | Computer and Information Sciences and Support Services                           |
|   10 | Social Sciences                                                                  |

### Script

```text
Pig/PIG4/pig4_top10_employment.pig
```

---

## Pig Operation 5 — High-Earning and Low-Debt Programs

### Objective

Identify college programs that meet both financial conditions:

```text
Median 4-Year Earnings > $70,000
AND
Median Student Debt < $30,000
```

### Operation Used

* `FILTER`

### Script

```text
Pig/PIG5/pig5_high_earning_low_debt.pig
```

---

# 📈 Key Results

The implemented operations produced the following analytical outputs:

| Technology  | Operation                    | Result                             |
| ----------- | ---------------------------- | ---------------------------------- |
| HDFS        | Dataset Storage              | 126.34 MB dataset stored in HDFS   |
| MapReduce 1 | Average Earnings by Major    | 358 major groups                   |
| MapReduce 2 | Employment by Category       | 44 major categories                |
| MapReduce 3 | Average Debt by Category     | Category-level debt analysis       |
| Pig 2       | Average Earnings by Category | 44 categories                      |
| Pig 3       | Total Employment by Category | Category-level employment analysis |
| Pig 4       | Top Employment Categories    | Top 10 categories                  |
| Pig 5       | Earnings + Debt Filter       | Programs meeting selected criteria |

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
│   │
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
│   │
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

| Technology           | Purpose                          |
| -------------------- | -------------------------------- |
| **Hadoop HDFS**      | Distributed data storage         |
| **Hadoop MapReduce** | Distributed data processing      |
| **Apache Pig**       | Data transformation and analysis |
| **Java**             | MapReduce programming            |
| **Pig Latin**        | Pig data processing              |
| **Git**              | Version control                  |
| **GitHub**           | Project repository               |
| **Git LFS**          | Large dataset storage            |

---

# 🎯 Project Objectives

The main objectives of this project are:

1. Store a large dataset using Hadoop HDFS.
2. Understand distributed storage and HDFS blocks.
3. Process large-scale data using MapReduce.
4. Perform earnings, employment, and debt analysis.
5. Use Apache Pig for data transformation and analysis.
6. Apply grouping, aggregation, filtering, sorting, and ranking.
7. Identify high-earning and relatively low-debt programs.
8. Demonstrate an end-to-end Big Data Analytics workflow.

---

# 📚 Learning Outcomes

This project provides practical experience with:

* Distributed file storage
* HDFS file and directory management
* HDFS block analysis
* MapReduce Mapper and Reducer
* Key-value based processing
* Data grouping and aggregation
* Average and sum calculations
* Filtering and sorting
* Ranking and limiting results
* Large CSV data processing
* Hadoop-based analytical workflows

---

# 📸 Screenshots

The repository contains screenshots documenting the implementation and execution of:

* HDFS dataset upload
* HDFS file analysis
* HDFS block analysis
* MapReduce operations
* MapReduce outputs
* Apache Pig operations
* Pig processing results

These screenshots provide evidence of the practical implementation of the project.

---

# 👨‍💻 Project Information

**Project:** Big Data Analytics Final Project

**Repository:** `MahafujulAlam1066/BDAL_Final_Project`

**Dataset:** College Major & Career Outcomes 2026

---

# ⭐ Conclusion

This project demonstrates an end-to-end Big Data Analytics workflow, starting with distributed storage using **Hadoop HDFS**, followed by data processing using **Hadoop MapReduce** and **Apache Pig**.

The implemented analyses provide insights into **college major earnings, employment, student debt, and career outcomes**, while demonstrating the practical use of Hadoop-based Big Data technologies for processing large-scale datasets.

````

