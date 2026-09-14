# BDAL Final Project

> Big Data Analytics project using Hadoop HDFS, MapReduce, and Apache Pig to analyze College Major & Career Outcomes.

---

## 📌 Project Overview

This project demonstrates an end-to-end Big Data Analytics workflow using a large-scale college major and career outcomes dataset.

The project focuses on storing, processing, and analyzing data related to:

- College majors
- Earnings
- Employment
- Student debt
- Occupations
- Career outcomes
- AI-related workforce indicators

### Technologies

- Hadoop HDFS
- Hadoop MapReduce
- Apache Pig
- Java
- Pig Latin
- Git & GitHub
- Git LFS

---

## 📊 Dataset

### Dataset Statistics

| Property | Value |
|---|---:|
| Dataset | College Major & Career Outcomes 2026 |
| File | `college_major_career_outcomes_2026.csv` |
| Records | 227,981 |
| Attributes | 72 |
| File Size | 126,340,696 bytes (~126.34 MB) |
| Distinct Major Titles | 358 |
| Major Categories | 44 |
| Format | CSV |

### Main Data Areas

The dataset contains information about:

- Institutions and campuses
- College majors and CIP classifications
- Credentials and degrees
- Tuition and admission
- 1-year, 4-year, and 5-year earnings
- Employment outcomes
- Student debt
- Occupations and wages
- BLS and O*NET information
- AI and technology indicators

### Important Fields

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

---

## 🏗️ Project Architecture

```text
College Major & Career Outcomes Dataset
                    |
                    v
             +-------------+
             |  Hadoop HDFS|
             |   Storage   |
             +------+------+
                    |
          +---------+---------+
          |                   |
          v                   v
   +--------------+    +--------------+
   |  MapReduce   |    | Apache Pig   |
   |  Processing  |    |  Processing  |
   +------+-------+    +------+-------+
          |                   |
          +---------+---------+
                    |
                    v
             Analytical Results
```

---

# 🗄️ HDFS Implementation

The dataset was uploaded and managed using **Hadoop Distributed File System (HDFS)**.

### HDFS Location

```text
/BDAFinal1066/college/college_major_career_outcomes_2026.csv
```

### Operations Performed

- Created the HDFS project directory
- Uploaded the dataset
- Verified the uploaded file
- Checked HDFS storage usage
- Analyzed HDFS blocks
- Verified dataset size

### Main Commands

```bash
hdfs dfs -mkdir -p /BDAFinal1066/college

hdfs dfs -put college_major_career_outcomes_2026.csv /BDAFinal1066/college/

hdfs dfs -ls /BDAFinal1066/college

hdfs dfs -du -h /BDAFinal1066/college
```

---

# ⚙️ MapReduce Implementation

Three Java-based MapReduce programs were implemented.

## 1. Average 4-Year Earnings by Major

**Objective:** Calculate the average 4-year median earnings for each college major.

**Output:**

```text
Major → Average 4-Year Earnings
```

**Files:**

```text
MapReduce/MR1/CollegeMajorAverageEarnings.java
MapReduce/MR1/college-major-earnings.jar
```

---

## 2. Total Employment by Major Category

**Objective:** Calculate total employment for each major category using the number of people working in-state after five years.

**Output:**

```text
Major Category → Total Employment
```

**Files:**

```text
MapReduce/MR2/CollegeCategoryEmployment.java
MapReduce/MR2/college-category-employment.jar
```

---

## 3. Average Student Debt by Major Category

**Objective:** Calculate the average median student debt for each major category.

**Output:**

```text
Major Category → Average Student Debt
```

**Files:**

```text
MapReduce/MR3/CollegeCategoryAverageDebt.java
MapReduce/MR3/college-category-average-debt.jar
```

---

# 🐷 Apache Pig Implementation

Apache Pig was used for data transformation and analysis, including grouping, aggregation, filtering, sorting, and ranking.

Because the dataset contains quoted CSV fields with commas, **`CSVExcelStorage()`** from the Piggybank library was used for proper CSV parsing.

## Pig Operations

| Operation | Analysis |
|---|---|
| Pig 1 | College major data analysis |
| Pig 2 | Average earnings by major category |
| Pig 3 | Total employment by major category |
| Pig 4 | Top 10 categories by employment |
| Pig 5 | High-earning and low-debt programs |

---

## Pig 2 — Average Earnings by Major Category

**Objective:** Calculate average 4-year earnings for each major category.

