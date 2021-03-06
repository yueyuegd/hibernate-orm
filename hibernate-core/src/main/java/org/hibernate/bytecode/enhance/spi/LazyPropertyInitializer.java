/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.bytecode.enhance.spi;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.engine.spi.SharedSessionContractImplementor;

/**
 * Contract for controlling how lazy properties get initialized.
 * 
 * @author Gavin King
 */
public interface LazyPropertyInitializer {

	/**
	 * Marker value for uninitialized properties.
	 */
	Serializable UNFETCHED_PROPERTY = new Serializable() {
		@Override
		public String toString() {
			return "<lazy>";
		}

		public Object readResolve() {
			return UNFETCHED_PROPERTY;
		}
	};

	interface InterceptorImplementor {
		Set<String> getInitializedLazyAttributeNames();
		void attributeInitialized(String name);
	}

	/**
	 * Initialize the property, and return its new value.
	 *
	 * @param fieldName The name of the field being initialized
	 * @param entity The entity on which the initialization is occurring
	 * @param session The session from which the initialization originated.
	 *
	 * @return ?
	 */
	Object initializeLazyProperty(String fieldName, Object entity, SharedSessionContractImplementor session);

}
