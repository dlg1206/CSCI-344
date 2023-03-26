package provided.symtable;
import java.util.ArrayList;

public class Function extends Symbol {

    private String name;
    private ArrayList<String> paramsType;
    private String returnType;
    private String scope;

    public Function(String name, ArrayList<String> paramsTypes, String returnType, String scope) {
        this.name = name;
        this.paramsType = paramsTypes;
        this.returnType = returnType;
        this.scope = scope;
    }

    public ArrayList<String> getParamsType() { 
        return paramsType;
    }

    public String getReturnType() { 
        return returnType;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Returns: " + this.returnType + " Scope: " + this.scope 
        + "\n\tExpected Params: " 
        +  (this.paramsType != null ? this.paramsType.toString() : "None"); 
    }
    
    
}
