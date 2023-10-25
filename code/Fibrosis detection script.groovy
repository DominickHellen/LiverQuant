//This .Groovy script is for automated batch detection of fibrosis within liver sections.//

//This line of code is placed before and after analyzing each slide//
//It is used to pause the current thread for 100 milliseconds//
//It also clears accumulated RAM for batch projects//
Thread.sleep(100)
javafx.application.Platform.runLater {
getCurrentViewer().getImageRegionStore().cache.clear()
System.gc()
}
Thread.sleep(100)

//Allows Qupath to make decisions for positive staining based on staining that is residual to DAB and hematoxylin//
setImageType('BRIGHTFIELD_OTHER');

// This should be deleted if you want to annotate by hand
clearSelectedObjects();

// This should be deleted if you want to annotate by hand
//Increase or decrease the 'threshold' number below to improve accuracy of automated tissue annotation
runPlugin('qupath.imagej.detect.tissue.SimpleTissueDetection2', '{"threshold":178,"requestedPixelSizeMicrons":200.0,"minAreaMicrons":10000.0,"maxHoleAreaMicrons":1000000.0,"darkBackground":false,"smoothImage":false,"medianCleanup":true,"dilateBoundaries":false,"smoothCoordinates":true,"excludeOnBoundary":false,"singleAnnotation":true}')

//Selects annotations on each slide//
selectAnnotations();

//Quantifies positive pixel amount (Fibrotic septa) for entire all annotations//
addPixelClassifierMeasurements("LiverQuantF", "LiverQuantF")

//Clears RAM and thread after each slide//
Thread.sleep(100)
javafx.application.Platform.runLater {
getCurrentViewer().getImageRegionStore().cache.clear()
System.gc()
}
Thread.sleep(100)
