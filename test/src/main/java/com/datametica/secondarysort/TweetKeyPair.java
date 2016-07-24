package com.datametica.secondarysort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import com.datametica.util.Util;

public class TweetKeyPair implements WritableComparable<TweetKeyPair> {

	private Text emp_id;
	private LongWritable timestamp;

	public Text getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Text emp_id) {
		this.emp_id = emp_id;
	}

	public LongWritable getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LongWritable timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		emp_id.write(out);
		timestamp.write(out);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		emp_id.readFields(in);
		timestamp.readFields(in);
	}

	@Override
	public int compareTo(TweetKeyPair tkp) {
		if (emp_id.compareTo(tkp.getEmp_id()) == 0) {
			// if(Date.valueOf(timestamp.toString()).getTime() >
			// Date.valueOf(tkp.timestamp.toString()).getTime())
			return Util.sortOrder * timestamp.compareTo(tkp.getTimestamp());
		} else
			return (emp_id.compareTo(tkp.getEmp_id()));
	}

}
