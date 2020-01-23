/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.start.site.contributor;

import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Adds jenkinsFile.
 *
 * @author Johnny Tam
 */
public class JenkinsfileContributor implements ProjectContributor {

	@Value("classpath:templates/jenkinsFile")
	Resource jenkinsFileContents;

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path file = Files.createFile(projectRoot.resolve("Jenkinsfile"));

		String result = "";
		result = new String(Files.readAllBytes(this.jenkinsFileContents.getFile().toPath()));

		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
			writer.println(result);
		}
	}

}
