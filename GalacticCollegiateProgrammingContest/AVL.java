package GalacticCollegiateProgrammingContest;

class BSTVertex {
  BSTVertex(Team t) {
    key = t;
    parent = left = right = null;
    height = 0;
    size = 1;
  }

  // all these attributes remain public to slightly simplify the code
  public BSTVertex parent, left, right;
  public Team key;
  public int height; 
  public int size; 
}

// This is just a sample implementation
// There are other ways to implement BST concepts...
class BST {
  public BSTVertex root;

  public BST() {
    root = null;
  }

  // public method called to search for a value v.
  // Return v if it is found in the BST otherwise return -1.
  // Here the assumption is that -1 is never a valid key value.
  public Team search(Team t) {
    BSTVertex res = search(root, t);
    return res == null ? new Team(-1, 0) : res.key;
  }

  // helper method to perform search
  public BSTVertex search(BSTVertex T, Team t) {
    if (T == null)
      return null; // not found
    else if (T.key.equals(t))
      return T; // found
    else if (T.key.compareTo(t) < 0) {
      return search(T.right, t); // search to the right
    } else {
      return search(T.left, t); // search to the left
    }
  }

  // public method called to find Minimum key value in BST
  public Team findMin() {
    return findMin(root);
  }

  // helper method to perform findMin
  public Team findMin(BSTVertex T) {
    if (T.left == null)
      return T.key; // this is the min
    else
      return findMin(T.left); // go to the left
  }

  // public method called to find Maximum key value in BST
  public Team findMax() {
    return findMax(root);
  }

  // helper method to perform findMax
  public Team findMax(BSTVertex T) {
    if (T.right == null)
      return T.key; // this is the max
    else
      return findMax(T.right); // go to the right
  }

  // public method to find successor to given value v in BST.
  public Team successor(Team t) {
    BSTVertex vPos = search(root, t);
    return vPos == null ? new Team(-1, 0) : successor(vPos);
  }

  // helper recursive method to find successor to for a given vertex T in BST
  public Team successor(BSTVertex T) {
    if (T.right != null) // this subtree has right subtree
      return findMin(T.right); // the successor is the minimum of right subtree
    else {
      BSTVertex par = T.parent;
      BSTVertex cur = T;
      // if par(ent) is not root and cur(rent) is its right children
      while ((par != null) && (cur.equals(par.right))) {
        cur = par; // continue moving up
        par = cur.parent;
      }
      return par == null ? new Team(-1, 0) : par.key; // this is the successor of T
    }
  }

  // public method to find predecessor to given value v in BST
  public Team predecessor(Team t) {
    BSTVertex vPos = search(root, t);
    return vPos == null ? new Team(-1, 0) : predecessor(vPos);
  }

  // helper recursive method to find predecessor to for a given vertex T in BST
  public Team predecessor(BSTVertex T) {
    if (T.left != null) // this subtree has left subtree
      return findMax(T.left); // the predecessor is the maximum of left subtree
    else {
      BSTVertex par = T.parent;
      BSTVertex cur = T;
      // if par(ent) is not root and cur(rent) is its left children
      while ((par != null) && (cur.equals(par.left))) {
        cur = par; // continue moving up
        par = cur.parent;
      }
      return par == null ? new Team(-1, 0) : par.key; // this is the successor of T
    }
  }

  // public method called to perform inorder traversal
  public void inorder() {
    inorder(root);
    System.out.println();
  }

  // helper method to perform inorder traversal
  public void inorder(BSTVertex T) {
    if (T == null)
      return;
    inorder(T.left); // recursively go to the left
    System.out.println(T.key); // visit this BST node
    inorder(T.right); // recursively go to the right
  }

  // public method called to insert a new key with value v into BST
  public void insert(Team t) {
    root = insert(root, t);
  }

  // helper recursive method to perform insertion of new vertex into BST
  public BSTVertex insert(BSTVertex T, Team t) {
    if (T == null)
      return new BSTVertex(t); // insertion point is found
    if (T.key.compareTo(t) < 0) { // search to the right
      T.right = insert(T.right, t);
      T.right.parent = T;
    } else { // search to the left
      T.left = insert(T.left, t);
      T.left.parent = T;
    }
    if (BF(T) == 2 && (BF(T.left) == 0 || BF(T.left) == 1)) {
      T = rotateRight(T);
    } else if (BF(T) == 2 && BF(T.left) == -1) {
      T.left = rotateLeft(T.left);
      T = rotateRight(T);
    } else if (BF(T) == -2 && (BF(T.right) == -1 || BF(T.right) == 0)) {
      T = rotateLeft(T);
    } else if (BF(T) == -2 && BF(T.right) == 1) {
      T.right = rotateRight(T.right);
      T = rotateLeft(T);
    }
    T.height = Math.max(upHeight(T.left), upHeight(T.right)) + 1;
    T.size = upSize(T.left) + upSize(T.right) + 1;
    return T; // return the updated BST
  }

