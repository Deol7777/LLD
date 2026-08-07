package Inheritance;

class Shape {
    protected String name;

    public Shape(String name) {
        this.name = name;
    }

    public double area() {
        // TODO: return 0 by default
        return 0;
    }

    public double perimeter() {
        // TODO: return 0 by default
        return 0;
    }

    public void describe() {
        // TODO: print "Shape: name, Area: area, Perimeter: perimeter"
        // Hint: use String.format("%.2f", value) for formatting
        System.out.println(
                "Shape: " + name
                        + ", Area: " + String.format("%.2f", area())
                        + ", Perimeter: " + String.format("%.2f", perimeter()));
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * this.radius;

    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        super("Rectangle");
        this.height = height;
        this.width = width;
    }

    @Override
    public double area() {
        return width * height;

    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}