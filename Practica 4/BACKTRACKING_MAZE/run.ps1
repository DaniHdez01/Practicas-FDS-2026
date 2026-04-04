$ErrorActionPreference = "Stop"

Write-Host "Compilando clases Java..."
javac --release 8 *.java

if ($LASTEXITCODE -ne 0) {
    Write-Error "Falló la compilación."
    exit $LASTEXITCODE
}

Write-Host "Empaquetando JAR..."
jar cfm MazeApp.jar MANIFEST.MF *.class

if ($LASTEXITCODE -ne 0) {
    Write-Error "Falló el empaquetado del JAR."
    exit $LASTEXITCODE
}

Write-Host "Ejecutando JAR..."
java -jar .\MazeApp.jar
exit $LASTEXITCODE