  // public method to delete a vertex containing key with value v from BST
  public void delete(Team t) {
    root = delete(root, t);
  }

  // helper recursive method to perform deletion
  public BSTVertex delete(BSTVertex T, Team t) {
    if (T == null)
      return T; // cannot find the item to be deleted
    if (T.key.compareTo(t) < 0) { // search to the right
      T.right = delete(T.right, t);
      // balance
      if (BF(T) == 2 && (BF(T.left) == 0 || BF(T.left) == 1)) {
        T = rotateRight(T);
      } else if (BF(T) == 2 && BF(T.left) == -1) {
        T.left = rotateLeft(T.left);
        T = rotateRight(T);
      } else if (BF(T) == -2 && (BF(T.right) == -1 || BF(T.right) == 0)) {
        T = rotateLeft(T);
      } else if (BF(T) == -2 && BF(T.right) == 1) {
        T.right = rotateRight(T.right);
        T = rotateLeft(T);
      }
    } else if (T.key.compareTo(t) > 0) { // search to the left
      T.left = delete(T.left, t);
      // balance
      if (BF(T) == 2 && (BF(T.left) == 0 || BF(T.left) == 1)) {
        T = rotateRight(T);
      } else if (BF(T) == 2 && BF(T.left) == -1) {
        T.left = rotateLeft(T.left);
        T = rotateRight(T);
      } else if (BF(T) == -2 && (BF(T.right) == -1 || BF(T.right) == 0)) {
        T = rotateLeft(T);
      } else if (BF(T) == -2 && BF(T.right) == 1) {
        T.right = rotateRight(T.right);
        T = rotateLeft(T);
      }

    } else { // this is the node to be deleted
      if (T.left == null && T.right == null) // this is a leaf
        T = null; // simply erase this node
      else if (T.left == null && T.right != null) { // only one child at right
        T.right.parent = T.parent;
        T = T.right; // bypass T
      } else if (T.left != null && T.right == null) { // only one child at left
        T.left.parent = T.parent;
        T = T.left; // bypass T
      } else { // has two children, find successor
        Team successorV = successor(t);
        T.key = successorV; // replace this key with the successor's key
        T.right = delete(T.right, successorV); // delete the old successorV
      }
    }
    if (T != null) {
      T.height = Math.max(upHeight(T.left), upHeight(T.right)) + 1;
      T.size = upSize(T.left) + upSize(T.right) + 1;
    }
    return T; // return the updated BST
  }

  public int BF(BSTVertex T) {
    if (T.left == null && T.right == null) {
      return 0;
    }
    if (T.right == null) {
      return T.left.height + 1;
    }
    if (T.left == null) {
      return 0 - (T.right.height + 1);
    }
    return T.left.height - T.right.height;
  }

  public BSTVertex rotateLeft(BSTVertex T) {
    BSTVertex w = T.right;
    w.parent = T.parent;
    T.parent = w;
    T.right = w.left;
    if (w.left != null) {
      w.left.parent = T;
    }
    w.left = T;
    T.height = Math.max(upHeight(T.left), upHeight(T.right)) + 1;
    w.height = Math.max(upHeight(w.left), upHeight(w.right)) + 1;
    T.size = upSize(T.left) + upSize(T.right) + 1;
    w.size = upSize(w.left) + upSize(w.right) + 1;
    return w;
  }

  public BSTVertex rotateRight(BSTVertex T) {
    BSTVertex w = T.left;
    w.parent = T.parent;
    T.parent = w;
    T.left = w.right;
    if (w.right != null) {
      w.right.parent = T;
    }
    w.right = T;
    T.height = Math.max(upHeight(T.left), upHeight(T.right)) + 1;
    w.height = Math.max(upHeight(w.left), upHeight(w.right)) + 1;
    T.size = upSize(T.left) + upSize(T.right) + 1;
    w.size = upSize(w.left) + upSize(w.right) + 1;
    return w;
  }

  public int upHeight(BSTVertex T) {
    if (T == null) {
      return 0;
    } else {
      return T.height;
    }
  }

  public int upSize(BSTVertex T) {
    if (T == null) {
      return 0;
    } else {
      return T.size;
    }
  }

  public int upRank(Team key) {
    return upRank(root, key);
  }

  public int upRank(BSTVertex T, Team key) {
    if (T == null) { // if not found
      return 0;
    }
    if (T.key.compareTo(key) == 0) {
      return upSize(T.left) + 1;
    } else if (T.key.compareTo(key) > 0) {
      return upRank(T.left, key);
    } else {
      return upSize(T.left) + 1 + upRank(T.right, key);
    }
  }

  public BSTVertex findID(int id) {
    return findID(root, id);
  }

  public BSTVertex findID(BSTVertex T, int id) {
    if (T.key.id == id) {
      return T;
    }
    BSTVertex t = null;
    if (T.left != null) {
      t = findID(T.left, id);
    }
    if (T.right != null && t == null) {
      t = findID(T.right, id);
    }
    return t;
  }
}
