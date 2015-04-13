package com.googlecode.cqengine.examples.codegen;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.codegen.AttributeBytecodeGenerator;
import com.googlecode.cqengine.resultset.ResultSet;
import com.googlecode.cqengine.testutil.Car;
import com.googlecode.cqengine.testutil.CarFactory;
import static com.googlecode.cqengine.query.QueryFactory.equal;

/**
 * Demonstrates how to auto-generate bytecode for CQEngine attributes which access fields in a given class, which
 * can then be used directly at runtime.
 *
 * @author Niall Gallagher
 */
public class GenerateAttributeByteCode {

    public static void main(String[] args) throws Exception {
        // Generate an attribute from bytecode to read the Car.model field...
        Class<? extends SimpleAttribute<Car, String>> carModelAttributeClass = AttributeBytecodeGenerator.generateSimpleAttributeForField(Car.class, String.class, "model", "model");
        SimpleAttribute<Car, String> MODEL = carModelAttributeClass.newInstance();

        // Create a collection of 10 Car objects (Ford Focus, Honda Civic etc.)...
        IndexedCollection<Car> cars = new ConcurrentIndexedCollection<Car>();
        cars.addAll(CarFactory.createCollectionOfCars(10));

        // Retrieve the cars whose Car.model field is "Civic" (i.e. the Honda Civic)...
        ResultSet<Car> results = cars.retrieve(equal(MODEL, "Civic"));
        for (Car car : results) {
            System.out.println(car);
        }
        // ..prints:
        // Car{carId=3, manufacturer='Honda', model='Civic', color=WHITE, doors=5, price=4000.0, features=[grade b]}
    }
}
