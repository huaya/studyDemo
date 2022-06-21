package com.maxlong.study.algorithm.leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.maxlong.study.utils.FileUtil;

import java.util.*;

public class SolutionFindSmallestSetOfVertices {

    public static void main(String[] args) {
//        String input = "[[0,1],[0,2],[2,5],[3,4],[4,2]]";
        String input = FileUtil.readFileToStr("E:\\workspace-mxl\\studyDemo\\src\\main\\resources\\json\\temp.json", "UTF-8");

        List<List<Integer>> edges = JSON.parseObject(input, new TypeReference<List<List<Integer>>>() {
        });
        List<Integer> result = new SolutionFindSmallestSetOfVertices().findSmallestSetOfVertices(4331, edges);
        System.out.println(result);
    }

    public List<Integer> findSmallestSetOfVertices2(int n, List<List<Integer>> edges) {
        int[] arr = new int[n];
        for (List<Integer> list : edges) {
            arr[list.get(1)]++;
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0){
                result.add(i);
            }
        }
        return result;
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        Map<Integer, Set<Integer>> edgeMap = new HashMap<>();

        for (List<Integer> edge : edges) {
            Set<Integer> set = edgeMap.computeIfAbsent(edge.get(0), k -> new HashSet<>());
            set.add(edge.get(1));
            set.add(edge.get(0));
        }

        for (Map.Entry<Integer, Set<Integer>> entry : edgeMap.entrySet()) {
            Set<Integer> paths = entry.getValue();
            Deque<Integer> deque = new ArrayDeque<>();
            deque.addAll(paths);
            while (deque.size() > 0) {
                Integer edge = deque.removeFirst();
                Set<Integer> edgePath = edgeMap.get(edge);
                if (edgePath != null) {
                    for (Integer integer : edgeMap.get(edge)) {
                        if (paths.add(integer)) {
                            deque.addLast(integer);
                        }
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        Set<Integer> pathed = new HashSet<>();
        while (pathed.size() < n && !edgeMap.isEmpty()) {
            findResult(result, pathed, edgeMap);
        }
        result.sort(Integer::compareTo);
        return result;
    }

    private void findResult(List<Integer> result, Set<Integer> pathed, Map<Integer, Set<Integer>> edgeMap) {
        Integer maxLenEdge = null;
        Integer maxLen = 0;
        for (Map.Entry<Integer, Set<Integer>> entry : edgeMap.entrySet()) {
            Set<Integer> paths = entry.getValue();
            paths.removeAll(pathed);
            if (paths.size() > maxLen) {
                maxLen = paths.size();
                maxLenEdge = entry.getKey();
            }
        }
        result.add(maxLenEdge);
        pathed.addAll(edgeMap.get(maxLenEdge));
        edgeMap.remove(maxLenEdge);
    }
}
