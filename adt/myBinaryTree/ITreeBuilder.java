package adt.myBinaryTree;

public interface ITreeBuilder<IStatement> {
    MyBinaryTree<IStatement> buildTree(IStatement program);
}
