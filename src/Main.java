import java.util.Random;
public class Main {

    static int MATRIX_SIZE=5;

    //find shortest
    int minDistance(int dist[], Boolean pathset[]){

        // set minimmum to infinity
        int min= Integer.MAX_VALUE;
        int index = -1;

        for (int v = 0; v < MATRIX_SIZE; v++)
            if(pathset[v]== false && dist[v]<= min){
            min = dist[v];
            index = v;
        }
        return index;
    }
    //for debugging, prints the matrix
    void printGraph(int [][] graph){
        for(int x=0; x < MATRIX_SIZE; x++)
            for(int y=0; y < MATRIX_SIZE; y++)
                if(y<MATRIX_SIZE-1)
                    System.out.print(graph[x][y]+",");
                else
                    System.out.println(graph[x][y]);

    }
    //typical print function of adjacency matrix representation of dijkstra's
    void printSolution(int[] dist){
        System.out.println("Vertex\tDistance from src:");
        for (int v=0; v< MATRIX_SIZE; v++)
            if(dist[v]==2147483647) //Our random matrix includes disconnected nodes, this is to fix the print function to compensate
                System.out.println(v+"\t\tNot connected to graph.");
            else
                System.out.println(v+"\t\t"+dist[v]);
    }
    void dijkstra(int graph[][],int src){

        int[] dist= new int[MATRIX_SIZE];
        Boolean sptSet[]= new Boolean[MATRIX_SIZE];

        for(int i=0; i< MATRIX_SIZE;i++){
            dist[i]= Integer.MAX_VALUE;
            sptSet[i]= false;
        }

        dist[src]=0;

        for(int count=0; count< MATRIX_SIZE-1; count++){

            int u = minDistance(dist,sptSet);
            sptSet[u]=true;
            for(int v=0; v< MATRIX_SIZE; v++){
                if(!sptSet[v] && graph[u][v] !=0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v]= dist[u]+ graph[u][v];
            }
        }
       // printSolution(dist);
    }

    //Method to make a randomized adjacency matrix of degree MATRIX_SIZE
public static int[][] largeGraph(){
        int size= MATRIX_SIZE;

        Random r= new Random();

        int graph[][] =new int[size][size];
        int nodes = size;
        for (int x=0; x<size;x++)
            for(int y=0; y<size;y++)
                graph[x][y]=0;

            int x = 0; int y=0;
        while(nodes>0){

            int i= r.nextInt(size-1);
            int j= r.nextInt(size-1);
            if(i!=j){
                int val = r.nextInt(20);
                graph[j][i]= val;
                graph[i][j]= val;
                nodes--;
            }}

            return graph;

}
    public static void main(String[] args) {

        int loops=50;
        int tests[] =  {10,100,1000,10000};

        Main x= new Main();

        //all lines below here are for the test run
        for (int test:tests) {
            MATRIX_SIZE=test;
            System.out.println("The test for "+ test+  " by " + test + " Matrix:");
            //initialize time variables.
            long endtime = 0;
            long startTime = System.nanoTime();
            //run large graph while the clock is on, to match python implementation
            int[][] graph = largeGraph();
            for (int i = 0; i < loops; i++)
                x.dijkstra(largeGraph(), 0);

            endtime = System.nanoTime();

            Long seconds = (endtime - startTime);

            if((seconds).toString().length() >9)
                System.out.println("The test took: " + (seconds)/1_000_000_000 + " Seconds");
            else
                System.out.println("The test took: " + (seconds)/1_000_000 + " Milliseconds");

        }


    }
}
