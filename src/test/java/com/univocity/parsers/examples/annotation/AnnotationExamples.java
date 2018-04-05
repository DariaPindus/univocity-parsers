/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.parsers.examples.annotation;

import com.univocity.parsers.examples.*;
import com.univocity.parsers.fixed.*;
import org.testng.annotations.*;

import java.io.*;

public class AnnotationExamples extends Example {

	private FixedWidthParserSettings getSettings(FixedWidthFields fields) {
		FixedWidthParserSettings settings = new FixedWidthParserSettings(fields);
		settings.getFormat().setLineSeparator("\n");
		settings.getFormat().setPadding('.');
		settings.setHeaderExtractionEnabled(true);
		return settings;
	}

	private FixedWidthParserSettings getBasicProfileSettings() {
		FixedWidthFields fields = new FixedWidthFields();
		fields.addField("profile_id", 12);
		fields.addField("username", 15);
		fields.addField("followers", 10);
		return getSettings(fields);
	}

	@Test
	public void parseProfileByFieldName() {
		Reader input = getReader("/examples/annotation/basic_profile.txt");

		for (ProfileByFieldName profile : new FixedWidthRoutines(getBasicProfileSettings()).iterate(ProfileByFieldName.class, input)) {
			println(profile);
		}
		printAndValidate();
	}

	@Test
	public void parseProfileByFieldPosition() {
		Reader input = getReader("/examples/annotation/basic_profile.txt");

		for (ProfileByFieldPosition profile : new FixedWidthRoutines(getBasicProfileSettings()).iterate(ProfileByFieldPosition.class, input)) {
			println(profile);
		}

		printAndValidate();
	}

	@Test
	public void parseProfileByMultipleFieldNames() {
		Reader input;

		input = getReader("/examples/annotation/basic_profile_2.txt");
		for (ProfileByMultipleFieldNames profile : new FixedWidthRoutines(getBasicProfileSettings()).iterate(ProfileByMultipleFieldNames.class, input)) {
			println(profile);
		}

		input = getReader("/examples/annotation/basic_profile_2.txt");
		for (ProfileByFieldName profile : new FixedWidthRoutines(getBasicProfileSettings()).iterate(ProfileByFieldName.class, input)) {
			println(profile);
		}

		printAndValidate();
	}
}
