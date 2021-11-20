package CodingTrain;

//邻接表存储
public class GraphOperate {
    public static void main(String[] args){
        GraphOperate g = new GraphOperate();
    }

    class VertexNode{
        int vertex;
        EdgeNode first_edge = new EdgeNode();
    }
    class EdgeNode{
        int vertex;
        EdgeNode next;
    }
    class Graph{
        VertexNode[] nodeList;
        int NodeNum, edgeNum;
    }
    class MatrixGraph{
        int[][] racs;
        public MatrixGraph(int[][] data){
            this.racs = data;
        }
    }
}
