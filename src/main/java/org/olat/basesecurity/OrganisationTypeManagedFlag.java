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
package org.olat.basesecurity;

import java.util.Arrays;

import org.olat.core.CoreSpringFactory;
import org.olat.core.logging.OLog;
import org.olat.core.logging.Tracing;
import org.olat.core.util.StringHelper;

/**
 * 
 * Initial date: 15 mai 2018<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public enum OrganisationTypeManagedFlag {
	
	all,
	 identifier(all),
	 displayName(all),
	 description(all),
	 cssClass(all),
	 externalId(all),
	 subTypes(all),
	 delete(all);
	
	private OrganisationTypeManagedFlag[] parents;
	private static final OLog log = Tracing.createLoggerFor(OrganisationTypeManagedFlag.class);
	public static final OrganisationTypeManagedFlag[] EMPTY_ARRAY = new OrganisationTypeManagedFlag[0];
	
	private static OrganisationModule organisationModule;

	private OrganisationTypeManagedFlag() {
		//
	}
	
	private OrganisationTypeManagedFlag(OrganisationTypeManagedFlag... parents) {
		if(parents == null) {
			this.parents = new OrganisationTypeManagedFlag[0];
		} else {
			this.parents = parents;
		}
	}
	
	public static OrganisationTypeManagedFlag[] toEnum(String flags) {
		if(StringHelper.containsNonWhitespace(flags)) {
			String[] flagArr = flags.split(",");
			OrganisationTypeManagedFlag[] flagEnums = new OrganisationTypeManagedFlag[flagArr.length];
	
			int count = 0;
			for(String flag:flagArr) {
				if(StringHelper.containsNonWhitespace(flag)) {
					try {
						OrganisationTypeManagedFlag flagEnum = valueOf(flag);
						flagEnums[count++] = flagEnum;
					} catch (Exception e) {
						log.warn("Cannot parse this managed flag: " + flag, e);
					}
				}
			}
			
			if(count != flagEnums.length) {
				flagEnums = Arrays.copyOf(flagEnums, count);
			}
			return flagEnums;
		}
		return EMPTY_ARRAY;
	}
	
	public static String toString(OrganisationTypeManagedFlag... flags) {
		StringBuilder sb = new StringBuilder();
		if(flags != null && flags.length > 0 && flags[0] != null) {
			for(OrganisationTypeManagedFlag flag:flags) {
				if(flag != null) {
					if(sb.length() > 0) sb.append(",");
					sb.append(flag.name());
				}
			}
		}
		return sb.length() == 0 ? null : sb.toString();
	}
	
	public static boolean isManaged(OrganisationType organisation, OrganisationTypeManagedFlag marker) {
		if(organisationModule == null) {
			organisationModule = CoreSpringFactory.getImpl(OrganisationModule.class);
		}
		if(!organisationModule.isOrganisationManaged()) {
			return false;
		}
		return (organisation != null && (contains(organisation, marker) || contains(organisation, marker.parents)));
	}
	
	public static boolean isManaged(OrganisationTypeManagedFlag[] flags, OrganisationTypeManagedFlag marker) {
		if(organisationModule == null) {
			organisationModule = CoreSpringFactory.getImpl(OrganisationModule.class);
		}
		if(!organisationModule.isOrganisationManaged()) {
			return false;
		}
		
		return (flags != null && (contains(flags, marker) || contains(flags, marker.parents)));
	}
	
	private static boolean contains(OrganisationType organisation, OrganisationTypeManagedFlag... markers) {
		if(organisation == null) return false;
		OrganisationTypeManagedFlag[] flags = organisation.getManagedFlags();
		return contains(flags, markers);
	}

	private static boolean contains(OrganisationTypeManagedFlag[] flags, OrganisationTypeManagedFlag... markers) {
		if(flags == null || flags.length == 0) return false;

		for(OrganisationTypeManagedFlag flag:flags) {
			for(OrganisationTypeManagedFlag marker:markers) {
				if(flag.equals(marker)) {
					return true;
				}
			}
		}
		return false;
	}
}