package com.datametica.secondarysort;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TweetMapper extends Mapper<LongWritable, Text, TweetKeyPair, Text> {

	@Override
	protected void setup(Mapper<LongWritable, Text, TweetKeyPair, Text>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, TweetKeyPair, Text>.Context context)
			throws IOException, InterruptedException {

		if (value == null || value.toString().isEmpty())
			return;
		TweetKeyPair keyPair = new TweetKeyPair();
		String record = value.toString();
		String[] columns = record.split(",");
		keyPair.setEmp_id(new Text(columns[0]));
		keyPair.setTimestamp(new LongWritable(Timestamp.valueOf(columns[1]).getTime()));

		context.write(keyPair, value);
	}
}
