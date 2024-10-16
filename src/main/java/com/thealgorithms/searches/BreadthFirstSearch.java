package com.thealgorithms.searches;

import com.thealgorithms.datastructures.Node;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

/**
 * Breadth-First Search implementation for tree/graph traversal.
 * @author: caos321
 * @co-author: manishraj27
 */
public class BreadthFirstSearch<T> {
    private final List<T> visited = new ArrayList<>();
    private final Set<T> visitedSet = new HashSet<>();  // For O(1) lookups

    public Optional<Node<T>> search(final Node<T> root, final T value) {
        // Handle null value search
        if (value == null) {
            return Optional.empty();
        }

        // Handle null root
        if (root == null) {
            return Optional.empty();
        }

        // Clear previous search results
        visited.clear();
        visitedSet.clear();

        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.offer(root);
        visitedSet.add(root.getValue());
        visited.add(root.getValue());

        while (!queue.isEmpty()) {
            final Node<T> current = queue.poll();

            // Check for target value
            if (value.equals(current.getValue())) {
                return Optional.of(current);
            }

            // Process children
            for (Node<T> child : current.getChildren()) {
                if (child != null && !visitedSet.contains(child.getValue())) {
                    queue.offer(child);
                    visitedSet.add(child.getValue());
                    visited.add(child.getValue());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the list of nodes in the order they were visited.
     * @return List of visited nodes
     */
    public List<T> getVisited() {
        return new ArrayList<>(visited);  // Return a defensive copy
    }
}
