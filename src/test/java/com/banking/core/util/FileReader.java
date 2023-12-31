package com.banking.core.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.lang3.exception.UncheckedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class FileReader {

	@Autowired
	private ResourceLoader resourceLoader;

	public String readAsString(String filePath) {
		Resource resource = resourceLoader.getResource("classpath:" + filePath);
		return toString(resource);
	}

	private String toString(Resource resource) {
		try (Reader reader = new InputStreamReader(resource.getInputStream())) {
			return FileCopyUtils.copyToString(reader);
		} catch (IOException ex) {
			throw new UncheckedException(ex);
		}
	}
}
