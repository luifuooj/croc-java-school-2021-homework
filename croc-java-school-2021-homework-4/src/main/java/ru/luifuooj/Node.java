package ru.luifuooj;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
    /**
     * Множество смежных вершин.
     */
    private final Map<Long, Node<?>> connectedNodes = new HashMap<>();

    /**
     * Уникальный идентификатор.
     */
    private final Long id;

    /**
     * Бизнес - элемент вершины.
     */
    private final T value;

    public Node(T value, Long id) {
        this.value = value;
        this.id = id;
    }

    /**
     * Добавление вершины в список смежных вершин.
     * @param node вершина
     */
    public void addNode(Node<?> node) {
        this.connectedNodes.put(node.getId(), node);
    }

    /**
     * Удаление вершины из списка смежных вершин.
     * @param id идентификатор вершины
     */
    public void removeNode(Long id) {
        this.connectedNodes.remove(id);
    }

    public Map<Long, Node<?>> getConnectedNodes() {
        return connectedNodes;
    }

    public T getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
