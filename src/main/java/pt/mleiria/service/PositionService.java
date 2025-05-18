package pt.mleiria.service;


import java.util.function.BiPredicate;
import java.util.function.Function;

@FunctionalInterface
public interface PositionService<T, R> {
    // Define a BiPredicate that compares two doubles with the given tolerance.
    double epsilon = 0.000001;
    BiPredicate<Double, Double> nearlyEqual = (a, b) -> Math.abs(a - b) < epsilon;
    Function<Integer, Double> convertDivide = x -> x / Math.pow(10, 7);
    Function<Double, Integer> convertMult = x -> (int) (x * Math.pow(10, 7));
    BiPredicate<Integer, Double> nearlyEqualConvertDivide = (x, y) -> nearlyEqual.test(convertDivide.apply(x), y);


    R process(T t);
}


