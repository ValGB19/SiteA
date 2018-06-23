package javas;

import java.util.ArrayList;
import java.util.List;

public class Tree<E> {
  private List<Tree<E>> childs;
  private E info;
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public Tree(){
	  childs = new ArrayList();
  }
  
  public void addChild(Tree<E> param)
  {
    childs.add(param);
  }
  
  public List<Tree<E>> getChilds() {
    return childs;
  }
  
  public void setRoot(E param) {
    info = param;
  }
  
  public E getRoot() {
    return info;
  }
}