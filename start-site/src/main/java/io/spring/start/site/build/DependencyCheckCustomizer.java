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

package io.spring.start.site.build;

import io.spring.initializr.generator.buildsystem.maven.MavenBuild;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * Adds the plugin section to the pom file.
 *
 * @author Johnny Tam
 */
@ProjectGenerationConfiguration
@ConditionalOnRequestedDependency("dependency-check")
public class DependencyCheckCustomizer implements BuildCustomizer<MavenBuild> {

	@Override
	public void customize(MavenBuild build) {
		build.plugins().add("org.owasp", "dependency-check-maven", (plugin) -> {
			plugin.execution("check", (check) -> check.goal("check"));
			plugin.configuration((config) -> {
				config.add("assemblyAnalyzerEnabled", "false");
				config.add("failBuildOnCVSS", "8");
				config.configure("suppressionFiles", (c1) -> c1.add("suppressionFile", "dependency-suppression.xml"));

			});
		});
	}

}
