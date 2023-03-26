package provided.symtable;
import java.util.HashMap;
import java.util.ArrayList;



public class SymTable {
    
    static private HashMap<String, Symbol> symTable;
    static private String currScope;

    static public void getNewSymTable() {
        symTable = new HashMap<>();
        currScope = "Global";
    }

    static public void addFunc(String name, ArrayList<String> paramsType, String returnType, String fileName, int lineNumber) {
        boolean containsName = symTable.containsKey(name);  
        Function containedFunc = containsName ? (Function) symTable.get(name) : null;
        boolean withSameParamsType = containsName ? paramsType.equals(containedFunc.getParamsType()) : false;
        boolean withSameReturnType = containsName ? returnType.equals(containedFunc.getReturnType()) : false;

        if (containsName && withSameParamsType && withSameReturnType) {
            System.err.println("Semantic Error: \nDuplicate function declaration: " + name + "\n" + fileName + ".jott:" + lineNumber);
        } else {
            symTable.put((name + "." + returnType), new Function(name, paramsType, returnType, currScope));
        }
    }

    static public Function getFunction(String name, String returnType) {
        if (symTable.containsKey(name + "." + returnType)) {
            return (Function) symTable.get(name + "." + returnType);
        }
        return null;
    }

    static public void addVar(String name, String type, String fileName, int lineNumber) {
        boolean containsName = symTable.containsKey(name);
        Var containedVar = containsName ? (Var) symTable.get(name) : null;
        boolean withSameScope = containsName ? currScope.equals(containedVar.getScope()) : false;
        boolean withSameType = containsName ? type.equals(containedVar.getname()) : false;
        if (containsName && withSameScope && withSameType) {
            System.err.println("Semantic Error: \nDuplicate local variable: " + name + "\n" + fileName + ".jott:" + lineNumber);
        } else {
            symTable.put(name, new Var(name, type, currScope));
        }
    } 

    static public Var getVar(String name, String type) {
        if (symTable.containsKey(name + "." + type)) {
            return (Var) symTable.get(name + "." + type);
        }
        return null;
    }

    static public void removeScopeLayer() {
        char[] result = currScope.toCharArray();
        for (int i=currScope.length()-1; i>=0; i--) {
            if (result[i] == '.') {
                currScope = currScope.substring(0, i);
            }
        }
    }

    static public void updateScope(String newScope) {
        if (!currScope.equals("Global")) {
            currScope = "Global";
        }
        currScope +=  "." + newScope;
    }


    static public String staticToString() {
        String result = "";
        for (String name: symTable.keySet()) {
            result += symTable.get(name).toString() + "\n";
        }
        return result;
    }
}
