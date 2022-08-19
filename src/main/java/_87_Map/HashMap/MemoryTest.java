package _87_Map.HashMap;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

import java.util.Map;
import java.util.Objects;

public class MemoryTest {

    public static void main(String[] args) {
        Node node = new Node();
        // System.out.println(RamUsageEstimator.shallowSizeOf(node));
        System.out.println(ClassLayout.parseInstance(node).toPrintable());

        System.out.println("——————————我是分割线————————————————————线割分是我——————————");

        TreeNode treeNode = new TreeNode();
        // System.out.println(RamUsageEstimator.shallowSizeOf(treeNode));
        System.out.println(ClassLayout.parseInstance(treeNode).toPrintable());
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        int hash;
        K key;
        V value;
        Node<K,V> next;

        public Node() {
        }

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    /**
     * HashMap.Node subclass for normal LinkedHashMap entries.
     */
    static class Entry<K,V> extends Node<K,V> {
        Entry<K,V> before, after;

        public Entry() {
        }

        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

    static final class TreeNode<K,V> extends Entry<K,V> {
        TreeNode<K, V> parent;  // red-black tree links
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> prev;    // needed to unlink next upon deletion
        boolean red;

        public TreeNode() {
        }

        TreeNode(int hash, K key, V val, Node<K, V> next) {
            super(hash, key, val, next);
        }
    }
}