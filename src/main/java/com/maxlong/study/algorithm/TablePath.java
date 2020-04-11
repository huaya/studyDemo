package com.maxlong.study.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * Created on 2020/4/11.
 *
 * @author MaXiaolong
 */
@Data
public class TablePath {

    private List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) {
        List<TablePath> paths = new ArrayList<>();

        int xSize = 4;
        int ySize = 6;

        for(int q = 0; q < 1000; q++){
            TablePath tablePath = new TablePath();

            int m = 1;
            int n = 1;
            while (m <= xSize || n <= ySize) {
                tablePath.addNode(new Node(m, n));
                if(m < xSize && n < ySize){
                    int rnum = randomAdd();
                    if (rnum == 0) m++; else n++;
                } else if(m < xSize){
                    m++;
                } else if(n < ySize){
                    n++;
                } else {
                    break;
                }
            }
            if(!paths.contains(tablePath)) paths.add(tablePath);
        }
        System.out.println(paths.size());
    }

    private void addNode(Node node) {
        if(!this.nodes.contains(node)) {
            this.nodes.add(node);
        }
    }

    public static int randomAdd(){
        Random r = new Random();
        return r.nextInt(2);
    }


    @Data
    @AllArgsConstructor
    static class Node {
        private int i;

        private int j;

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Node)){
                return false;
            }
            Node node = (Node) o;
            return node.i == this.i && node.j == this.j;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof TablePath) || o == null){
            return false;
        }
        TablePath tablePath = (TablePath) o;

        List<Node> nodes = tablePath.nodes;
        if(nodes.size() != this.nodes.size()){
            return false;
        }

        for (int x = 0; x < nodes.size(); x++) {
            if(!nodes.get(x).equals(this.nodes.get(x))){
                return false;
            }
        }
        return true;
    }
}
