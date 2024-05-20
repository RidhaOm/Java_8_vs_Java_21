package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TriangulationTest {
    public static void main(String[] args) throws IOException {
        String[] dataFileNames = {"Sphere.csv", "Floor.csv", "Bunny.csv", "Terrain_1.csv", "Terrain_2.csv"};
        List<GarbageCollectorMXBean> gcMxBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (String dataFileName : dataFileNames) {
            DelaunayTriangulator triangulator = new DelaunayTriangulator();
            BufferedReader reader = new BufferedReader(new FileReader("data/" + dataFileName));
            String line;
            List<String[]> results = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                double z = Double.parseDouble(parts[2]);
                triangulator.addPoint(x, y, z);
            }
            reader.close();

            System.out.println("Starte Triangulation für " + dataFileName);

            // 51 Durchläufe ausführen, ignoriert aber den ersten Durchlauf für die Berechnungen
            for (int i = 0; i < 51; i++) {
                Map<String, Long> gcCountsBefore = getGCInfo(gcMxBeans);
                Map<String, Long> gcTimesBefore = getGCTime(gcMxBeans);

                long startTime = System.nanoTime();
                triangulator.performTriangulation();
                long endTime = System.nanoTime();

                Map<String, Long> gcCountsAfter = getGCInfo(gcMxBeans);
                Map<String, Long> gcTimesAfter = getGCTime(gcMxBeans);

                long duration = (endTime - startTime) / 1_000_000;
                if (i > 0) {
                    System.out.println("Ausführungszeit für Durchlauf " + i + ": " + duration + " ms");
                    List<String> rowData = new ArrayList<>();
                    rowData.add(Integer.toString(i));
                    rowData.add(Long.toString(duration));
                    for (GarbageCollectorMXBean bean : gcMxBeans) {
                        String gcName = bean.getName();
                        long collectionsDiff = gcCountsAfter.get(gcName) - gcCountsBefore.get(gcName);
                        long timeDiff = gcTimesAfter.get(gcName) - gcTimesBefore.get(gcName);
                        System.out.println(gcName + ": Collections für Durchlauf " + i + ": " + collectionsDiff);
                        System.out.println(gcName + ": Zeit für Durchlauf " + i + ": " + timeDiff + " ms");
                        rowData.add(Long.toString(collectionsDiff));
                        rowData.add(Long.toString(timeDiff));
                    }
                    results.add(rowData.toArray(new String[0]));
                }
            }

            // Speichern der Ergebnisse in eine CSV-Datei
            try (PrintWriter pw = new PrintWriter(new FileWriter("results/" + dataFileName.replace(".csv", "_results_8.csv")))) {
                // Header der CSV
                List<String> headers = new ArrayList<>();
                headers.add("Durchlauf");
                headers.add("Dauer in ms");
                for (GarbageCollectorMXBean bean : gcMxBeans) {
                    String gcName = bean.getName();
                    headers.add(gcName + " Collections");
                    headers.add(gcName + " Zeit");
                }
                pw.println(String.join(",", headers));

                // Datenzeilen
                for (String[] result : results) {
                    pw.println(String.join(",", result));
                }
            }
        }
    }

    private static Map<String, Long> getGCInfo(List<GarbageCollectorMXBean> gcBeans) {
        return gcBeans.stream().collect(Collectors.toMap(GarbageCollectorMXBean::getName, GarbageCollectorMXBean::getCollectionCount));
    }

    private static Map<String, Long> getGCTime(List<GarbageCollectorMXBean> gcBeans) {
        return gcBeans.stream().collect(Collectors.toMap(GarbageCollectorMXBean::getName, GarbageCollectorMXBean::getCollectionTime));
    }
}