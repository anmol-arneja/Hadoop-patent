package in.edureka.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer; 
 

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

 

/*
 * The Patent program finds the number of sub-patents associated with each id in the provided
 * input file.A map reduce code is written to achieve this, where mapper makes key value pair from 
 * the input file and reducer does aggregation on this key value pair.
 */

public class Patent { 
	
 
	public static class Map extends
	Mapper<LongWritable, Text, Text, Text> {
    	
    	//Mapper
		
		
    	/*
    	 *This method takes the input as text data type and and tokenizes input
    	 * by taking whitespace as delimiter. Now key value pair is made and this key 
    	 * value pair is passed to reducer.                                             
    	 * @method_arguments key, value, output, reporter
    	 * @return void
    	 */	
    	
    	
    	Text k= new Text();

     	Text v= new Text(); 

  
    	
        @Override 
        public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {


        	
        	String line = value.toString(); 

        	StringTokenizer tokenizer = new StringTokenizer(line," "); 
 
         	

            while (tokenizer.hasMoreTokens()) { 


            	String jiten= tokenizer.nextToken();
            	k.set(jiten);
            	String jiten1= tokenizer.nextToken();
            	v.set(jiten1);

            	
                context.write(k,v); 
            } 
        } 
    } 
    
    	
 
	public static class Reduce extends Reducer<Text, Text, Text, IntWritable> {
    	
        @Override 
        public void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {

        	

            int sum = 0; 

          

            for(Text x : values)
            {
            	sum++;
            }
            
            
            
            context.write(key, new IntWritable(sum)); 
        } 
 
    } 
    
    	

    //Driver
     
    public static void main(String[] args) throws Exception { 
 
		
		Configuration conf = new Configuration();
		
		Job job = new Job(conf, "patent");
		
		
		
		job.setJarByClass(Patent.class);
		
		
		
		job.setMapperClass(Map.class);
		
		
		job.setReducerClass(Reduce.class);
		
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		
		
		job.setOutputKeyClass(Text.class);
		
		
		
		job.setOutputValueClass(IntWritable.class);
		
		
		
		job.setOutputKeyClass(Text.class);
		
		
		
		job.setOutputValueClass(Text.class);
		
		
		
		job.setInputFormatClass(TextInputFormat.class);
		
		
		
		job.setOutputFormatClass(TextOutputFormat.class);
 
		
        Path outputPath = new Path(args[1]);
		
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		
		outputPath.getFileSystem(conf).delete(outputPath);
		
		
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
 
    } 
}

