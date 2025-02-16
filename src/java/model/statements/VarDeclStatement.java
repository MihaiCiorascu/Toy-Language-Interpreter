package src.java.model.statements;

import src.java.adt.myDictionary.MyIDictionary;
import src.java.exceptions.IStatementException;
import src.java.model.ProgramState;
import src.java.model.types.IType;
import src.java.model.values.IValue;

public class VarDeclStatement implements IStatement {
    private String varName;
    private IType varType;

    public VarDeclStatement(String varName, IType type) {
        this.varName = varName;
        this.varType = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws IStatementException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (symTable.isDefined(this.varName)) {
            throw new IStatementException("!EXCEPTION! Variable '" + varName + "' already declared");
        }
        symTable.put(this.varName, this.varType.defaultValue());

        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws IStatementException{
        if(typeEnv.isDefined(this.varName)) {
            throw new IStatementException("!Variable Declaration Error! Variable name already exists");
        }

        typeEnv.put(this.varName, this.varType);
        return typeEnv;
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(this.varName, this.varType.deepCopy());
    }

    @Override
    public String toString() {
        return this.varType.toString() + " " + this.varName;
    }
}