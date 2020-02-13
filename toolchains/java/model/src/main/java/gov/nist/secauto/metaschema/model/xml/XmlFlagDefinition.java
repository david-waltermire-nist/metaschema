package gov.nist.secauto.metaschema.model.xml;

import java.util.Collections;
import java.util.Map;

import gov.nist.itl.metaschema.model.xml.DefineFlagDocument;
import gov.nist.secauto.metaschema.datatype.markup.MarkupLine;
import gov.nist.secauto.metaschema.model.info.definitions.AbstractFlagDefinition;
import gov.nist.secauto.metaschema.model.info.definitions.DataType;
import gov.nist.secauto.metaschema.model.info.definitions.FlagDefinition;
import gov.nist.secauto.metaschema.model.info.instances.FlagInstance;

public class XmlFlagDefinition extends AbstractFlagDefinition<XmlMetaschema> implements FlagDefinition {
	private final DefineFlagDocument.DefineFlag xFlag;

	public XmlFlagDefinition(DefineFlagDocument.DefineFlag xFlag, XmlMetaschema metaschema) {
		super(metaschema);
		this.xFlag = xFlag;
	}

	@Override
	public String getName() {
		return getXmlFlag().getName();
	}

	@Override
	public String getFormalName() {
		return getXmlFlag().getFormalName();
	}

	@Override
	public MarkupLine getDescription() {
		return MarkupStringConverter.toMarkupString(getXmlFlag().getDescription());
	}

	@Override
	public DataType getDatatype() {
		DataType retval;
		if (getXmlFlag().isSetAsType()) {
			retval = DataType.lookup(getXmlFlag().getAsType());
		} else {
			// the default
			retval = DataType.STRING;
		}
		return retval;
	}

	protected DefineFlagDocument.DefineFlag getXmlFlag() {
		return xFlag;
	}

	@Override
	public FlagInstance getFlagInstanceByName(String name) {
		return null;
	}

	@Override
	public Map<String, ? extends FlagInstance> getFlagInstances() {
		return Collections.emptyMap();
	}
}