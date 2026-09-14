data = LOAD '/BDAFinal1066/college/college_major_career_outcomes_2026.csv'
USING PigStorage(',')
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
    median_earnings_4yr_usd:double
);

filtered_data = FILTER data BY median_earnings_4yr_usd > 70000;

result = FOREACH filtered_data GENERATE
         cip_title,
         median_earnings_4yr_usd;

STORE result INTO '/BDAFinal1066/output/pig1';