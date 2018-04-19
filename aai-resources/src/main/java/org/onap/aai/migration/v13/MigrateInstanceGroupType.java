/**
 * ============LICENSE_START=======================================================
 * org.onap.aai
 * ================================================================================
 * Copyright © 2017-2018 AT&T Intellectual Property. All rights reserved.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */
package org.onap.aai.migration.v13;
import java.util.Optional;

import org.onap.aai.migration.Enabled;
import org.onap.aai.migration.MigrationDangerRating;
import org.onap.aai.migration.MigrationPriority;
import org.onap.aai.migration.PropertyMigrator;
import org.onap.aai.serialization.engines.TransactionalGraphEngine;

import org.janusgraph.core.Cardinality;

@MigrationPriority(20)
@MigrationDangerRating(2)
@Enabled
public class MigrateInstanceGroupType extends PropertyMigrator{

    protected static final String TYPE_PROPERTY = "type";
    protected static final String INSTANCE_GROUP_TYPE_PROPERTY = "instance-group-type";
    protected static final String INSTANCE_GROUP_NODE_TYPE = "instance-group";

    public MigrateInstanceGroupType(TransactionalGraphEngine engine) {
        super(engine,TYPE_PROPERTY , INSTANCE_GROUP_TYPE_PROPERTY, String.class,Cardinality.SINGLE);
    }
    
    @Override
    public Optional<String[]> getAffectedNodeTypes() {
    	return Optional.of(new String[]{INSTANCE_GROUP_NODE_TYPE});
    }

    @Override
    public String getMigrationName() {
        return "MigrateInstanceGroupType";
    }

	@Override
	public boolean isIndexed() {
		return true;
	}

}