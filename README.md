# Bachelorarbeit: Vergleich zwischen Java 8 und Java 21

Dieses Repository enthält den Code und die Daten, die in meiner Bachelorarbeit verwendet wurden. Das Projekt umfasst die Erstellung von Punktwolken für verschiedene Modelle, die Durchführung von Delaunay-Triangulationstests mit zwei verschiedenen Java-Versionen und die Analyse der Leistungsergebnisse. Das Repository ist in vier Hauptordner unterteilt, die jeweils einem Schritt im Workflow entsprechen.

## Ordnerstruktur

### 1. Create_Data

Dieser Ordner enthält Jupyter-Notebooks, die zur Erstellung der Punktwolken für verschiedene Modelle verwendet werden. Jedes Notebook erzeugt eine CSV-Datei mit den gewünschten Daten.

- **Sphere.ipynb**: Erstellt `Sphere.csv`
- **Floor.ipynb**: Erstellt `Floor.csv`
- **Bunny.ipynb**: Erstellt `Bunny.csv`
- **Terrain_1.ipynb**: Erstellt `Terrain_1.csv`
- **Terrain_2.ipynb**: Erstellt `Terrain_2.csv`

### 2. Delaunay_Triangulation_8

Dieser Ordner enthält die Implementierung der Delaunay-Triangulationstests mit Java Version 8. Die im Schritt `Create_Data` erzeugten CSV-Dateien werden als Eingabedaten verwendet. Die Ergebnisse werden im Ordner `results` gespeichert.

- **Eingabedaten**: Die CSV-Dateien aus `Create_Data` werden im Ordner `data` abgelegt.
- **Ausgabedaten**: Die Ergebnisse für jedes Modell werden als CSV-Dateien im Ordner `results` gespeichert.

### 3. Delaunay_Triangulation_21

Dieser Ordner enthält die Implementierung der Delaunay-Triangulationstests mit Java Version 21. Ähnlich wie bei `Delaunay_Triangulation_8` sind die Eingabedaten die CSV-Dateien aus `Create_Data`, und die Ergebnisse werden im Ordner `results` gespeichert.

- **Eingabedaten**: Die CSV-Dateien aus `Create_Data` werden im Ordner `data` abgelegt.
- **Ausgabedaten**: Die Ergebnisse für jedes Modell werden als CSV-Dateien im Ordner `results` gespeichert.

### 4. Analyse

Dieser Ordner enthält Jupyter-Notebooks zur Analyse der Ergebnisse der Delaunay-Triangulationstests. Die Ergebnisse beider Java-Versionen werden hinsichtlich der Ausführungszeiten und der Garbage Collection (GC)-Leistung verglichen.

- **Initialdaten**: Die 10 CSV-Dateien aus der vorherigen Phase werden im Ordner `initial_data` abgelegt.
- **Ausführungszeiten_Analyse.ipynb**: Analysiert die Ausführungszeiten.
- **GC_Analyse.ipynb**: Analysiert die Garbage Collection Leistung.
- **Ausgabedaten**: Kombinierte Daten für jedes Modell werden im Ordner `combined_data` gespeichert.
- **Plots**: Erzeugte Plots werden im Ordner `performance_comparison` gespeichert.

## Verwendung

1. **Datenerstellung**: Führen Sie die Jupyter-Notebooks im Ordner `Create_Data` aus, um die Punktwolken zu erstellen.
2. **Delaunay-Triangulationstests**:
    - Für Java Version 8: Legen Sie die erzeugten CSV-Dateien im Ordner `data` von `Delaunay_Triangulation_8` ab und führen Sie die Tests aus. Die Ergebnisse werden im Ordner `results` gespeichert.
    - Für Java Version 21: Legen Sie die erzeugten CSV-Dateien im Ordner `data` von `Delaunay_Triangulation_21` ab und führen Sie die Tests aus. Die Ergebnisse werden im Ordner `results` gespeichert.
3. **Datenanalyse**: Verschieben Sie die CSV-Dateien aus den Ordnern `results` in den Ordner `initial_data` in `Analyse`. Führen Sie die Jupyter-Notebooks aus, um die Ausführungszeiten und die Garbage Collection Leistung zu analysieren. Die kombinierten Daten und Plots werden in den entsprechenden Ordnern gespeichert.
