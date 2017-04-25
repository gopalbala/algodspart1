package redblack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by gopalbala on 23/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node<T> {
    T data;
    NodeColor color;
    Node<T> leftChild;
    Node<T> rightChild;
    Node<T> parent;

    @Override
    public String toString() {
        return data.toString();
    }
}
