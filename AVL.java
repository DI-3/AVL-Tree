import java.util.*;
import java.math.*;
class AVNode{

   char data ;

   AVNode lchild;
   AVNode rchild;
   AVNode parent;
   int height;

   public AVNode(char data,AVNode parent){
   
   this.data = data ;
   this.rchild = null;
   this.lchild = null;
   this.parent = parent; 
   this.height = 1;
   
  }

}

class AVL{

    AVNode root ;

    public AVL(){

    	root = null;
    }

    public void fix_height(AVNode node){

       int hl = heightofTree(node.lchild);
       int hr = heightofTree(node.rchild);

       node.height = (hl > hr ? (hl + 1):(hr +  1));
       return;

    } 


    public AVNode rebalance_right(AVNode node){

        if(balanceFactor(node) < -1) {
            
            if(heightofTree(node.rchild.rchild) > heightofTree(node.rchild.lchild))
              {
                node = rotL(node);
                return node;
              }
            else{

               node.rchild = rotR(node.rchild);
               node = rotL(node);
               return node;
             }
            
            }  else{
              
                       fix_height(node);
                       return node ;
            } 

    }  

    public AVNode rebalance_left(AVNode node){

      
         if(balanceFactor(node) > 1){

             if(heightofTree(node.lchild.lchild) > heightofTree(node.lchild.rchild)){

                node = rotR(node);
                return node;
             }
             else{

                node.lchild = rotL(node.lchild);
                node = rotR(node);
                return node;
             }
          }else{

                  fix_height(node);
                  return node ;
          }   
  

         }



   public AVNode rotR(AVNode node){

      AVNode temp = node.lchild ;

      node.lchild = temp.rchild ;
      temp.rchild = node ;
      
      //after the Rotation the heights of the rotated nodes need to be set

      fix_height(node.rchild);
      fix_height(node);
      
      return temp;

   }

  public AVNode rotL(AVNode node){

     AVNode temp = node.rchild;

     node.rchild = temp.lchild;
     temp.lchild = node;

      //after the Rotation the heights of the rotated nodes need to be set

     fix_height(node.lchild);
     fix_height(node);
     return temp;

  } 
    
    
       
      
    public int balanceFactor(AVNode node){

      
       return  heightofTree(node.lchild) - heightofTree(node.rchild);


    }

   


    public AVNode insertAVNode(AVNode node,char data,AVNode parent){

            if(node == null) return new AVNode(data,parent);

            if(node.data  > data){
                 node.lchild = insertAVNode(node.lchild,data,node);
                 node = rebalance_left(node);
            }    
            else {
            	   node.rchild = insertAVNode(node.rchild,data,node); 
                   node = rebalance_right(node);
            }
            return node;
    }
    
    public void insert(char data){

       this.root = insertAVNode(root,data,null); 

    }  
   
    public int heightofTree(AVNode node){


           if(node == null)
                return 0;

           return  ( 1 + Math.max(heightofTree(node.lchild) ,heightofTree(node.rchild)));           

    }
   

   public void levelOrder(){

        AVNode node = this.root ;

        Queue<AVNode> q = new LinkedList<AVNode>();
        q.add(node);

        while(!q.isEmpty()){

           AVNode temp = q.remove();
           System.out.print(temp.data);

           if(temp.parent != null)
           System.out.print(" :"+temp.parent.data);

           System.out.println();
           if(temp.lchild != null)
           	       q.add(temp.lchild);
           if(temp.rchild != null)
                    q.add(temp.rchild);	   


        }

    }


    public static void main(String[] args){

    
     AVL t   = new AVL();

       // t.insert('H');
       //  t.levelOrder();	
       //  System.out.println();
       // t.insert('A');
       //  t.levelOrder();
       //  System.out.println();	
       // t.insert('M');
       //  t.levelOrder();	
       //  System.out.println();
       // t.insert('Z');
       //  t.levelOrder();	
       //  System.out.println();
       // t.insert('B');
       //  t.levelOrder();	
       //  System.out.println();
       // t.insert('E');
       // t.insert('Y');
       // t.levelOrder();	
       // System.out.println();
       // System.out.println(t.heightofTree(t.root));

       t.insert('3');
       t.insert('5');
       t.insert('4');
       //t.insert('4');
       t.levelOrder();  

      // System.out.println(t.heightTree(t.root));
    }  



}