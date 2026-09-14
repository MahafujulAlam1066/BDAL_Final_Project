REGISTER 'file:///C:/pig/lib/piggybank.jar';

data = LOAD '/BDAFinal1066/college/college_major_career_outcomes_2026.csv'
USING org.apache.pig.piggybank.storage.CSVExcelStorage()
AS (
    program_id:chararray,
    unitid:chararray,
    opeid6:chararray,
    institution_name:chararray,
    institution_control:chararray,
    is_main_campus:int,
    institution_city:chararray,
    institution_state:chararray,
    institution_region:chararray,
    institution_latitude:double,
    institution_longitude:double,
    institution_is_hbcu:int,
    institution_admission_rate:double,
    institution_avg_sat:double,
    institution_undergrad_enrollment:int,
    institution_tuition_in_state_usd:double,
    institution_tuition_out_state_usd:double,
    cip_code_4digit:chararray,
    cip_title:chararray,
    cip_family_code:chararray,
    cip_family_title:chararray,
    credential_level:int,
    credential_name:chararray,
    distance_education:chararray,
    awards_year1:double,
    awards_year2:double,
    outcomes_shared_across_campuses:int,
    median_earnings_4yr_usd:double,
    earnings_cohort_size_4yr:double,
    national_median_earnings_4yr_usd:double,
    national_p25_earnings_4yr_usd:double,
    national_p75_earnings_4yr_usd:double,
    earnings_vs_national_pct:double,
    median_earnings_1yr_usd:double,
    earnings_cohort_size_1yr:double,
    median_earnings_5yr_usd:double,
    earnings_cohort_size_5yr:double,
    not_working_count_5yr:double,
    count_above_hs_threshold_5yr:double,
    count_working_in_state_5yr:double,
    median_debt_usd:double
);

filtered_data = FILTER data BY
    median_earnings_4yr_usd > 70000
    AND median_debt_usd < 30000;

result = FOREACH filtered_data GENERATE
         cip_title AS major,
         median_earnings_4yr_usd AS earnings,
         median_debt_usd AS debt;

STORE result INTO '/BDAFinal1066/output/pig5';