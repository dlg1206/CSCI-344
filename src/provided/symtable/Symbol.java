package provided.symtable;

public abstract class Symbol {
    private String name;
    private String scope;

    public String getname() {
        return this.name;
    }   

    public String getScope() {
        return this.scope;
    }

}
