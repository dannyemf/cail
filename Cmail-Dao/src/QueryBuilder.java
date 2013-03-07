/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class QueryBuilder {
    
    public static <T> CQuery<T> from(Class<T> clase){
        return new CQuery<T>(clase);
    }
    
}
