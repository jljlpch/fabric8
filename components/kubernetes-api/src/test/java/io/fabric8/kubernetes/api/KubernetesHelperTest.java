/**
 *  Copyright 2005-2014 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.kubernetes.api;

import io.fabric8.kubernetes.api.model.PodListSchema;
import io.fabric8.kubernetes.api.model.PodSchema;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class KubernetesHelperTest {

	@Test
	public void testRemoveEmptyPods() throws Exception {
		
		PodSchema pod1 = new PodSchema();
		pod1.setId("test1");
		
		PodSchema pod2 = new PodSchema();
		
		PodListSchema podSchema = new PodListSchema();
		podSchema.getItems().add(pod1);
		podSchema.getItems().add(pod2);
		
		KubernetesHelper.removeEmptyPods(podSchema);
		
		assertNotNull(podSchema);
		assertEquals(1, podSchema.getItems().size());
	}

	@Test
	public void testfilterMatchesIdOrLabels() throws Exception {
		String text = "container=java,name=foo,food=cheese";
		String id = "foo";
		HashMap<String,String>map = new HashMap<>();
		map.put("container","java");
		map.put("name","foo");
		map.put("food","cheese");
		assertTrue(text + " should = " + map,KubernetesHelper.filterMatchesIdOrLabels(text, id, map));
	}
	
}
