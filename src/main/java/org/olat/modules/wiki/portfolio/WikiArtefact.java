/**
 * <a href="http://www.openolat.org">
 * OpenOLAT - Online Learning and Training</a><br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at the
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache homepage</a>
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Initial code contributed and copyrighted by<br>
 * frentix GmbH, http://www.frentix.com
 * <p>
 */
package org.olat.modules.wiki.portfolio;

import org.olat.fileresource.types.WikiResource;
import org.olat.portfolio.model.artefacts.AbstractArtefact;

/**
 * 
 * Description:<br>
 * TODO: srosse Class Description for WikiArtefact
 * 
 * <P>
 * Initial Date:  7 oct. 2010 <br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 */
public class WikiArtefact extends AbstractArtefact {

	private static final long serialVersionUID = -3208512062986405560L;
	public static final String ARTEFACT_TYPE = WikiResource.TYPE_NAME;

	@Override
	public String getIcon() {
		return "o_wiki_icon";
	}

	@Override
	public String getResourceableTypeName() {
		return ARTEFACT_TYPE;
	}

}
