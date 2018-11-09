//注：算法代码较详细，部分简单函数没有给方法体。
/**
 * 连连看求最短路径算法，拐弯最少的基础上求步数最少
 * 对外三个方法
 * 1、构造：根据地图信息构造Search对象
 * 2、search（）方法，根据起点终点搜最优路径并返回
 * 3、setTwoKongbai（），设置两点为空白值
 */
public class Search {
    public int a[][];  //地图,取名map更合适
    int mapWidth; //地图宽
    int mapHeight; //地图高
    public int nowMinB = 10000; //当前最少步数
    public int nowMinG = 10000; //当前最少拐弯次数
    private final int limitB = 100;  //步数的值限制
    private final int limitG = 5;   //拐弯次数限制
    private Point targetP = null;  //终点
    private Point startP = null; //起点
    //方向对应的点坐标变化，下标一对应方向，下标二取0为x变化值，取1为y变化值
    // 如向上，则x变化值为fxMapPC[0][0], y为fxMapPC[0][1]
    private final int[][] fxMapPC = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private Route routeTemp = new Route(limitB + 1); //递归过程中存单条路径用的容器
    public List<Route> routeList = new ArrayList<Route>(); //存储所有到达终点的路径，不可能最优的路径除外
    String[] fxString = {"上", "右", "下", "左"}; //0，1，2，3与方向的对应关系
    final int noFx = 100;  //没有方向的值
    public int kongBai = -1; //空白的值

    //设置地图并创建Search对象，最后一个参数指空白对应的值
    public  Search(int[][] map, int mapHeight, int mapWidth, int kongBai) {
        a=map;
        this.mapHeight=mapHeight;
        this.mapWidth=mapWidth;
        this.kongBai=kongBai;
    }

    public void setTwoKongbai(Point a,Point b){
//        将地图上两点设置为空白值
    }

    /**
     * 注：围墙指包裹地图一圈，且连线可以走的格子
     * 作用：判断点与围墙关系，简单，用位置和地图宽高比较就行了
     * 返回值-1，0，1
     * -1 围墙内（也就是地图上）   0 围墙上    1 围墙外（在不可连线的区域）
     */
    private int inOnOutWall(Point p) {
        if (p.x < mapWidth && p.x >= 0)
            if (p.y < mapHeight && p.y >= 0)
                return -1;
        if (p.x > mapWidth || p.x < -1)
            return 1;
        if (p.y > mapHeight || p.y < -1)
            return 1;
        return 0;
    }

    private Route getBestRoute(){
        //在routeList 中找到拐弯最少且最短的路径并返回
    }

    public Route search(Point startP, Point targetP) {  //根据起点和终点搜索路径并存储路径
        nowMinB = 10000; //当前最少步数
        nowMinG = 10000; //当前最少拐弯次数
        this.targetP = targetP;
        this.startP = startP;
        routeList.clear();  //清空路径集合，然后用来存储本次搜索出的所有路径
        digui(noFx, 0, 0, this.startP);
        return getBestRoute();
    }

    /**
     * 作用：用递归回溯法求路径，并存储路径。每调用一次，，则向三个方向走一步
     * @param preFx 上一次的方向，取值noFx（常量），0，1，2，3；noFx对应无方向，0上，1右，2下，3左
     * @param numG  拐弯次数
     * @param numB  步数，到达当前点经过格子数
     * @param nowP  当前点坐标
     先判断当前位置是否合法，再判断是否到达终点，如果都不是，则向三个方向都走一步，进入下一层。
    方法体思路：
    {
    1 如果超过步数或者拐弯次数限制，则递归结束
    2 如果超过当前最优步数，结束
    3 如果步数等于当前最优，但拐弯次数超过当前最优次数，结束
    4.1 如果当前位置超出围墙，则结束
    4.2 如果在围墙内，且不是目标且不是空格，则结束
    4.3 如果在围墙上，do nothing
    5 （经过上面测试，可知当前位置是可走的）记录当前位置到路径中
    6.1 如果找到目标，把路径信息（包括每一步以及步数和拐弯次数）存到集合中，
        并更新当前最优步数和拐弯次数，结束
    6.2 否则进入下一层，三个方向都可以走（除原方向的反方向外），只要保证走的地方是空格或者地图外或者目标，每走一步进入下一层。
    }
     */
    private void digui(int preFx, int numG, int numB, Point nowP) {
        if (numB > limitB || numG > limitG)  //超过步数或者拐弯次数限制，则递归结束
            return;
            if (numG > nowMinG)  //超过当前搜到终点的最优的拐弯次数，结束
                return;
            if (numG == nowMinG && numB > nowMinB)  //步数等于最优，但拐弯次数多，结束
                return;

        if (inOnOutWall(nowP) == 1) //超出围墙，结束
            return;
            //在围墙内，且不是空格且不是目标且不是第0步，结束
        else if (inOnOutWall(nowP) == -1 && !nowP.biJiao(targetP) && a[nowP.y][nowP.x] != kongBai && numB != 0)
            return;
        //围墙上，什么都不做,继续后面操作

        //经过上面测试，可知当前位置是可走的,此处记录当前位置和方向到路径中
        //第一个元素记录的是起点
        routeTemp.fxs[numB] = preFx;
        routeTemp.points[numB] = nowP;

        if (nowP.biJiao(targetP)) //找到目标，存储路径并结束
        {
            //把路径存在集合里面，并记录当前最优步数和拐弯次数
            Route route = routeTemp.copyRoute(routeTemp, numB + 1, numG);
            routeList.add(route);
            nowMinB = numB;
            nowMinG = numG;
            return;
        }

        for (int i = 0; i < 4; i++)//分别试每个方向
            if (preFx - i != -2 && preFx - i != 2)//不走原方向反向
            {
                if (preFx == i || preFx == noFx)//同向,或者起点，拐弯次数不增加
                    digui(i, numG, numB + 1, new Point(nowP.x + fxMapPC[i][0], nowP.y + fxMapPC[i][1]));
                else  //拐弯
                    digui(i, numG + 1, numB + 1, new Point(nowP.x + fxMapPC[i][0], nowP.y + fxMapPC[i][1]));
            }
    }
}
class Point {
    int x; //横坐标，对应数组第二个下标
    int y;//纵坐标，数组第一个下标
    boolean bijiao(Point p){
     //判断两点x，y是否相等
    }
}
class Route {
    public int[] fxs = null;//方向数组
    public luoji.Point[] points = null;//经过的点构成的数组
    public int sumTrun;//转向次数
    int lenth;
}