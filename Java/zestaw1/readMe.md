# Object-Oriented Geometry Project

## Description

The Object-Oriented Geometry Project is a Java program that implements three classes representing point, rectangle, and circle geometries. The program performs various operations such as moving points, rectangles, and circles, calculating their areas, checking if a point is inside a rectangle or circle, and checking the intersection of two circles.

## Classes

### Point
- Represents a point on the plane using coordinates x and y.
- Methods:
  - `toString`: Returns the point representation as a string.
  - `move(dx, dy)`: Moves the point by the specified values dx and dy.

### Rectangle
- Represents a rectangle with specified length, width, and top-left vertex.
- Methods:
  - `toString`: Returns the rectangle representation as a string.
  - `move(u, v)`: Moves the rectangle by the specified values u and v.
  - `contains(point)`: Checks if the point is inside the rectangle.
  - `area()`: Calculates the area of the rectangle.

### Circle
- Represents a circle with specified radius and center.
- Methods:
  - `toString`: Returns the circle representation as a string.
  - `move(u, v)`: Moves the circle by the specified values u and v.
  - `contains(point)`: Checks if the point is inside the circle.
  - `area()`: Calculates the area of the circle.
  - `intersects(circle)`: Checks if two circles intersect.

## Usage Instructions

1. Run the program by executing the `Main` class.
2. The program will create various instances of points, rectangles, and circles.
3. Perform various operations such as moving, calculating areas, and checking containment.
4. View the results in the console.

## Additional Notes

- The program is written in Java.
- Object-oriented programming classes and methods are used to represent geometry and perform operations on objects.
- Feel free to experiment and modify the code according to your needs.

---

**Enjoy exploring geometric concepts with this project!**
