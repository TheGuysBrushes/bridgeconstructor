package expertsystem;

/**
 *
 * @author Florian
 */
public class Comparison extends Word{
	private final Operator op;
	private final float valCondition;
	
	/**
	 * Constructeur
	 * @author florian
	 * 
	 * @param varName
	 * @param _op
	 * @param _valCondition
	 */
	public Comparison(String varName, Operator _op, float _valCondition){
		super(varName);
		op= _op;
		valCondition= _valCondition;		
	}
	
//	#########################
//	######	Getters	#########
//	#########################
	
	/**
	 * Retour sous forme de chaine de la classe
     * @return 
	 */
	public String toString(){
		return getName() +" "+ op +" "+ valCondition ;
	}

	/**
	 * Retourne si la comparaison est exacte ou non
	 * @author florian
     * @param valeurVariable
     * @return 
	 */
	public boolean isTrue(float valeurVariable) {
		AIEngine.flux.print("  vrai avec : "+ valeurVariable +" ? -> ");
		
		if (op.equals(Operators.inf)){
			if (valeurVariable < valCondition) {
                return true;
            }
		}else if (op.equals(Operators.sup)){
			if (valeurVariable > valCondition) {
                return true;
            }
		}else if (op.equals(Operators.equal)){
			if (valeurVariable == valCondition) {
                return true;
            }
		}else if (op.equals(Operators.sup_equal)){
			if (valeurVariable >= valCondition) {
                return true;
            }
		}else if (op.equals(Operators.inf_equal)){
			if (valeurVariable <= valCondition) {
                return true;
            }
		}
//			Cas par défaut
		return false; 
	}

    /**
     *
     * @return
     */
    
	public String getVal() {
		return ""+ valCondition;
	}

    /**
     *
     * @param value
     * @return
     */
    public boolean respectValue(String value) {
		float val = Float.parseFloat(value);
		boolean same= isTrue(val);
		AIEngine.flux.println(same);
		
		return same;
	}
    
    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean respectValue(Word other) {
        if(other.getClass()==Comparison.class){
            return respectValue(other.getVal());
        }
        else {
            return false;
        }
    }
    

}
