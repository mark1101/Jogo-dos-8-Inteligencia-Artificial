package JogoModelo;

import java.util.Collection;

/**
 * 
 * @author Mark
 * 
 */
public interface Estado extends Comparable<Estado> { // parametro de entrada objeto do tipo estado 

	Collection<Estado> movimento();
	Estado getPai();

}