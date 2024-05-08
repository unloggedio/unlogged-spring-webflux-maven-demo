package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecordPatternController {

    private record Point(int x, int y) {}
    private record Line(Point start, Point end) {}

    //Not completely reactive but we wanted to test the destructuring of records
    @RequestMapping("/points/sum/{x}/{y}")
    public Mono<Integer> sumController(@PathVariable int x, @PathVariable int y) {
        Point point = new Point(x, y);
        return Mono.just(point).map(this::sumOfCoordinates);
    }

    @RequestMapping("/points/length")
    public Mono<Integer> lengthController() {
        Line line = new Line(new Point(1, 1), new Point(2, 2));
        return Mono.just(line).map(this::lengthOfLine);
    }

    private int sumOfCoordinates(Point point) {
        if(point instanceof Point(int x, int y)) {
            return x + y;
        }
        return -999;
    }
    private int lengthOfLine(Line l1) {
        if(l1 instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }
        return -999;
    }

    private boolean checkEqualPoints(Point point1, Point point2) {
        return point1.equals(point2);
    }
}
