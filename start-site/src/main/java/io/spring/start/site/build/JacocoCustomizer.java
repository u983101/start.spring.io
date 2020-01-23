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
 * Adds the plugin section into the pom file.
 *
 * @author Johnny Tam
 */
@ProjectGenerationConfiguration
@ConditionalOnRequestedDependency("Jacoco")
public class JacocoCustomizer implements BuildCustomizer<MavenBuild> {

	@Override
	public void customize(MavenBuild build) {
		build.plugins().add("org.jacoco", "jacoco-maven-plugin", (plugin) -> {
			plugin.execution("prepare-agent", (d) -> d.goal("prepare-agent"));
			plugin.execution("jacoco-report", (d) -> d.phase("test").goal("report"));
			plugin.execution("jacoco-check", (d) -> {
				d.goal("check");
				d.configuration((d1) -> {
					d1.add("haltOnFailure", "true");
					d1.configure("rules", (r) -> {
						r.configure("rule", (r1) -> {
							r1.add("element", "BUNDLE");

							r1.configure("limits", (l) -> {
								l.configure("limit", (l1) -> {
									l1.add("counter", "CLASS");
									l1.add("value", "COVEREDRATIO");
									l1.add("minimum", "0.6");
								});
								l.configure("limit1", (l2) -> {
									l2.add("counter", "METHOD");
									l2.add("value", "COVEREDRATIO");
									l2.add("minimum", "0.6");
								});
								l.configure("limit2", (l3) -> {
									l3.add("counter", "LINE");
									l3.add("value", "COVEREDRATIO");
									l3.add("minimum", "0.6");
								});
							});
						});
					});
				});
			});
		});
	}

}
