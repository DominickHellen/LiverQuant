//This` .Groovy script is for automated batch detection of macrophages within liver sections.//

//This line of code is placed before and after analyzing each slide//
//It is used to pause the current thread for 100 milliseconds//
//It also clears accumulated RAM for batch projects//
Thread.sleep(100)
javafx.application.Platform.runLater {
getCurrentViewer().getImageRegionStore().cache.clear()
System.gc()
}
Thread.sleep(100)

//Allows Qupath to make decisions for positive staining based on DAB (brown) staining//
setImageType('BRIGHTFIELD_H_DAB');

// This should be deleted if you want to annotate by hand
clearSelectedObjects();

// This should be deleted if you want to annotate by hand
//Increase or decrease the 'threshold' number below to improve accuracy of automated tissue annotation
runPlugin('qupath.imagej.detect.tissue.SimpleTissueDetection2', '{"threshold":187,"requestedPixelSizeMicrons":100.0,"minAreaMicrons":10000.0,"maxHoleAreaMicrons":1000000.0,"darkBackground":false,"smoothImage":false,"medianCleanup":true,"dilateBoundaries":false,"smoothCoordinates":true,"excludeOnBoundary":true,"singleAnnotation":true}')

//Selects annotations on each slide//
selectAnnotations();

//Creates objects within each annotation based on the threshold set for 'Macrophages' classifier//
createDetectionsFromPixelClassifier("LiverQuantM", 30.0, 0.0, "SPLIT", "DELETE_EXISTING")

//Clears RAM and thread after each slide//
Thread.sleep(100)
javafx.application.Platform.runLater {
getCurrentViewer().getImageRegionStore().cache.clear()
System.gc()
}
Thread.sleep(100)

