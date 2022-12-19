public class FiguresModel {
    public static Integer[][][] I = {
            {{5,0},{5,1},{5,2},{5,3}},
            {{3,1},{4,1},{5,1},{6,1}},
            {{5,0},{5,1},{5,2},{5,3}},
            {{3,1},{4,1},{5,1},{6,1}},
    };
    public static Integer[][][] O = {
            {{4,0},{4,1},{5,0},{5,1}},
            {{4,0},{4,1},{5,0},{5,1}},
            {{4,0},{4,1},{5,0},{5,1}},
            {{4,0},{4,1},{5,0},{5,1}},
    };
    public static Integer[][][] J = {
            {{5,0},{5,1},{5,2},{4,2}},
            {{4,0},{4,1},{5,1},{6,1}},
            {{6,0},{5,0},{5,1},{5,2}},
            {{4,1},{5,1},{6,1},{6,2}}
    };
    public static Integer[][][] L = {
            {{5,0},{5,1},{5,2},{6,2}},
            {{4,2},{4,1},{5,1},{6,1}},
            {{4,0},{5,0},{5,1},{5,2}},
            {{4,1},{5,1},{6,1},{6,0}}
    };
    public static Integer[][][] Z = {
            {{4,0},{5,0},{5,1},{6,1}},
            {{5,0},{5,1},{4,1},{4,2}},
            {{4,0},{5,0},{5,1},{6,1}},
            {{5,0},{5,1},{4,1},{4,2}},
    };
    public static Integer[][][] S = {
            {{6,0},{5,0},{5,1},{4,1}},
            {{5,0},{5,1},{6,1},{6,2}},
            {{6,0},{5,0},{5,1},{4,1}},
            {{5,0},{5,1},{6,1},{6,2}},
    };
    public static Integer[][][] T = {
            {{4,1},{5,1},{5,0},{6,1}},
            {{5,0},{5,1},{5,2},{6,1}},
            {{4,0},{5,0},{5,1},{6,0}},
            {{5,0},{5,1},{5,2},{4,1}},
    };
    public static Integer[][][][] figure = {I,O,L,J,Z,S,T};

    public static Integer[][] get(int i, int j) {
        return figure[i][j];
    }
}