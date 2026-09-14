# BDAL Final Project

> Big Data Analytics project using Hadoop HDFS, MapReduce, and Apache Pig to analyze College Major & Career Outcomes.

---

## 📌 Project Overview

This project demonstrates an end-to-end Big Data Analytics workflow using a large-scale college major and career outcomes dataset.

The analysis focuses on:

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
| Major Groups | 358 |
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
             | Hadoop HDFS |
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

### Main Commands

```bash
hdfs dfs -mkdir -p /BDAFinal1066/college

hdfs dfs -put college_major_career_outcomes_2026.csv /BDAFinal1066/college/

hdfs dfs -ls /BDAFinal1066/college

hdfs dfs -du -h /BDAFinal1066/college
```

---

# ⚙️ MapReduce Implementation

Three Java-based MapReduce programs were implemented to analyze earnings, employment, and student debt.

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

Apache Pig was used for data transformation and analysis, including filtering, grouping, aggregation, sorting, and ranking.

Because the dataset contains quoted CSV fields with commas, **`CSVExcelStorage()`** from the Piggybank library was used for proper CSV parsing.

## Pig Operations

| ID | Analysis |
|---|---|
| **PIG1** | High-Earning Programs |
| **PIG2** | Average Earnings by Major Category |
| **PIG3** | Total Employment by Major Category |
| **PIG4** | Top 10 Categories by Employment |
| **PIG5** | High-Earning and Low-Debt Programs |

---

## PIG1 — High-Earning Programs

**Objective:** Identify college programs with 4-year median earnings above **$70,000**.

**Condition:**

```text
median_earnings_4yr_usd > 70000
```

**Script:**

```text
Pig/PIG1/pig1_high_earning_programs.pig
```

---

## PIG2 — Average Earnings by Major Category

**Objective:** Calculate average 4-year earnings for each major category.

**Script:**

```text
Pig/PIG2/pig2_average_earnings_by_category.pig
```

---

## PIG3 — Total Employment by Major Category

**Objective:** Calculate total employment for each major category.

**Script:**

```text
Pig/PIG3/pig3_total_employment_by_category.pig
```

---

## PIG4 — Top 10 Categories by Employment

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

## PIG5 — High-Earning and Low-Debt Programs

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

The project successfully produced analytical results using **HDFS, Java MapReduce, and Apache Pig**. The main results are summarized below.

| ID | Technology | Analysis | Key Result |
|---|---|---|---|
| **MR1** | MapReduce | Average 4-Year Earnings by Major | Average earnings calculated for **358 major groups** |
| **MR2** | MapReduce | Total Employment by Major Category | Employment aggregated across **44 major categories** |
| **MR3** | MapReduce | Average Student Debt by Major Category | Category-wise average student debt calculated |
| **PIG1** | Apache Pig | High-Earning Programs | Programs with **4-year earnings above $70,000** identified |
| **PIG2** | Apache Pig | Average Earnings by Category | **Engineering:** approximately **$100,107.42** |
| **PIG3** | Apache Pig | Total Employment by Category | **Health Professions:** **997,860** |
| **PIG4** | Apache Pig | Top 10 Employment Categories | **10 highest-employment categories** identified |
| **PIG5** | Apache Pig | High Earnings + Low Debt | Programs with earnings **>$70,000** and debt **<$30,000** identified |

### Key Findings

- **358 major groups** were analyzed in the earnings-by-major operation.
- **44 major categories** were analyzed for category-level aggregation.
- **Engineering** recorded approximately **$100,107.42** average 4-year earnings in the Pig analysis.
- **Health Professions and Related Programs** recorded the highest total employment with **997,860**.
- The final Pig operation identified programs combining **higher earnings with relatively lower student debt**.

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
| **Hadoop HDFS** | Distributed data storage |
| **Hadoop MapReduce** | Distributed data processing |
| **Apache Pig** | Data transformation and analysis |
| **Java** | MapReduce programming |
| **Pig Latin** | Pig data processing |
| **Git & GitHub** | Version control and project hosting |
| **Git LFS** | Large dataset management |

---

