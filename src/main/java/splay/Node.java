package splay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by gopalbala on 29/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node<T> {
    T data;
    Node<T> leftChild;
    Node<T> rightChild;
    Node<T> parent;
}
