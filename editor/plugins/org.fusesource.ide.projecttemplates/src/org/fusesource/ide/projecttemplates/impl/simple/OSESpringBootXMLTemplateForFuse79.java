/*******************************************************************************
 * Copyright (c) 2021 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.fusesource.ide.projecttemplates.impl.simple;

import java.util.Arrays;
import java.util.List;

import org.fusesource.ide.camel.model.service.core.util.FuseBomFilter;
import org.fusesource.ide.foundation.core.util.VersionUtil;
import org.fusesource.ide.projecttemplates.adopters.configurators.TemplateConfiguratorSupport;
import org.fusesource.ide.projecttemplates.adopters.creators.TemplateCreatorSupport;
import org.fusesource.ide.projecttemplates.util.CommonNewProjectMetaData;
import org.fusesource.ide.projecttemplates.wizards.pages.model.EnvironmentData;

public class OSESpringBootXMLTemplateForFuse79 extends AbstractOSESpringBootXMLTemplate {

	private static final String MINIMAL_COMPATIBLE_CAMEL_VERSION = "2.23.2.fuse-790";

	@Override
	public TemplateConfiguratorSupport getConfigurator() {
		return new MavenConfiguratorForOSESpringBootTemplate(FuseBomFilter.BOM_FUSE_71_SPRINGBOOT);
	}

	@Override
	public TemplateCreatorSupport getCreator(CommonNewProjectMetaData projectMetaData) {
		return new OSEUnzipTemplateCreator("7.9");
	}
	
	@Override
	public boolean isCompatible(EnvironmentData environment) {
		return super.isCompatible(environment)
				&& new VersionUtil().isStrictlyGreaterThan(environment.getCamelVersion(), MINIMAL_COMPATIBLE_CAMEL_VERSION)
				// exceptionally using a string comparator as it needs to compare 2.23.2.xxx with 2.23.2.yyy. And Maven Comparator used is not doing it
				&& environment.getCamelVersion().compareTo(MINIMAL_COMPATIBLE_CAMEL_VERSION) > 0;
	}
	
	@Override
	public List<String> getJavaExecutionEnvironments() {
		return Arrays.asList("JavaSE-1.8", "JavaSE-11");
	}

}
