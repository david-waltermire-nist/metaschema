package gov.nist.secauto.metaschema.model.xml;

import gov.nist.csrc.ns.oscal.metaschema.x10.Boolean;
import gov.nist.csrc.ns.oscal.metaschema.x10.FlagDocument;
import gov.nist.secauto.metaschema.datatype.MarkupString;
import gov.nist.secauto.metaschema.model.AbstractFlagInstance;
import gov.nist.secauto.metaschema.model.DataType;
import gov.nist.secauto.metaschema.model.FlagDefinition;
import gov.nist.secauto.metaschema.model.InfoElementDefinition;
import gov.nist.secauto.metaschema.model.Metaschema;
import gov.nist.secauto.metaschema.model.Type;

public class XmlFlagInstance extends AbstractFlagInstance {
	private final FlagDocument.Flag xFlag;
	private final LocalFlagDefinition localFlagDefinition;

	public XmlFlagInstance(FlagDocument.Flag xFlag, InfoElementDefinition parent) {
		super(parent);
		this.xFlag = xFlag;

		if (xFlag.isSetName()) {
			localFlagDefinition = new LocalFlagDefinition();
		} else {
			localFlagDefinition = null;
		}
	}

	@Override
	protected FlagDefinition getLocalFlagDefinition() {
		return localFlagDefinition;
	}

	@Override
	public String getName() {
		FlagDocument.Flag xFlag = getXmlFlag();
		return xFlag.isSetRef() ? xFlag.getRef() : xFlag.getName();
	}

	@Override
	public String getFormalName() {
		String retval = null;
		if (getXmlFlag().isSetFormalName()) {
			retval = getXmlFlag().getFormalName();
		} else if (isReference()) {
			retval = getFlagDefinition().getFormalName();
		}
		return retval;
	}

	@Override
	public MarkupString getDescription() {
		MarkupString retval = null;
		if (getXmlFlag().isSetDescription()) {
			retval = MarkupStringConverter.toMarkupString(getXmlFlag().getDescription());
		} else if (isReference()) {
			retval = getFlagDefinition().getDescription();
		}
		return retval;
	}

/* TODO: implement
	@Override
	public String getDescription() {
		String retval = null;
		if (xFlag.isSetDescription()) {
			retval = xFlag.getDescription();
		} else if (isReference()) {
			retval = getFlagDefinition().getDescription();
		}
		return retval;
	}

	@Override
	public String getRemarks() {
		String retval = null;
		if (xFlag.isSetRemarks()) {
			retval = xFlag.getRemarks();
		} else if (isReference()) {
			// TODO: append?
			retval = getFlagDefinition().getRemarks();
		}
		return retval;
	}
    

	@Override
	public String getAllowedValues() {
		String retval = null;
		if (xFlag.isSetRemarks()) {
			retval = xFlag.getAllowedValues();
		} else if (isReference()) {
			// TODO: ???
			retval = getFlagDefinition().getAllowedValues();
		}
		return retval;
	}
*/
	@Override
	public DataType getDatatype() {
		DataType retval;
		if (getXmlFlag().isSetAsType()) {
			retval = DataType.lookup(getXmlFlag().getAsType());
		} else if (isReference()) {
			retval = getFlagDefinition().getDatatype();
		} else {
			// the default
			retval = DataType.STRING;
		}
		return retval;
	}

	@Override
	public boolean isRequired() {
		boolean retval = false;
		if (getXmlFlag().isSetRequired()) {
			Boolean.Enum required = getXmlFlag().getRequired();
			if (Boolean.INT_YES == required.intValue()) {
				retval = true;
			}
		}
		return retval;
	}


	protected FlagDocument.Flag getXmlFlag() {
		return xFlag;
	}

    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
	private class LocalFlagDefinition implements FlagDefinition {

		public LocalFlagDefinition() {
		}

		@Override
		public String getName() {
			return XmlFlagInstance.this.getName();
		}

		@Override
		public Type getType() {
			return Type.FLAG;
		}

		@Override
		public Metaschema getContainingMetaschema() {
			return XmlFlagInstance.this.getContainingMetaschema();
		}

		@Override
		public String getFormalName() {
			return XmlFlagInstance.this.getFormalName();
		}

		@Override
		public DataType getDatatype() {
			return XmlFlagInstance.this.getDatatype();
		}

		@Override
		public MarkupString getDescription() {
			return XmlFlagInstance.this.getDescription();
		}

	}
}
