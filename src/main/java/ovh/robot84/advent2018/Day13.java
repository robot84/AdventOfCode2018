package ovh.robot84.advent2018;

import java.util.ArrayList;
import java.util.List;

public class Day13 {
final static int DIRECTION_LEFT = 5;
final static int DIRECTION_STRAIGHT = 10;
final static int DIRECTION_RIGHT = 15;
final static int DIRECTION_NORTH = 100;
final static int DIRECTION_EAST = 150;
final static int DIRECTION_SOUTH = 200;
final static int DIRECTION_WEST = 250;
final static int CART_STATE_1 = 51;
final static int CART_STATE_2 = 52;
final static int CART_STATE_3 = 53;
private static final int MAX_CART_STATE = 3;
private List<Cart> cartList = new ArrayList<>();
private List<Curve> curveList = new ArrayList<>();
private List<TrackField> fieldList = new ArrayList<>();
private List<Intersection> intersectionList = new ArrayList<>();


static public void main(String[] args) {

// utworz sie i skok do start
}


void start() {

    //  cartList.sort();
    for (Cart cart : cartList) {
    }
    //moveCart(cart);
}


void moveCart(Cart cart) {

// TODO
    switch (cart.direction) {
        case DIRECTION_NORTH:
            cart.y--;
            break;
        case DIRECTION_EAST:
            cart.x++;
            break;
        case DIRECTION_SOUTH:
            cart.y++;
            break;
        case DIRECTION_WEST:
            cart.x--;
            break;
        default:

            for (TrackField track : fieldList)
                if (track.x == cart.x && track.y == cart.y) {
                    if (track.isCartHere) processCrash();
// pozniej update tracka zrob, ze tam stanales! a jak sobie pojdziesz, to ze odszedles!
                    if (track.isIntersection) processIntersection(cart);
                    if (track.isCurve) processCurve(cart);
                }
// nie mozliwe ze nie ma!
    }
}


private void processCurve(Cart cart) {
    {
        for (Curve curve : curveList)
            if (curve.x == cart.x && curve.y == cart.y) {
                switch (curve.direction) {
                    case DIRECTION_LEFT:
                        if (cart.direction == DIRECTION_NORTH)
                            cart.direction = DIRECTION_WEST;
                        else if (cart.direction == DIRECTION_EAST)
                            cart.direction = DIRECTION_NORTH;
                        else if (cart.direction == DIRECTION_SOUTH)
                            cart.direction = DIRECTION_EAST;
                        else if (cart.direction == DIRECTION_WEST)
                            cart.direction = DIRECTION_SOUTH;
                        break;
                    case DIRECTION_RIGHT:
                        if (cart.direction == DIRECTION_NORTH)
                            cart.direction = DIRECTION_EAST;
                        else if (cart.direction == DIRECTION_EAST)
                            cart.direction = DIRECTION_SOUTH;
                        else if (cart.direction == DIRECTION_SOUTH)
                            cart.direction = DIRECTION_WEST;
                        else if (cart.direction == DIRECTION_WEST)
                            cart.direction = DIRECTION_NORTH;
                        break;
                    default:
                }
            }
    }
}


private void processIntersection(Cart cart) {
    {
        switch (cart.state) {
            case CART_STATE_1:
                cart.direction = DIRECTION_LEFT;
                break;
            case CART_STATE_2:
                cart.direction = DIRECTION_STRAIGHT;
                break;
            case CART_STATE_3:
                cart.direction = DIRECTION_RIGHT;
                break;

            default:
        }
        if (cart.state < MAX_CART_STATE) cart.state++;
        else cart.state = 0;

    }
}


void processCrash() {
}


/*  class CartSorter implements Comparator {

      @Override
      public int compare(Cart o1, Cart o2) {
          return 0;
      }
  }
*/
class Cart implements Comparable<Cart> {
    int position;
    int x, y, state, direction, priority;


    Cart(int x, int y, int state, int direction, int priority) {
        this.x = x;
        this.y = y;
        this.state = state;
        this.direction = direction;
        this.priority = priority;
    }


    @Override
    public int compareTo(Cart o) {
        return (o.y - this.y) * 1000 + (o.x - this.x);

    }
}

class TrackField {
    boolean isIntersection;
    boolean isCurve;
    boolean isCartHere;
    int curveDirection;
    int cartNumHere;
    int y = 0;
    int x = 0;


    public TrackField(int x, int y, boolean isCurve, boolean isIntersection) {
        this.x = x;
        this.y = y;
        this.isIntersection = isIntersection;
        this.isCurve = isCurve;
    }


    void tracksLoad() {
// get map field by field as one char string

        String s = null;
        switch (s) {
            case "|":
            case "-":
// do nothing
                break;
            case "/":
                curveList.add(new Curve(x, y, 1));
                fieldList.add(new TrackField(x, y, true, false));
                break;
            case "\\":
                curveList.add(new Curve(x, y, 2));
                fieldList.add(new TrackField(x, y, true, false));
                break;
            case "+":
                intersectionList.add(new Intersection(x, y));
                fieldList.add(new TrackField(x, y, false, true));
                break;
            case "v":
                cartList.add(new Cart(x, y, 0, DIRECTION_SOUTH, 1000));
                break;
            case "^":
                cartList.add(new Cart(x, y, 0, DIRECTION_NORTH, 1000));
                break;
            case "<":
                cartList.add(new Cart(x, y, 0, DIRECTION_WEST, 1000));
                break;
            case ">":
                cartList.add(new Cart(x, y, 0, DIRECTION_EAST, 1000));
                break;
            default:
// do nothing
        }
        x++;
//when newline:
        y++;
    }

}

class Curve {
    int x, y;
    int direction;


    public Curve(int x, int y, int i) {
        this.x = x;
        this.y = y;
        this.direction = i;
    }
}

private class Intersection {
    int x, y;


    public Intersection(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
}
