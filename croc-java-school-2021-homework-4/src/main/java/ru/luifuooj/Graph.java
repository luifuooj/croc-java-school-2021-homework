package ru.luifuooj;

import java.util.*;

public class Graph {
    /**
     * Множество всех вершин.
     */
    private final Map<Long, Node<?>> nodeMap = new HashMap<>();

    /**
     * Дерево компонент связности, каждый компонент представляет собой TreeSet (уникальный элемент, хранимый в
     * упорядоченном виде)
     */
    private final TreeSet<ComparableSet<Node<?>>> treeSetList = new TreeSet<>();

    /**
     * Количество компонент связности.
     */
    private int componentCount = 0;

    /**
     * Обновление списка компонент связности.
     */
    private void refreshComponentCount() {
        this.treeSetList.clear();
        //Проходим по каждой вершине из множества всех вершин
        for (Node<?> eachNode: nodeMap.values()) {
            //Создаем новый TreeSet
            ComparableSet<Node<?>> newTreeSet = new ComparableSet<>();
            boolean isNew = true;
            for (ComparableSet<Node<?>> eachTree: treeSetList) {
                //Проверка, что вершина уже находится в дереве
                if (eachTree.contains(eachNode)) {
                    //Если вершина нашлась, то работаем с найденным деревом
                    newTreeSet = eachTree;
                    isNew = false;
                    break;
                }
            }
            //Если вершина не нашлась, добавляем ее
            newTreeSet.add(eachNode);
            //Добавляем все связные с этой вершиной компоненты
            newTreeSet.addAll(eachNode.getConnectedNodes().values());
            //Если не нашлась, используем новый
            if (isNew) {
                this.treeSetList.add(newTreeSet);
            }
        }
        this.componentCount = this.treeSetList.size();
    }

    /**
     * Добавление вершины.
     * @param node вершина
     */
    public void addNode(Node<?> node) {
        this.nodeMap.put(node.getId(), node);
        refreshComponentCount();
    }

    /**
     * Удаление вершины.
     * @param id идентификатор вершины
     */
    public void removeNode(Long id) {
        this.nodeMap.remove(id);
        refreshComponentCount();
    }

    /**
     * Добавление ребра.
     * @param id1 идентификатор вершины
     * @param id2 идентификатор вершины
     */
    public void addLink(Long id1, Long id2) {
        nodeMap.get(id1).addNode(nodeMap.get(id2));
        nodeMap.get(id2).addNode(nodeMap.get(id1));
        refreshComponentCount();
    }

    /**
     * Удаление ребра.
     * @param id1 идентификатор вершины
     * @param id2 идентификатор вершины
     */
    public void removeLink(Long id1, Long id2) {
        nodeMap.get(id1).removeNode(id2);
        nodeMap.get(id2).removeNode(id1);
        refreshComponentCount();
    }

    /**
     * Метод, возвращающий количество компонент связности.
     * @return количество компонент связности
     */
    public int getComponentCount() {
        return componentCount;
    }

    /**
     * Метод, возвращающий дерево компонент связности в упорядоченном виде.
     * @return список
     */
    public List<ComparableSet<Node<?>>> getSortedComponentList() {
        return new ArrayList<>(this.treeSetList);
    }
}
