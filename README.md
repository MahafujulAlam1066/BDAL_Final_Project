# BDAL\_Final\_Project

Big Data Analytics project implementing HDFS, MapReduce, and Apache Pig for College Major Career Outcome analysis.



\## 📌 Project Overview



This project is a Big Data Analytics implementation based on a large-scale dataset containing information about college majors, institutions, career outcomes, earnings, student debt, employment, occupations, and AI-related workforce indicators.



The main objective of this project is to demonstrate how Big Data technologies can be used to store, process, analyze, and extract meaningful insights from a large real-world dataset.



The project implements three major Big Data technologies:



\- \*\*Hadoop HDFS\*\* — Distributed storage and dataset management

\- \*\*Hadoop MapReduce\*\* — Distributed data processing and analytical operations

\- \*\*Apache Pig\*\* — High-level data analysis and transformation



\---



\## 📊 Dataset



\*\*Dataset:\*\* `college\_major\_career\_outcomes\_2026.csv`



The dataset contains approximately \*\*126 MB\*\* of data and includes \*\*72 attributes\*\* related to college programs and career outcomes.



\### Major Data Categories



\- Institution information

\- College major and CIP classification

\- Credential and degree information

\- Tuition and admission information

\- 1-year, 4-year, and 5-year earnings

\- Employment statistics

\- Student debt

\- Debt-to-earnings indicators

\- Occupation information

\- BLS occupation statistics

\- O\*NET information

\- AI-related occupation indicators



The dataset is stored in \*\*HDFS\*\* and processed using Hadoop and Apache Pig.



\---



\# 🏗️ Project Architecture



```text

&#x20;                 College Major \& Career

&#x20;                   Outcomes Dataset

&#x20;                          │

&#x20;                          ▼

&#x20;               ┌─────────────────────┐

&#x20;               │      Hadoop HDFS    │

&#x20;               │   Distributed       │

&#x20;               │   Storage           │

&#x20;               └──────────┬──────────┘

&#x20;                          │

&#x20;             ┌────────────┴────────────┐

&#x20;             │                         │

&#x20;             ▼                         ▼

&#x20;    ┌─────────────────┐       ┌─────────────────┐

&#x20;    │   MapReduce     │       │   Apache Pig    │

&#x20;    │   Processing    │       │   Processing    │

&#x20;    └────────┬────────┘       └────────┬────────┘

&#x20;             │                         │

&#x20;             ▼                         ▼

&#x20;      Analytical Results        Analytical Results

&#x20;             │                         │

&#x20;             └────────────┬────────────┘

&#x20;                          ▼

&#x20;                   Data Insights

