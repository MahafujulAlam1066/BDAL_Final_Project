import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CollegeMajorAverageEarnings {

    public static class EarningsMapper
            extends Mapper<Object, Text, Text, DoubleWritable> {

        private Text major = new Text();
        private DoubleWritable earnings = new DoubleWritable();

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {

            String line = value.toString();

            // Skip CSV header
            if (line.startsWith("program_id,")) {
                return;
            }

            String[] fields = parseCSV(line);

            // cip_title = column 18
            // median_earnings_4yr_usd = column 27
            if (fields.length > 27) {
                String majorName = fields[17].trim();
                String earningsValue = fields[27].trim();

                if (!majorName.isEmpty() && !earningsValue.isEmpty()) {
                    try {
                        double value4yr = Double.parseDouble(earningsValue);

                        major.set(majorName);
                        earnings.set(value4yr);

                        context.write(major, earnings);
                    } catch (NumberFormatException e) {
                        // Ignore invalid numeric values
                    }
                }
            }
        }

        // Simple CSV parser that handles quoted commas
        private String[] parseCSV(String line) {
            java.util.List<String> fields = new java.util.ArrayList<>();
            StringBuilder field = new StringBuilder();
            boolean insideQuotes = false;

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (c == '"') {
                    insideQuotes = !insideQuotes;
                } else if (c == ',' && !insideQuotes) {
                    fields.add(field.toString());
                    field.setLength(0);
                } else {
                    field.append(c);
                }
            }

            fields.add(field.toString());

            return fields.toArray(new String[0]);
        }
    }

    public static class EarningsReducer
            extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

        private DoubleWritable result = new DoubleWritable();

        public void reduce(Text key, Iterable<DoubleWritable> values,
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

        Job job = Job.getInstance(conf, "Average 4 Year Earnings by Major");

        job.setJarByClass(CollegeMajorAverageEarnings.class);

        job.setMapperClass(EarningsMapper.class);
        job.setReducerClass(EarningsReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(
                job, new Path(args[0]));

        FileOutputFormat.setOutputPath(
                job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}