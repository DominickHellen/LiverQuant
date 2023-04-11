//This .Groovy script is for automated batch detection of cholangiocytes (Immunofluorescence) within liver sections.

//This line of code is placed before and after analyzing each slide
//It is used to pause the current thread for 100 milliseconds
//It also clears accumulated RAM for batch projects
Thread.sleep(100)
javafx.application.Platform.runLater {
getCurrentViewer().getImageRegionStore().cache.clear()
System.gc()
}
Thread.sleep(100)

//Allows Qupath to make decisions for positive staining based on fruoscent staining
setImageType('FLUORESCENCE');

// This should be deleted if you want to annotate by hand
clearSelectedObjects();

// This should be deleted if you want to annotate by hand
//Increase or decrease the 'threshold' number below to improve accuracy of automated tissue annotation
runPlugin('qupath.imagej.detect.tissue.SimpleTissueDetection2', '{"threshold":40,"requestedPixelSizeMicrons":100.0,"minAreaMicrons":10000.0,"maxHoleAreaMicrons":1000000.0,"darkBackground":true,"smoothImage":true,"medianCleanup":false,"dilateBoundaries":true,"smoothCoordinates":true,"excludeOnBoundary":false,"singleAnnotation":true}')

//Selelcts annotations on each slide
selectAnnotations();

//This line of code is specific to identifying cholangiocytes//
//Change 'Threshold: 45.0' lower and higher to select for more and less positive signal, respectively
//Change 'detection image: "Texas Red"' to 'Cy5 MSI' or 'FITC' depending on chosen secondary antibody
//Change 'thresholdCompartment: "Cell: Texas Red mean"' to 'Cell: Cy5 mean' or Cell: FITC mean"' depending on chosen secondary antibody
runPlugin('qupath.imagej.detect.cells.PositiveCellDetection', '{"detectionImage": "Texas Red", "requestedPixelSizeMicrons": 0.3, "backgroundRadiusMicrons": 15.0, "medianRadiusMicrons": 1.0, "sigmaMicrons": 0.5, "minAreaMicrons": 10.0, "maxAreaMicrons": 500.0, "threshold": 45.0, "watershedPostProcess": true, "cellExpansionMicrons": 1.5, "includeNuclei": true, "smoothBoundaries": false, "makeMeasurements": true, "thresholdCompartment": "Cell: Texas Red mean", "thresholdPositive1": 10.0, "thresholdPositive2": 20.0, "thresholdPositive3": 30.0, "singleThreshold": true}');

//Clears RAM and thread after each slide
Thread.sleep(100)
javafx.application.Platform.runLater {
getCurrentViewer().getImageRegionStore().cache.clear()
System.gc()
}
Thread.sleep(100)
