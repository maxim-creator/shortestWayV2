public class NodeForAStarAlgorithm extends Node {
    private double heuristicDistanceToFinish;
    private double distanceFromStart;
    private double distanceSum;

    public NodeForAStarAlgorithm(int x, int y) {
        super(x, y);
    }

    public double getDistanceSum() {
        return distanceSum;
    }

    public void setDistanceSum(double distanceSum) {
        this.distanceSum = distanceSum;
    }

    public double getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(double distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public double getHeuristicDistanceToFinish() {
        return heuristicDistanceToFinish;
    }

    public void setHeuristicDistanceToFinish(double heuristicDistanceToFinish) {
        this.heuristicDistanceToFinish = heuristicDistanceToFinish;
    }

    public void countDistanceSum() {
        distanceSum = heuristicDistanceToFinish + distanceFromStart;
    }


}
