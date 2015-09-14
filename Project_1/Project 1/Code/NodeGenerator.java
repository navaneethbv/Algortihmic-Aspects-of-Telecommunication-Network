public class NodeGenerator {
//integer to identify predecessor node
int Pred;
int dist; /*minimum distance of node from source*/
//stores status - permanent or temporary
int status;
public NodeGenerator() {
super();
}
public int getDistance() {
return dist;
}
public void setDistance(int dist) {
this.dist = dist;
}
public int getPredecessor() {
return Pred;
}
public void setPredecessor(int pred) {
this.Pred = pred;
}
public int getStatus() {
return status;
}
public void setStatus(int status) {
this.status = status;
}
}