**Script:**

```text
Pig/PIG2/pig2_average_earnings_by_category.pig
```

---

## Pig 3 — Total Employment by Major Category

**Objective:** Calculate total employment for each major category.

**Script:**

```text
Pig/PIG3/pig3_total_employment_by_category.pig
```

---

## Pig 4 — Top 10 Categories by Employment

**Objective:** Identify the 10 major categories with the highest total employment.

### Operations

```text
GROUP → SUM → ORDER → DESC → LIMIT
```

### Top 10 Categories

| Rank | Major Category |
|---:|---|
| 1 | Health Professions and Related Programs |
| 2 | Business, Management, Marketing, and Related Support Services |
| 3 | Liberal Arts and Sciences, General Studies and Humanities |
| 4 | Culinary, Entertainment, and Personal Services |
| 5 | Education |
| 6 | Psychology |
| 7 | Mechanic and Repair Technologies/Technicians |
| 8 | Homeland Security, Law Enforcement, Firefighting and Related Protective Services |
| 9 | Computer and Information Sciences and Support Services |
| 10 | Social Sciences |

**Script:**

```text
Pig/PIG4/pig4_top10_employment.pig
```

---

## Pig 5 — High-Earning and Low-Debt Programs

**Objective:** Identify programs satisfying both conditions:

```text
Median 4-Year Earnings > $70,000
Median Student Debt < $30,000
```

**Operation:**

```text
FILTER
```

**Script:**

```text
Pig/PIG5/pig5_high_earning_low_debt.pig
```

---

# 📈 Key Results

| Technology | Analysis | Result |
|---|---|---|
| HDFS | Dataset storage | 126.34 MB stored in HDFS |
| MapReduce 1 | Average earnings by major | 358 major groups |
| MapReduce 2 | Employment by category | 44 categories |
| MapReduce 3 | Average debt by category | Category-level debt analysis |
| Pig 2 | Average earnings by category | 44 categories |
| Pig 3 | Total employment by category | Category-level employment analysis |
| Pig 4 | Employment ranking | Top 10 categories |
| Pig 5 | Earnings + debt filtering | Qualified programs |

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
│   ├── MR2/
│   ├── MR3/
│   └── Screenshot/
│
├── Pig/
│   ├── PIG1/
│   ├── PIG2/
│   ├── PIG3/
│   ├── PIG4/
│   ├── PIG5/
│   └── Screenshot/
│
├── college_major_career_outcomes_2026.csv
├── .gitattributes
└── README.md
```

---

# 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Hadoop HDFS | Distributed data storage |
| Hadoop MapReduce | Distributed data processing |
| Apache Pig | Data transformation and analysis |
| Java | MapReduce programming |
| Pig Latin | Pig data processing |
| Git | Version control |
| GitHub | Project repository |
| Git LFS | Large dataset storage |

---

# 🎯 Project Objectives

1. Store a large dataset using Hadoop HDFS.
2. Understand distributed storage and HDFS blocks.
3. Process large-scale data using MapReduce.
4. Analyze earnings, employment, and student debt.
5. Perform data transformation using Apache Pig.
6. Apply grouping, aggregation, filtering, sorting, and ranking.
7. Identify high-earning and relatively low-debt programs.
8. Demonstrate an end-to-end Big Data Analytics workflow.

---

# 📚 Learning Outcomes

This project provides practical experience with:

- Distributed file storage
- HDFS file management
- HDFS block analysis
- MapReduce Mapper and Reducer
- Key-value processing
- Data grouping and aggregation
- Average and sum calculations
- Filtering and sorting
- Ranking and limiting
- Large CSV data processing
- Hadoop-based analytical workflows

---

# 📸 Screenshots

The repository includes screenshots documenting:

- HDFS dataset upload
- HDFS analysis
- HDFS block analysis
- MapReduce operations
- MapReduce outputs
- Apache Pig operations
- Pig processing results

---

# 👨‍💻 Project Information

**Project:** Big Data Analytics Final Project

**Repository:** `MahafujulAlam1066/BDAL_Final_Project`

**Dataset:** College Major & Career Outcomes 2026

---

# ⭐ Conclusion

This project demonstrates an end-to-end Big Data Analytics workflow using **Hadoop HDFS, MapReduce, and Apache Pig**.

The analysis provides insights into **college major earnings, employment, student debt, and career outcomes**, while demonstrating practical techniques for processing large-scale datasets using Hadoop technologies.