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
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Adds OWASP suppresion file.
 *
 * @author Johnny Tam
 */
public class DependencySuppressionContributor implements ProjectContributor {

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path file = Files.createFile(projectRoot.resolve("dependency-suppression.xml"));

		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			writer.println(
					"<suppressions xmlns=\"https://jeremylong.github.io/DependencyCheck/dependency-suppression.1.3.xsd\">");
			writer.println("</suppressions>");
		}
	}

}
