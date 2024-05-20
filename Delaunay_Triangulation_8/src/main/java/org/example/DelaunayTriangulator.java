package org.example;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;
import java.util.ArrayList;
import java.util.List;

public class DelaunayTriangulator {
    private GeometryFactory geomFactory = new GeometryFactory();
    private List<Coordinate> coordinates = new ArrayList<>();

    public void addPoint(double x, double y, double z) {
        coordinates.add(new Coordinate(x, y, z));
    }

    public void performTriangulation() {
        DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
        builder.setSites(geomFactory.createMultiPointFromCoords(coordinates.toArray(new Coordinate[0])));
        Geometry triangles = builder.getTriangles(geomFactory);

        // Ausgabe der Triangulation
        //System.out.println(triangles);
    }
}
