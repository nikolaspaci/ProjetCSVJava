package column;


/**
 * The Class DescriptorColumn.
 */
public class DescriptorColumn extends Column {

	/**
	 * Instantiates a new descriptor column.
	 *
	 * @param dataType the data type
	 * @param name the name
	 */
	public DescriptorColumn(String dataType, String name) {
		super(name);
		this.dataType = dataType;
	}

	/** The data type. */
	private String dataType;

	/**
	 * Gets the data type.
	 *
	 * @return the data type
	 */
	public String getDataType() {
		return dataType;
	}
}
