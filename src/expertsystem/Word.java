package expertsystem;

/**
 *
 * @author Florian
 */
public abstract class Word {

    /**
     *
     */
    private String name;

    /**
     *
     * @param _name
     */
    public Word(String _name){
		name= _name;
	}
	
    /**
     *
     * @return
     */
    @Override
    public abstract String toString();
	
    /**
     *
     * @return
     */
    public abstract String getVal();

    /**
     *
     * @param value
     * @return
     */
    public abstract boolean respectValue(String value);

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public abstract boolean respectValue(Word other);

}
