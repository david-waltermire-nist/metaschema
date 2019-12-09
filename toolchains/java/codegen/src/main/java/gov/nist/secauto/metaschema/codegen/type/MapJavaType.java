package gov.nist.secauto.metaschema.codegen.type;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MapJavaType extends AbstractCollectionJavaType {
	private final JavaType keyClass;

	MapJavaType(Class<?> keyClass, JavaType valueClass) {
		this(new ClassJavaType(keyClass), valueClass);
	}

	MapJavaType(JavaType keyClass, JavaType itemClass) {
		super(Map.class, itemClass);
		Objects.requireNonNull(keyClass);
		this.keyClass = keyClass;
	}

	protected JavaType getKeyClass() {
		return keyClass;
	}

	@Override
	public Set<JavaType> getImports(JavaType classType) {
		Set<JavaType> retval = new HashSet<>(super.getImports(classType));
		retval.addAll(getKeyClass().getImports(classType));
		return Collections.unmodifiableSet(retval);
	}

	@Override
	protected String getGenerics(JavaType classType) {
		return getKeyClass().getType(classType) + "," + getValueClass().getType(classType);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((keyClass == null) ? 0 : keyClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof MapJavaType)) {
			return false;
		}
		MapJavaType other = (MapJavaType) obj;
		if (keyClass == null) {
			if (other.keyClass != null) {
				return false;
			}
		} else if (!keyClass.equals(other.keyClass)) {
			return false;
		}
		return true;
	}
}
