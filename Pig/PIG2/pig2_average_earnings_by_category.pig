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
    median_earnings_4yr_usd:double
);

valid_data = FILTER data BY
    cip_family_title IS NOT NULL
    AND median_earnings_4yr_usd IS NOT NULL;

grouped_data = GROUP valid_data BY cip_family_title;

result = FOREACH grouped_data GENERATE
         group AS major_category,
         AVG(valid_data.median_earnings_4yr_usd) AS average_earnings;

STORE result INTO '/BDAFinal1066/output/pig2_corrected';