package CodingTrain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class MatrixGraphOperate {
    int[] vexs;
    int[][] edges;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MatrixGraphOperate mgo = new MatrixGraphOperate();
        MatrixGraph mg = mgo.createGraph(sc);
        ArrayList<Integer> list = new ArrayList<>();
        mgo.prim(mg);
        /*mgo.bfs(mg, 0, list);
        System.out.println("bfs:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)+" ");
        }*/
    }

    //prim to get minimal spanning tree
    public void prim(MatrixGraph mg){
        int start = 0, end=0, count = 1;
        mg.visited[start] = true;
        ArrayList<Integer> visitedList = new ArrayList<>();
        visitedList.add(start);
        while (count < mg.vexs.length){
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < visitedList.size(); i++) {
                int tmp = visitedList.get(i);
                int next  = getFirst(mg, tmp);
                while (next != -1){
                    if(!mg.visited[next] && mg.edges[tmp][next]<=min){
                        start = tmp;
                        min = mg.edges[tmp][next];
                        end = next;
                    }
                    next = getNext(mg, tmp, next);
                }
            }
            ++ count;
            mg.visited[end] = true;
            visitedList.add(end);
            System.out.println(mg.vexs[start]+" "+mg.vexs[end]+" "+min);
        }
    }

    //bfs
    public void bfs(MatrixGraph mg ,int idx, ArrayList<Integer> list){
        Queue<Integer> queue = new ArrayDeque<>();
        mg.visited[idx] = true;
        int next = getFirst(mg, idx);
        list.add(mg.vexs[idx]);
        queue.add(next);
        while (!queue.isEmpty()){
            while (next != -1){
                if(!mg.visited[next]){
                    queue.add(next);
                    list.add(mg.vexs[next]);
                    mg.visited[next] = true;
                }
                next = getNext(mg, idx, next);
            }
            idx = queue.poll();
            next = getFirst(mg, idx);
        }
    }

    //dfs
    public void dfs(MatrixGraph mg, int idx, ArrayList<Integer> list){
        if (!mg.visited[idx])
            mg.visited[idx] = true;
        list.add(mg.vexs[idx]);
        int next = getFirst(mg, idx);
        while (next != -1){
            if(!mg.visited[next])
                dfs(mg, next, list);
            next = getNext(mg, idx, next);
        }
    }

    public int getFirst(MatrixGraph mg, int v){
        for (int i = 0; i < mg.vexs.length; i++) {
            if(v!=i && mg.edges[v][i]!=0){
                return i;
            }
        }
        return -1;
    }
    public int getNext(MatrixGraph mg, int v, int next){
        for (int i = next+1; i < mg.vexs.length; i++) {
            if(v!=i && mg.edges[v][i]!=0){
                return i;
            }
        }
        return -1;
    }

    public MatrixGraph createGraph(Scanner sc){
        int vexsNum = sc.nextInt();
        int[] vexs = new int[vexsNum];
        for (int i = 0; i < vexsNum; i++) {
            vexs[i] = sc.nextInt();
        }
        int edgeNum = sc.nextInt();
        int[][] edges = new int[vexsNum][vexsNum];
        for (int i = 0; i < edgeNum; i++) {
            int startIdx = sc.nextInt() - 1;
            int endIdx = sc.nextInt() - 1;
            int value = sc.nextInt();
            if(startIdx != endIdx){
                edges[startIdx][endIdx] = value;
                edges[endIdx][startIdx] = value;
            }
        }
        return new MatrixGraph(vexs, edges);
    }
    class MatrixGraph{
        int[] vexs;
        int[][] edges;
        boolean[] visited;
        public MatrixGraph(int[] vexs, int[][] edges){
            this.edges = edges;
            this.vexs = vexs;
            this.visited = new boolean[vexs.length];
        }
    }

}
