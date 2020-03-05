package column;


/**
 * The Class AnonymeColumn.
 */
public class AnonymeColumn extends Column {

	/**
	 * Instantiates a new anonyme column.
	 *
	 * @param name the name
	 */
	public AnonymeColumn(String name) {
		super(name);
	}

	/** The change to. */
	private String changeTo;

	/**
	 * Gets the change to.
	 *
	 * @return the change to
	 */
	public String getChangeTo() {
		return changeTo;
	}
}
