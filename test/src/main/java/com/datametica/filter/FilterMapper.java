package com.datametica.filter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.datametica.util.Util;

public class FilterMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		if (value == null || value.toString().isEmpty())
			return;
		String record = value.toString();
		String[] columns = record.split(",");
		if (columns[1].contains(Util.word))
			context.write(new Text(columns[0] + "," + columns[1]), new Text());
	}
}
