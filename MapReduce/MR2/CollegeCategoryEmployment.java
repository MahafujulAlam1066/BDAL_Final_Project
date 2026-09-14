import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CollegeCategoryEmployment {

    public static class EmploymentMapper
            extends Mapper<Object, Text, Text, DoubleWritable> {

        private Text category = new Text();
        private DoubleWritable employment = new DoubleWritable();

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            String line = value.toString();

            // Skip CSV header
            if (line.startsWith("program_id,")) {
                return;
            }

            String[] fields = parseCSV(line);

            /*
             * cip_family_title = column 21 → index 20
             * count_working_in_state_5yr = column 43 → index 42
             */

            if (fields.length > 42) {

                String categoryName = fields[20].trim();
                String employmentValue = fields[42].trim();

                if (!categoryName.isEmpty() && !employmentValue.isEmpty()) {

                    try {
                        double workingCount =
                                Double.parseDouble(employmentValue);

                        category.set(categoryName);
                        employment.set(workingCount);

                        context.write(category, employment);

                    } catch (NumberFormatException e) {
                        // Ignore invalid values
                    }
                }
            }
        }

        // CSV parser supporting quoted fields containing commas
        private String[] parseCSV(String line) {

            List<String> fields = new ArrayList<>();
            StringBuilder field = new StringBuilder();
            boolean insideQuotes = false;

            for (int i = 0; i < line.length(); i++) {

                char c = line.charAt(i);

                if (c == '"') {
                    insideQuotes = !insideQuotes;
                }
                else if (c == ',' && !insideQuotes) {

                    fields.add(field.toString());
                    field.setLength(0);

                }
                else {
                    field.append(c);
                }
            }

            fields.add(field.toString());

            return fields.toArray(new String[0]);
        }
    }


    public static class EmploymentReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key,
                           Iterable<DoubleWritable> values,
                           Context context)
                throws IOException, InterruptedException {

            double total = 0.0;

            for (DoubleWritable value : values) {
                total += value.get();
            }

            result.set(total);

            context.write(key, result);
        }
    }


    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(
                conf,
                "Total Employment by Major Category"
        );

        job.setJarByClass(CollegeCategoryEmployment.class);

        job.setMapperClass(EmploymentMapper.class);
        job.setReducerClass(EmploymentReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(
                job,
                new Path(args[0])
        );

        FileOutputFormat.setOutputPath(
                job,
                new Path(args[1])
        );

        System.exit(
                job.waitForCompletion(true) ? 0 : 1
        );
    }
}