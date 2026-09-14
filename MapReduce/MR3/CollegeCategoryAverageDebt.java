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

public class CollegeCategoryAverageDebt {

    public static class DebtMapper
            extends Mapper<Object, Text, Text, DoubleWritable> {

        private Text category = new Text();
        private DoubleWritable debt = new DoubleWritable();

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
             * median_debt_usd = column 40 → index 39
             */

            if (fields.length > 39) {

                String categoryName = fields[20].trim();
                String debtValue = fields[39].trim();

                if (!categoryName.isEmpty() && !debtValue.isEmpty()) {

                    try {

                        double debtAmount =
                                Double.parseDouble(debtValue);

                        category.set(categoryName);
                        debt.set(debtAmount);

                        context.write(category, debt);

                    } catch (NumberFormatException e) {
                        // Ignore invalid or suppressed values
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


    public static class DebtReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key,
                           Iterable<DoubleWritable> values,
                           Context context)
                throws IOException, InterruptedException {

            double sum = 0.0;
            long count = 0;

            for (DoubleWritable value : values) {

                sum += value.get();
                count++;
            }

            if (count > 0) {

                result.set(sum / count);

                context.write(key, result);
            }
        }
    }


    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(
                conf,
                "Average Student Debt by Major Category"
        );

        job.setJarByClass(CollegeCategoryAverageDebt.class);

        job.setMapperClass(DebtMapper.class);
        job.setReducerClass(DebtReducer.class);

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