package ru.luifuooj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GraphTest {

    Graph graph = new Graph();

    @BeforeEach
    public void setup() {
        //Создание вершин
        Node<String> node1 = new Node<>("1", 1L);
        Node<Boolean> node2 = new Node<>(true, 2L);
        Node<Long> node3 = new Node<>(3L, 3L);
        Node<Double> node4 = new Node<>(4d, 4L);
        Node<Integer> node5 = new Node<>(5, 5L);
        Node<Integer> node6 = new Node<>(6, 6L);
        Node<Integer> node7 = new Node<>(7, 7L);
        Node<Integer> node8 = new Node<>(8, 8L);
        Node<Integer> node9 = new Node<>(9, 9L);
        Node<Integer> node10 = new Node<>(10, 10L);
        Node<Integer> node11 = new Node<>(11, 11L);
        Node<Integer> node12 = new Node<>(12, 12L);
        Node<Integer> node13 = new Node<>(13, 13L);
        Node<Integer> node14 = new Node<>(14, 14L);

        //Добавление вершин
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);
        graph.addNode(node7);
        graph.addNode(node8);
        graph.addNode(node9);
        graph.addNode(node10);
        graph.addNode(node11);
        graph.addNode(node12);
        graph.addNode(node13);
        graph.addNode(node14);

        //Добавление ребер
        graph.addLink(1L,2L);
        graph.addLink(1L,4L);
        graph.addLink(4L,5L);
        graph.addLink(5L,2L);
        graph.addLink(2L,6L);
        graph.addLink(5L,7L);
        graph.addLink(6L,7L);
        graph.addLink(6L,3L);

        graph.addLink(8L,9L);
        graph.addLink(9L,10L);

        graph.addLink(11L,12L);
        graph.addLink(11L,13L);
        graph.addLink(13L,12L);
    }

    @Test
    public void testGetComponentCount() {
        Assertions.assertEquals(4, graph.getComponentCount());
    }

    @Test
    public void getSortedComponentList() {
        List<ComparableSet<Node<?>>> componentList = graph.getSortedComponentList();
        for (int i = 0; i < componentList.size() - 1; i++) {
            Assertions.assertTrue(componentList.get(i).size() <= componentList.get(i + 1).size());
        }
    }
}
