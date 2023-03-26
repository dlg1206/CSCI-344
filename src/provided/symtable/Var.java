package provided.symtable;

public class Var extends Symbol{
    String name;
    String type;
    String scope;
    
    public Var(String name, String type, String scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Type: " + this.type + " Scope: " + this.scope; 
    }

    
}
